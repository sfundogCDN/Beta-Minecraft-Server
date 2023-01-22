// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, World, NetHandler

public class Packet53BlockChange extends Packet
{

    public Packet53BlockChange()
    {
        k = true;
    }

    public Packet53BlockChange(int i, int j, int k, World world)
    {
        this.k = true;
        a = i;
        b = j;
        c = k;
        material = world.getTypeId(i, j, k);
        data = world.getData(i, j, k);
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.read();
        c = datainputstream.readInt();
        material = datainputstream.read();
        data = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.write(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.write(material);
        dataoutputstream.write(data);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 11;
    }

    public int a;
    public int b;
    public int c;
    public int material;
    public int data;
}
