// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockGrass.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World

public class BlockGrass extends net.minecraft.server.Block
{

    protected BlockGrass(int i)
    {
        super(i, Material.GRASS);
        textureId = 3;
        a(true);
    }

    public int a(int i, int j)
    {
        return i != 1 ? i != 0 ? 3 : 2 : 0;
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        if(!world.isStatic)
            if(world.getLightLevel(i, j + 1, k) < 4 && Block.q[world.getTypeId(i, j + 1, k)] > 2)
            {
                if(random.nextInt(4) != 0)
                    return;
                World bworld = world.getWorld();
                BlockState blockState = bworld.getBlockAt(i, j, k).getState();
                blockState.setTypeId(Block.DIRT.id);
                BlockFadeEvent event = new BlockFadeEvent(blockState.getBlock(), blockState);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    blockState.update(true);
            } else
            if(world.getLightLevel(i, j + 1, k) >= 9)
            {
                int l = (i + random.nextInt(3)) - 1;
                int i1 = (j + random.nextInt(5)) - 3;
                int j1 = (k + random.nextInt(3)) - 1;
                int k1 = world.getTypeId(l, i1 + 1, j1);
                if(world.getTypeId(l, i1, j1) == Block.DIRT.id && world.getLightLevel(l, i1 + 1, j1) >= 4 && Block.q[k1] <= 2)
                {
                    World bworld = world.getWorld();
                    BlockState blockState = bworld.getBlockAt(l, i1, j1).getState();
                    blockState.setTypeId(id);
                    BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), bworld.getBlockAt(i, j, k), blockState);
                    world.getServer().getPluginManager().callEvent(event);
                    if(!event.isCancelled())
                        blockState.update(true);
                }
            }
    }

    public int a(int i, Random random)
    {
        return Block.DIRT.a(0, random);
    }
}
