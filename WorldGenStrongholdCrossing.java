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

public class WorldGenStrongholdCrossing extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
        b = random.nextBoolean();
        c = random.nextBoolean();
        d = random.nextBoolean();
        e = random.nextBoolean();
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        a((WorldGenStrongholdStairs2)structurepiece, list, random, 5, 1);
        if(b)
            b((WorldGenStrongholdStairs2)structurepiece, list, random, 3, 1);
        if(c)
            b((WorldGenStrongholdStairs2)structurepiece, list, random, 5, 7);
        if(d)
            c((WorldGenStrongholdStairs2)structurepiece, list, random, 3, 1);
        if(e)
            c((WorldGenStrongholdStairs2)structurepiece, list, random, 5, 7);
    }

    public static WorldGenStrongholdCrossing a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -3, 0, 10, 9, 11, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdCrossing(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 0, 0, 9, 8, 10, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 4, 3, 0);
        if(b)
            a(world, structureboundingbox, 0, 3, 1, 0, 5, 3, 0, 0, false);
        if(d)
            a(world, structureboundingbox, 9, 3, 1, 9, 5, 3, 0, 0, false);
        if(c)
            a(world, structureboundingbox, 0, 5, 7, 0, 7, 9, 0, 0, false);
        if(e)
            a(world, structureboundingbox, 9, 5, 7, 9, 7, 9, 0, 0, false);
        a(world, structureboundingbox, 5, 1, 10, 7, 3, 10, 0, 0, false);
        a(world, structureboundingbox, 1, 2, 1, 8, 2, 6, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 4, 1, 5, 4, 4, 9, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 8, 1, 5, 8, 4, 9, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 1, 4, 7, 3, 4, 9, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 1, 3, 5, 3, 3, 6, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 1, 3, 4, 3, 3, 4, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 1, 4, 6, 3, 4, 6, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 5, 1, 7, 7, 1, 8, false, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, structureboundingbox, 5, 1, 9, 7, 1, 9, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 5, 2, 7, 7, 2, 7, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 4, 5, 7, 4, 5, 9, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 8, 5, 7, 8, 5, 9, Block.STEP.id, Block.STEP.id, false);
        a(world, structureboundingbox, 5, 5, 7, 7, 5, 9, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
        a(world, Block.TORCH.id, 0, 6, 5, 6, structureboundingbox);
        return true;
    }

    protected final WorldGenStrongholdDoorType a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
}
