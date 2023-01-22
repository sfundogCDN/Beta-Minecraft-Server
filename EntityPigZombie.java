// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityPigZombie.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityZombie, EntityHuman, Entity, ItemStack, 
//            World, NBTTagCompound, DamageSource, AxisAlignedBB, 
//            Item

public class EntityPigZombie extends EntityZombie
{

    public EntityPigZombie(World world)
    {
        super(world);
        angerLevel = 0;
        soundDelay = 0;
        texture = "/mob/pigzombie.png";
        aU = 0.5F;
        damage = 5;
        fireProof = true;
    }

    public void s_()
    {
        aU = target == null ? 0.5F : 0.95F;
        if(soundDelay > 0 && --soundDelay == 0)
            world.makeSound(this, "mob.zombiepig.zpigangry", l() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        super.s_();
    }

    public boolean d()
    {
        return world.difficulty > 0 && world.containsEntity(boundingBox) && world.getEntities(this, boundingBox).size() == 0 && !world.c(boundingBox);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Anger", (short)angerLevel);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        angerLevel = nbttagcompound.d("Anger");
    }

    protected Entity findTarget()
    {
        return angerLevel != 0 ? super.findTarget() : null;
    }

    public void s()
    {
        super.s();
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        if(entity instanceof EntityHuman)
        {
            List list = world.b(this, boundingBox.b(32D, 32D, 32D));
            for(int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);
                if(entity1 instanceof EntityPigZombie)
                {
                    EntityPigZombie entitypigzombie = (EntityPigZombie)entity1;
                    entitypigzombie.e(entity);
                }
            }

            e(entity);
        }
        return super.damageEntity(damagesource, i);
    }

    private void e(Entity entity)
    {
        org.bukkit.entity.Entity bukkitTarget = entity != null ? entity.getBukkitEntity() : null;
        EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.PIG_ZOMBIE_TARGET);
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        if(event.getTarget() == null)
        {
            target = null;
            return;
        } else
        {
            entity = ((CraftEntity)event.getTarget()).getHandle();
            target = entity;
            angerLevel = 400 + random.nextInt(400);
            soundDelay = random.nextInt(40);
            return;
        }
    }

    protected String h()
    {
        return "mob.zombiepig.zpig";
    }

    protected String i()
    {
        return "mob.zombiepig.zpighurt";
    }

    protected String j()
    {
        return "mob.zombiepig.zpigdeath";
    }

    protected int k()
    {
        return Item.GRILLED_PORK.id;
    }

    public int angerLevel;
    private int soundDelay;
    private static final ItemStack g;

    static 
    {
        g = new ItemStack(Item.GOLD_SWORD, 1);
    }
}
