// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, IntCache, BiomeBase

public class GenLayerDownfall extends GenLayer
{

    public GenLayerDownfall(GenLayer genlayer)
    {
        super(0L);
        a = genlayer;
    }

    public int[] a(int i, int j, int k, int l)
    {
        int ai[] = a.a(i, j, k, l);
        int ai1[] = IntCache.a(k * l);
        for(int i1 = 0; i1 < k * l; i1++)
            ai1[i1] = BiomeBase.a[ai[i1]].e();

        return ai1;
    }
}
