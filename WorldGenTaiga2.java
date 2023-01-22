// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldGenTaiga2.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.BlockChangeDelegate;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, Block, BlockLeaves, BlockGrass, 
//            World

public class WorldGenTaiga2 extends WorldGenerator
{

    public WorldGenTaiga2()
    {
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        return generate((BlockChangeDelegate)world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(4) + 6;
        int i1 = 1 + random.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + random.nextInt(2);
        boolean flag = true;
        if(j >= 1)
        {
            int l1 = j + l + 1;
            world.getClass();
            if(l1 <= 128)
            {
                int i2;
                for(i2 = j; i2 <= j + 1 + l && flag; i2++)
                {
                    boolean flag1 = true;
                    int l2;
                    if(i2 - j < i1)
                        l2 = 0;
                    else
                        l2 = k1;
                    for(int j2 = i - l2; j2 <= i + l2 && flag; j2++)
                    {
                        for(int i3 = k - l2; i3 <= k + l2 && flag; i3++)
                        {
                            if(i2 >= 0)
                            {
                                world.getClass();
                                if(i2 < 128)
                                {
                                    int k2 = world.getTypeId(j2, i2, i3);
                                    if(k2 != 0 && k2 != Block.LEAVES.id)
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
                i2 = world.getTypeId(i, j - 1, k);
                if(i2 == Block.GRASS.id || i2 == Block.DIRT.id)
                {
                    world.getClass();
                    if(j < 128 - l - 1)
                    {
                        world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
                        int l2 = random.nextInt(2);
                        int j2 = 1;
                        byte b0 = 0;
                        int k2;
                        for(k2 = 0; k2 <= j1; k2++)
                        {
                            int k3 = (j + l) - k2;
                            for(int j3 = i - l2; j3 <= i + l2; j3++)
                            {
                                int l3 = j3 - i;
                                for(int i4 = k - l2; i4 <= k + l2; i4++)
                                {
                                    int j4 = i4 - k;
                                    if((Math.abs(l3) != l2 || Math.abs(j4) != l2 || l2 <= 0) && !Block.o[world.getTypeId(j3, k3, i4)])
                                        world.setRawTypeIdAndData(j3, k3, i4, Block.LEAVES.id, 1);
                                }

                            }

                            if(l2 >= j2)
                            {
                                l2 = b0;
                                b0 = 1;
                                if(++j2 > k1)
                                    j2 = k1;
                            } else
                            {
                                l2++;
                            }
                        }

                        k2 = random.nextInt(3);
                        for(int k3 = 0; k3 < l - k2; k3++)
                        {
                            int j3 = world.getTypeId(i, j + k3, k);
                            if(j3 == 0 || j3 == Block.LEAVES.id)
                                world.setRawTypeIdAndData(i, j + k3, k, Block.LOG.id, 1);
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
