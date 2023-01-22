// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, IntCache, BiomeBase

public class GenLayerRiver extends GenLayer
{

    public GenLayerRiver(long l, GenLayer genlayer)
    {
        super(l);
        super.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l)
    {
        int i1 = i - 1;
        int j1 = j - 1;
        int k1 = k + 2;
        int l1 = l + 2;
        int ai[] = a.a(i1, j1, k1, l1);
        int ai1[] = IntCache.a(k * l);
        for(int i2 = 0; i2 < l; i2++)
        {
            for(int j2 = 0; j2 < k; j2++)
            {
                int k2 = ai[j2 + 0 + (i2 + 1) * k1];
                int l2 = ai[j2 + 2 + (i2 + 1) * k1];
                int i3 = ai[j2 + 1 + (i2 + 0) * k1];
                int j3 = ai[j2 + 1 + (i2 + 2) * k1];
                int k3 = ai[j2 + 1 + (i2 + 1) * k1];
                if(k3 == 0 || k2 == 0 || l2 == 0 || i3 == 0 || j3 == 0)
                {
                    ai1[j2 + i2 * k] = BiomeBase.RIVER.y;
                    continue;
                }
                if(k3 != k2 || k3 != i3 || k3 != l2 || k3 != j3)
                    ai1[j2 + i2 * k] = BiomeBase.RIVER.y;
                else
                    ai1[j2 + i2 * k] = -1;
            }

        }

        return ai1;
    }
}
