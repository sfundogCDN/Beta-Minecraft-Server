// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagByteArray extends NBTBase
{

    public NBTTagByteArray()
    {
    }

    public NBTTagByteArray(byte abyte0[])
    {
        a = abyte0;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeInt(a.length);
        dataoutput.write(a);
    }

    void a(DataInput datainput)
    {
        int i = datainput.readInt();
        a = new byte[i];
        datainput.readFully(a);
    }

    public byte a()
    {
        return 7;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[").append(a.length).append(" bytes]").toString();
    }

    public byte a[];
}
