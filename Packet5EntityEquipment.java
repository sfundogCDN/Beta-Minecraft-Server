// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, ItemStack, NetHandler

public class Packet5EntityEquipment extends Packet
{

    public Packet5EntityEquipment()
    {
    }

    public Packet5EntityEquipment(int i, int j, ItemStack itemstack)
    {
        a = i;
        b = j;
        if(itemstack == null)
        {
            c = -1;
            d = 0;
        } else
        {
            c = itemstack.id;
            d = itemstack.getData();
        }
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
        return 8;
    }

    public int a;
    public int b;
    public int c;
    public int d;
}
