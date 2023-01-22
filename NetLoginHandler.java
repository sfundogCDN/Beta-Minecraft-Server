// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetLoginHandler.java

package net.minecraft.server;

import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.server.ServerListPingEvent;

// Referenced classes of package net.minecraft.server:
//            NetHandler, NetworkManager, Packet255KickDisconnect, Packet2Handshake, 
//            ThreadLoginVerifier, WorldServer, NetServerHandler, Packet1Login, 
//            Packet6SpawnPosition, Packet4UpdateTime, MobEffect, Packet41MobEffect, 
//            MinecraftServer, ServerConfigurationManager, EntityPlayer, ItemInWorldManager, 
//            World, WorldData, WorldProvider, ChunkCoordinates, 
//            NetworkListenThread, Packet254GetInfo, Packet

public class NetLoginHandler extends NetHandler
{

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s)
    {
        c = false;
        f = 0;
        g = null;
        h = null;
        i = Long.toHexString(d.nextLong());
        server = minecraftserver;
        networkManager = new NetworkManager(socket, s, this);
        networkManager.f = 0;
    }

    public Socket getSocket()
    {
        return networkManager.socket;
    }

    public void a()
    {
        if(h != null)
        {
            b(h);
            h = null;
        }
        if(f++ == 600)
            disconnect("Took too long to log in");
        else
            networkManager.b();
    }

    public void disconnect(String s)
    {
        try
        {
            a.info((new StringBuilder()).append("Disconnecting ").append(b()).append(": ").append(s).toString());
            networkManager.queue(new Packet255KickDisconnect(s));
            networkManager.d();
            c = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void a(Packet2Handshake packet2handshake)
    {
        if(server.onlineMode)
            networkManager.queue(new Packet2Handshake(i));
        else
            networkManager.queue(new Packet2Handshake("-"));
    }

    public void a(Packet1Login packet1login)
    {
        g = packet1login.name;
        if(packet1login.a != 17)
        {
            if(packet1login.a > 17)
                disconnect("Outdated server!");
            else
                disconnect("Outdated client!");
        } else
        if(!server.onlineMode)
            b(packet1login);
        else
            (new ThreadLoginVerifier(this, packet1login, server.server)).start();
    }

    public void b(Packet1Login packet1login)
    {
        EntityPlayer entityplayer = server.serverConfigurationManager.a(this, packet1login.name);
        if(entityplayer != null)
        {
            server.serverConfigurationManager.b(entityplayer);
            entityplayer.itemInWorldManager.a((WorldServer)entityplayer.world);
            a.info((new StringBuilder()).append(b()).append(" logged in with entity id ").append(entityplayer.id).append(" at ([").append(entityplayer.world.worldData.name).append("] ").append(entityplayer.locX).append(", ").append(entityplayer.locY).append(", ").append(entityplayer.locZ).append(")").toString());
            WorldServer worldserver = (WorldServer)entityplayer.world;
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
            entityplayer.itemInWorldManager.b(worldserver.p().getGameType());
            NetServerHandler netserverhandler = new NetServerHandler(server, networkManager, entityplayer);
            int i = entityplayer.id;
            long j = worldserver.getSeed();
            int k = entityplayer.itemInWorldManager.a();
            byte b0 = (byte)worldserver.worldProvider.dimension;
            byte b1 = (byte)worldserver.difficulty;
            worldserver.getClass();
            int maxPlayers = server.serverConfigurationManager.h();
            if(maxPlayers > 60)
                maxPlayers = 60;
            Packet1Login packet1login1 = new Packet1Login("", i, j, k, b0, b1, (byte)-128, (byte)maxPlayers);
            netserverhandler.sendPacket(packet1login1);
            netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
            server.serverConfigurationManager.a(entityplayer, worldserver);
            server.serverConfigurationManager.c(entityplayer);
            netserverhandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
            server.networkListenThread.a(netserverhandler);
            netserverhandler.sendPacket(new Packet4UpdateTime(entityplayer.getPlayerTime()));
            MobEffect mobeffect;
            for(Iterator iterator = entityplayer.getEffects().iterator(); iterator.hasNext(); netserverhandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect)))
                mobeffect = (MobEffect)iterator.next();

            entityplayer.syncInventory();
        }
        c = true;
    }

    public void a(String s, Object aobject[])
    {
        a.info((new StringBuilder()).append(b()).append(" lost connection").toString());
        c = true;
    }

    public void a(Packet254GetInfo packet254getinfo)
    {
        if(networkManager.f() == null)
            return;
        try
        {
            ServerListPingEvent pingEvent = CraftEventFactory.callServerListPingEvent(server.server, getSocket().getInetAddress(), server.p, server.serverConfigurationManager.g(), server.serverConfigurationManager.h());
            String s = (new StringBuilder()).append(pingEvent.getMotd()).append("\247").append(server.serverConfigurationManager.g()).append("\247").append(pingEvent.getMaxPlayers()).toString();
            networkManager.queue(new Packet255KickDisconnect(s));
            networkManager.d();
            server.networkListenThread.a(networkManager.f());
            c = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void a(Packet packet)
    {
        disconnect("Protocol error");
    }

    public String b()
    {
        return g == null ? networkManager.getSocketAddress().toString() : (new StringBuilder()).append(g).append(" [").append(networkManager.getSocketAddress().toString()).append("]").toString();
    }

    public boolean c()
    {
        return true;
    }

    static String a(NetLoginHandler netloginhandler)
    {
        return netloginhandler.i;
    }

    static Packet1Login a(NetLoginHandler netloginhandler, Packet1Login packet1login)
    {
        return netloginhandler.h = packet1login;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public NetworkManager networkManager;
    public boolean c;
    private MinecraftServer server;
    private int f;
    private String g;
    private Packet1Login h;
    private String i;

}
