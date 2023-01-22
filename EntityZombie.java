// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityZombie.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, World, MathHelper, Item

public class EntityZombie extends EntityMonster
{

    public EntityZombie(World world)
    {
        super(world);
        texture = "/mob/zombie.png";
        aU = 0.5F;
        damage = 5;
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

    protected String h()
    {
        return "mob.zombie";
    }

    protected String i()
    {
        return "mob.zombiehurt";
    }

    protected String j()
    {
        return "mob.zombiedeath";
    }

    protected int k()
    {
        return Item.ROTTEN_FLESH.id;
    }
}
