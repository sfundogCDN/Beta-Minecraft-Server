// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.ByteArrayOutputStream;

// Referenced classes of package net.minecraft.server:
//            RegionFile

class ChunkBuffer extends ByteArrayOutputStream
{

    public ChunkBuffer(RegionFile regionfile, int i, int j)
    {
        a = regionfile;
        super(8096);
        b = i;
        c = j;
    }

    public void close()
    {
        a.a(b, c, buf, count);
    }

    private int b;
    private int c;
    final RegionFile a;
}
