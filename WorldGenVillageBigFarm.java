// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillagePiece, StructureBoundingBox, StructurePiece, Block, 
//            MathHelper, World

public class WorldGenVillageBigFarm extends WorldGenVillagePiece
{

    public WorldGenVillageBigFarm(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageBigFarm a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 13, 4, 9, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageBigFarm(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 4) - 1, 0);
        }
        a(world, structureboundingbox, 0, 1, 0, 12, 4, 8, 0, 0, false);
        a(world, structureboundingbox, 1, 0, 1, 2, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
        a(world, structureboundingbox, 4, 0, 1, 5, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
        a(world, structureboundingbox, 7, 0, 1, 8, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
        a(world, structureboundingbox, 10, 0, 1, 11, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
        a(world, structureboundingbox, 0, 0, 0, 0, 0, 8, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 6, 0, 0, 6, 0, 8, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 12, 0, 0, 12, 0, 8, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 1, 0, 0, 11, 0, 0, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 1, 0, 8, 11, 0, 8, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 3, 0, 1, 3, 0, 7, Block.WATER.id, Block.WATER.id, false);
        a(world, structureboundingbox, 9, 0, 1, 9, 0, 7, Block.WATER.id, Block.WATER.id, false);
        for(int i = 1; i <= 7; i++)
        {
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 1, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 2, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 4, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 5, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 7, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 8, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 10, 1, i, structureboundingbox);
            a(world, Block.CROPS.id, MathHelper.a(random, 2, 7), 11, 1, i, structureboundingbox);
        }

        for(int j = 0; j < 9; j++)
        {
            for(int k = 0; k < 13; k++)
            {
                b(world, k, 4, j, structureboundingbox);
                b(world, Block.DIRT.id, 0, k, -1, j, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
