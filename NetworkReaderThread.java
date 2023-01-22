// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            NetworkManager

class NetworkReaderThread extends Thread
{

    NetworkReaderThread(NetworkManager networkmanager, String s)
    {
        a = networkmanager;
        super(s);
    }

    public void run()
    {
        synchronized(NetworkManager.a)
        {
            NetworkManager.b++;
        }
        while(NetworkManager.a(a) && !NetworkManager.b(a)) 
        {
            while(NetworkManager.c(a)) ;
            try
            {
                sleep(2L);
            }
            catch(InterruptedException interruptedexception) { }
        }
        synchronized(NetworkManager.a)
        {
            NetworkManager.b--;
        }
        break MISSING_BLOCK_LABEL_131;
        Exception exception2;
        exception2;
        synchronized(NetworkManager.a)
        {
            NetworkManager.b--;
        }
        throw exception2;
    }

    final NetworkManager a;
}
