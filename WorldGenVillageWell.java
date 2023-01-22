// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillagePiece, StructureBoundingBox, WorldGenVillageStartPiece, WorldGenVillagePieces, 
//            Block, StructurePiece, World

public class WorldGenVillageWell extends WorldGenVillagePiece
{

    public WorldGenVillageWell(int i, Random random, int j, int k)
    {
        super(i);
        b = -1;
        h = random.nextInt(4);
        switch(h)
        {
        case 0: // '\0'
        case 2: // '\002'
            g = new StructureBoundingBox(j, 64, k, (j + 6) - 1, 78, (k + 6) - 1);
            break;

        default:
            g = new StructureBoundingBox(j, 64, k, (j + 6) - 1, 78, (k + 6) - 1);
            break;
        }
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a - 1, g.e - 4, g.c + 1, 1, c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.d + 1, g.e - 4, g.c + 1, 3, c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a + 1, g.e - 4, g.c - 1, 2, c());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a + 1, g.e - 4, g.f + 1, 0, c());
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(b < 0)
        {
            b = b(world, structureboundingbox);
            if(b < 0)
                return true;
            g.a(0, (b - g.e) + 3, 0);
        }
        if(!a);
        a(world, structureboundingbox, 1, 0, 1, 4, 12, 4, Block.COBBLESTONE.id, Block.WATER.id, false);
        a(world, 0, 0, 2, 12, 2, structureboundingbox);
        a(world, 0, 0, 3, 12, 2, structureboundingbox);
        a(world, 0, 0, 2, 12, 3, structureboundingbox);
        a(world, 0, 0, 3, 12, 3, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 13, 1, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 14, 1, structureboundingbox);
        a(world, Block.FENCE.id, 0, 4, 13, 1, structureboundingbox);
        a(world, Block.FENCE.id, 0, 4, 14, 1, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 13, 4, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 14, 4, structureboundingbox);
        a(world, Block.FENCE.id, 0, 4, 13, 4, structureboundingbox);
        a(world, Block.FENCE.id, 0, 4, 14, 4, structureboundingbox);
        a(world, structureboundingbox, 1, 15, 1, 4, 15, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        for(int i = 0; i <= 5; i++)
        {
            for(int j = 0; j <= 5; j++)
                if(j == 0 || j == 5 || i == 0 || i == 5)
                {
                    a(world, Block.GRAVEL.id, 0, j, 11, i, structureboundingbox);
                    b(world, j, 12, i, structureboundingbox);
                }

        }

        return true;
    }

    private final boolean a = true;
    private int b;
}
