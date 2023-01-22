// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySkeleton.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, EntityArrow, EntityHuman, ItemStack, 
//            DamageSource, AchievementList, World, MathHelper, 
//            Entity, Item, NBTTagCompound

public class EntitySkeleton extends EntityMonster
{

    public EntitySkeleton(World world)
    {
        super(world);
        texture = "/mob/skeleton.png";
    }

    protected String h()
    {
        return "mob.skeleton";
    }

    protected String i()
    {
        return "mob.skeletonhurt";
    }

    protected String j()
    {
        return "mob.skeletonhurt";
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        return super.damageEntity(damagesource, i);
    }

    public void die(DamageSource damagesource)
    {
        super.die(damagesource);
        if((damagesource.e() instanceof EntityArrow) && (damagesource.getEntity() instanceof EntityHuman))
        {
            EntityHuman entityhuman = (EntityHuman)damagesource.getEntity();
            double d0 = entityhuman.locX - locX;
            double d1 = entityhuman.locZ - locZ;
            if(d0 * d0 + d1 * d1 >= 2500D)
                entityhuman.a(AchievementList.v);
        }
    }

    public void s()
    {
        if(world.d() && !world.isStatic)
        {
            float f = a_(1.0F);
            if(f > 0.5F && world.isChunkLoaded(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ)) && random.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity());
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    fireTicks = 300;
            }
        }
        super.s();
    }

    protected void a(Entity entity, float f)
    {
        if(f < 10F)
        {
            double d0 = entity.locX - locX;
            double d1 = entity.locZ - locZ;
            if(attackTicks == 0)
            {
                EntityArrow entityarrow = new EntityArrow(world, this, 1.0F);
                double d2 = (entity.locY + (double)entity.t()) - 0.69999998807907104D - entityarrow.locY;
                float f1 = MathHelper.a(d0 * d0 + d1 * d1) * 0.2F;
                world.makeSound(this, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
                world.addEntity(entityarrow);
                entityarrow.a(d0, d2 + (double)f1, d1, 1.6F, 12F);
                attackTicks = 60;
            }
            yaw = (float)((Math.atan2(d1, d0) * 180D) / 3.1415927410125732D) - 90F;
            e = true;
        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    protected int k()
    {
        return Item.ARROW.id;
    }

    protected void a(boolean flag)
    {
        List loot = new ArrayList();
        int count = random.nextInt(3);
        if(count > 0)
            loot.add(new ItemStack(Material.ARROW, count));
        count = random.nextInt(3);
        if(count > 0)
            loot.add(new ItemStack(Material.BONE, count));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    private static final net.minecraft.server.ItemStack a;

    static 
    {
        a = new net.minecraft.server.ItemStack(Item.BOW, 1);
    }
}
