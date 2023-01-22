// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            PlayerList, BiomeCacheBlock, WorldChunkManager, BiomeBase

public class BiomeCache
{

    public BiomeCache(WorldChunkManager worldchunkmanager)
    {
        b = 0L;
        c = new PlayerList();
        d = new ArrayList();
        a = worldchunkmanager;
    }

    private BiomeCacheBlock c(int i, int j)
    {
        i >>= 4;
        j >>= 4;
        long l = (long)i & 0xffffffffL | ((long)j & 0xffffffffL) << 32;
        BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)c.getEntry(l);
        if(biomecacheblock == null)
        {
            biomecacheblock = new BiomeCacheBlock(this, i, j);
            c.put(l, biomecacheblock);
            d.add(biomecacheblock);
        }
        biomecacheblock.f = System.currentTimeMillis();
        return biomecacheblock;
    }

    public BiomeBase a(int i, int j)
    {
        return c(i, j).a(i, j);
    }

    public void a()
    {
        long l = System.currentTimeMillis();
        long l1 = l - b;
        if(l1 > 7500L || l1 < 0L)
        {
            b = l;
            for(int i = 0; i < d.size(); i++)
            {
                BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)d.get(i);
                long l2 = l - biomecacheblock.f;
                if(l2 > 30000L || l2 < 0L)
                {
                    d.remove(i--);
                    long l3 = (long)biomecacheblock.d & 0xffffffffL | ((long)biomecacheblock.e & 0xffffffffL) << 32;
                    c.remove(l3);
                }
            }

        }
    }

    public BiomeBase[] b(int i, int j)
    {
        return c(i, j).c;
    }

    static WorldChunkManager a(BiomeCache biomecache)
    {
        return biomecache.a;
    }

    private final WorldChunkManager a;
    private long b;
    private PlayerList c;
    private List d;
}
