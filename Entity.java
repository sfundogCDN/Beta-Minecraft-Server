// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Entity.java

package net.minecraft.server;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            DataWatcher, EntityPlayer, EntityLiving, AxisAlignedBB, 
//            NBTTagDouble, NBTTagFloat, NBTTagList, ItemStack, 
//            EntityItem, MathHelper, World, DamageSource, 
//            Block, StepSound, Material, BlockFluids, 
//            NBTTagCompound, MinecraftServer, WorldServer, EntityTypes, 
//            EntityWeatherStorm, StatisticCollector, EntityHuman, Vec3D

public abstract class Entity
{

    public Entity(World world)
    {
        uniqueId = UUID.randomUUID();
        id = entityCount++;
        aX = 1.0D;
        aY = false;
        onGround = false;
        bt = false;
        velocityChanged = false;
        bw = true;
        dead = false;
        height = 0.0F;
        length = 0.6F;
        width = 1.8F;
        bB = 0.0F;
        bC = 0.0F;
        fallDistance = 0.0F;
        b = 1;
        bH = 0.0F;
        bI = 0.0F;
        bJ = false;
        bK = 0.0F;
        random = new Random();
        ticksLived = 0;
        maxFireTicks = 1;
        fireTicks = 0;
        maxAirTicks = 300;
        bQ = false;
        noDamageTicks = 0;
        airTicks = 300;
        justCreated = true;
        fireProof = false;
        datawatcher = new DataWatcher();
        bV = false;
        this.world = world;
        setPosition(0.0D, 0.0D, 0.0D);
        datawatcher.a(0, Byte.valueOf((byte)0));
        b();
    }

    protected abstract void b();

    public DataWatcher getDataWatcher()
    {
        return datawatcher;
    }

    public boolean equals(Object object)
    {
        return (object instanceof Entity) ? ((Entity)object).id == id : false;
    }

    public int hashCode()
    {
        return id;
    }

    public void die()
    {
        dead = true;
    }

    protected void b(float f, float f1)
    {
        length = f;
        width = f1;
    }

    protected void c(float f, float f1)
    {
        if(Float.isNaN(f))
            f = 0.0F;
        if(f == (1.0F / 0.0F) || f == (-1.0F / 0.0F))
        {
            if(this instanceof EntityPlayer)
            {
                System.err.println((new StringBuilder()).append(((CraftPlayer)getBukkitEntity()).getName()).append(" was caught trying to crash the server with an invalid yaw").toString());
                ((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
            }
            f = 0.0F;
        }
        if(Float.isNaN(f1))
            f1 = 0.0F;
        if(f1 == (1.0F / 0.0F) || f1 == (-1.0F / 0.0F))
        {
            if(this instanceof EntityPlayer)
            {
                System.err.println((new StringBuilder()).append(((CraftPlayer)getBukkitEntity()).getName()).append(" was caught trying to crash the server with an invalid pitch").toString());
                ((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
            }
            f1 = 0.0F;
        }
        yaw = f % 360F;
        pitch = f1 % 360F;
    }

    public void setPosition(double d0, double d1, double d2)
    {
        locX = d0;
        locY = d1;
        locZ = d2;
        float f = length / 2.0F;
        float f1 = width;
        boundingBox.c(d0 - (double)f, (d1 - (double)height) + (double)bH, d2 - (double)f, d0 + (double)f, (d1 - (double)height) + (double)bH + (double)f1, d2 + (double)f);
    }

    public void s_()
    {
        aa();
    }

    public void aa()
    {
        if(vehicle != null && vehicle.dead)
            vehicle = null;
        ticksLived++;
        bB = bC;
        lastX = locX;
        lastY = locY;
        lastZ = locZ;
        lastPitch = pitch;
        lastYaw = yaw;
        if(isSprinting())
        {
            int j = MathHelper.floor(locX);
            int k = MathHelper.floor(locY - 0.20000000298023224D - (double)height);
            int i = MathHelper.floor(locZ);
            int l = world.getTypeId(j, k, i);
            if(l > 0)
                world.a((new StringBuilder()).append("tilecrack_").append(l).toString(), locX + ((double)random.nextFloat() - 0.5D) * (double)length, boundingBox.b + 0.10000000000000001D, locZ + ((double)random.nextFloat() - 0.5D) * (double)length, -motX * 4D, 1.5D, -motZ * 4D);
        }
        if(f_())
        {
            if(!bQ && !justCreated)
            {
                float f = MathHelper.a(motX * motX * 0.20000000298023224D + motY * motY + motZ * motZ * 0.20000000298023224D) * 0.2F;
                if(f > 1.0F)
                    f = 1.0F;
                world.makeSound(this, "random.splash", f, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.4F);
                float f1 = MathHelper.floor(boundingBox.b);
                for(int i = 0; (float)i < 1.0F + length * 20F; i++)
                {
                    float f3 = (random.nextFloat() * 2.0F - 1.0F) * length;
                    float f2 = (random.nextFloat() * 2.0F - 1.0F) * length;
                    world.a("bubble", locX + (double)f3, f1 + 1.0F, locZ + (double)f2, motX, motY - (double)(random.nextFloat() * 0.2F), motZ);
                }

                for(int i = 0; (float)i < 1.0F + length * 20F; i++)
                {
                    float f3 = (random.nextFloat() * 2.0F - 1.0F) * length;
                    float f2 = (random.nextFloat() * 2.0F - 1.0F) * length;
                    world.a("splash", locX + (double)f3, f1 + 1.0F, locZ + (double)f2, motX, motY, motZ);
                }

            }
            fallDistance = 0.0F;
            bQ = true;
            fireTicks = 0;
        } else
        {
            bQ = false;
        }
        if(world.isStatic)
            fireTicks = 0;
        else
        if(fireTicks > 0)
            if(fireProof)
            {
                fireTicks -= 4;
                if(fireTicks < 0)
                    fireTicks = 0;
            } else
            {
                if(fireTicks % 20 == 0)
                    if(this instanceof EntityLiving)
                    {
                        EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE_TICK, 1);
                        world.getServer().getPluginManager().callEvent(event);
                        if(!event.isCancelled())
                            damageEntity(DamageSource.BURN, event.getDamage());
                    } else
                    {
                        damageEntity(DamageSource.BURN, 1);
                    }
                fireTicks--;
            }
        if(ap())
            am();
        if(locY < -64D)
            ah();
        if(!world.isStatic)
        {
            a(0, fireTicks > 0);
            a(2, vehicle != null);
        }
        justCreated = false;
    }

    protected void am()
    {
        if(!fireProof)
        {
            if(this instanceof EntityLiving)
            {
                Server server = world.getServer();
                Block damager = null;
                org.bukkit.entity.Entity damagee = getBukkitEntity();
                EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.LAVA, 4);
                server.getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    damageEntity(DamageSource.LAVA, event.getDamage());
                if(fireTicks <= 0)
                {
                    EntityCombustEvent combustEvent = new EntityCombustEvent(damagee);
                    server.getPluginManager().callEvent(combustEvent);
                    if(!combustEvent.isCancelled())
                        fireTicks = 600;
                } else
                {
                    fireTicks = 600;
                }
                return;
            }
            damageEntity(DamageSource.LAVA, 4);
            fireTicks = 600;
        }
    }

    protected void ah()
    {
        die();
    }

    public boolean d(double d0, double d1, double d2)
    {
        AxisAlignedBB axisalignedbb = boundingBox.c(d0, d1, d2);
        List list = world.getEntities(this, axisalignedbb);
        return list.size() <= 0 ? !world.c(axisalignedbb) : false;
    }

    public void move(double d0, double d1, double d2)
    {
        if(bJ)
        {
            boundingBox.d(d0, d1, d2);
            locX = (boundingBox.a + boundingBox.d) / 2D;
            locY = (boundingBox.b + (double)height) - (double)bH;
            locZ = (boundingBox.c + boundingBox.f) / 2D;
        } else
        {
            bH *= 0.4F;
            double d3 = locX;
            double d4 = locZ;
            if(bv)
            {
                bv = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                motX = 0.0D;
                motY = 0.0D;
                motZ = 0.0D;
            }
            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            AxisAlignedBB axisalignedbb = boundingBox.clone();
            boolean flag = onGround && isSneaking();
            if(flag)
            {
                double d8 = 0.050000000000000003D;
                for(; d0 != 0.0D && world.getEntities(this, boundingBox.c(d0, -1D, 0.0D)).size() == 0; d5 = d0)
                {
                    if(d0 < d8 && d0 >= -d8)
                    {
                        d0 = 0.0D;
                        continue;
                    }
                    if(d0 > 0.0D)
                        d0 -= d8;
                    else
                        d0 += d8;
                }

                for(; d2 != 0.0D && world.getEntities(this, boundingBox.c(0.0D, -1D, d2)).size() == 0; d7 = d2)
                {
                    if(d2 < d8 && d2 >= -d8)
                    {
                        d2 = 0.0D;
                        continue;
                    }
                    if(d2 > 0.0D)
                        d2 -= d8;
                    else
                        d2 += d8;
                }

            }
            List list = world.getEntities(this, boundingBox.a(d0, d1, d2));
            for(int i = 0; i < list.size(); i++)
                d1 = ((AxisAlignedBB)list.get(i)).b(boundingBox, d1);

            boundingBox.d(0.0D, d1, 0.0D);
            if(!bw && d6 != d1)
            {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }
            boolean flag1 = onGround || d6 != d1 && d6 < 0.0D;
            for(int j = 0; j < list.size(); j++)
                d0 = ((AxisAlignedBB)list.get(j)).a(boundingBox, d0);

            boundingBox.d(d0, 0.0D, 0.0D);
            if(!bw && d5 != d0)
            {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }
            for(int j = 0; j < list.size(); j++)
                d2 = ((AxisAlignedBB)list.get(j)).c(boundingBox, d2);

            boundingBox.d(0.0D, 0.0D, d2);
            if(!bw && d7 != d2)
            {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }
            double d9;
            double d10;
            int k;
            if(bI > 0.0F && flag1 && (flag || bH < 0.05F) && (d5 != d0 || d7 != d2))
            {
                d9 = d0;
                d10 = d1;
                double d11 = d2;
                d0 = d5;
                d1 = bI;
                d2 = d7;
                AxisAlignedBB axisalignedbb1 = boundingBox.clone();
                boundingBox.b(axisalignedbb);
                list = world.getEntities(this, boundingBox.a(d5, d1, d7));
                for(k = 0; k < list.size(); k++)
                    d1 = ((AxisAlignedBB)list.get(k)).b(boundingBox, d1);

                boundingBox.d(0.0D, d1, 0.0D);
                if(!bw && d6 != d1)
                {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }
                for(k = 0; k < list.size(); k++)
                    d0 = ((AxisAlignedBB)list.get(k)).a(boundingBox, d0);

                boundingBox.d(d0, 0.0D, 0.0D);
                if(!bw && d5 != d0)
                {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }
                for(k = 0; k < list.size(); k++)
                    d2 = ((AxisAlignedBB)list.get(k)).c(boundingBox, d2);

                boundingBox.d(0.0D, 0.0D, d2);
                if(!bw && d7 != d2)
                {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }
                if(!bw && d6 != d1)
                {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else
                {
                    d1 = -bI;
                    for(k = 0; k < list.size(); k++)
                        d1 = ((AxisAlignedBB)list.get(k)).b(boundingBox, d1);

                    boundingBox.d(0.0D, d1, 0.0D);
                }
                if(d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2)
                {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    boundingBox.b(axisalignedbb1);
                } else
                {
                    double d12 = boundingBox.b - (double)(int)boundingBox.b;
                    if(d12 > 0.0D)
                        bH = (float)((double)bH + d12 + 0.01D);
                }
            }
            locX = (boundingBox.a + boundingBox.d) / 2D;
            locY = (boundingBox.b + (double)height) - (double)bH;
            locZ = (boundingBox.c + boundingBox.f) / 2D;
            positionChanged = d5 != d0 || d7 != d2;
            bs = d6 != d1;
            onGround = d6 != d1 && d6 < 0.0D;
            bt = positionChanged || bs;
            a(d1, onGround);
            if(d5 != d0)
                motX = 0.0D;
            if(d6 != d1)
                motY = 0.0D;
            if(d7 != d2)
                motZ = 0.0D;
            d9 = locX - d3;
            d10 = locZ - d4;
            if(positionChanged && (getBukkitEntity() instanceof Vehicle))
            {
                Vehicle vehicle = (Vehicle)getBukkitEntity();
                Block block = world.getWorld().getBlockAt(MathHelper.floor(locX), MathHelper.floor(locY - 0.20000000298023224D - (double)height), MathHelper.floor(locZ));
                if(d5 > d0)
                    block = block.getRelative(BlockFace.SOUTH);
                else
                if(d5 < d0)
                    block = block.getRelative(BlockFace.NORTH);
                else
                if(d7 > d2)
                    block = block.getRelative(BlockFace.WEST);
                else
                if(d7 < d2)
                    block = block.getRelative(BlockFace.EAST);
                VehicleBlockCollisionEvent event = new VehicleBlockCollisionEvent(vehicle, block);
                world.getServer().getPluginManager().callEvent(event);
            }
            int l;
            int i1;
            int j1;
            if(e_() && !flag && this.vehicle == null)
            {
                bC = (float)((double)bC + (double)MathHelper.a(d9 * d9 + d10 * d10) * 0.59999999999999998D);
                l = MathHelper.floor(locX);
                i1 = MathHelper.floor(locY - 0.20000000298023224D - (double)height);
                j1 = MathHelper.floor(locZ);
                k = world.getTypeId(l, i1, j1);
                if(world.getTypeId(l, i1 - 1, j1) == Block.FENCE.id)
                    k = world.getTypeId(l, i1 - 1, j1);
                if(bC > (float)b && k > 0)
                {
                    b = (int)bC + 1;
                    StepSound stepsound = Block.byId[k].stepSound;
                    if(world.getTypeId(l, i1 + 1, j1) == Block.SNOW.id)
                    {
                        stepsound = Block.SNOW.stepSound;
                        world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
                    } else
                    if(!Block.byId[k].material.isLiquid())
                        world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
                    Block.byId[k].b(world, l, i1, j1, this);
                }
            }
            l = MathHelper.floor(boundingBox.a + 0.001D);
            i1 = MathHelper.floor(boundingBox.b + 0.001D);
            j1 = MathHelper.floor(boundingBox.c + 0.001D);
            k = MathHelper.floor(boundingBox.d - 0.001D);
            int k1 = MathHelper.floor(boundingBox.e - 0.001D);
            int l1 = MathHelper.floor(boundingBox.f - 0.001D);
            if(world.a(l, i1, j1, k, k1, l1))
            {
                for(int i2 = l; i2 <= k; i2++)
                {
                    for(int j2 = i1; j2 <= k1; j2++)
                    {
                        for(int k2 = j1; k2 <= l1; k2++)
                        {
                            int l2 = world.getTypeId(i2, j2, k2);
                            if(l2 > 0)
                                Block.byId[l2].a(world, i2, j2, k2, this);
                        }

                    }

                }

            }
            boolean flag2 = an();
            if(world.d(boundingBox.shrink(0.001D, 0.001D, 0.001D)))
            {
                burn(1);
                if(!flag2)
                {
                    fireTicks++;
                    if(fireTicks <= 0)
                    {
                        EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity());
                        world.getServer().getPluginManager().callEvent(event);
                        if(!event.isCancelled())
                            fireTicks = 300;
                    } else
                    {
                        fireTicks = 300;
                    }
                }
            } else
            if(fireTicks <= 0)
                fireTicks = -maxFireTicks;
            if(flag2 && fireTicks > 0)
            {
                world.makeSound(this, "random.fizz", 0.7F, 1.6F + (random.nextFloat() - random.nextFloat()) * 0.4F);
                fireTicks = -maxFireTicks;
            }
        }
    }

    protected boolean e_()
    {
        return true;
    }

    protected void a(double d0, boolean flag)
    {
        if(flag)
        {
            if(fallDistance > 0.0F)
            {
                a(fallDistance);
                fallDistance = 0.0F;
            }
        } else
        if(d0 < 0.0D)
            fallDistance = (float)((double)fallDistance - d0);
    }

    public AxisAlignedBB f()
    {
        return null;
    }

    protected void burn(int i)
    {
        if(!fireProof)
        {
            if(this instanceof EntityLiving)
            {
                EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE, i);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
                i = event.getDamage();
            }
            damageEntity(DamageSource.FIRE, i);
        }
    }

    protected void a(float f)
    {
        if(passenger != null)
            passenger.a(f);
    }

    public boolean an()
    {
        return bQ || world.s(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ));
    }

    public boolean ao()
    {
        return bQ;
    }

    public boolean f_()
    {
        return world.a(boundingBox.b(0.0D, -0.40000000596046448D, 0.0D).shrink(0.001D, 0.001D, 0.001D), Material.WATER, this);
    }

    public boolean a(Material material)
    {
        double d0 = locY + (double)t();
        int i = MathHelper.floor(locX);
        int j = MathHelper.d(MathHelper.floor(d0));
        int k = MathHelper.floor(locZ);
        int l = world.getTypeId(i, j, k);
        if(l != 0 && Block.byId[l].material == material)
        {
            float f = BlockFluids.c(world.getData(i, j, k)) - 0.1111111F;
            float f1 = (float)(j + 1) - f;
            return d0 < (double)f1;
        } else
        {
            return false;
        }
    }

    public float t()
    {
        return 0.0F;
    }

    public boolean ap()
    {
        return world.a(boundingBox.b(-0.10000000149011612D, -0.40000000596046448D, -0.10000000149011612D), Material.LAVA);
    }

    public void a(float f, float f1, float f2)
    {
        float f3 = MathHelper.c(f * f + f1 * f1);
        if(f3 >= 0.01F)
        {
            if(f3 < 1.0F)
                f3 = 1.0F;
            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = MathHelper.sin((yaw * 3.141593F) / 180F);
            float f5 = MathHelper.cos((yaw * 3.141593F) / 180F);
            motX += f * f5 - f1 * f4;
            motZ += f1 * f5 + f * f4;
        }
    }

    public float a_(float f)
    {
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(locZ);
        World world = this.world;
        if(world.isLoaded(i, 64, j))
        {
            double d0 = (boundingBox.e - boundingBox.b) * 0.66000000000000003D;
            int k = MathHelper.floor((locY - (double)height) + d0);
            return this.world.m(i, k, j);
        } else
        {
            return 0.0F;
        }
    }

    public void spawnIn(World world)
    {
        if(world == null)
        {
            die();
            this.world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
            return;
        } else
        {
            this.world = world;
            return;
        }
    }

    public void setLocation(double d0, double d1, double d2, float f, 
            float f1)
    {
        lastX = locX = d0;
        lastY = locY = d1;
        lastZ = locZ = d2;
        lastYaw = yaw = f;
        lastPitch = pitch = f1;
        bH = 0.0F;
        double d3 = lastYaw - f;
        if(d3 < -180D)
            lastYaw += 360F;
        if(d3 >= 180D)
            lastYaw -= 360F;
        setPosition(locX, locY, locZ);
        c(f, f1);
    }

    public void setPositionRotation(double d0, double d1, double d2, float f, 
            float f1)
    {
        bE = lastX = locX = d0;
        bF = lastY = locY = d1 + (double)height;
        bG = lastZ = locZ = d2;
        yaw = f;
        pitch = f1;
        setPosition(locX, locY, locZ);
    }

    public float g(Entity entity)
    {
        float f = (float)(locX - entity.locX);
        float f1 = (float)(locY - entity.locY);
        float f2 = (float)(locZ - entity.locZ);
        return MathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2)
    {
        double d3 = locX - d0;
        double d4 = locY - d1;
        double d5 = locZ - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2)
    {
        double d3 = locX - d0;
        double d4 = locY - d1;
        double d5 = locZ - d2;
        return (double)MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double h(Entity entity)
    {
        double d0 = locX - entity.locX;
        double d1 = locY - entity.locY;
        double d2 = locZ - entity.locZ;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void a_(EntityHuman entityhuman1)
    {
    }

    public void collide(Entity entity)
    {
        if(entity.passenger != this && entity.vehicle != this)
        {
            double d0 = entity.locX - locX;
            double d1 = entity.locZ - locZ;
            double d2 = MathHelper.a(d0, d1);
            if(d2 >= 0.0099999997764825821D)
            {
                d2 = MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;
                if(d3 > 1.0D)
                    d3 = 1.0D;
                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= 1.0F - bK;
                d1 *= 1.0F - bK;
                b(-d0, 0.0D, -d1);
                entity.b(d0, 0.0D, d1);
            }
        }
    }

    public void b(double d0, double d1, double d2)
    {
        motX += d0;
        motY += d1;
        motZ += d2;
        ca = true;
    }

    protected void aq()
    {
        velocityChanged = true;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        aq();
        return false;
    }

    public boolean r_()
    {
        return false;
    }

    public boolean g()
    {
        return false;
    }

    public void b(Entity entity1, int j)
    {
    }

    public boolean c(NBTTagCompound nbttagcompound)
    {
        String s = ar();
        if(!dead && s != null)
        {
            nbttagcompound.setString("id", s);
            d(nbttagcompound);
            return true;
        } else
        {
            return false;
        }
    }

    public void d(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Pos", a(new double[] {
            locX, locY + (double)bH, locZ
        }));
        nbttagcompound.a("Motion", a(new double[] {
            motX, motY, motZ
        }));
        if(Float.isNaN(yaw))
            yaw = 0.0F;
        if(Float.isNaN(pitch))
            pitch = 0.0F;
        nbttagcompound.a("Rotation", a(new float[] {
            yaw, pitch
        }));
        nbttagcompound.a("FallDistance", fallDistance);
        nbttagcompound.a("Fire", (short)fireTicks);
        nbttagcompound.a("Air", (short)airTicks);
        nbttagcompound.a("OnGround", onGround);
        nbttagcompound.setLong("WorldUUIDLeast", world.getUUID().getLeastSignificantBits());
        nbttagcompound.setLong("WorldUUIDMost", world.getUUID().getMostSignificantBits());
        nbttagcompound.setLong("UUIDLeast", uniqueId.getLeastSignificantBits());
        nbttagcompound.setLong("UUIDMost", uniqueId.getMostSignificantBits());
        b(nbttagcompound);
    }

    public void e(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = nbttagcompound.l("Pos");
        NBTTagList nbttaglist1 = nbttagcompound.l("Motion");
        NBTTagList nbttaglist2 = nbttagcompound.l("Rotation");
        motX = ((NBTTagDouble)nbttaglist1.a(0)).a;
        motY = ((NBTTagDouble)nbttaglist1.a(1)).a;
        motZ = ((NBTTagDouble)nbttaglist1.a(2)).a;
        lastX = bE = locX = ((NBTTagDouble)nbttaglist.a(0)).a;
        lastY = bF = locY = ((NBTTagDouble)nbttaglist.a(1)).a;
        lastZ = bG = locZ = ((NBTTagDouble)nbttaglist.a(2)).a;
        lastYaw = yaw = ((NBTTagFloat)nbttaglist2.a(0)).a;
        lastPitch = pitch = ((NBTTagFloat)nbttaglist2.a(1)).a;
        fallDistance = nbttagcompound.g("FallDistance");
        fireTicks = nbttagcompound.d("Fire");
        airTicks = nbttagcompound.d("Air");
        onGround = nbttagcompound.m("OnGround");
        setPosition(locX, locY, locZ);
        long least = nbttagcompound.getLong("UUIDLeast");
        long most = nbttagcompound.getLong("UUIDMost");
        if(least != 0L && most != 0L)
            uniqueId = new UUID(most, least);
        c(yaw, pitch);
        a(nbttagcompound);
        if(!(getBukkitEntity() instanceof Vehicle))
        {
            if(Math.abs(motX) > 10D)
                motX = 0.0D;
            if(Math.abs(motY) > 10D)
                motY = 0.0D;
            if(Math.abs(motZ) > 10D)
                motZ = 0.0D;
        }
        if(this instanceof EntityPlayer)
        {
            Server server = Bukkit.getServer();
            org.bukkit.World bworld = null;
            String worldName = nbttagcompound.getString("World");
            if(nbttagcompound.hasKey("WorldUUIDMost") && nbttagcompound.hasKey("WorldUUIDLeast"))
            {
                UUID uid = new UUID(nbttagcompound.getLong("WorldUUIDMost"), nbttagcompound.getLong("WorldUUIDLeast"));
                bworld = server.getWorld(uid);
            } else
            {
                bworld = server.getWorld(worldName);
            }
            if(bworld == null)
            {
                EntityPlayer entityPlayer = (EntityPlayer)this;
                bworld = ((CraftServer)server).getServer().getWorldServer(entityPlayer.dimension).getWorld();
            }
            spawnIn(bworld != null ? ((World) (((CraftWorld)bworld).getHandle())) : null);
        }
    }

    protected final String ar()
    {
        return EntityTypes.b(this);
    }

    protected abstract void a(NBTTagCompound nbttagcompound);

    protected abstract void b(NBTTagCompound nbttagcompound);

    protected transient NBTTagList a(double adouble[])
    {
        NBTTagList nbttaglist = new NBTTagList();
        double adouble1[] = adouble;
        int i = adouble.length;
        for(int j = 0; j < i; j++)
        {
            double d0 = adouble1[j];
            nbttaglist.a(new NBTTagDouble(d0));
        }

        return nbttaglist;
    }

    protected transient NBTTagList a(float afloat[])
    {
        NBTTagList nbttaglist = new NBTTagList();
        float afloat1[] = afloat;
        int i = afloat.length;
        for(int j = 0; j < i; j++)
        {
            float f = afloat1[j];
            nbttaglist.a(new NBTTagFloat(f));
        }

        return nbttaglist;
    }

    public EntityItem b(int i, int j)
    {
        return a(i, j, 0.0F);
    }

    public EntityItem a(int i, int j, float f)
    {
        return a(new ItemStack(i, j, 0), f);
    }

    public EntityItem a(ItemStack itemstack, float f)
    {
        EntityItem entityitem = new EntityItem(world, locX, locY + (double)f, locZ, itemstack);
        entityitem.pickupDelay = 10;
        world.addEntity(entityitem);
        return entityitem;
    }

    public boolean ac()
    {
        return !dead;
    }

    public boolean O()
    {
        for(int i = 0; i < 8; i++)
        {
            float f = ((float)((i >> 0) % 2) - 0.5F) * length * 0.9F;
            float f1 = ((float)((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float)((i >> 2) % 2) - 0.5F) * length * 0.9F;
            int j = MathHelper.floor(locX + (double)f);
            int k = MathHelper.floor(locY + (double)t() + (double)f1);
            int l = MathHelper.floor(locZ + (double)f2);
            if(world.e(j, k, l))
                return true;
        }

        return false;
    }

    public boolean b(EntityHuman entityhuman)
    {
        return false;
    }

    public AxisAlignedBB b(Entity entity)
    {
        return null;
    }

    public void I()
    {
        if(vehicle.dead)
        {
            vehicle = null;
        } else
        {
            motX = 0.0D;
            motY = 0.0D;
            motZ = 0.0D;
            s_();
            if(vehicle != null)
            {
                vehicle.g_();
                e += vehicle.yaw - vehicle.lastYaw;
                d += vehicle.pitch - vehicle.lastPitch;
                for(; e >= 180D; e -= 360D);
                for(; e < -180D; e += 360D);
                for(; d >= 180D; d -= 360D);
                for(; d < -180D; d += 360D);
                double d0 = e * 0.5D;
                double d1 = d * 0.5D;
                float f = 10F;
                if(d0 > (double)f)
                    d0 = f;
                if(d0 < (double)(-f))
                    d0 = -f;
                if(d1 > (double)f)
                    d1 = f;
                if(d1 < (double)(-f))
                    d1 = -f;
                e -= d0;
                d -= d1;
                yaw = (float)((double)yaw + d0);
                pitch = (float)((double)pitch + d1);
            }
        }
    }

    public void g_()
    {
        passenger.setPosition(locX, locY + n() + passenger.M(), locZ);
    }

    public double M()
    {
        return (double)height;
    }

    public double n()
    {
        return (double)width * 0.75D;
    }

    public void mount(Entity entity)
    {
        setPassengerOf(entity);
    }

    public org.bukkit.entity.Entity getBukkitEntity()
    {
        if(bukkitEntity == null)
            bukkitEntity = CraftEntity.getEntity(world.getServer(), this);
        return bukkitEntity;
    }

    public void setPassengerOf(Entity entity)
    {
        d = 0.0D;
        e = 0.0D;
        if(entity == null)
        {
            if(vehicle != null)
            {
                if((getBukkitEntity() instanceof LivingEntity) && (vehicle.getBukkitEntity() instanceof Vehicle))
                {
                    VehicleExitEvent event = new VehicleExitEvent((Vehicle)vehicle.getBukkitEntity(), (LivingEntity)getBukkitEntity());
                    world.getServer().getPluginManager().callEvent(event);
                }
                setPositionRotation(vehicle.locX, vehicle.boundingBox.b + (double)vehicle.width, vehicle.locZ, yaw, pitch);
                vehicle.passenger = null;
            }
            vehicle = null;
        } else
        if(vehicle == entity)
        {
            if((getBukkitEntity() instanceof LivingEntity) && (vehicle.getBukkitEntity() instanceof Vehicle))
            {
                VehicleExitEvent event = new VehicleExitEvent((Vehicle)vehicle.getBukkitEntity(), (LivingEntity)getBukkitEntity());
                world.getServer().getPluginManager().callEvent(event);
            }
            vehicle.passenger = null;
            vehicle = null;
            setPositionRotation(entity.locX, entity.boundingBox.b + (double)entity.width, entity.locZ, yaw, pitch);
        } else
        {
            if(vehicle != null)
                vehicle.passenger = null;
            if(entity.passenger != null)
                entity.passenger.vehicle = null;
            vehicle = entity;
            entity.passenger = this;
        }
    }

    public Vec3D ai()
    {
        return null;
    }

    public void T()
    {
    }

    public ItemStack[] getEquipment()
    {
        return null;
    }

    public boolean isSneaking()
    {
        return e(1);
    }

    public void setSneak(boolean flag)
    {
        a(1, flag);
    }

    public boolean isSprinting()
    {
        return e(3);
    }

    public void setSprinting(boolean flag)
    {
        a(3, flag);
    }

    public void h(boolean flag)
    {
        a(4, flag);
    }

    protected boolean e(int i)
    {
        return (datawatcher.getByte(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag)
    {
        byte b0 = datawatcher.getByte(0);
        if(flag)
            datawatcher.watch(0, Byte.valueOf((byte)(b0 | 1 << i)));
        else
            datawatcher.watch(0, Byte.valueOf((byte)(b0 & ~(1 << i))));
    }

    public void a(EntityWeatherStorm entityweatherstorm)
    {
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(entityweatherstorm.getBukkitEntity(), getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.LIGHTNING, 5);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        burn(event.getDamage());
        fireTicks++;
        if(fireTicks == 0)
            fireTicks = 300;
    }

    public void a(EntityLiving entityliving1)
    {
    }

    protected boolean g(double d0, double d1, double d2)
    {
        int i = MathHelper.floor(d0);
        int j = MathHelper.floor(d1);
        int k = MathHelper.floor(d2);
        double d3 = d0 - (double)i;
        double d4 = d1 - (double)j;
        double d5 = d2 - (double)k;
        if(world.e(i, j, k))
        {
            boolean flag = !world.e(i - 1, j, k);
            boolean flag1 = !world.e(i + 1, j, k);
            boolean flag2 = !world.e(i, j - 1, k);
            boolean flag3 = !world.e(i, j + 1, k);
            boolean flag4 = !world.e(i, j, k - 1);
            boolean flag5 = !world.e(i, j, k + 1);
            byte b0 = -1;
            double d6 = 9999D;
            if(flag && d3 < d6)
            {
                d6 = d3;
                b0 = 0;
            }
            if(flag1 && 1.0D - d3 < d6)
            {
                d6 = 1.0D - d3;
                b0 = 1;
            }
            if(flag2 && d4 < d6)
            {
                d6 = d4;
                b0 = 2;
            }
            if(flag3 && 1.0D - d4 < d6)
            {
                d6 = 1.0D - d4;
                b0 = 3;
            }
            if(flag4 && d5 < d6)
            {
                d6 = d5;
                b0 = 4;
            }
            if(flag5 && 1.0D - d5 < d6)
            {
                d6 = 1.0D - d5;
                b0 = 5;
            }
            float f = random.nextFloat() * 0.2F + 0.1F;
            if(b0 == 0)
                motX = -f;
            if(b0 == 1)
                motX = f;
            if(b0 == 2)
                motY = -f;
            if(b0 == 3)
                motY = f;
            if(b0 == 4)
                motZ = -f;
            if(b0 == 5)
                motZ = f;
        }
        return false;
    }

    public void q()
    {
        bv = true;
    }

    public String Y()
    {
        String s = EntityTypes.b(this);
        if(s == null)
            s = "generic";
        return StatisticCollector.a((new StringBuilder()).append("entity.").append(s).append(".name").toString());
    }

    private static int entityCount = 0;
    public int id;
    public double aX;
    public boolean aY;
    public Entity passenger;
    public Entity vehicle;
    public World world;
    public double lastX;
    public double lastY;
    public double lastZ;
    public double locX;
    public double locY;
    public double locZ;
    public double motX;
    public double motY;
    public double motZ;
    public float yaw;
    public float pitch;
    public float lastYaw;
    public float lastPitch;
    public final AxisAlignedBB boundingBox = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean onGround;
    public boolean positionChanged;
    public boolean bs;
    public boolean bt;
    public boolean velocityChanged;
    protected boolean bv;
    public boolean bw;
    public boolean dead;
    public float height;
    public float length;
    public float width;
    public float bB;
    public float bC;
    public float fallDistance;
    private int b;
    public double bE;
    public double bF;
    public double bG;
    public float bH;
    public float bI;
    public boolean bJ;
    public float bK;
    protected Random random;
    public int ticksLived;
    public int maxFireTicks;
    public int fireTicks;
    public int maxAirTicks;
    protected boolean bQ;
    public int noDamageTicks;
    public int airTicks;
    private boolean justCreated;
    protected boolean fireProof;
    protected DataWatcher datawatcher;
    private double d;
    private double e;
    public boolean bV;
    public int bW;
    public int bX;
    public int bY;
    public boolean bZ;
    public boolean ca;
    public UUID uniqueId;
    protected org.bukkit.entity.Entity bukkitEntity;

}
