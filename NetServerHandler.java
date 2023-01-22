// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetServerHandler.java

package net.minecraft.server;

import java.io.PrintStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.*;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            NetHandler, EntityList, Packet0KeepAlive, Packet255KickDisconnect, 
//            Packet3Chat, Packet13PlayerLookMove, Packet53BlockChange, Packet103SetSlot, 
//            Packet6SpawnPosition, Packet106Transaction, Slot, ItemStack, 
//            TileEntitySign, Packet130UpdateSign, ICommandListener, MinecraftServer, 
//            NetworkManager, EntityPlayer, ServerConfigurationManager, Packet27, 
//            Packet10Flying, Entity, WorldServer, AxisAlignedBB, 
//            ItemInWorldManager, Packet14BlockDig, ChunkCoordinates, MathHelper, 
//            Packet15Place, InventoryPlayer, Container, Packet, 
//            Packet16BlockItemSwitch, FontAllowedCharacters, Packet18ArmAnimation, Vec3D, 
//            World, MovingObjectPosition, EnumMovingObjectType, Packet19EntityAction, 
//            Packet7UseEntity, Packet102WindowClick, Packet107SetCreativeSlot, Item, 
//            Packet9Respawn, Packet101CloseWindow

public class NetServerHandler extends NetHandler
    implements ICommandListener
{

    public NetServerHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer)
    {
        disconnected = false;
        checkMovement = true;
        q = new EntityList();
        lastTick = MinecraftServer.currentTick;
        lastDropTick = MinecraftServer.currentTick;
        dropCount = 0;
        lastPosX = 1.7976931348623157E+308D;
        lastPosY = 1.7976931348623157E+308D;
        lastPosZ = 1.7976931348623157E+308D;
        lastPitch = 3.402823E+038F;
        lastYaw = 3.402823E+038F;
        justTeleported = false;
        minecraftServer = minecraftserver;
        networkManager = networkmanager;
        networkmanager.a(this);
        player = entityplayer;
        entityplayer.netServerHandler = this;
        server = minecraftserver.server;
    }

    public CraftPlayer getPlayer()
    {
        return player != null ? (CraftPlayer)player.getBukkitEntity() : null;
    }

    public void a()
    {
        h = false;
        f++;
        networkManager.b();
        if((long)f - l > 20L)
        {
            l = f;
            j = System.nanoTime() / 0xf4240L;
            i = k.nextInt();
            sendPacket(new Packet0KeepAlive(i));
        }
    }

    public void disconnect(String s)
    {
        String leaveMessage = (new StringBuilder()).append("\247e").append(player.name).append(" left the game.").toString();
        PlayerKickEvent event = new PlayerKickEvent(server.getPlayer(player), s, leaveMessage);
        server.getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        s = event.getReason();
        player.A();
        sendPacket(new Packet255KickDisconnect(s));
        networkManager.d();
        leaveMessage = event.getLeaveMessage();
        if(leaveMessage != null)
            minecraftServer.serverConfigurationManager.sendAll(new Packet3Chat(leaveMessage));
        minecraftServer.serverConfigurationManager.disconnect(player);
        disconnected = true;
    }

    public void a(Packet27 packet27)
    {
        player.a(packet27.c(), packet27.e(), packet27.g(), packet27.h(), packet27.d(), packet27.f());
    }

    public void a(Packet10Flying packet10flying)
    {
        WorldServer worldserver = minecraftServer.getWorldServer(this.player.dimension);
        h = true;
        if(!checkMovement)
        {
            double d0 = packet10flying.y - y;
            if(packet10flying.x == x && d0 * d0 < 0.01D && packet10flying.z == z)
                checkMovement = true;
        }
        Player player = getPlayer();
        Location from = new Location(player.getWorld(), lastPosX, lastPosY, lastPosZ, lastYaw, lastPitch);
        Location to = player.getLocation().clone();
        if(packet10flying.h && (!packet10flying.h || packet10flying.y != -999D || packet10flying.stance != -999D))
        {
            to.setX(packet10flying.x);
            to.setY(packet10flying.y);
            to.setZ(packet10flying.z);
        }
        if(packet10flying.hasLook)
        {
            to.setYaw(packet10flying.yaw);
            to.setPitch(packet10flying.pitch);
        }
        double delta = Math.pow(lastPosX - to.getX(), 2D) + Math.pow(lastPosY - to.getY(), 2D) + Math.pow(lastPosZ - to.getZ(), 2D);
        float deltaAngle = Math.abs(lastYaw - to.getYaw()) + Math.abs(lastPitch - to.getPitch());
        if((delta > 0.00390625D || deltaAngle > 10F) && checkMovement && !this.player.dead)
        {
            lastPosX = to.getX();
            lastPosY = to.getY();
            lastPosZ = to.getZ();
            lastYaw = to.getYaw();
            lastPitch = to.getPitch();
            if(from.getX() != 1.7976931348623157E+308D)
            {
                PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
                server.getPluginManager().callEvent(event);
                if(event.isCancelled())
                {
                    this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(from.getX(), from.getY() + 1.6200000047683716D, from.getY(), from.getZ(), from.getYaw(), from.getPitch(), false));
                    return;
                }
                if(!to.equals(event.getTo()) && !event.isCancelled())
                {
                    this.player.getBukkitEntity().teleport(event.getTo());
                    return;
                }
                if(!from.equals(getPlayer().getLocation()) && justTeleported)
                {
                    justTeleported = false;
                    return;
                }
            }
        }
        if(Double.isNaN(packet10flying.x) || Double.isNaN(packet10flying.y) || Double.isNaN(packet10flying.z) || Double.isNaN(packet10flying.stance))
        {
            player.teleport(player.getWorld().getSpawnLocation());
            System.err.println((new StringBuilder()).append(player.getName()).append(" was caught trying to crash the server with an invalid position.").toString());
            player.kickPlayer("Nope!");
            return;
        }
        if(checkMovement && !this.player.dead)
        {
            double d1;
            double d2;
            double d3;
            double d4;
            if(this.player.vehicle != null)
            {
                float f = this.player.yaw;
                float f1 = this.player.pitch;
                this.player.vehicle.g_();
                d1 = this.player.locX;
                d2 = this.player.locY;
                d3 = this.player.locZ;
                double d5 = 0.0D;
                d4 = 0.0D;
                if(packet10flying.hasLook)
                {
                    f = packet10flying.yaw;
                    f1 = packet10flying.pitch;
                }
                if(packet10flying.h && packet10flying.y == -999D && packet10flying.stance == -999D)
                {
                    d5 = packet10flying.x;
                    d4 = packet10flying.z;
                }
                this.player.onGround = packet10flying.g;
                this.player.b(true);
                this.player.move(d5, 0.0D, d4);
                this.player.setLocation(d1, d2, d3, f, f1);
                this.player.motX = d5;
                this.player.motZ = d4;
                if(this.player.vehicle != null)
                    worldserver.vehicleEnteredWorld(this.player.vehicle, true);
                if(this.player.vehicle != null)
                    this.player.vehicle.g_();
                minecraftServer.serverConfigurationManager.d(this.player);
                x = this.player.locX;
                y = this.player.locY;
                z = this.player.locZ;
                worldserver.playerJoinedWorld(this.player);
                return;
            }
            if(this.player.isSleeping())
            {
                this.player.b(true);
                this.player.setLocation(x, y, z, this.player.yaw, this.player.pitch);
                worldserver.playerJoinedWorld(this.player);
                return;
            }
            double d0 = this.player.locY;
            x = this.player.locX;
            y = this.player.locY;
            z = this.player.locZ;
            d1 = this.player.locX;
            d2 = this.player.locY;
            d3 = this.player.locZ;
            float f2 = this.player.yaw;
            float f3 = this.player.pitch;
            if(packet10flying.h && packet10flying.y == -999D && packet10flying.stance == -999D)
                packet10flying.h = false;
            if(packet10flying.h)
            {
                d1 = packet10flying.x;
                d2 = packet10flying.y;
                d3 = packet10flying.z;
                d4 = packet10flying.stance - packet10flying.y;
                if(!this.player.isSleeping() && (d4 > 1.6499999999999999D || d4 < 0.10000000000000001D))
                {
                    disconnect("Illegal stance");
                    a.warning((new StringBuilder()).append(this.player.name).append(" had an illegal stance: ").append(d4).toString());
                    return;
                }
                if(Math.abs(packet10flying.x) > 32000000D || Math.abs(packet10flying.z) > 32000000D)
                {
                    disconnect("Illegal position");
                    return;
                }
            }
            if(packet10flying.hasLook)
            {
                f2 = packet10flying.yaw;
                f3 = packet10flying.pitch;
            }
            this.player.b(true);
            this.player.bH = 0.0F;
            this.player.setLocation(x, y, z, f2, f3);
            if(!checkMovement)
                return;
            d4 = d1 - this.player.locX;
            double d6 = d2 - this.player.locY;
            double d7 = d3 - this.player.locZ;
            double d8 = d4 * d4 + d6 * d6 + d7 * d7;
            if(d8 > 100D && checkMovement)
            {
                a.warning((new StringBuilder()).append(this.player.name).append(" moved too quickly!").toString());
                disconnect("You moved too quickly :( (Hacking?)");
                return;
            }
            float f4 = 0.0625F;
            boolean flag = worldserver.getEntities(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).size() == 0;
            if(this.player.onGround && !packet10flying.g && d6 > 0.0D)
                this.player.b(0.2F);
            this.player.move(d4, d6, d7);
            this.player.onGround = packet10flying.g;
            this.player.a(d4, d6, d7);
            d4 = d1 - this.player.locX;
            d6 = d2 - this.player.locY;
            if(d6 > -0.5D || d6 < 0.5D)
                d6 = 0.0D;
            d7 = d3 - this.player.locZ;
            d8 = d4 * d4 + d6 * d6 + d7 * d7;
            boolean flag1 = false;
            if(d8 > 0.0625D && !this.player.isSleeping() && !this.player.itemInWorldManager.b())
            {
                flag1 = true;
                a.warning((new StringBuilder()).append(this.player.name).append(" moved wrongly!").toString());
                System.out.println((new StringBuilder()).append("Got position ").append(d1).append(", ").append(d2).append(", ").append(d3).toString());
                System.out.println((new StringBuilder()).append("Expected ").append(this.player.locX).append(", ").append(this.player.locY).append(", ").append(this.player.locZ).toString());
            }
            this.player.setLocation(d1, d2, d3, f2, f3);
            boolean flag2 = worldserver.getEntities(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).size() == 0;
            if(flag && (flag1 || !flag2) && !this.player.isSleeping())
            {
                a(x, y, z, f2, f3);
                return;
            }
            AxisAlignedBB axisalignedbb = this.player.boundingBox.clone().b(f4, f4, f4).a(0.0D, -0.55000000000000004D, 0.0D);
            if(!minecraftServer.allowFlight && !this.player.itemInWorldManager.b() && !worldserver.b(axisalignedbb))
            {
                if(d6 >= -0.03125D)
                {
                    g++;
                    if(g > 80)
                    {
                        a.warning((new StringBuilder()).append(this.player.name).append(" was kicked for floating too long!").toString());
                        disconnect("Flying is not enabled on this server");
                        return;
                    }
                }
            } else
            {
                g = 0;
            }
            this.player.onGround = packet10flying.g;
            minecraftServer.serverConfigurationManager.d(this.player);
            if(this.player.itemInWorldManager.b())
                return;
            this.player.b(this.player.locY - d0, packet10flying.g);
        }
    }

    public void a(double d0, double d1, double d2, float f, 
            float f1)
    {
        Player player = getPlayer();
        Location from = player.getLocation();
        Location to = new Location(getPlayer().getWorld(), d0, d1, d2, f, f1);
        PlayerTeleportEvent event = new PlayerTeleportEvent(player, from, to);
        server.getPluginManager().callEvent(event);
        from = event.getFrom();
        to = event.isCancelled() ? from : event.getTo();
        teleport(to);
    }

    public void teleport(Location dest)
    {
        double d0 = dest.getX();
        double d1 = dest.getY();
        double d2 = dest.getZ();
        float f = dest.getYaw();
        float f1 = dest.getPitch();
        if(Float.isNaN(f))
            f = 0.0F;
        if(Float.isNaN(f1))
            f1 = 0.0F;
        lastPosX = d0;
        lastPosY = d1;
        lastPosZ = d2;
        lastYaw = f;
        lastPitch = f1;
        justTeleported = true;
        checkMovement = false;
        x = d0;
        y = d1;
        z = d2;
        player.setLocation(d0, d1, d2, f, f1);
        player.netServerHandler.sendPacket(new Packet13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false));
    }

    public void a(Packet14BlockDig packet14blockdig)
    {
        if(player.dead)
            return;
        WorldServer worldserver = minecraftServer.getWorldServer(player.dimension);
        if(packet14blockdig.e == 4)
        {
            if(lastDropTick != MinecraftServer.currentTick)
            {
                dropCount = 0;
                lastDropTick = MinecraftServer.currentTick;
            } else
            {
                dropCount++;
                if(dropCount >= 20)
                {
                    a.warning((new StringBuilder()).append(player.name).append(" dropped their items too quickly!").toString());
                    disconnect("You dropped your items too quickly (Hacking?)");
                    return;
                }
            }
            player.J();
        } else
        if(packet14blockdig.e == 5)
        {
            player.E();
        } else
        {
            boolean flag = worldserver.weirdIsOpCache = worldserver.dimension != 0 || minecraftServer.serverConfigurationManager.isOp(player.name);
            boolean flag1 = false;
            if(packet14blockdig.e == 0)
                flag1 = true;
            if(packet14blockdig.e == 2)
                flag1 = true;
            if(player.itemInWorldManager.b())
                flag1 = true;
            int i = packet14blockdig.a;
            int j = packet14blockdig.b;
            int k = packet14blockdig.c;
            if(flag1)
            {
                double d0 = player.locX - ((double)i + 0.5D);
                double d1 = player.locY - ((double)j + 0.5D);
                double d2 = player.locZ - ((double)k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if(d3 > 36D)
                    return;
            }
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
            int l = MathHelper.a(i - chunkcoordinates.x);
            int i1 = MathHelper.a(k - chunkcoordinates.z);
            if(l > i1)
                i1 = l;
            if(packet14blockdig.e == 0)
            {
                if(i1 < server.getSpawnRadius() && !flag)
                    player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
                else
                    player.itemInWorldManager.dig(i, j, k, packet14blockdig.face);
            } else
            if(packet14blockdig.e == 2)
            {
                player.itemInWorldManager.a(i, j, k);
                if(worldserver.getTypeId(i, j, k) != 0)
                    player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
            } else
            if(packet14blockdig.e == 3)
            {
                double d4 = player.locX - ((double)i + 0.5D);
                double d5 = player.locY - ((double)j + 0.5D);
                double d6 = player.locZ - ((double)k + 0.5D);
                double d7 = d4 * d4 + d5 * d5 + d6 * d6;
                if(d7 < 256D)
                    player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
            }
            worldserver.weirdIsOpCache = false;
        }
    }

    public void a(Packet15Place packet15place)
    {
        WorldServer worldserver = minecraftServer.getWorldServer(player.dimension);
        if(player.dead)
            return;
        if(packet15place.face == 255)
        {
            if(packet15place.itemstack != null && packet15place.itemstack.id == lastMaterial && lastPacket != null && packet15place.timestamp - lastPacket.longValue() < 100L)
            {
                lastPacket = null;
                return;
            }
        } else
        {
            lastMaterial = packet15place.itemstack != null ? packet15place.itemstack.id : -1;
            lastPacket = Long.valueOf(packet15place.timestamp);
        }
        boolean always = false;
        ItemStack itemstack = player.inventory.getItemInHand();
        boolean flag = worldserver.weirdIsOpCache = worldserver.dimension != 0 || minecraftServer.serverConfigurationManager.isOp(player.name);
        if(packet15place.face == 255)
        {
            if(itemstack == null)
                return;
            int itemstackAmount = itemstack.count;
            PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(player, Action.RIGHT_CLICK_AIR, itemstack);
            if(event.useItemInHand() != org.bukkit.event.Event.Result.DENY)
                player.itemInWorldManager.useItem(player, player.world, itemstack);
            always = itemstack.count != itemstackAmount;
        } else
        {
            int i = packet15place.a;
            int j = packet15place.b;
            int k = packet15place.c;
            int l = packet15place.face;
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
            int i1 = MathHelper.a(i - chunkcoordinates.x);
            int j1 = MathHelper.a(k - chunkcoordinates.z);
            if(i1 > j1)
                j1 = i1;
            Location eyeLoc = getPlayer().getEyeLocation();
            if(Math.pow(eyeLoc.getX() - (double)i, 2D) + Math.pow(eyeLoc.getY() - (double)j, 2D) + Math.pow(eyeLoc.getZ() - (double)k, 2D) > 36D)
                return;
            flag = true;
            if(j1 > 16 || flag)
                player.itemInWorldManager.interact(player, worldserver, itemstack, i, j, k, l);
            player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
            if(l == 0)
                j--;
            if(l == 1)
                j++;
            if(l == 2)
                k--;
            if(l == 3)
                k++;
            if(l == 4)
                i--;
            if(l == 5)
                i++;
            player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
        }
        itemstack = player.inventory.getItemInHand();
        if(itemstack != null && itemstack.count == 0)
        {
            player.inventory.items[player.inventory.itemInHandIndex] = null;
            itemstack = null;
        }
        if(itemstack == null || itemstack.l() == 0)
        {
            player.h = true;
            player.inventory.items[player.inventory.itemInHandIndex] = ItemStack.b(player.inventory.items[player.inventory.itemInHandIndex]);
            Slot slot = player.activeContainer.a(player.inventory, player.inventory.itemInHandIndex);
            player.activeContainer.a();
            player.h = false;
            if(!ItemStack.equals(player.inventory.getItemInHand(), packet15place.itemstack) || always)
                sendPacket(new Packet103SetSlot(player.activeContainer.windowId, slot.b, player.inventory.getItemInHand()));
        }
        worldserver.weirdIsOpCache = false;
    }

    public void a(String s, Object aobject[])
    {
        if(disconnected)
            return;
        a.info((new StringBuilder()).append(player.name).append(" lost connection: ").append(s).toString());
        String quitMessage = minecraftServer.serverConfigurationManager.disconnect(player);
        if(quitMessage != null)
            minecraftServer.serverConfigurationManager.sendAll(new Packet3Chat(quitMessage));
        disconnected = true;
    }

    public void a(Packet packet)
    {
        a.warning((new StringBuilder()).append(getClass()).append(" wasn't prepared to deal with a ").append(packet.getClass()).toString());
        disconnect("Protocol error, unexpected packet");
    }

    public void sendPacket(Packet packet)
    {
        if(packet instanceof Packet6SpawnPosition)
        {
            Packet6SpawnPosition packet6 = (Packet6SpawnPosition)packet;
            player.compassTarget = new Location(getPlayer().getWorld(), packet6.x, packet6.y, packet6.z);
        } else
        if(packet instanceof Packet3Chat)
        {
            String message = ((Packet3Chat)packet).message;
            String arr$[] = TextWrapper.wrapText(message);
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                String line = arr$[i$];
                networkManager.queue(new Packet3Chat(line));
            }

            packet = null;
        } else
        if(packet.k)
        {
            ChunkCompressionThread.sendPacket(player, packet);
            packet = null;
        }
        if(packet != null)
            networkManager.queue(packet);
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch)
    {
        if(player.dead)
            return;
        if(packet16blockitemswitch.itemInHandIndex >= 0 && packet16blockitemswitch.itemInHandIndex < InventoryPlayer.g())
        {
            PlayerItemHeldEvent event = new PlayerItemHeldEvent(getPlayer(), player.inventory.itemInHandIndex, packet16blockitemswitch.itemInHandIndex);
            server.getPluginManager().callEvent(event);
            player.inventory.itemInHandIndex = packet16blockitemswitch.itemInHandIndex;
        } else
        {
            a.warning((new StringBuilder()).append(player.name).append(" tried to set an invalid carried item").toString());
        }
    }

    public void a(Packet3Chat packet3chat)
    {
        String s = packet3chat.message;
        if(s.length() > 100)
        {
            disconnect("Chat message too long");
        } else
        {
            s = s.trim();
            for(int i = 0; i < s.length(); i++)
                if(FontAllowedCharacters.allowedCharacters.indexOf(s.charAt(i)) < 0)
                {
                    disconnect("Illegal characters in chat");
                    return;
                }

            chat(s);
        }
    }

    public boolean chat(String s)
    {
        if(!this.player.dead)
        {
            if(s.startsWith("/"))
            {
                handleCommand(s);
                return true;
            }
            Player player = getPlayer();
            PlayerChatEvent event = new PlayerChatEvent(player, s);
            server.getPluginManager().callEvent(event);
            if(event.isCancelled())
                return true;
            s = String.format(event.getFormat(), new Object[] {
                event.getPlayer().getDisplayName(), event.getMessage()
            });
            minecraftServer.console.sendMessage(s);
            Player recipient;
            for(Iterator i$ = event.getRecipients().iterator(); i$.hasNext(); recipient.sendMessage(s))
                recipient = (Player)i$.next();

        }
        return false;
    }

    private void handleCommand(String s)
    {
        CraftPlayer player = getPlayer();
        PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, s);
        server.getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        try
        {
            if(server.dispatchCommand(player, s.substring(1)))
                return;
        }
        catch(CommandException ex)
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("An internal error occurred while attempting to perform this command").toString());
            Logger.getLogger(net/minecraft/server/NetServerHandler.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    public void a(Packet18ArmAnimation packet18armanimation)
    {
        if(player.dead)
            return;
        if(packet18armanimation.b == 1)
        {
            float f = 1.0F;
            float f1 = player.lastPitch + (player.pitch - player.lastPitch) * f;
            float f2 = player.lastYaw + (player.yaw - player.lastYaw) * f;
            double d0 = player.lastX + (player.locX - player.lastX) * (double)f;
            double d1 = (player.lastY + (player.locY - player.lastY) * (double)f + 1.6200000000000001D) - (double)player.height;
            double d2 = player.lastZ + (player.locZ - player.lastZ) * (double)f;
            Vec3D vec3d = Vec3D.create(d0, d1, d2);
            float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
            float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
            float f5 = -MathHelper.cos(-f1 * 0.01745329F);
            float f6 = MathHelper.sin(-f1 * 0.01745329F);
            float f7 = f4 * f5;
            float f8 = f3 * f5;
            double d3 = 5D;
            Vec3D vec3d1 = vec3d.add((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
            MovingObjectPosition movingobjectposition = player.world.rayTrace(vec3d, vec3d1, true);
            if(movingobjectposition == null || movingobjectposition.type != EnumMovingObjectType.TILE)
                CraftEventFactory.callPlayerInteractEvent(player, Action.LEFT_CLICK_AIR, player.inventory.getItemInHand());
            PlayerAnimationEvent event = new PlayerAnimationEvent(getPlayer());
            server.getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
            player.v();
        }
    }

    public void a(Packet19EntityAction packet19entityaction)
    {
        if(player.dead)
            return;
        if(packet19entityaction.animation == 1 || packet19entityaction.animation == 2)
        {
            PlayerToggleSneakEvent event = new PlayerToggleSneakEvent(getPlayer(), packet19entityaction.animation == 1);
            server.getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
        }
        if(packet19entityaction.animation == 4 || packet19entityaction.animation == 5)
        {
            PlayerToggleSprintEvent event = new PlayerToggleSprintEvent(getPlayer(), packet19entityaction.animation == 4);
            server.getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
        }
        if(packet19entityaction.animation == 1)
            player.setSneak(true);
        else
        if(packet19entityaction.animation == 2)
            player.setSneak(false);
        else
        if(packet19entityaction.animation == 4)
            player.setSprinting(true);
        else
        if(packet19entityaction.animation == 5)
            player.setSprinting(false);
        else
        if(packet19entityaction.animation == 3)
        {
            player.a(false, true, true);
            checkMovement = false;
        }
    }

    public void a(Packet255KickDisconnect packet255kickdisconnect)
    {
        networkManager.a("disconnect.quitting", new Object[0]);
    }

    public int b()
    {
        return networkManager.e();
    }

    public void sendMessage(String s)
    {
        sendPacket(new Packet3Chat((new StringBuilder()).append("\2477").append(s).toString()));
    }

    public String getName()
    {
        return player.name;
    }

    public void a(Packet7UseEntity packet7useentity)
    {
        if(player.dead)
            return;
        WorldServer worldserver = minecraftServer.getWorldServer(player.dimension);
        net.minecraft.server.Entity entity = worldserver.getEntity(packet7useentity.target);
        if(entity != null && player.f(entity) && player.h(entity) < 36D)
        {
            ItemStack itemInHand = player.inventory.getItemInHand();
            if(packet7useentity.c == 0)
            {
                PlayerInteractEntityEvent event = new PlayerInteractEntityEvent(getPlayer(), entity.getBukkitEntity());
                server.getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
                player.c(entity);
                if(itemInHand != null && itemInHand.count <= -1)
                    player.updateInventory(player.activeContainer);
            } else
            if(packet7useentity.c == 1)
            {
                player.d(entity);
                if(itemInHand != null && itemInHand.count <= -1)
                    player.updateInventory(player.activeContainer);
            }
        }
    }

    public void a(Packet9Respawn packet9respawn)
    {
        if(player.health <= 0)
        {
            player = minecraftServer.serverConfigurationManager.moveToWorld(player, 0);
            getPlayer().setHandle(player);
        }
    }

    public void a(Packet101CloseWindow packet101closewindow)
    {
        if(player.dead)
        {
            return;
        } else
        {
            player.z();
            return;
        }
    }

    public void a(Packet102WindowClick packet102windowclick)
    {
        if(player.dead)
            return;
        if(player.activeContainer.windowId == packet102windowclick.a && player.activeContainer.c(player))
        {
            ItemStack itemstack = player.activeContainer.a(packet102windowclick.b, packet102windowclick.c, packet102windowclick.f, player);
            if(ItemStack.equals(packet102windowclick.e, itemstack))
            {
                player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true));
                player.h = true;
                player.activeContainer.a();
                player.y();
                player.h = false;
            } else
            {
                q.a(player.activeContainer.windowId, Short.valueOf(packet102windowclick.d));
                player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false));
                player.activeContainer.a(player, false);
                ArrayList arraylist = new ArrayList();
                for(int i = 0; i < player.activeContainer.e.size(); i++)
                    arraylist.add(((Slot)player.activeContainer.e.get(i)).getItem());

                player.a(player.activeContainer, arraylist);
            }
        }
    }

    public void a(Packet107SetCreativeSlot packet107setcreativeslot)
    {
        if(player.itemInWorldManager.b())
        {
            boolean flag = packet107setcreativeslot.a >= 36 && packet107setcreativeslot.a < 36 + InventoryPlayer.g();
            boolean flag1 = packet107setcreativeslot.b >= -1 && packet107setcreativeslot.b < Item.byId.length && (packet107setcreativeslot.b < 0 || Item.byId[packet107setcreativeslot.b] != null);
            boolean flag2 = packet107setcreativeslot.d >= 0 && packet107setcreativeslot.c >= 0 && packet107setcreativeslot.c <= 64;
            if(flag && flag1 && flag2)
            {
                if(packet107setcreativeslot.b <= 0)
                    player.defaultContainer.a(packet107setcreativeslot.a, (ItemStack)null);
                else
                    player.defaultContainer.a(packet107setcreativeslot.a, new ItemStack(packet107setcreativeslot.b, packet107setcreativeslot.c, packet107setcreativeslot.d));
                player.defaultContainer.a(player, true);
            } else
            if(!flag && flag1 && flag2 && packet107setcreativeslot.a == -1 && packet107setcreativeslot.b > 0)
                player.b(new ItemStack(packet107setcreativeslot.b, packet107setcreativeslot.c, packet107setcreativeslot.d));
        }
    }

    public void a(Packet106Transaction packet106transaction)
    {
        if(player.dead)
            return;
        Short oshort = (Short)q.a(player.activeContainer.windowId);
        if(oshort != null && packet106transaction.b == oshort.shortValue() && player.activeContainer.windowId == packet106transaction.a && !player.activeContainer.c(player))
            player.activeContainer.a(player, true);
    }

    public void a(Packet130UpdateSign packet130updatesign)
    {
        if(this.player.dead)
            return;
        WorldServer worldserver = minecraftServer.getWorldServer(this.player.dimension);
        if(worldserver.isLoaded(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z))
        {
            TileEntity tileentity = worldserver.getTileEntity(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z);
            if(tileentity instanceof TileEntitySign)
            {
                TileEntitySign tileentitysign = (TileEntitySign)tileentity;
                if(!tileentitysign.a())
                {
                    minecraftServer.c((new StringBuilder()).append("Player ").append(this.player.name).append(" just tried to change non-editable sign").toString());
                    sendPacket(new Packet130UpdateSign(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z, tileentitysign.lines));
                    return;
                }
            }
            for(int j = 0; j < 4; j++)
            {
                boolean flag = true;
                if(packet130updatesign.lines[j].length() > 15)
                {
                    flag = false;
                } else
                {
                    for(int i = 0; i < packet130updatesign.lines[j].length(); i++)
                        if(FontAllowedCharacters.allowedCharacters.indexOf(packet130updatesign.lines[j].charAt(i)) < 0)
                            flag = false;

                }
                if(!flag)
                    packet130updatesign.lines[j] = "!?";
            }

            if(tileentity instanceof TileEntitySign)
            {
                int j = packet130updatesign.x;
                int k = packet130updatesign.y;
                int i = packet130updatesign.z;
                TileEntitySign tileentitysign1 = (TileEntitySign)tileentity;
                Player player = server.getPlayer(this.player);
                SignChangeEvent event = new SignChangeEvent((CraftBlock)player.getWorld().getBlockAt(j, k, i), server.getPlayer(this.player), packet130updatesign.lines);
                server.getPluginManager().callEvent(event);
                if(!event.isCancelled())
                {
                    for(int l = 0; l < 4; l++)
                        tileentitysign1.lines[l] = event.getLine(l);

                    tileentitysign1.isEditable = true;
                }
                tileentitysign1.update();
                worldserver.notify(j, k, i);
            }
        }
    }

    public void a(Packet0KeepAlive packet0keepalive)
    {
        if(packet0keepalive.a == this.i)
        {
            int i = (int)(System.nanoTime() / 0xf4240L - j);
            player.i = (player.i * 3 + i) / 4;
        }
    }

    public boolean c()
    {
        return true;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    public NetworkManager networkManager;
    public boolean disconnected;
    private MinecraftServer minecraftServer;
    public EntityPlayer player;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private static Random k = new Random();
    private long l;
    private double x;
    private double y;
    private double z;
    private boolean checkMovement;
    private EntityList q;
    private final CraftServer server;
    private int lastTick;
    private int lastDropTick;
    private int dropCount;
    private static final int PLACE_DISTANCE_SQUARED = 36;
    private double lastPosX;
    private double lastPosY;
    private double lastPosZ;
    private float lastPitch;
    private float lastYaw;
    private boolean justTeleported;
    Long lastPacket;
    private int lastMaterial;

}
