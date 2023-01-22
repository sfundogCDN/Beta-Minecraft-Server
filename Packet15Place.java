// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, ItemStack, NetHandler

public class Packet15Place extends Packet
{

    public Packet15Place()
    {
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.read();
        c = datainputstream.readInt();
        face = datainputstream.read();
        short word0 = datainputstream.readShort();
        if(word0 >= 0)
        {
            byte byte0 = datainputstream.readByte();
            short word1 = datainputstream.readShort();
            itemstack = new ItemStack(word0, byte0, word1);
        } else
        {
            itemstack = null;
        }
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.write(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.write(face);
        if(itemstack == null)
        {
            dataoutputstream.writeShort(-1);
        } else
        {
            dataoutputstream.writeShort(itemstack.id);
            dataoutputstream.writeByte(itemstack.count);
            dataoutputstream.writeShort(itemstack.getData());
        }
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 15;
    }

    public int a;
    public int b;
    public int c;
    public int face;
    public ItemStack itemstack;
}
