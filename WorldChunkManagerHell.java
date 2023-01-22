// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WorldChunkManager, BiomeBase, ChunkPosition, ChunkCoordIntPair

public class WorldChunkManagerHell extends WorldChunkManager
{

    public WorldChunkManagerHell(BiomeBase biomebase, float f, float f1)
    {
        a = biomebase;
        b = f;
        c = f1;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair)
    {
        return a;
    }

    public BiomeBase getBiome(int i, int j)
    {
        return a;
    }

    public float[] getTemperatures(float af[], int i, int j, int k, int l)
    {
        if(af == null || af.length < k * l)
            af = new float[k * l];
        Arrays.fill(af, 0, k * l, b);
        return af;
    }

    public float[] getWetness(float af[], int i, int j, int k, int l)
    {
        if(af == null || af.length < k * l)
            af = new float[k * l];
        Arrays.fill(af, 0, k * l, c);
        return af;
    }

    public BiomeBase[] a(BiomeBase abiomebase[], int i, int j, int k, int l)
    {
        if(abiomebase == null || abiomebase.length < k * l)
            abiomebase = new BiomeBase[k * l];
        Arrays.fill(abiomebase, 0, k * l, a);
        return abiomebase;
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random)
    {
        if(list.contains(a))
            return new ChunkPosition((i - k) + random.nextInt(k * 2 + 1), 0, (j - k) + random.nextInt(k * 2 + 1));
        else
            return null;
    }

    public boolean a(int i, int j, int k, List list)
    {
        return list.contains(a);
    }

    private BiomeBase a;
    private float b;
    private float c;
}
