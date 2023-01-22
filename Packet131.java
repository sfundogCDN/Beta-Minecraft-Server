// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet131 extends Packet
{

    public Packet131()
    {
        k = true;
    }

    public Packet131(short word0, short word1, byte abyte0[])
    {
        k = true;
        a = word0;
        b = word1;
        c = abyte0;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readShort();
        b = datainputstream.readShort();
        c = new byte[datainputstream.readByte() & 0xff];
        datainputstream.readFully(c);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeShort(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeByte(c.length);
        dataoutputstream.write(c);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 4 + c.length;
    }

    public short a;
    public short b;
    public byte c[];
}
