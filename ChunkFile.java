// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.server:
//            ChunkFilenameFilter

class ChunkFile
    implements Comparable
{

    public ChunkFile(File file)
    {
        a = file;
        Matcher matcher = ChunkFilenameFilter.a.matcher(file.getName());
        if(matcher.matches())
        {
            b = Integer.parseInt(matcher.group(1), 36);
            c = Integer.parseInt(matcher.group(2), 36);
        } else
        {
            b = 0;
            c = 0;
        }
    }

    public int compareTo(ChunkFile chunkfile)
    {
        int i = b >> 5;
        int j = chunkfile.b >> 5;
        if(i == j)
        {
            int k = c >> 5;
            int l = chunkfile.c >> 5;
            return k - l;
        } else
        {
            return i - j;
        }
    }

    public File a()
    {
        return a;
    }

    public int b()
    {
        return b;
    }

    public int c()
    {
        return c;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((ChunkFile)obj);
    }

    private final File a;
    private final int b;
    private final int c;
}
