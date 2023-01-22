// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, Item, EnumSkyBlock, 
//            World

public class BlockSnowBlock extends Block
{

    protected BlockSnowBlock(int i, int j)
    {
        super(i, j, Material.SNOW_BLOCK);
        a(true);
    }

    public int a(int i, Random random)
    {
        return Item.SNOW_BALL.id;
    }

    public int a(Random random)
    {
        return 4;
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(world.a(EnumSkyBlock.BLOCK, i, j, k) > 11)
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }
}
