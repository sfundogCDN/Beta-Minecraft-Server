// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, Item, 
//            EntityHuman, Entity

public class BlockRedstoneOre extends Block
{

    public BlockRedstoneOre(int i, int j, boolean flag)
    {
        super(i, j, Material.STONE);
        if(flag)
            a(true);
        a = flag;
    }

    public int c()
    {
        return 30;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        g(world, i, j, k);
        super.b(world, i, j, k, entityhuman);
    }

    public void b(World world, int i, int j, int k, Entity entity)
    {
        g(world, i, j, k);
        super.b(world, i, j, k, entity);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        g(world, i, j, k);
        return super.interact(world, i, j, k, entityhuman);
    }

    private void g(World world, int i, int j, int k)
    {
        h(world, i, j, k);
        if(id == Block.REDSTONE_ORE.id)
            world.setTypeId(i, j, k, Block.GLOWING_REDSTONE_ORE.id);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(id == Block.GLOWING_REDSTONE_ORE.id)
            world.setTypeId(i, j, k, Block.REDSTONE_ORE.id);
    }

    public int a(int i, Random random)
    {
        return Item.REDSTONE.id;
    }

    public int a(Random random)
    {
        return 4 + random.nextInt(2);
    }

    private void h(World world, int i, int j, int k)
    {
        Random random = world.random;
        double d = 0.0625D;
        for(int l = 0; l < 6; l++)
        {
            double d1 = (float)i + random.nextFloat();
            double d2 = (float)j + random.nextFloat();
            double d3 = (float)k + random.nextFloat();
            if(l == 0 && !world.o(i, j + 1, k))
                d2 = (double)(j + 1) + d;
            if(l == 1 && !world.o(i, j - 1, k))
                d2 = (double)(j + 0) - d;
            if(l == 2 && !world.o(i, j, k + 1))
                d3 = (double)(k + 1) + d;
            if(l == 3 && !world.o(i, j, k - 1))
                d3 = (double)(k + 0) - d;
            if(l == 4 && !world.o(i + 1, j, k))
                d1 = (double)(i + 1) + d;
            if(l == 5 && !world.o(i - 1, j, k))
                d1 = (double)(i + 0) - d;
            if(d1 < (double)i || d1 > (double)(i + 1) || d2 < 0.0D || d2 > (double)(j + 1) || d3 < (double)k || d3 > (double)(k + 1))
                world.a("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        }

    }

    private boolean a;
}
