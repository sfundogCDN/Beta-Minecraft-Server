// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, NetHandler

public class Packet17 extends Packet
{

    public Packet17()
    {
    }

    public Packet17(Entity entity, int i, int j, int k, int l)
    {
        e = i;
        b = j;
        c = k;
        d = l;
        a = entity.id;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        e = datainputstream.readByte();
        b = datainputstream.readInt();
        c = datainputstream.readByte();
        d = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeInt(d);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 14;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
}
