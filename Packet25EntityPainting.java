// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, EntityPainting, EnumArt, NetHandler

public class Packet25EntityPainting extends Packet
{

    public Packet25EntityPainting()
    {
    }

    public Packet25EntityPainting(EntityPainting entitypainting)
    {
        a = entitypainting.id;
        b = entitypainting.b;
        c = entitypainting.c;
        d = entitypainting.d;
        e = entitypainting.a;
        f = entitypainting.e.A;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        f = a(datainputstream, EnumArt.z);
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        a(f, dataoutputstream);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeInt(e);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 24;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
}
