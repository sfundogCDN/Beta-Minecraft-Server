// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet7UseEntity extends Packet
{

    public Packet7UseEntity()
    {
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        target = datainputstream.readInt();
        c = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(target);
        dataoutputstream.writeByte(c);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 9;
    }

    public int a;
    public int target;
    public int c;
}
