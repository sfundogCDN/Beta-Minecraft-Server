// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityTracker.java

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            EntityList, EntityPlayer, EntityTrackerEntry, EntityFishingHook, 
//            EntityArrow, EntityFireball, EntitySnowball, EntityEgg, 
//            EntityItem, EntityMinecart, EntityBoat, EntitySquid, 
//            IAnimal, EntityTNTPrimed, EntityFallingSand, EntityPainting, 
//            EntityExperienceOrb, MinecraftServer, ServerConfigurationManager, Entity, 
//            WorldServer, Packet

public class EntityTracker
{

    public EntityTracker(MinecraftServer minecraftserver, int i)
    {
        a = new HashSet();
        trackedEntities = new EntityList();
        c = minecraftserver;
        e = i;
        d = minecraftserver.serverConfigurationManager.a();
    }

    public synchronized void track(Entity entity)
    {
        if(entity instanceof EntityPlayer)
        {
            addEntity(entity, 512, 2);
            EntityPlayer entityplayer = (EntityPlayer)entity;
            Iterator iterator = a.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
                if(entitytrackerentry.tracker != entityplayer)
                    entitytrackerentry.b(entityplayer);
            } while(true);
        } else
        if(entity instanceof EntityFishingHook)
            addEntity(entity, 64, 5, true);
        else
        if(entity instanceof EntityArrow)
            addEntity(entity, 64, 20, false);
        else
        if(entity instanceof EntityFireball)
            addEntity(entity, 64, 10, false);
        else
        if(entity instanceof EntitySnowball)
            addEntity(entity, 64, 10, true);
        else
        if(entity instanceof EntityEgg)
            addEntity(entity, 64, 10, true);
        else
        if(entity instanceof EntityItem)
            addEntity(entity, 64, 20, true);
        else
        if(entity instanceof EntityMinecart)
            addEntity(entity, 160, 5, true);
        else
        if(entity instanceof EntityBoat)
            addEntity(entity, 160, 5, true);
        else
        if(entity instanceof EntitySquid)
            addEntity(entity, 160, 3, true);
        else
        if(entity instanceof IAnimal)
            addEntity(entity, 160, 3, true);
        else
        if(entity instanceof EntityTNTPrimed)
            addEntity(entity, 160, 10, true);
        else
        if(entity instanceof EntityFallingSand)
            addEntity(entity, 160, 20, true);
        else
        if(entity instanceof EntityPainting)
            addEntity(entity, 160, 0x7fffffff, false);
        else
        if(entity instanceof EntityExperienceOrb)
            addEntity(entity, 160, 20, true);
    }

    public void addEntity(Entity entity, int i, int j)
    {
        addEntity(entity, i, j, false);
    }

    public synchronized void addEntity(Entity entity, int i, int j, boolean flag)
    {
        if(i > d)
            i = d;
        if(!trackedEntities.b(entity.id))
        {
            EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, j, flag);
            a.add(entitytrackerentry);
            trackedEntities.a(entity.id, entitytrackerentry);
            entitytrackerentry.scanPlayers(c.getWorldServer(e).players);
        }
    }

    public synchronized void untrackEntity(Entity entity)
    {
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            EntityTrackerEntry entitytrackerentry;
            for(Iterator iterator = a.iterator(); iterator.hasNext(); entitytrackerentry.a(entityplayer))
                entitytrackerentry = (EntityTrackerEntry)iterator.next();

        }
        EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)trackedEntities.d(entity.id);
        if(entitytrackerentry1 != null)
        {
            a.remove(entitytrackerentry1);
            entitytrackerentry1.a();
        }
    }

    public synchronized void updatePlayers()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = a.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
            entitytrackerentry.track(c.getWorldServer(e).players);
            if(entitytrackerentry.m && (entitytrackerentry.tracker instanceof EntityPlayer))
                arraylist.add((EntityPlayer)entitytrackerentry.tracker);
        } while(true);
label0:
        for(int i = 0; i < arraylist.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)arraylist.get(i);
            Iterator iterator1 = a.iterator();
            do
            {
                if(!iterator1.hasNext())
                    continue label0;
                EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)iterator1.next();
                if(entitytrackerentry1.tracker != entityplayer)
                    entitytrackerentry1.b(entityplayer);
            } while(true);
        }

    }

    public synchronized void a(Entity entity, Packet packet)
    {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)trackedEntities.a(entity.id);
        if(entitytrackerentry != null)
            entitytrackerentry.a(packet);
    }

    public synchronized void sendPacketToEntity(Entity entity, Packet packet)
    {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)trackedEntities.a(entity.id);
        if(entitytrackerentry != null)
            entitytrackerentry.b(packet);
    }

    public synchronized void untrackPlayer(EntityPlayer entityplayer)
    {
        EntityTrackerEntry entitytrackerentry;
        for(Iterator iterator = a.iterator(); iterator.hasNext(); entitytrackerentry.c(entityplayer))
            entitytrackerentry = (EntityTrackerEntry)iterator.next();

    }

    private Set a;
    private EntityList trackedEntities;
    private MinecraftServer c;
    private int d;
    private int e;
}
