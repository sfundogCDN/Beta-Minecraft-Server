// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;
import java.util.logging.Logger;

// Referenced classes of package net.minecraft.server:
//            ServerCommand, ICommandListener, MinecraftServer, ServerConfigurationManager, 
//            WorldServer, EntityPlayer, NetServerHandler, Item, 
//            ItemStack, WorldSettings, ItemInWorldManager, Packet70Bed, 
//            Packet3Chat, PropertyManager

public class ConsoleCommandHandler
{

    public ConsoleCommandHandler(MinecraftServer minecraftserver)
    {
        server = minecraftserver;
    }

    public void handle(ServerCommand servercommand)
    {
        String s = servercommand.command;
        ICommandListener icommandlistener = servercommand.b;
        String s1 = icommandlistener.getName();
        ServerConfigurationManager serverconfigurationmanager = server.serverConfigurationManager;
        if(s.toLowerCase().startsWith("help") || s.toLowerCase().startsWith("?"))
            a(icommandlistener);
        else
        if(s.toLowerCase().startsWith("list"))
            icommandlistener.sendMessage((new StringBuilder()).append("Connected players: ").append(serverconfigurationmanager.c()).toString());
        else
        if(s.toLowerCase().startsWith("stop"))
        {
            print(s1, "Stopping the server..");
            server.safeShutdown();
        } else
        if(s.toLowerCase().startsWith("save-all"))
        {
            print(s1, "Forcing save..");
            if(serverconfigurationmanager != null)
                serverconfigurationmanager.savePlayers();
            for(int i = 0; i < server.worldServer.length; i++)
            {
                WorldServer worldserver = server.worldServer[i];
                worldserver.save(true, null);
            }

            print(s1, "Save complete.");
        } else
        if(s.toLowerCase().startsWith("save-off"))
        {
            print(s1, "Disabling level saving..");
            for(int j = 0; j < server.worldServer.length; j++)
            {
                WorldServer worldserver1 = server.worldServer[j];
                worldserver1.savingDisabled = true;
            }

        } else
        if(s.toLowerCase().startsWith("save-on"))
        {
            print(s1, "Enabling level saving..");
            for(int k = 0; k < server.worldServer.length; k++)
            {
                WorldServer worldserver2 = server.worldServer[k];
                worldserver2.savingDisabled = false;
            }

        } else
        if(s.toLowerCase().startsWith("op "))
        {
            String s2 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.addOp(s2);
            print(s1, (new StringBuilder()).append("Opping ").append(s2).toString());
            serverconfigurationmanager.a(s2, "\247eYou are now op!");
        } else
        if(s.toLowerCase().startsWith("deop "))
        {
            String s3 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.removeOp(s3);
            serverconfigurationmanager.a(s3, "\247eYou are no longer op!");
            print(s1, (new StringBuilder()).append("De-opping ").append(s3).toString());
        } else
        if(s.toLowerCase().startsWith("ban-ip "))
        {
            String s4 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.addIpBan(s4);
            print(s1, (new StringBuilder()).append("Banning ip ").append(s4).toString());
        } else
        if(s.toLowerCase().startsWith("pardon-ip "))
        {
            String s5 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.removeIpBan(s5);
            print(s1, (new StringBuilder()).append("Pardoning ip ").append(s5).toString());
        } else
        if(s.toLowerCase().startsWith("ban "))
        {
            String s6 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.addUserBan(s6);
            print(s1, (new StringBuilder()).append("Banning ").append(s6).toString());
            EntityPlayer entityplayer = serverconfigurationmanager.i(s6);
            if(entityplayer != null)
                entityplayer.netServerHandler.disconnect("Banned by admin");
        } else
        if(s.toLowerCase().startsWith("pardon "))
        {
            String s7 = s.substring(s.indexOf(" ")).trim();
            serverconfigurationmanager.removeUserBan(s7);
            print(s1, (new StringBuilder()).append("Pardoning ").append(s7).toString());
        } else
        if(s.toLowerCase().startsWith("kick "))
        {
            String s8 = s.substring(s.indexOf(" ")).trim();
            EntityPlayer entityplayer1 = null;
            for(int l = 0; l < serverconfigurationmanager.players.size(); l++)
            {
                EntityPlayer entityplayer6 = (EntityPlayer)serverconfigurationmanager.players.get(l);
                if(entityplayer6.name.equalsIgnoreCase(s8))
                    entityplayer1 = entityplayer6;
            }

            if(entityplayer1 != null)
            {
                entityplayer1.netServerHandler.disconnect("Kicked by admin");
                print(s1, (new StringBuilder()).append("Kicking ").append(entityplayer1.name).toString());
            } else
            {
                icommandlistener.sendMessage((new StringBuilder()).append("Can't find user ").append(s8).append(". No kick.").toString());
            }
        } else
        if(s.toLowerCase().startsWith("tp "))
        {
            String as[] = s.split(" ");
            if(as.length == 3)
            {
                EntityPlayer entityplayer2 = serverconfigurationmanager.i(as[1]);
                EntityPlayer entityplayer3 = serverconfigurationmanager.i(as[2]);
                if(entityplayer2 == null)
                    icommandlistener.sendMessage((new StringBuilder()).append("Can't find user ").append(as[1]).append(". No tp.").toString());
                else
                if(entityplayer3 == null)
                    icommandlistener.sendMessage((new StringBuilder()).append("Can't find user ").append(as[2]).append(". No tp.").toString());
                else
                if(entityplayer2.dimension != entityplayer3.dimension)
                {
                    icommandlistener.sendMessage((new StringBuilder()).append("User ").append(as[1]).append(" and ").append(as[2]).append(" are in different dimensions. No tp.").toString());
                } else
                {
                    entityplayer2.netServerHandler.a(entityplayer3.locX, entityplayer3.locY, entityplayer3.locZ, entityplayer3.yaw, entityplayer3.pitch);
                    print(s1, (new StringBuilder()).append("Teleporting ").append(as[1]).append(" to ").append(as[2]).append(".").toString());
                }
            } else
            {
                icommandlistener.sendMessage("Syntax error, please provice a source and a target.");
            }
        } else
        if(s.toLowerCase().startsWith("give "))
        {
            String as1[] = s.split(" ");
            if(as1.length != 3 && as1.length != 4)
                return;
            String s9 = as1[1];
            EntityPlayer entityplayer4 = serverconfigurationmanager.i(s9);
            if(entityplayer4 != null)
                try
                {
                    int j1 = Integer.parseInt(as1[2]);
                    if(Item.byId[j1] != null)
                    {
                        print(s1, (new StringBuilder()).append("Giving ").append(entityplayer4.name).append(" some ").append(j1).toString());
                        int j2 = 1;
                        if(as1.length > 3)
                            j2 = a(as1[3], 1);
                        if(j2 < 1)
                            j2 = 1;
                        if(j2 > 64)
                            j2 = 64;
                        entityplayer4.b(new ItemStack(j1, j2, 0));
                    } else
                    {
                        icommandlistener.sendMessage((new StringBuilder()).append("There's no item with id ").append(j1).toString());
                    }
                }
                catch(NumberFormatException numberformatexception1)
                {
                    icommandlistener.sendMessage((new StringBuilder()).append("There's no item with id ").append(as1[2]).toString());
                }
            else
                icommandlistener.sendMessage((new StringBuilder()).append("Can't find user ").append(s9).toString());
        } else
        if(s.toLowerCase().startsWith("gamemode "))
        {
            String as2[] = s.split(" ");
            if(as2.length != 3)
                return;
            String s10 = as2[1];
            EntityPlayer entityplayer5 = serverconfigurationmanager.i(s10);
            if(entityplayer5 != null)
                try
                {
                    int k1 = Integer.parseInt(as2[2]);
                    k1 = WorldSettings.a(k1);
                    if(entityplayer5.itemInWorldManager.a() != k1)
                    {
                        print(s1, (new StringBuilder()).append("Setting ").append(entityplayer5.name).append(" to game mode ").append(k1).toString());
                        entityplayer5.itemInWorldManager.a(k1);
                        entityplayer5.netServerHandler.sendPacket(new Packet70Bed(3, k1));
                    } else
                    {
                        print(s1, (new StringBuilder()).append(entityplayer5.name).append(" already has game mode ").append(k1).toString());
                    }
                }
                catch(NumberFormatException numberformatexception2)
                {
                    icommandlistener.sendMessage((new StringBuilder()).append("There's no game mode with id ").append(as2[2]).toString());
                }
            else
                icommandlistener.sendMessage((new StringBuilder()).append("Can't find user ").append(s10).toString());
        } else
        if(s.toLowerCase().startsWith("time "))
        {
            String as3[] = s.split(" ");
            if(as3.length != 3)
                return;
            String s11 = as3[1];
            try
            {
                int i1 = Integer.parseInt(as3[2]);
                if("add".equalsIgnoreCase(s11))
                {
                    for(int l1 = 0; l1 < server.worldServer.length; l1++)
                    {
                        WorldServer worldserver3 = server.worldServer[l1];
                        worldserver3.setTimeAndFixTicklists(worldserver3.getTime() + (long)i1);
                    }

                    print(s1, (new StringBuilder()).append("Added ").append(i1).append(" to time").toString());
                } else
                if("set".equalsIgnoreCase(s11))
                {
                    for(int i2 = 0; i2 < server.worldServer.length; i2++)
                    {
                        WorldServer worldserver4 = server.worldServer[i2];
                        worldserver4.setTimeAndFixTicklists(i1);
                    }

                    print(s1, (new StringBuilder()).append("Set time to ").append(i1).toString());
                } else
                {
                    icommandlistener.sendMessage("Unknown method, use either \"add\" or \"set\"");
                }
            }
            catch(NumberFormatException numberformatexception)
            {
                icommandlistener.sendMessage((new StringBuilder()).append("Unable to convert time value, ").append(as3[2]).toString());
            }
        } else
        if(s.toLowerCase().startsWith("say "))
        {
            s = s.substring(s.indexOf(" ")).trim();
            a.info((new StringBuilder()).append("[").append(s1).append("] ").append(s).toString());
            serverconfigurationmanager.sendAll(new Packet3Chat((new StringBuilder()).append("\247d[Server] ").append(s).toString()));
        } else
        if(s.toLowerCase().startsWith("tell "))
        {
            String as4[] = s.split(" ");
            if(as4.length >= 3)
            {
                s = s.substring(s.indexOf(" ")).trim();
                s = s.substring(s.indexOf(" ")).trim();
                a.info((new StringBuilder()).append("[").append(s1).append("->").append(as4[1]).append("] ").append(s).toString());
                s = (new StringBuilder()).append("\2477").append(s1).append(" whispers ").append(s).toString();
                a.info(s);
                if(!serverconfigurationmanager.a(as4[1], new Packet3Chat(s)))
                    icommandlistener.sendMessage("There's no player by that name online.");
            }
        } else
        if(s.toLowerCase().startsWith("whitelist "))
            a(s1, s, icommandlistener);
        else
            a.info("Unknown console command. Type \"help\" for help.");
    }

    private void a(String s, String s1, ICommandListener icommandlistener)
    {
        String as[] = s1.split(" ");
        if(as.length < 2)
            return;
        String s2 = as[1].toLowerCase();
        if("on".equals(s2))
        {
            print(s, "Turned on white-listing");
            server.propertyManager.setBoolean("white-list", true);
        } else
        if("off".equals(s2))
        {
            print(s, "Turned off white-listing");
            server.propertyManager.setBoolean("white-list", false);
        } else
        if("list".equals(s2))
        {
            Set set = server.serverConfigurationManager.getWhitelisted();
            String s5 = "";
            for(Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                String s6 = (String)iterator.next();
                s5 = (new StringBuilder()).append(s5).append(s6).append(" ").toString();
            }

            icommandlistener.sendMessage((new StringBuilder()).append("White-listed players: ").append(s5).toString());
        } else
        if("add".equals(s2) && as.length == 3)
        {
            String s3 = as[2].toLowerCase();
            server.serverConfigurationManager.addWhitelist(s3);
            print(s, (new StringBuilder()).append("Added ").append(s3).append(" to white-list").toString());
        } else
        if("remove".equals(s2) && as.length == 3)
        {
            String s4 = as[2].toLowerCase();
            server.serverConfigurationManager.removeWhitelist(s4);
            print(s, (new StringBuilder()).append("Removed ").append(s4).append(" from white-list").toString());
        } else
        if("reload".equals(s2))
        {
            server.serverConfigurationManager.reloadWhitelist();
            print(s, "Reloaded white-list from file");
        }
    }

    private void a(ICommandListener icommandlistener)
    {
        icommandlistener.sendMessage("To run the server without a gui, start it like this:");
        icommandlistener.sendMessage("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
        icommandlistener.sendMessage("Console commands:");
        icommandlistener.sendMessage("   help  or  ?               shows this message");
        icommandlistener.sendMessage("   kick <player>             removes a player from the server");
        icommandlistener.sendMessage("   ban <player>              bans a player from the server");
        icommandlistener.sendMessage("   pardon <player>           pardons a banned player so that they can connect again");
        icommandlistener.sendMessage("   ban-ip <ip>               bans an IP address from the server");
        icommandlistener.sendMessage("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
        icommandlistener.sendMessage("   op <player>               turns a player into an op");
        icommandlistener.sendMessage("   deop <player>             removes op status from a player");
        icommandlistener.sendMessage("   tp <player1> <player2>    moves one player to the same location as another player");
        icommandlistener.sendMessage("   give <player> <id> [num]  gives a player a resource");
        icommandlistener.sendMessage("   tell <player> <message>   sends a private message to a player");
        icommandlistener.sendMessage("   stop                      gracefully stops the server");
        icommandlistener.sendMessage("   save-all                  forces a server-wide level save");
        icommandlistener.sendMessage("   save-off                  disables terrain saving (useful for backup scripts)");
        icommandlistener.sendMessage("   save-on                   re-enables terrain saving");
        icommandlistener.sendMessage("   list                      lists all currently connected players");
        icommandlistener.sendMessage("   say <message>             broadcasts a message to all players");
        icommandlistener.sendMessage("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
        icommandlistener.sendMessage("   gamemode <player> <mode>  sets player's game mode (0 or 1)");
    }

    private void print(String s, String s1)
    {
        String s2 = (new StringBuilder()).append(s).append(": ").append(s1).toString();
        server.serverConfigurationManager.j((new StringBuilder()).append("\2477(").append(s2).append(")").toString());
        a.info(s2);
    }

    private int a(String s, int i)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            return i;
        }
    }

    private static Logger a = Logger.getLogger("Minecraft");
    private MinecraftServer server;

}
