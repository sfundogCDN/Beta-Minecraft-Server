// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet14BlockDig extends Packet
{

    public Packet14BlockDig()
    {
    }

    public void a(DataInputStream datainputstream)
    {
        e = datainputstream.read();
        a = datainputstream.readInt();
        b = datainputstream.read();
        c = datainputstream.readInt();
        face = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.write(e);
        dataoutputstream.writeInt(a);
        dataoutputstream.write(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.write(face);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 11;
    }

    public int a;
    public int b;
    public int c;
    public int face;
    public int e;
}
