// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityEgg.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, MovingObjectPosition, EntityLiving, EntityPlayer, 
//            EntityChicken, EntityCow, EntityCreeper, EntityGhast, 
//            EntityGiantZombie, EntityPig, EntityPigZombie, EntitySheep, 
//            EntitySkeleton, EntitySpider, EntityZombie, EntitySquid, 
//            EntitySlime, EntityWolf, ItemStack, MathHelper, 
//            World, Vec3D, AxisAlignedBB, DamageSource, 
//            NBTTagCompound, EntityHuman, Item, InventoryPlayer

public class EntityEgg extends Entity
{

    public EntityEgg(World world)
    {
        super(world);
        b = -1;
        c = -1;
        d = -1;
        e = 0;
        f = false;
        a = 0;
        i = 0;
        b(0.25F, 0.25F);
    }

    protected void b()
    {
    }

    public EntityEgg(World world, EntityLiving entityliving)
    {
        super(world);
        b = -1;
        c = -1;
        d = -1;
        e = 0;
        this.f = false;
        a = 0;
        i = 0;
        thrower = entityliving;
        b(0.25F, 0.25F);
        setPositionRotation(entityliving.locX, entityliving.locY + (double)entityliving.t(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
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

    public EntityEgg(World world, double d0, double d1, double d2)
    {
        super(world);
        b = -1;
        c = -1;
        d = -1;
        e = 0;
        f = false;
        a = 0;
        i = 0;
        h = 0;
        b(0.25F, 0.25F);
        setPosition(d0, d1, d2);
        height = 0.0F;
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
        h = 0;
    }

    public void s_()
    {
        bE = locX;
        bF = locY;
        bG = locZ;
        super.s_();
        if(a > 0)
            a--;
        if(this.f)
        {
            int i = world.getTypeId(b, c, d);
            if(i == e)
            {
                h++;
                if(h == 1200)
                    die();
                return;
            }
            this.f = false;
            motX *= random.nextFloat() * 0.2F;
            motY *= random.nextFloat() * 0.2F;
            motZ *= random.nextFloat() * 0.2F;
            h = 0;
            this.i = 0;
        } else
        {
            this.i++;
        }
        Vec3D vec3d = Vec3D.create(locX, locY, locZ);
        Vec3D vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
        MovingObjectPosition movingobjectposition = world.a(vec3d, vec3d1);
        vec3d = Vec3D.create(locX, locY, locZ);
        vec3d1 = Vec3D.create(locX + motX, locY + motY, locZ + motZ);
        if(movingobjectposition != null)
            vec3d1 = Vec3D.create(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
        if(!world.isStatic)
        {
            Entity entity = null;
            List list = world.b(this, boundingBox.a(motX, motY, motZ).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            for(int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);
                if(!entity1.r_() || entity1 == thrower && this.i < 5)
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
        }
        if(movingobjectposition != null)
        {
            ProjectileHitEvent phe = new ProjectileHitEvent((Projectile)getBukkitEntity());
            world.getServer().getPluginManager().callEvent(phe);
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
                        stick = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, thrower), event.getDamage());
                } else
                {
                    stick = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, thrower), 0);
                }
                if(!stick);
            }
            boolean hatching = !world.isStatic && random.nextInt(8) == 0;
            int numHatching = random.nextInt(32) != 0 ? 1 : 4;
            if(!hatching)
                numHatching = 0;
            CreatureType hatchingType = CreatureType.CHICKEN;
            if(thrower instanceof EntityPlayer)
            {
                Player player = thrower != null ? (Player)thrower.getBukkitEntity() : null;
                PlayerEggThrowEvent event = new PlayerEggThrowEvent(player, (Egg)getBukkitEntity(), hatching, (byte)numHatching, hatchingType);
                world.getServer().getPluginManager().callEvent(event);
                hatching = event.isHatching();
                numHatching = event.getNumHatches();
                hatchingType = event.getHatchType();
            }
            if(hatching)
            {
                for(int k = 0; k < numHatching; k++)
                {
                    Entity entity = null;
                    static class _cls1
                    {

                        static final int $SwitchMap$org$bukkit$entity$CreatureType[];

                        static 
                        {
                            $SwitchMap$org$bukkit$entity$CreatureType = new int[CreatureType.values().length];
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.CHICKEN.ordinal()] = 1;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.COW.ordinal()] = 2;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.CREEPER.ordinal()] = 3;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.GHAST.ordinal()] = 4;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.GIANT.ordinal()] = 5;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.PIG.ordinal()] = 6;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.PIG_ZOMBIE.ordinal()] = 7;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.SHEEP.ordinal()] = 8;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.SKELETON.ordinal()] = 9;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.SPIDER.ordinal()] = 10;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.ZOMBIE.ordinal()] = 11;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.SQUID.ordinal()] = 12;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.SLIME.ordinal()] = 13;
                            }
                            catch(NoSuchFieldError ex) { }
                            try
                            {
                                $SwitchMap$org$bukkit$entity$CreatureType[CreatureType.WOLF.ordinal()] = 14;
                            }
                            catch(NoSuchFieldError ex) { }
                        }
                    }

                    switch(_cls1..SwitchMap.org.bukkit.entity.CreatureType[hatchingType.ordinal()])
                    {
                    case 1: // '\001'
                        entity = new EntityChicken(world);
                        break;

                    case 2: // '\002'
                        entity = new EntityCow(world);
                        break;

                    case 3: // '\003'
                        entity = new EntityCreeper(world);
                        break;

                    case 4: // '\004'
                        entity = new EntityGhast(world);
                        break;

                    case 5: // '\005'
                        entity = new EntityGiantZombie(world);
                        break;

                    case 6: // '\006'
                        entity = new EntityPig(world);
                        break;

                    case 7: // '\007'
                        entity = new EntityPigZombie(world);
                        break;

                    case 8: // '\b'
                        entity = new EntitySheep(world);
                        break;

                    case 9: // '\t'
                        entity = new EntitySkeleton(world);
                        break;

                    case 10: // '\n'
                        entity = new EntitySpider(world);
                        break;

                    case 11: // '\013'
                        entity = new EntityZombie(world);
                        break;

                    case 12: // '\f'
                        entity = new EntitySquid(world);
                        break;

                    case 13: // '\r'
                        entity = new EntitySlime(world);
                        break;

                    case 14: // '\016'
                        entity = new EntityWolf(world);
                        break;

                    default:
                        entity = new EntityChicken(world);
                        break;
                    }
                    entity.setPositionRotation(locX, locY, locZ, yaw, 0.0F);
                    world.addEntity(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.EGG);
                }

            }
            for(int l = 0; l < 8; l++)
                world.a("snowballpoof", locX, locY, locZ, 0.0D, 0.0D, 0.0D);

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
        float f2 = 0.99F;
        float f3 = 0.03F;
        if(ao())
        {
            for(int i1 = 0; i1 < 4; i1++)
            {
                float f4 = 0.25F;
                world.a("bubble", locX - motX * (double)f4, locY - motY * (double)f4, locZ - motZ * (double)f4, motX, motY, motZ);
            }

            f2 = 0.8F;
        }
        motX *= f2;
        motY *= f2;
        motZ *= f2;
        motY -= f3;
        setPosition(locX, locY, locZ);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("xTile", (short)b);
        nbttagcompound.a("yTile", (short)c);
        nbttagcompound.a("zTile", (short)d);
        nbttagcompound.a("inTile", (byte)e);
        nbttagcompound.a("shake", (byte)a);
        nbttagcompound.a("inGround", (byte)(f ? 1 : 0));
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        b = nbttagcompound.d("xTile");
        c = nbttagcompound.d("yTile");
        d = nbttagcompound.d("zTile");
        e = nbttagcompound.c("inTile") & 0xff;
        a = nbttagcompound.c("shake") & 0xff;
        f = nbttagcompound.c("inGround") == 1;
    }

    public void a_(EntityHuman entityhuman)
    {
        if(f && thrower == entityhuman && a <= 0 && entityhuman.inventory.pickup(new ItemStack(Item.ARROW, 1)))
        {
            world.makeSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityhuman.receive(this, 1);
            die();
        }
    }

    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    public int a;
    public EntityLiving thrower;
    private int h;
    private int i;
}
