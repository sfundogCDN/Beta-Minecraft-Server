// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, Entity, NetHandler

public class Packet39AttachEntity extends Packet
{

    public Packet39AttachEntity()
    {
    }

    public Packet39AttachEntity(Entity entity, Entity entity1)
    {
        a = entity.id;
        b = entity1 == null ? -1 : entity1.id;
    }

    public int a()
    {
        return 8;
    }

    public void a(DataInputStream datainputstream)
    {
        a = datainputstream.readInt();
        b = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeInt(a);
        dataoutputstream.writeInt(b);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a;
    public int b;
}
