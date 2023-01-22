// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Packet3Chat.java

package net.minecraft.server;

import java.io.*;

// Referenced classes of package net.minecraft.server:
//            Packet, NetHandler

public class Packet3Chat extends Packet
{

    public Packet3Chat()
    {
    }

    public Packet3Chat(String s)
    {
        message = s;
    }

    public void a(DataInputStream datainputstream)
        throws IOException
    {
        message = a(datainputstream, 119);
    }

    public void a(DataOutputStream dataoutputstream)
        throws IOException
    {
        a(message, dataoutputstream);
    }

    public void a(NetHandler nethandler)
    {
        nethandler.a(this);
    }

    public int a()
    {
        return message.length();
    }

    public String message;
}
