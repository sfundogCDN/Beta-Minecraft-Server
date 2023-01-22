// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagByte extends NBTBase
{

    public NBTTagByte()
    {
    }

    public NBTTagByte(byte byte0)
    {
        a = byte0;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeByte(a);
    }

    void a(DataInput datainput)
    {
        a = datainput.readByte();
    }

    public byte a()
    {
        return 1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a).toString();
    }

    public byte a;
}
