// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet8UpdateHealth extends Packet
{

    public Packet8UpdateHealth()
    {
    }

    public Packet8UpdateHealth(int i, int j, float f)
    {
        a = i;
        b = j;
        c = f;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readShort();
        b = datainputstream.readShort();
        c = datainputstream.readFloat();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeShort(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeFloat(c);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 8;
    }

    public int a;
    public int b;
    public float c;
}
