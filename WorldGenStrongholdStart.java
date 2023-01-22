// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructureStart, WorldGenStrongholdPieces, WorldGenStrongholdStairs2, StructurePiece, 
//            World

class WorldGenStrongholdStart extends StructureStart
{

    public WorldGenStrongholdStart(World world, Random random, int i, int j)
    {
        WorldGenStrongholdPieces.a();
        WorldGenStrongholdStairs2 worldgenstrongholdstairs2 = new WorldGenStrongholdStairs2(0, random, (i << 4) + 2, (j << 4) + 2);
        a.add(worldgenstrongholdstairs2);
        worldgenstrongholdstairs2.a(worldgenstrongholdstairs2, a, random);
        StructurePiece structurepiece;
        for(ArrayList arraylist = worldgenstrongholdstairs2.b; !arraylist.isEmpty(); structurepiece.a(worldgenstrongholdstairs2, a, random))
        {
            int k = random.nextInt(arraylist.size());
            structurepiece = (StructurePiece)arraylist.remove(k);
        }

        c();
        a(world, random, 10);
    }
}
