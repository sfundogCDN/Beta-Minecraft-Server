// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, StructureBoundingBox, StructurePiece, Block, 
//            World

public class WorldGenStrongholdCorridor extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        g = structureboundingbox;
        a = j != 2 && j != 0 ? structureboundingbox.b() : structureboundingbox.d();
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 4, l);
        StructurePiece structurepiece = StructurePiece.a(list, structureboundingbox);
        if(structurepiece == null)
            return null;
        if(structurepiece.b().b == structureboundingbox.b)
        {
            for(int i1 = 3; i1 >= 1; i1--)
            {
                StructureBoundingBox structureboundingbox1 = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, i1 - 1, l);
                if(!structurepiece.b().a(structureboundingbox1))
                    return StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, i1, l);
            }

        }
        return null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        for(int i = 0; i < a; i++)
        {
            a(world, Block.SMOOTH_BRICK.id, 0, 0, 0, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 0, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 2, 0, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 3, 0, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 4, 0, i, structureboundingbox);
            for(int j = 1; j <= 3; j++)
            {
                a(world, Block.SMOOTH_BRICK.id, 0, 0, j, i, structureboundingbox);
                a(world, 0, 0, 1, j, i, structureboundingbox);
                a(world, 0, 0, 2, j, i, structureboundingbox);
                a(world, 0, 0, 3, j, i, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 4, j, i, structureboundingbox);
            }

            a(world, Block.SMOOTH_BRICK.id, 0, 0, 4, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 1, 4, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 2, 4, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 3, 4, i, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 4, 4, i, structureboundingbox);
        }

        return true;
    }

    private final int a;
}
