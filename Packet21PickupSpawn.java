// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, EntityItem, ItemStack, MathHelper, 
//            NetHandler

public class Packet21PickupSpawn extends Packet
{

    public Packet21PickupSpawn()
    {
    }

    public Packet21PickupSpawn(EntityItem entityitem)
    {
        a = entityitem.id;
        h = entityitem.itemStack.id;
        i = entityitem.itemStack.count;
        l = entityitem.itemStack.getData();
        b = MathHelper.floor(entityitem.locX * 32D);
        c = MathHelper.floor(entityitem.locY * 32D);
        d = MathHelper.floor(entityitem.locZ * 32D);
        e = (byte)(int)(entityitem.motX * 128D);
        f = (byte)(int)(entityitem.motY * 128D);
        g = (byte)(int)(entityitem.motZ * 128D);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        h = datainputstream.readShort();
        i = datainputstream.readByte();
        l = datainputstream.readShort();
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = datainputstream.readByte();
        f = datainputstream.readByte();
        g = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeShort(h);
        dataoutputstream.writeByte(i);
        dataoutputstream.writeShort(l);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeByte(e);
        dataoutputstream.writeByte(f);
        dataoutputstream.writeByte(g);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 24;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public int h;
    public int i;
    public int l;
}
