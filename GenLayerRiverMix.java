// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, IntCache, BiomeBase

public class GenLayerRiverMix extends GenLayer
{

    public GenLayerRiverMix(long l, GenLayer genlayer, GenLayer genlayer1)
    {
        super(l);
        b = genlayer;
        c = genlayer1;
    }

    public void b(long l)
    {
        b.b(l);
        c.b(l);
        super.b(l);
    }

    public int[] a(int i, int j, int k, int l)
    {
        int ai[] = b.a(i, j, k, l);
        int ai1[] = c.a(i, j, k, l);
        int ai2[] = IntCache.a(k * l);
        for(int i1 = 0; i1 < k * l; i1++)
            if(ai[i1] == BiomeBase.OCEAN.y)
                ai2[i1] = ai[i1];
            else
                ai2[i1] = ai1[i1] < 0 ? ai[i1] : ai1[i1];

        return ai2;
    }

    private GenLayer b;
    private GenLayer c;
}
