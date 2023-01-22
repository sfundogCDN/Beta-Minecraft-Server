// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

// Referenced classes of package net.minecraft.server:
//            NetworkListenThread, NetLoginHandler, MinecraftServer

class NetworkAcceptThread extends Thread
{

    NetworkAcceptThread(NetworkListenThread networklistenthread, String s, MinecraftServer minecraftserver)
    {
        b = networklistenthread;
        a = minecraftserver;
        super(s);
    }

    public void run()
    {
_L3:
        if(!b.b)
            break; /* Loop/switch isn't completed */
        Socket socket;
        NetLoginHandler netloginhandler;
        java.net.InetAddress inetaddress;
        try
        {
label0:
            {
                socket = NetworkListenThread.a(b).accept();
                if(socket == null)
                    continue; /* Loop/switch isn't completed */
                synchronized(NetworkListenThread.b(b))
                {
                    inetaddress = socket.getInetAddress();
                    if(!NetworkListenThread.b(b).containsKey(inetaddress) || System.currentTimeMillis() - ((Long)NetworkListenThread.b(b).get(inetaddress)).longValue() >= 5000L)
                        break label0;
                    NetworkListenThread.b(b).put(inetaddress, Long.valueOf(System.currentTimeMillis()));
                    socket.close();
                }
                continue; /* Loop/switch isn't completed */
            }
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
        NetworkListenThread.b(b).put(inetaddress, Long.valueOf(System.currentTimeMillis()));
        hashmap;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
        netloginhandler = new NetLoginHandler(a, socket, (new StringBuilder()).append("Connection #").append(NetworkListenThread.c(b)).toString());
        NetworkListenThread.a(b, netloginhandler);
        continue; /* Loop/switch isn't completed */
        if(true) goto _L3; else goto _L2
_L2:
    }

    final MinecraftServer a;
    final NetworkListenThread b;
}
