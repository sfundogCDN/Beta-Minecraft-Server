// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Packet, ItemStack, NetHandler

public class Packet104WindowItems extends Packet
{

    public Packet104WindowItems()
    {
    }

    public Packet104WindowItems(int i, List list)
    {
        a = i;
        b = new ItemStack[list.size()];
        for(int j = 0; j < b.length; j++)
        {
            ItemStack itemstack = (ItemStack)list.get(j);
            b[j] = itemstack != null ? itemstack.cloneItemStack() : null;
        }

    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        short word0 = datainputstream.readShort();
        b = new ItemStack[word0];
        for(int i = 0; i < word0; i++)
        {
            short word1 = datainputstream.readShort();
            if(word1 >= 0)
            {
                byte byte0 = datainputstream.readByte();
                short word2 = datainputstream.readShort();
                b[i] = new ItemStack(word1, byte0, word2);
            }
        }

    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeShort(b.length);
        for(int i = 0; i < b.length; i++)
            if(b[i] == null)
            {
                dataoutputstream.writeShort(-1);
            } else
            {
                dataoutputstream.writeShort((short)b[i].id);
                dataoutputstream.writeByte((byte)b[i].count);
                dataoutputstream.writeShort((short)b[i].getData());
            }

    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 3 + b.length * 5;
    }

    public int a;
    public ItemStack b[];
}
