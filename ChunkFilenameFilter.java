// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.server:
//            EmptyClass2

class ChunkFilenameFilter
    implements FilenameFilter
{

    private ChunkFilenameFilter()
    {
    }

    public boolean accept(File file, String s)
    {
        Matcher matcher = a.matcher(s);
        return matcher.matches();
    }

    ChunkFilenameFilter(EmptyClass2 emptyclass2)
    {
        this();
    }

    public static final Pattern a = Pattern.compile("c\\.(-?[0-9a-z]+)\\.(-?[0-9a-z]+)\\.dat");

}
