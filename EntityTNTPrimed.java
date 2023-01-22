// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityTNTPrimed.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, MathHelper, World, NBTTagCompound

public class EntityTNTPrimed extends Entity
{

    public EntityTNTPrimed(World world)
    {
        super(world);
        yield = 4F;
        isIncendiary = false;
        fuseTicks = 0;
        aY = true;
        b(0.98F, 0.98F);
        height = width / 2.0F;
    }

    public EntityTNTPrimed(World world, double d0, double d1, double d2)
    {
        this(world);
        setPosition(d0, d1, d2);
        float f = (float)(Math.random() * 3.1415927410125732D * 2D);
        motX = -MathHelper.sin((f * 3.141593F) / 180F) * 0.02F;
        motY = 0.20000000298023224D;
        motZ = -MathHelper.cos((f * 3.141593F) / 180F) * 0.02F;
        fuseTicks = 80;
        lastX = d0;
        lastY = d1;
        lastZ = d2;
    }

    protected void b()
    {
    }

    protected boolean e_()
    {
        return false;
    }

    public boolean r_()
    {
        return !dead;
    }

    public void s_()
    {
        lastX = locX;
        lastY = locY;
        lastZ = locZ;
        motY -= 0.039999999105930328D;
        move(motX, motY, motZ);
        motX *= 0.98000001907348633D;
        motY *= 0.98000001907348633D;
        motZ *= 0.98000001907348633D;
        if(onGround)
        {
            motX *= 0.69999998807907104D;
            motZ *= 0.69999998807907104D;
            motY *= -0.5D;
        }
        if(fuseTicks-- <= 0)
        {
            if(!world.isStatic)
            {
                explode();
                die();
            } else
            {
                die();
            }
        } else
        {
            world.a("smoke", locX, locY + 0.5D, locZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        CraftServer server = world.getServer();
        ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(server, this));
        server.getPluginManager().callEvent(event);
        if(!event.isCancelled())
            world.createExplosion(this, locX, locY, locZ, event.getRadius(), event.getFire());
    }

    protected void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Fuse", (byte)fuseTicks);
    }

    protected void a(NBTTagCompound nbttagcompound)
    {
        fuseTicks = nbttagcompound.c("Fuse");
    }

    public int fuseTicks;
    public float yield;
    public boolean isIncendiary;
}
