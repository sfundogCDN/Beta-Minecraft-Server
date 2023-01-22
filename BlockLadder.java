// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, AxisAlignedBB

public class BlockLadder extends Block
{

    protected BlockLadder(int i, int j)
    {
        super(i, j, Material.ORIENTABLE);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        float f = 0.125F;
        if(l == 2)
            a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        if(l == 3)
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        if(l == 4)
            a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        if(l == 5)
            a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        return super.e(world, i, j, k);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        if(world.e(i - 1, j, k))
            return true;
        if(world.e(i + 1, j, k))
            return true;
        if(world.e(i, j, k - 1))
            return true;
        return world.e(i, j, k + 1);
    }

    public void postPlace(World world, int i, int j, int k, int l)
    {
        int i1 = world.getData(i, j, k);
        if((i1 == 0 || l == 2) && world.e(i, j, k + 1))
            i1 = 2;
        if((i1 == 0 || l == 3) && world.e(i, j, k - 1))
            i1 = 3;
        if((i1 == 0 || l == 4) && world.e(i + 1, j, k))
            i1 = 4;
        if((i1 == 0 || l == 5) && world.e(i - 1, j, k))
            i1 = 5;
        world.setData(i, j, k, i1);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        int i1 = world.getData(i, j, k);
        boolean flag = false;
        if(i1 == 2 && world.e(i, j, k + 1))
            flag = true;
        if(i1 == 3 && world.e(i, j, k - 1))
            flag = true;
        if(i1 == 4 && world.e(i + 1, j, k))
            flag = true;
        if(i1 == 5 && world.e(i - 1, j, k))
            flag = true;
        if(!flag)
        {
            g(world, i, j, k, i1);
            world.setTypeId(i, j, k, 0);
        }
        super.doPhysics(world, i, j, k, l);
    }

    public int a(Random random)
    {
        return 1;
    }
}
