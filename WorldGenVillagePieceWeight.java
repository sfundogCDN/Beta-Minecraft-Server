// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class WorldGenVillagePieceWeight
{

    public WorldGenVillagePieceWeight(Class class1, int i, int j)
    {
        a = class1;
        b = i;
        d = j;
    }

    public boolean a(int i)
    {
        return d == 0 || c < d;
    }

    public boolean a()
    {
        return d == 0 || c < d;
    }

    public Class a;
    public final int b;
    public int c;
    public int d;
}
