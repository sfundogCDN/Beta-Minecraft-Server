// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityFireball.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, MovingObjectPosition, EntityLiving, MathHelper, 
//            World, Vec3D, AxisAlignedBB, DamageSource, 
//            NBTTagCompound

public class EntityFireball extends Entity
{

    public EntityFireball(World world)
    {
        super(world);
        f = -1;
        g = -1;
        h = -1;
        i = 0;
        j = false;
        a = 0;
        l = 0;
        yield = 1.0F;
        isIncendiary = true;
        b(1.0F, 1.0F);
    }

    protected void b()
    {
    }

    public EntityFireball(World world, EntityLiving entityliving, double d0, double d1, double d2)
    {
        super(world);
        f = -1;
        g = -1;
        h = -1;
        i = 0;
        j = false;
        a = 0;
        l = 0;
        yield = 1.0F;
        isIncendiary = true;
        shooter = entityliving;
        b(1.0F, 1.0F);
        setPositionRotation(entityliving.locX, entityliving.locY, entityliving.locZ, entityliving.yaw, entityliving.pitch);
        setPosition(locX, locY, locZ);
        height = 0.0F;
        motX = motY = motZ = 0.0D;
        setDirection(d0, d1, d2);
    }

    public void setDirection(double d0, double d1, double d2)
    {
        d0 += random.nextGaussian() * 0.40000000000000002D;
        d1 += random.nextGaussian() * 0.40000000000000002D;
        d2 += random.nextGaussian() * 0.40000000000000002D;
        double d3 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
        dirX = (d0 / d3) * 0.10000000000000001D;
        dirY = (d1 / d3) * 0.10000000000000001D;
        dirZ = (d2 / d3) * 0.10000000000000001D;
    }

    public void s_()
    {
        super.s_();
        fireTicks = 10;
        if(a > 0)
            a--;
        if(this.j)
        {
            int i = world.getTypeId(this.f, g, h);
            if(i == this.i)
            {
                this.k++;
                if(this.k == 1200)
                    die();
                return;
            }
            this.j = false;
            motX *= random.nextFloat() * 0.2F;
            motY *= random.nextFloat() * 0.2F;
            motZ *= random.nextFloat() * 0.2F;
            this.k = 0;
            l = 0;
        } else
        {
            l++;
        }
        Vec3D vec3d = Vec3D.create(locX, locY, locZ);
        Vec3D vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
        MovingObjectPosition movingobjectposition = world.a(vec3d, vec3d1);
        vec3d = Vec3D.create(locX, locY, locZ);
        vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
        if(movingobjectposition != null)
            vec3d1 = Vec3D.create(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
        Entity entity = null;
        List list = world.b(this, boundingBox.a(motX, motY, motZ).b(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);
            if(!entity1.r_() || entity1 == shooter && l < 25)
                continue;
            float f = 0.3F;
            AxisAlignedBB axisalignedbb = entity1.boundingBox.b(f, f, f);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
            if(movingobjectposition1 == null)
                continue;
            double d1 = vec3d.b(movingobjectposition1.f);
            if(d1 < d0 || d0 == 0.0D)
            {
                entity = entity1;
                d0 = d1;
            }
        }

        if(entity != null)
            movingobjectposition = new MovingObjectPosition(entity);
        if(movingobjectposition != null)
        {
            ProjectileHitEvent phe = new ProjectileHitEvent((Projectile)getBukkitEntity());
            world.getServer().getPluginManager().callEvent(phe);
            if(!world.isStatic)
            {
                if(movingobjectposition.entity != null)
                {
                    boolean stick;
                    if(movingobjectposition.entity instanceof EntityLiving)
                    {
                        org.bukkit.entity.Entity damagee = movingobjectposition.entity.getBukkitEntity();
                        Projectile projectile = (Projectile)getBukkitEntity();
                        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(projectile, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE, 0);
                        world.getServer().getPluginManager().callEvent(event);
                        shooter = projectile.getShooter() != null ? ((CraftLivingEntity)projectile.getShooter()).getHandle() : null;
                        if(event.isCancelled())
                            stick = !projectile.doesBounce();
                        else
                            stick = movingobjectposition.entity.damageEntity(DamageSource.fireball(this, shooter), event.getDamage());
                    } else
                    {
                        stick = movingobjectposition.entity.damageEntity(DamageSource.fireball(this, shooter), 0);
                    }
                    if(!stick);
                }
                ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(world.getServer(), this));
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    world.createExplosion(this, locX, locY, locZ, event.getRadius(), event.getFire());
            }
            die();
        }
        locX += motX;
        locY += motY;
        locZ += motZ;
        float f1 = MathHelper.a(motX * motX + motZ * motZ);
        yaw = (float)((Math.atan2(motX, motZ) * 180D) / 3.1415927410125732D);
        for(pitch = (float)((Math.atan2(motY, f1) * 180D) / 3.1415927410125732D); pitch - lastPitch < -180F; lastPitch -= 360F);
        for(; pitch - lastPitch >= 180F; lastPitch += 360F);
        for(; yaw - lastYaw < -180F; lastYaw -= 360F);
        for(; yaw - lastYaw >= 180F; lastYaw += 360F);
        pitch = lastPitch + (pitch - lastPitch) * 0.2F;
        yaw = lastYaw + (yaw - lastYaw) * 0.2F;
        float f2 = 0.95F;
        if(ao())
        {
            for(int k = 0; k < 4; k++)
            {
                float f3 = 0.25F;
                world.a("bubble", locX - motX * (double)f3, locY - motY * (double)f3, locZ - motZ * (double)f3, motX, motY, motZ);
            }

            f2 = 0.8F;
        }
        motX += dirX;
        motY += dirY;
        motZ += dirZ;
        motX *= f2;
        motY *= f2;
        motZ *= f2;
        world.a("smoke", locX, locY + 0.5D, locZ, 0.0D, 0.0D, 0.0D);
        setPosition(locX, locY, locZ);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("xTile", (short)f);
        nbttagcompound.a("yTile", (short)g);
        nbttagcompound.a("zTile", (short)h);
        nbttagcompound.a("inTile", (byte)i);
        nbttagcompound.a("shake", (byte)a);
        nbttagcompound.a("inGround", (byte)(j ? 1 : 0));
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        f = nbttagcompound.d("xTile");
        g = nbttagcompound.d("yTile");
        h = nbttagcompound.d("zTile");
        i = nbttagcompound.c("inTile") & 0xff;
        a = nbttagcompound.c("shake") & 0xff;
        j = nbttagcompound.c("inGround") == 1;
    }

    public boolean r_()
    {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        aq();
        if(damagesource.getEntity() != null)
        {
            Vec3D vec3d = damagesource.getEntity().ai();
            if(vec3d != null)
            {
                motX = vec3d.a;
                motY = vec3d.b;
                motZ = vec3d.c;
                dirX = motX * 0.10000000000000001D;
                dirY = motY * 0.10000000000000001D;
                dirZ = motZ * 0.10000000000000001D;
            }
            return true;
        } else
        {
            return false;
        }
    }

    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    public int a;
    public EntityLiving shooter;
    private int k;
    private int l;
    public double dirX;
    public double dirY;
    public double dirZ;
    public float yield;
    public boolean isIncendiary;
}
