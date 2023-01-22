// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class NextTickListEntry
    implements Comparable
{

    public NextTickListEntry(int i, int j, int k, int l)
    {
        g = f++;
        a = i;
        b = j;
        c = k;
        d = l;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof NextTickListEntry)
        {
            NextTickListEntry nextticklistentry = (NextTickListEntry)obj;
            return a == nextticklistentry.a && b == nextticklistentry.b && c == nextticklistentry.c && d == nextticklistentry.d;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return (a * 1024 * 1024 + c * 1024 + b) * 256 + d;
    }

    public NextTickListEntry a(long l)
    {
        e = l;
        return this;
    }

    public int compareTo(NextTickListEntry nextticklistentry)
    {
        if(e < nextticklistentry.e)
            return -1;
        if(e > nextticklistentry.e)
            return 1;
        if(g < nextticklistentry.g)
            return -1;
        return g <= nextticklistentry.g ? 0 : 1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((NextTickListEntry)obj);
    }

    private static long f = 0L;
    public int a;
    public int b;
    public int c;
    public int d;
    public long e;
    private long g;

}
