// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet105CraftProgressBar extends Packet
{

    public Packet105CraftProgressBar()
    {
    }

    public Packet105CraftProgressBar(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        b = datainputstream.readShort();
        c = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeShort(c);
    }

    public int a()
    {
        return 5;
    }

    public int a;
    public int b;
    public int c;
}
