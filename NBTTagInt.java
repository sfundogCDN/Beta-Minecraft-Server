// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagInt extends NBTBase
{

    public NBTTagInt()
    {
    }

    public NBTTagInt(int i)
    {
        a = i;
    }

    void a(DataOutput dataoutput)
    {
        dataoutput.writeInt(a);
    }

    void a(DataInput datainput)
    {
        a = datainput.readInt();
    }

    public byte a()
    {
        return 3;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a).toString();
    }

    public int a;
}
