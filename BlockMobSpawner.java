// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockMobSpawner.java

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, TileEntityMobSpawner, Material, Block, 
//            World, TileEntity

public class BlockMobSpawner extends BlockContainer
{

    protected BlockMobSpawner(int i, int j)
    {
        super(i, j, Material.STONE);
    }

    public TileEntity a_()
    {
        return new TileEntityMobSpawner();
    }

    public int a(int i, Random random)
    {
        return Block.MOB_SPAWNER.id;
    }

    public int a(Random random)
    {
        return 0;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f)
    {
        TileEntity entity = world.getTileEntity(i, j, k);
        if(entity instanceof TileEntityMobSpawner)
            super.dropNaturally(world, i, j, k, ((TileEntityMobSpawner)entity).getId(), f);
    }

    public void remove(World world, int i, int j, int k)
    {
        dropNaturally(world, i, j, k, 0, 1.0F);
        super.remove(world, i, j, k);
    }

    protected int a_(int i)
    {
        return i;
    }

    public boolean a()
    {
        return false;
    }
}
