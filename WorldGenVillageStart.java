// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructureStart, WorldGenVillagePieces, WorldGenVillageStartPiece, World, 
//            StructurePiece, WorldGenVillageRoadPiece

class WorldGenVillageStart extends StructureStart
{

    public WorldGenVillageStart(World world, Random random, int i, int j)
    {
        c = false;
        int k = 0;
        ArrayList arraylist = WorldGenVillagePieces.a(random, k);
        WorldGenVillageStartPiece worldgenvillagestartpiece = new WorldGenVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, arraylist, k);
        a.add(worldgenvillagestartpiece);
        worldgenvillagestartpiece.a(worldgenvillagestartpiece, a, random);
        ArrayList arraylist1 = worldgenvillagestartpiece.f;
        for(ArrayList arraylist2 = worldgenvillagestartpiece.e; !arraylist1.isEmpty() || !arraylist2.isEmpty();)
            if(!arraylist1.isEmpty())
            {
                int l = random.nextInt(arraylist1.size());
                StructurePiece structurepiece = (StructurePiece)arraylist1.remove(l);
                structurepiece.a(worldgenvillagestartpiece, a, random);
            } else
            {
                int i1 = random.nextInt(arraylist2.size());
                StructurePiece structurepiece1 = (StructurePiece)arraylist2.remove(i1);
                structurepiece1.a(worldgenvillagestartpiece, a, random);
            }

        c();
        int j1 = 0;
        Iterator iterator = a.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StructurePiece structurepiece2 = (StructurePiece)iterator.next();
            if(!(structurepiece2 instanceof WorldGenVillageRoadPiece))
                j1++;
        } while(true);
        c = j1 > 2;
    }

    public boolean a()
    {
        return c;
    }

    private boolean c;
}
