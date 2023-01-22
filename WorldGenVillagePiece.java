// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, WorldGenVillagePieces, World, 
//            WorldGenVillageStartPiece

abstract class WorldGenVillagePiece extends StructurePiece
{

    protected WorldGenVillagePiece(int i)
    {
        super(i);
    }

    protected StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(h)
        {
        case 2: // '\002'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a - 1, g.b + i, g.c + j, 1, c());

        case 0: // '\0'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a - 1, g.b + i, g.c + j, 1, c());

        case 1: // '\001'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a + j, g.b + i, g.c - 1, 2, c());

        case 3: // '\003'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a + j, g.b + i, g.c - 1, 2, c());
        }
        return null;
    }

    protected StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(h)
        {
        case 2: // '\002'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.d + 1, g.b + i, g.c + j, 3, c());

        case 0: // '\0'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.d + 1, g.b + i, g.c + j, 3, c());

        case 1: // '\001'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a + j, g.b + i, g.f + 1, 0, c());

        case 3: // '\003'
            return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, g.a + j, g.b + i, g.f + 1, 0, c());
        }
        return null;
    }

    protected int b(World world, StructureBoundingBox structureboundingbox)
    {
        int i = 0;
        int j = 0;
        for(int k = g.c; k <= g.f; k++)
        {
            for(int l = g.a; l <= g.d; l++)
                if(structureboundingbox.b(l, 64, k))
                {
                    world.getClass();
                    i += Math.max(world.f(l, k), 63);
                    j++;
                }

        }

        if(j == 0)
            return -1;
        else
            return i / j;
    }

    protected static boolean a(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
