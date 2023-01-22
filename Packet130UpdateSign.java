// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet130UpdateSign extends Packet
{

    public Packet130UpdateSign()
    {
        k = true;
    }

    public Packet130UpdateSign(int i, int j, int k, String as[])
    {
        this.k = true;
        x = i;
        y = j;
        z = k;
        lines = as;
    }

    public void a(DataInputStream datainputstream)
    {
        x = datainputstream.readInt();
        y = datainputstream.readShort();
        z = datainputstream.readInt();
        lines = new String[4];
        for(int i = 0; i < 4; i++)
            lines[i] = a(datainputstream, 15);

    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(x);
        dataoutputstream.writeShort(y);
        dataoutputstream.writeInt(z);
        for(int i = 0; i < 4; i++)
            a(lines[i], dataoutputstream);

    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        int i = 0;
        for(int j = 0; j < 4; j++)
            i += lines[j].length();

        return i;
    }

    public int x;
    public int y;
    public int z;
    public String lines[];
}
