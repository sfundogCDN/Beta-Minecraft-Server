// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, WorldGenStrongholdStairs2, StructureBoundingBox, StructurePiece, 
//            WorldGenStrongholdPieces, WorldGenStrongholdDoorType, Block, World

public class WorldGenStrongholdStairsStraight extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdStairsStraight(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        a((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdStairsStraight a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 8, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdStairsStraight(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 0, 0, 4, 10, 7, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 1, 7, 0);
        a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 7);
        int i = c(Block.COBBLESTONE_STAIRS.id, 2);
        for(int j = 0; j < 6; j++)
        {
            a(world, Block.COBBLESTONE_STAIRS.id, i, 1, 6 - j, 1 + j, structureboundingbox);
            a(world, Block.COBBLESTONE_STAIRS.id, i, 2, 6 - j, 1 + j, structureboundingbox);
            a(world, Block.COBBLESTONE_STAIRS.id, i, 3, 6 - j, 1 + j, structureboundingbox);
            if(j < 5)
            {
                a(world, Block.SMOOTH_BRICK.id, 0, 1, 5 - j, 1 + j, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 2, 5 - j, 1 + j, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 3, 5 - j, 1 + j, structureboundingbox);
            }
        }

        return true;
    }

    private final WorldGenStrongholdDoorType a;
}
