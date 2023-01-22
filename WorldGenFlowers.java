// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Block, BlockFlower

public class WorldGenFlowers extends WorldGenerator
{

    public WorldGenFlowers(int i)
    {
        a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        for(int l = 0; l < 64; l++)
        {
            int i1 = (i + random.nextInt(8)) - random.nextInt(8);
            int j1 = (j + random.nextInt(4)) - random.nextInt(4);
            int k1 = (k + random.nextInt(8)) - random.nextInt(8);
            if(world.isEmpty(i1, j1, k1) && ((BlockFlower)Block.byId[a]).f(world, i1, j1, k1))
                world.setRawTypeId(i1, j1, k1, a);
        }

        return true;
    }

    private int a;
}
