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

public class WorldGenVillageTemple extends WorldGenVillagePiece
{

    public WorldGenVillageTemple(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageTemple a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 12, 9, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageTemple(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 12) - 1, 0);
        }
        a(world, structureboundingbox, 1, 1, 1, 3, 3, 7, 0, 0, false);
        a(world, structureboundingbox, 1, 5, 1, 3, 9, 3, 0, 0, false);
        a(world, structureboundingbox, 1, 0, 0, 3, 0, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 1, 0, 3, 10, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 1, 1, 0, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 4, 1, 1, 4, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 0, 4, 0, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 4, 0, 4, 4, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 1, 8, 3, 4, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 5, 4, 3, 10, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 5, 5, 3, 5, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 9, 0, 4, 9, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, Block.COBBLESTONE.id, 0, 0, 11, 2, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 4, 11, 2, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 2, 11, 0, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 2, 11, 4, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 1, 1, 6, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 1, 1, 7, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 2, 1, 7, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 3, 1, 6, structureboundingbox);
        a(world, Block.COBBLESTONE.id, 0, 3, 1, 7, structureboundingbox);
        a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 1, 5, structureboundingbox);
        a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 1, 6, structureboundingbox);
        a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 3, 1, 5, structureboundingbox);
        a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 1), 1, 2, 7, structureboundingbox);
        a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 0), 3, 2, 7, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 3, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 3, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 6, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 7, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 6, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 7, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 6, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 7, 0, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 6, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 7, 4, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 0, 3, 6, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 4, 3, 6, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 2, 3, 8, structureboundingbox);
        a(world, Block.TORCH.id, 0, 2, 4, 7, structureboundingbox);
        a(world, Block.TORCH.id, 0, 1, 4, 6, structureboundingbox);
        a(world, Block.TORCH.id, 0, 3, 4, 6, structureboundingbox);
        a(world, Block.TORCH.id, 0, 2, 4, 5, structureboundingbox);
        int i = c(Block.LADDER.id, 4);
        for(int j = 1; j <= 9; j++)
            a(world, Block.LADDER.id, i, 3, j, 3, structureboundingbox);

        a(world, 0, 0, 2, 1, 0, structureboundingbox);
        a(world, 0, 0, 2, 2, 0, structureboundingbox);
        a(world, structureboundingbox, random, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
        if(a(world, 2, 0, -1, structureboundingbox) == 0 && a(world, 2, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, structureboundingbox);
        for(int k = 0; k < 9; k++)
        {
            for(int l = 0; l < 5; l++)
            {
                b(world, l, 12, k, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, l, -1, k, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
}
