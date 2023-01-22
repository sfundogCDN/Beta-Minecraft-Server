// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockFlower, Block, World, EntityItem, 
//            ItemStack, Item

public class BlockCrops extends BlockFlower
{

    protected BlockCrops(int i, int j)
    {
        super(i, j);
        textureId = j;
        a(true);
        float f = 0.5F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    protected boolean c(int i)
    {
        return i == Block.SOIL.id;
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        super.a(world, i, j, k, random);
        if(world.getLightLevel(i, j + 1, k) >= 9)
        {
            int l = world.getData(i, j, k);
            if(l < 7)
            {
                float f = h(world, i, j, k);
                if(random.nextInt((int)(100F / f)) == 0)
                {
                    l++;
                    world.setData(i, j, k, l);
                }
            }
        }
    }

    public void e_(World world, int i, int j, int k)
    {
        world.setData(i, j, k, 7);
    }

    private float h(World world, int i, int j, int k)
    {
        float f = 1.0F;
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        int l1 = world.getTypeId(i - 1, j, k - 1);
        int i2 = world.getTypeId(i + 1, j, k - 1);
        int j2 = world.getTypeId(i + 1, j, k + 1);
        int k2 = world.getTypeId(i - 1, j, k + 1);
        boolean flag = j1 == id || k1 == id;
        boolean flag1 = l == id || i1 == id;
        boolean flag2 = l1 == id || i2 == id || j2 == id || k2 == id;
        for(int l2 = i - 1; l2 <= i + 1; l2++)
        {
            for(int i3 = k - 1; i3 <= k + 1; i3++)
            {
                int j3 = world.getTypeId(l2, j - 1, i3);
                float f1 = 0.0F;
                if(j3 == Block.SOIL.id)
                {
                    f1 = 1.0F;
                    if(world.getData(l2, j - 1, i3) > 0)
                        f1 = 3F;
                }
                if(l2 != i || i3 != k)
                    f1 /= 4F;
                f += f1;
            }

        }

        if(flag2 || flag && flag1)
            f /= 2.0F;
        return f;
    }

    public int a(int i, int j)
    {
        if(j < 0)
            j = 7;
        return textureId + j;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f)
    {
        super.dropNaturally(world, i, j, k, l, f);
        if(world.isStatic)
            return;
        for(int i1 = 0; i1 < 3; i1++)
            if(world.random.nextInt(15) <= l)
            {
                float f1 = 0.7F;
                float f2 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                float f3 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                float f4 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                EntityItem entityitem = new EntityItem(world, (float)i + f2, (float)j + f3, (float)k + f4, new ItemStack(Item.SEEDS));
                entityitem.pickupDelay = 10;
                world.addEntity(entityitem);
            }

    }

    public int a(int i, Random random)
    {
        if(i == 7)
            return Item.WHEAT.id;
        else
            return -1;
    }

    public int a(Random random)
    {
        return 1;
    }
}
