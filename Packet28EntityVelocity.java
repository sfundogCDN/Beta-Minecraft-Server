// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, NetHandler

public class Packet28EntityVelocity extends Packet
{

    public Packet28EntityVelocity()
    {
    }

    public Packet28EntityVelocity(Entity entity)
    {
        this(entity.id, entity.motX, entity.motY, entity.motZ);
    }

    public Packet28EntityVelocity(int i, double d1, double d2, double d3)
    {
        a = i;
        double d4 = 3.8999999999999999D;
        if(d1 < -d4)
            d1 = -d4;
        if(d2 < -d4)
            d2 = -d4;
        if(d3 < -d4)
            d3 = -d4;
        if(d1 > d4)
            d1 = d4;
        if(d2 > d4)
            d2 = d4;
        if(d3 > d4)
            d3 = d4;
        b = (int)(d1 * 8000D);
        c = (int)(d2 * 8000D);
        d = (int)(d3 * 8000D);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readShort();
        c = datainputstream.readShort();
        d = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeShort(c);
        dataoutputstream.writeShort(d);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 10;
    }

    public int a;
    public int b;
    public int c;
    public int d;
}
