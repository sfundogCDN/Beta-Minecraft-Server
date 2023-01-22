// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityWeatherStorm.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityWeather, Entity, World, MathHelper, 
//            Block, BlockFire, AxisAlignedBB, NBTTagCompound

public class EntityWeatherStorm extends EntityWeather
{

    public EntityWeatherStorm(World world, double d0, double d1, double d2)
    {
        this(world, d0, d1, d2, false);
    }

    public EntityWeatherStorm(World world, double d0, double d1, double d2, 
            boolean isEffect)
    {
        super(world);
        a = 0L;
        this.isEffect = false;
        this.isEffect = isEffect;
        cworld = world.getWorld();
        setPositionRotation(d0, d1, d2, 0.0F, 0.0F);
        lifeTicks = 2;
        a = random.nextLong();
        c = random.nextInt(3) + 1;
        if(!isEffect && world.difficulty >= 2 && world.areChunksLoaded(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2), 10))
        {
            int i = MathHelper.floor(d0);
            int j = MathHelper.floor(d1);
            int k = MathHelper.floor(d2);
            if(world.getTypeId(i, j, k) == 0 && Block.FIRE.canPlace(world, i, j, k))
            {
                BlockIgniteEvent event = new BlockIgniteEvent(cworld.getBlockAt(i, j, k), org.bukkit.event.block.BlockIgniteEvent.IgniteCause.LIGHTNING, null);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    world.setTypeId(i, j, k, Block.FIRE.id);
            }
            for(i = 0; i < 4; i++)
            {
                j = (MathHelper.floor(d0) + random.nextInt(3)) - 1;
                k = (MathHelper.floor(d1) + random.nextInt(3)) - 1;
                int l = (MathHelper.floor(d2) + random.nextInt(3)) - 1;
                if(world.getTypeId(j, k, l) != 0 || !Block.FIRE.canPlace(world, j, k, l))
                    continue;
                BlockIgniteEvent event = new BlockIgniteEvent(cworld.getBlockAt(j, k, l), org.bukkit.event.block.BlockIgniteEvent.IgniteCause.LIGHTNING, null);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    world.setTypeId(j, k, l, Block.FIRE.id);
            }

        }
    }

    public void s_()
    {
        super.s_();
        if(lifeTicks == 2)
        {
            world.makeSound(locX, locY, locZ, "ambient.weather.thunder", 10000F, 0.8F + random.nextFloat() * 0.2F);
            world.makeSound(locX, locY, locZ, "random.explode", 2.0F, 0.5F + random.nextFloat() * 0.2F);
        }
        lifeTicks--;
        if(lifeTicks < 0)
            if(c == 0)
                die();
            else
            if(lifeTicks < -random.nextInt(10))
            {
                c--;
                lifeTicks = 1;
                a = random.nextLong();
                if(!isEffect && world.areChunksLoaded(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ), 10))
                {
                    int i = MathHelper.floor(locX);
                    int j = MathHelper.floor(locY);
                    int k = MathHelper.floor(locZ);
                    if(world.getTypeId(i, j, k) == 0 && Block.FIRE.canPlace(world, i, j, k))
                    {
                        BlockIgniteEvent event = new BlockIgniteEvent(cworld.getBlockAt(i, j, k), org.bukkit.event.block.BlockIgniteEvent.IgniteCause.LIGHTNING, null);
                        world.getServer().getPluginManager().callEvent(event);
                        if(!event.isCancelled())
                            world.setTypeId(i, j, k, Block.FIRE.id);
                    }
                }
            }
        if(lifeTicks >= 0 && !isEffect)
        {
            double d0 = 3D;
            List list = world.b(this, AxisAlignedBB.b(locX - d0, locY - d0, locZ - d0, locX + d0, locY + 6D + d0, locZ + d0));
            for(int l = 0; l < list.size(); l++)
            {
                Entity entity = (Entity)list.get(l);
                entity.a(this);
            }

            world.s = 2;
        }
    }

    protected void b()
    {
    }

    protected void a(NBTTagCompound nbttagcompound1)
    {
    }

    protected void b(NBTTagCompound nbttagcompound1)
    {
    }

    private int lifeTicks;
    public long a;
    private int c;
    private CraftWorld cworld;
    public boolean isEffect;
}
