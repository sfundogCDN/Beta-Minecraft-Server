// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            GenLayer, IntCache

public class GenLayerZoom extends GenLayer
{

    public GenLayerZoom(long l, GenLayer genlayer)
    {
        super(l);
        super.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l)
    {
        int i1 = i >> 1;
        int j1 = j >> 1;
        int k1 = (k >> 1) + 3;
        int l1 = (l >> 1) + 3;
        int ai[] = a.a(i1, j1, k1, l1);
        int ai1[] = IntCache.a(k1 * 2 * (l1 * 2));
        int i2 = k1 << 1;
        for(int j2 = 0; j2 < l1 - 1; j2++)
        {
            int k2 = j2 << 1;
            int i3 = k2 * i2;
            int j3 = ai[0 + (j2 + 0) * k1];
            int k3 = ai[0 + (j2 + 1) * k1];
            for(int l3 = 0; l3 < k1 - 1; l3++)
            {
                a(l3 + i1 << 1, j2 + j1 << 1);
                int i4 = ai[l3 + 1 + (j2 + 0) * k1];
                int j4 = ai[l3 + 1 + (j2 + 1) * k1];
                ai1[i3] = j3;
                ai1[i3++ + i2] = a(j3, k3);
                ai1[i3] = a(j3, i4);
                ai1[i3++ + i2] = b(j3, i4, k3, j4);
                j3 = i4;
                k3 = j4;
            }

        }

        int ai2[] = IntCache.a(k * l);
        for(int l2 = 0; l2 < l; l2++)
            System.arraycopy(ai1, (l2 + (j & 1)) * (k1 << 1) + (i & 1), ai2, l2 * k, k);

        return ai2;
    }

    protected int a(int i, int j)
    {
        return a(2) != 0 ? j : i;
    }

    protected int b(int i, int j, int k, int l)
    {
        if(j == k && k == l)
            return j;
        if(i == j && i == k)
            return i;
        if(i == j && i == l)
            return i;
        if(i == k && i == l)
            return i;
        if(i == j && k != l)
            return i;
        if(i == k && j != l)
            return i;
        if(i == l && j != k)
            return i;
        if(j == i && k != l)
            return j;
        if(j == k && i != l)
            return j;
        if(j == l && i != k)
            return j;
        if(k == i && j != l)
            return k;
        if(k == j && i != l)
            return k;
        if(k == l && i != j)
            return k;
        if(l == i && j != k)
            return k;
        if(l == j && i != k)
            return k;
        if(l == k && i != j)
            return k;
        int i1 = a(4);
        if(i1 == 0)
            return i;
        if(i1 == 1)
            return j;
        if(i1 == 2)
            return k;
        else
            return l;
    }

    public static GenLayer a(long l, GenLayer genlayer, int i)
    {
        Object obj = genlayer;
        for(int j = 0; j < i; j++)
            obj = new GenLayerZoom(l + (long)j, ((GenLayer) (obj)));

        return ((GenLayer) (obj));
    }
}
