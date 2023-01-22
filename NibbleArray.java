// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class NibbleArray
{

    public NibbleArray(int i, int j)
    {
        a = new byte[i >> 1];
        b = j;
        c = j + 4;
    }

    public NibbleArray(byte abyte0[], int i)
    {
        a = abyte0;
        b = i;
        c = i + 4;
    }

    public int a(int i, int j, int k)
    {
        int l = i << c | k << b | j;
        int i1 = l >> 1;
        int j1 = l & 1;
        if(j1 == 0)
            return a[i1] & 0xf;
        else
            return a[i1] >> 4 & 0xf;
    }

    public void a(int i, int j, int k, int l)
    {
        int i1 = i << c | k << b | j;
        int j1 = i1 >> 1;
        int k1 = i1 & 1;
        if(k1 == 0)
            a[j1] = (byte)(a[j1] & 0xf0 | l & 0xf);
        else
            a[j1] = (byte)(a[j1] & 0xf | (l & 0xf) << 4);
    }

    public boolean a()
    {
        return a != null;
    }

    public final byte a[];
    private final int b;
    private final int c;
}
