// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet29DestroyEntity extends Packet
{

    public Packet29DestroyEntity()
    {
    }

    public Packet29DestroyEntity(int i)
    {
        a = i;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
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
}
