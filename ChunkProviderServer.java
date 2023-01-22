// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChunkProviderServer.java

package net.minecraft.server;

import java.util.*;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.*;
import org.bukkit.craftbukkit.util.LongHashset;
import org.bukkit.craftbukkit.util.LongHashtable;
import org.bukkit.event.world.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EmptyChunk, Chunk, IChunkProvider, WorldServer, 
//            ChunkCoordinates, MinecraftServer, IChunkLoader, BlockSand, 
//            IProgressUpdate

public class ChunkProviderServer
    implements IChunkProvider
{

    public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, IChunkProvider ichunkprovider)
    {
        unloadQueue = new LongHashset();
        forceChunkLoad = false;
        chunks = new LongHashtable();
        chunkList = new ArrayList();
        worldserver.getClass();
        EmptyChunk emptychunk = new EmptyChunk(worldserver, new byte[32768], 0, 0);
        emptyChunk = emptychunk;
        world = worldserver;
        e = ichunkloader;
        chunkProvider = ichunkprovider;
    }

    public boolean isChunkLoaded(int i, int j)
    {
        return chunks.containsKey(i, j);
    }

    public void queueUnload(int i, int j)
    {
        ChunkCoordinates chunkcoordinates = world.getSpawn();
        int k = (i * 16 + 8) - chunkcoordinates.x;
        int l = (j * 16 + 8) - chunkcoordinates.z;
        short short1 = 128;
        if(k < -short1 || k > short1 || l < -short1 || l > short1 || !world.keepSpawnInMemory)
            unloadQueue.add(i, j);
    }

    public Chunk getChunkAt(int i, int j)
    {
        unloadQueue.remove(i, j);
        Chunk chunk = (Chunk)chunks.get(i, j);
        boolean newChunk = false;
        if(chunk == null)
        {
            chunk = loadChunk(i, j);
            if(chunk == null)
            {
                if(chunkProvider == null)
                    chunk = emptyChunk;
                else
                    chunk = chunkProvider.getOrCreateChunk(i, j);
                newChunk = true;
            }
            chunks.put(i, j, chunk);
            chunkList.add(chunk);
            if(chunk != null)
            {
                chunk.loadNOP();
                chunk.addEntities();
            }
            Server server = world.getServer();
            if(server != null)
                server.getPluginManager().callEvent(new ChunkLoadEvent(chunk.bukkitChunk, newChunk));
            chunk.a(this, this, i, j);
        }
        return chunk;
    }

    public Chunk getOrCreateChunk(int i, int j)
    {
        Chunk chunk = (Chunk)chunks.get(i, j);
        chunk = chunk != null ? chunk : world.isLoading || forceChunkLoad ? getChunkAt(i, j) : emptyChunk;
        if(chunk == emptyChunk)
            return chunk;
        if(i != chunk.x || j != chunk.z)
        {
            MinecraftServer.log.severe((new StringBuilder()).append("Chunk (").append(chunk.x).append(", ").append(chunk.z).append(") stored at  (").append(i).append(", ").append(j).append(") in world '").append(world.getWorld().getName()).append("'").toString());
            MinecraftServer.log.severe(chunk.getClass().getName());
            Throwable ex = new Throwable();
            ex.fillInStackTrace();
            ex.printStackTrace();
        }
        return chunk;
    }

    public Chunk loadChunk(int i, int j)
    {
        if(e == null)
            return null;
        try
        {
            Chunk chunk = e.a(world, i, j);
            if(chunk != null)
                chunk.t = world.getTime();
            return chunk;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public void saveChunkNOP(Chunk chunk)
    {
        if(e != null)
            try
            {
                e.b(world, chunk);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
    }

    public void saveChunk(Chunk chunk)
    {
        if(e != null)
            try
            {
                chunk.t = world.getTime();
                e.a(world, chunk);
            }
            catch(Exception ioexception)
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
            if(chunkProvider != null)
            {
                chunkProvider.getChunkAt(ichunkprovider, i, j);
                BlockSand.instaFall = true;
                Random random = new Random();
                random.setSeed(this.world.getSeed());
                long xRand = (random.nextLong() / 2L) * 2L + 1L;
                long zRand = (random.nextLong() / 2L) * 2L + 1L;
                random.setSeed((long)i * xRand + (long)j * zRand ^ this.world.getSeed());
                World world = this.world.getWorld();
                if(world != null)
                {
                    BlockPopulator populator;
                    for(Iterator i$ = world.getPopulators().iterator(); i$.hasNext(); populator.populate(world, random, chunk.bukkitChunk))
                        populator = (BlockPopulator)i$.next();

                }
                BlockSand.instaFall = false;
                this.world.getServer().getPluginManager().callEvent(new ChunkPopulateEvent(chunk.bukkitChunk));
                chunk.f();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        int i = 0;
        for(int j = 0; j < chunkList.size(); j++)
        {
            Chunk chunk = (Chunk)chunkList.get(j);
            if(flag && !chunk.r)
                saveChunkNOP(chunk);
            if(!chunk.a(flag))
                continue;
            saveChunk(chunk);
            chunk.q = false;
            if(++i == 24 && !flag)
                return false;
        }

        if(flag)
        {
            if(e == null)
                return true;
            e.b();
        }
        return true;
    }

    public boolean unloadChunks()
    {
        if(!world.savingDisabled)
        {
            Server server = world.getServer();
            for(int i = 0; i < 50 && !unloadQueue.isEmpty(); i++)
            {
                long chunkcoordinates = unloadQueue.popFirst();
                Chunk chunk = (Chunk)chunks.get(chunkcoordinates);
                if(chunk == null)
                    continue;
                ChunkUnloadEvent event = new ChunkUnloadEvent(chunk.bukkitChunk);
                server.getPluginManager().callEvent(event);
                if(!event.isCancelled())
                {
                    world.getWorld().preserveChunk((CraftChunk)chunk.bukkitChunk);
                    chunk.removeEntities();
                    saveChunk(chunk);
                    saveChunkNOP(chunk);
                    chunks.remove(chunkcoordinates);
                    chunkList.remove(chunk);
                }
            }

            if(e != null)
                e.a();
        }
        return chunkProvider.unloadChunks();
    }

    public boolean canSave()
    {
        return !world.savingDisabled;
    }

    public LongHashset unloadQueue;
    public Chunk emptyChunk;
    public IChunkProvider chunkProvider;
    private IChunkLoader e;
    public boolean forceChunkLoad;
    public LongHashtable chunks;
    public List chunkList;
    public WorldServer world;
}
