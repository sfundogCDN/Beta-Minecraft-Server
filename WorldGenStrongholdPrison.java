// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, WorldGenStrongholdStairs2, StructureBoundingBox, StructurePiece, 
//            WorldGenStrongholdPieces, Block, WorldGenStrongholdDoorType, World

public class WorldGenStrongholdPrison extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdPrison(int i, Random random, StructureBoundingBox structureboundingbox, int j)
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

    public static WorldGenStrongholdPrison a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 9, 5, 11, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdPrison(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
        {
            return false;
        } else
        {
            a(world, structureboundingbox, 0, 0, 0, 8, 4, 10, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, random, structureboundingbox, a, 1, 1, 0);
            a(world, structureboundingbox, 1, 1, 10, 3, 3, 10, 0, 0, false);
            a(world, structureboundingbox, 4, 1, 1, 4, 3, 1, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, structureboundingbox, 4, 1, 3, 4, 3, 3, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, structureboundingbox, 4, 1, 7, 4, 3, 7, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, structureboundingbox, 4, 1, 9, 4, 3, 9, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, structureboundingbox, 4, 1, 4, 4, 3, 6, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
            a(world, structureboundingbox, 5, 1, 5, 7, 3, 5, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
            a(world, Block.IRON_FENCE.id, 0, 4, 3, 2, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, 4, 3, 8, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 2, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 2, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 8, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 8, structureboundingbox);
            return true;
        }
    }

    protected final WorldGenStrongholdDoorType a;
}
