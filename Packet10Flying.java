// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet10Flying extends Packet
{

    public Packet10Flying()
    {
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream)
    {
        g = datainputstream.read() != 0;
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.write(g ? 1 : 0);
    }

    public int a()
    {
        return 1;
    }

    public double x;
    public double y;
    public double z;
    public double stance;
    public float yaw;
    public float pitch;
    public boolean g;
    public boolean h;
    public boolean hasLook;
}
