// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockSoil.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, EntityHuman, Material, AxisAlignedBB, 
//            World, Entity

public class BlockSoil extends Block
{

    protected BlockSoil(int i)
    {
        super(i, Material.EARTH);
        textureId = 87;
        a(true);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        f(255);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return AxisAlignedBB.b(i + 0, j + 0, k + 0, i + 1, j + 1, k + 1);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public int a(int i, int j)
    {
        return i != 1 || j <= 0 ? i != 1 ? 2 : textureId : textureId - 1;
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(random.nextInt(5) == 0)
            if(!h(world, i, j, k) && !world.s(i, j + 1, k))
            {
                int l = world.getData(i, j, k);
                if(l > 0)
                    world.setData(i, j, k, l - 1);
                else
                if(!g(world, i, j, k))
                    world.setTypeId(i, j, k, Block.DIRT.id);
            } else
            {
                world.setData(i, j, k, 7);
            }
    }

    public void b(World world, int i, int j, int k, Entity entity)
    {
        if(world.random.nextInt(4) == 0)
        {
            Cancellable cancellable;
            if(entity instanceof EntityHuman)
            {
                cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, i, j, k, -1, null);
            } else
            {
                cancellable = new EntityInteractEvent(entity.getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
                world.getServer().getPluginManager().callEvent((EntityInteractEvent)cancellable);
            }
            if(cancellable.isCancelled())
                return;
            world.setTypeId(i, j, k, Block.DIRT.id);
        }
    }

    private boolean g(World world, int i, int j, int k)
    {
        byte b0 = 0;
        for(int l = i - b0; l <= i + b0; l++)
        {
            for(int i1 = k - b0; i1 <= k + b0; i1++)
            {
                int j1 = world.getTypeId(l, j + 1, i1);
                if(j1 == Block.CROPS.id || j1 == Block.MELON_STEM.id || j1 == Block.PUMPKIN_STEM.id)
                    return true;
            }

        }

        return false;
    }

    private boolean h(World world, int i, int j, int k)
    {
        for(int l = i - 4; l <= i + 4; l++)
        {
            for(int i1 = j; i1 <= j + 1; i1++)
            {
                for(int j1 = k - 4; j1 <= k + 4; j1++)
                    if(world.getMaterial(l, i1, j1) == Material.WATER)
                        return true;

            }

        }

        return false;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        super.doPhysics(world, i, j, k, l);
        Material material = world.getMaterial(i, j + 1, k);
        if(material.isBuildable())
            world.setTypeId(i, j, k, Block.DIRT.id);
    }

    public int a(int i, Random random)
    {
        return Block.DIRT.a(0, random);
    }
}
