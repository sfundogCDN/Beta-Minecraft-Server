// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet38EntityStatus extends Packet
{

    public Packet38EntityStatus()
    {
    }

    public Packet38EntityStatus(int i, byte byte0)
    {
        a = i;
        b = byte0;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(b);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 5;
    }

    public int a;
    public byte b;
}
