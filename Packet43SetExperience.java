// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet43SetExperience extends Packet
{

    public Packet43SetExperience()
    {
    }

    public Packet43SetExperience(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        c = datainputstream.readByte();
        b = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeShort(b);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 4;
    }

    public int a;
    public int b;
    public int c;
}
