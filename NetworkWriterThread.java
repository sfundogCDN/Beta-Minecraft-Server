// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package net.minecraft.server:
//            NetworkManager

class NetworkWriterThread extends Thread
{

    NetworkWriterThread(NetworkManager networkmanager, String s)
    {
        a = networkmanager;
        super(s);
    }

    public void run()
    {
        synchronized(NetworkManager.a)
        {
            NetworkManager.c++;
        }
        while(NetworkManager.a(a)) 
        {
            while(NetworkManager.d(a)) ;
            try
            {
                if(NetworkManager.e(a) != null)
                    NetworkManager.e(a).flush();
            }
            catch(IOException ioexception)
            {
                if(!NetworkManager.f(a))
                    NetworkManager.a(a, ioexception);
                ioexception.printStackTrace();
            }
            try
            {
                sleep(2L);
            }
            catch(InterruptedException interruptedexception) { }
        }
        synchronized(NetworkManager.a)
        {
            NetworkManager.c--;
        }
        break MISSING_BLOCK_LABEL_167;
        Exception exception2;
        exception2;
        synchronized(NetworkManager.a)
        {
            NetworkManager.c--;
        }
        throw exception2;
    }

    final NetworkManager a;
}
