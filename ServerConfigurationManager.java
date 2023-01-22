// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigurationManager.java

package net.minecraft.server;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import org.bukkit.*;
import org.bukkit.craftbukkit.*;
import org.bukkit.craftbukkit.command.ColouredConsoleSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            WorldServer, Packet201PlayerInfo, Packet3Chat, EntityPlayer, 
//            ItemInWorldManager, Packet70Bed, FoodMetaData, Packet9Respawn, 
//            EntityHuman, Packet4UpdateTime, MinecraftServer, PropertyManager, 
//            IDataManager, PlayerManager, ChunkProviderServer, PlayerFileData, 
//            NetServerHandler, NetLoginHandler, NetworkManager, EntityTracker, 
//            ChunkCoordinates, Packet, TileEntity

public class ServerConfigurationManager
{

    public ServerConfigurationManager(MinecraftServer minecraftserver)
    {
        players = new ArrayList();
        banByName = new HashSet();
        banByIP = new HashSet();
        operators = new HashSet();
        whitelist = new HashSet();
        p = 0;
        minecraftserver.server = new CraftServer(minecraftserver, this);
        minecraftserver.console = ColouredConsoleSender.getInstance();
        cserver = minecraftserver.server;
        server = minecraftserver;
        j = minecraftserver.a("banned-players.txt");
        k = minecraftserver.a("banned-ips.txt");
        l = minecraftserver.a("ops.txt");
        m = minecraftserver.a("white-list.txt");
        int i = minecraftserver.propertyManager.getInt("view-distance", 10);
        maxPlayers = minecraftserver.propertyManager.getInt("max-players", 20);
        hasWhitelist = minecraftserver.propertyManager.getBoolean("white-list", false);
        i();
        k();
        m();
        o();
        j();
        l();
        n();
        p();
    }

    public void setPlayerFileData(WorldServer aworldserver[])
    {
        if(playerFileData != null)
        {
            return;
        } else
        {
            playerFileData = aworldserver[0].o().d();
            return;
        }
    }

    public void a(EntityPlayer entityplayer)
    {
        Iterator i$ = server.worlds.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            WorldServer world = (WorldServer)i$.next();
            if(!world.manager.managedPlayers.contains(entityplayer))
                continue;
            world.manager.removePlayer(entityplayer);
            break;
        } while(true);
        getPlayerManager(entityplayer.dimension).addPlayer(entityplayer);
        WorldServer worldserver = server.getWorldServer(entityplayer.dimension);
        worldserver.chunkProviderServer.getChunkAt((int)entityplayer.locX >> 4, (int)entityplayer.locZ >> 4);
    }

    public int a()
    {
        if(server.worlds.size() == 0)
            return server.propertyManager.getInt("view-distance", 10) * 16 - 16;
        else
            return ((WorldServer)server.worlds.get(0)).manager.getFurthestViewableBlock();
    }

    private PlayerManager getPlayerManager(int i)
    {
        return server.getWorldServer(i).manager;
    }

    public void b(EntityPlayer entityplayer)
    {
        playerFileData.b(entityplayer);
    }

    public void c(EntityPlayer entityplayer)
    {
        cserver.detectListNameConflict(entityplayer);
        sendAll(new Packet201PlayerInfo(entityplayer.listName, true, 1000));
        players.add(entityplayer);
        WorldServer worldserver = server.getWorldServer(entityplayer.dimension);
        worldserver.chunkProviderServer.getChunkAt((int)entityplayer.locX >> 4, (int)entityplayer.locZ >> 4);
        for(; worldserver.getEntities(entityplayer, entityplayer.boundingBox).size() != 0; entityplayer.setPosition(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ));
        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(cserver.getPlayer(entityplayer), (new StringBuilder()).append("\247e").append(entityplayer.name).append(" joined the game.").toString());
        cserver.getPluginManager().callEvent(playerJoinEvent);
        String joinMessage = playerJoinEvent.getJoinMessage();
        if(joinMessage != null)
            server.serverConfigurationManager.sendAll(new Packet3Chat(joinMessage));
        worldserver.addEntity(entityplayer);
        getPlayerManager(entityplayer.dimension).addPlayer(entityplayer);
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayer entityplayer1 = (EntityPlayer)players.get(i);
            entityplayer.netServerHandler.sendPacket(new Packet201PlayerInfo(entityplayer1.listName, true, entityplayer1.i));
        }

    }

    public void d(EntityPlayer entityplayer)
    {
        getPlayerManager(entityplayer.dimension).movePlayer(entityplayer);
    }

    public String disconnect(EntityPlayer entityplayer)
    {
        getPlayerManager(entityplayer.dimension).removePlayer(entityplayer);
        PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(cserver.getPlayer(entityplayer), (new StringBuilder()).append("\247e").append(entityplayer.name).append(" left the game.").toString());
        cserver.getPluginManager().callEvent(playerQuitEvent);
        playerFileData.a(entityplayer);
        server.getWorldServer(entityplayer.dimension).kill(entityplayer);
        players.remove(entityplayer);
        getPlayerManager(entityplayer.dimension).removePlayer(entityplayer);
        sendAll(new Packet201PlayerInfo(entityplayer.listName, false, 9999));
        return playerQuitEvent.getQuitMessage();
    }

    public EntityPlayer a(NetLoginHandler netloginhandler, String s)
    {
        EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), s, new ItemInWorldManager(server.getWorldServer(0)));
        Player player = entity != null ? (Player)entity.getBukkitEntity() : null;
        PlayerLoginEvent event = new PlayerLoginEvent(player);
        String s1 = netloginhandler.networkManager.getSocketAddress().toString();
        s1 = s1.substring(s1.indexOf("/") + 1);
        s1 = s1.substring(0, s1.indexOf(":"));
        if(banByName.contains(s.trim().toLowerCase()))
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_BANNED, "You are banned from this server!");
        else
        if(!isWhitelisted(s))
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_WHITELIST, "You are not white-listed on this server!");
        else
        if(banByIP.contains(s1))
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_BANNED, "Your IP address is banned from this server!");
        else
        if(players.size() >= maxPlayers)
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_FULL, "The server is full!");
        else
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.ALLOWED, s1);
        cserver.getPluginManager().callEvent(event);
        if(event.getResult() != org.bukkit.event.player.PlayerLoginEvent.Result.ALLOWED)
        {
            netloginhandler.disconnect(event.getKickMessage());
            return null;
        }
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(i);
            if(entityplayer.name.equalsIgnoreCase(s))
                entityplayer.netServerHandler.disconnect("You logged in from another location");
        }

        return entity;
    }

    public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i)
    {
        return moveToWorld(entityplayer, i, null);
    }

    public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, Location location)
    {
        server.getTracker(entityplayer.dimension).untrackPlayer(entityplayer);
        getPlayerManager(entityplayer.dimension).removePlayer(entityplayer);
        players.remove(entityplayer);
        server.getWorldServer(entityplayer.dimension).removeEntity(entityplayer);
        ChunkCoordinates chunkcoordinates = entityplayer.getBed();
        EntityPlayer entityplayer1 = entityplayer;
        World fromWorld = entityplayer1.getBukkitEntity().getWorld();
        if(location == null)
        {
            boolean isBedSpawn = false;
            CraftWorld cworld = (CraftWorld)server.server.getWorld(entityplayer.spawnWorld);
            if(cworld != null && chunkcoordinates != null)
            {
                ChunkCoordinates chunkcoordinates1 = EntityHuman.getBed(cworld.getHandle(), chunkcoordinates);
                if(chunkcoordinates1 != null)
                {
                    isBedSpawn = true;
                    location = new Location(cworld, (double)chunkcoordinates1.x + 0.5D, chunkcoordinates1.y, (double)chunkcoordinates1.z + 0.5D);
                } else
                {
                    entityplayer1.netServerHandler.sendPacket(new Packet70Bed(0, 0));
                }
            }
            if(location == null)
            {
                cworld = (CraftWorld)server.server.getWorlds().get(0);
                chunkcoordinates = cworld.getHandle().getSpawn();
                location = new Location(cworld, (double)chunkcoordinates.x + 0.5D, chunkcoordinates.y, (double)chunkcoordinates.z + 0.5D);
            }
            Player respawnPlayer = cserver.getPlayer(entityplayer);
            PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, isBedSpawn);
            cserver.getPluginManager().callEvent(respawnEvent);
            location = respawnEvent.getRespawnLocation();
            entityplayer.health = 20;
            entityplayer.fireTicks = 0;
            entityplayer.fallDistance = 0.0F;
            entityplayer.foodData = new FoodMetaData();
            entityplayer.expTotal = 0;
            entityplayer.exp = 0;
            entityplayer.expLevel = 0;
            entityplayer.d(entityplayer.newExp);
        } else
        {
            location.setWorld(server.getWorldServer(i).getWorld());
        }
        WorldServer worldserver = ((CraftWorld)location.getWorld()).getHandle();
        entityplayer1.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        worldserver.chunkProviderServer.getChunkAt((int)entityplayer1.locX >> 4, (int)entityplayer1.locZ >> 4);
        for(; worldserver.getEntities(entityplayer1, entityplayer1.boundingBox).size() != 0; entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ));
        byte actualDimension = (byte)worldserver.getWorld().getEnvironment().getId();
        entityplayer1.netServerHandler.sendPacket(new Packet9Respawn(actualDimension, (byte)worldserver.difficulty, worldserver.getSeed(), 128, entityplayer1.itemInWorldManager.a()));
        entityplayer1.spawnIn(worldserver);
        entityplayer1.dead = false;
        entityplayer1.netServerHandler.teleport(new Location(worldserver.getWorld(), entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch));
        a(entityplayer1, worldserver);
        getPlayerManager(entityplayer1.dimension).addPlayer(entityplayer1);
        worldserver.addEntity(entityplayer1);
        players.add(entityplayer1);
        updateClient(entityplayer1);
        entityplayer1.w();
        if(fromWorld != location.getWorld())
        {
            PlayerChangedWorldEvent event = new PlayerChangedWorldEvent((Player)entityplayer1.getBukkitEntity(), fromWorld);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
        return entityplayer1;
    }

    public void f(EntityPlayer entityplayer)
    {
        int dimension = entityplayer.dimension;
        WorldServer fromWorld = server.getWorldServer(dimension);
        WorldServer toWorld = null;
        if(dimension < 10)
        {
            int toDimension = dimension != -1 ? -1 : 0;
            Iterator i$ = server.worlds.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                WorldServer world = (WorldServer)i$.next();
                if(world.dimension == toDimension)
                    toWorld = world;
            } while(true);
        }
        double blockRatio = dimension != -1 ? 0.125D : 8D;
        Location fromLocation = new Location(fromWorld.getWorld(), entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
        Location toLocation = toWorld != null ? new Location(toWorld.getWorld(), entityplayer.locX * blockRatio, entityplayer.locY, entityplayer.locZ * blockRatio, entityplayer.yaw, entityplayer.pitch) : null;
        PortalTravelAgent pta = new PortalTravelAgent();
        PlayerPortalEvent event = new PlayerPortalEvent((Player)entityplayer.getBukkitEntity(), fromLocation, toLocation, pta);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled() || event.getTo() == null)
            return;
        Location finalLocation = event.getTo();
        if(event.useTravelAgent())
            finalLocation = event.getPortalTravelAgent().findOrCreate(finalLocation);
        toWorld = ((CraftWorld)finalLocation.getWorld()).getHandle();
        moveToWorld(entityplayer, toWorld.dimension, finalLocation);
    }

    public void b()
    {
        for(int i = 0; i < server.worlds.size(); i++)
            ((WorldServer)server.worlds.get(i)).manager.flush();

    }

    public void flagDirty(int i, int j, int k, int l)
    {
        getPlayerManager(l).flagDirty(i, j, k);
    }

    public void sendAll(Packet packet)
    {
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(i);
            entityplayer.netServerHandler.sendPacket(packet);
        }

    }

    public void a(Packet packet, int i)
    {
        for(int j = 0; j < players.size(); j++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(j);
            if(entityplayer.dimension == i)
                entityplayer.netServerHandler.sendPacket(packet);
        }

    }

    public String c()
    {
        String s = "";
        for(int i = 0; i < players.size(); i++)
        {
            if(i > 0)
                s = (new StringBuilder()).append(s).append(", ").toString();
            s = (new StringBuilder()).append(s).append(((EntityPlayer)players.get(i)).name).toString();
        }

        return s;
    }

    public void addUserBan(String s)
    {
        banByName.add(s.toLowerCase());
        j();
    }

    public void removeUserBan(String s)
    {
        banByName.remove(s.toLowerCase());
        j();
    }

    private void i()
    {
        try
        {
            banByName.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(j));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
                banByName.add(s.trim().toLowerCase());

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to load ban list: ").append(exception).toString());
        }
    }

    private void j()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(j, false));
            String s;
            for(Iterator iterator = banByName.iterator(); iterator.hasNext(); printwriter.println(s))
                s = (String)iterator.next();

            printwriter.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to save ban list: ").append(exception).toString());
        }
    }

    public void addIpBan(String s)
    {
        banByIP.add(s.toLowerCase());
        l();
    }

    public void removeIpBan(String s)
    {
        banByIP.remove(s.toLowerCase());
        l();
    }

    private void k()
    {
        try
        {
            banByIP.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(k));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
                banByIP.add(s.trim().toLowerCase());

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to load ip ban list: ").append(exception).toString());
        }
    }

    private void l()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(k, false));
            String s;
            for(Iterator iterator = banByIP.iterator(); iterator.hasNext(); printwriter.println(s))
                s = (String)iterator.next();

            printwriter.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to save ip ban list: ").append(exception).toString());
        }
    }

    public void addOp(String s)
    {
        operators.add(s.toLowerCase());
        n();
        Player player = server.server.getPlayer(s);
        if(player != null)
            player.recalculatePermissions();
    }

    public void removeOp(String s)
    {
        operators.remove(s.toLowerCase());
        n();
        Player player = server.server.getPlayer(s);
        if(player != null)
            player.recalculatePermissions();
    }

    private void m()
    {
        try
        {
            operators.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(l));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
                operators.add(s.trim().toLowerCase());

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to load ops: ").append(exception).toString());
        }
    }

    private void n()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(l, false));
            String s;
            for(Iterator iterator = operators.iterator(); iterator.hasNext(); printwriter.println(s))
                s = (String)iterator.next();

            printwriter.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to save ops: ").append(exception).toString());
        }
    }

    private void o()
    {
        try
        {
            whitelist.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(m));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
                whitelist.add(s.trim().toLowerCase());

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to load white-list: ").append(exception).toString());
        }
    }

    private void p()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(m, false));
            String s;
            for(Iterator iterator = whitelist.iterator(); iterator.hasNext(); printwriter.println(s))
                s = (String)iterator.next();

            printwriter.close();
        }
        catch(Exception exception)
        {
            a.warning((new StringBuilder()).append("Failed to save white-list: ").append(exception).toString());
        }
    }

    public boolean isWhitelisted(String s)
    {
        s = s.trim().toLowerCase();
        return !hasWhitelist || operators.contains(s) || whitelist.contains(s);
    }

    public boolean isOp(String s)
    {
        return operators.contains(s.trim().toLowerCase());
    }

    public EntityPlayer i(String s)
    {
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(i);
            if(entityplayer.name.equalsIgnoreCase(s))
                return entityplayer;
        }

        return null;
    }

    public void a(String s, String s1)
    {
        EntityPlayer entityplayer = i(s);
        if(entityplayer != null)
            entityplayer.netServerHandler.sendPacket(new Packet3Chat(s1));
    }

    public void sendPacketNearby(double d0, double d1, double d2, double d3, int i, Packet packet)
    {
        sendPacketNearby((EntityHuman)null, d0, d1, d2, d3, i, packet);
    }

    public void sendPacketNearby(EntityHuman entityhuman, double d0, double d1, double d2, 
            double d3, int i, Packet packet)
    {
        for(int j = 0; j < players.size(); j++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(j);
            if(entityplayer == entityhuman || entityplayer.dimension != i)
                continue;
            double d4 = d0 - entityplayer.locX;
            double d5 = d1 - entityplayer.locY;
            double d6 = d2 - entityplayer.locZ;
            if(d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3)
                entityplayer.netServerHandler.sendPacket(packet);
        }

    }

    public void j(String s)
    {
        Packet3Chat packet3chat = new Packet3Chat(s);
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)players.get(i);
            if(isOp(entityplayer.name))
                entityplayer.netServerHandler.sendPacket(packet3chat);
        }

    }

    public boolean a(String s, Packet packet)
    {
        EntityPlayer entityplayer = i(s);
        if(entityplayer != null)
        {
            entityplayer.netServerHandler.sendPacket(packet);
            return true;
        } else
        {
            return false;
        }
    }

    public void savePlayers()
    {
        for(int i = 0; i < players.size(); i++)
            playerFileData.a((EntityHuman)players.get(i));

    }

    public void a(int i1, int j1, int k1, TileEntity tileentity1)
    {
    }

    public void addWhitelist(String s)
    {
        whitelist.add(s);
        p();
    }

    public void removeWhitelist(String s)
    {
        whitelist.remove(s);
        p();
    }

    public Set getWhitelisted()
    {
        return whitelist;
    }

    public void reloadWhitelist()
    {
        o();
    }

    public void a(EntityPlayer entityplayer, WorldServer worldserver)
    {
        entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
        if(worldserver.u())
            entityplayer.netServerHandler.sendPacket(new Packet70Bed(1, 0));
    }

    public void updateClient(EntityPlayer entityplayer)
    {
        entityplayer.updateInventory(entityplayer.defaultContainer);
        entityplayer.B();
    }

    public int g()
    {
        return players.size();
    }

    public int h()
    {
        return maxPlayers;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    public List players;
    public MinecraftServer server;
    public int maxPlayers;
    public Set banByName;
    public Set banByIP;
    public Set operators;
    private Set whitelist;
    private File j;
    private File k;
    private File l;
    private File m;
    public PlayerFileData playerFileData;
    public boolean hasWhitelist;
    private int p;
    private CraftServer cserver;

}
