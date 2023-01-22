// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet30Entity

public class Packet31RelEntityMove extends Packet30Entity
{

    public Packet31RelEntityMove()
    {
    }

    public Packet31RelEntityMove(int i, byte byte0, byte byte1, byte byte2)
    {
        super(i);
        b = byte0;
        c = byte1;
        d = byte2;
    }

    public void a(DataInputStream datainputstream)
    {
        super.a(datainputstream);
        b = datainputstream.readByte();
        c = datainputstream.readByte();
        d = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        super.a(dataoutputstream);
        dataoutputstream.writeByte(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeByte(d);
    }

    public int a()
    {
        return 7;
    }
}
