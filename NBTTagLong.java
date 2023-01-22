// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagLong extends NBTBase
{

    public NBTTagLong()
    {
    }

    public NBTTagLong(long l)
    {
        a = l;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeLong(a);
    }

    void a(DataInput datainput)
    {
        a = datainput.readLong();
    }

    public byte a()
    {
        return 4;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a).toString();
    }

    public long a;
}
