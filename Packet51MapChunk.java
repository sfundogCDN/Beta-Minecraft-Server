// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Packet51MapChunk.java

package net.minecraft.server;

import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

// Referenced classes of package net.minecraft.server:
//            Packet, World, NetHandler

public class Packet51MapChunk extends Packet
{

    public Packet51MapChunk()
    {
        k = true;
    }

    public Packet51MapChunk(int i, int j, int k, int l, int i1, int j1, World world)
    {
        this(i, j, k, l, i1, j1, world.getMultiChunkData(i, j, k, l, i1, j1));
    }

    public Packet51MapChunk(int i, int j, int k, int l, int i1, int j1, byte data[])
    {
        this.k = true;
        a = i;
        b = j;
        c = k;
        d = l;
        e = i1;
        f = j1;
        rawData = data;
    }

    public void a(DataInputStream datainputstream)
        throws IOException
    {
        Inflater inflater;
        a = datainputstream.readInt();
        b = datainputstream.readShort();
        c = datainputstream.readInt();
        d = datainputstream.read() + 1;
        e = datainputstream.read() + 1;
        f = datainputstream.read() + 1;
        h = datainputstream.readInt();
        byte abyte[] = new byte[h];
        datainputstream.readFully(abyte);
        g = new byte[(d * e * f * 5) / 2];
        inflater = new Inflater();
        inflater.setInput(abyte);
        try
        {
            inflater.inflate(g);
        }
        catch(DataFormatException dataformatexception)
        {
            throw new IOException("Bad compressed data format");
        }
        inflater.end();
        break MISSING_BLOCK_LABEL_148;
        Exception exception;
        exception;
        inflater.end();
        throw exception;
    }

    public void a(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.write(d - 1);
        dataoutputstream.write(e - 1);
        dataoutputstream.write(f - 1);
        dataoutputstream.writeInt(h);
        dataoutputstream.write(g, 0, h);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 17 + h;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public byte g[];
    public int h;
    public byte rawData[];
}
