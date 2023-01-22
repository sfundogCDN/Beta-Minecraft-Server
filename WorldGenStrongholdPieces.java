// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPieceWeight, WorldGenStrongholdStraight, WorldGenStrongholdPrison, WorldGenStrongholdLeftTurn, 
//            WorldGenStrongholdRightTurn, WorldGenStrongholdRoomCrossing, WorldGenStrongholdStairsStraight, WorldGenStrongholdStairs, 
//            WorldGenStrongholdCrossing, WorldGenStrongholdLibrary, WorldGenStrongholdStairs2, WorldGenStrongholdCorridor, 
//            StructureBoundingBox, WorldGenStrongholdUnknown, WorldGenStrongholdStones, WorldGenStrongholdPiece, 
//            StructurePiece

public class WorldGenStrongholdPieces
{

    public WorldGenStrongholdPieces()
    {
    }

    public static void a()
    {
        c = new ArrayList();
        WorldGenStrongholdPieceWeight aworldgenstrongholdpieceweight[] = b;
        int i = aworldgenstrongholdpieceweight.length;
        for(int j = 0; j < i; j++)
        {
            WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = aworldgenstrongholdpieceweight[j];
            worldgenstrongholdpieceweight.c = 0;
            c.add(worldgenstrongholdpieceweight);
        }

    }

    private static boolean c()
    {
        boolean flag = false;
        a = 0;
        for(Iterator iterator = c.iterator(); iterator.hasNext();)
        {
            WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight)iterator.next();
            if(worldgenstrongholdpieceweight.d > 0 && worldgenstrongholdpieceweight.c < worldgenstrongholdpieceweight.d)
                flag = true;
            a += worldgenstrongholdpieceweight.b;
        }

        return flag;
    }

    private static WorldGenStrongholdPiece a(WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Class class1 = worldgenstrongholdpieceweight.a;
        Object obj = null;
        if(class1 == net/minecraft/server/WorldGenStrongholdStraight)
            obj = WorldGenStrongholdStraight.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdPrison)
            obj = WorldGenStrongholdPrison.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdLeftTurn)
            obj = WorldGenStrongholdLeftTurn.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdRightTurn)
            obj = WorldGenStrongholdRightTurn.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdRoomCrossing)
            obj = WorldGenStrongholdRoomCrossing.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdStairsStraight)
            obj = WorldGenStrongholdStairsStraight.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdStairs)
            obj = WorldGenStrongholdStairs.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdCrossing)
            obj = WorldGenStrongholdCrossing.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenStrongholdLibrary)
            obj = WorldGenStrongholdLibrary.a(list, random, i, j, k, l, i1);
        return ((WorldGenStrongholdPiece) (obj));
    }

    private static WorldGenStrongholdPiece b(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        int j1;
        if(!c())
            return null;
        j1 = 0;
_L2:
        int k1;
        Iterator iterator;
        if(j1 >= 5)
            break MISSING_BLOCK_LABEL_166;
        j1++;
        k1 = random.nextInt(a);
        iterator = c.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight;
        worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight)iterator.next();
        k1 -= worldgenstrongholdpieceweight.b;
        if(k1 >= 0) goto _L4; else goto _L3
_L3:
        if(worldgenstrongholdpieceweight.a(i1) && worldgenstrongholdpieceweight != worldgenstrongholdstairs2.a) goto _L5; else goto _L2
_L5:
        WorldGenStrongholdPiece worldgenstrongholdpiece = a(worldgenstrongholdpieceweight, list, random, i, j, k, l, i1);
        if(worldgenstrongholdpiece == null) goto _L4; else goto _L6
_L6:
        worldgenstrongholdpieceweight.c++;
        worldgenstrongholdstairs2.a = worldgenstrongholdpieceweight;
        if(!worldgenstrongholdpieceweight.a())
            c.remove(worldgenstrongholdpieceweight);
        return worldgenstrongholdpiece;
        StructureBoundingBox structureboundingbox = WorldGenStrongholdCorridor.a(list, random, i, j, k, l);
        if(structureboundingbox != null && structureboundingbox.b > 1)
            return new WorldGenStrongholdCorridor(i1, random, structureboundingbox, l);
        else
            return null;
    }

    private static StructurePiece c(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 50)
            return null;
        if(Math.abs(i - worldgenstrongholdstairs2.b().a) > 112 || Math.abs(k - worldgenstrongholdstairs2.b().c) > 112)
            return null;
        WorldGenStrongholdPiece worldgenstrongholdpiece = b(worldgenstrongholdstairs2, list, random, i, j, k, l, i1 + 1);
        if(worldgenstrongholdpiece != null)
        {
            list.add(worldgenstrongholdpiece);
            worldgenstrongholdstairs2.b.add(worldgenstrongholdpiece);
        }
        return worldgenstrongholdpiece;
    }

    static StructurePiece a(WorldGenStrongholdStairs2 worldgenstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return c(worldgenstrongholdstairs2, list, random, i, j, k, l, i1);
    }

    static WorldGenStrongholdStones b()
    {
        return d;
    }

    private static final WorldGenStrongholdPieceWeight b[] = {
        new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdStraight, 40, 0), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdPrison, 5, 5), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdLeftTurn, 20, 0), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdRightTurn, 20, 0), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdRoomCrossing, 10, 6), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdStairsStraight, 5, 10), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdStairs, 5, 10), new WorldGenStrongholdPieceWeight(net/minecraft/server/WorldGenStrongholdCrossing, 5, 4), new WorldGenStrongholdUnknown(net/minecraft/server/WorldGenStrongholdLibrary, 10, 1)
    };
    private static List c;
    static int a = 0;
    private static final WorldGenStrongholdStones d = new WorldGenStrongholdStones(null);

}
