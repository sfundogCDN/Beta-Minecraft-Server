// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            IChunkLoader, CompressedStreamTools, NBTTagCompound, Chunk, 
//            World, WorldData, NibbleArray, NBTTagList, 
//            Entity, TileEntity, EntityTypes

public class ChunkLoader
    implements IChunkLoader
{

    public ChunkLoader(File file, boolean flag)
    {
        a = file;
        b = flag;
    }

    private File a(int i, int j)
    {
        String s = (new StringBuilder()).append("c.").append(Integer.toString(i, 36)).append(".").append(Integer.toString(j, 36)).append(".dat").toString();
        String s1 = Integer.toString(i & 0x3f, 36);
        String s2 = Integer.toString(j & 0x3f, 36);
        File file = new File(a, s1);
        if(!file.exists())
            if(b)
                file.mkdir();
            else
                return null;
        file = new File(file, s2);
        if(!file.exists())
            if(b)
                file.mkdir();
            else
                return null;
        file = new File(file, s);
        if(!file.exists() && !b)
            return null;
        else
            return file;
    }

    public Chunk a(World world, int i, int j)
    {
        File file;
        file = a(i, j);
        if(file == null || !file.exists())
            break MISSING_BLOCK_LABEL_296;
        NBTTagCompound nbttagcompound;
        FileInputStream fileinputstream = new FileInputStream(file);
        nbttagcompound = CompressedStreamTools.a(fileinputstream);
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
        try
        {
            Chunk chunk = a(world, nbttagcompound.k("Level"));
            if(!chunk.a(i, j))
            {
                System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is in the wrong location; relocating. (Expected ").append(i).append(", ").append(j).append(", got ").append(chunk.x).append(", ").append(chunk.z).append(")").toString());
                nbttagcompound.a("xPos", i);
                nbttagcompound.a("zPos", j);
                chunk = a(world, nbttagcompound.k("Level"));
            }
            chunk.g();
            return chunk;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public void a(World world, Chunk chunk)
    {
        world.j();
        File file = a(chunk.x, chunk.z);
        if(file.exists())
        {
            WorldData worlddata = world.p();
            worlddata.b(worlddata.g() - file.length());
        }
        try
        {
            File file1 = new File(a, "tmp_chunk.dat");
            FileOutputStream fileoutputstream = new FileOutputStream(file1);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.a("Level", nbttagcompound1);
            a(chunk, world, nbttagcompound1);
            CompressedStreamTools.a(nbttagcompound, fileoutputstream);
            fileoutputstream.close();
            if(file.exists())
                file.delete();
            file1.renameTo(file);
            WorldData worlddata1 = world.p();
            worlddata1.b(worlddata1.g() + file.length());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void a(Chunk chunk, World world, NBTTagCompound nbttagcompound)
    {
        world.j();
        nbttagcompound.a("xPos", chunk.x);
        nbttagcompound.a("zPos", chunk.z);
        nbttagcompound.setLong("LastUpdate", world.getTime());
        nbttagcompound.a("Blocks", chunk.b);
        nbttagcompound.a("Data", chunk.g.a);
        nbttagcompound.a("SkyLight", chunk.h.a);
        nbttagcompound.a("BlockLight", chunk.i.a);
        nbttagcompound.a("HeightMap", chunk.heightMap);
        nbttagcompound.a("TerrainPopulated", chunk.done);
        chunk.s = false;
        NBTTagList nbttaglist = new NBTTagList();
label0:
        for(int i = 0; i < chunk.entitySlices.length; i++)
        {
            Iterator iterator = chunk.entitySlices[i].iterator();
            do
            {
                if(!iterator.hasNext())
                    continue label0;
                Entity entity = (Entity)iterator.next();
                chunk.s = true;
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                if(entity.c(nbttagcompound1))
                    nbttaglist.a(nbttagcompound1);
            } while(true);
        }

        nbttagcompound.a("Entities", nbttaglist);
        NBTTagList nbttaglist1 = new NBTTagList();
        NBTTagCompound nbttagcompound2;
        for(Iterator iterator1 = chunk.tileEntities.values().iterator(); iterator1.hasNext(); nbttaglist1.a(nbttagcompound2))
        {
            TileEntity tileentity = (TileEntity)iterator1.next();
            nbttagcompound2 = new NBTTagCompound();
            tileentity.b(nbttagcompound2);
        }

        nbttagcompound.a("TileEntities", nbttaglist1);
    }

    public static Chunk a(World world, NBTTagCompound nbttagcompound)
    {
        int i = nbttagcompound.e("xPos");
        int j = nbttagcompound.e("zPos");
        Chunk chunk = new Chunk(world, i, j);
        chunk.b = nbttagcompound.j("Blocks");
        world.getClass();
        chunk.g = new NibbleArray(nbttagcompound.j("Data"), 7);
        world.getClass();
        chunk.h = new NibbleArray(nbttagcompound.j("SkyLight"), 7);
        world.getClass();
        chunk.i = new NibbleArray(nbttagcompound.j("BlockLight"), 7);
        chunk.heightMap = nbttagcompound.j("HeightMap");
        chunk.done = nbttagcompound.m("TerrainPopulated");
        if(!chunk.g.a())
        {
            world.getClass();
            chunk.g = new NibbleArray(chunk.b.length, 7);
        }
        if(chunk.heightMap == null || !chunk.h.a())
        {
            chunk.heightMap = new byte[256];
            world.getClass();
            chunk.h = new NibbleArray(chunk.b.length, 7);
            chunk.initLighting();
        }
        if(!chunk.i.a())
        {
            world.getClass();
            chunk.i = new NibbleArray(chunk.b.length, 7);
            chunk.a();
        }
        NBTTagList nbttaglist = nbttagcompound.l("Entities");
        if(nbttaglist != null)
        {
            for(int k = 0; k < nbttaglist.c(); k++)
            {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(k);
                Entity entity = EntityTypes.a(nbttagcompound1, world);
                chunk.s = true;
                if(entity != null)
                    chunk.a(entity);
            }

        }
        NBTTagList nbttaglist1 = nbttagcompound.l("TileEntities");
        if(nbttaglist1 != null)
        {
            for(int l = 0; l < nbttaglist1.c(); l++)
            {
                NBTTagCompound nbttagcompound2 = (NBTTagCompound)nbttaglist1.a(l);
                TileEntity tileentity = TileEntity.c(nbttagcompound2);
                if(tileentity != null)
                    chunk.a(tileentity);
            }

        }
        return chunk;
    }

    public void a()
    {
    }

    public void b()
    {
    }

    public void b(World world, Chunk chunk)
    {
    }

    private File a;
    private boolean b;
}
