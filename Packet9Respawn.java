// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet9Respawn extends Packet
{

    public Packet9Respawn()
    {
    }

    public Packet9Respawn(byte byte0, byte byte1, long l, int i, int j)
    {
        b = byte0;
        c = byte1;
        a = l;
        d = i;
        e = j;
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        b = datainputstream.readByte();
        c = datainputstream.readByte();
        e = datainputstream.readByte();
        d = datainputstream.readShort();
        a = datainputstream.readLong();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeShort(d);
        dataoutputstream.writeLong(a);
    }

    public int a()
    {
        return 13;
    }

    public long a;
    public int b;
    public int c;
    public int d;
    public int e;
}
