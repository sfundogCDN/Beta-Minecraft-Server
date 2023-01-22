// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;

// Referenced classes of package net.minecraft.server:
//            IChunkLoader, RegionFileCache, CompressedStreamTools, NBTTagCompound, 
//            ChunkLoader, Chunk, World, WorldData

public class ChunkRegionLoader
    implements IChunkLoader
{

    public ChunkRegionLoader(File file)
    {
        a = file;
    }

    public Chunk a(World world, int i, int j)
    {
        java.io.DataInputStream datainputstream = RegionFileCache.c(a, i, j);
        NBTTagCompound nbttagcompound;
        if(datainputstream != null)
            nbttagcompound = CompressedStreamTools.a(datainputstream);
        else
            return null;
        if(!nbttagcompound.hasKey("Level"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing level data, skipping").toString());
            return null;
        }
        if(!nbttagcompound.k("Level").hasKey("Blocks"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing block data, skipping").toString());
            return null;
        }
        Chunk chunk = ChunkLoader.a(world, nbttagcompound.k("Level"));
        if(!chunk.a(i, j))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is in the wrong location; relocating. (Expected ").append(i).append(", ").append(j).append(", got ").append(chunk.x).append(", ").append(chunk.z).append(")").toString());
            nbttagcompound.a("xPos", i);
            nbttagcompound.a("zPos", j);
            chunk = ChunkLoader.a(world, nbttagcompound.k("Level"));
        }
        chunk.g();
        return chunk;
    }

    public void a(World world, Chunk chunk)
    {
        world.j();
        try
        {
            DataOutputStream dataoutputstream = RegionFileCache.d(a, chunk.x, chunk.z);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.a("Level", nbttagcompound1);
            ChunkLoader.a(chunk, world, nbttagcompound1);
            CompressedStreamTools.a(nbttagcompound, dataoutputstream);
            dataoutputstream.close();
            WorldData worlddata = world.p();
            worlddata.b(worlddata.g() + (long)RegionFileCache.b(a, chunk.x, chunk.z));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void b(World world, Chunk chunk)
    {
    }

    public void a()
    {
    }

    public void b()
    {
    }

    private final File a;
}
