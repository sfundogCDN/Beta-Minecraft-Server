// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinecraftServer.java

package net.minecraft.server;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.ConsoleReader;
import joptsimple.OptionSet;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.*;
import org.bukkit.craftbukkit.scheduler.CraftScheduler;
import org.bukkit.craftbukkit.util.ServerShutdownThread;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.world.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            ThreadSleepForever, ConsoleCommandHandler, ThreadCommandReader, PropertyManager, 
//            NetworkListenThread, ServerConfigurationManager, WorldLoaderServer, ConvertProgressUpdater, 
//            WorldSettings, WorldServer, ServerNBTManager, SecondaryWorldServer, 
//            EntityTracker, WorldManager, IProgressUpdate, EntityPlayer, 
//            Packet4UpdateTime, IUpdatePlayerListBox, ServerCommand, ThreadServerApplication, 
//            ICommandListener, ConsoleLogManager, Convertable, WorldData, 
//            ChunkCoordinates, ChunkProviderServer, World, AxisAlignedBB, 
//            Vec3D, NetServerHandler, StatisticList

public class MinecraftServer
    implements Runnable, ICommandListener
{

    public MinecraftServer(OptionSet options)
    {
        isRunning = true;
        isStopped = false;
        ticks = 0;
        s = new ArrayList();
        t = Collections.synchronizedList(new ArrayList());
        worlds = new ArrayList();
        new ThreadSleepForever(this);
        this.options = options;
        try
        {
            reader = new ConsoleReader();
        }
        catch(IOException ex)
        {
            Logger.getLogger(net/minecraft/server/MinecraftServer.getName()).log(Level.SEVERE, null, ex);
        }
        Runtime.getRuntime().addShutdownHook(new ServerShutdownThread(this));
    }

    private boolean init()
        throws UnknownHostException
    {
        consoleCommandHandler = new ConsoleCommandHandler(this);
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);
        threadcommandreader.setDaemon(true);
        threadcommandreader.start();
        ConsoleLogManager.init(this);
        System.setOut(new PrintStream(new LoggerOutputStream(log, Level.INFO), true));
        System.setErr(new PrintStream(new LoggerOutputStream(log, Level.SEVERE), true));
        log.info("Starting minecraft server version Beta 1.8.1");
        if(Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L)
        {
            log.warning("**** NOT ENOUGH RAM!");
            log.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }
        log.info("Loading properties");
        propertyManager = new PropertyManager(options);
        String s = propertyManager.getString("server-ip", "");
        onlineMode = propertyManager.getBoolean("online-mode", true);
        spawnAnimals = propertyManager.getBoolean("spawn-animals", true);
        pvpMode = propertyManager.getBoolean("pvp", true);
        allowFlight = propertyManager.getBoolean("allow-flight", false);
        p = propertyManager.getString("motd", "A Minecraft Server");
        p.replace('\247', '$');
        InetAddress inetaddress = null;
        if(s.length() > 0)
            inetaddress = InetAddress.getByName(s);
        int i = propertyManager.getInt("server-port", 25565);
        log.info((new StringBuilder()).append("Starting Minecraft server on ").append(s.length() != 0 ? s : "*").append(":").append(i).toString());
        try
        {
            networkListenThread = new NetworkListenThread(this, inetaddress, i);
        }
        catch(Throwable ioexception)
        {
            log.warning("**** FAILED TO BIND TO PORT!");
            log.log(Level.WARNING, (new StringBuilder()).append("The exception was: ").append(ioexception.toString()).toString());
            log.warning("Perhaps a server is already running on that port?");
            return false;
        }
        if(!onlineMode)
        {
            log.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            log.warning("The server will make no attempt to authenticate usernames. Beware.");
            log.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            log.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }
        serverConfigurationManager = new ServerConfigurationManager(this);
        long j = System.nanoTime();
        String s1 = propertyManager.getString("level-name", "world");
        String s2 = propertyManager.getString("level-seed", "");
        long k = (new Random()).nextLong();
        if(s2.length() > 0)
            try
            {
                k = Long.parseLong(s2);
            }
            catch(NumberFormatException numberformatexception)
            {
                k = s2.hashCode();
            }
        log.info((new StringBuilder()).append("Preparing level \"").append(s1).append("\"").toString());
        a(new WorldLoaderServer(new File(".")), s1, k);
        long elapsed = System.nanoTime() - j;
        String time = String.format("%.3fs", new Object[] {
            Double.valueOf((double)elapsed / 10000000000D)
        });
        log.info((new StringBuilder()).append("Done (").append(time).append(")! For help, type \"help\" or \"?\"").toString());
        if(propertyManager.properties.containsKey("spawn-protection"))
        {
            log.info("'spawn-protection' in server.properties has been moved to 'settings.spawn-radius' in bukkit.yml. I will move your config for you.");
            server.setSpawnRadius(propertyManager.getInt("spawn-protection", 16));
            propertyManager.properties.remove("spawn-protection");
            propertyManager.savePropertiesFile();
        }
        return true;
    }

    private void a(Convertable convertable, String s, long i)
    {
        if(convertable.isConvertable(s))
        {
            log.info("Converting map!");
            convertable.convert(s, new ConvertProgressUpdater(this));
        }
        int j = propertyManager.getInt("gamemode", 0);
        j = WorldSettings.a(j);
        log.info((new StringBuilder()).append("Default game type: ").append(j).toString());
        for(int k = 0; k < (propertyManager.getBoolean("allow-nether", true) ? 2 : 1); k++)
        {
            int dimension = k != 0 ? -1 : 0;
            String worldType = org.bukkit.World.Environment.getEnvironment(dimension).toString().toLowerCase();
            String name = dimension != 0 ? (new StringBuilder()).append(s).append("_").append(worldType).toString() : s;
            ChunkGenerator gen = server.getGenerator(name);
            WorldSettings settings = new WorldSettings(i, j, true);
            WorldServer world;
            if(k == 0)
            {
                world = new WorldServer(this, new ServerNBTManager(server.getWorldContainer(), s, true), s, dimension, settings, org.bukkit.World.Environment.getEnvironment(dimension), gen);
            } else
            {
                String dim = "DIM-1";
                File newWorld = new File(new File(name), dim);
                File oldWorld = new File(new File(s), dim);
                if(!newWorld.isDirectory() && oldWorld.isDirectory())
                {
                    log.info((new StringBuilder()).append("---- Migration of old ").append(worldType).append(" folder required ----").toString());
                    log.info((new StringBuilder()).append("Unfortunately due to the way that Minecraft implemented multiworld support in 1.6, Bukkit requires that you move your ").append(worldType).append(" folder to a new location in order to operate correctly.").toString());
                    log.info("We will move this folder for you, but it will mean that you need to move it back should you wish to stop using Bukkit in the future.");
                    log.info((new StringBuilder()).append("Attempting to move ").append(oldWorld).append(" to ").append(newWorld).append("...").toString());
                    if(newWorld.exists())
                    {
                        log.severe((new StringBuilder()).append("A file or folder already exists at ").append(newWorld).append("!").toString());
                        log.info((new StringBuilder()).append("---- Migration of old ").append(worldType).append(" folder failed ----").toString());
                    } else
                    if(newWorld.getParentFile().mkdirs())
                    {
                        if(oldWorld.renameTo(newWorld))
                        {
                            log.info((new StringBuilder()).append("Success! To restore the nether in the future, simply move ").append(newWorld).append(" to ").append(oldWorld).toString());
                            log.info((new StringBuilder()).append("---- Migration of old ").append(worldType).append(" folder complete ----").toString());
                        } else
                        {
                            log.severe((new StringBuilder()).append("Could not move folder ").append(oldWorld).append(" to ").append(newWorld).append("!").toString());
                            log.info((new StringBuilder()).append("---- Migration of old ").append(worldType).append(" folder failed ----").toString());
                        }
                    } else
                    {
                        log.severe((new StringBuilder()).append("Could not create path for ").append(newWorld).append("!").toString());
                        log.info((new StringBuilder()).append("---- Migration of old ").append(worldType).append(" folder failed ----").toString());
                    }
                }
                world = new SecondaryWorldServer(this, new ServerNBTManager(server.getWorldContainer(), name, true), name, dimension, settings, (WorldServer)worlds.get(0), org.bukkit.World.Environment.getEnvironment(dimension), gen);
            }
            if(gen != null)
                world.getWorld().getPopulators().addAll(gen.getDefaultPopulators(world.getWorld()));
            server.getPluginManager().callEvent(new WorldInitEvent(world.getWorld()));
            world.tracker = new EntityTracker(this, dimension);
            world.addIWorldAccess(new WorldManager(this, world));
            world.difficulty = propertyManager.getInt("difficulty", 1);
            world.setSpawnFlags(propertyManager.getBoolean("spawn-monsters", true), spawnAnimals);
            world.p().setGameType(j);
            worlds.add(world);
            serverConfigurationManager.setPlayerFileData((WorldServer[])worlds.toArray(new WorldServer[0]));
        }

        short short1 = 196;
        long l = System.currentTimeMillis();
label0:
        for(int i1 = 0; i1 < worlds.size(); i1++)
        {
            WorldServer worldserver = (WorldServer)worlds.get(i1);
            log.info((new StringBuilder()).append("Preparing start region for level ").append(i1).append(" (Seed: ").append(worldserver.getSeed()).append(")").toString());
            if(!worldserver.getWorld().getKeepSpawnInMemory())
                continue;
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
            int j1 = -short1;
            do
            {
                if(j1 > short1 || !isRunning)
                    continue label0;
                for(int k1 = -short1; k1 <= short1 && isRunning; k1 += 16)
                {
                    long l1 = System.currentTimeMillis();
                    if(l1 < l)
                        l = l1;
                    if(l1 > l + 1000L)
                    {
                        int i2 = (short1 * 2 + 1) * (short1 * 2 + 1);
                        int j2 = (j1 + short1) * (short1 * 2 + 1) + k1 + 1;
                        a("Preparing spawn area", (j2 * 100) / i2);
                        l = l1;
                    }
                    worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + j1 >> 4, chunkcoordinates.z + k1 >> 4);
                    while(worldserver.v() && isRunning) ;
                }

                j1 += 16;
            } while(true);
        }

        net.minecraft.server.World world;
        for(Iterator i$ = worlds.iterator(); i$.hasNext(); server.getPluginManager().callEvent(new WorldLoadEvent(world.getWorld())))
            world = (WorldServer)i$.next();

        e();
    }

    private void a(String s, int i)
    {
        this.i = s;
        j = i;
        log.info((new StringBuilder()).append(s).append(": ").append(i).append("%").toString());
    }

    private void e()
    {
        i = null;
        j = 0;
        server.enablePlugins(PluginLoadOrder.POSTWORLD);
    }

    void saveChunks()
    {
        log.info("Saving chunks");
        for(int i = 0; i < worlds.size(); i++)
        {
            WorldServer worldserver = (WorldServer)worlds.get(i);
            worldserver.save(true, (IProgressUpdate)null);
            worldserver.saveLevel();
            WorldSaveEvent event = new WorldSaveEvent(worldserver.getWorld());
            server.getPluginManager().callEvent(event);
        }

        WorldServer world = (WorldServer)worlds.get(0);
        if(!world.savingDisabled)
            serverConfigurationManager.savePlayers();
    }

    public void stop()
    {
        log.info("Stopping server");
        if(server != null)
            server.disablePlugins();
        if(serverConfigurationManager != null)
            serverConfigurationManager.savePlayers();
        WorldServer worldserver = (WorldServer)worlds.get(0);
        if(worldserver != null)
            saveChunks();
    }

    public void safeShutdown()
    {
        isRunning = false;
    }

    public void run()
    {
        if(init())
        {
            long i = System.currentTimeMillis();
            long j = 0L;
            while(isRunning) 
            {
                long k = System.currentTimeMillis();
                long l = k - i;
                if(l > 2000L)
                {
                    log.warning("Can't keep up! Did the system time change, or is the server overloaded?");
                    l = 2000L;
                }
                if(l < 0L)
                {
                    log.warning("Time ran backwards! Did the system time change?");
                    l = 0L;
                }
                j += l;
                i = k;
                if(((WorldServer)worlds.get(0)).everyoneDeeplySleeping())
                {
                    h();
                    j = 0L;
                } else
                {
                    while(j > 50L) 
                    {
                        currentTick = (int)(System.currentTimeMillis() / 50L);
                        j -= 50L;
                        h();
                    }
                }
                Thread.sleep(1L);
            }
        } else
        {
            while(isRunning) 
            {
                b();
                try
                {
                    Thread.sleep(10L);
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            }
        }
        stop();
        isStopped = true;
        System.exit(0);
        break MISSING_BLOCK_LABEL_347;
        Throwable throwable1;
        throwable1;
        throwable1.printStackTrace();
        System.exit(0);
        break MISSING_BLOCK_LABEL_347;
        Exception exception;
        exception;
        System.exit(0);
        throw exception;
        Throwable throwable;
        throwable;
        throwable.printStackTrace();
        log.log(Level.SEVERE, "Unexpected exception", throwable);
        while(isRunning) 
        {
            b();
            try
            {
                Thread.sleep(10L);
            }
            catch(InterruptedException interruptedexception1)
            {
                interruptedexception1.printStackTrace();
            }
        }
        stop();
        isStopped = true;
        System.exit(0);
        break MISSING_BLOCK_LABEL_347;
        throwable;
        throwable.printStackTrace();
        System.exit(0);
        break MISSING_BLOCK_LABEL_347;
        Exception exception1;
        exception1;
        System.exit(0);
        throw exception1;
        Exception exception2;
        exception2;
        stop();
        isStopped = true;
        System.exit(0);
        break MISSING_BLOCK_LABEL_344;
        Throwable throwable1;
        throwable1;
        throwable1.printStackTrace();
        System.exit(0);
        break MISSING_BLOCK_LABEL_344;
        Exception exception3;
        exception3;
        System.exit(0);
        throw exception3;
        throw exception2;
    }

    private void h()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = trackerList.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            int i = ((Integer)trackerList.get(s)).intValue();
            if(i > 0)
                trackerList.put(s, Integer.valueOf(i - 1));
            else
                arraylist.add(s);
        }

        for(int j = 0; j < arraylist.size(); j++)
            trackerList.remove(arraylist.get(j));

        AxisAlignedBB.a();
        Vec3D.a();
        ticks++;
        ((CraftScheduler)server.getScheduler()).mainThreadHeartbeat(ticks);
        if(ticks % 20 == 0)
        {
            for(int i = 0; i < serverConfigurationManager.players.size(); i++)
            {
                EntityPlayer entityplayer = (EntityPlayer)serverConfigurationManager.players.get(i);
                entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(entityplayer.getPlayerTime()));
            }

        }
        for(int j = 0; j < worlds.size(); j++)
        {
            WorldServer worldserver = (WorldServer)worlds.get(j);
            worldserver.doTick();
            while(worldserver.v()) ;
            worldserver.tickEntities();
        }

        networkListenThread.a();
        serverConfigurationManager.b();
        for(int j = 0; j < worlds.size(); j++)
            ((WorldServer)worlds.get(j)).tracker.updatePlayers();

        for(int j = 0; j < this.s.size(); j++)
            ((IUpdatePlayerListBox)this.s.get(j)).a();

        try
        {
            b();
        }
        catch(Exception exception)
        {
            log.log(Level.WARNING, "Unexpected exception while parsing console command", exception);
        }
    }

    public void issueCommand(String s, ICommandListener icommandlistener)
    {
        t.add(new ServerCommand(s, icommandlistener));
    }

    public void b()
    {
        ServerCommand servercommand;
        for(; t.size() > 0; server.dispatchCommand(console, servercommand))
        {
            servercommand = (ServerCommand)t.remove(0);
            ServerCommandEvent event = new ServerCommandEvent(console, servercommand.command);
            server.getPluginManager().callEvent(event);
            servercommand = new ServerCommand(event.getCommand(), servercommand.b);
        }

    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox)
    {
        s.add(iupdateplayerlistbox);
    }

    public static void main(OptionSet options)
    {
        StatisticList.a();
        try
        {
            MinecraftServer minecraftserver = new MinecraftServer(options);
            (new ThreadServerApplication("Server thread", minecraftserver)).start();
        }
        catch(Exception exception)
        {
            log.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }
    }

    public File a(String s)
    {
        return new File(s);
    }

    public void sendMessage(String s)
    {
        log.info(s);
    }

    public void c(String s)
    {
        log.warning(s);
    }

    public String getName()
    {
        return "CONSOLE";
    }

    public WorldServer getWorldServer(int i)
    {
        for(Iterator i$ = worlds.iterator(); i$.hasNext();)
        {
            WorldServer world = (WorldServer)i$.next();
            if(world.dimension == i)
                return world;
        }

        return (WorldServer)worlds.get(0);
    }

    public EntityTracker getTracker(int i)
    {
        return getWorldServer(i).tracker;
    }

    public static boolean isRunning(MinecraftServer minecraftserver)
    {
        return minecraftserver.isRunning;
    }

    public static Logger log = Logger.getLogger("Minecraft");
    public static HashMap trackerList = new HashMap();
    public NetworkListenThread networkListenThread;
    public PropertyManager propertyManager;
    public ServerConfigurationManager serverConfigurationManager;
    public ConsoleCommandHandler consoleCommandHandler;
    private boolean isRunning;
    public boolean isStopped;
    int ticks;
    public String i;
    public int j;
    private List s;
    private List t;
    public boolean onlineMode;
    public boolean spawnAnimals;
    public boolean pvpMode;
    public boolean allowFlight;
    public String p;
    public List worlds;
    public CraftServer server;
    public OptionSet options;
    public ConsoleCommandSender console;
    public ConsoleReader reader;
    public static int currentTick;

}
