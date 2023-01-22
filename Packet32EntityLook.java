// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet30Entity

public class Packet32EntityLook extends Packet30Entity
{

    public Packet32EntityLook()
    {
        g = true;
    }

    public Packet32EntityLook(int i, byte byte0, byte byte1)
    {
        super(i);
        e = byte0;
        f = byte1;
        g = true;
    }

    public void a(DataInputStream datainputstream)
    {
        super.a(datainputstream);
        e = datainputstream.readByte();
        f = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        super.a(dataoutputstream);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeByte(f);
    }

    public int a()
    {
        return 6;
    }
}
