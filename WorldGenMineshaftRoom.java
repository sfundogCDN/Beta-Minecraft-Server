// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, WorldGenMineshaftPieces, Block, 
//            World

public class WorldGenMineshaftRoom extends StructurePiece
{

    public WorldGenMineshaftRoom(int i, Random random, int j, int k)
    {
        super(i);
        a = new LinkedList();
        g = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        int i = c();
        int j = g.c() - 3 - 1;
        if(j <= 0)
            j = 1;
        for(int k = 0; k < g.b(); k += 4)
        {
            k += random.nextInt(g.b());
            if(k + 3 > g.b())
                break;
            StructurePiece structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + k, g.b + random.nextInt(j) + 1, g.c - 1, 2, i);
            if(structurepiece1 != null)
            {
                StructureBoundingBox structureboundingbox = structurepiece1.b();
                a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, g.c, structureboundingbox.d, structureboundingbox.e, g.c + 1));
            }
        }

        for(int l = 0; l < g.b(); l += 4)
        {
            l += random.nextInt(g.b());
            if(l + 3 > g.b())
                break;
            StructurePiece structurepiece2 = WorldGenMineshaftPieces.a(structurepiece, list, random, g.a + l, g.b + random.nextInt(j) + 1, g.f + 1, 0, i);
            if(structurepiece2 != null)
            {
                StructureBoundingBox structureboundingbox1 = structurepiece2.b();
                a.add(new StructureBoundingBox(structureboundingbox1.a, structureboundingbox1.b, g.f - 1, structureboundingbox1.d, structureboundingbox1.e, g.f));
            }
        }

        for(int i1 = 0; i1 < g.d(); i1 += 4)
        {
            i1 += random.nextInt(g.d());
            if(i1 + 3 > g.d())
                break;
            StructurePiece structurepiece3 = WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b + random.nextInt(j) + 1, g.c + i1, 1, i);
            if(structurepiece3 != null)
            {
                StructureBoundingBox structureboundingbox2 = structurepiece3.b();
                a.add(new StructureBoundingBox(g.a, structureboundingbox2.b, structureboundingbox2.c, g.a + 1, structureboundingbox2.e, structureboundingbox2.f));
            }
        }

        for(int j1 = 0; j1 < g.d(); j1 += 4)
        {
            j1 += random.nextInt(g.d());
            if(j1 + 3 > g.d())
                break;
            StructurePiece structurepiece4 = WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b + random.nextInt(j) + 1, g.c + j1, 3, i);
            if(structurepiece4 != null)
            {
                StructureBoundingBox structureboundingbox3 = structurepiece4.b();
                a.add(new StructureBoundingBox(g.d - 1, structureboundingbox3.b, structureboundingbox3.c, g.d, structureboundingbox3.e, structureboundingbox3.f));
            }
        }

    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, g.a, g.b, g.c, g.d, g.b, g.f, Block.DIRT.id, 0, true);
        a(world, structureboundingbox, g.a, g.b + 1, g.c, g.d, Math.min(g.b + 3, g.e), g.f, 0, 0, false);
        StructureBoundingBox structureboundingbox1;
        for(Iterator iterator = a.iterator(); iterator.hasNext(); a(world, structureboundingbox, structureboundingbox1.a, structureboundingbox1.e - 2, structureboundingbox1.c, structureboundingbox1.d, structureboundingbox1.e, structureboundingbox1.f, 0, 0, false))
            structureboundingbox1 = (StructureBoundingBox)iterator.next();

        a(world, structureboundingbox, g.a, g.b + 4, g.c, g.d, g.e, g.f, 0, false);
        return true;
    }

    private LinkedList a;
}
