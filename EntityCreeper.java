// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityCreeper.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, EntitySkeleton, DataWatcher, NBTTagCompound, 
//            World, DamageSource, Item, EntityWeatherStorm, 
//            Entity

public class EntityCreeper extends EntityMonster
{

    public EntityCreeper(World world)
    {
        super(world);
        texture = "/mob/creeper.png";
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, Byte.valueOf((byte)-1));
        datawatcher.a(17, Byte.valueOf((byte)0));
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        if(datawatcher.getByte(17) == 1)
            nbttagcompound.a("powered", true);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        datawatcher.watch(17, Byte.valueOf((byte)(nbttagcompound.m("powered") ? 1 : 0)));
    }

    protected void b(Entity entity, float f)
    {
        if(!world.isStatic && fuseTicks > 0)
        {
            b(-1);
            fuseTicks--;
            if(fuseTicks < 0)
                fuseTicks = 0;
        }
    }

    public void s_()
    {
        b = fuseTicks;
        if(world.isStatic)
        {
            int i = w();
            if(i > 0 && fuseTicks == 0)
                world.makeSound(this, "random.fuse", 1.0F, 0.5F);
            fuseTicks += i;
            if(fuseTicks < 0)
                fuseTicks = 0;
            if(fuseTicks >= 30)
                fuseTicks = 30;
        }
        super.s_();
        if(target == null && fuseTicks > 0)
        {
            b(-1);
            fuseTicks--;
            if(fuseTicks < 0)
                fuseTicks = 0;
        }
    }

    protected String i()
    {
        return "mob.creeper";
    }

    protected String j()
    {
        return "mob.creeperdeath";
    }

    public void die(DamageSource damagesource)
    {
        super.die(damagesource);
        if(damagesource.getEntity() instanceof EntitySkeleton)
            b(Item.GOLD_RECORD.id + random.nextInt(2), 1);
    }

    protected void a(Entity entity, float f)
    {
        if(!world.isStatic)
        {
            int i = w();
            if((i > 0 || f >= 3F) && (i <= 0 || f >= 7F))
            {
                b(-1);
                fuseTicks--;
                if(fuseTicks < 0)
                    fuseTicks = 0;
            } else
            {
                if(fuseTicks == 0)
                    world.makeSound(this, "random.fuse", 1.0F, 0.5F);
                b(1);
                fuseTicks++;
                if(fuseTicks >= 30)
                {
                    float radius = isPowered() ? 6F : 3F;
                    ExplosionPrimeEvent event = new ExplosionPrimeEvent(CraftEntity.getEntity(world.getServer(), this), radius, false);
                    world.getServer().getPluginManager().callEvent(event);
                    if(!event.isCancelled())
                    {
                        world.createExplosion(this, locX, locY, locZ, event.getRadius(), event.getFire());
                        die();
                    } else
                    {
                        fuseTicks = 0;
                    }
                }
                e = true;
            }
        }
    }

    public boolean isPowered()
    {
        return datawatcher.getByte(17) == 1;
    }

    protected int k()
    {
        return Item.SULPHUR.id;
    }

    private int w()
    {
        return datawatcher.getByte(16);
    }

    private void b(int i)
    {
        datawatcher.watch(16, Byte.valueOf((byte)i));
    }

    public void a(EntityWeatherStorm entityweatherstorm)
    {
        super.a(entityweatherstorm);
        CreeperPowerEvent event = new CreeperPowerEvent(getBukkitEntity(), entityweatherstorm.getBukkitEntity(), org.bukkit.event.entity.CreeperPowerEvent.PowerCause.LIGHTNING);
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
        {
            return;
        } else
        {
            setPowered(true);
            return;
        }
    }

    public void setPowered(boolean powered)
    {
        if(!powered)
            datawatcher.watch(17, Byte.valueOf((byte)0));
        else
            datawatcher.watch(17, Byte.valueOf((byte)1));
    }

    int fuseTicks;
    int b;
}
