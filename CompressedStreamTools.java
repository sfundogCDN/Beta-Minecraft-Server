// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package net.minecraft.server:
//            NBTBase, NBTTagCompound

public class CompressedStreamTools
{

    public CompressedStreamTools()
    {
    }

    public static NBTTagCompound a(InputStream inputstream)
    {
        DataInputStream datainputstream = new DataInputStream(new GZIPInputStream(inputstream));
        NBTTagCompound nbttagcompound = a(((DataInput) (datainputstream)));
        datainputstream.close();
        return nbttagcompound;
        Exception exception;
        exception;
        datainputstream.close();
        throw exception;
    }

    public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream)
    {
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(outputstream));
        a(nbttagcompound, ((DataOutput) (dataoutputstream)));
        dataoutputstream.close();
        break MISSING_BLOCK_LABEL_35;
        Exception exception;
        exception;
        dataoutputstream.close();
        throw exception;
    }

    public static NBTTagCompound a(DataInput datainput)
    {
        NBTBase nbtbase = NBTBase.b(datainput);
        if(nbtbase instanceof NBTTagCompound)
            return (NBTTagCompound)nbtbase;
        else
            throw new IOException("Root tag must be a named compound tag");
    }

    public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput)
    {
        NBTBase.a(nbttagcompound, dataoutput);
    }
}
