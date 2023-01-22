// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Packet.java

package net.minecraft.server;

import java.io.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            PacketCounter, EmptyClass1, EntityList, Packet0KeepAlive, 
//            Packet1Login, Packet2Handshake, Packet3Chat, Packet4UpdateTime, 
//            Packet5EntityEquipment, Packet6SpawnPosition, Packet7UseEntity, Packet8UpdateHealth, 
//            Packet9Respawn, Packet10Flying, Packet11PlayerPosition, Packet12PlayerLook, 
//            Packet13PlayerLookMove, Packet14BlockDig, Packet15Place, Packet16BlockItemSwitch, 
//            Packet17, Packet18ArmAnimation, Packet19EntityAction, Packet20NamedEntitySpawn, 
//            Packet21PickupSpawn, Packet22Collect, Packet23VehicleSpawn, Packet24MobSpawn, 
//            Packet25EntityPainting, Packet26AddExpOrb, Packet27, Packet28EntityVelocity, 
//            Packet29DestroyEntity, Packet30Entity, Packet31RelEntityMove, Packet32EntityLook, 
//            Packet33RelEntityMoveLook, Packet34EntityTeleport, Packet38EntityStatus, Packet39AttachEntity, 
//            Packet40EntityMetadata, Packet41MobEffect, Packet42RemoveMobEffect, Packet43SetExperience, 
//            Packet50PreChunk, Packet51MapChunk, Packet52MultiBlockChange, Packet53BlockChange, 
//            Packet54PlayNoteBlock, Packet60Explosion, Packet61, Packet70Bed, 
//            Packet71Weather, Packet100OpenWindow, Packet101CloseWindow, Packet102WindowClick, 
//            Packet103SetSlot, Packet104WindowItems, Packet105CraftProgressBar, Packet106Transaction, 
//            Packet107SetCreativeSlot, Packet130UpdateSign, Packet131, Packet200Statistic, 
//            Packet201PlayerInfo, Packet254GetInfo, Packet255KickDisconnect, NetHandler

public abstract class Packet
{

    public Packet()
    {
        k = false;
    }

    static void a(int i, boolean flag, boolean flag1, Class oclass)
    {
        if(a.b(i))
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet id:").append(i).toString());
        if(b.containsKey(oclass))
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet class:").append(oclass).toString());
        a.a(i, oclass);
        b.put(oclass, Integer.valueOf(i));
        if(flag)
            c.add(Integer.valueOf(i));
        if(flag1)
            d.add(Integer.valueOf(i));
    }

    public static Packet a(int i)
    {
        try
        {
            Class oclass = (Class)a.a(i);
            return oclass != null ? (Packet)oclass.newInstance() : null;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        System.out.println((new StringBuilder()).append("Skipping packet with id ").append(i).toString());
        return null;
    }

    public final int b()
    {
        return ((Integer)b.get(getClass())).intValue();
    }

    public static Packet a(DataInputStream datainputstream, boolean flag)
        throws IOException
    {
        Packet packet;
        int i;
        boolean flag1 = false;
        packet = null;
        try
        {
            i = datainputstream.read();
            if(i == -1)
                return null;
        }
        catch(EOFException eofexception)
        {
            System.out.println("Reached end of stream");
            return null;
        }
        catch(SocketTimeoutException exception)
        {
            System.out.println("Read timed out");
            return null;
        }
        catch(SocketException exception)
        {
            System.out.println("Connection reset");
            return null;
        }
        if(flag && !d.contains(Integer.valueOf(i)) || !flag && !c.contains(Integer.valueOf(i)))
            throw new IOException((new StringBuilder()).append("Bad packet id ").append(i).toString());
        packet = a(i);
        if(packet == null)
            throw new IOException((new StringBuilder()).append("Bad packet id ").append(i).toString());
        packet.a(datainputstream);
        PacketCounter packetcounter = (PacketCounter)e.a(i);
        if(packetcounter == null)
        {
            packetcounter = new PacketCounter((EmptyClass1)null);
            e.a(i, packetcounter);
        }
        packetcounter.a(packet.a());
        f++;
        if(f % 1000 != 0);
        return packet;
    }

    public static void a(Packet packet, DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.write(packet.b());
        packet.a(dataoutputstream);
    }

    public static void a(String s, DataOutputStream dataoutputstream)
        throws IOException
    {
        if(s.length() > 32767)
        {
            throw new IOException("String too big");
        } else
        {
            dataoutputstream.writeShort(s.length());
            dataoutputstream.writeChars(s);
            return;
        }
    }

    public static String a(DataInputStream datainputstream, int i)
        throws IOException
    {
        short short1 = datainputstream.readShort();
        if(short1 > i)
            throw new IOException((new StringBuilder()).append("Received string length longer than maximum allowed (").append(short1).append(" > ").append(i).append(")").toString());
        if(short1 < 0)
            throw new IOException("Received string length is less than zero! Weird string!");
        StringBuilder stringbuilder = new StringBuilder();
        for(int j = 0; j < short1; j++)
            stringbuilder.append(datainputstream.readChar());

        return stringbuilder.toString();
    }

    public abstract void a(DataInputStream datainputstream)
        throws IOException;

    public abstract void a(DataOutputStream dataoutputstream)
        throws IOException;

    public abstract void a(NetHandler nethandler);

    public abstract int a();

    private static EntityList a = new EntityList();
    private static Map b = new HashMap();
    private static Set c = new HashSet();
    private static Set d = new HashSet();
    public final long timestamp = System.currentTimeMillis();
    public boolean k;
    private static EntityList e = new EntityList();
    private static int f = 0;

    static 
    {
        a(0, true, true, net/minecraft/server/Packet0KeepAlive);
        a(1, true, true, net/minecraft/server/Packet1Login);
        a(2, true, true, net/minecraft/server/Packet2Handshake);
        a(3, true, true, net/minecraft/server/Packet3Chat);
        a(4, true, false, net/minecraft/server/Packet4UpdateTime);
        a(5, true, false, net/minecraft/server/Packet5EntityEquipment);
        a(6, true, false, net/minecraft/server/Packet6SpawnPosition);
        a(7, false, true, net/minecraft/server/Packet7UseEntity);
        a(8, true, false, net/minecraft/server/Packet8UpdateHealth);
        a(9, true, true, net/minecraft/server/Packet9Respawn);
        a(10, true, true, net/minecraft/server/Packet10Flying);
        a(11, true, true, net/minecraft/server/Packet11PlayerPosition);
        a(12, true, true, net/minecraft/server/Packet12PlayerLook);
        a(13, true, true, net/minecraft/server/Packet13PlayerLookMove);
        a(14, false, true, net/minecraft/server/Packet14BlockDig);
        a(15, false, true, net/minecraft/server/Packet15Place);
        a(16, false, true, net/minecraft/server/Packet16BlockItemSwitch);
        a(17, true, false, net/minecraft/server/Packet17);
        a(18, true, true, net/minecraft/server/Packet18ArmAnimation);
        a(19, false, true, net/minecraft/server/Packet19EntityAction);
        a(20, true, false, net/minecraft/server/Packet20NamedEntitySpawn);
        a(21, true, false, net/minecraft/server/Packet21PickupSpawn);
        a(22, true, false, net/minecraft/server/Packet22Collect);
        a(23, true, false, net/minecraft/server/Packet23VehicleSpawn);
        a(24, true, false, net/minecraft/server/Packet24MobSpawn);
        a(25, true, false, net/minecraft/server/Packet25EntityPainting);
        a(26, true, false, net/minecraft/server/Packet26AddExpOrb);
        a(27, false, false, net/minecraft/server/Packet27);
        a(28, true, false, net/minecraft/server/Packet28EntityVelocity);
        a(29, true, false, net/minecraft/server/Packet29DestroyEntity);
        a(30, true, false, net/minecraft/server/Packet30Entity);
        a(31, true, false, net/minecraft/server/Packet31RelEntityMove);
        a(32, true, false, net/minecraft/server/Packet32EntityLook);
        a(33, true, false, net/minecraft/server/Packet33RelEntityMoveLook);
        a(34, true, false, net/minecraft/server/Packet34EntityTeleport);
        a(38, true, false, net/minecraft/server/Packet38EntityStatus);
        a(39, true, false, net/minecraft/server/Packet39AttachEntity);
        a(40, true, false, net/minecraft/server/Packet40EntityMetadata);
        a(41, true, false, net/minecraft/server/Packet41MobEffect);
        a(42, true, false, net/minecraft/server/Packet42RemoveMobEffect);
        a(43, true, false, net/minecraft/server/Packet43SetExperience);
        a(50, true, false, net/minecraft/server/Packet50PreChunk);
        a(51, true, false, net/minecraft/server/Packet51MapChunk);
        a(52, true, false, net/minecraft/server/Packet52MultiBlockChange);
        a(53, true, false, net/minecraft/server/Packet53BlockChange);
        a(54, true, false, net/minecraft/server/Packet54PlayNoteBlock);
        a(60, true, false, net/minecraft/server/Packet60Explosion);
        a(61, true, false, net/minecraft/server/Packet61);
        a(70, true, false, net/minecraft/server/Packet70Bed);
        a(71, true, false, net/minecraft/server/Packet71Weather);
        a(100, true, false, net/minecraft/server/Packet100OpenWindow);
        a(101, true, true, net/minecraft/server/Packet101CloseWindow);
        a(102, false, true, net/minecraft/server/Packet102WindowClick);
        a(103, true, false, net/minecraft/server/Packet103SetSlot);
        a(104, true, false, net/minecraft/server/Packet104WindowItems);
        a(105, true, false, net/minecraft/server/Packet105CraftProgressBar);
        a(106, true, true, net/minecraft/server/Packet106Transaction);
        a(107, true, true, net/minecraft/server/Packet107SetCreativeSlot);
        a(130, true, true, net/minecraft/server/Packet130UpdateSign);
        a(131, true, false, net/minecraft/server/Packet131);
        a(200, true, false, net/minecraft/server/Packet200Statistic);
        a(201, true, false, net/minecraft/server/Packet201PlayerInfo);
        a(254, false, true, net/minecraft/server/Packet254GetInfo);
        a(255, true, true, net/minecraft/server/Packet255KickDisconnect);
    }
}
