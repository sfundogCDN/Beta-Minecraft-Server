// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.server:
//            EmptyClass2

class ChunkFileFilter
    implements FileFilter
{

    private ChunkFileFilter()
    {
    }

    public boolean accept(File file)
    {
        if(file.isDirectory())
        {
            Matcher matcher = a.matcher(file.getName());
            return matcher.matches();
        } else
        {
            return false;
        }
    }

    ChunkFileFilter(EmptyClass2 emptyclass2)
    {
        this();
    }

    public static final Pattern a = Pattern.compile("[0-9a-z]|([0-9a-z][0-9a-z])");

}
