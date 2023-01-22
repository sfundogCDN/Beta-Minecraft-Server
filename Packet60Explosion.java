// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            Packet, ChunkPosition, NetHandler

public class Packet60Explosion extends Packet
{

    public Packet60Explosion()
    {
    }

    public Packet60Explosion(double d1, double d2, double d3, float f, 
            Set set)
    {
        a = d1;
        b = d2;
        c = d3;
        d = f;
        e = new HashSet(set);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readDouble();
        b = datainputstream.readDouble();
        c = datainputstream.readDouble();
        d = datainputstream.readFloat();
        int i = datainputstream.readInt();
        e = new HashSet();
        int j = (int)a;
        int k = (int)b;
        int l = (int)c;
        for(int i1 = 0; i1 < i; i1++)
        {
            int j1 = datainputstream.readByte() + j;
            int k1 = datainputstream.readByte() + k;
            int l1 = datainputstream.readByte() + l;
            e.add(new ChunkPosition(j1, k1, l1));
        }

    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeDouble(a);
        dataoutputstream.writeDouble(b);
        dataoutputstream.writeDouble(c);
        dataoutputstream.writeFloat(d);
        dataoutputstream.writeInt(e.size());
        int i = (int)a;
        int j = (int)b;
        int k = (int)c;
        int j1;
        for(Iterator iterator = e.iterator(); iterator.hasNext(); dataoutputstream.writeByte(j1))
        {
            ChunkPosition chunkposition = (ChunkPosition)iterator.next();
            int l = chunkposition.x - i;
            int i1 = chunkposition.y - j;
            j1 = chunkposition.z - k;
            dataoutputstream.writeByte(l);
            dataoutputstream.writeByte(i1);
        }

    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 32 + e.size() * 3;
    }

    public double a;
    public double b;
    public double c;
    public float d;
    public Set e;
}
