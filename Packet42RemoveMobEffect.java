// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, MobEffect, NetHandler

public class Packet42RemoveMobEffect extends Packet
{

    public Packet42RemoveMobEffect()
    {
    }

    public Packet42RemoveMobEffect(int i, MobEffect mobeffect)
    {
        a = i;
        b = (byte)(mobeffect.getEffectId() & 0xff);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(b);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 5;
    }

    public int a;
    public byte b;
}
