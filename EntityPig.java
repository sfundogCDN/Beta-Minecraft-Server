// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityPig.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityAnimal, EntityPigZombie, EntityHuman, DataWatcher, 
//            NBTTagCompound, World, Item, EntityWeatherStorm, 
//            AchievementList

public class EntityPig extends EntityAnimal
{

    public EntityPig(World world)
    {
        super(world);
        texture = "/mob/pig.png";
        b(0.9F, 0.9F);
    }

    protected void b()
    {
        datawatcher.a(16, Byte.valueOf((byte)0));
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Saddle", hasSaddle());
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setSaddle(nbttagcompound.m("Saddle"));
    }

    protected String h()
    {
        return "mob.pig";
    }

    protected String i()
    {
        return "mob.pig";
    }

    protected String j()
    {
        return "mob.pigdeath";
    }

    public boolean b(EntityHuman entityhuman)
    {
        if(hasSaddle() && !world.isStatic && (passenger == null || passenger == entityhuman))
        {
            entityhuman.mount(this);
            return true;
        } else
        {
            return false;
        }
    }

    protected int k()
    {
        return fireTicks <= 0 ? Item.PORK.id : Item.GRILLED_PORK.id;
    }

    public boolean hasSaddle()
    {
        return (datawatcher.getByte(16) & 1) != 0;
    }

    public void setSaddle(boolean flag)
    {
        if(flag)
            datawatcher.watch(16, Byte.valueOf((byte)1));
        else
            datawatcher.watch(16, Byte.valueOf((byte)0));
    }

    public void a(EntityWeatherStorm entityweatherstorm)
    {
        if(!world.isStatic)
        {
            EntityPigZombie entitypigzombie = new EntityPigZombie(world);
            PigZapEvent event = new PigZapEvent(getBukkitEntity(), entityweatherstorm.getBukkitEntity(), entitypigzombie.getBukkitEntity());
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
            entitypigzombie.setPositionRotation(locX, locY, locZ, yaw, pitch);
            world.addEntity(entitypigzombie, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.LIGHTNING);
            die();
        }
    }

    protected void a(float f)
    {
        super.a(f);
        if(f > 5F && (passenger instanceof EntityHuman))
            ((EntityHuman)passenger).a(AchievementList.u);
    }
}
