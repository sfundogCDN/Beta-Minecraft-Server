// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, IBlockAccess, World, 
//            AxisAlignedBB, EntityHuman, FoodMetaData

public class BlockCake extends Block
{

    protected BlockCake(int i, int j)
    {
        super(i, j, Material.CAKE);
        a(true);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k);
        float f1 = 0.0625F;
        float f2 = (float)(1 + l * 2) / 16F;
        float f3 = 0.5F;
        a(f2, 0.0F, f1, 1.0F - f1, f3, 1.0F - f1);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        float f1 = 0.0625F;
        float f2 = (float)(1 + l * 2) / 16F;
        float f3 = 0.5F;
        return AxisAlignedBB.b((float)i + f2, j, (float)k + f1, (float)(i + 1) - f1, ((float)j + f3) - f1, (float)(k + 1) - f1);
    }

    public int a(int i, int j)
    {
        if(i == 1)
            return textureId;
        if(i == 0)
            return textureId + 3;
        if(j > 0 && i == 4)
            return textureId + 2;
        else
            return textureId + 1;
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId;
        if(i == 0)
            return textureId + 3;
        else
            return textureId + 1;
    }

    public boolean b()
    {
        return false;
    }

    public boolean a()
    {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        c(world, i, j, k, entityhuman);
        return true;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        c(world, i, j, k, entityhuman);
    }

    private void c(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(entityhuman.c(false))
        {
            entityhuman.getFoodData().a(2, 0.1F);
            int l = world.getData(i, j, k) + 1;
            if(l >= 6)
            {
                world.setTypeId(i, j, k, 0);
            } else
            {
                world.setData(i, j, k, l);
                world.i(i, j, k);
            }
        }
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        if(!super.canPlace(world, i, j, k))
            return false;
        else
            return f(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!f(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean f(World world, int i, int j, int k)
    {
        return world.getMaterial(i, j - 1, k).isBuildable();
    }

    public int a(Random random)
    {
        return 0;
    }

    public int a(int i, Random random)
    {
        return 0;
    }
}
