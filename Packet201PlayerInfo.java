// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet201PlayerInfo extends Packet
{

    public Packet201PlayerInfo()
    {
    }

    public Packet201PlayerInfo(String s, boolean flag, int i)
    {
        a = s;
        b = flag;
        c = i;
    }

    public void a(DataInputStream datainputstream)
    {
        a = a(datainputstream, 16);
        b = datainputstream.readByte() != 0;
        c = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        a(a, dataoutputstream);
        dataoutputstream.writeByte(b ? 1 : 0);
        dataoutputstream.writeShort(c);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return a.length() + 2 + 1 + 2;
    }

    public String a;
    public boolean b;
    public int c;
}
