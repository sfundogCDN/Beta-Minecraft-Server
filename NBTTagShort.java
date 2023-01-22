// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagShort extends NBTBase
{

    public NBTTagShort()
    {
    }

    public NBTTagShort(short word0)
    {
        a = word0;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeShort(a);
    }

    void a(DataInput datainput)
    {
        a = datainput.readShort();
    }

    public byte a()
    {
        return 2;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a).toString();
    }

    public short a;
}
