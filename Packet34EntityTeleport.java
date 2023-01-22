// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, MathHelper, NetHandler

public class Packet34EntityTeleport extends Packet
{

    public Packet34EntityTeleport()
    {
    }

    public Packet34EntityTeleport(Entity entity)
    {
        a = entity.id;
        b = MathHelper.floor(entity.locX * 32D);
        c = MathHelper.floor(entity.locY * 32D);
        d = MathHelper.floor(entity.locZ * 32D);
        e = (byte)(int)((entity.yaw * 256F) / 360F);
        f = (byte)(int)((entity.pitch * 256F) / 360F);
    }

    public Packet34EntityTeleport(int i, int j, int k, int l, byte byte0, byte byte1)
    {
        a = i;
        b = j;
        c = k;
        d = l;
        e = byte0;
        f = byte1;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = (byte)datainputstream.read();
        f = (byte)datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.write(e);
        dataoutputstream.write(f);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 34;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
}
