// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, EntityExperienceOrb, MathHelper, NetHandler

public class Packet26AddExpOrb extends Packet
{

    public Packet26AddExpOrb()
    {
    }

    public Packet26AddExpOrb(EntityExperienceOrb entityexperienceorb)
    {
        a = entityexperienceorb.id;
        b = MathHelper.floor(entityexperienceorb.locX * 32D);
        c = MathHelper.floor(entityexperienceorb.locY * 32D);
        d = MathHelper.floor(entityexperienceorb.locZ * 32D);
        e = entityexperienceorb.j_();
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readInt();
        c = datainputstream.readInt();
        d = datainputstream.readInt();
        e = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(b);
        dataoutputstream.writeInt(c);
        dataoutputstream.writeInt(d);
        dataoutputstream.writeShort(e);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return 18;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
}
