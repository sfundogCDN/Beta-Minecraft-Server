// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            BiomeCache, BiomeBase, World, GenLayer, 
//            ChunkCoordIntPair, IntCache, ChunkPosition

public class WorldChunkManager
{

    protected WorldChunkManager()
    {
        e = new BiomeCache(this);
        f = new ArrayList();
        f.add(BiomeBase.FOREST);
        f.add(BiomeBase.SWAMPLAND);
        f.add(BiomeBase.TAIGA);
    }

    public WorldChunkManager(World world)
    {
        this();
        GenLayer agenlayer[] = GenLayer.a(world.getSeed());
        temperature = agenlayer[0];
        rain = agenlayer[1];
        c = agenlayer[2];
        d = agenlayer[3];
    }

    public List a()
    {
        return f;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair)
    {
        return getBiome(chunkcoordintpair.x << 4, chunkcoordintpair.z << 4);
    }

    public BiomeBase getBiome(int i, int j)
    {
        return e.a(i, j);
    }

    public float[] getWetness(float af[], int i, int j, int k, int l)
    {
        IntCache.a();
        if(af == null || af.length < k * l)
            af = new float[k * l];
        int ai[] = d.a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            float f1 = (float)ai[i1] / 65536F;
            if(f1 > 1.0F)
                f1 = 1.0F;
            af[i1] = f1;
        }

        return af;
    }

    public float[] getTemperatures(float af[], int i, int j, int k, int l)
    {
        IntCache.a();
        if(af == null || af.length < k * l)
            af = new float[k * l];
        int ai[] = c.a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            float f1 = (float)ai[i1] / 65536F;
            if(f1 > 1.0F)
                f1 = 1.0F;
            af[i1] = f1;
        }

        return af;
    }

    public BiomeBase[] getBiomes(BiomeBase abiomebase[], int i, int j, int k, int l)
    {
        IntCache.a();
        if(abiomebase == null || abiomebase.length < k * l)
            abiomebase = new BiomeBase[k * l];
        int ai[] = temperature.a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
            abiomebase[i1] = BiomeBase.a[ai[i1]];

        return abiomebase;
    }

    public BiomeBase[] a(BiomeBase abiomebase[], int i, int j, int k, int l)
    {
        return a(abiomebase, i, j, k, l, true);
    }

    public BiomeBase[] a(BiomeBase abiomebase[], int i, int j, int k, int l, boolean flag)
    {
        IntCache.a();
        if(abiomebase == null || abiomebase.length < k * l)
            abiomebase = new BiomeBase[k * l];
        if(flag && k == 16 && l == 16 && (i & 0xf) == 0 && (j & 0xf) == 0)
        {
            BiomeBase abiomebase1[] = e.b(i, j);
            System.arraycopy(abiomebase1, 0, abiomebase, 0, k * l);
            return abiomebase;
        }
        int ai[] = rain.a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
            abiomebase[i1] = BiomeBase.a[ai[i1]];

        return abiomebase;
    }

    public boolean a(int i, int j, int k, List list)
    {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = (j1 - l) + 1;
        int i2 = (k1 - i1) + 1;
        int ai[] = temperature.a(l, i1, l1, i2);
        for(int j2 = 0; j2 < l1 * i2; j2++)
        {
            BiomeBase biomebase = BiomeBase.a[ai[j2]];
            if(!list.contains(biomebase))
                return false;
        }

        return true;
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random)
    {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = (j1 - l) + 1;
        int i2 = (k1 - i1) + 1;
        int ai[] = temperature.a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;
        for(int k2 = 0; k2 < ai.length; k2++)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeBase biomebase = BiomeBase.a[ai[k2]];
            if(list.contains(biomebase) && (chunkposition == null || random.nextInt(j2 + 1) == 0))
            {
                chunkposition = new ChunkPosition(l2, 0, i3);
                j2++;
            }
        }

        return chunkposition;
    }

    public void b()
    {
        e.a();
    }

    private GenLayer temperature;
    private GenLayer rain;
    private GenLayer c;
    private GenLayer d;
    private BiomeCache e;
    private List f;
}
