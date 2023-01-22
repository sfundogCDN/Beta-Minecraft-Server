// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class WatchableObject
{

    public WatchableObject(int i, int j, Object obj)
    {
        b = j;
        c = obj;
        a = i;
        d = true;
    }

    public int a()
    {
        return b;
    }

    public void a(Object obj)
    {
        c = obj;
    }

    public Object b()
    {
        return c;
    }

    public int c()
    {
        return a;
    }

    public boolean d()
    {
        return d;
    }

    public void a(boolean flag)
    {
        d = flag;
    }

    private final int a;
    private final int b;
    private Object c;
    private boolean d;
}
