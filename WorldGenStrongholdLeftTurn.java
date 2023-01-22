// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, WorldGenStrongholdStairs2, StructureBoundingBox, StructurePiece, 
//            WorldGenStrongholdPieces, WorldGenStrongholdDoorType, World

public class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdLeftTurn(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        if(h == 2 || h == 3)
            b((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 1);
        else
            c((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdLeftTurn a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 5, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdLeftTurn(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 1, 1, 0);
        if(h == 2 || h == 3)
            a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, 0, 0, false);
        else
            a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, 0, 0, false);
        return true;
    }

    protected final WorldGenStrongholdDoorType a;
}
