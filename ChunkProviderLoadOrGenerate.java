// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.IOException;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            IChunkProvider, PlayerList, EmptyChunk, ChunkCoordIntPair, 
//            World, ChunkCoordinates, Chunk, IChunkLoader, 
//            IProgressUpdate

public class ChunkProviderLoadOrGenerate
    implements IChunkProvider
{

    public ChunkProviderLoadOrGenerate(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider)
    {
        a = new HashSet();
        e = new PlayerList();
        f = new ArrayList();
        world.getClass();
        b = new EmptyChunk(world, new byte[256 * 128], 0, 0);
        g = world;
        d = ichunkloader;
        c = ichunkprovider;
    }

    public boolean isChunkLoaded(int i, int j)
    {
        return e.contains(ChunkCoordIntPair.a(i, j));
    }

    public void d(int i, int j)
    {
        ChunkCoordinates chunkcoordinates = g.getSpawn();
        int k = (i * 16 + 8) - chunkcoordinates.x;
        int l = (j * 16 + 8) - chunkcoordinates.z;
        char c1 = '\200';
        if(k < -c1 || k > c1 || l < -c1 || l > c1)
            a.add(Long.valueOf(ChunkCoordIntPair.a(i, j)));
    }

    public Chunk getChunkAt(int i, int j)
    {
        long l = ChunkCoordIntPair.a(i, j);
        a.remove(Long.valueOf(l));
        Chunk chunk = (Chunk)e.getEntry(l);
        if(chunk == null)
        {
            int k = 0x1c9c3c;
            if(i < -k || j < -k || i >= k || j >= k)
                return b;
            chunk = e(i, j);
            if(chunk == null)
                if(c == null)
                    chunk = b;
                else
                    chunk = c.getOrCreateChunk(i, j);
            e.put(l, chunk);
            f.add(chunk);
            if(chunk != null)
            {
                chunk.loadNOP();
                chunk.addEntities();
            }
            chunk.a(this, this, i, j);
        }
        return chunk;
    }

    public Chunk getOrCreateChunk(int i, int j)
    {
        Chunk chunk = (Chunk)e.getEntry(ChunkCoordIntPair.a(i, j));
        if(chunk == null)
            return getChunkAt(i, j);
        else
            return chunk;
    }

    private Chunk e(int i, int j)
    {
        if(d == null)
            return null;
        try
        {
            Chunk chunk = d.a(g, i, j);
            if(chunk != null)
                chunk.t = g.getTime();
            return chunk;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private void a(Chunk chunk)
    {
        if(d == null)
            return;
        try
        {
            d.b(g, chunk);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void b(Chunk chunk)
    {
        if(d == null)
            return;
        try
        {
            chunk.t = g.getTime();
            d.a(g, chunk);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j)
    {
        Chunk chunk = getOrCreateChunk(i, j);
        if(!chunk.done)
        {
            chunk.done = true;
            if(c != null)
            {
                c.getChunkAt(ichunkprovider, i, j);
                chunk.f();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        int i = 0;
        for(int j = 0; j < f.size(); j++)
        {
            Chunk chunk = (Chunk)f.get(j);
            if(flag && !chunk.r)
                a(chunk);
            if(!chunk.a(flag))
                continue;
            b(chunk);
            chunk.q = false;
            if(++i == 24 && !flag)
                return false;
        }

        if(flag)
        {
            if(d == null)
                return true;
            d.b();
        }
        return true;
    }

    public boolean unloadChunks()
    {
        for(int i = 0; i < 100; i++)
            if(!a.isEmpty())
            {
                Long long1 = (Long)a.iterator().next();
                Chunk chunk1 = (Chunk)e.getEntry(long1.longValue());
                chunk1.removeEntities();
                b(chunk1);
                a(chunk1);
                a.remove(long1);
                e.remove(long1.longValue());
                f.remove(chunk1);
            }

        for(int j = 0; j < 10; j++)
        {
            if(h >= f.size())
            {
                h = 0;
                break;
            }
            Chunk chunk = (Chunk)f.get(h++);
            EntityHuman entityhuman = g.a((double)(chunk.x << 4) + 8D, 64D, (double)(chunk.z << 4) + 8D, 288D);
            if(entityhuman == null)
                d(chunk.x, chunk.z);
        }

        if(d != null)
            d.a();
        return c.unloadChunks();
    }

    public boolean canSave()
    {
        return true;
    }

    private Set a;
    private Chunk b;
    private IChunkProvider c;
    private IChunkLoader d;
    private PlayerList e;
    private List f;
    private World g;
    private int h;
}
