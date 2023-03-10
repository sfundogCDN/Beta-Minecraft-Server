// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldGenForest.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.BlockChangeDelegate;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, Block, BlockLeaves, BlockGrass, 
//            World

public class WorldGenForest extends WorldGenerator
{

    public WorldGenForest()
    {
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        return generate((BlockChangeDelegate)world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(3) + 5;
        boolean flag = true;
        if(j >= 1)
        {
            int k1000 = j + l + 1;
            world.getClass();
            if(k1000 <= 128)
            {
                int j1;
                for(j1 = j; j1 <= j + 1 + l; j1++)
                {
                    byte b0 = 1;
                    if(j1 == j)
                        b0 = 0;
                    if(j1 >= (j + 1 + l) - 2)
                        b0 = 2;
                    for(int k1 = i - b0; k1 <= i + b0 && flag; k1++)
                    {
                        for(int l1 = k - b0; l1 <= k + b0 && flag; l1++)
                        {
                            if(j1 >= 0)
                            {
                                world.getClass();
                                if(j1 < 128)
                                {
                                    int i2 = world.getTypeId(k1, j1, l1);
                                    if(i2 != 0 && i2 != Block.LEAVES.id)
                                        flag = false;
                                    continue;
                                }
                            }
                            flag = false;
                        }

                    }

                }

                if(!flag)
                    return false;
                j1 = world.getTypeId(i, j - 1, k);
                if(j1 == Block.GRASS.id || j1 == Block.DIRT.id)
                {
                    world.getClass();
                    if(j < 128 - l - 1)
                    {
                        world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
                        for(int j2 = (j - 3) + l; j2 <= j + l; j2++)
                        {
                            int k1 = j2 - (j + l);
                            int l1 = 1 - k1 / 2;
                            for(int i2 = i - l1; i2 <= i + l1; i2++)
                            {
                                int k2 = i2 - i;
                                for(int l2 = k - l1; l2 <= k + l1; l2++)
                                {
                                    int i3 = l2 - k;
                                    if((Math.abs(k2) != l1 || Math.abs(i3) != l1 || random.nextInt(2) != 0 && k1 != 0) && !Block.o[world.getTypeId(i2, j2, l2)])
                                        world.setRawTypeIdAndData(i2, j2, l2, Block.LEAVES.id, 2);
                                }

                            }

                        }

                        for(int j2 = 0; j2 < l; j2++)
                        {
                            int k1 = world.getTypeId(i, j + j2, k);
                            if(k1 == 0 || k1 == Block.LEAVES.id)
                                world.setRawTypeIdAndData(i, j + j2, k, Block.LOG.id, 2);
                        }

                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
