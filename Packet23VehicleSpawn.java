// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, MathHelper, NetHandler

public class Packet23VehicleSpawn extends Packet
{

    public Packet23VehicleSpawn()
    {
    }

    public Packet23VehicleSpawn(Entity entity, int j)
    {
        this(entity, j, 0);
    }

    public Packet23VehicleSpawn(Entity entity, int j, int k)
    {
        a = entity.id;
        b = MathHelper.floor(entity.locX * 32D);
        c = MathHelper.floor(entity.locY * 32D);
        d = MathHelper.floor(entity.locZ * 32D);
        h = j;
        i = k;
        if(k > 0)
        {
            double d1 = entity.motX;
            double d2 = entity.motY;
            double d3 = entity.motZ;
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
            e = (int)(d1 * 8000D);
            f = (int)(d2 * 8000D);
            g = (int)(d3 * 8000D);
        }
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        h = datainputstream.readByte();
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        i = datainputstream.readInt();
        if(i > 0)
        {
            e = datainputstream.readShort();
            f = datainputstream.readShort();
            g = datainputstream.readShort();
        }
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(h);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeInt(i);
        if(i > 0)
        {
            dataoutputstream.writeShort(e);
            dataoutputstream.writeShort(f);
            dataoutputstream.writeShort(g);
        }
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 21 + i <= 0 ? 0 : 6;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
}
