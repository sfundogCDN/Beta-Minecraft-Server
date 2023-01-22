// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockMushroom.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockFlower, WorldGenHugeMushroom, World, Block, 
//            BlockGrass

public class BlockMushroom extends BlockFlower
{

    protected BlockMushroom(int i, int j)
    {
        super(i, j);
        float f = 0.2F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        a(true);
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        if(random.nextInt(100) == 0)
        {
            byte b0 = 4;
            int l = 5;
            int i1;
            int j1;
            int k1;
            for(i1 = i - b0; i1 <= i + b0; i1++)
                for(j1 = k - b0; j1 <= k + b0; j1++)
                    for(k1 = j - 1; k1 <= j + 1; k1++)
                        if(world.getTypeId(i1, k1, j1) == id && --l <= 0)
                            return;



            i1 = (i + random.nextInt(3)) - 1;
            j1 = (j + random.nextInt(2)) - random.nextInt(2);
            k1 = (k + random.nextInt(3)) - 1;
            for(int l1 = 0; l1 < 4; l1++)
            {
                if(world.isEmpty(i1, j1, k1) && f(world, i1, j1, k1))
                {
                    i = i1;
                    j = j1;
                    k = k1;
                }
                i1 = (i + random.nextInt(3)) - 1;
                j1 = (j + random.nextInt(2)) - random.nextInt(2);
                k1 = (k + random.nextInt(3)) - 1;
            }

            if(world.isEmpty(i1, j1, k1) && f(world, i1, j1, k1))
            {
                World bworld = world.getWorld();
                BlockState blockState = bworld.getBlockAt(i1, j1, k1).getState();
                blockState.setTypeId(id);
                BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), bworld.getBlockAt(i, j, k), blockState);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    blockState.update(true);
            }
        }
    }

    protected boolean c(int i)
    {
        return Block.o[i];
    }

    public boolean f(net.minecraft.server.World world, int i, int j, int k)
    {
        if(j >= 0)
        {
            world.getClass();
            if(j < 128)
                return world.k(i, j, k) < 13 && c(world.getTypeId(i, j - 1, k));
        }
        return false;
    }

    public boolean b(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        int l = world.getTypeId(i, j - 1, k);
        if(l != Block.DIRT.id && l != Block.GRASS.id)
            return false;
        int i1 = world.getData(i, j, k);
        world.setRawTypeId(i, j, k, 0);
        WorldGenHugeMushroom worldgenhugemushroom = null;
        if(id == Block.BROWN_MUSHROOM.id)
            worldgenhugemushroom = new WorldGenHugeMushroom(0);
        else
        if(id == Block.RED_MUSHROOM.id)
            worldgenhugemushroom = new WorldGenHugeMushroom(1);
        if(worldgenhugemushroom != null && worldgenhugemushroom.a(world, random, i, j, k))
        {
            return true;
        } else
        {
            world.setRawTypeIdAndData(i, j, k, id, i1);
            return false;
        }
    }
}
