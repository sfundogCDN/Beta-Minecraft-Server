// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityWolf.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityAnimal, EntitySheep, Entity, EntityHuman, 
//            ItemFood, EntityArrow, EntityLiving, ItemStack, 
//            PathEntity, DataWatcher, NBTTagCompound, World, 
//            AxisAlignedBB, InventoryPlayer, Item, MathHelper, 
//            DamageSource

public class EntityWolf extends EntityAnimal
{

    public EntityWolf(World world)
    {
        super(world);
        a = false;
        texture = "/mob/wolf.png";
        b(0.8F, 0.8F);
        aU = 1.1F;
        health = 8;
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, Byte.valueOf((byte)0));
        datawatcher.a(17, "");
        datawatcher.a(18, new Integer(health));
    }

    protected boolean e_()
    {
        return false;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Angry", isAngry());
        nbttagcompound.a("Sitting", isSitting());
        if(getOwnerName() == null)
            nbttagcompound.setString("Owner", "");
        else
            nbttagcompound.setString("Owner", getOwnerName());
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setAngry(nbttagcompound.m("Angry"));
        setSitting(nbttagcompound.m("Sitting"));
        String s = nbttagcompound.getString("Owner");
        if(s.length() > 0)
        {
            setOwnerName(s);
            setTamed(true);
        }
    }

    protected boolean d_()
    {
        return !isTamed();
    }

    protected String h()
    {
        return isAngry() ? "mob.wolf.growl" : random.nextInt(3) != 0 ? "mob.wolf.bark" : !isTamed() || datawatcher.getInt(18) >= 10 ? "mob.wolf.panting" : "mob.wolf.whine";
    }

    protected String i()
    {
        return "mob.wolf.hurt";
    }

    protected String j()
    {
        return "mob.wolf.death";
    }

    protected float l()
    {
        return 0.4F;
    }

    protected int k()
    {
        return -1;
    }

    protected void c_()
    {
        super.c_();
        if(!e && !B() && isTamed() && vehicle == null)
        {
            EntityHuman entityhuman = world.a(getOwnerName());
            if(entityhuman != null)
            {
                float f = entityhuman.g(this);
                if(f > 5F)
                    c(entityhuman, f);
            } else
            if(!ao())
                setSitting(true);
        } else
        if(target == null && !B() && !isTamed() && world.random.nextInt(100) == 0)
        {
            List list = world.a(net/minecraft/server/EntitySheep, AxisAlignedBB.b(locX, locY, locZ, locX + 1.0D, locY + 1.0D, locZ + 1.0D).b(16D, 4D, 16D));
            if(!list.isEmpty())
            {
                Entity entity = (Entity)list.get(world.random.nextInt(list.size()));
                org.bukkit.entity.Entity bukkitTarget = entity != null ? entity.getBukkitEntity() : null;
                EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.RANDOM_TARGET);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled() || event.getTarget() != null)
                    setTarget(entity);
            }
        }
        if(ao())
            setSitting(false);
        if(!world.isStatic)
            datawatcher.watch(18, Integer.valueOf(health));
    }

    public void s()
    {
        super.s();
        a = false;
        if(ae() && !B() && !isAngry())
        {
            Entity entity = af();
            if(entity instanceof EntityHuman)
            {
                EntityHuman entityhuman = (EntityHuman)entity;
                ItemStack itemstack = entityhuman.inventory.getItemInHand();
                if(itemstack != null)
                    if(!isTamed() && itemstack.id == Item.BONE.id)
                        a = true;
                    else
                    if(isTamed() && (Item.byId[itemstack.id] instanceof ItemFood))
                        a = ((ItemFood)Item.byId[itemstack.id]).m();
            }
        }
        if(!ai && g && !h && !B() && onGround)
        {
            h = true;
            i = 0.0F;
            j = 0.0F;
            world.a(this, (byte)8);
        }
    }

    public void s_()
    {
        super.s_();
        c = b;
        if(a)
            b += (1.0F - b) * 0.4F;
        else
            b += (0.0F - b) * 0.4F;
        if(a)
            aV = 10;
        if(an())
        {
            g = true;
            h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else
        if((g || h) && h)
        {
            if(this.i == 0.0F)
                world.makeSound(this, "mob.wolf.shake", l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            this.j = this.i;
            this.i += 0.05F;
            if(this.j >= 2.0F)
            {
                g = false;
                h = false;
                this.j = 0.0F;
                this.i = 0.0F;
            }
            if(this.i > 0.4F)
            {
                float f = (float)boundingBox.b;
                int i = (int)(MathHelper.sin((this.i - 0.4F) * 3.141593F) * 7F);
                for(int j = 0; j < i; j++)
                {
                    float f1 = (random.nextFloat() * 2.0F - 1.0F) * length * 0.5F;
                    float f2 = (random.nextFloat() * 2.0F - 1.0F) * length * 0.5F;
                    world.a("splash", locX + (double)f1, f + 0.8F, locZ + (double)f2, motX, motY, motZ);
                }

            }
        }
    }

    public float t()
    {
        return width * 0.8F;
    }

    protected int u()
    {
        return isSitting() ? 20 : super.u();
    }

    private void c(Entity entity, float f)
    {
        PathEntity pathentity = world.findPath(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor(entity.locX) - 2;
            int j = MathHelper.floor(entity.locZ) - 2;
            int k = MathHelper.floor(entity.boundingBox.b);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && world.e(i + l, k - 1, j + i1) && !world.e(i + l, k, j + i1) && !world.e(i + l, k + 1, j + i1))
                    {
                        setPositionRotation((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, yaw, pitch);
                        return;
                    }

            }

        } else
        {
            setPathEntity(pathentity);
        }
    }

    protected boolean v()
    {
        return isSitting() || h;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        setSitting(false);
        if(entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow))
            i = (i + 1) / 2;
        if(!super.damageEntity(damagesource, i))
            return false;
        if(!isTamed() && !isAngry())
        {
            if(entity instanceof EntityHuman)
            {
                org.bukkit.entity.Entity bukkitTarget = entity != null ? entity.getBukkitEntity() : null;
                EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    if(event.getTarget() == null)
                    {
                        target = null;
                    } else
                    {
                        setAngry(true);
                        target = ((CraftEntity)event.getTarget()).getHandle();
                    }
            }
            if((entity instanceof EntityArrow) && ((EntityArrow)entity).shooter != null)
                entity = ((EntityArrow)entity).shooter;
            if(entity instanceof EntityLiving)
            {
                List list = world.a(net/minecraft/server/EntityWolf, AxisAlignedBB.b(locX, locY, locZ, locX + 1.0D, locY + 1.0D, locZ + 1.0D).b(16D, 4D, 16D));
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Entity entity1 = (Entity)iterator.next();
                    EntityWolf entitywolf = (EntityWolf)entity1;
                    if(!entitywolf.isTamed() && entitywolf.target == null)
                    {
                        org.bukkit.entity.Entity bukkitTarget = entity != null ? entity.getBukkitEntity() : null;
                        EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY);
                        world.getServer().getPluginManager().callEvent(event);
                        if(!event.isCancelled())
                            if(event.getTarget() == null)
                            {
                                target = null;
                            } else
                            {
                                entitywolf.target = entity;
                                if(entity instanceof EntityHuman)
                                    entitywolf.setAngry(true);
                            }
                    }
                } while(true);
            }
        } else
        if(entity != this && entity != null)
        {
            if(isTamed() && (entity instanceof EntityHuman) && ((EntityHuman)entity).name.equalsIgnoreCase(getOwnerName()))
                return true;
            target = entity;
        }
        return true;
    }

    protected Entity findTarget()
    {
        return isAngry() ? world.findNearbyPlayer(this, 16D) : null;
    }

    protected void a(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d0 = entity.locX - locX;
                double d1 = entity.locZ - locZ;
                float f1 = MathHelper.a(d0 * d0 + d1 * d1);
                motX = (d0 / (double)f1) * 0.5D * 0.80000001192092896D + motX * 0.20000000298023224D;
                motZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + motZ * 0.20000000298023224D;
                motY = 0.40000000596046448D;
            }
        } else
        if((double)f < 1.5D && entity.boundingBox.e > boundingBox.b && entity.boundingBox.b < boundingBox.e)
        {
            attackTicks = 20;
            byte b0 = 2;
            if(isTamed())
                b0 = 4;
            org.bukkit.entity.Entity damager = getBukkitEntity();
            org.bukkit.entity.Entity damagee = entity != null ? entity.getBukkitEntity() : null;
            EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK, b0);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
            entity.damageEntity(DamageSource.mobAttack(this), b0);
        }
    }

    public boolean b(EntityHuman entityhuman)
    {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();
        if(!isTamed())
        {
            if(itemstack != null && itemstack.id == Item.BONE.id && !isAngry())
            {
                itemstack.count--;
                if(itemstack.count <= 0)
                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
                if(!world.isStatic)
                    if(random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled())
                    {
                        setTamed(true);
                        setPathEntity((PathEntity)null);
                        setSitting(true);
                        health = 20;
                        setOwnerName(entityhuman.name);
                        b(true);
                        world.a(this, (byte)7);
                    } else
                    {
                        b(false);
                        world.a(this, (byte)6);
                    }
                return true;
            }
        } else
        {
            if(itemstack != null && (Item.byId[itemstack.id] instanceof ItemFood))
            {
                ItemFood itemfood = (ItemFood)Item.byId[itemstack.id];
                if(itemfood.m() && datawatcher.getInt(18) < 20)
                {
                    itemstack.count--;
                    c(itemfood.k(), org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.EATING);
                    if(itemstack.count <= 0)
                        entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
                    return true;
                }
            }
            if(entityhuman.name.equalsIgnoreCase(getOwnerName()))
            {
                if(!world.isStatic)
                {
                    setSitting(!isSitting());
                    aS = false;
                    setPathEntity((PathEntity)null);
                }
                return true;
            }
        }
        return false;
    }

    void b(boolean flag)
    {
        String s = "heart";
        if(!flag)
            s = "smoke";
        for(int i = 0; i < 7; i++)
        {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            world.a(s, (locX + (double)(random.nextFloat() * length * 2.0F)) - (double)length, locY + 0.5D + (double)(random.nextFloat() * width), (locZ + (double)(random.nextFloat() * length * 2.0F)) - (double)length, d0, d1, d2);
        }

    }

    public int m()
    {
        return 8;
    }

    public String getOwnerName()
    {
        return datawatcher.getString(17);
    }

    public void setOwnerName(String s)
    {
        datawatcher.watch(17, s);
    }

    public boolean isSitting()
    {
        return (datawatcher.getByte(16) & 1) != 0;
    }

    public void setSitting(boolean flag)
    {
        byte b0 = datawatcher.getByte(16);
        if(flag)
            datawatcher.watch(16, Byte.valueOf((byte)(b0 | 1)));
        else
            datawatcher.watch(16, Byte.valueOf((byte)(b0 & -2)));
    }

    public boolean isAngry()
    {
        return (datawatcher.getByte(16) & 2) != 0;
    }

    public void setAngry(boolean flag)
    {
        byte b0 = datawatcher.getByte(16);
        if(flag)
            datawatcher.watch(16, Byte.valueOf((byte)(b0 | 2)));
        else
            datawatcher.watch(16, Byte.valueOf((byte)(b0 & -3)));
    }

    public boolean isTamed()
    {
        return (datawatcher.getByte(16) & 4) != 0;
    }

    public void setTamed(boolean flag)
    {
        byte b0 = datawatcher.getByte(16);
        if(flag)
            datawatcher.watch(16, Byte.valueOf((byte)(b0 | 4)));
        else
            datawatcher.watch(16, Byte.valueOf((byte)(b0 & -5)));
    }

    private boolean a;
    private float b;
    private float c;
    private boolean g;
    private boolean h;
    private float i;
    private float j;
}
