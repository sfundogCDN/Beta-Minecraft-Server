// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            MinecraftServer

public final class ThreadServerApplication extends Thread
{

    public ThreadServerApplication(String s, MinecraftServer minecraftserver)
    {
        a = minecraftserver;
        super(s);
    }

    public void run()
    {
        a.run();
    }

    final MinecraftServer a;
}
