// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockCactus.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, EntityLiving, Material, World, 
//            AxisAlignedBB, Entity, DamageSource

public class BlockCactus extends Block
{

    protected BlockCactus(int i, int j)
    {
        super(i, j, Material.CACTUS);
        a(true);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(world.isEmpty(i, j + 1, k))
        {
            int l;
            for(l = 1; world.getTypeId(i, j - l, k) == id; l++);
            if(l < 3)
            {
                int i1 = world.getData(i, j, k);
                if(i1 == 15)
                {
                    world.setTypeId(i, j + 1, k, id);
                    world.setData(i, j, k, 0);
                } else
                {
                    world.setData(i, j, k, i1 + 1);
                }
            }
        }
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        float f = 0.0625F;
        return AxisAlignedBB.b((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 1) - f, (float)(k + 1) - f);
    }

    public int a(int i)
    {
        return i != 1 ? i != 0 ? textureId : textureId + 1 : textureId - 1;
    }

    public boolean b()
    {
        return false;
    }

    public boolean a()
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return super.canPlace(world, i, j, k) ? f(world, i, j, k) : false;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!f(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean f(World world, int i, int j, int k)
    {
        if(world.getMaterial(i - 1, j, k).isBuildable())
            return false;
        if(world.getMaterial(i + 1, j, k).isBuildable())
            return false;
        if(world.getMaterial(i, j, k - 1).isBuildable())
            return false;
        if(world.getMaterial(i, j, k + 1).isBuildable())
        {
            return false;
        } else
        {
            int l = world.getTypeId(i, j - 1, k);
            return l == Block.CACTUS.id || l == Block.SAND.id;
        }
    }

    public void a(World world, int i, int j, int k, Entity entity)
    {
        if(entity instanceof EntityLiving)
        {
            org.bukkit.block.Block damager = world.getWorld().getBlockAt(i, j, k);
            org.bukkit.entity.Entity damagee = entity != null ? entity.getBukkitEntity() : null;
            EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.CONTACT, 1);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                entity.damageEntity(DamageSource.CACTUS, event.getDamage());
            return;
        } else
        {
            entity.damageEntity(DamageSource.CACTUS, 1);
            return;
        }
    }
}
