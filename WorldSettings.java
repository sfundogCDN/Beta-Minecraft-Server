// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class WorldSettings
{

    public WorldSettings(long l, int i, boolean flag)
    {
        a = l;
        b = i;
        c = flag;
    }

    public long a()
    {
        return a;
    }

    public int b()
    {
        return b;
    }

    public boolean c()
    {
        return c;
    }

    public static int a(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
            return i;
        }
        return 0;
    }

    private final long a;
    private final int b;
    private final boolean c;
}
