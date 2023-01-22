// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, WorldGenMineshaftPieces, Block, 
//            World

public class WorldGenMineshaftCross extends StructurePiece
{

    public WorldGenMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = j;
        g = structureboundingbox;
        b = structureboundingbox.c() > 3;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);
        if(random.nextInt(4) == 0)
            structureboundingbox.e += 4;
        switch(l)
        {
        case 2: // '\002'
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.c = k - 4;
            break;

        case 0: // '\0'
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.f = k + 4;
            break;

        case 1: // '\001'
            structureboundingbox.a = i - 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
            break;

        case 3: // '\003'
            structureboundingbox.d = i + 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
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
        switch(a)
        {
        case 2: // '\002'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b, g.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b, g.c + 1, 3, i);
            break;

        case 0: // '\0'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b, g.c + 1, 1, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b, g.c + 1, 3, i);
            break;

        case 1: // '\001'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b, g.c + 1, 1, i);
            break;

        case 3: // '\003'
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.c - 1, 2, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b, g.f + 1, 0, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b, g.c + 1, 3, i);
            break;
        }
        if(b)
        {
            if(random.nextBoolean())
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b + 3 + 1, g.c - 1, 2, i);
            if(random.nextBoolean())
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b + 3 + 1, g.c + 1, 1, i);
            if(random.nextBoolean())
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b + 3 + 1, g.c + 1, 3, i);
            if(random.nextBoolean())
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + 1, g.b + 3 + 1, g.f + 1, 0, i);
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        if(b)
        {
            a(world, structureboundingbox, g.a + 1, g.b, g.c, g.d - 1, (g.b + 3) - 1, g.f, 0, 0, false);
            a(world, structureboundingbox, g.a, g.b, g.c + 1, g.d, (g.b + 3) - 1, g.f - 1, 0, 0, false);
            a(world, structureboundingbox, g.a + 1, g.e - 2, g.c, g.d - 1, g.e, g.f, 0, 0, false);
            a(world, structureboundingbox, g.a, g.e - 2, g.c + 1, g.d, g.e, g.f - 1, 0, 0, false);
            a(world, structureboundingbox, g.a + 1, g.b + 3, g.c + 1, g.d - 1, g.b + 3, g.f - 1, 0, 0, false);
        } else
        {
            a(world, structureboundingbox, g.a + 1, g.b, g.c, g.d - 1, g.e, g.f, 0, 0, false);
            a(world, structureboundingbox, g.a, g.b, g.c + 1, g.d, g.e, g.f - 1, 0, 0, false);
        }
        a(world, structureboundingbox, g.a + 1, g.b, g.c + 1, g.a + 1, g.e, g.c + 1, Block.WOOD.id, 0, false);
        a(world, structureboundingbox, g.a + 1, g.b, g.f - 1, g.a + 1, g.e, g.f - 1, Block.WOOD.id, 0, false);
        a(world, structureboundingbox, g.d - 1, g.b, g.c + 1, g.d - 1, g.e, g.c + 1, Block.WOOD.id, 0, false);
        a(world, structureboundingbox, g.d - 1, g.b, g.f - 1, g.d - 1, g.e, g.f - 1, Block.WOOD.id, 0, false);
        return true;
    }

    private final int a;
    private final boolean b;
}
