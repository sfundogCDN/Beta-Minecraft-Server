// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet70Bed extends Packet
{

    public Packet70Bed()
    {
    }

    public Packet70Bed(int i, int j)
    {
        b = i;
        c = j;
    }

    public void a(DataInputStream datainputstream)
    {
        b = datainputstream.readByte();
        c = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(b);
        dataoutputstream.writeByte(c);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 2;
    }

    public static final String a[] = {
        "tile.bed.notValid", null, null, "gameMode.changed"
    };
    public int b;
    public int c;

}
