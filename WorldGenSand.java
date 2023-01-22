// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Material, Block, 
//            BlockGrass

public class WorldGenSand extends WorldGenerator
{

    public WorldGenSand(int i, int j)
    {
        a = j;
        b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        if(world.getMaterial(i, j, k) != Material.WATER)
            return false;
        int l = random.nextInt(b - 2) + 2;
        byte byte0 = 2;
        for(int i1 = i - l; i1 <= i + l; i1++)
        {
            for(int j1 = k - l; j1 <= k + l; j1++)
            {
                int k1 = i1 - i;
                int l1 = j1 - k;
                if(k1 * k1 + l1 * l1 > l * l)
                    continue;
                for(int i2 = j - byte0; i2 <= j + byte0; i2++)
                {
                    int j2 = world.getTypeId(i1, i2, j1);
                    if(j2 == Block.DIRT.id || j2 == Block.GRASS.id)
                        world.setRawTypeId(i1, i2, j1, a);
                }

            }

        }

        return true;
    }

    private int a;
    private int b;
}
