// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityBoat.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, EntityHuman, World, DamageSource, 
//            Block, Item, AxisAlignedBB, Material, 
//            MathHelper, NBTTagCompound

public class EntityBoat extends Entity
{

    public void collide(Entity entity)
    {
        org.bukkit.entity.Entity hitEntity = entity != null ? entity.getBukkitEntity() : null;
        VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), hitEntity);
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
        {
            return;
        } else
        {
            super.collide(entity);
            return;
        }
    }

    public EntityBoat(World world)
    {
        super(world);
        maxSpeed = 0.40000000000000002D;
        occupiedDeceleration = 0.20000000000000001D;
        unoccupiedDeceleration = -1D;
        landBoats = false;
        damage = 0;
        b = 0;
        c = 1;
        aY = true;
        b(1.5F, 0.6F);
        height = width / 2.0F;
    }

    protected boolean e_()
    {
        return false;
    }

    protected void b()
    {
    }

    public AxisAlignedBB b(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB f()
    {
        return boundingBox;
    }

    public boolean g()
    {
        return true;
    }

    public EntityBoat(World world, double d0, double d1, double d2)
    {
        this(world);
        setPosition(d0, d1 + (double)height, d2);
        motX = 0.0D;
        motY = 0.0D;
        motZ = 0.0D;
        lastX = d0;
        lastY = d1;
        lastZ = d2;
        this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
    }

    public double n()
    {
        return (double)width * 0.0D - 0.30000001192092896D;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(!world.isStatic && !dead)
        {
            Vehicle vehicle = (Vehicle)getBukkitEntity();
            org.bukkit.entity.Entity attacker = damagesource.getEntity() != null ? damagesource.getEntity().getBukkitEntity() : null;
            VehicleDamageEvent event = new VehicleDamageEvent(vehicle, attacker, i);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return true;
            c = -c;
            b = 10;
            damage += i * 10;
            aq();
            if(damage > 40)
            {
                VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, attacker);
                world.getServer().getPluginManager().callEvent(destroyEvent);
                if(destroyEvent.isCancelled())
                {
                    damage = 40;
                    return true;
                }
                if(passenger != null)
                    passenger.mount(this);
                for(int j = 0; j < 3; j++)
                    a(Block.WOOD.id, 1, 0.0F);

                for(int j = 0; j < 2; j++)
                    a(Item.STICK.id, 1, 0.0F);

                die();
            }
            return true;
        } else
        {
            return true;
        }
    }

    public boolean r_()
    {
        return !dead;
    }

    public void s_()
    {
        double prevX = locX;
        double prevY = locY;
        double prevZ = locZ;
        float prevYaw = yaw;
        float prevPitch = pitch;
        super.s_();
        if(b > 0)
            b--;
        if(damage > 0)
            damage--;
        lastX = locX;
        lastY = locY;
        lastZ = locZ;
        byte b0 = 5;
        double d0 = 0.0D;
        for(int i = 0; i < b0; i++)
        {
            double d1 = (boundingBox.b + ((boundingBox.e - boundingBox.b) * (double)(i + 0)) / (double)b0) - 0.125D;
            double d2 = (boundingBox.b + ((boundingBox.e - boundingBox.b) * (double)(i + 1)) / (double)b0) - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.b(boundingBox.a, d1, boundingBox.c, boundingBox.d, d2, boundingBox.f);
            if(world.b(axisalignedbb, Material.WATER))
                d0 += 1.0D / (double)b0;
        }

        if(world.isStatic)
        {
            if(d > 0)
            {
                double d3 = locX + (e - locX) / (double)d;
                double d4 = locY + (f - locY) / (double)d;
                double d5 = locZ + (g - locZ) / (double)d;
                double d6;
                for(d6 = h - (double)yaw; d6 < -180D; d6 += 360D);
                for(; d6 >= 180D; d6 -= 360D);
                yaw = (float)((double)yaw + d6 / (double)d);
                pitch = (float)((double)pitch + (this.i - (double)pitch) / (double)d);
                d--;
                setPosition(d3, d4, d5);
                c(yaw, pitch);
            } else
            {
                double d3 = locX + motX;
                double d4 = locY + motY;
                double d5 = locZ + motZ;
                setPosition(d3, d4, d5);
                if(onGround)
                {
                    motX *= 0.5D;
                    motY *= 0.5D;
                    motZ *= 0.5D;
                }
                motX *= 0.99000000953674316D;
                motY *= 0.94999998807907104D;
                motZ *= 0.99000000953674316D;
            }
        } else
        {
            double d3;
            if(d0 < 1.0D)
            {
                d3 = d0 * 2D - 1.0D;
                motY += 0.039999999105930328D * d3;
            } else
            {
                if(motY < 0.0D)
                    motY /= 2D;
                motY += 0.0070000002160668373D;
            }
            if(passenger != null)
            {
                motX += passenger.motX * occupiedDeceleration;
                motZ += passenger.motZ * occupiedDeceleration;
            } else
            if(unoccupiedDeceleration >= 0.0D)
            {
                motX *= unoccupiedDeceleration;
                motZ *= unoccupiedDeceleration;
                if(motX <= 1.0000000000000001E-005D)
                    motX = 0.0D;
                if(motZ <= 1.0000000000000001E-005D)
                    motZ = 0.0D;
            }
            d3 = maxSpeed;
            if(motX < -d3)
                motX = -d3;
            if(motX > d3)
                motX = d3;
            if(motZ < -d3)
                motZ = -d3;
            if(motZ > d3)
                motZ = d3;
            if(onGround && !landBoats)
            {
                motX *= 0.5D;
                motY *= 0.5D;
                motZ *= 0.5D;
            }
            move(motX, motY, motZ);
            double d4 = Math.sqrt(motX * motX + motZ * motZ);
            double d5;
            double d6;
            if(d4 > 0.14999999999999999D)
            {
                d5 = Math.cos(((double)yaw * 3.1415926535897931D) / 180D);
                d6 = Math.sin(((double)yaw * 3.1415926535897931D) / 180D);
                for(int j = 0; (double)j < 1.0D + d4 * 60D; j++)
                {
                    double d7 = random.nextFloat() * 2.0F - 1.0F;
                    double d8 = (double)(random.nextInt(2) * 2 - 1) * 0.69999999999999996D;
                    if(random.nextBoolean())
                    {
                        double d9 = (locX - d5 * d7 * 0.80000000000000004D) + d6 * d8;
                        double d10 = locZ - d6 * d7 * 0.80000000000000004D - d5 * d8;
                        world.a("splash", d9, locY - 0.125D, d10, motX, motY, motZ);
                    } else
                    {
                        double d9 = locX + d5 + d6 * d7 * 0.69999999999999996D;
                        double d10 = (locZ + d6) - d5 * d7 * 0.69999999999999996D;
                        world.a("splash", d9, locY - 0.125D, d10, motX, motY, motZ);
                    }
                }

            }
            if(positionChanged && d4 > 0.14999999999999999D)
            {
                if(!world.isStatic)
                {
                    Vehicle vehicle = (Vehicle)getBukkitEntity();
                    VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, null);
                    world.getServer().getPluginManager().callEvent(destroyEvent);
                    if(!destroyEvent.isCancelled())
                    {
                        die();
                        for(int k = 0; k < 3; k++)
                            a(Block.WOOD.id, 1, 0.0F);

                        for(int k = 0; k < 2; k++)
                            a(Item.STICK.id, 1, 0.0F);

                    }
                }
            } else
            {
                motX *= 0.99000000953674316D;
                motY *= 0.94999998807907104D;
                motZ *= 0.99000000953674316D;
            }
            pitch = 0.0F;
            d5 = yaw;
            d6 = lastX - locX;
            double d11 = lastZ - locZ;
            if(d6 * d6 + d11 * d11 > 0.001D)
                d5 = (float)((Math.atan2(d11, d6) * 180D) / 3.1415926535897931D);
            double d12;
            for(d12 = d5 - (double)yaw; d12 >= 180D; d12 -= 360D);
            for(; d12 < -180D; d12 += 360D);
            if(d12 > 20D)
                d12 = 20D;
            if(d12 < -20D)
                d12 = -20D;
            yaw = (float)((double)yaw + d12);
            c(yaw, pitch);
            Server server = world.getServer();
            org.bukkit.World bworld = world.getWorld();
            Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
            Location to = new Location(bworld, locX, locY, locZ, yaw, pitch);
            Vehicle vehicle = (Vehicle)getBukkitEntity();
            server.getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
            if(!from.equals(to))
            {
                VehicleMoveEvent event = new VehicleMoveEvent(vehicle, from, to);
                server.getPluginManager().callEvent(event);
            }
            List list = world.b(this, boundingBox.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            if(list != null && list.size() > 0)
            {
                for(int l = 0; l < list.size(); l++)
                {
                    Entity entity = (Entity)list.get(l);
                    if(entity != passenger && entity.g() && (entity instanceof EntityBoat))
                        entity.collide(this);
                }

            }
            for(int l = 0; l < 4; l++)
            {
                int i1 = MathHelper.floor(locX + ((double)(l % 2) - 0.5D) * 0.80000000000000004D);
                int j1 = MathHelper.floor(locY);
                int k1 = MathHelper.floor(locZ + ((double)(l / 2) - 0.5D) * 0.80000000000000004D);
                if(world.getTypeId(i1, j1, k1) == Block.SNOW.id)
                    world.setTypeId(i1, j1, k1, 0);
            }

            if(passenger != null && passenger.dead)
            {
                passenger.vehicle = null;
                passenger = null;
            }
        }
    }

    public void g_()
    {
        if(passenger != null)
        {
            double d0 = Math.cos(((double)yaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            double d1 = Math.sin(((double)yaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            passenger.setPosition(locX + d0, locY + n() + passenger.M(), locZ + d1);
        }
    }

    protected void b(NBTTagCompound nbttagcompound1)
    {
    }

    protected void a(NBTTagCompound nbttagcompound1)
    {
    }

    public boolean b(EntityHuman entityhuman)
    {
        if(passenger != null && (passenger instanceof EntityHuman) && passenger != entityhuman)
            return true;
        if(!world.isStatic)
        {
            VehicleEnterEvent event = new VehicleEnterEvent((Vehicle)getBukkitEntity(), entityhuman.getBukkitEntity());
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return true;
            entityhuman.mount(this);
        }
        return true;
    }

    public int damage;
    public int b;
    public int c;
    private int d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    public double maxSpeed;
    public double occupiedDeceleration;
    public double unoccupiedDeceleration;
    public boolean landBoats;
}
