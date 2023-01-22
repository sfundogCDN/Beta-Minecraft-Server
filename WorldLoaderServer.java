// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

// Referenced classes of package net.minecraft.server:
//            WorldLoader, ServerNBTManager, WorldData, IProgressUpdate, 
//            IDataManager, ChunkFileFilter, ChunkFilenameFilter, ChunkFile, 
//            RegionFileCache, RegionFile

public class WorldLoaderServer extends WorldLoader
{

    public WorldLoaderServer(File file)
    {
        super(file);
    }

    public IDataManager a(String s, boolean flag)
    {
        return new ServerNBTManager(a, s, flag);
    }

    public boolean isConvertable(String s)
    {
        WorldData worlddata = b(s);
        return worlddata != null && worlddata.i() == 0;
    }

    public boolean convert(String s, IProgressUpdate iprogressupdate)
    {
        iprogressupdate.a(0);
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        ArrayList arraylist2 = new ArrayList();
        ArrayList arraylist3 = new ArrayList();
        File file = new File(a, s);
        File file1 = new File(file, "DIM-1");
        System.out.println("Scanning folders...");
        a(file, arraylist, arraylist1);
        if(file1.exists())
            a(file1, arraylist2, arraylist3);
        int i = arraylist.size() + arraylist2.size() + arraylist1.size() + arraylist3.size();
        System.out.println((new StringBuilder()).append("Total conversion count is ").append(i).toString());
        a(file, arraylist, 0, i, iprogressupdate);
        a(file1, arraylist2, arraylist.size(), i, iprogressupdate);
        WorldData worlddata = b(s);
        worlddata.a(19132);
        IDataManager idatamanager = a(s, false);
        idatamanager.a(worlddata);
        a(arraylist1, arraylist.size() + arraylist2.size(), i, iprogressupdate);
        if(file1.exists())
            a(arraylist3, arraylist.size() + arraylist2.size() + arraylist1.size(), i, iprogressupdate);
        return true;
    }

    private void a(File file, ArrayList arraylist, ArrayList arraylist1)
    {
        ChunkFileFilter chunkfilefilter = new ChunkFileFilter(null);
        ChunkFilenameFilter chunkfilenamefilter = new ChunkFilenameFilter(null);
        File afile[] = file.listFiles(chunkfilefilter);
        File afile1[] = afile;
        int i = afile1.length;
        for(int j = 0; j < i; j++)
        {
            File file1 = afile1[j];
            arraylist1.add(file1);
            File afile2[] = file1.listFiles(chunkfilefilter);
            File afile3[] = afile2;
            int k = afile3.length;
            for(int l = 0; l < k; l++)
            {
                File file2 = afile3[l];
                File afile4[] = file2.listFiles(chunkfilenamefilter);
                File afile5[] = afile4;
                int i1 = afile5.length;
                for(int j1 = 0; j1 < i1; j1++)
                {
                    File file3 = afile5[j1];
                    arraylist.add(new ChunkFile(file3));
                }

            }

        }

    }

    private void a(File file, ArrayList arraylist, int i, int j, IProgressUpdate iprogressupdate)
    {
        Collections.sort(arraylist);
        byte abyte0[] = new byte[4096];
        int i1;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); iprogressupdate.a(i1))
        {
            ChunkFile chunkfile = (ChunkFile)iterator.next();
            int k = chunkfile.b();
            int l = chunkfile.c();
            RegionFile regionfile = RegionFileCache.a(file, k, l);
            if(!regionfile.c(k & 0x1f, l & 0x1f))
                try
                {
                    DataInputStream datainputstream = new DataInputStream(new GZIPInputStream(new FileInputStream(chunkfile.a())));
                    DataOutputStream dataoutputstream = regionfile.b(k & 0x1f, l & 0x1f);
                    for(int j1 = 0; (j1 = datainputstream.read(abyte0)) != -1;)
                        dataoutputstream.write(abyte0, 0, j1);

                    dataoutputstream.close();
                    datainputstream.close();
                }
                catch(IOException ioexception)
                {
                    ioexception.printStackTrace();
                }
            i++;
            i1 = (int)Math.round((100D * (double)i) / (double)j);
        }

        RegionFileCache.a();
    }

    private void a(ArrayList arraylist, int i, int j, IProgressUpdate iprogressupdate)
    {
        int k;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); iprogressupdate.a(k))
        {
            File file = (File)iterator.next();
            File afile[] = file.listFiles();
            a(afile);
            file.delete();
            i++;
            k = (int)Math.round((100D * (double)i) / (double)j);
        }

    }
}
