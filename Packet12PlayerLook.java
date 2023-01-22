// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet10Flying

public class Packet12PlayerLook extends Packet10Flying
{

    public Packet12PlayerLook()
    {
        hasLook = true;
    }

    public void a(DataInputStream datainputstream)
    {
        yaw = datainputstream.readFloat();
        pitch = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeFloat(yaw);
        dataoutputstream.writeFloat(pitch);
        super.a(dataoutputstream);
    }

    public int a()
    {
        return 9;
    }
}
