// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PacketCounter.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EmptyClass1

class PacketCounter
{

    private PacketCounter()
    {
    }

    public void a(int i)
    {
        a++;
        b += i;
    }

    PacketCounter(EmptyClass1 emptyclass1)
    {
        this();
    }

    private int a;
    private long b;
}
