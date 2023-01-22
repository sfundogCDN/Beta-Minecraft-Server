// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityCreature.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityLiving, Entity, World, AxisAlignedBB, 
//            MathHelper, PathEntity, Vec3D

public abstract class EntityCreature extends EntityLiving
{

    public EntityCreature(World world)
    {
        super(world);
        e = false;
        f = 0;
    }

    protected boolean v()
    {
        return false;
    }

    protected void c_()
    {
        if(this.f > 0)
            this.f--;
        e = v();
        float f = 16F;
        if(this.target == null)
        {
            Entity target = findTarget();
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
                pathEntity = world.findPath(this, this.target, f);
        } else
        if(!this.target.ac())
        {
            EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_DIED);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                if(event.getTarget() == null)
                    this.target = null;
                else
                    this.target = ((CraftEntity)event.getTarget()).getHandle();
        } else
        {
            float f1 = this.target.g(this);
            if(f(this.target))
                a(this.target, f1);
            else
                b(this.target, f1);
        }
        if(!e && this.target != null && (pathEntity == null || random.nextInt(20) == 0))
            pathEntity = world.findPath(this, this.target, f);
        else
        if(!e && (pathEntity == null && random.nextInt(80) == 0 || this.f > 0 || random.nextInt(80) == 0))
            A();
        int i = MathHelper.floor(boundingBox.b + 0.5D);
        boolean flag = ao();
        boolean flag1 = ap();
        pitch = 0.0F;
        if(pathEntity != null && random.nextInt(100) != 0)
        {
            Vec3D vec3d = pathEntity.a(this);
            for(double d0 = length * 2.0F; vec3d != null && vec3d.d(locX, vec3d.b, locZ) < d0 * d0;)
            {
                pathEntity.a();
                if(pathEntity.b())
                {
                    vec3d = null;
                    pathEntity = null;
                } else
                {
                    vec3d = pathEntity.a(this);
                }
            }

            aS = false;
            if(vec3d != null)
            {
                double d1 = vec3d.a - locX;
                double d2 = vec3d.c - locZ;
                double d3 = vec3d.b - (double)i;
                float f2 = (float)((TrigMath.atan2(d2, d1) * 180D) / 3.1415927410125732D) - 90F;
                float f3 = f2 - yaw;
                aQ = aU;
                for(; f3 < -180F; f3 += 360F);
                for(; f3 >= 180F; f3 -= 360F);
                if(f3 > 30F)
                    f3 = 30F;
                if(f3 < -30F)
                    f3 = -30F;
                yaw += f3;
                if(e && this.target != null)
                {
                    double d4 = this.target.locX - locX;
                    double d5 = this.target.locZ - locZ;
                    float f4 = yaw;
                    yaw = (float)((Math.atan2(d5, d4) * 180D) / 3.1415927410125732D) - 90F;
                    f3 = (((f4 - yaw) + 90F) * 3.141593F) / 180F;
                    aP = -MathHelper.sin(f3) * aQ * 1.0F;
                    aQ = MathHelper.cos(f3) * aQ * 1.0F;
                }
                if(d3 > 0.0D)
                    aS = true;
            }
            if(this.target != null)
                a(this.target, 30F, 30F);
            if(positionChanged && !B())
                aS = true;
            if(random.nextFloat() < 0.8F && (flag || flag1))
                aS = true;
        } else
        {
            super.c_();
            pathEntity = null;
        }
    }

    protected void A()
    {
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999F;
        for(int l = 0; l < 10; l++)
        {
            int i1 = MathHelper.floor((locX + (double)random.nextInt(13)) - 6D);
            int j1 = MathHelper.floor((locY + (double)random.nextInt(7)) - 3D);
            int k1 = MathHelper.floor((locZ + (double)random.nextInt(13)) - 6D);
            float f1 = a(i1, j1, k1);
            if(f1 > f)
            {
                f = f1;
                i = i1;
                j = j1;
                k = k1;
                flag = true;
            }
        }

        if(flag)
            pathEntity = world.a(this, i, j, k, 10F);
    }

    protected void a(Entity entity1, float f1)
    {
    }

    protected void b(Entity entity1, float f1)
    {
    }

    protected float a(int i, int j, int k)
    {
        return 0.0F;
    }

    protected Entity findTarget()
    {
        return null;
    }

    public boolean d()
    {
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(boundingBox.b);
        int k = MathHelper.floor(locZ);
        return super.d() && a(i, j, k) >= 0.0F;
    }

    public boolean B()
    {
        return pathEntity != null;
    }

    public void setPathEntity(PathEntity pathentity)
    {
        pathEntity = pathentity;
    }

    public Entity C()
    {
        return target;
    }

    public void setTarget(Entity entity)
    {
        target = entity;
    }

    protected float D()
    {
        float f = super.D();
        if(this.f > 0)
            f *= 2.0F;
        return f;
    }

    public PathEntity pathEntity;
    public Entity target;
    protected boolean e;
    protected int f;
}
