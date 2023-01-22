// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillageRoadPiece, StructureBoundingBox, WorldGenVillageStartPiece, StructurePiece, 
//            WorldGenVillagePieces, MathHelper, World, Block

public class WorldGenVillageRoad extends WorldGenVillageRoadPiece
{

    public WorldGenVillageRoad(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        g = structureboundingbox;
        a = Math.max(structureboundingbox.b(), structureboundingbox.d());
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        boolean flag = false;
        for(int i = random.nextInt(5); i < a - 8; i += 2 + random.nextInt(5))
        {
            StructurePiece structurepiece1 = a((WorldGenVillageStartPiece)structurepiece, list, random, 0, i);
            if(structurepiece1 != null)
            {
                i += Math.max(structurepiece1.g.b(), structurepiece1.g.d());
                flag = true;
            }
        }

        for(int j = random.nextInt(5); j < a - 8; j += 2 + random.nextInt(5))
        {
            StructurePiece structurepiece2 = b((WorldGenVillageStartPiece)structurepiece, list, random, 0, j);
            if(structurepiece2 != null)
            {
                j += Math.max(structurepiece2.g.b(), structurepiece2.g.d());
                flag = true;
            }
        }

        if(flag && random.nextInt(3) > 0)
            switch(h)
            {
            case 2: // '\002'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a - 1, g.b, g.c, 1, c());
                break;

            case 0: // '\0'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a - 1, g.b, g.f - 2, 1, c());
                break;

            case 3: // '\003'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.d - 2, g.b, g.c - 1, 2, c());
                break;

            case 1: // '\001'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a, g.b, g.c - 1, 2, c());
                break;
            }
        if(flag && random.nextInt(3) > 0)
            switch(h)
            {
            case 2: // '\002'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.d + 1, g.b, g.c, 3, c());
                break;

            case 0: // '\0'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.d + 1, g.b, g.f - 2, 3, c());
                break;

            case 3: // '\003'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.d - 2, g.b, g.f + 1, 0, c());
                break;

            case 1: // '\001'
                WorldGenVillagePieces.b((WorldGenVillageStartPiece)structurepiece, list, random, g.a, g.b, g.f + 1, 0, c());
                break;
            }
    }

    public static StructureBoundingBox a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l)
    {
        for(int i1 = 7 * MathHelper.a(random, 3, 5); i1 >= 7; i1 -= 7)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 3, i1, l);
            if(StructurePiece.a(list, structureboundingbox) == null)
                return structureboundingbox;
        }

        return null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        for(int i = g.a; i <= g.d; i++)
        {
            for(int j = g.c; j <= g.f; j++)
                if(structureboundingbox.b(i, 64, j))
                {
                    int k = world.f(i, j) - 1;
                    world.setRawTypeId(i, k, j, Block.GRAVEL.id);
                }

        }

        return true;
    }

    private int a;
}
