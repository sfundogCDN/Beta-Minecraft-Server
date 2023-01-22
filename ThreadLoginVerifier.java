// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadLoginVerifier.java

package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            NetLoginHandler, Packet1Login

class ThreadLoginVerifier extends Thread
{

    ThreadLoginVerifier(NetLoginHandler netloginhandler, Packet1Login packet1login, CraftServer server)
    {
        this.server = server;
        netLoginHandler = netloginhandler;
        loginPacket = packet1login;
    }

    public void run()
    {
        String s = NetLoginHandler.a(netLoginHandler);
        URL url = new URL((new StringBuilder()).append("http://session.minecraft.net/game/checkserver.jsp?user=").append(URLEncoder.encode(loginPacket.name, "UTF-8")).append("&serverId=").append(URLEncoder.encode(s, "UTF-8")).toString());
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
        String s1 = bufferedreader.readLine();
        bufferedreader.close();
        if(!s1.equals("YES"))
            break MISSING_BLOCK_LABEL_189;
        if(netLoginHandler.getSocket() == null)
            return;
        PlayerPreLoginEvent event = new PlayerPreLoginEvent(loginPacket.name, netLoginHandler.getSocket().getInetAddress());
        server.getPluginManager().callEvent(event);
        if(event.getResult() != org.bukkit.event.player.PlayerPreLoginEvent.Result.ALLOWED)
        {
            netLoginHandler.disconnect(event.getKickMessage());
            return;
        }
        NetLoginHandler.a(netLoginHandler, loginPacket);
        break MISSING_BLOCK_LABEL_237;
        netLoginHandler.disconnect("Failed to verify username!");
        break MISSING_BLOCK_LABEL_237;
        Exception exception;
        exception;
        netLoginHandler.disconnect((new StringBuilder()).append("Failed to verify username! [internal error ").append(exception).append("]").toString());
        exception.printStackTrace();
    }

    final Packet1Login loginPacket;
    final NetLoginHandler netLoginHandler;
    CraftServer server;
}
