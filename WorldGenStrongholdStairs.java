// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, WorldGenStrongholdDoorType, StructureBoundingBox, WorldGenStrongholdStairs2, 
//            StructurePiece, WorldGenStrongholdPieces, Block, World

public class WorldGenStrongholdStairs extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdStairs(int i, Random random, int j, int k)
    {
        super(i);
        a = true;
        h = random.nextInt(4);
        b = WorldGenStrongholdDoorType.a;
        switch(h)
        {
        case 0: // '\0'
        case 2: // '\002'
            g = new StructureBoundingBox(j, 64, k, (j + 5) - 1, 74, (k + 5) - 1);
            break;

        default:
            g = new StructureBoundingBox(j, 64, k, (j + 5) - 1, 74, (k + 5) - 1);
            break;
        }
    }

    public WorldGenStrongholdStairs(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = false;
        h = j;
        b = a(random);
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        a((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdStairs a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 5, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdStairs(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
        {
            return false;
        } else
        {
            if(!a);
            a(world, structureboundingbox, 0, 0, 0, 4, 10, 4, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
            a(world, random, structureboundingbox, b, 1, 7, 0);
            a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 4);
            a(world, Block.SMOOTH_BRICK.id, 0, 2, 6, 1, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 5, 1, structureboundingbox);
            a(world, Block.STEP.id, 0, 1, 6, 1, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 5, 2, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 4, 3, structureboundingbox);
            a(world, Block.STEP.id, 0, 1, 5, 3, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 2, 4, 3, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 3, 3, 3, structureboundingbox);
            a(world, Block.STEP.id, 0, 3, 4, 3, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 3, 3, 2, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 3, 2, 1, structureboundingbox);
            a(world, Block.STEP.id, 0, 3, 3, 1, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 2, 2, 1, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 1, 1, structureboundingbox);
            a(world, Block.STEP.id, 0, 1, 2, 1, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 1, 2, structureboundingbox);
            a(world, Block.STEP.id, 0, 1, 1, 3, structureboundingbox);
            return true;
        }
    }

    private final boolean a;
    private final WorldGenStrongholdDoorType b;
}
