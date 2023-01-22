// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, ItemStack, NetHandler

public class Packet103SetSlot extends Packet
{

    public Packet103SetSlot()
    {
    }

    public Packet103SetSlot(int i, int j, ItemStack itemstack)
    {
        a = i;
        b = j;
        c = itemstack != null ? itemstack.cloneItemStack() : itemstack;
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        b = datainputstream.readShort();
        short word0 = datainputstream.readShort();
        if(word0 >= 0)
        {
            byte byte0 = datainputstream.readByte();
            short word1 = datainputstream.readShort();
            c = new ItemStack(word0, byte0, word1);
        } else
        {
            c = null;
        }
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeShort(b);
        if(c == null)
        {
            dataoutputstream.writeShort(-1);
        } else
        {
            dataoutputstream.writeShort(c.id);
            dataoutputstream.writeByte(c.count);
            dataoutputstream.writeShort(c.getData());
        }
    }

    public int a()
    {
        return 8;
    }

    public int a;
    public int b;
    public ItemStack c;
}
