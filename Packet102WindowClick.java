// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler, ItemStack

public class Packet102WindowClick extends Packet
{

    public Packet102WindowClick()
    {
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readByte();
        b = datainputstream.readShort();
        c = datainputstream.readByte();
        d = datainputstream.readShort();
        f = datainputstream.readBoolean();
        short word0 = datainputstream.readShort();
        if(word0 >= 0)
        {
            byte byte0 = datainputstream.readByte();
            short word1 = datainputstream.readShort();
            e = new ItemStack(word0, byte0, word1);
        } else
        {
            e = null;
        }
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeByte(a);
        dataoutputstream.writeShort(b);
        dataoutputstream.writeByte(c);
        dataoutputstream.writeShort(d);
        dataoutputstream.writeBoolean(f);
        if(e == null)
        {
            dataoutputstream.writeShort(-1);
        } else
        {
            dataoutputstream.writeShort(e.id);
            dataoutputstream.writeByte(e.count);
            dataoutputstream.writeShort(e.getData());
        }
    }

    public int a()
    {
        return 11;
    }

    public int a;
    public int b;
    public int c;
    public short d;
    public ItemStack e;
    public boolean f;
}
