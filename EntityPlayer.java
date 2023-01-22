// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityPlayer.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.ChunkCompressionThread;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

// Referenced classes of package net.minecraft.server:
//            EntityHuman, ItemStack, WorldServer, ItemInWorldManager, 
//            Packet5EntityEquipment, Packet3Chat, EntityDamageSource, EntityArrow, 
//            ItemWorldMapBase, ChunkCoordIntPair, Packet51MapChunk, TileEntity, 
//            Packet8UpdateHealth, Packet43SetExperience, EntityItem, Packet22Collect, 
//            EntityExperienceOrb, Packet18ArmAnimation, Packet17, Packet39AttachEntity, 
//            Packet100OpenWindow, ContainerWorkbench, ContainerChest, ContainerFurnace, 
//            ContainerDispenser, SlotResult, Packet103SetSlot, Packet104WindowItems, 
//            Packet105CraftProgressBar, Packet101CloseWindow, Packet200Statistic, Packet38EntityStatus, 
//            Packet41MobEffect, Packet42RemoveMobEffect, ICrafting, World, 
//            ChunkCoordinates, WorldProvider, NBTTagCompound, Container, 
//            MinecraftServer, EntityTracker, InventoryPlayer, DamageSource, 
//            ServerConfigurationManager, Item, NetServerHandler, FoodMetaData, 
//            Entity, EnumBedError, IInventory, TileEntityFurnace, 
//            TileEntityDispenser, Statistic, StatisticStorage, EnumAnimation, 
//            MobEffect

public class EntityPlayer extends EntityHuman
    implements ICrafting
{

    public EntityPlayer(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager)
    {
        super(world);
        chunkCoordIntPairQueue = new LinkedList();
        playerChunkCoordIntPairs = new HashSet();
        cb = 0xfa0a1f01;
        cc = 0xfa0a1f01;
        cd = true;
        ce = 0xfa0a1f01;
        cf = 60;
        ch = 0;
        newExp = 0;
        timeOffset = 0L;
        relativeTime = true;
        iteminworldmanager.player = this;
        itemInWorldManager = iteminworldmanager;
        ChunkCoordinates chunkcoordinates = world.getSpawn();
        int i = chunkcoordinates.x;
        int j = chunkcoordinates.z;
        int k = chunkcoordinates.y;
        if(!world.worldProvider.e)
        {
            i += random.nextInt(20) - 10;
            k = world.f(i, j);
            j += random.nextInt(20) - 10;
        }
        setPositionRotation((double)i + 0.5D, k, (double)j + 0.5D, 0.0F, 0.0F);
        b = minecraftserver;
        bI = 0.0F;
        name = s;
        height = 0.0F;
        displayName = name;
        listName = name;
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        if(nbttagcompound.hasKey("playerGameType"))
            itemInWorldManager.a(nbttagcompound.e("playerGameType"));
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("playerGameType", itemInWorldManager.a());
    }

    public void spawnIn(World world)
    {
        super.spawnIn(world);
        if(world == null)
        {
            dead = false;
            ChunkCoordinates position = null;
            if(spawnWorld != null && !spawnWorld.equals(""))
            {
                CraftWorld cworld = (CraftWorld)Bukkit.getServer().getWorld(spawnWorld);
                if(cworld != null && getBed() != null)
                {
                    world = cworld.getHandle();
                    position = EntityHuman.getBed(cworld.getHandle(), getBed());
                }
            }
            if(world == null || position == null)
            {
                world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
                position = world.getSpawn();
            }
            this.world = world;
            setPosition((double)position.x + 0.5D, position.y, (double)position.z + 0.5D);
        }
        dimension = ((WorldServer)this.world).dimension;
        int oldMode = itemInWorldManager.a();
        itemInWorldManager = new ItemInWorldManager((WorldServer)world);
        itemInWorldManager.player = this;
        itemInWorldManager.a(oldMode);
    }

    public void syncInventory()
    {
        activeContainer.a(this);
    }

    public ItemStack[] getEquipment()
    {
        return cg;
    }

    protected void m_()
    {
        height = 0.0F;
    }

    public float t()
    {
        return 1.62F;
    }

    public void s_()
    {
        itemInWorldManager.c();
        cf--;
        activeContainer.a();
        for(int i = 0; i < 5; i++)
        {
            ItemStack itemstack = b(i);
            if(itemstack != cg[i])
            {
                b.getTracker(dimension).a(this, new Packet5EntityEquipment(id, i, itemstack));
                cg[i] = itemstack;
            }
        }

    }

    public ItemStack b(int i)
    {
        return i != 0 ? inventory.armor[i - 1] : inventory.getItemInHand();
    }

    public void die(DamageSource damagesource)
    {
        List loot = new ArrayList();
        for(int i = 0; i < inventory.items.length; i++)
            if(inventory.items[i] != null)
                loot.add(new CraftItemStack(inventory.items[i]));

        for(int i = 0; i < inventory.armor.length; i++)
            if(inventory.armor[i] != null)
                loot.add(new CraftItemStack(inventory.armor[i]));

        PlayerDeathEvent event = CraftEventFactory.callPlayerDeathEvent(this, loot, damagesource.a(this));
        String deathMessage = event.getDeathMessage();
        if(deathMessage != null && deathMessage.length() > 0)
            b.serverConfigurationManager.sendAll(new Packet3Chat(event.getDeathMessage()));
        for(int i = 0; i < inventory.items.length; i++)
            inventory.items[i] = null;

        for(int i = 0; i < inventory.armor.length; i++)
            inventory.armor[i] = null;

        closeInventory();
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(cf > 0)
            return false;
        if(!world.pvpMode && (damagesource instanceof EntityDamageSource))
        {
            Entity entity = damagesource.getEntity();
            if(entity instanceof EntityHuman)
                return false;
            if(entity instanceof EntityArrow)
            {
                EntityArrow entityarrow = (EntityArrow)entity;
                if(entityarrow.shooter instanceof EntityHuman)
                    return false;
            }
        }
        return super.damageEntity(damagesource, i);
    }

    protected boolean n_()
    {
        return b.pvpMode;
    }

    public void c(int i)
    {
        super.c(i, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.EATING);
    }

    public void b(boolean flag)
    {
        super.s_();
        for(int i = 0; i < inventory.getSize(); i++)
        {
            ItemStack itemstack = inventory.getItem(i);
            if(itemstack == null || !Item.byId[itemstack.id].i_() || netServerHandler.b() > 2)
                continue;
            Packet packet = ((ItemWorldMapBase)Item.byId[itemstack.id]).c(itemstack, world, this);
            if(packet != null)
                netServerHandler.sendPacket(packet);
        }

        if(flag && !chunkCoordIntPairQueue.isEmpty())
        {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)chunkCoordIntPairQueue.get(0);
            if(chunkcoordintpair != null)
            {
                boolean flag1 = false;
                if(netServerHandler.b() + ChunkCompressionThread.getPlayerQueueSize(this) < 4)
                    flag1 = true;
                if(flag1)
                {
                    WorldServer worldserver = b.getWorldServer(dimension);
                    chunkCoordIntPairQueue.remove(chunkcoordintpair);
                    NetServerHandler netserverhandler = netServerHandler;
                    int j = chunkcoordintpair.x * 16;
                    int k = chunkcoordintpair.z * 16;
                    worldserver.getClass();
                    Packet51MapChunk packet51mapchunk = new Packet51MapChunk(j, 0, k, 16, 128, 16, worldserver);
                    netserverhandler.sendPacket(packet51mapchunk);
                    int l = chunkcoordintpair.x * 16;
                    j = chunkcoordintpair.z * 16;
                    int i1 = chunkcoordintpair.x * 16 + 16;
                    worldserver.getClass();
                    List list = worldserver.getTileEntities(l, 0, j, i1, 128, chunkcoordintpair.z * 16 + 16);
                    for(int j1 = 0; j1 < list.size(); j1++)
                        a((TileEntity)list.get(j1));

                }
            }
        }
        if(I)
        {
            if(activeContainer != defaultContainer)
                closeInventory();
            if(vehicle != null)
            {
                mount(vehicle);
            } else
            {
                J += 0.0125F;
                if(J >= 1.0F)
                {
                    J = 1.0F;
                    H = 10;
                    b.serverConfigurationManager.f(this);
                    ce = -1;
                    cb = -1;
                    cc = -1;
                }
            }
            I = false;
        } else
        {
            if(J > 0.0F)
                J -= 0.05F;
            if(J < 0.0F)
                J = 0.0F;
        }
        if(H > 0)
            H--;
        if(health != cb || cc != foodData.a() || (foodData.c() == 0.0F) != cd)
        {
            netServerHandler.sendPacket(new Packet8UpdateHealth(health, foodData.a(), foodData.c()));
            cb = health;
            cc = foodData.a();
            cd = foodData.c() == 0.0F;
        }
        if(expTotal != ce)
        {
            ce = expTotal;
            netServerHandler.sendPacket(new Packet43SetExperience(exp, expTotal, expLevel));
        }
    }

    private void a(TileEntity tileentity)
    {
        if(tileentity != null)
        {
            Packet packet = tileentity.l();
            if(packet != null)
                netServerHandler.sendPacket(packet);
        }
    }

    public void receive(Entity entity, int i)
    {
        if(!entity.dead)
        {
            EntityTracker entitytracker = b.getTracker(dimension);
            if(entity instanceof EntityItem)
                entitytracker.a(entity, new Packet22Collect(entity.id, id));
            if(entity instanceof EntityArrow)
                entitytracker.a(entity, new Packet22Collect(entity.id, id));
            if(entity instanceof EntityExperienceOrb)
                entitytracker.a(entity, new Packet22Collect(entity.id, id));
        }
        super.receive(entity, i);
        activeContainer.a();
    }

    public void v()
    {
        if(!s)
        {
            t = -1;
            s = true;
            EntityTracker entitytracker = b.getTracker(dimension);
            entitytracker.a(this, new Packet18ArmAnimation(this, 1));
        }
    }

    public void w()
    {
    }

    public EnumBedError a(int i, int j, int k)
    {
        EnumBedError enumbederror = super.a(i, j, k);
        if(enumbederror == EnumBedError.OK)
        {
            EntityTracker entitytracker = b.getTracker(dimension);
            Packet17 packet17 = new Packet17(this, 0, i, j, k);
            entitytracker.a(this, packet17);
            netServerHandler.a(locX, locY, locZ, yaw, pitch);
            netServerHandler.sendPacket(packet17);
        }
        return enumbederror;
    }

    public void a(boolean flag, boolean flag1, boolean flag2)
    {
        if(isSleeping())
        {
            EntityTracker entitytracker = b.getTracker(dimension);
            entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(this, 3));
        }
        super.a(flag, flag1, flag2);
        if(netServerHandler != null)
            netServerHandler.a(locX, locY, locZ, yaw, pitch);
    }

    public void mount(Entity entity)
    {
        setPassengerOf(entity);
    }

    public void setPassengerOf(Entity entity)
    {
        super.setPassengerOf(entity);
        netServerHandler.sendPacket(new Packet39AttachEntity(this, vehicle));
        netServerHandler.a(locX, locY, locZ, yaw, pitch);
    }

    protected void a(double d1, boolean flag1)
    {
    }

    public void b(double d0, boolean flag)
    {
        super.a(d0, flag);
    }

    private void au()
    {
        ch = ch % 100 + 1;
    }

    public void b(int i, int j, int k)
    {
        au();
        netServerHandler.sendPacket(new Packet100OpenWindow(ch, 1, "Crafting", 9));
        activeContainer = new ContainerWorkbench(inventory, world, i, j, k);
        activeContainer.windowId = ch;
        activeContainer.a(this);
    }

    public void a(IInventory iinventory)
    {
        au();
        netServerHandler.sendPacket(new Packet100OpenWindow(ch, 0, iinventory.getName(), iinventory.getSize()));
        activeContainer = new ContainerChest(inventory, iinventory);
        activeContainer.windowId = ch;
        activeContainer.a(this);
    }

    public void a(TileEntityFurnace tileentityfurnace)
    {
        au();
        netServerHandler.sendPacket(new Packet100OpenWindow(ch, 2, tileentityfurnace.getName(), tileentityfurnace.getSize()));
        activeContainer = new ContainerFurnace(inventory, tileentityfurnace);
        activeContainer.windowId = ch;
        activeContainer.a(this);
    }

    public void a(TileEntityDispenser tileentitydispenser)
    {
        au();
        netServerHandler.sendPacket(new Packet100OpenWindow(ch, 3, tileentitydispenser.getName(), tileentitydispenser.getSize()));
        activeContainer = new ContainerDispenser(inventory, tileentitydispenser);
        activeContainer.windowId = ch;
        activeContainer.a(this);
    }

    public void a(Container container, int i, ItemStack itemstack)
    {
        if(!(container.b(i) instanceof SlotResult) && !h)
            netServerHandler.sendPacket(new Packet103SetSlot(container.windowId, i, itemstack));
    }

    public void updateInventory(Container container)
    {
        a(container, container.b());
    }

    public void a(Container container, List list)
    {
        netServerHandler.sendPacket(new Packet104WindowItems(container.windowId, list));
        netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, inventory.l()));
    }

    public void a(Container container, int i, int j)
    {
        netServerHandler.sendPacket(new Packet105CraftProgressBar(container.windowId, i, j));
    }

    public void a(ItemStack itemstack1)
    {
    }

    public void closeInventory()
    {
        netServerHandler.sendPacket(new Packet101CloseWindow(activeContainer.windowId));
        z();
    }

    public void y()
    {
        if(!h)
            netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, inventory.l()));
    }

    public void z()
    {
        activeContainer.a(this);
        activeContainer = defaultContainer;
    }

    public void a(float f, float f1, boolean flag, boolean flag1, float f2, float f3)
    {
        aP = f;
        aQ = f1;
        aS = flag;
        setSneak(flag1);
        pitch = f2;
        yaw = f3;
    }

    public void a(Statistic statistic, int i)
    {
        if(statistic != null && !statistic.g)
        {
            for(; i > 100; i -= 100)
                netServerHandler.sendPacket(new Packet200Statistic(statistic.e, 100));

            netServerHandler.sendPacket(new Packet200Statistic(statistic.e, i));
        }
    }

    public void A()
    {
        if(vehicle != null)
            mount(vehicle);
        if(passenger != null)
            passenger.mount(this);
        if(sleeping)
            a(true, false, false);
    }

    public void B()
    {
        cb = 0xfa0a1f01;
    }

    public void a(String s)
    {
        StatisticStorage statisticstorage = StatisticStorage.a();
        String s1 = statisticstorage.a(s);
        netServerHandler.sendPacket(new Packet3Chat(s1));
    }

    protected void C()
    {
        netServerHandler.sendPacket(new Packet38EntityStatus(id, (byte)9));
        super.C();
    }

    public void a(ItemStack itemstack, int i)
    {
        super.a(itemstack, i);
        if(itemstack != null && itemstack.getItem() != null && itemstack.getItem().b(itemstack) == EnumAnimation.b)
        {
            EntityTracker entitytracker = b.getTracker(dimension);
            entitytracker.sendPacketToEntity(this, new Packet18ArmAnimation(this, 5));
        }
    }

    protected void a(MobEffect mobeffect)
    {
        super.a(mobeffect);
        netServerHandler.sendPacket(new Packet41MobEffect(id, mobeffect));
    }

    protected void b(MobEffect mobeffect)
    {
        super.b(mobeffect);
        netServerHandler.sendPacket(new Packet41MobEffect(id, mobeffect));
    }

    protected void c(MobEffect mobeffect)
    {
        super.c(mobeffect);
        netServerHandler.sendPacket(new Packet42RemoveMobEffect(id, mobeffect));
    }

    public long getPlayerTime()
    {
        if(relativeTime)
            return world.getTime() + timeOffset;
        else
            return (world.getTime() - world.getTime() % 24000L) + timeOffset;
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append("(").append(name).append(" at ").append(locX).append(",").append(locY).append(",").append(locZ).append(")").toString();
    }

    public NetServerHandler netServerHandler;
    public MinecraftServer b;
    public ItemInWorldManager itemInWorldManager;
    public double d;
    public double e;
    public List chunkCoordIntPairQueue;
    public Set playerChunkCoordIntPairs;
    private int cb;
    private int cc;
    private boolean cd;
    private int ce;
    private int cf;
    private ItemStack cg[] = {
        null, null, null, null, null
    };
    private int ch;
    public boolean h;
    public int i;
    public String displayName;
    public String listName;
    public Location compassTarget;
    public int newExp;
    public long timeOffset;
    public boolean relativeTime;
}
