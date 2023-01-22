// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet106Transaction extends Packet
{

    public Packet106Transaction()
    {
    }

    public Packet106Transaction(int i, short word0, boolean flag)
    {
        a = i;
        b = word0;
        c = flag;
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        b = datainputstream.readShort();
        c = datainputstream.readByte() != 0;
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeByte(c ? 1 : 0);
    }

    public int a()
    {
        return 4;
    }

    public int a;
    public short b;
    public boolean c;
}
