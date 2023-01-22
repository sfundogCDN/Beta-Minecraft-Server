// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Block

public class WorldGenLightStone2 extends WorldGenerator
{

    public WorldGenLightStone2()
    {
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        if(!world.isEmpty(i, j, k))
            return false;
        if(world.getTypeId(i, j + 1, k) != Block.NETHERRACK.id)
            return false;
        world.setTypeId(i, j, k, Block.GLOWSTONE.id);
        for(int l = 0; l < 1500; l++)
        {
            int i1 = (i + random.nextInt(8)) - random.nextInt(8);
            int j1 = j - random.nextInt(12);
            int k1 = (k + random.nextInt(8)) - random.nextInt(8);
            if(world.getTypeId(i1, j1, k1) != 0)
                continue;
            int l1 = 0;
            for(int i2 = 0; i2 < 6; i2++)
            {
                int j2 = 0;
                if(i2 == 0)
                    j2 = world.getTypeId(i1 - 1, j1, k1);
                if(i2 == 1)
                    j2 = world.getTypeId(i1 + 1, j1, k1);
                if(i2 == 2)
                    j2 = world.getTypeId(i1, j1 - 1, k1);
                if(i2 == 3)
                    j2 = world.getTypeId(i1, j1 + 1, k1);
                if(i2 == 4)
                    j2 = world.getTypeId(i1, j1, k1 - 1);
                if(i2 == 5)
                    j2 = world.getTypeId(i1, j1, k1 + 1);
                if(j2 == Block.GLOWSTONE.id)
                    l1++;
            }

            if(l1 == 1)
                world.setTypeId(i1, j1, k1, Block.GLOWSTONE.id);
        }

        return true;
    }
}
