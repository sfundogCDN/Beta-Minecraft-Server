// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, Block, World, Material

public class WorldGenClay extends WorldGenerator
{

    public WorldGenClay(int i)
    {
        a = Block.CLAY.id;
        b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        if(world.getMaterial(i, j, k) != Material.WATER)
            return false;
        int l = random.nextInt(b - 2) + 2;
        int i1 = 1;
        for(int j1 = i - l; j1 <= i + l; j1++)
        {
            for(int k1 = k - l; k1 <= k + l; k1++)
            {
                int l1 = j1 - i;
                int i2 = k1 - k;
                if(l1 * l1 + i2 * i2 > l * l)
                    continue;
                for(int j2 = j - i1; j2 <= j + i1; j2++)
                {
                    int k2 = world.getTypeId(j1, j2, k1);
                    if(k2 == Block.DIRT.id || k2 == Block.CLAY.id)
                        world.setRawTypeId(j1, j2, k1, a);
                }

            }

        }

        return true;
    }

    private int a;
    private int b;
}
