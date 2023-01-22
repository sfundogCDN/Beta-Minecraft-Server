// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, WorldGenStrongholdPieceWeight2, WorldGenStrongholdDoorType, Block, 
//            StructureBoundingBox, WorldGenStrongholdPieces, World, WorldGenStrongholdStairs2

abstract class WorldGenStrongholdPiece extends StructurePiece
{

    protected WorldGenStrongholdPiece(int i)
    {
        super(i);
    }

    protected void a(World world, Random random, StructureBoundingBox structureboundingbox, WorldGenStrongholdDoorType worldgenstrongholddoortype, int i, int j, int k)
    {
        switch(WorldGenStrongholdPieceWeight2.a[worldgenstrongholddoortype.ordinal()])
        {
        case 1: // '\001'
        default:
            a(world, structureboundingbox, i, j, k, (i + 3) - 1, (j + 3) - 1, k, 0, 0, false);
            break;

        case 2: // '\002'
            a(world, Block.SMOOTH_BRICK.id, 0, i, j, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i, j + 1, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 1, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 1, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j, k, structureboundingbox);
            a(world, Block.WOODEN_DOOR.id, 0, i + 1, j, k, structureboundingbox);
            a(world, Block.WOODEN_DOOR.id, 8, i + 1, j + 1, k, structureboundingbox);
            break;

        case 3: // '\003'
            a(world, 0, 0, i + 1, j, k, structureboundingbox);
            a(world, 0, 0, i + 1, j + 1, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i, j, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i, j + 1, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i, j + 2, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i + 1, j + 2, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i + 2, j + 2, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i + 2, j + 1, k, structureboundingbox);
            a(world, Block.IRON_FENCE.id, 0, i + 2, j, k, structureboundingbox);
            break;

        case 4: // '\004'
            a(world, Block.SMOOTH_BRICK.id, 0, i, j, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i, j + 1, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 1, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 2, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j + 1, k, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, i + 2, j, k, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, 0, i + 1, j, k, structureboundingbox);
            a(world, Block.IRON_DOOR_BLOCK.id, 8, i + 1, j + 1, k, structureboundingbox);
            a(world, Block.STONE_BUTTON.id, c(Block.STONE_BUTTON.id, 4), i + 2, j + 1, k + 1, structureboundingbox);
            a(world, Block.STONE_BUTTON.id, c(Block.STONE_BUTTON.id, 3), i + 2, j + 1, k - 1, structureboundingbox);
            break;
        }
    }

    protected WorldGenStrongholdDoorType a(Random random)
    {
        int i = random.nextInt(5);
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
        default:
            return WorldGenStrongholdDoorType.a;

        case 2: // '\002'
            return WorldGenStrongholdDoorType.b;

        case 3: // '\003'
            return WorldGenStrongholdDoorType.c;

        case 4: // '\004'
            return WorldGenStrongholdDoorType.d;
        }
    }

    protected StructurePiece a(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(h)
        {
        case 2: // '\002'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + i, g.b + j, g.c - 1, h, c());

        case 0: // '\0'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + i, g.b + j, g.f + 1, h, c());

        case 1: // '\001'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a - 1, g.b + j, g.c + i, h, c());

        case 3: // '\003'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.d + 1, g.b + j, g.c + i, h, c());
        }
        return null;
    }

    protected StructurePiece b(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(h)
        {
        case 2: // '\002'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a - 1, g.b + i, g.c + j, 1, c());

        case 0: // '\0'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a - 1, g.b + i, g.c + j, 1, c());

        case 1: // '\001'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + j, g.b + i, g.c - 1, 2, c());

        case 3: // '\003'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + j, g.b + i, g.c - 1, 2, c());
        }
        return null;
    }

    protected StructurePiece c(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(h)
        {
        case 2: // '\002'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.d + 1, g.b + i, g.c + j, 3, c());

        case 0: // '\0'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.d + 1, g.b + i, g.c + j, 3, c());

        case 1: // '\001'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + j, g.b + i, g.f + 1, 0, c());

        case 3: // '\003'
            return WorldGenStrongholdPieces.a(worldgenstrongholdstairs2, list, random, g.a + j, g.b + i, g.f + 1, 0, c());
        }
        return null;
    }

    protected static boolean a(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
