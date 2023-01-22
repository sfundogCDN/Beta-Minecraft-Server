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

public class WorldGenStrongholdStraight extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdStraight(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
        b = random.nextInt(2) == 0;
        c = random.nextInt(2) == 0;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        a((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 1);
        if(b)
            b((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 2);
        if(c)
            c((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 2);
    }

    public static WorldGenStrongholdStraight a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdStraight(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 1, 1, 0);
        a(world, random, structureboundingbox, WorldGenStrongholdDoorType.a, 1, 1, 6);
        a(world, structureboundingbox, random, 0.1F, 1, 2, 1, Block.TORCH.id, 0);
        a(world, structureboundingbox, random, 0.1F, 3, 2, 1, Block.TORCH.id, 0);
        a(world, structureboundingbox, random, 0.1F, 1, 2, 5, Block.TORCH.id, 0);
        a(world, structureboundingbox, random, 0.1F, 3, 2, 5, Block.TORCH.id, 0);
        if(b)
            a(world, structureboundingbox, 0, 1, 2, 0, 3, 4, 0, 0, false);
        if(c)
            a(world, structureboundingbox, 4, 1, 2, 4, 3, 4, 0, 0, false);
        return true;
    }

    private final WorldGenStrongholdDoorType a;
    private final boolean b;
    private final boolean c;
}
