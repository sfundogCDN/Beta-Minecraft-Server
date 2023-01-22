// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityEnderman.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, EntityHuman, DataWatcher, NBTTagCompound, 
//            World, InventoryPlayer, ItemStack, Block, 
//            Vec3D, AxisAlignedBB, DamageSource, MathHelper, 
//            Entity, Material, Item, BlockGrass, 
//            BlockLeaves, BlockFlower

public class EntityEnderman extends EntityMonster
{

    public EntityEnderman(World world)
    {
        super(world);
        a = false;
        g = 0;
        h = 0;
        texture = "/mob/enderman.png";
        aU = 0.2F;
        damage = 5;
        b(0.6F, 2.9F);
        bI = 1.0F;
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, new Byte((byte)0));
        datawatcher.a(17, new Byte((byte)0));
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("carried", (short)getCarriedId());
        nbttagcompound.a("carryingData", (short)getCarriedData());
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setCarriedId(nbttagcompound.d("carried"));
        setCarriedData(nbttagcompound.d("carryingData"));
    }

    protected Entity findTarget()
    {
        EntityHuman entityhuman = world.findNearbyPlayer(this, 64D);
        if(entityhuman != null)
            if(c(entityhuman))
            {
                if(h++ == 5)
                {
                    h = 0;
                    return entityhuman;
                }
            } else
            {
                h = 0;
            }
        return null;
    }

    public float a_(float f)
    {
        return super.a_(f);
    }

    private boolean c(EntityHuman entityhuman)
    {
        net.minecraft.server.ItemStack itemstack = entityhuman.inventory.armor[3];
        if(itemstack != null && itemstack.id == Block.PUMPKIN.id)
        {
            return false;
        } else
        {
            Vec3D vec3d = entityhuman.c(1.0F).b();
            Vec3D vec3d1 = Vec3D.create(locX - entityhuman.locX, ((boundingBox.b + (double)(width / 2.0F)) - entityhuman.locY) + (double)entityhuman.t(), locZ - entityhuman.locZ);
            double d0 = vec3d1.c();
            vec3d1 = vec3d1.b();
            double d1 = vec3d.a(vec3d1);
            return d1 <= 1.0D - 0.025000000000000001D / d0 ? false : entityhuman.f(this);
        }
    }

    public void s()
    {
        if(an())
            damageEntity(DamageSource.DROWN, 1);
        a = target != null;
        aU = target == null ? 0.3F : 4.5F;
        if(!world.isStatic)
            if(getCarriedId() == 0)
            {
                if(random.nextInt(20) == 0)
                {
                    int i = MathHelper.floor((locX - 2D) + random.nextDouble() * 4D);
                    int j = MathHelper.floor(locY + random.nextDouble() * 3D);
                    int k = MathHelper.floor((locZ - 2D) + random.nextDouble() * 4D);
                    int l = world.getTypeId(i, j, k);
                    if(b[l])
                    {
                        EndermanPickupEvent pickup = new EndermanPickupEvent(getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
                        world.getServer().getPluginManager().callEvent(pickup);
                        if(!pickup.isCancelled())
                        {
                            setCarriedId(world.getTypeId(i, j, k));
                            setCarriedData(world.getData(i, j, k));
                            world.setTypeId(i, j, k, 0);
                        }
                    }
                }
            } else
            if(random.nextInt(2000) == 0)
            {
                int i = MathHelper.floor((locX - 1.0D) + random.nextDouble() * 2D);
                int j = MathHelper.floor(locY + random.nextDouble() * 2D);
                int k = MathHelper.floor((locZ - 1.0D) + random.nextDouble() * 2D);
                int l = world.getTypeId(i, j, k);
                int i1 = world.getTypeId(i, j - 1, k);
                if(l == 0 && i1 > 0 && Block.byId[i1].b())
                {
                    EndermanPlaceEvent place = new EndermanPlaceEvent(getBukkitEntity(), new Location(world.getWorld(), i, j, k));
                    world.getServer().getPluginManager().callEvent(place);
                    if(!place.isCancelled())
                    {
                        world.setTypeIdAndData(i, j, k, getCarriedId(), getCarriedData());
                        setCarriedId(0);
                    }
                }
            }
        for(int i = 0; i < 2; i++)
            world.a("portal", locX + (random.nextDouble() - 0.5D) * (double)length, (locY + random.nextDouble() * (double)width) - 0.25D, locZ + (random.nextDouble() - 0.5D) * (double)length, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D);

        if(world.d() && !world.isStatic)
        {
            float f = a_(1.0F);
            if(f > 0.5F && world.isChunkLoaded(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
                fireTicks = 300;
        }
        aS = false;
        if(target != null)
            a(target, 100F, 100F);
        if(!world.isStatic)
            if(target != null)
            {
                if((target instanceof EntityHuman) && c((EntityHuman)target))
                {
                    aP = aQ = 0.0F;
                    aU = 0.0F;
                    if(target.h(this) < 16D)
                        w();
                    g = 0;
                } else
                if(target.h(this) > 256D && g++ >= 30 && e(target))
                    g = 0;
            } else
            {
                g = 0;
            }
        super.s();
    }

    protected boolean w()
    {
        double d0 = locX + (random.nextDouble() - 0.5D) * 64D;
        double d1 = locY + (double)(random.nextInt(64) - 32);
        double d2 = locZ + (random.nextDouble() - 0.5D) * 64D;
        return a(d0, d1, d2);
    }

    protected boolean e(Entity entity)
    {
        Vec3D vec3d = Vec3D.create(locX - entity.locX, ((boundingBox.b + (double)(width / 2.0F)) - entity.locY) + (double)entity.t(), locZ - entity.locZ);
        vec3d = vec3d.b();
        double d0 = 16D;
        double d1 = (locX + (random.nextDouble() - 0.5D) * 8D) - vec3d.a * d0;
        double d2 = (locY + (double)(random.nextInt(16) - 8)) - vec3d.b * d0;
        double d3 = (locZ + (random.nextDouble() - 0.5D) * 8D) - vec3d.c * d0;
        return a(d1, d2, d3);
    }

    protected boolean a(double d0, double d1, double d2)
    {
        double d3 = locX;
        double d4 = locY;
        double d5 = locZ;
        locX = d0;
        locY = d1;
        locZ = d2;
        boolean flag = false;
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(locY);
        int k = MathHelper.floor(locZ);
        if(world.isLoaded(i, j, k))
        {
            boolean flag1;
            for(flag1 = false; !flag1 && j > 0;)
            {
                int l = world.getTypeId(i, j - 1, k);
                if(l != 0 && Block.byId[l].material.isSolid())
                {
                    flag1 = true;
                } else
                {
                    locY--;
                    j--;
                }
            }

            if(flag1)
            {
                setPosition(locX, locY, locZ);
                if(world.getEntities(this, boundingBox).size() == 0 && !world.c(boundingBox))
                    flag = true;
            }
        }
        if(!flag)
        {
            setPosition(d3, d4, d5);
            return false;
        }
        short short1 = 128;
        for(int l = 0; l < short1; l++)
        {
            double d6 = (double)l / ((double)short1 - 1.0D);
            float f = (random.nextFloat() - 0.5F) * 0.2F;
            float f1 = (random.nextFloat() - 0.5F) * 0.2F;
            float f2 = (random.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (locX - d3) * d6 + (random.nextDouble() - 0.5D) * (double)length * 2D;
            double d8 = d4 + (locY - d4) * d6 + random.nextDouble() * (double)width;
            double d9 = d5 + (locZ - d5) * d6 + (random.nextDouble() - 0.5D) * (double)length * 2D;
            world.a("portal", d7, d8, d9, f, f1, f2);
        }

        return true;
    }

    protected String h()
    {
        return "mob.zombie";
    }

    protected String i()
    {
        return "mob.zombiehurt";
    }

    protected String j()
    {
        return "mob.zombiedeath";
    }

    protected int k()
    {
        return Item.ENDER_PEARL.id;
    }

    protected void a(boolean flag)
    {
        int i = k();
        List loot = new ArrayList();
        int count = random.nextInt(2);
        if(i > 0 && count > 0)
            loot.add(new ItemStack(i, count));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    public void setCarriedId(int i)
    {
        datawatcher.watch(16, Byte.valueOf((byte)(i & 0xff)));
    }

    public int getCarriedId()
    {
        return datawatcher.getByte(16);
    }

    public void setCarriedData(int i)
    {
        datawatcher.watch(17, Byte.valueOf((byte)(i & 0xff)));
    }

    public int getCarriedData()
    {
        return datawatcher.getByte(17);
    }

    private static boolean b[];
    public boolean a;
    private int g;
    private int h;

    static 
    {
        b = new boolean[256];
        b[Block.STONE.id] = true;
        b[Block.GRASS.id] = true;
        b[Block.DIRT.id] = true;
        b[Block.COBBLESTONE.id] = true;
        b[Block.WOOD.id] = true;
        b[Block.SAND.id] = true;
        b[Block.GRAVEL.id] = true;
        b[Block.GOLD_ORE.id] = true;
        b[Block.IRON_ORE.id] = true;
        b[Block.COAL_ORE.id] = true;
        b[Block.LOG.id] = true;
        b[Block.LEAVES.id] = true;
        b[Block.SPONGE.id] = true;
        b[Block.GLASS.id] = true;
        b[Block.LAPIS_ORE.id] = true;
        b[Block.LAPIS_BLOCK.id] = true;
        b[Block.SANDSTONE.id] = true;
        b[Block.WOOL.id] = true;
        b[Block.YELLOW_FLOWER.id] = true;
        b[Block.RED_ROSE.id] = true;
        b[Block.BROWN_MUSHROOM.id] = true;
        b[Block.RED_MUSHROOM.id] = true;
        b[Block.GOLD_BLOCK.id] = true;
        b[Block.IRON_BLOCK.id] = true;
        b[Block.BRICK.id] = true;
        b[Block.TNT.id] = true;
        b[Block.BOOKSHELF.id] = true;
        b[Block.MOSSY_COBBLESTONE.id] = true;
        b[Block.DIAMOND_ORE.id] = true;
        b[Block.DIAMOND_BLOCK.id] = true;
        b[Block.WORKBENCH.id] = true;
        b[Block.REDSTONE_ORE.id] = true;
        b[Block.GLOWING_REDSTONE_ORE.id] = true;
        b[Block.ICE.id] = true;
        b[Block.CACTUS.id] = true;
        b[Block.CLAY.id] = true;
        b[Block.PUMPKIN.id] = true;
        b[Block.NETHERRACK.id] = true;
        b[Block.SOUL_SAND.id] = true;
        b[Block.GLOWSTONE.id] = true;
        b[Block.JACK_O_LANTERN.id] = true;
        b[Block.SMOOTH_BRICK.id] = true;
        b[Block.BIG_MUSHROOM_1.id] = true;
        b[Block.BIG_MUSHROOM_2.id] = true;
        b[Block.MELON.id] = true;
    }
}
