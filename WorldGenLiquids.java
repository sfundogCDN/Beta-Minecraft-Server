// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Block

public class WorldGenLiquids extends WorldGenerator
{

    public WorldGenLiquids(int i)
    {
        a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        if(world.getTypeId(i, j + 1, k) != Block.STONE.id)
            return false;
        if(world.getTypeId(i, j - 1, k) != Block.STONE.id)
            return false;
        if(world.getTypeId(i, j, k) != 0 && world.getTypeId(i, j, k) != Block.STONE.id)
            return false;
        int l = 0;
        if(world.getTypeId(i - 1, j, k) == Block.STONE.id)
            l++;
        if(world.getTypeId(i + 1, j, k) == Block.STONE.id)
            l++;
        if(world.getTypeId(i, j, k - 1) == Block.STONE.id)
            l++;
        if(world.getTypeId(i, j, k + 1) == Block.STONE.id)
            l++;
        int i1 = 0;
        if(world.isEmpty(i - 1, j, k))
            i1++;
        if(world.isEmpty(i + 1, j, k))
            i1++;
        if(world.isEmpty(i, j, k - 1))
            i1++;
        if(world.isEmpty(i, j, k + 1))
            i1++;
        if(l == 3 && i1 == 1)
        {
            world.setTypeId(i, j, k, a);
            world.f = true;
            Block.byId[a].a(world, i, j, k, random);
            world.f = false;
        }
        return true;
    }

    private int a;
}