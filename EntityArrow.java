// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityArrow.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, EntityHuman, MovingObjectPosition, EntityLiving, 
//            ItemStack, EntityItem, MathHelper, World, 
//            Block, Vec3D, AxisAlignedBB, DamageSource, 
//            NBTTagCompound, Item, InventoryPlayer

public class EntityArrow extends Entity
{

    public EntityArrow(World world)
    {
        super(world);
        e = -1;
        f = -1;
        g = -1;
        h = 0;
        i = 0;
        inGround = false;
        fromPlayer = false;
        shake = 0;
        l = 0;
        d = false;
        b(0.5F, 0.5F);
    }

    public EntityArrow(World world, double d0, double d1, double d2)
    {
        super(world);
        e = -1;
        f = -1;
        g = -1;
        h = 0;
        i = 0;
        inGround = false;
        fromPlayer = false;
        shake = 0;
        l = 0;
        d = false;
        b(0.5F, 0.5F);
        setPosition(d0, d1, d2);
        height = 0.0F;
    }

    public EntityArrow(World world, EntityLiving entityliving, float f)
    {
        super(world);
        e = -1;
        this.f = -1;
        g = -1;
        h = 0;
        i = 0;
        inGround = false;
        fromPlayer = false;
        shake = 0;
        l = 0;
        d = false;
        shooter = entityliving;
        fromPlayer = entityliving instanceof EntityHuman;
        b(0.5F, 0.5F);
        setPositionRotation(entityliving.locX, entityliving.locY + (double)entityliving.t(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
        locX -= MathHelper.cos((yaw / 180F) * 3.141593F) * 0.16F;
        locY -= 0.10000000149011612D;
        locZ -= MathHelper.sin((yaw / 180F) * 3.141593F) * 0.16F;
        setPosition(locX, locY, locZ);
        height = 0.0F;
        motX = -MathHelper.sin((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        motZ = MathHelper.cos((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
        motY = -MathHelper.sin((pitch / 180F) * 3.141593F);
        a(motX, motY, motZ, f * 1.5F, 1.0F);
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
        k = 0;
    }

    public void s_()
    {
        super.s_();
        if(lastPitch == 0.0F && lastYaw == 0.0F)
        {
            float f = MathHelper.a(motX * motX + motZ * motZ);
            lastYaw = yaw = (float)((Math.atan2(motX, motZ) * 180D) / 3.1415927410125732D);
            lastPitch = pitch = (float)((Math.atan2(motY, f) * 180D) / 3.1415927410125732D);
        }
        int i = world.getTypeId(e, this.f, g);
        if(i > 0)
        {
            Block.byId[i].a(world, e, this.f, g);
            AxisAlignedBB axisalignedbb = Block.byId[i].e(world, e, this.f, g);
            if(axisalignedbb != null && axisalignedbb.a(Vec3D.create(locX, locY, locZ)))
                inGround = true;
        }
        if(shake > 0)
            shake--;
        if(inGround)
        {
            i = world.getTypeId(e, this.f, g);
            int j = world.getData(e, this.f, g);
            if(i == h && j == this.i)
            {
                this.k++;
                if(this.k == 1200)
                    die();
            } else
            {
                inGround = false;
                motX *= random.nextFloat() * 0.2F;
                motY *= random.nextFloat() * 0.2F;
                motZ *= random.nextFloat() * 0.2F;
                this.k = 0;
                this.l = 0;
            }
        } else
        {
            this.l++;
            Vec3D vec3d = Vec3D.create(locX, locY, locZ);
            Vec3D vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
            MovingObjectPosition movingobjectposition = world.rayTrace(vec3d, vec3d1, false, true);
            vec3d = Vec3D.create(locX, locY, locZ);
            vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
            if(movingobjectposition != null)
                vec3d1 = Vec3D.create(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
            Entity entity = null;
            List list = world.b(this, boundingBox.a(motX, motY, motZ).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            float f1;
            for(int k = 0; k < list.size(); k++)
            {
                Entity entity1 = (Entity)list.get(k);
                if(!entity1.r_() || entity1 == shooter && this.l < 5)
                    continue;
                f1 = 0.3F;
                AxisAlignedBB axisalignedbb1 = entity1.boundingBox.b(f1, f1, f1);
                MovingObjectPosition movingobjectposition1 = axisalignedbb1.a(vec3d, vec3d1);
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
            float f2;
            if(movingobjectposition != null)
            {
                ProjectileHitEvent phe = new ProjectileHitEvent((Projectile)getBukkitEntity());
                world.getServer().getPluginManager().callEvent(phe);
                if(movingobjectposition.entity != null)
                {
                    f2 = MathHelper.a(motX * motX + motY * motY + motZ * motZ);
                    int l = (int)Math.ceil((double)f2 * 2D);
                    if(d)
                        l = (l * 3) / 2 + 1;
                    boolean stick;
                    if(entity instanceof EntityLiving)
                    {
                        Server server = world.getServer();
                        org.bukkit.entity.Entity damagee = movingobjectposition.entity.getBukkitEntity();
                        Projectile projectile = (Projectile)getBukkitEntity();
                        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(projectile, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE, l);
                        server.getPluginManager().callEvent(event);
                        shooter = projectile.getShooter() != null ? ((Entity) (((CraftLivingEntity)projectile.getShooter()).getHandle())) : null;
                        if(event.isCancelled())
                            stick = !projectile.doesBounce();
                        else
                            stick = movingobjectposition.entity.damageEntity(DamageSource.arrow(this, shooter), event.getDamage());
                    } else
                    {
                        stick = movingobjectposition.entity.damageEntity(DamageSource.arrow(this, shooter), l);
                    }
                    if(stick)
                    {
                        if(movingobjectposition.entity instanceof EntityLiving)
                            ((EntityLiving)movingobjectposition.entity).aD++;
                        world.makeSound(this, "random.drr", 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
                        die();
                    } else
                    {
                        motX *= -0.10000000149011612D;
                        motY *= -0.10000000149011612D;
                        motZ *= -0.10000000149011612D;
                        yaw += 180F;
                        lastYaw += 180F;
                        this.l = 0;
                    }
                } else
                {
                    e = movingobjectposition.b;
                    this.f = movingobjectposition.c;
                    g = movingobjectposition.d;
                    h = world.getTypeId(e, this.f, g);
                    this.i = world.getData(e, this.f, g);
                    motX = (float)(movingobjectposition.f.a - locX);
                    motY = (float)(movingobjectposition.f.b - locY);
                    motZ = (float)(movingobjectposition.f.c - locZ);
                    f2 = MathHelper.a(motX * motX + motY * motY + motZ * motZ);
                    locX -= (motX / (double)f2) * 0.05000000074505806D;
                    locY -= (motY / (double)f2) * 0.05000000074505806D;
                    locZ -= (motZ / (double)f2) * 0.05000000074505806D;
                    world.makeSound(this, "random.drr", 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
                    inGround = true;
                    shake = 7;
                    d = false;
                }
            }
            if(d)
            {
                for(int k = 0; k < 4; k++)
                    world.a("crit", locX + (motX * (double)k) / 4D, locY + (motY * (double)k) / 4D, locZ + (motZ * (double)k) / 4D, -motX, -motY + 0.20000000000000001D, -motZ);

            }
            locX += motX;
            locY += motY;
            locZ += motZ;
            f2 = MathHelper.a(motX * motX + motZ * motZ);
            yaw = (float)((Math.atan2(motX, motZ) * 180D) / 3.1415927410125732D);
            for(pitch = (float)((Math.atan2(motY, f2) * 180D) / 3.1415927410125732D); pitch - lastPitch < -180F; lastPitch -= 360F);
            for(; pitch - lastPitch >= 180F; lastPitch += 360F);
            for(; yaw - lastYaw < -180F; lastYaw -= 360F);
            for(; yaw - lastYaw >= 180F; lastYaw += 360F);
            pitch = lastPitch + (pitch - lastPitch) * 0.2F;
            yaw = lastYaw + (yaw - lastYaw) * 0.2F;
            float f3 = 0.99F;
            f1 = 0.05F;
            if(ao())
            {
                for(int i1 = 0; i1 < 4; i1++)
                {
                    float f4 = 0.25F;
                    world.a("bubble", locX - motX * (double)f4, locY - motY * (double)f4, locZ - motZ * (double)f4, motX, motY, motZ);
                }

                f3 = 0.8F;
            }
            motX *= f3;
            motY *= f3;
            motZ *= f3;
            motY -= f1;
            setPosition(locX, locY, locZ);
        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("xTile", (short)e);
        nbttagcompound.a("yTile", (short)f);
        nbttagcompound.a("zTile", (short)g);
        nbttagcompound.a("inTile", (byte)h);
        nbttagcompound.a("inData", (byte)i);
        nbttagcompound.a("shake", (byte)shake);
        nbttagcompound.a("inGround", (byte)(inGround ? 1 : 0));
        nbttagcompound.a("player", fromPlayer);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        e = nbttagcompound.d("xTile");
        f = nbttagcompound.d("yTile");
        g = nbttagcompound.d("zTile");
        h = nbttagcompound.c("inTile") & 0xff;
        i = nbttagcompound.c("inData") & 0xff;
        shake = nbttagcompound.c("shake") & 0xff;
        inGround = nbttagcompound.c("inGround") == 1;
        fromPlayer = nbttagcompound.m("player");
    }

    public void a_(EntityHuman entityhuman)
    {
        if(!world.isStatic)
        {
            ItemStack itemstack = new ItemStack(Item.ARROW, 1);
            if(inGround && fromPlayer && shake <= 0 && entityhuman.inventory.canHold(itemstack) > 0)
            {
                EntityItem item = new EntityItem(world, locX, locY, locZ, itemstack);
                PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), new CraftItem(world.getServer(), item), 0);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
            }
            if(inGround && fromPlayer && shake <= 0 && entityhuman.inventory.pickup(new ItemStack(Item.ARROW, 1)))
            {
                world.makeSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.receive(this, 1);
                die();
            }
        }
    }

    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean inGround;
    public boolean fromPlayer;
    public int shake;
    public Entity shooter;
    private int k;
    private int l;
    public boolean d;
}
