// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, WorldGenMineshaftPieces, World

public class WorldGenMineshaftStairs extends StructurePiece
{

    public WorldGenMineshaftStairs(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        g = structureboundingbox;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j - 5, k, i, j + 2, k);
        switch(l)
        {
        case 2: // '\002'
            structureboundingbox.d = i + 2;
            structureboundingbox.c = k - 8;
            break;

        case 0: // '\0'
            structureboundingbox.d = i + 2;
            structureboundingbox.f = k + 8;
            break;

        case 1: // '\001'
            structureboundingbox.a = i - 8;
            structureboundingbox.f = k + 2;
            break;

        case 3: // '\003'
            structureboundingbox.d = i + 8;
            structureboundingbox.f = k + 2;
            break;
        }
        if(StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        int i = c();
        switch(h)
        {
        case 2: // '\002'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, g.b, g.c - 1, 2, i);
            break;

        case 0: // '\0'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, g.b, g.f + 1, 0, i);
            break;

        case 1: // '\001'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b, g.c, 1, i);
            break;

        case 3: // '\003'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b, g.c, 3, i);
            break;
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 5, 0, 2, 7, 1, 0, 0, false);
        a(world, structureboundingbox, 0, 0, 7, 2, 2, 8, 0, 0, false);
        for(int i = 0; i < 5; i++)
            a(world, structureboundingbox, 0, 5 - i - (i >= 4 ? 0 : 1), 2 + i, 2, 7 - i, 2 + i, 0, 0, false);

        return true;
    }
}
