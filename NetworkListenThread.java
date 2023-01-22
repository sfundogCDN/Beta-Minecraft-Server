// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package net.minecraft.server:
//            NetworkAcceptThread, NetLoginHandler, NetworkManager, NetServerHandler, 
//            MinecraftServer

public class NetworkListenThread
{

    public NetworkListenThread(MinecraftServer minecraftserver, InetAddress inetaddress, int j)
    {
        b = false;
        f = 0;
        g = new ArrayList();
        h = new ArrayList();
        i = new HashMap();
        c = minecraftserver;
        d = new ServerSocket(j, 0, inetaddress);
        d.setPerformancePreferences(0, 2, 1);
        b = true;
        e = new NetworkAcceptThread(this, "Listen thread", minecraftserver);
        e.start();
    }

    public void a(Socket socket)
    {
        InetAddress inetaddress = socket.getInetAddress();
        synchronized(i)
        {
            i.remove(inetaddress);
        }
    }

    public void a(NetServerHandler netserverhandler)
    {
        h.add(netserverhandler);
    }

    private void a(NetLoginHandler netloginhandler)
    {
        if(netloginhandler == null)
        {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else
        {
            g.add(netloginhandler);
            return;
        }
    }

    public void a()
    {
        for(int j = 0; j < g.size(); j++)
        {
            NetLoginHandler netloginhandler = (NetLoginHandler)g.get(j);
            try
            {
                netloginhandler.a();
            }
            catch(Exception exception)
            {
                netloginhandler.disconnect("Internal server error");
                a.log(Level.WARNING, (new StringBuilder()).append("Failed to handle packet: ").append(exception).toString(), exception);
            }
            if(netloginhandler.c)
                g.remove(j--);
            netloginhandler.networkManager.a();
        }

        for(int k = 0; k < h.size(); k++)
        {
            NetServerHandler netserverhandler = (NetServerHandler)h.get(k);
            try
            {
                netserverhandler.a();
            }
            catch(Exception exception1)
            {
                a.log(Level.WARNING, (new StringBuilder()).append("Failed to handle packet: ").append(exception1).toString(), exception1);
                netserverhandler.disconnect("Internal server error");
            }
            if(netserverhandler.disconnected)
                h.remove(k--);
            netserverhandler.networkManager.a();
        }

    }

    static ServerSocket a(NetworkListenThread networklistenthread)
    {
        return networklistenthread.d;
    }

    static HashMap b(NetworkListenThread networklistenthread)
    {
        return networklistenthread.i;
    }

    static int c(NetworkListenThread networklistenthread)
    {
        return networklistenthread.f++;
    }

    static void a(NetworkListenThread networklistenthread, NetLoginHandler netloginhandler)
    {
        networklistenthread.a(netloginhandler);
    }

    public static Logger a = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b;
    private int f;
    private ArrayList g;
    private ArrayList h;
    public MinecraftServer c;
    private HashMap i;

}
