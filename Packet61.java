// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet61 extends Packet
{

    public Packet61()
    {
    }

    public Packet61(int i, int j, int k, int l, int i1)
    {
        a = i;
        c = j;
        d = k;
        e = l;
        b = i1;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readByte();
        e = datainputstream.readInt();
        b = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeByte(d);
        dataoutputstream.writeInt(e);
        dataoutputstream.writeInt(b);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 20;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
}
