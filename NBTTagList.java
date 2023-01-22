// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            NBTBase

public class NBTTagList extends NBTBase
{

    public NBTTagList()
    {
        a = new ArrayList();
    }

    void a(DataOutput dataoutput)
    {
        if(a.size() > 0)
            b = ((NBTBase)a.get(0)).a();
        else
            b = 1;
        dataoutput.writeByte(b);
        dataoutput.writeInt(a.size());
        for(int i = 0; i < a.size(); i++)
            ((NBTBase)a.get(i)).a(dataoutput);

    }

    void a(DataInput datainput)
    {
        b = datainput.readByte();
        int i = datainput.readInt();
        a = new ArrayList();
        for(int j = 0; j < i; j++)
        {
            NBTBase nbtbase = NBTBase.a(b);
            nbtbase.a(datainput);
            a.add(nbtbase);
        }

    }

    public byte a()
    {
        return 9;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(a.size()).append(" entries of type ").append(NBTBase.b(b)).toString();
    }

    public void a(NBTBase nbtbase)
    {
        b = nbtbase.a();
        a.add(nbtbase);
    }

    public NBTBase a(int i)
    {
        return (NBTBase)a.get(i);
    }

    public int c()
    {
        return a.size();
    }

    private List a;
    private byte b;
}
