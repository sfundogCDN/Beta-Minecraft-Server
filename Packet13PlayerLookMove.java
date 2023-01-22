// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet10Flying

public class Packet13PlayerLookMove extends Packet10Flying
{

    public Packet13PlayerLookMove()
    {
        hasLook = true;
        h = true;
    }

    public Packet13PlayerLookMove(double d, double d1, double d2, double d3, float f, float f1, boolean flag)
    {
        x = d;
        y = d1;
        stance = d2;
        z = d3;
        yaw = f;
        pitch = f1;
        g = flag;
        hasLook = true;
        h = true;
    }

    public void a(DataInputStream datainputstream)
    {
        x = datainputstream.readDouble();
        y = datainputstream.readDouble();
        stance = datainputstream.readDouble();
        z = datainputstream.readDouble();
        yaw = datainputstream.readFloat();
        pitch = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeDouble(x);
        dataoutputstream.writeDouble(y);
        dataoutputstream.writeDouble(stance);
        dataoutputstream.writeDouble(z);
        dataoutputstream.writeFloat(yaw);
        dataoutputstream.writeFloat(pitch);
        super.a(dataoutputstream);
    }

    public int a()
    {
        return 41;
    }
}
