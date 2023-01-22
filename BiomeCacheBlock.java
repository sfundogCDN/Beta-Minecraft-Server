// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            BiomeBase, BiomeCache, WorldChunkManager

class BiomeCacheBlock
{

    public BiomeCacheBlock(BiomeCache biomecache, int i, int j)
    {
        g = biomecache;
        super();
        a = new float[256];
        b = new float[256];
        c = new BiomeBase[256];
        d = i;
        e = j;
        BiomeCache.a(biomecache).getTemperatures(a, i << 4, j << 4, 16, 16);
        BiomeCache.a(biomecache).getWetness(b, i << 4, j << 4, 16, 16);
        BiomeCache.a(biomecache).a(c, i << 4, j << 4, 16, 16, false);
    }

    public BiomeBase a(int i, int j)
    {
        return c[i & 0xf | (j & 0xf) << 4];
    }

    public float a[];
    public float b[];
    public BiomeBase c[];
    public int d;
    public int e;
    public long f;
    final BiomeCache g;
}
