// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, World, Chunk, NetHandler

public class Packet52MultiBlockChange extends Packet
{

    public Packet52MultiBlockChange()
    {
        k = true;
    }

    public Packet52MultiBlockChange(int i, int j, short aword0[], int k, World world)
    {
        this.k = true;
        a = i;
        b = j;
        f = k;
        c = new short[k];
        d = new byte[k];
        e = new byte[k];
        Chunk chunk = world.getChunkAt(i, j);
        for(int l = 0; l < k; l++)
        {
            int i1 = aword0[l] >> 12 & 0xf;
            int j1 = aword0[l] >> 8 & 0xf;
            int k1 = aword0[l] & 0xff;
            c[l] = aword0[l];
            d[l] = (byte)chunk.getTypeId(i1, k1, j1);
            e[l] = (byte)chunk.getData(i1, k1, j1);
        }

    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readInt();
        f = datainputstream.readShort() & 0xffff;
        c = new short[f];
        d = new byte[f];
        e = new byte[f];
        for(int i = 0; i < f; i++)
            c[i] = datainputstream.readShort();

        datainputstream.readFully(d);
        datainputstream.readFully(e);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeShort((short)f);
        for(int i = 0; i < f; i++)
            dataoutputstream.writeShort(c[i]);

        dataoutputstream.write(d);
        dataoutputstream.write(e);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 10 + f * 4;
    }

    public int a;
    public int b;
    public short c[];
    public byte d[];
    public byte e[];
    public int f;
}
