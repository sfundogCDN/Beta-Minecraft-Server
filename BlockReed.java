// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, BlockGrass, 
//            Item, AxisAlignedBB

public class BlockReed extends Block
{

    protected BlockReed(int i, int j)
    {
        super(i, Material.PLANT);
        textureId = j;
        float f1 = 0.375F;
        a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, 1.0F, 0.5F + f1);
        a(true);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(world.isEmpty(i, j + 1, k))
        {
            int l;
            for(l = 1; world.getTypeId(i, j - l, k) == id; l++);
            if(l < 3)
            {
                int i1 = world.getData(i, j, k);
                if(i1 == 15)
                {
                    world.setTypeId(i, j + 1, k, id);
                    world.setData(i, j, k, 0);
                } else
                {
                    world.setData(i, j, k, i1 + 1);
                }
            }
        }
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        int l = world.getTypeId(i, j - 1, k);
        if(l == id)
            return true;
        if(l != Block.GRASS.id && l != Block.DIRT.id && l != Block.SAND.id)
            return false;
        if(world.getMaterial(i - 1, j - 1, k) == Material.WATER)
            return true;
        if(world.getMaterial(i + 1, j - 1, k) == Material.WATER)
            return true;
        if(world.getMaterial(i, j - 1, k - 1) == Material.WATER)
            return true;
        return world.getMaterial(i, j - 1, k + 1) == Material.WATER;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        g(world, i, j, k);
    }

    protected final void g(World world, int i, int j, int k)
    {
        if(!f(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean f(World world, int i, int j, int k)
    {
        return canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return null;
    }

    public int a(int i, Random random)
    {
        return Item.SUGAR_CANE.id;
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }
}
