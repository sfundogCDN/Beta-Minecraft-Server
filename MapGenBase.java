// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            World, IChunkProvider

public class MapGenBase
{

    public MapGenBase()
    {
        b = 8;
        c = new Random();
    }

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        int k = b;
        d = world;
        c.setSeed(world.getSeed());
        long l = c.nextLong();
        long l1 = c.nextLong();
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                long l2 = (long)i1 * l;
                long l3 = (long)j1 * l1;
                c.setSeed(l2 ^ l3 ^ world.getSeed());
                a(world, i1, j1, i, j, abyte0);
            }

        }

    }

    protected void a(World world, int i, int j, int k, int l, byte abyte0[])
    {
    }

    protected int b;
    protected Random c;
    protected World d;
}
