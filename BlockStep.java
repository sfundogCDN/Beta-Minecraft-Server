// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World

public class BlockStep extends Block
{

    public BlockStep(int i, boolean flag)
    {
        super(i, 6, Material.STONE);
        b = flag;
        if(!flag)
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        f(255);
    }

    public int a(int i, int j)
    {
        if(j == 0)
            return i > 1 ? 5 : 6;
        if(j == 1)
        {
            if(i == 0)
                return 208;
            return i != 1 ? 192 : 176;
        }
        if(j == 2)
            return 4;
        if(j == 3)
            return 16;
        if(j == 4)
            return Block.BRICK.textureId;
        if(j == 5)
            return Block.SMOOTH_BRICK.textureId;
        else
            return 6;
    }

    public int a(int i)
    {
        return a(i, 0);
    }

    public boolean a()
    {
        return b;
    }

    public void a(World world, int i, int j, int k)
    {
        if(this != Block.STEP)
            super.a(world, i, j, k);
        int l = world.getTypeId(i, j - 1, k);
        int i1 = world.getData(i, j, k);
        int j1 = world.getData(i, j - 1, k);
        if(i1 != j1)
            return;
        if(l == STEP.id)
        {
            world.setTypeId(i, j, k, 0);
            world.setTypeIdAndData(i, j - 1, k, Block.DOUBLE_STEP.id, i1);
        }
    }

    public int a(int i, Random random)
    {
        return Block.STEP.id;
    }

    public int a(Random random)
    {
        return !b ? 1 : 2;
    }

    protected int a_(int i)
    {
        return i;
    }

    public boolean b()
    {
        return b;
    }

    public static final String a[] = {
        "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick"
    };
    private boolean b;

}
