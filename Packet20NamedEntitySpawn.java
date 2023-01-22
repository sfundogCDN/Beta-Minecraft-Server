// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, EntityHuman, MathHelper, InventoryPlayer, 
//            ItemStack, NetHandler

public class Packet20NamedEntitySpawn extends Packet
{

    public Packet20NamedEntitySpawn()
    {
    }

    public Packet20NamedEntitySpawn(EntityHuman entityhuman)
    {
        a = entityhuman.id;
        b = entityhuman.name;
        c = MathHelper.floor(entityhuman.locX * 32D);
        d = MathHelper.floor(entityhuman.locY * 32D);
        e = MathHelper.floor(entityhuman.locZ * 32D);
        f = (byte)(int)((entityhuman.yaw * 256F) / 360F);
        g = (byte)(int)((entityhuman.pitch * 256F) / 360F);
        ItemStack itemstack = entityhuman.inventory.getItemInHand();
        h = itemstack != null ? itemstack.id : 0;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = a(datainputstream, 16);
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = datainputstream.readInt();
        f = datainputstream.readByte();
        g = datainputstream.readByte();
        h = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        a(b, dataoutputstream);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeInt(e);
        dataoutputstream.writeByte(f);
        dataoutputstream.writeByte(g);
        dataoutputstream.writeShort(h);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 28;
    }

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;
}
