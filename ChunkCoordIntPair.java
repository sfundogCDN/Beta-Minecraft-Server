// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class ChunkCoordIntPair
{

    public ChunkCoordIntPair(int i, int j)
    {
        x = i;
        z = j;
    }

    public static long a(int i, int j)
    {
        long l = i;
        long l1 = j;
        return l & 0xffffffffL | (l1 & 0xffffffffL) << 32;
    }

    public int hashCode()
    {
        long l = a(x, z);
        int i = (int)l;
        int j = (int)(l >> 32);
        return i ^ j;
    }

    public boolean equals(Object obj)
    {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)obj;
        return chunkcoordintpair.x == x && chunkcoordintpair.z == z;
    }

    public final int x;
    public final int z;
}
