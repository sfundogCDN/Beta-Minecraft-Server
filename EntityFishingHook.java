// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityFishingHook.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, MovingObjectPosition, EntityLiving, EntityItem, 
//            ItemStack, EntityHuman, MathHelper, World, 
//            Item, AxisAlignedBB, Vec3D, DamageSource, 
//            Material, NBTTagCompound, StatisticList

public class EntityFishingHook extends Entity
{

    public EntityFishingHook(World world)
    {
        super(world);
        d = -1;
        e = -1;
        f = -1;
        g = 0;
        h = false;
        a = 0;
        j = 0;
        k = 0;
        c = null;
        b(0.25F, 0.25F);
        bZ = true;
    }

    public EntityFishingHook(World world, EntityHuman entityhuman)
    {
        super(world);
        d = -1;
        e = -1;
        this.f = -1;
        g = 0;
        h = false;
        a = 0;
        j = 0;
        k = 0;
        c = null;
        bZ = true;
        owner = entityhuman;
        owner.hookedFish = this;
        b(0.25F, 0.25F);
        setPositionRotation(entityhuman.locX, (entityhuman.locY + 1.6200000000000001D) - (double)entityhuman.height, entityhuman.locZ, entityhuman.yaw, entityhuman.pitch);
        locX -= MathHelper.cos((yaw / 180F) * 3.141593F) * 0.16F;
        locY -= 0.10000000149011612D;
        locZ -= MathHelper.sin((yaw / 180F) * 3.141593F) * 0.16F;
        setPosition(locX, locY, locZ);
        height = 0.0F;
        float f = 0.4F;
        motX = -MathHelper.sin((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F) * f;
        motZ = MathHelper.cos((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F) * f;
        motY = -MathHelper.sin((pitch / 180F) * 3.141593F) * f;
        a(motX, motY, motZ, 1.5F, 1.0F);
    }

    protected void b()
    {
    }

    public void a(double d0, double d1, double d2, float f, 
            float f1)
    {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
        d0 /= f2;
        d1 /= f2;
        d2 /= f2;
        d0 += random.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += random.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += random.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d0 *= f;
        d1 *= f;
        d2 *= f;
        motX = d0;
        motY = d1;
        motZ = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);
        lastYaw = yaw = (float)((Math.atan2(d0, d2) * 180D) / 3.1415927410125732D);
        lastPitch = pitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        i = 0;
    }

    public void s_()
    {
        super.s_();
        if(this.l > 0)
        {
            double d0 = locX + (m - locX) / (double)this.l;
            double d1 = locY + (n - locY) / (double)this.l;
            double d2 = locZ + (o - locZ) / (double)this.l;
            double d3;
            for(d3 = p - (double)yaw; d3 < -180D; d3 += 360D);
            for(; d3 >= 180D; d3 -= 360D);
            yaw = (float)((double)yaw + d3 / (double)this.l);
            pitch = (float)((double)pitch + (q - (double)pitch) / (double)this.l);
            this.l--;
            setPosition(d0, d1, d2);
            c(yaw, pitch);
        } else
        {
            if(!world.isStatic)
            {
                ItemStack itemstack = owner.K();
                if(owner.dead || !owner.ac() || itemstack == null || itemstack.getItem() != Item.FISHING_ROD || h(owner) > 1024D)
                {
                    die();
                    owner.hookedFish = null;
                    return;
                }
                if(c != null)
                {
                    if(!c.dead)
                    {
                        locX = c.locX;
                        locY = c.boundingBox.b + (double)c.width * 0.80000000000000004D;
                        locZ = c.locZ;
                        return;
                    }
                    c = null;
                }
            }
            if(a > 0)
                a--;
            if(h)
            {
                int i = world.getTypeId(d, e, this.f);
                if(i == g)
                {
                    this.i++;
                    if(this.i == 1200)
                        die();
                    return;
                }
                h = false;
                motX *= random.nextFloat() * 0.2F;
                motY *= random.nextFloat() * 0.2F;
                motZ *= random.nextFloat() * 0.2F;
                this.i = 0;
                this.j = 0;
            } else
            {
                this.j++;
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
            double d4 = 0.0D;
            for(int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);
                if(!entity1.r_() || entity1 == owner && this.j < 5)
                    continue;
                float f = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.b(f, f, f);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
                if(movingobjectposition1 == null)
                    continue;
                double d5 = vec3d.b(movingobjectposition1.f);
                if(d5 < d4 || d4 == 0.0D)
                {
                    entity = entity1;
                    d4 = d5;
                }
            }

            if(entity != null)
                movingobjectposition = new MovingObjectPosition(entity);
            if(movingobjectposition != null)
                if(movingobjectposition.entity != null)
                {
                    boolean stick;
                    if(movingobjectposition.entity instanceof EntityLiving)
                    {
                        org.bukkit.entity.Entity damagee = movingobjectposition.entity.getBukkitEntity();
                        Projectile projectile = (Projectile)getBukkitEntity();
                        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(projectile, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE, 0);
                        world.getServer().getPluginManager().callEvent(event);
                        if(event.isCancelled())
                            stick = !projectile.doesBounce();
                        else
                            stick = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, owner), event.getDamage());
                    } else
                    {
                        stick = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, owner), 0);
                    }
                    if(!stick)
                        c = movingobjectposition.entity;
                } else
                {
                    h = true;
                }
            if(!h)
            {
                move(motX, motY, motZ);
                float f1 = MathHelper.a(motX * motX + motZ * motZ);
                yaw = (float)((Math.atan2(motX, motZ) * 180D) / 3.1415927410125732D);
                for(pitch = (float)((Math.atan2(motY, f1) * 180D) / 3.1415927410125732D); pitch - lastPitch < -180F; lastPitch -= 360F);
                for(; pitch - lastPitch >= 180F; lastPitch += 360F);
                for(; yaw - lastYaw < -180F; lastYaw -= 360F);
                for(; yaw - lastYaw >= 180F; lastYaw += 360F);
                pitch = lastPitch + (pitch - lastPitch) * 0.2F;
                yaw = lastYaw + (yaw - lastYaw) * 0.2F;
                float f2 = 0.92F;
                if(onGround || positionChanged)
                    f2 = 0.5F;
                byte b0 = 5;
                double d6 = 0.0D;
                for(int k = 0; k < b0; k++)
                {
                    double d7 = ((boundingBox.b + ((boundingBox.e - boundingBox.b) * (double)(k + 0)) / (double)b0) - 0.125D) + 0.125D;
                    double d8 = ((boundingBox.b + ((boundingBox.e - boundingBox.b) * (double)(k + 1)) / (double)b0) - 0.125D) + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.b(boundingBox.a, d7, boundingBox.c, boundingBox.d, d8, boundingBox.f);
                    if(world.b(axisalignedbb1, Material.WATER))
                        d6 += 1.0D / (double)b0;
                }

                if(d6 > 0.0D)
                    if(this.k > 0)
                    {
                        this.k--;
                    } else
                    {
                        short short1 = 500;
                        if(world.s(MathHelper.floor(locX), MathHelper.floor(locY) + 1, MathHelper.floor(locZ)))
                            short1 = 300;
                        if(random.nextInt(short1) == 0)
                        {
                            this.k = random.nextInt(30) + 10;
                            motY -= 0.20000000298023224D;
                            world.makeSound(this, "random.splash", 0.25F, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.4F);
                            float f3 = MathHelper.floor(boundingBox.b);
                            for(int l = 0; (float)l < 1.0F + length * 20F; l++)
                            {
                                float f5 = (random.nextFloat() * 2.0F - 1.0F) * length;
                                float f4 = (random.nextFloat() * 2.0F - 1.0F) * length;
                                world.a("bubble", locX + (double)f5, f3 + 1.0F, locZ + (double)f4, motX, motY - (double)(random.nextFloat() * 0.2F), motZ);
                            }

                            for(int l = 0; (float)l < 1.0F + length * 20F; l++)
                            {
                                float f5 = (random.nextFloat() * 2.0F - 1.0F) * length;
                                float f4 = (random.nextFloat() * 2.0F - 1.0F) * length;
                                world.a("splash", locX + (double)f5, f3 + 1.0F, locZ + (double)f4, motX, motY, motZ);
                            }

                        }
                    }
                if(this.k > 0)
                    motY -= (double)(random.nextFloat() * random.nextFloat() * random.nextFloat()) * 0.20000000000000001D;
                double d5 = d6 * 2D - 1.0D;
                motY += 0.039999999105930328D * d5;
                if(d6 > 0.0D)
                {
                    f2 = (float)((double)f2 * 0.90000000000000002D);
                    motY *= 0.80000000000000004D;
                }
                motX *= f2;
                motY *= f2;
                motZ *= f2;
                setPosition(locX, locY, locZ);
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("xTile", (short)d);
        nbttagcompound.a("yTile", (short)e);
        nbttagcompound.a("zTile", (short)f);
        nbttagcompound.a("inTile", (byte)g);
        nbttagcompound.a("shake", (byte)a);
        nbttagcompound.a("inGround", (byte)(h ? 1 : 0));
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        d = nbttagcompound.d("xTile");
        e = nbttagcompound.d("yTile");
        f = nbttagcompound.d("zTile");
        g = nbttagcompound.c("inTile") & 0xff;
        a = nbttagcompound.c("shake") & 0xff;
        h = nbttagcompound.c("inGround") == 1;
    }

    public int i()
    {
        byte b0 = 0;
        if(c != null)
        {
            PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)owner.getBukkitEntity(), c.getBukkitEntity(), org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_ENTITY);
            world.getServer().getPluginManager().callEvent(playerFishEvent);
            if(playerFishEvent.isCancelled())
            {
                die();
                owner.hookedFish = null;
                return 0;
            }
            double d0 = owner.locX - locX;
            double d1 = owner.locY - locY;
            double d2 = owner.locZ - locZ;
            double d3 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
            double d4 = 0.10000000000000001D;
            c.motX += d0 * d4;
            c.motY += d1 * d4 + (double)MathHelper.a(d3) * 0.080000000000000002D;
            c.motZ += d2 * d4;
            b0 = 3;
        } else
        if(k > 0)
        {
            EntityItem entityitem = new EntityItem(world, locX, locY, locZ, new ItemStack(Item.RAW_FISH));
            PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)owner.getBukkitEntity(), entityitem.getBukkitEntity(), org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH);
            world.getServer().getPluginManager().callEvent(playerFishEvent);
            if(playerFishEvent.isCancelled())
            {
                die();
                owner.hookedFish = null;
                return 0;
            }
            double d5 = owner.locX - locX;
            double d6 = owner.locY - locY;
            double d7 = owner.locZ - locZ;
            double d8 = MathHelper.a(d5 * d5 + d6 * d6 + d7 * d7);
            double d9 = 0.10000000000000001D;
            entityitem.motX = d5 * d9;
            entityitem.motY = d6 * d9 + (double)MathHelper.a(d8) * 0.080000000000000002D;
            entityitem.motZ = d7 * d9;
            world.addEntity(entityitem);
            owner.a(StatisticList.B, 1);
            b0 = 1;
        }
        if(h)
        {
            PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)owner.getBukkitEntity(), null, org.bukkit.event.player.PlayerFishEvent.State.IN_GROUND);
            world.getServer().getPluginManager().callEvent(playerFishEvent);
            if(playerFishEvent.isCancelled())
            {
                die();
                owner.hookedFish = null;
                return 0;
            }
            b0 = 2;
        }
        if(b0 == 0)
        {
            PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)owner.getBukkitEntity(), null, org.bukkit.event.player.PlayerFishEvent.State.FAILED_ATTEMPT);
            world.getServer().getPluginManager().callEvent(playerFishEvent);
        }
        die();
        owner.hookedFish = null;
        return b0;
    }

    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    public int a;
    public EntityHuman owner;
    private int i;
    private int j;
    private int k;
    public Entity c;
    private int l;
    private double m;
    private double n;
    private double o;
    private double p;
    private double q;
}
