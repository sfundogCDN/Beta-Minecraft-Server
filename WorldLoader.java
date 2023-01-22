// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;

// Referenced classes of package net.minecraft.server:
//            Convertable, CompressedStreamTools, NBTTagCompound, WorldData, 
//            PlayerNBTManager, IDataManager, IProgressUpdate

public class WorldLoader
    implements Convertable
{

    public WorldLoader(File file)
    {
        if(!file.exists())
            file.mkdirs();
        a = file;
    }

    public WorldData b(String s)
    {
        File file = new File(a, s);
        if(!file.exists())
            return null;
        File file1 = new File(file, "level.dat");
        if(file1.exists())
            try
            {
                NBTTagCompound nbttagcompound = CompressedStreamTools.a(new FileInputStream(file1));
                NBTTagCompound nbttagcompound2 = nbttagcompound.k("Data");
                return new WorldData(nbttagcompound2);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        file1 = new File(file, "level.dat_old");
        if(file1.exists())
            try
            {
                NBTTagCompound nbttagcompound1 = CompressedStreamTools.a(new FileInputStream(file1));
                NBTTagCompound nbttagcompound3 = nbttagcompound1.k("Data");
                return new WorldData(nbttagcompound3);
            }
            catch(Exception exception1)
            {
                exception1.printStackTrace();
            }
        return null;
    }

    protected static void a(File afile[])
    {
        for(int i = 0; i < afile.length; i++)
        {
            if(afile[i].isDirectory())
                a(afile[i].listFiles());
            afile[i].delete();
        }

    }

    public IDataManager a(String s, boolean flag)
    {
        return new PlayerNBTManager(a, s, flag);
    }

    public boolean isConvertable(String s)
    {
        return false;
    }

    public boolean convert(String s, IProgressUpdate iprogressupdate)
    {
        return false;
    }

    protected final File a;
}
