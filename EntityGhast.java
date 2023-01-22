// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityGhast.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityFlying, EntityFireball, IMonster, DataWatcher, 
//            World, MathHelper, Entity, AxisAlignedBB, 
//            Vec3D, Item

public class EntityGhast extends EntityFlying
    implements IMonster
{

    public EntityGhast(World world)
    {
        super(world);
        a = 0;
        target = null;
        h = 0;
        e = 0;
        f = 0;
        texture = "/mob/ghast.png";
        b(4F, 4F);
        fireProof = true;
        ax = 5;
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, Byte.valueOf((byte)0));
    }

    public void s_()
    {
        super.s_();
        byte b0 = datawatcher.getByte(16);
        texture = b0 != 1 ? "/mob/ghast.png" : "/mob/ghast_fire.png";
    }

    protected void c_()
    {
        if(!world.isStatic && world.difficulty == 0)
            die();
        ad();
        e = f;
        double d0 = b - locX;
        double d1 = c - locY;
        double d2 = d - locZ;
        double d3 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
        if(d3 < 1.0D || d3 > 60D)
        {
            b = locX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
            c = locY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
            d = locZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
        }
        if(a-- <= 0)
        {
            a += random.nextInt(5) + 2;
            if(a(b, c, d, d3))
            {
                motX += (d0 / d3) * 0.10000000000000001D;
                motY += (d1 / d3) * 0.10000000000000001D;
                motZ += (d2 / d3) * 0.10000000000000001D;
            } else
            {
                b = locX;
                c = locY;
                d = locZ;
            }
        }
        if(this.target != null && this.target.dead)
        {
            EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_DIED);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                if(event.getTarget() == null)
                    this.target = null;
                else
                    this.target = ((CraftEntity)event.getTarget()).getHandle();
        }
        if(this.target == null || h-- <= 0)
        {
            Entity target = world.findNearbyPlayer(this, 100D);
            if(target != null)
            {
                EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), target.getBukkitEntity(), org.bukkit.event.entity.EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    if(event.getTarget() == null)
                        this.target = null;
                    else
                        this.target = ((CraftEntity)event.getTarget()).getHandle();
            }
            if(this.target != null)
                h = 20;
        }
        double d4 = 64D;
        if(this.target != null && this.target.h(this) < d4 * d4)
        {
            double d5 = this.target.locX - locX;
            double d6 = (this.target.boundingBox.b + (double)(this.target.width / 2.0F)) - (locY + (double)(width / 2.0F));
            double d7 = this.target.locZ - locZ;
            U = yaw = (-(float)Math.atan2(d5, d7) * 180F) / 3.141593F;
            if(f(this.target))
            {
                if(f == 10)
                    world.makeSound(this, "mob.ghast.charge", l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                f++;
                if(f == 20)
                {
                    world.makeSound(this, "mob.ghast.fireball", l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                    EntityFireball entityfireball = new EntityFireball(world, this, d5, d6, d7);
                    double d8 = 4D;
                    Vec3D vec3d = c(1.0F);
                    entityfireball.locX = locX + vec3d.a * d8;
                    entityfireball.locY = locY + (double)(width / 2.0F) + 0.5D;
                    entityfireball.locZ = locZ + vec3d.c * d8;
                    world.addEntity(entityfireball);
                    f = -40;
                }
            } else
            if(f > 0)
                f--;
        } else
        {
            U = yaw = (-(float)Math.atan2(motX, motZ) * 180F) / 3.141593F;
            if(f > 0)
                f--;
        }
        if(!world.isStatic)
        {
            byte b0 = datawatcher.getByte(16);
            byte b1 = (byte)(f <= 10 ? 0 : 1);
            if(b0 != b1)
                datawatcher.watch(16, Byte.valueOf(b1));
        }
    }

    private boolean a(double d0, double d1, double d2, double d3)
    {
        double d4 = (b - locX) / d3;
        double d5 = (c - locY) / d3;
        double d6 = (d - locZ) / d3;
        AxisAlignedBB axisalignedbb = boundingBox.clone();
        for(int i = 1; (double)i < d3; i++)
        {
            axisalignedbb.d(d4, d5, d6);
            if(world.getEntities(this, axisalignedbb).size() > 0)
                return false;
        }

        return true;
    }

    protected String h()
    {
        return "mob.ghast.moan";
    }

    protected String i()
    {
        return "mob.ghast.scream";
    }

    protected String j()
    {
        return "mob.ghast.death";
    }

    protected int k()
    {
        return Item.SULPHUR.id;
    }

    protected float l()
    {
        return 10F;
    }

    public boolean d()
    {
        return random.nextInt(20) == 0 && super.d() && world.difficulty > 0;
    }

    public int m()
    {
        return 1;
    }

    public int a;
    public double b;
    public double c;
    public double d;
    private Entity target;
    private int h;
    public int e;
    public int f;
}
