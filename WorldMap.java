// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldMap.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.map.CraftMapView;

// Referenced classes of package net.minecraft.server:
//            WorldMapBase, WorldMapHumanTracker, WorldMapOrienter, NBTTagCompound, 
//            WorldServer, EntityHuman, InventoryPlayer, ItemStack, 
//            World

public class WorldMap extends WorldMapBase
{

    public WorldMap(String s)
    {
        super(s);
        f = new byte[16384];
        h = new ArrayList();
        j = new HashMap();
        i = new ArrayList();
        uniqueId = null;
        server = (CraftServer)Bukkit.getServer();
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        byte dimension = nbttagcompound.c("dimension");
        if(dimension >= 10)
        {
            long least = nbttagcompound.getLong("UUIDLeast");
            long most = nbttagcompound.getLong("UUIDMost");
            if(least != 0L && most != 0L)
            {
                uniqueId = new UUID(most, least);
                CraftWorld world = (CraftWorld)server.getWorld(uniqueId);
                if(world == null)
                    dimension = 127;
                else
                    dimension = (byte)world.getHandle().dimension;
            }
        }
        map = dimension;
        b = nbttagcompound.e("xCenter");
        c = nbttagcompound.e("zCenter");
        e = nbttagcompound.c("scale");
        if(e < 0)
            e = 0;
        if(e > 4)
            e = 4;
        short short1 = nbttagcompound.d("width");
        short short2 = nbttagcompound.d("height");
        if(short1 == 128 && short2 == 128)
        {
            f = nbttagcompound.j("colors");
        } else
        {
            byte abyte[] = nbttagcompound.j("colors");
            f = new byte[16384];
            int i = (128 - short1) / 2;
            int j = (128 - short2) / 2;
            for(int k = 0; k < short2; k++)
            {
                int l = k + j;
                if(l < 0 && l >= 128)
                    continue;
                for(int i1 = 0; i1 < short1; i1++)
                {
                    int j1 = i1 + i;
                    if(j1 >= 0 || j1 < 128)
                        f[j1 + l * 128] = abyte[i1 + k * short1];
                }

            }

        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
label0:
        {
label1:
            {
                if(map < 10)
                    break label0;
                if(uniqueId != null)
                    break label1;
                Iterator i$ = server.getWorlds().iterator();
                CraftWorld cWorld;
                do
                {
                    if(!i$.hasNext())
                        break label1;
                    World world = (World)i$.next();
                    cWorld = (CraftWorld)world;
                } while(cWorld.getHandle().dimension != map);
                uniqueId = cWorld.getUID();
            }
            if(uniqueId != null)
            {
                nbttagcompound.setLong("UUIDLeast", uniqueId.getLeastSignificantBits());
                nbttagcompound.setLong("UUIDMost", uniqueId.getMostSignificantBits());
            }
        }
        nbttagcompound.a("dimension", map);
        nbttagcompound.a("xCenter", b);
        nbttagcompound.a("zCenter", c);
        nbttagcompound.a("scale", e);
        nbttagcompound.a("width", (short)128);
        nbttagcompound.a("height", (short)128);
        nbttagcompound.a("colors", f);
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack)
    {
        if(!this.j.containsKey(entityhuman))
        {
            WorldMapHumanTracker worldmaphumantracker = new WorldMapHumanTracker(this, entityhuman);
            this.j.put(entityhuman, worldmaphumantracker);
            h.add(worldmaphumantracker);
        }
        this.i.clear();
        for(int i = 0; i < h.size(); i++)
        {
            WorldMapHumanTracker worldmaphumantracker1 = (WorldMapHumanTracker)h.get(i);
            if(!worldmaphumantracker1.trackee.dead && worldmaphumantracker1.trackee.inventory.c(itemstack))
            {
                float f = (float)(worldmaphumantracker1.trackee.locX - (double)b) / (float)(1 << e);
                float f1 = (float)(worldmaphumantracker1.trackee.locZ - (double)c) / (float)(1 << e);
                byte b0 = 64;
                byte b1 = 64;
                if(f < (float)(-b0) || f1 < (float)(-b1) || f > (float)b0 || f1 > (float)b1)
                    continue;
                byte b2 = 0;
                byte b3 = (byte)(int)((double)(f * 2.0F) + 0.5D);
                byte b4 = (byte)(int)((double)(f1 * 2.0F) + 0.5D);
                byte b5 = (byte)(int)((double)((worldmaphumantracker1.trackee.yaw * 16F) / 360F) + 0.5D);
                if(map < 0)
                {
                    int j = g / 10;
                    b5 = (byte)(j * j * 0x209a771 + j * 121 >> 15 & 0xf);
                }
                if(worldmaphumantracker1.trackee.dimension == map)
                    this.i.add(new WorldMapOrienter(this, b2, b3, b4, b5));
            } else
            {
                this.j.remove(worldmaphumantracker1.trackee);
                h.remove(worldmaphumantracker1);
            }
        }

    }

    public byte[] a(ItemStack itemstack, net.minecraft.server.World world, EntityHuman entityhuman)
    {
        WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker)j.get(entityhuman);
        if(worldmaphumantracker == null)
        {
            return null;
        } else
        {
            byte abyte[] = worldmaphumantracker.a(itemstack);
            return abyte;
        }
    }

    public void a(int i, int j, int k)
    {
        super.a();
        for(int l = 0; l < h.size(); l++)
        {
            WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker)h.get(l);
            if(worldmaphumantracker.b[i] < 0 || worldmaphumantracker.b[i] > j)
                worldmaphumantracker.b[i] = j;
            if(worldmaphumantracker.c[i] < 0 || worldmaphumantracker.c[i] < k)
                worldmaphumantracker.c[i] = k;
        }

    }

    public int b;
    public int c;
    public byte map;
    public byte e;
    public byte f[];
    public int g;
    public List h;
    private Map j;
    public List i;
    public final CraftMapView mapView = new CraftMapView(this);
    private CraftServer server;
    private UUID uniqueId;
}
