// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            RegionFile

public class RegionFileCache
{

    private RegionFileCache()
    {
    }

    public static synchronized RegionFile a(File file, int i, int j)
    {
        File file1 = new File(file, "region");
        File file2 = new File(file1, (new StringBuilder()).append("r.").append(i >> 5).append(".").append(j >> 5).append(".mcr").toString());
        Reference reference = (Reference)a.get(file2);
        if(reference != null)
        {
            RegionFile regionfile = (RegionFile)reference.get();
            if(regionfile != null)
                return regionfile;
        }
        if(!file1.exists())
            file1.mkdirs();
        if(a.size() >= 256)
            a();
        RegionFile regionfile1 = new RegionFile(file2);
        a.put(file2, new SoftReference(regionfile1));
        return regionfile1;
    }

    public static synchronized void a()
    {
        Iterator iterator = a.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Reference reference = (Reference)iterator.next();
            try
            {
                RegionFile regionfile = (RegionFile)reference.get();
                if(regionfile != null)
                    regionfile.b();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        } while(true);
        a.clear();
    }

    public static int b(File file, int i, int j)
    {
        RegionFile regionfile = a(file, i, j);
        return regionfile.a();
    }

    public static DataInputStream c(File file, int i, int j)
    {
        RegionFile regionfile = a(file, i, j);
        return regionfile.a(i & 0x1f, j & 0x1f);
    }

    public static DataOutputStream d(File file, int i, int j)
    {
        RegionFile regionfile = a(file, i, j);
        return regionfile.b(i & 0x1f, j & 0x1f);
    }

    private static final Map a = new HashMap();

}
