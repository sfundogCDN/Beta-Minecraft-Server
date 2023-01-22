// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Material, Block, 
//            EnumSkyBlock, BlockGrass

public class WorldGenLakes extends WorldGenerator
{

    public WorldGenLakes(int i)
    {
        a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        i -= 8;
        for(k -= 8; j > 0 && world.isEmpty(i, j, k); j--);
        j -= 4;
        boolean aflag[] = new boolean[2048];
        int l = random.nextInt(4) + 4;
        for(int i1 = 0; i1 < l; i1++)
        {
            double d = random.nextDouble() * 6D + 3D;
            double d1 = random.nextDouble() * 4D + 2D;
            double d2 = random.nextDouble() * 6D + 3D;
            double d3 = random.nextDouble() * (16D - d - 2D) + 1.0D + d / 2D;
            double d4 = random.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
            double d5 = random.nextDouble() * (16D - d2 - 2D) + 1.0D + d2 / 2D;
            for(int j2 = 1; j2 < 15; j2++)
            {
                for(int k2 = 1; k2 < 15; k2++)
                {
                    for(int l2 = 1; l2 < 7; l2++)
                    {
                        double d6 = ((double)j2 - d3) / (d / 2D);
                        double d7 = ((double)l2 - d4) / (d1 / 2D);
                        double d8 = ((double)k2 - d5) / (d2 / 2D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if(d9 < 1.0D)
                            aflag[(j2 * 16 + k2) * 8 + l2] = true;
                    }

                }

            }

        }

        for(int j1 = 0; j1 < 16; j1++)
        {
            for(int i3 = 0; i3 < 16; i3++)
            {
                for(int i4 = 0; i4 < 8; i4++)
                {
                    boolean flag = !aflag[(j1 * 16 + i3) * 8 + i4] && (j1 < 15 && aflag[((j1 + 1) * 16 + i3) * 8 + i4] || j1 > 0 && aflag[((j1 - 1) * 16 + i3) * 8 + i4] || i3 < 15 && aflag[(j1 * 16 + (i3 + 1)) * 8 + i4] || i3 > 0 && aflag[(j1 * 16 + (i3 - 1)) * 8 + i4] || i4 < 7 && aflag[(j1 * 16 + i3) * 8 + (i4 + 1)] || i4 > 0 && aflag[(j1 * 16 + i3) * 8 + (i4 - 1)]);
                    if(!flag)
                        continue;
                    Material material = world.getMaterial(i + j1, j + i4, k + i3);
                    if(i4 >= 4 && material.isLiquid())
                        return false;
                    if(i4 < 4 && !material.isBuildable() && world.getTypeId(i + j1, j + i4, k + i3) != a)
                        return false;
                }

            }

        }

        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int j3 = 0; j3 < 16; j3++)
            {
                for(int j4 = 0; j4 < 8; j4++)
                    if(aflag[(k1 * 16 + j3) * 8 + j4])
                        world.setRawTypeId(i + k1, j + j4, k + j3, j4 < 4 ? a : 0);

            }

        }

        for(int l1 = 0; l1 < 16; l1++)
        {
            for(int k3 = 0; k3 < 16; k3++)
            {
                for(int k4 = 4; k4 < 8; k4++)
                    if(aflag[(l1 * 16 + k3) * 8 + k4] && world.getTypeId(i + l1, (j + k4) - 1, k + k3) == Block.DIRT.id && world.a(EnumSkyBlock.SKY, i + l1, j + k4, k + k3) > 0)
                        world.setRawTypeId(i + l1, (j + k4) - 1, k + k3, Block.GRASS.id);

            }

        }

        if(Block.byId[a].material == Material.LAVA)
        {
            for(int i2 = 0; i2 < 16; i2++)
            {
                for(int l3 = 0; l3 < 16; l3++)
                {
                    for(int l4 = 0; l4 < 8; l4++)
                    {
                        boolean flag1 = !aflag[(i2 * 16 + l3) * 8 + l4] && (i2 < 15 && aflag[((i2 + 1) * 16 + l3) * 8 + l4] || i2 > 0 && aflag[((i2 - 1) * 16 + l3) * 8 + l4] || l3 < 15 && aflag[(i2 * 16 + (l3 + 1)) * 8 + l4] || l3 > 0 && aflag[(i2 * 16 + (l3 - 1)) * 8 + l4] || l4 < 7 && aflag[(i2 * 16 + l3) * 8 + (l4 + 1)] || l4 > 0 && aflag[(i2 * 16 + l3) * 8 + (l4 - 1)]);
                        if(flag1 && (l4 < 4 || random.nextInt(2) != 0) && world.getMaterial(i + i2, j + l4, k + l3).isBuildable())
                            world.setRawTypeId(i + i2, j + l4, k + l3, Block.STONE.id);
                    }

                }

            }

        }
        return true;
    }

    private int a;
}
