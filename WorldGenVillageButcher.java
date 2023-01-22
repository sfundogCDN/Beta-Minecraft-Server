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

public class WorldGenVillageButcher extends WorldGenVillagePiece
{

    public WorldGenVillageButcher(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageButcher a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 11, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageButcher(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 7) - 1, 0);
        }
        a(world, structureboundingbox, 1, 1, 1, 7, 4, 4, 0, 0, false);
        a(world, structureboundingbox, 2, 1, 6, 8, 4, 10, 0, 0, false);
        a(world, structureboundingbox, 2, 0, 6, 8, 0, 10, Block.DIRT.id, Block.DIRT.id, false);
        a(world, Block.COBBLESTONE.id, 0, 6, 0, 6, structureboundingbox);
        a(world, structureboundingbox, 2, 1, 6, 2, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        a(world, structureboundingbox, 8, 1, 6, 8, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        a(world, structureboundingbox, 3, 1, 10, 7, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
        a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 8, 0, 0, 8, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 0, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 0, 5, 7, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 2, 5, 7, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 4, 4, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.WOOD.id, 0, 0, 4, 2, structureboundingbox);
        a(world, Block.WOOD.id, 0, 0, 4, 3, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 4, 2, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 4, 3, structureboundingbox);
        int i = c(Block.WOOD_STAIRS.id, 3);
        int j = c(Block.WOOD_STAIRS.id, 2);
        for(int k = -1; k <= 2; k++)
        {
            for(int i1 = 0; i1 <= 8; i1++)
            {
                a(world, Block.WOOD_STAIRS.id, i, i1, 4 + k, k, structureboundingbox);
                a(world, Block.WOOD_STAIRS.id, j, i1, 4 + k, 5 - k, structureboundingbox);
            }

        }

        a(world, Block.LOG.id, 0, 0, 2, 1, structureboundingbox);
        a(world, Block.LOG.id, 0, 0, 2, 4, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 1, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 5, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 3, 2, 5, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 6, 2, 5, structureboundingbox);
        a(world, Block.FENCE.id, 0, 2, 1, 3, structureboundingbox);
        a(world, Block.WOOD_PLATE.id, 0, 2, 2, 3, structureboundingbox);
        a(world, Block.WOOD.id, 0, 1, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 3), 2, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 1), 1, 1, 3, structureboundingbox);
        a(world, structureboundingbox, 5, 0, 1, 7, 0, 3, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
        a(world, Block.DOUBLE_STEP.id, 0, 6, 1, 1, structureboundingbox);
        a(world, Block.DOUBLE_STEP.id, 0, 6, 1, 2, structureboundingbox);
        a(world, 0, 0, 2, 1, 0, structureboundingbox);
        a(world, 0, 0, 2, 2, 0, structureboundingbox);
        a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);
        a(world, structureboundingbox, random, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
        if(a(world, 2, 0, -1, structureboundingbox) == 0 && a(world, 2, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        a(world, 0, 0, 6, 1, 5, structureboundingbox);
        a(world, 0, 0, 6, 2, 5, structureboundingbox);
        a(world, Block.TORCH.id, 0, 6, 3, 4, structureboundingbox);
        a(world, structureboundingbox, random, 6, 1, 5, c(Block.WOODEN_DOOR.id, 1));
        for(int l = 0; l < 5; l++)
        {
            for(int j1 = 0; j1 < 9; j1++)
            {
                b(world, j1, 7, l, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, j1, -1, l, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
