// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet27 extends Packet
{

    public Packet27()
    {
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readFloat();
        b = datainputstream.readFloat();
        e = datainputstream.readFloat();
        f = datainputstream.readFloat();
        c = datainputstream.readBoolean();
        d = datainputstream.readBoolean();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeFloat(a);
        dataoutputstream.writeFloat(b);
        dataoutputstream.writeFloat(e);
        dataoutputstream.writeFloat(f);
        dataoutputstream.writeBoolean(c);
        dataoutputstream.writeBoolean(d);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 18;
    }

    public float c()
    {
        return a;
    }

    public float d()
    {
        return e;
    }

    public float e()
    {
        return b;
    }

    public float f()
    {
        return f;
    }

    public boolean g()
    {
        return c;
    }

    public boolean h()
    {
        return d;
    }

    private float a;
    private float b;
    private boolean c;
    private boolean d;
    private float e;
    private float f;
}
