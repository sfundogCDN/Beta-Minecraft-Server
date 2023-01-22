// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, MobEffect, NetHandler

public class Packet41MobEffect extends Packet
{

    public Packet41MobEffect()
    {
    }

    public Packet41MobEffect(int i, MobEffect mobeffect)
    {
        a = i;
        b = (byte)(mobeffect.getEffectId() & 0xff);
        c = (byte)(mobeffect.getAmplifier() & 0xff);
        d = (short)mobeffect.getDuration();
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readByte();
        c = datainputstream.readByte();
        d = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeShort(d);
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
    public byte b;
    public byte c;
    public short d;
}
