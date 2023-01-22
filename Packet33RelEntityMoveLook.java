// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet30Entity

public class Packet33RelEntityMoveLook extends Packet30Entity
{

    public Packet33RelEntityMoveLook()
    {
        g = true;
    }

    public Packet33RelEntityMoveLook(int i, byte byte0, byte byte1, byte byte2, byte byte3, byte byte4)
    {
        super(i);
        b = byte0;
        c = byte1;
        d = byte2;
        e = byte3;
        f = byte4;
        g = true;
    }

    public void a(DataInputStream datainputstream)
    {
        super.a(datainputstream);
        b = datainputstream.readByte();
        c = datainputstream.readByte();
        d = datainputstream.readByte();
        e = datainputstream.readByte();
        f = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        super.a(dataoutputstream);
        dataoutputstream.writeByte(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeByte(d);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeByte(f);
    }

    public int a()
    {
        return 9;
    }
}
