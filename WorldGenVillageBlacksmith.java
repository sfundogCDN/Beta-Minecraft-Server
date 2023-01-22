// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillagePiece, StructureBoundingBox, StructurePiece, Block, 
//            World

public class WorldGenVillageBlacksmith extends WorldGenVillagePiece
{

    public WorldGenVillageBlacksmith(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageBlacksmith a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 10, 6, 7, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageBlacksmith(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 6) - 1, 0);
        }
        a(world, structureboundingbox, 0, 1, 0, 9, 4, 6, 0, 0, false);
        a(world, structureboundingbox, 0, 0, 0, 9, 0, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 4, 0, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 5, 0, 9, 5, 6, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 1, 5, 1, 8, 5, 5, 0, 0, false);
        a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 1, 0, 0, 4, 0, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 3, 1, 0, 3, 4, 0, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 0, 1, 6, 0, 4, 6, Block.LOG.id, Block.LOG.id, false);
        a(world, Block.WOOD.id, 0, 3, 3, 1, structureboundingbox);
        a(world, structureboundingbox, 3, 1, 2, 3, 3, 2, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 4, 1, 3, 5, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 1, 1, 0, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 1, 6, 5, 3, 6, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 5, 1, 0, 5, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
        a(world, structureboundingbox, 9, 1, 0, 9, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
        a(world, structureboundingbox, 6, 1, 4, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, Block.LAVA.id, 0, 7, 1, 5, structureboundingbox);
        a(world, Block.LAVA.id, 0, 8, 1, 5, structureboundingbox);
        a(world, Block.IRON_FENCE.id, 0, 9, 2, 5, structureboundingbox);
        a(world, Block.IRON_FENCE.id, 0, 9, 2, 4, structureboundingbox);
        a(world, structureboundingbox, 7, 2, 4, 8, 2, 5, 0, 0, false);
        a(world, Block.COBBLESTONE.id, 0, 6, 1, 3, structureboundingbox);
        a(world, Block.FURNACE.id, 0, 6, 2, 3, structureboundingbox);
        a(world, Block.FURNACE.id, 0, 6, 3, 3, structureboundingbox);
        a(world, Block.DOUBLE_STEP.id, 0, 8, 1, 1, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 6, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 2, 6, structureboundingbox);
        a(world, Block.FENCE.id, 0, 2, 1, 4, structureboundingbox);
        a(world, Block.WOOD_PLATE.id, 0, 2, 2, 4, structureboundingbox);
        a(world, Block.WOOD.id, 0, 1, 1, 5, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 3), 2, 1, 5, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 1), 1, 1, 4, structureboundingbox);
        for(int i = 6; i <= 8; i++)
            if(a(world, i, 0, -1, structureboundingbox) == 0 && a(world, i, -1, -1, structureboundingbox) != 0)
                a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), i, 0, -1, structureboundingbox);

        for(int j = 0; j < 7; j++)
        {
            for(int k = 0; k < 10; k++)
            {
                b(world, k, 6, j, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, k, -1, j, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
