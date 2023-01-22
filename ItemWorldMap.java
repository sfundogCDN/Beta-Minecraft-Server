// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemWorldMap.java

package net.minecraft.server;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            ItemWorldMapBase, WorldMap, WorldServer, EntityHuman, 
//            Packet131, ItemStack, World, WorldData, 
//            WorldProvider, Entity, MathHelper, Block, 
//            Chunk, Material, MaterialMapColor, Item, 
//            Packet

public class ItemWorldMap extends ItemWorldMapBase
{

    protected ItemWorldMap(int i)
    {
        super(i);
        c(1);
    }

    public WorldMap a(ItemStack itemstack, World world)
    {
        WorldMap worldmap = (WorldMap)world.a(net/minecraft/server/WorldMap, (new StringBuilder()).append("map_").append(itemstack.getData()).toString());
        if(worldmap == null)
        {
            itemstack.b(world.b("map"));
            String s = (new StringBuilder()).append("map_").append(itemstack.getData()).toString();
            worldmap = new WorldMap(s);
            worldmap.b = world.p().c();
            worldmap.c = world.p().e();
            worldmap.e = 3;
            worldmap.map = (byte)world.worldProvider.dimension;
            worldmap.a();
            world.a(s, worldmap);
            MapInitializeEvent event = new MapInitializeEvent(worldmap.mapView);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
        return worldmap;
    }

    public void a(World world, Entity entity, WorldMap worldmap)
    {
        if(((WorldServer)world).dimension == worldmap.map)
        {
            short short1 = 128;
            short short2 = 128;
            int i = 1 << worldmap.e;
            int j = worldmap.b;
            int k = worldmap.c;
            int l = MathHelper.floor(entity.locX - (double)j) / i + short1 / 2;
            int i1 = MathHelper.floor(entity.locZ - (double)k) / i + short2 / 2;
            int j1 = 128 / i;
            if(world.worldProvider.e)
                j1 /= 2;
            worldmap.g++;
            for(int k1 = (l - j1) + 1; k1 < l + j1; k1++)
            {
                if((k1 & 0xf) != (worldmap.g & 0xf))
                    continue;
                int l1 = 255;
                int i2 = 0;
                double d0 = 0.0D;
                for(int j2 = i1 - j1 - 1; j2 < i1 + j1; j2++)
                {
                    if(k1 < 0 || j2 < -1 || k1 >= short1 || j2 >= short2)
                        continue;
                    int k2 = k1 - l;
                    int l2 = j2 - i1;
                    boolean flag = k2 * k2 + l2 * l2 > (j1 - 2) * (j1 - 2);
                    int i3 = ((j / i + k1) - short1 / 2) * i;
                    int j3 = ((k / i + j2) - short2 / 2) * i;
                    byte b0 = 0;
                    byte b1 = 0;
                    byte b2 = 0;
                    int aint[] = new int[256];
                    Chunk chunk = world.getChunkAtWorldCoords(i3, j3);
                    int k3 = i3 & 0xf;
                    int l3 = j3 & 0xf;
                    int i4 = 0;
                    double d1 = 0.0D;
                    int j4;
                    int l4;
                    int i5;
                    if(world.worldProvider.e)
                    {
                        l4 = i3 + j3 * 0x389bf;
                        l4 = l4 * l4 * 0x1dd6751 + l4 * 11;
                        if((l4 >> 20 & 1) == 0)
                            aint[Block.DIRT.id] += 10;
                        else
                            aint[Block.STONE.id] += 10;
                        d1 = 100D;
                    } else
                    {
                        for(l4 = 0; l4 < i; l4++)
                            for(j4 = 0; j4 < i; j4++)
                            {
                                int k4 = chunk.b(l4 + k3, j4 + l3) + 1;
                                int j5 = 0;
                                if(k4 > 1)
                                {
                                    boolean flag1 = false;
                                    do
                                    {
                                        flag1 = true;
                                        j5 = chunk.getTypeId(l4 + k3, k4 - 1, j4 + l3);
                                        if(j5 == 0)
                                            flag1 = false;
                                        else
                                        if(k4 > 0 && j5 > 0 && Block.byId[j5].material.D == MaterialMapColor.b)
                                            flag1 = false;
                                        if(flag1)
                                            continue;
                                        if(--k4 <= 0)
                                            break;
                                        j5 = chunk.getTypeId(l4 + k3, k4 - 1, j4 + l3);
                                    } while(!flag1);
                                    if(j5 != 0 && Block.byId[j5].material.isLiquid())
                                    {
                                        i5 = k4 - 1;
                                        boolean flag2 = false;
                                        int k5;
                                        do
                                        {
                                            k5 = chunk.getTypeId(l4 + k3, i5--, j4 + l3);
                                            i4++;
                                        } while(i5 > 0 && k5 != 0 && Block.byId[k5].material.isLiquid());
                                    }
                                }
                                d1 += (double)k4 / (double)(i * i);
                                aint[j5]++;
                            }


                    }
                    i4 /= i * i;
                    int l5 = b0 / (i * i);
                    l5 = b1 / (i * i);
                    l5 = b2 / (i * i);
                    l4 = 0;
                    j4 = 0;
                    for(int k4 = 0; k4 < 256; k4++)
                        if(aint[k4] > l4)
                        {
                            j4 = k4;
                            l4 = aint[k4];
                        }

                    double d2 = ((d1 - d0) * 4D) / (double)(i + 4) + ((double)(k1 + j2 & 1) - 0.5D) * 0.40000000000000002D;
                    byte b3 = 1;
                    if(d2 > 0.59999999999999998D)
                        b3 = 2;
                    if(d2 < -0.59999999999999998D)
                        b3 = 0;
                    i5 = 0;
                    if(j4 > 0)
                    {
                        MaterialMapColor materialmapcolor = Block.byId[j4].material.D;
                        if(materialmapcolor == MaterialMapColor.n)
                        {
                            d2 = (double)i4 * 0.10000000000000001D + (double)(k1 + j2 & 1) * 0.20000000000000001D;
                            b3 = 1;
                            if(d2 < 0.5D)
                                b3 = 2;
                            if(d2 > 0.90000000000000002D)
                                b3 = 0;
                        }
                        i5 = materialmapcolor.q;
                    }
                    d0 = d1;
                    if(j2 < 0 || k2 * k2 + l2 * l2 >= j1 * j1 || flag && (k1 + j2 & 1) == 0)
                        continue;
                    byte b4 = worldmap.f[k1 + j2 * short1];
                    byte b5 = (byte)(i5 * 4 + b3);
                    if(b4 == b5)
                        continue;
                    if(l1 > j2)
                        l1 = j2;
                    if(i2 < j2)
                        i2 = j2;
                    worldmap.f[k1 + j2 * short1] = b5;
                }

                if(l1 <= i2)
                    worldmap.a(k1, l1, i2);
            }

        }
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
        if(!world.isStatic)
        {
            WorldMap worldmap = a(itemstack, world);
            if(entity instanceof EntityHuman)
            {
                EntityHuman entityhuman = (EntityHuman)entity;
                worldmap.a(entityhuman, itemstack);
            }
            if(flag)
                a(world, entity, worldmap);
        }
    }

    public void d(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        itemstack.b(world.b("map"));
        String s = (new StringBuilder()).append("map_").append(itemstack.getData()).toString();
        WorldMap worldmap = new WorldMap(s);
        world.a(s, worldmap);
        worldmap.b = MathHelper.floor(entityhuman.locX);
        worldmap.c = MathHelper.floor(entityhuman.locZ);
        worldmap.e = 3;
        worldmap.map = (byte)world.worldProvider.dimension;
        worldmap.a();
    }

    public Packet c(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        byte abyte[] = a(itemstack, world).a(itemstack, world, entityhuman);
        return abyte != null ? new Packet131((short)Item.MAP.id, (short)itemstack.getData(), abyte) : null;
    }
}
