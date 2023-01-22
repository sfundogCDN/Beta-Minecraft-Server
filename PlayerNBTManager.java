// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlayerNBTManager.java

package net.minecraft.server;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

// Referenced classes of package net.minecraft.server:
//            MinecraftException, WorldProviderHell, ChunkLoader, WorldData, 
//            NBTTagCompound, PlayerFileData, IDataManager, CompressedStreamTools, 
//            EntityHuman, WorldProvider, IChunkLoader

public class PlayerNBTManager
    implements PlayerFileData, IDataManager
{

    public PlayerNBTManager(File file1, String s, boolean flag)
    {
        uuid = null;
        b = new File(file1, s);
        b.mkdirs();
        c = new File(b, "players");
        d = new File(b, "data");
        d.mkdirs();
        if(flag)
            c.mkdirs();
        f();
    }

    private void f()
    {
        DataOutputStream dataoutputstream;
        File file1 = new File(b, "session.lock");
        dataoutputstream = new DataOutputStream(new FileOutputStream(file1));
        dataoutputstream.writeLong(e);
        dataoutputstream.close();
        break MISSING_BLOCK_LABEL_70;
        Exception exception;
        exception;
        dataoutputstream.close();
        throw exception;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        throw new RuntimeException("Failed to check session lock, aborting");
    }

    protected File a()
    {
        return b;
    }

    public void b()
    {
        DataInputStream datainputstream;
        File file1 = new File(b, "session.lock");
        datainputstream = new DataInputStream(new FileInputStream(file1));
        if(datainputstream.readLong() != e)
            throw new MinecraftException("The save is being accessed from another location, aborting");
        datainputstream.close();
        break MISSING_BLOCK_LABEL_80;
        Exception exception;
        exception;
        datainputstream.close();
        throw exception;
        IOException ioexception;
        ioexception;
        throw new MinecraftException("Failed to check session lock, aborting");
    }

    public IChunkLoader a(WorldProvider worldprovider)
    {
        if(worldprovider instanceof WorldProviderHell)
        {
            File file1 = new File(b, "DIM-1");
            file1.mkdirs();
            return new ChunkLoader(file1, true);
        } else
        {
            return new ChunkLoader(b, true);
        }
    }

    public WorldData c()
    {
        File file1 = new File(b, "level.dat");
        if(file1.exists())
            try
            {
                NBTTagCompound nbttagcompound = CompressedStreamTools.a(new FileInputStream(file1));
                NBTTagCompound nbttagcompound1 = nbttagcompound.k("Data");
                return new WorldData(nbttagcompound1);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        file1 = new File(b, "level.dat_old");
        if(file1.exists())
            try
            {
                NBTTagCompound nbttagcompound = CompressedStreamTools.a(new FileInputStream(file1));
                NBTTagCompound nbttagcompound1 = nbttagcompound.k("Data");
                return new WorldData(nbttagcompound1);
            }
            catch(Exception exception1)
            {
                exception1.printStackTrace();
            }
        return null;
    }

    public void a(WorldData worlddata, List list)
    {
        NBTTagCompound nbttagcompound = worlddata.a(list);
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.a("Data", nbttagcompound);
        try
        {
            File file1 = new File(b, "level.dat_new");
            File file2 = new File(b, "level.dat_old");
            File file3 = new File(b, "level.dat");
            CompressedStreamTools.a(nbttagcompound1, new FileOutputStream(file1));
            if(file2.exists())
                file2.delete();
            file3.renameTo(file2);
            if(file3.exists())
                file3.delete();
            file1.renameTo(file3);
            if(file1.exists())
                file1.delete();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void a(WorldData worlddata)
    {
        NBTTagCompound nbttagcompound = worlddata.a();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.a("Data", nbttagcompound);
        try
        {
            File file1 = new File(b, "level.dat_new");
            File file2 = new File(b, "level.dat_old");
            File file3 = new File(b, "level.dat");
            CompressedStreamTools.a(nbttagcompound1, new FileOutputStream(file1));
            if(file2.exists())
                file2.delete();
            file3.renameTo(file2);
            if(file3.exists())
                file3.delete();
            file1.renameTo(file3);
            if(file1.exists())
                file1.delete();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void a(EntityHuman entityhuman)
    {
        try
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            entityhuman.d(nbttagcompound);
            File file1 = new File(c, "_tmp_.dat");
            File file2 = new File(c, (new StringBuilder()).append(entityhuman.name).append(".dat").toString());
            CompressedStreamTools.a(nbttagcompound, new FileOutputStream(file1));
            if(file2.exists())
                file2.delete();
            file1.renameTo(file2);
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to save player data for ").append(entityhuman.name).toString());
        }
    }

    public void b(EntityHuman entityhuman)
    {
        NBTTagCompound nbttagcompound = a(entityhuman.name);
        if(nbttagcompound != null)
            entityhuman.e(nbttagcompound);
    }

    public NBTTagCompound a(String s)
    {
        try
        {
            File file1 = new File(c, (new StringBuilder()).append(s).append(".dat").toString());
            if(file1.exists())
                return CompressedStreamTools.a(new FileInputStream(file1));
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to load player data for ").append(s).toString());
        }
        return null;
    }

    public PlayerFileData d()
    {
        return this;
    }

    public void e()
    {
    }

    public File b(String s)
    {
        return new File(d, (new StringBuilder()).append(s).append(".dat").toString());
    }

    public UUID getUUID()
    {
        if(uuid != null)
            return uuid;
        try
        {
            File file1 = new File(b, "uid.dat");
            if(!file1.exists())
            {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(file1));
                uuid = UUID.randomUUID();
                dos.writeLong(uuid.getMostSignificantBits());
                dos.writeLong(uuid.getLeastSignificantBits());
                dos.close();
            } else
            {
                DataInputStream dis = new DataInputStream(new FileInputStream(file1));
                uuid = new UUID(dis.readLong(), dis.readLong());
                dis.close();
            }
            return uuid;
        }
        catch(IOException ex)
        {
            return null;
        }
    }

    private static final Logger a = Logger.getLogger("Minecraft");
    private final File b;
    private final File c;
    private final File d;
    private final long e = System.currentTimeMillis();
    private UUID uuid;

}
