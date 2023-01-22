// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.server:
//            Packet10Flying

public class Packet11PlayerPosition extends Packet10Flying
{

    public Packet11PlayerPosition()
    {
        h = true;
    }

    public void a(DataInputStream datainputstream)
    {
        x = datainputstream.readDouble();
        y = datainputstream.readDouble();
        stance = datainputstream.readDouble();
        z = datainputstream.readDouble();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream)
    {
        dataoutputstream.writeDouble(x);
        dataoutputstream.writeDouble(y);
        dataoutputstream.writeDouble(stance);
        dataoutputstream.writeDouble(z);
        super.a(dataoutputstream);
    }

    public int a()
    {
        return 33;
    }
}
