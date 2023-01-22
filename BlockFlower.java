// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, BlockGrass, 
//            AxisAlignedBB

public class BlockFlower extends Block
{

    protected BlockFlower(int i, int j)
    {
        super(i, Material.PLANT);
        textureId = j;
        a(true);
        float f1 = 0.2F;
        a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, f1 * 3F, 0.5F + f1);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return super.canPlace(world, i, j, k) && c(world.getTypeId(i, j - 1, k));
    }

    protected boolean c(int i)
    {
        return i == Block.GRASS.id || i == Block.DIRT.id || i == Block.SOIL.id;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        super.doPhysics(world, i, j, k, l);
        g(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random)
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
        return (world.k(i, j, k) >= 8 || world.isChunkLoaded(i, j, k)) && c(world.getTypeId(i, j - 1, k));
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return null;
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
