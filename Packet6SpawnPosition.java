// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet6SpawnPosition extends Packet
{

    public Packet6SpawnPosition()
    {
    }

    public Packet6SpawnPosition(int i, int j, int k)
    {
        x = i;
        y = j;
        z = k;
    }

    public void a(DataInputStream datainputstream)
    {
        x = datainputstream.readInt();
        y = datainputstream.readInt();
        z = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(x);
        dataoutputstream.writeInt(y);
        dataoutputstream.writeInt(z);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 12;
    }

    public int x;
    public int y;
    public int z;
}
