// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, MathHelper, EntityWeatherStorm, 
//            NetHandler

public class Packet71Weather extends Packet
{

    public Packet71Weather()
    {
    }

    public Packet71Weather(Entity entity)
    {
        a = entity.id;
        b = MathHelper.floor(entity.locX * 32D);
        c = MathHelper.floor(entity.locY * 32D);
        d = MathHelper.floor(entity.locZ * 32D);
        if(entity instanceof EntityWeatherStorm)
            e = 1;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        e = datainputstream.readByte();
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 17;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
}
