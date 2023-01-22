// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Packet, EntityLiving, EntityTypes, MathHelper, 
//            DataWatcher, NetHandler

public class Packet24MobSpawn extends Packet
{

    public Packet24MobSpawn()
    {
    }

    public Packet24MobSpawn(EntityLiving entityliving)
    {
        a = entityliving.id;
        b = (byte)EntityTypes.a(entityliving);
        c = MathHelper.floor(entityliving.locX * 32D);
        d = MathHelper.floor(entityliving.locY * 32D);
        e = MathHelper.floor(entityliving.locZ * 32D);
        f = (byte)(int)((entityliving.yaw * 256F) / 360F);
        g = (byte)(int)((entityliving.pitch * 256F) / 360F);
        h = entityliving.getDataWatcher();
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readByte();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = datainputstream.readInt();
        f = datainputstream.readByte();
        g = datainputstream.readByte();
        i = DataWatcher.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeByte(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeInt(e);
        dataoutputstream.writeByte(f);
        dataoutputstream.writeByte(g);
        h.a(dataoutputstream);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 20;
    }

    public int a;
    public byte b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    private DataWatcher h;
    private List i;
}
