// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet16BlockItemSwitch extends Packet
{

    public Packet16BlockItemSwitch()
    {
    }

    public void a(DataInputStream datainputstream)
    {
        itemInHandIndex = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeShort(itemInHandIndex);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 2;
    }

    public int itemInHandIndex;
}
