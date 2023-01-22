// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, BiomeBase, IntCache

public class GenLayerBiome extends GenLayer
{

    public GenLayerBiome(long l, GenLayer genlayer)
    {
        super(l);
        b = (new BiomeBase[] {
            BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.PLAINS, BiomeBase.TAIGA
        });
        a = genlayer;
    }

    public int[] a(int i, int j, int k, int l)
    {
        int ai[] = a.a(i, j, k, l);
        int ai1[] = IntCache.a(k * l);
        for(int i1 = 0; i1 < l; i1++)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                a(j1 + i, i1 + j);
                ai1[j1 + i1 * k] = ai[j1 + i1 * k] <= 0 ? 0 : b[a(b.length)].y;
            }

        }

        return ai1;
    }

    private BiomeBase b[];
}
