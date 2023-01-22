// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillagePieceWeight, WorldGenVillageHouse, MathHelper, WorldGenVillageTemple, 
//            WorldGenVillageLibrary, WorldGenVillageHut, WorldGenVillageButcher, WorldGenVillageBigFarm, 
//            WorldGenVillageFarm, WorldGenVillageBlacksmith, WorldGenVillageHouse2, WorldGenVillageStartPiece, 
//            WorldGenVillageLight, StructureBoundingBox, StructurePiece, WorldGenVillage, 
//            WorldChunkManager, WorldGenVillageRoad, WorldGenVillagePiece

public class WorldGenVillagePieces
{

    public WorldGenVillagePieces()
    {
    }

    public static ArrayList a(Random random, int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageHouse, 4, MathHelper.a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageTemple, 20, MathHelper.a(random, 0 + i, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageLibrary, 20, MathHelper.a(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageHut, 3, MathHelper.a(random, 2 + i, 5 + i * 3)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageButcher, 15, MathHelper.a(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageBigFarm, 3, MathHelper.a(random, 1 + i, 4 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageFarm, 3, MathHelper.a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageBlacksmith, 15, MathHelper.a(random, 0, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(net/minecraft/server/WorldGenVillageHouse2, 8, MathHelper.a(random, 0 + i, 3 + i * 2)));
        Iterator iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((WorldGenVillagePieceWeight)iterator.next()).d == 0)
                iterator.remove();
        } while(true);
        return arraylist;
    }

    private static int a(ArrayList arraylist)
    {
        boolean flag = false;
        int i = 0;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            WorldGenVillagePieceWeight worldgenvillagepieceweight = (WorldGenVillagePieceWeight)iterator.next();
            if(worldgenvillagepieceweight.d > 0 && worldgenvillagepieceweight.c < worldgenvillagepieceweight.d)
                flag = true;
            i += worldgenvillagepieceweight.b;
        }

        return flag ? i : -1;
    }

    private static WorldGenVillagePiece a(WorldGenVillagePieceWeight worldgenvillagepieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Class class1 = worldgenvillagepieceweight.a;
        Object obj = null;
        if(class1 == net/minecraft/server/WorldGenVillageHouse)
            obj = WorldGenVillageHouse.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageTemple)
            obj = WorldGenVillageTemple.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageLibrary)
            obj = WorldGenVillageLibrary.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageHut)
            obj = WorldGenVillageHut.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageButcher)
            obj = WorldGenVillageButcher.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageBigFarm)
            obj = WorldGenVillageBigFarm.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageFarm)
            obj = WorldGenVillageFarm.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageBlacksmith)
            obj = WorldGenVillageBlacksmith.a(list, random, i, j, k, l, i1);
        else
        if(class1 == net/minecraft/server/WorldGenVillageHouse2)
            obj = WorldGenVillageHouse2.a(list, random, i, j, k, l, i1);
        return ((WorldGenVillagePiece) (obj));
    }

    private static WorldGenVillagePiece c(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        int j1;
        int k1;
        j1 = a(worldgenvillagestartpiece.d);
        if(j1 <= 0)
            return null;
        k1 = 0;
_L2:
        int l1;
        Iterator iterator;
        if(k1 >= 5)
            break MISSING_BLOCK_LABEL_182;
        k1++;
        l1 = random.nextInt(j1);
        iterator = worldgenvillagestartpiece.d.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        WorldGenVillagePieceWeight worldgenvillagepieceweight;
        worldgenvillagepieceweight = (WorldGenVillagePieceWeight)iterator.next();
        l1 -= worldgenvillagepieceweight.b;
        if(l1 >= 0) goto _L4; else goto _L3
_L3:
        if(worldgenvillagepieceweight.a(i1) && (worldgenvillagepieceweight != worldgenvillagestartpiece.c || worldgenvillagestartpiece.d.size() <= 1)) goto _L5; else goto _L2
_L5:
        WorldGenVillagePiece worldgenvillagepiece = a(worldgenvillagepieceweight, list, random, i, j, k, l, i1);
        if(worldgenvillagepiece == null) goto _L4; else goto _L6
_L6:
        worldgenvillagepieceweight.c++;
        worldgenvillagestartpiece.c = worldgenvillagepieceweight;
        if(!worldgenvillagepieceweight.a())
            worldgenvillagestartpiece.d.remove(worldgenvillagepieceweight);
        return worldgenvillagepiece;
        StructureBoundingBox structureboundingbox = WorldGenVillageLight.a(list, random, i, j, k, l);
        if(structureboundingbox != null)
            return new WorldGenVillageLight(i1, random, structureboundingbox, l);
        else
            return null;
    }

    private static StructurePiece d(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 50)
            return null;
        if(Math.abs(i - worldgenvillagestartpiece.b().a) > 112 || Math.abs(k - worldgenvillagestartpiece.b().c) > 112)
            return null;
        WorldGenVillagePiece worldgenvillagepiece = c(worldgenvillagestartpiece, list, random, i, j, k, l, i1 + 1);
        if(worldgenvillagepiece != null)
        {
            int j1 = (((StructurePiece) (worldgenvillagepiece)).g.a + ((StructurePiece) (worldgenvillagepiece)).g.d) / 2;
            int k1 = (((StructurePiece) (worldgenvillagepiece)).g.c + ((StructurePiece) (worldgenvillagepiece)).g.f) / 2;
            int l1 = ((StructurePiece) (worldgenvillagepiece)).g.d - ((StructurePiece) (worldgenvillagepiece)).g.a;
            int i2 = ((StructurePiece) (worldgenvillagepiece)).g.f - ((StructurePiece) (worldgenvillagepiece)).g.c;
            int j2 = l1 <= i2 ? i2 : l1;
            if(worldgenvillagestartpiece.a().a(j1, k1, j2 / 2 + 4, WorldGenVillage.a))
            {
                list.add(worldgenvillagepiece);
                worldgenvillagestartpiece.e.add(worldgenvillagepiece);
                return worldgenvillagepiece;
            }
        }
        return null;
    }

    private static StructurePiece e(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 3 + worldgenvillagestartpiece.b)
            return null;
        if(Math.abs(i - worldgenvillagestartpiece.b().a) > 112 || Math.abs(k - worldgenvillagestartpiece.b().c) > 112)
            return null;
        StructureBoundingBox structureboundingbox = WorldGenVillageRoad.a(worldgenvillagestartpiece, list, random, i, j, k, l);
        if(structureboundingbox != null && structureboundingbox.b > 10)
        {
            WorldGenVillageRoad worldgenvillageroad = new WorldGenVillageRoad(i1, random, structureboundingbox, l);
            int j1 = (((StructurePiece) (worldgenvillageroad)).g.a + ((StructurePiece) (worldgenvillageroad)).g.d) / 2;
            int k1 = (((StructurePiece) (worldgenvillageroad)).g.c + ((StructurePiece) (worldgenvillageroad)).g.f) / 2;
            int l1 = ((StructurePiece) (worldgenvillageroad)).g.d - ((StructurePiece) (worldgenvillageroad)).g.a;
            int i2 = ((StructurePiece) (worldgenvillageroad)).g.f - ((StructurePiece) (worldgenvillageroad)).g.c;
            int j2 = l1 <= i2 ? i2 : l1;
            if(worldgenvillagestartpiece.a().a(j1, k1, j2 / 2 + 4, WorldGenVillage.a))
            {
                list.add(worldgenvillageroad);
                worldgenvillagestartpiece.f.add(worldgenvillageroad);
                return worldgenvillageroad;
            }
        }
        return null;
    }

    static StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return d(worldgenvillagestartpiece, list, random, i, j, k, l, i1);
    }

    static StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return e(worldgenvillagestartpiece, list, random, i, j, k, l, i1);
    }
}
