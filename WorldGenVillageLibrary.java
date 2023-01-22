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

public class WorldGenVillageLibrary extends WorldGenVillagePiece
{

    public WorldGenVillageLibrary(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageLibrary a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 9, 6, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageLibrary(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 9) - 1, 0);
        }
        a(world, structureboundingbox, 1, 1, 1, 7, 5, 4, 0, 0, false);
        a(world, structureboundingbox, 0, 0, 0, 8, 0, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 5, 0, 8, 5, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 6, 1, 8, 6, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 7, 2, 8, 7, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        int i = c(Block.WOOD_STAIRS.id, 3);
        int j = c(Block.WOOD_STAIRS.id, 2);
        for(int k = -1; k <= 2; k++)
        {
            for(int i1 = 0; i1 <= 8; i1++)
            {
                a(world, Block.WOOD_STAIRS.id, i, i1, 6 + k, k, structureboundingbox);
                a(world, Block.WOOD_STAIRS.id, j, i1, 6 + k, 5 - k, structureboundingbox);
            }

        }

        a(world, structureboundingbox, 0, 1, 0, 0, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 1, 5, 8, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 8, 1, 0, 8, 1, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 2, 1, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 2, 0, 0, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 2, 5, 0, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 8, 2, 5, 8, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 8, 2, 0, 8, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 2, 1, 0, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 2, 5, 7, 4, 5, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 8, 2, 1, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 2, 0, 7, 4, 0, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.THIN_GLASS.id, 0, 4, 2, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 6, 2, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 3, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 3, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 6, 3, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 3, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 3, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 3, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 3, 3, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 5, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 3, 2, 5, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 2, 5, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 6, 2, 5, structureboundingbox);
        a(world, structureboundingbox, 1, 4, 1, 7, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 4, 4, 7, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 3, 4, 7, 3, 4, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
        a(world, Block.WOOD.id, 0, 7, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 0), 7, 1, 3, structureboundingbox);
        int l = c(Block.WOOD_STAIRS.id, 3);
        a(world, Block.WOOD_STAIRS.id, l, 6, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, l, 5, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, l, 4, 1, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, l, 3, 1, 4, structureboundingbox);
        a(world, Block.FENCE.id, 0, 6, 1, 3, structureboundingbox);
        a(world, Block.WOOD_PLATE.id, 0, 6, 2, 3, structureboundingbox);
        a(world, Block.FENCE.id, 0, 4, 1, 3, structureboundingbox);
        a(world, Block.WOOD_PLATE.id, 0, 4, 2, 3, structureboundingbox);
        a(world, Block.WORKBENCH.id, 0, 7, 1, 1, structureboundingbox);
        a(world, 0, 0, 1, 1, 0, structureboundingbox);
        a(world, 0, 0, 1, 2, 0, structureboundingbox);
        a(world, structureboundingbox, random, 1, 1, 0, c(Block.WOODEN_DOOR.id, 1));
        if(a(world, 1, 0, -1, structureboundingbox) == 0 && a(world, 1, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, structureboundingbox);
        for(int j1 = 0; j1 < 6; j1++)
        {
            for(int k1 = 0; k1 < 9; k1++)
            {
                b(world, k1, 9, j1, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, k1, -1, j1, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
