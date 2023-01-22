// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockSapling.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.BlockChangeDelegate;

// Referenced classes of package net.minecraft.server:
//            BlockFlower, WorldGenTaiga2, WorldGenForest, WorldGenBigTree, 
//            WorldGenTrees, World

public class BlockSapling extends BlockFlower
{
    private class BlockChangeWithNotify
        implements BlockChangeDelegate
    {

        public boolean setRawTypeId(int x, int y, int z, int type)
        {
            return world.setTypeId(x, y, z, type);
        }

        public boolean setRawTypeIdAndData(int x, int y, int z, int type, int data)
        {
            return world.setTypeIdAndData(x, y, z, type, data);
        }

        public int getTypeId(int x, int y, int z)
        {
            return world.getTypeId(x, y, z);
        }

        public int getHeight()
        {
            world.getClass();
            return 128;
        }

        World world;
        final BlockSapling this$0;

        BlockChangeWithNotify(World world)
        {
            this$0 = BlockSapling.this;
            super();
            this.world = world;
        }
    }


    protected BlockSapling(int i, int j)
    {
        super(i, j);
        float f = 0.4F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(!world.isStatic)
        {
            super.a(world, i, j, k, random);
            if(world.getLightLevel(i, j + 1, k) >= 9 && random.nextInt(30) == 0)
            {
                int l = world.getData(i, j, k);
                if((l & 8) == 0)
                    world.setData(i, j, k, l | 8);
                else
                    b(world, i, j, k, random);
            }
        }
    }

    public int a(int i, int j)
    {
        j &= 3;
        return j != 1 ? j != 2 ? super.a(i, j) : 79 : 63;
    }

    public void b(World world, int i, int j, int k, Random random)
    {
        int l = world.getData(i, j, k) & 3;
        world.setRawTypeId(i, j, k, 0);
        BlockChangeWithNotify delegate = new BlockChangeWithNotify(world);
        boolean grownTree;
        if(l == 1)
            grownTree = (new WorldGenTaiga2()).generate(delegate, random, i, j, k);
        else
        if(l == 2)
            grownTree = (new WorldGenForest()).generate(delegate, random, i, j, k);
        else
        if(random.nextInt(10) == 0)
            grownTree = (new WorldGenBigTree()).generate(delegate, random, i, j, k);
        else
            grownTree = (new WorldGenTrees()).generate(delegate, random, i, j, k);
        if(!grownTree)
            world.setRawTypeIdAndData(i, j, k, id, l);
    }

    protected int a_(int i)
    {
        return i & 3;
    }
}
