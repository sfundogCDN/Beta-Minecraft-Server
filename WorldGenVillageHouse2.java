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

public class WorldGenVillageHouse2 extends WorldGenVillagePiece
{

    public WorldGenVillageHouse2(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageHouse2 a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 12, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageHouse2(i1, random, structureboundingbox, l);
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
        a(world, structureboundingbox, 2, 0, 5, 8, 0, 10, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 8, 0, 0, 8, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 0, 0, 7, 2, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 0, 5, 2, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 2, 0, 6, 2, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 3, 0, 10, 7, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 2, 5, 2, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 4, 4, 3, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.WOOD.id, 0, 0, 4, 2, structureboundingbox);
        a(world, Block.WOOD.id, 0, 0, 4, 3, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 4, 2, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 4, 3, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 4, 4, structureboundingbox);
        int i = c(Block.WOOD_STAIRS.id, 3);
        int j = c(Block.WOOD_STAIRS.id, 2);
        for(int k = -1; k <= 2; k++)
        {
            for(int i1 = 0; i1 <= 8; i1++)
            {
                a(world, Block.WOOD_STAIRS.id, i, i1, 4 + k, k, structureboundingbox);
                if((k > -1 || i1 <= 1) && (k > 0 || i1 <= 3) && (k > 1 || i1 <= 4 || i1 >= 6))
                    a(world, Block.WOOD_STAIRS.id, j, i1, 4 + k, 5 - k, structureboundingbox);
            }

        }

        a(world, structureboundingbox, 3, 4, 5, 3, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 7, 4, 2, 7, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 4, 5, 4, 4, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 6, 5, 4, 6, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 5, 6, 3, 5, 6, 10, Block.WOOD.id, Block.WOOD.id, false);
        int l = c(Block.WOOD_STAIRS.id, 0);
        for(int j1 = 4; j1 >= 1; j1--)
        {
            a(world, Block.WOOD.id, 0, j1, 2 + j1, 7 - j1, structureboundingbox);
            for(int l1 = 8 - j1; l1 <= 10; l1++)
                a(world, Block.WOOD_STAIRS.id, l, j1, 2 + j1, l1, structureboundingbox);

        }

        int k1 = c(Block.WOOD_STAIRS.id, 1);
        a(world, Block.WOOD.id, 0, 6, 6, 3, structureboundingbox);
        a(world, Block.WOOD.id, 0, 7, 5, 4, structureboundingbox);
        a(world, Block.WOOD_STAIRS.id, k1, 6, 6, 4, structureboundingbox);
        for(int i2 = 6; i2 <= 8; i2++)
        {
            for(int l2 = 5; l2 <= 10; l2++)
                a(world, Block.WOOD_STAIRS.id, k1, i2, 12 - i2, l2, structureboundingbox);

        }

        a(world, Block.LOG.id, 0, 0, 2, 1, structureboundingbox);
        a(world, Block.LOG.id, 0, 0, 2, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 3, structureboundingbox);
        a(world, Block.LOG.id, 0, 4, 2, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 2, 0, structureboundingbox);
        a(world, Block.LOG.id, 0, 6, 2, 0, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 1, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 3, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 4, structureboundingbox);
        a(world, Block.WOOD.id, 0, 8, 2, 5, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 6, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 7, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 8, 2, 8, structureboundingbox);
        a(world, Block.LOG.id, 0, 8, 2, 9, structureboundingbox);
        a(world, Block.LOG.id, 0, 2, 2, 6, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 7, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 2, 8, structureboundingbox);
        a(world, Block.LOG.id, 0, 2, 2, 9, structureboundingbox);
        a(world, Block.LOG.id, 0, 4, 4, 10, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 5, 4, 10, structureboundingbox);
        a(world, Block.LOG.id, 0, 6, 4, 10, structureboundingbox);
        a(world, Block.WOOD.id, 0, 5, 5, 10, structureboundingbox);
        a(world, 0, 0, 2, 1, 0, structureboundingbox);
        a(world, 0, 0, 2, 2, 0, structureboundingbox);
        a(world, Block.TORCH.id, 0, 2, 3, 1, structureboundingbox);
        a(world, structureboundingbox, random, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
        a(world, structureboundingbox, 1, 0, -1, 3, 2, -1, 0, 0, false);
        if(a(world, 2, 0, -1, structureboundingbox) == 0 && a(world, 2, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        for(int j2 = 0; j2 < 5; j2++)
        {
            for(int i3 = 0; i3 < 9; i3++)
            {
                b(world, i3, 7, j2, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, i3, -1, j2, structureboundingbox);
            }

        }

        for(int k2 = 5; k2 < 11; k2++)
        {
            for(int j3 = 2; j3 < 9; j3++)
            {
                b(world, j3, 7, k2, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, j3, -1, k2, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
