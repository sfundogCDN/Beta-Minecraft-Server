// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockStationary.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockFluids, Material, World, Block, 
//            BlockFire

public class BlockStationary extends BlockFluids
{

    protected BlockStationary(int i, Material material)
    {
        super(i, material);
        a(false);
        if(material == Material.LAVA)
            a(true);
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        super.doPhysics(world, i, j, k, l);
        if(world.getTypeId(i, j, k) == id)
            i(world, i, j, k);
    }

    private void i(net.minecraft.server.World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        world.suppressPhysics = true;
        world.setRawTypeIdAndData(i, j, k, id - 1, l);
        world.b(i, j, k, i, j, k);
        world.c(i, j, k, id - 1, c());
        world.suppressPhysics = false;
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        if(material == Material.LAVA)
        {
            int l = random.nextInt(3);
            World bworld = world.getWorld();
            org.bukkit.event.block.BlockIgniteEvent.IgniteCause igniteCause = org.bukkit.event.block.BlockIgniteEvent.IgniteCause.LAVA;
            for(int i1 = 0; i1 < l; i1++)
            {
                i += random.nextInt(3) - 1;
                j++;
                k += random.nextInt(3) - 1;
                int j1 = world.getTypeId(i, j, k);
                if(j1 == 0)
                {
                    if(!j(world, i - 1, j, k) && !j(world, i + 1, j, k) && !j(world, i, j, k - 1) && !j(world, i, j, k + 1) && !j(world, i, j - 1, k) && !j(world, i, j + 1, k))
                        continue;
                    Block block = bworld.getBlockAt(i, j, k);
                    if(block.getTypeId() != Block.FIRE.id)
                    {
                        BlockIgniteEvent event = new BlockIgniteEvent(block, igniteCause, null);
                        world.getServer().getPluginManager().callEvent(event);
                        if(event.isCancelled())
                            continue;
                    }
                    world.setTypeId(i, j, k, Block.FIRE.id);
                    return;
                }
                if(Block.byId[j1].material.isSolid())
                    return;
            }

        }
    }

    private boolean j(net.minecraft.server.World world, int i, int j, int k)
    {
        return world.getMaterial(i, j, k).isBurnable();
    }
}
