// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet1Login extends Packet
{

    public Packet1Login()
    {
    }

    public Packet1Login(String s, int i, long l, int j, byte byte0, byte byte1, 
            byte byte2, byte byte3)
    {
        name = s;
        a = i;
        c = l;
        e = byte0;
        f = byte1;
        d = j;
        g = byte2;
        h = byte3;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        name = a(datainputstream, 16);
        c = datainputstream.readLong();
        d = datainputstream.readInt();
        e = datainputstream.readByte();
        f = datainputstream.readByte();
        g = datainputstream.readByte();
        h = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        a(name, dataoutputstream);
        dataoutputstream.writeLong(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeByte(f);
        dataoutputstream.writeByte(g);
        dataoutputstream.writeByte(h);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 4 + name.length() + 4 + 7 + 4;
    }

    public int a;
    public String name;
    public long c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public byte h;
}
