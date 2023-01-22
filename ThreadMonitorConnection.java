// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            NetworkManager

class ThreadMonitorConnection extends Thread
{

    ThreadMonitorConnection(NetworkManager networkmanager)
    {
        a = networkmanager;
        super();
    }

    public void run()
    {
        try
        {
            Thread.sleep(2000L);
            if(NetworkManager.a(a))
            {
                NetworkManager.h(a).interrupt();
                a.a("disconnect.closed", new Object[0]);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    final NetworkManager a;
}
