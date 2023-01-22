// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, IntCache

public class LayerIsland extends GenLayer
{

    public LayerIsland(long l)
    {
        super(l);
    }

    public int[] a(int i, int j, int k, int l)
    {
        int ai[] = IntCache.a(k * l);
        for(int i1 = 0; i1 < l; i1++)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                a(i + j1, j + i1);
                ai[j1 + i1 * k] = a(10) != 0 ? 0 : 1;
            }

        }

        if(i > -k && i <= 0 && j > -l && j <= 0)
            ai[-i + -j * k] = 1;
        return ai;
    }
}
