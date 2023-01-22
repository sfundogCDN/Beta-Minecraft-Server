// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet100OpenWindow extends Packet
{

    public Packet100OpenWindow()
    {
    }

    public Packet100OpenWindow(int i, int j, String s, int k)
    {
        a = i;
        b = j;
        c = s;
        d = k;
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        b = datainputstream.readByte();
        c = a(datainputstream, 16);
        d = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeByte(b);
        a(c, dataoutputstream);
        dataoutputstream.writeByte(d);
    }

    public int a()
    {
        return 3 + c.length();
    }

    public int a;
    public int b;
    public String c;
    public int d;
}
