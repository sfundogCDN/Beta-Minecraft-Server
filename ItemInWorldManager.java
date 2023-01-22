// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemInWorldManager.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityPlayer, Packet53BlockChange, EntityHuman, PlayerAbilities, 
//            World, Block, InventoryPlayer, NetServerHandler, 
//            ItemStack, WorldServer

public class ItemInWorldManager
{

    public ItemInWorldManager(World world)
    {
        c = -1;
        d = 0.0F;
        this.world = world;
    }

    public ItemInWorldManager(WorldServer world)
    {
        c = -1;
        d = 0.0F;
        this.world = world;
    }

    public void a(int i)
    {
        c = i;
        if(i == 0)
        {
            player.abilities.canFly = false;
            player.abilities.isFlying = false;
            player.abilities.canInstantlyBuild = false;
            player.abilities.isInvulnerable = false;
        } else
        {
            player.abilities.canFly = true;
            player.abilities.canInstantlyBuild = true;
            player.abilities.isInvulnerable = true;
        }
    }

    public int a()
    {
        return c;
    }

    public boolean b()
    {
        return c == 1;
    }

    public void b(int i)
    {
        if(c == -1)
            c = i;
        a(c);
    }

    public void c()
    {
        currentTick = (int)(System.currentTimeMillis() / 50L);
        if(this.j)
        {
            int i = currentTick - n;
            int j = world.getTypeId(k, l, m);
            if(j != 0)
            {
                Block block = Block.byId[j];
                float f = block.getDamage(player) * (float)(i + 1);
                if(f >= 1.0F)
                {
                    this.j = false;
                    c(k, l, m);
                }
            } else
            {
                this.j = false;
            }
        }
    }

    public void dig(int i, int j, int k, int l)
    {
        PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(player, Action.LEFT_CLICK_BLOCK, i, j, k, l, player.inventory.getItemInHand());
        if(b())
        {
            if(event.isCancelled())
                return;
            c(i, j, k);
        } else
        {
            lastDigTick = (int)(System.currentTimeMillis() / 50L);
            int i1 = world.getTypeId(i, j, k);
            if(i1 <= 0)
                return;
            if(event.useInteractedBlock() == org.bukkit.event.Event.Result.DENY)
            {
                if(i1 == Block.WOODEN_DOOR.id)
                {
                    boolean bottom = (world.getData(i, j, k) & 8) == 0;
                    ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, world));
                    ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, world));
                } else
                if(i1 == Block.TRAP_DOOR.id)
                    ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, world));
            } else
            {
                Block.byId[i1].b(world, i, j, k, player);
                world.douseFire((EntityHuman)null, i, j, k, l);
            }
            float toolDamage = Block.byId[i1].getDamage(player);
            if(event.useItemInHand() == org.bukkit.event.Event.Result.DENY)
            {
                if(toolDamage > 1.0F)
                    ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, world));
                return;
            }
            BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(player, i, j, k, player.inventory.getItemInHand(), toolDamage >= 1.0F);
            if(blockEvent.isCancelled())
                return;
            if(blockEvent.getInstaBreak())
                toolDamage = 2.0F;
            if(toolDamage >= 1.0F)
            {
                c(i, j, k);
            } else
            {
                f = i;
                g = j;
                h = k;
            }
        }
    }

    public void a(int i, int j, int k)
    {
        if(i == this.f && j == g && k == h)
        {
            currentTick = (int)(System.currentTimeMillis() / 50L);
            int l = currentTick - lastDigTick;
            int i1 = world.getTypeId(i, j, k);
            if(i1 != 0)
            {
                Block block = Block.byId[i1];
                float f = block.getDamage(player) * (float)(l + 1);
                if(f >= 0.7F)
                    c(i, j, k);
                else
                if(!this.j)
                {
                    this.j = true;
                    this.k = i;
                    this.l = j;
                    m = k;
                    n = lastDigTick;
                }
            }
        } else
        {
            ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, world));
        }
        d = 0.0F;
    }

    public boolean b(int i, int j, int k)
    {
        Block block = Block.byId[world.getTypeId(i, j, k)];
        int l = world.getData(i, j, k);
        boolean flag = world.setTypeId(i, j, k, 0);
        if(block != null && flag)
            block.postBreak(world, i, j, k, l);
        return flag;
    }

    public boolean c(int i, int j, int k)
    {
        if(player instanceof EntityPlayer)
        {
            org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
            BlockBreakEvent event = new BlockBreakEvent(block, (Player)player.getBukkitEntity());
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return false;
        }
        int l = world.getTypeId(i, j, k);
        int i1 = world.getData(i, j, k);
        world.a(player, 2001, i, j, k, l + world.getData(i, j, k) * 256);
        boolean flag = b(i, j, k);
        if(b())
        {
            ((EntityPlayer)player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, world));
        } else
        {
            ItemStack itemstack = player.K();
            if(itemstack != null)
            {
                itemstack.a(l, i, j, k, player);
                if(itemstack.count == 0)
                {
                    itemstack.a(player);
                    player.L();
                }
            }
            if(flag && player.b(Block.byId[l]))
                Block.byId[l].a(world, player, i, j, k, i1);
        }
        return flag;
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack)
    {
        int i = itemstack.count;
        int j = itemstack.getData();
        ItemStack itemstack1 = itemstack.a(world, entityhuman);
        if(itemstack1 == itemstack && (itemstack1 == null || itemstack1.count == i) && (itemstack1 == null || itemstack1.l() <= 0))
            return false;
        entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
        if(b())
        {
            itemstack1.count = i;
            itemstack1.b(j);
        }
        if(itemstack1.count == 0)
            entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
        return true;
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l)
    {
        int i1 = world.getTypeId(i, j, k);
        boolean result = false;
        if(i1 > 0)
        {
            PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);
            if(event.useInteractedBlock() == org.bukkit.event.Event.Result.DENY)
            {
                if(i1 == Block.WOODEN_DOOR.id)
                {
                    boolean bottom = (world.getData(i, j, k) & 8) == 0;
                    ((EntityPlayer)entityhuman).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, world));
                }
                result = event.useItemInHand() != org.bukkit.event.Event.Result.ALLOW;
            } else
            {
                result = Block.byId[i1].interact(world, i, j, k, entityhuman);
            }
            if(itemstack != null && !result)
            {
                int j1 = itemstack.getData();
                int k1 = itemstack.count;
                result = itemstack.placeItem(entityhuman, world, i, j, k, l);
                if(b())
                {
                    itemstack.b(j1);
                    itemstack.count = k1;
                }
            }
            if(itemstack != null && (!result && event.useItemInHand() != org.bukkit.event.Event.Result.DENY || event.useItemInHand() == org.bukkit.event.Event.Result.ALLOW))
                useItem(entityhuman, world, itemstack);
        }
        return result;
    }

    public void a(WorldServer worldserver)
    {
        world = worldserver;
    }

    public World world;
    public EntityHuman player;
    private int c;
    private float d;
    private int lastDigTick;
    private int f;
    private int g;
    private int h;
    private int currentTick;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
}
