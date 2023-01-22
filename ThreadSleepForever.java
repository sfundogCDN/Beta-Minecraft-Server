// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            MinecraftServer

public class ThreadSleepForever extends Thread
{

    public ThreadSleepForever(MinecraftServer minecraftserver)
    {
        a = minecraftserver;
        super();
        setDaemon(true);
        start();
    }

    public void run()
    {
        do
            try
            {
                Thread.sleep(0x7fffffffL);
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    final MinecraftServer a;
}
