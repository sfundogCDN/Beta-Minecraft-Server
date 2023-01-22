// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            NetworkManager

class NetworkMasterThread extends Thread
{

    NetworkMasterThread(NetworkManager networkmanager)
    {
        a = networkmanager;
        super();
    }

    public void run()
    {
        try
        {
            Thread.sleep(5000L);
            if(NetworkManager.g(a).isAlive())
                try
                {
                    NetworkManager.g(a).stop();
                }
                catch(Throwable throwable) { }
            if(NetworkManager.h(a).isAlive())
                try
                {
                    NetworkManager.h(a).stop();
                }
                catch(Throwable throwable1) { }
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
    }

    final NetworkManager a;
}
