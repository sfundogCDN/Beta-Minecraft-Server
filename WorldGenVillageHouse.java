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

public class WorldGenVillageHouse extends WorldGenVillagePiece
{

    public WorldGenVillageHouse(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
        b = random.nextBoolean();
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageHouse a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 6, 5, l);
        if(StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageHouse(i1, random, structureboundingbox, l);
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
        a(world, structureboundingbox, 0, 0, 0, 4, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 1, 4, 1, 3, 4, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.COBBLESTONE.id, 0, 0, 1, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 0, 2, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 0, 3, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 1, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 2, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 3, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 0, 1, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 0, 2, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 0, 3, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 1, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 2, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 3, 4, structureboundingbox);
        a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 1, 4, 3, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 2, 2, structureboundingbox);
        a(world, Block.WOOD.id, 0, 1, 1, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 1, 2, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 1, 3, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 2, 3, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 3, 3, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 3, 2, 0, structureboundingbox);
        a(world, Block.WOOD.id, 0, 3, 1, 0, structureboundingbox);
        if(a(world, 2, 0, -1, structureboundingbox) == 0 && a(world, 2, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        a(world, structureboundingbox, 1, 1, 1, 3, 3, 3, 0, 0, false);
        if(b)
        {
            a(world, Block.FENCE.id, 0, 0, 5, 0, structureboundingbox);
            a(world, Block.FENCE.id, 0, 1, 5, 0, structureboundingbox);
            a(world, Block.FENCE.id, 0, 2, 5, 0, structureboundingbox);
            a(world, Block.FENCE.id, 0, 3, 5, 0, structureboundingbox);
            a(world, Block.FENCE.id, 0, 4, 5, 0, structureboundingbox);
            a(world, Block.FENCE.id, 0, 0, 5, 4, structureboundingbox);
            a(world, Block.FENCE.id, 0, 1, 5, 4, structureboundingbox);
            a(world, Block.FENCE.id, 0, 2, 5, 4, structureboundingbox);
            a(world, Block.FENCE.id, 0, 3, 5, 4, structureboundingbox);
            a(world, Block.FENCE.id, 0, 4, 5, 4, structureboundingbox);
            a(world, Block.FENCE.id, 0, 4, 5, 1, structureboundingbox);
            a(world, Block.FENCE.id, 0, 4, 5, 2, structureboundingbox);
            a(world, Block.FENCE.id, 0, 4, 5, 3, structureboundingbox);
            a(world, Block.FENCE.id, 0, 0, 5, 1, structureboundingbox);
            a(world, Block.FENCE.id, 0, 0, 5, 2, structureboundingbox);
            a(world, Block.FENCE.id, 0, 0, 5, 3, structureboundingbox);
        }
        if(b)
        {
            int i = c(Block.LADDER.id, 3);
            a(world, Block.LADDER.id, i, 3, 1, 3, structureboundingbox);
            a(world, Block.LADDER.id, i, 3, 2, 3, structureboundingbox);
            a(world, Block.LADDER.id, i, 3, 3, 3, structureboundingbox);
            a(world, Block.LADDER.id, i, 3, 4, 3, structureboundingbox);
        }
        a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);
        for(int j = 0; j < 5; j++)
        {
            for(int k = 0; k < 5; k++)
            {
                b(world, k, 6, j, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, k, -1, j, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
    private final boolean b;
}
