// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

// Referenced classes of package net.minecraft.server:
//            NBTTagEnd, NBTTagByte, NBTTagShort, NBTTagInt, 
//            NBTTagLong, NBTTagFloat, NBTTagDouble, NBTTagByteArray, 
//            NBTTagString, NBTTagList, NBTTagCompound

public abstract class NBTBase
{

    public NBTBase()
    {
        a = null;
    }

    abstract void a(DataOutput dataoutput);

    abstract void a(DataInput datainput);

    public abstract byte a();

    public String b()
    {
        if(a == null)
            return "";
        else
            return a;
    }

    public NBTBase a(String s)
    {
        a = s;
        return this;
    }

    public static NBTBase b(DataInput datainput)
    {
        byte byte0 = datainput.readByte();
        if(byte0 == 0)
        {
            return new NBTTagEnd();
        } else
        {
            NBTBase nbtbase = a(byte0);
            nbtbase.a = datainput.readUTF();
            nbtbase.a(datainput);
            return nbtbase;
        }
    }

    public static void a(NBTBase nbtbase, DataOutput dataoutput)
    {
        dataoutput.writeByte(nbtbase.a());
        if(nbtbase.a() == 0)
        {
            return;
        } else
        {
            dataoutput.writeUTF(nbtbase.b());
            nbtbase.a(dataoutput);
            return;
        }
    }

    public static NBTBase a(byte byte0)
    {
        switch(byte0)
        {
        case 0: // '\0'
            return new NBTTagEnd();

        case 1: // '\001'
            return new NBTTagByte();

        case 2: // '\002'
            return new NBTTagShort();

        case 3: // '\003'
            return new NBTTagInt();

        case 4: // '\004'
            return new NBTTagLong();

        case 5: // '\005'
            return new NBTTagFloat();

        case 6: // '\006'
            return new NBTTagDouble();

        case 7: // '\007'
            return new NBTTagByteArray();

        case 8: // '\b'
            return new NBTTagString();

        case 9: // '\t'
            return new NBTTagList();

        case 10: // '\n'
            return new NBTTagCompound();
        }
        return null;
    }

    public static String b(byte byte0)
    {
        switch(byte0)
        {
        case 0: // '\0'
            return "TAG_End";

        case 1: // '\001'
            return "TAG_Byte";

        case 2: // '\002'
            return "TAG_Short";

        case 3: // '\003'
            return "TAG_Int";

        case 4: // '\004'
            return "TAG_Long";

        case 5: // '\005'
            return "TAG_Float";

        case 6: // '\006'
            return "TAG_Double";

        case 7: // '\007'
            return "TAG_Byte_Array";

        case 8: // '\b'
            return "TAG_String";

        case 9: // '\t'
            return "TAG_List";

        case 10: // '\n'
            return "TAG_Compound";
        }
        return "UNKNOWN";
    }

    private String a;
}
