// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet107SetCreativeSlot extends Packet
{

    public Packet107SetCreativeSlot()
    {
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readShort();
        b = datainputstream.readShort();
        c = datainputstream.readShort();
        d = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeShort(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeShort(c);
        dataoutputstream.writeShort(d);
    }

    public int a()
    {
        return 8;
    }

    public int a;
    public int b;
    public int c;
    public int d;
}
