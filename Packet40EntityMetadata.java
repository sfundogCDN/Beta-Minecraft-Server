// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Packet, DataWatcher, NetHandler

public class Packet40EntityMetadata extends Packet
{

    public Packet40EntityMetadata()
    {
    }

    public Packet40EntityMetadata(int i, DataWatcher datawatcher)
    {
        a = i;
        b = datawatcher.b();
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = DataWatcher.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        DataWatcher.a(b, dataoutputstream);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 5;
    }

    public int a;
    private List b;
}
