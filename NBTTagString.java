// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagString extends NBTBase
{

    public NBTTagString()
    {
    }

    public NBTTagString(String s)
    {
        a = s;
        if(s == null)
            throw new IllegalArgumentException("Empty string not allowed");
        else
            return;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeUTF(a);
    }

    void a(DataInput datainput)
    {
        a = datainput.readUTF();
    }

    public byte a()
    {
        return 8;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a).toString();
    }

    public String a;
}
