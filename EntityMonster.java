// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityMonster.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityCreature, EntityLiving, EntityHuman, IMonster, 
//            World, DamageSource, Entity, AxisAlignedBB, 
//            MathHelper, EnumSkyBlock, NBTTagCompound

public abstract class EntityMonster extends EntityCreature
    implements IMonster
{

    public EntityMonster(World world)
    {
        super(world);
        damage = 2;
        health = 20;
        ax = 5;
    }

    public void s()
    {
        float f = a_(1.0F);
        if(f > 0.5F)
            aO += 2;
        super.s();
    }

    public void s_()
    {
        super.s_();
        if(!world.isStatic && world.difficulty == 0)
            die();
    }

    protected Entity findTarget()
    {
        EntityHuman entityhuman = world.findNearbyPlayer(this, 16D);
        return entityhuman == null || !f(entityhuman) ? null : entityhuman;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(super.damageEntity(damagesource, i))
        {
            Entity entity = damagesource.getEntity();
            if(passenger != entity && vehicle != entity)
            {
                if(entity != this)
                {
                    org.bukkit.entity.Entity bukkitTarget = entity != null ? entity.getBukkitEntity() : null;
                    EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY);
                    world.getServer().getPluginManager().callEvent(event);
                    if(!event.isCancelled())
                        if(event.getTarget() == null)
                            target = null;
                        else
                            target = ((CraftEntity)event.getTarget()).getHandle();
                }
                return true;
            } else
            {
                return true;
            }
        } else
        {
            return false;
        }
    }

    protected boolean c(Entity entity)
    {
        if((entity instanceof EntityLiving) && !(entity instanceof EntityHuman))
        {
            org.bukkit.entity.Entity damagee = entity != null ? entity.getBukkitEntity() : null;
            EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(getBukkitEntity(), damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                return entity.damageEntity(DamageSource.mobAttack(this), event.getDamage());
            else
                return false;
        } else
        {
            return entity.damageEntity(DamageSource.mobAttack(this), damage);
        }
    }

    protected void a(Entity entity, float f)
    {
        if(attackTicks <= 0 && f < 2.0F && entity.boundingBox.e > boundingBox.b && entity.boundingBox.b < boundingBox.e)
        {
            attackTicks = 20;
            c(entity);
        }
    }

    protected float a(int i, int j, int k)
    {
        return 0.5F - world.m(i, j, k);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    public boolean d()
    {
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(boundingBox.b);
        int k = MathHelper.floor(locZ);
        if(world.a(EnumSkyBlock.SKY, i, j, k) > random.nextInt(32))
            return false;
        int l = world.getLightLevel(i, j, k);
        if(world.t())
        {
            int i1 = world.k;
            world.k = 10;
            l = world.getLightLevel(i, j, k);
            world.k = i1;
        }
        return l <= random.nextInt(8) && super.d();
    }

    protected int damage;
}
