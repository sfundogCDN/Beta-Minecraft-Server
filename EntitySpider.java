// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySpider.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, World, Entity, MathHelper, 
//            Item, NBTTagCompound

public class EntitySpider extends EntityMonster
{

    public EntitySpider(World world)
    {
        super(world);
        texture = "/mob/spider.png";
        b(1.4F, 0.9F);
        aU = 0.8F;
    }

    public double n()
    {
        return (double)width * 0.75D - 0.5D;
    }

    protected boolean e_()
    {
        return false;
    }

    protected Entity findTarget()
    {
        float f = a_(1.0F);
        if(f < 0.5F)
        {
            double d0 = 16D;
            return world.findNearbyPlayer(this, d0);
        } else
        {
            return null;
        }
    }

    protected String h()
    {
        return "mob.spider";
    }

    protected String i()
    {
        return "mob.spider";
    }

    protected String j()
    {
        return "mob.spiderdeath";
    }

    protected void a(Entity entity, float f)
    {
        float f1 = a_(1.0F);
        if(f1 > 0.5F && random.nextInt(100) == 0)
        {
            EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, org.bukkit.event.entity.EntityTargetEvent.TargetReason.FORGOT_TARGET);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
            {
                if(event.getTarget() == null)
                    target = null;
                else
                    target = ((CraftEntity)event.getTarget()).getHandle();
                return;
            }
        } else
        if(f > 2.0F && f < 6F && random.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d0 = entity.locX - locX;
                double d1 = entity.locZ - locZ;
                float f2 = MathHelper.a(d0 * d0 + d1 * d1);
                motX = (d0 / (double)f2) * 0.5D * 0.80000001192092896D + motX * 0.20000000298023224D;
                motZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motZ * 0.20000000298023224D;
                motY = 0.40000000596046448D;
            }
        } else
        {
            super.a(entity, f);
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
        return Item.STRING.id;
    }

    public boolean p()
    {
        return positionChanged;
    }

    public void q()
    {
    }
}
