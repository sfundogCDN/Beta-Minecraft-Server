// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockFlower

public class WorldGenDeadBush extends WorldGenerator
{

    public WorldGenDeadBush(int i)
    {
        a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        for(int l = 0; ((l = world.getTypeId(i, j, k)) == 0 || l == Block.LEAVES.id) && j > 0; j--);
        for(int i1 = 0; i1 < 4; i1++)
        {
            int j1 = (i + random.nextInt(8)) - random.nextInt(8);
            int k1 = (j + random.nextInt(4)) - random.nextInt(4);
            int l1 = (k + random.nextInt(8)) - random.nextInt(8);
            if(world.isEmpty(j1, k1, l1) && ((BlockFlower)Block.byId[a]).f(world, j1, k1, l1))
                world.setRawTypeId(j1, k1, l1, a);
        }

        return true;
    }

    private int a;
}
