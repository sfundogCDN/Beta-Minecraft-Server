// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityTrackerEntry.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

// Referenced classes of package net.minecraft.server:
//            Packet33RelEntityMoveLook, Packet31RelEntityMove, Packet32EntityLook, Packet34EntityTeleport, 
//            Packet28EntityVelocity, Packet, Packet40EntityMetadata, EntityPlayer, 
//            Packet29DestroyEntity, Packet5EntityEquipment, EntityHuman, Packet17, 
//            EntityLiving, MobEffect, Packet41MobEffect, EntityItem, 
//            Packet21PickupSpawn, Packet20NamedEntitySpawn, EntityMinecart, Packet23VehicleSpawn, 
//            EntityBoat, IAnimal, Packet24MobSpawn, EntityFishingHook, 
//            EntityArrow, EntitySnowball, EntityFireball, EntityEgg, 
//            EntityTNTPrimed, EntityFallingSand, EntityPainting, Packet25EntityPainting, 
//            EntityExperienceOrb, Packet26AddExpOrb, Entity, MathHelper, 
//            DataWatcher, World, NetServerHandler, Block

public class EntityTrackerEntry
{

    public EntityTrackerEntry(Entity entity, int i, int j, boolean flag)
    {
        l = 0;
        r = false;
        t = 0;
        m = false;
        trackedPlayers = new HashSet();
        tracker = entity;
        b = i;
        c = j;
        isMoving = flag;
        d = MathHelper.floor(entity.locX * 32D);
        e = MathHelper.floor(entity.locY * 32D);
        f = MathHelper.floor(entity.locZ * 32D);
        g = MathHelper.d((entity.yaw * 256F) / 360F);
        h = MathHelper.d((entity.pitch * 256F) / 360F);
    }

    public boolean equals(Object object)
    {
        return (object instanceof EntityTrackerEntry) ? ((EntityTrackerEntry)object).tracker.id == tracker.id : false;
    }

    public int hashCode()
    {
        return tracker.id;
    }

    public void track(List list)
    {
        m = false;
        if(!r || tracker.e(o, p, q) > 16D)
        {
            o = tracker.locX;
            p = tracker.locY;
            q = tracker.locZ;
            r = true;
            m = true;
            scanPlayers(list);
        }
        t++;
        if(++this.l % c == 0 || tracker.ca)
        {
            int i = MathHelper.floor(tracker.locX * 32D);
            int j = MathHelper.floor(tracker.locY * 32D);
            int k = MathHelper.floor(tracker.locZ * 32D);
            int l = MathHelper.d((tracker.yaw * 256F) / 360F);
            int i1 = MathHelper.d((tracker.pitch * 256F) / 360F);
            int j1 = i - d;
            int k1 = j - e;
            int l1 = k - f;
            Object object = null;
            boolean flag = Math.abs(i) >= 8 || Math.abs(j) >= 8 || Math.abs(k) >= 8;
            boolean flag1 = Math.abs(l - g) >= 8 || Math.abs(i1 - h) >= 8;
            if(j1 >= -128 && j1 < 128 && k1 >= -128 && k1 < 128 && l1 >= -128 && l1 < 128 && t <= 400)
            {
                if(flag && flag1)
                    object = new Packet33RelEntityMoveLook(tracker.id, (byte)j1, (byte)k1, (byte)l1, (byte)l, (byte)i1);
                else
                if(flag)
                    object = new Packet31RelEntityMove(tracker.id, (byte)j1, (byte)k1, (byte)l1);
                else
                if(flag1)
                    object = new Packet32EntityLook(tracker.id, (byte)l, (byte)i1);
            } else
            {
                t = 0;
                tracker.locX = (double)i / 32D;
                tracker.locY = (double)j / 32D;
                tracker.locZ = (double)k / 32D;
                object = new Packet34EntityTeleport(tracker.id, i, j, k, (byte)l, (byte)i1);
            }
            if(isMoving)
            {
                double d0 = tracker.motX - this.i;
                double d1 = tracker.motY - this.j;
                double d2 = tracker.motZ - this.k;
                double d3 = 0.02D;
                double d4 = d0 * d0 + d1 * d1 + d2 * d2;
                if(d4 > d3 * d3 || d4 > 0.0D && tracker.motX == 0.0D && tracker.motY == 0.0D && tracker.motZ == 0.0D)
                {
                    this.i = tracker.motX;
                    this.j = tracker.motY;
                    this.k = tracker.motZ;
                    a(new Packet28EntityVelocity(tracker.id, this.i, this.j, this.k));
                }
            }
            if(object != null)
                a((Packet)object);
            DataWatcher datawatcher = tracker.getDataWatcher();
            if(datawatcher.a())
                b(new Packet40EntityMetadata(tracker.id, datawatcher));
            if(flag)
            {
                d = i;
                e = j;
                f = k;
            }
            if(flag1)
            {
                g = l;
                h = i1;
            }
        }
        tracker.ca = false;
        if(tracker.velocityChanged)
        {
            boolean cancelled = false;
            if(tracker instanceof EntityPlayer)
            {
                Player player = (Player)tracker.getBukkitEntity();
                Vector velocity = player.getVelocity();
                PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity);
                tracker.world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    cancelled = true;
                else
                if(!velocity.equals(event.getVelocity()))
                    player.setVelocity(velocity);
            }
            if(!cancelled)
                b(new Packet28EntityVelocity(tracker));
            tracker.velocityChanged = false;
        }
    }

    public void a(Packet packet)
    {
        EntityPlayer entityplayer;
        for(Iterator iterator = trackedPlayers.iterator(); iterator.hasNext(); entityplayer.netServerHandler.sendPacket(packet))
            entityplayer = (EntityPlayer)iterator.next();

    }

    public void b(Packet packet)
    {
        a(packet);
        if(tracker instanceof EntityPlayer)
            ((EntityPlayer)tracker).netServerHandler.sendPacket(packet);
    }

    public void a()
    {
        a(((Packet) (new Packet29DestroyEntity(tracker.id))));
    }

    public void a(EntityPlayer entityplayer)
    {
        if(trackedPlayers.contains(entityplayer))
            trackedPlayers.remove(entityplayer);
    }

    public void b(EntityPlayer entityplayer)
    {
        if(entityplayer != tracker)
        {
            double d0 = entityplayer.locX - (double)(d / 32);
            double d1 = entityplayer.locZ - (double)(f / 32);
            if(d0 >= (double)(-b) && d0 <= (double)b && d1 >= (double)(-b) && d1 <= (double)b)
            {
                if(!trackedPlayers.contains(entityplayer))
                {
                    trackedPlayers.add(entityplayer);
                    entityplayer.netServerHandler.sendPacket(b());
                    if(isMoving)
                        entityplayer.netServerHandler.sendPacket(new Packet28EntityVelocity(tracker.id, tracker.motX, tracker.motY, tracker.motZ));
                    ItemStack aitemstack[] = tracker.getEquipment();
                    if(aitemstack != null)
                    {
                        for(int i = 0; i < aitemstack.length; i++)
                            entityplayer.netServerHandler.sendPacket(new Packet5EntityEquipment(tracker.id, i, aitemstack[i]));

                    }
                    if(tracker instanceof EntityHuman)
                    {
                        EntityHuman entityhuman = (EntityHuman)tracker;
                        if(entityhuman.isSleeping())
                            entityplayer.netServerHandler.sendPacket(new Packet17(tracker, 0, MathHelper.floor(tracker.locX), MathHelper.floor(tracker.locY), MathHelper.floor(tracker.locZ)));
                    }
                    if(tracker instanceof EntityLiving)
                    {
                        EntityLiving entityliving = (EntityLiving)tracker;
                        MobEffect mobeffect;
                        for(Iterator iterator = entityliving.getEffects().iterator(); iterator.hasNext(); entityplayer.netServerHandler.sendPacket(new Packet41MobEffect(tracker.id, mobeffect)))
                            mobeffect = (MobEffect)iterator.next();

                    }
                }
            } else
            if(trackedPlayers.contains(entityplayer))
            {
                trackedPlayers.remove(entityplayer);
                entityplayer.netServerHandler.sendPacket(new Packet29DestroyEntity(tracker.id));
            }
        }
    }

    public void scanPlayers(List list)
    {
        for(int i = 0; i < list.size(); i++)
            b((EntityPlayer)list.get(i));

    }

    private Packet b()
    {
        if(tracker instanceof EntityItem)
        {
            EntityItem entityitem = (EntityItem)tracker;
            Packet21PickupSpawn packet21pickupspawn = new Packet21PickupSpawn(entityitem);
            entityitem.locX = (double)packet21pickupspawn.b / 32D;
            entityitem.locY = (double)packet21pickupspawn.c / 32D;
            entityitem.locZ = (double)packet21pickupspawn.d / 32D;
            return packet21pickupspawn;
        }
        if(tracker instanceof EntityPlayer)
        {
            if(((EntityHuman)tracker).name.length() > 16)
                ((EntityHuman)tracker).name = ((EntityHuman)tracker).name.substring(0, 16);
            return new Packet20NamedEntitySpawn((EntityHuman)tracker);
        }
        if(tracker instanceof EntityMinecart)
        {
            EntityMinecart entityminecart = (EntityMinecart)tracker;
            if(entityminecart.type == 0)
                return new Packet23VehicleSpawn(tracker, 10);
            if(entityminecart.type == 1)
                return new Packet23VehicleSpawn(tracker, 11);
            if(entityminecart.type == 2)
                return new Packet23VehicleSpawn(tracker, 12);
        }
        if(tracker instanceof EntityBoat)
            return new Packet23VehicleSpawn(tracker, 1);
        if(tracker instanceof IAnimal)
            return new Packet24MobSpawn((EntityLiving)tracker);
        if(tracker instanceof EntityFishingHook)
            return new Packet23VehicleSpawn(tracker, 90);
        if(tracker instanceof EntityArrow)
        {
            Entity entity = ((EntityArrow)tracker).shooter;
            return new Packet23VehicleSpawn(tracker, 60, entity == null ? tracker.id : entity.id);
        }
        if(tracker instanceof EntitySnowball)
            return new Packet23VehicleSpawn(tracker, 61);
        if(tracker instanceof EntityFireball)
        {
            EntityFireball entityfireball = (EntityFireball)tracker;
            int shooter = ((EntityFireball)tracker).shooter == null ? 1 : ((EntityFireball)tracker).shooter.id;
            Packet23VehicleSpawn packet23vehiclespawn = new Packet23VehicleSpawn(tracker, 63, shooter);
            packet23vehiclespawn.e = (int)(entityfireball.dirX * 8000D);
            packet23vehiclespawn.f = (int)(entityfireball.dirY * 8000D);
            packet23vehiclespawn.g = (int)(entityfireball.dirZ * 8000D);
            return packet23vehiclespawn;
        }
        if(tracker instanceof EntityEgg)
            return new Packet23VehicleSpawn(tracker, 62);
        if(tracker instanceof EntityTNTPrimed)
            return new Packet23VehicleSpawn(tracker, 50);
        if(tracker instanceof EntityFallingSand)
        {
            EntityFallingSand entityfallingsand = (EntityFallingSand)tracker;
            if(entityfallingsand.a == Block.SAND.id)
                return new Packet23VehicleSpawn(tracker, 70);
            if(entityfallingsand.a == Block.GRAVEL.id)
                return new Packet23VehicleSpawn(tracker, 71);
        }
        if(tracker instanceof EntityPainting)
            return new Packet25EntityPainting((EntityPainting)tracker);
        if(tracker instanceof EntityExperienceOrb)
            return new Packet26AddExpOrb((EntityExperienceOrb)tracker);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Don't know how to add ").append(tracker.getClass()).append("!").toString());
    }

    public void c(EntityPlayer entityplayer)
    {
        if(trackedPlayers.contains(entityplayer))
        {
            trackedPlayers.remove(entityplayer);
            entityplayer.netServerHandler.sendPacket(new Packet29DestroyEntity(tracker.id));
        }
    }

    public Entity tracker;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public double i;
    public double j;
    public double k;
    public int l;
    private double o;
    private double p;
    private double q;
    private boolean r;
    private boolean isMoving;
    private int t;
    public boolean m;
    public Set trackedPlayers;
}
