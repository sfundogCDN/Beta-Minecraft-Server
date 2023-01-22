// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockFlower, Item, World, EntityHuman, 
//            ItemStack, ItemShears, StatisticList, Block

public class BlockLongGrass extends BlockFlower
{

    protected BlockLongGrass(int i, int j)
    {
        super(i, j);
        float f = 0.4F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public int a(int i, int j)
    {
        if(j == 1)
            return textureId;
        if(j == 2)
            return textureId + 16 + 1;
        if(j == 0)
            return textureId + 16;
        else
            return textureId;
    }

    public int a(int i, Random random)
    {
        if(random.nextInt(8) == 0)
            return Item.SEEDS.id;
        else
            return -1;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
    {
        if(!world.isStatic && entityhuman.K() != null && entityhuman.K().id == Item.SHEARS.id)
        {
            entityhuman.a(StatisticList.C[id], 1);
            a(world, i, j, k, new ItemStack(Block.LONG_GRASS, 1, l));
        } else
        {
            super.a(world, entityhuman, i, j, k, l);
        }
    }
}
