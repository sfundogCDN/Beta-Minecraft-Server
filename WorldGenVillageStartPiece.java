// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillageWell, WorldChunkManager, WorldGenVillagePieceWeight

public class WorldGenVillageStartPiece extends WorldGenVillageWell
{

    public WorldGenVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, ArrayList arraylist, int l)
    {
        super(0, random, j, k);
        e = new ArrayList();
        f = new ArrayList();
        a = worldchunkmanager;
        d = arraylist;
        b = l;
    }

    public WorldChunkManager a()
    {
        return a;
    }

    public WorldChunkManager a;
    public int b;
    public WorldGenVillagePieceWeight c;
    public ArrayList d;
    public ArrayList e;
    public ArrayList f;
}
