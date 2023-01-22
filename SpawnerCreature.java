// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpawnerCreature.java

package net.minecraft.server;

import java.lang.reflect.Constructor;
import java.util.*;
import org.bukkit.event.entity.CreatureSpawnEvent;

// Referenced classes of package net.minecraft.server:
//            ChunkPosition, EntityHuman, ChunkCoordIntPair, BiomeMeta, 
//            World, EntityLiving, EntitySpider, EntitySkeleton, 
//            EntitySheep, Pathfinder, ChunkCoordinates, EntityZombie, 
//            MathHelper, EnumCreatureType, WorldChunkManager, BiomeBase, 
//            WeightedRandom, Material, PathEntity, PathPoint, 
//            BlockBed

public final class SpawnerCreature
{

    public SpawnerCreature()
    {
    }

    protected static ChunkPosition a(World world, int i, int j)
    {
        int k = i + world.random.nextInt(16);
        Random random = world.random;
        world.getClass();
        int l = random.nextInt(128);
        int i1 = j + world.random.nextInt(16);
        return new ChunkPosition(k, l, i1);
    }

    public static final int spawnEntities(World world, boolean flag, boolean flag1)
    {
        int i;
        int j;
        ChunkCoordinates chunkcoordinates;
        EnumCreatureType aenumcreaturetype[];
        int j1;
        if(!flag && !flag1)
            return 0;
        b.clear();
        for(i = 0; i < world.players.size(); i++)
        {
            EntityHuman entityhuman = (EntityHuman)world.players.get(i);
            int k = MathHelper.floor(entityhuman.locX / 16D);
            j = MathHelper.floor(entityhuman.locZ / 16D);
            byte b0 = 8;
            for(int l = -b0; l <= b0; l++)
            {
                for(int i1 = -b0; i1 <= b0; i1++)
                    b.add(new ChunkCoordIntPair(l + k, i1 + j));

            }

        }

        i = 0;
        chunkcoordinates = world.getSpawn();
        aenumcreaturetype = EnumCreatureType.values();
        j = aenumcreaturetype.length;
        j1 = 0;
_L10:
        if(j1 >= j) goto _L2; else goto _L1
_L1:
        EnumCreatureType enumcreaturetype;
        Iterator iterator;
        enumcreaturetype = aenumcreaturetype[j1];
        if(enumcreaturetype.d() && !flag1 || !enumcreaturetype.d() && !flag || world.a(enumcreaturetype.a()) > (enumcreaturetype.b() * b.size()) / 256)
            continue; /* Loop/switch isn't completed */
        iterator = b.iterator();
_L4:
        BiomeMeta biomemeta;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        do
        {
            ChunkCoordIntPair chunkcoordintpair;
            List list;
            do
            {
                if(!iterator.hasNext())
                    continue; /* Loop/switch isn't completed */
                chunkcoordintpair = (ChunkCoordIntPair)iterator.next();
                BiomeBase biomebase = world.getWorldChunkManager().a(chunkcoordintpair);
                list = biomebase.a(enumcreaturetype);
            } while(list == null || list.isEmpty());
            biomemeta = (BiomeMeta)WeightedRandom.a(world.random, list);
            ChunkPosition chunkposition = a(world, chunkcoordintpair.x * 16, chunkcoordintpair.z * 16);
            k1 = chunkposition.x;
            l1 = chunkposition.y;
            i2 = chunkposition.z;
        } while(world.e(k1, l1, i2) || world.getMaterial(k1, l1, i2) != enumcreaturetype.c());
        j2 = 0;
        k2 = 0;
_L9:
        if(k2 >= 3) goto _L4; else goto _L3
_L3:
        int l2;
        int i3;
        int j3;
        byte b1;
        int k3;
        l2 = k1;
        i3 = l1;
        j3 = i2;
        b1 = 6;
        k3 = 0;
_L8:
        if(k3 >= 4) goto _L6; else goto _L5
_L5:
        EntityLiving entityliving;
        l2 += world.random.nextInt(b1) - world.random.nextInt(b1);
        i3 += world.random.nextInt(1) - world.random.nextInt(1);
        j3 += world.random.nextInt(b1) - world.random.nextInt(b1);
        if(!a(enumcreaturetype, world, l2, i3, j3))
            continue; /* Loop/switch isn't completed */
        float f = (float)l2 + 0.5F;
        float f1 = i3;
        float f2 = (float)j3 + 0.5F;
        if(world.a(f, f1, f2, 24D) != null)
            continue; /* Loop/switch isn't completed */
        float f3 = f - (float)chunkcoordinates.x;
        float f4 = f1 - (float)chunkcoordinates.y;
        float f5 = f2 - (float)chunkcoordinates.z;
        float f6 = f3 * f3 + f4 * f4 + f5 * f5;
        if(f6 < 576F)
            continue; /* Loop/switch isn't completed */
        try
        {
            entityliving = (EntityLiving)biomemeta.a.getConstructor(new Class[] {
                net/minecraft/server/World
            }).newInstance(new Object[] {
                world
            });
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return i;
        }
        entityliving.setPositionRotation(f, f1, f2, world.random.nextFloat() * 360F, 0.0F);
        if(!entityliving.d())
            break; /* Loop/switch isn't completed */
        j2++;
        world.addEntity(entityliving, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.NATURAL);
        a(entityliving, world, f, f1, f2);
        if(j2 < entityliving.m()) goto _L7; else goto _L4
_L7:
        i += j2;
        k3++;
          goto _L8
_L6:
        k2++;
          goto _L9
        j1++;
          goto _L10
_L2:
        return i;
    }

    private static boolean a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
    {
        return enumcreaturetype.c() != Material.WATER ? world.e(i, j - 1, k) && !world.e(i, j, k) && !world.getMaterial(i, j, k).isLiquid() && !world.e(i, j + 1, k) : world.getMaterial(i, j, k).isLiquid() && !world.e(i, j + 1, k);
    }

    private static void a(EntityLiving entityliving, World world, float f, float f1, float f2)
    {
        if((entityliving instanceof EntitySpider) && world.random.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);
            entityskeleton.setPositionRotation(f, f1, f2, entityliving.yaw, 0.0F);
            world.addEntity(entityskeleton, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.NATURAL);
            entityskeleton.mount(entityliving);
        } else
        if(entityliving instanceof EntitySheep)
            ((EntitySheep)entityliving).setColor(EntitySheep.a(world.random));
    }

    public static boolean a(World world, List list)
    {
        boolean flag = false;
        Pathfinder pathfinder = new Pathfinder(world);
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            EntityHuman entityhuman = (EntityHuman)iterator.next();
            if(!entityhuman.fauxSleeping)
            {
                Class aclass[] = a;
                if(aclass != null && aclass.length != 0)
                {
                    boolean flag1 = false;
                    int i = 0;
                    while(i < 20 && !flag1) 
                    {
                        int j = (MathHelper.floor(entityhuman.locX) + world.random.nextInt(32)) - world.random.nextInt(32);
                        int k = (MathHelper.floor(entityhuman.locZ) + world.random.nextInt(32)) - world.random.nextInt(32);
                        int l = (MathHelper.floor(entityhuman.locY) + world.random.nextInt(16)) - world.random.nextInt(16);
                        if(l < 1)
                        {
                            l = 1;
                        } else
                        {
                            world.getClass();
                            if(l > 128)
                            {
                                world.getClass();
                                l = 128;
                            }
                        }
                        int i1 = world.random.nextInt(aclass.length);
                        int j1;
                        for(j1 = l; j1 > 2 && !world.e(j, j1 - 1, k); j1--);
                        do
                        {
                            if(a(EnumCreatureType.MONSTER, world, j, j1, k) || j1 >= l + 16)
                                break;
                            world.getClass();
                            if(j1 >= 128)
                                break;
                            j1++;
                        } while(true);
                        if(j1 < l + 16)
                        {
                            world.getClass();
                            if(j1 < 128)
                            {
                                float f = (float)j + 0.5F;
                                float f1 = j1;
                                float f2 = (float)k + 0.5F;
                                EntityLiving entityliving;
                                try
                                {
                                    entityliving = (EntityLiving)aclass[i1].getConstructor(new Class[] {
                                        net/minecraft/server/World
                                    }).newInstance(new Object[] {
                                        world
                                    });
                                }
                                catch(Exception exception)
                                {
                                    exception.printStackTrace();
                                    return flag;
                                }
                                entityliving.setPositionRotation(f, f1, f2, world.random.nextFloat() * 360F, 0.0F);
                                if(entityliving.d())
                                {
                                    PathEntity pathentity = pathfinder.a(entityliving, entityhuman, 32F);
                                    if(pathentity != null && pathentity.a > 1)
                                    {
                                        PathPoint pathpoint = pathentity.c();
                                        if(Math.abs((double)pathpoint.a - entityhuman.locX) < 1.5D && Math.abs((double)pathpoint.c - entityhuman.locZ) < 1.5D && Math.abs((double)pathpoint.b - entityhuman.locY) < 1.5D)
                                        {
                                            ChunkCoordinates chunkcoordinates = BlockBed.f(world, MathHelper.floor(entityhuman.locX), MathHelper.floor(entityhuman.locY), MathHelper.floor(entityhuman.locZ), 1);
                                            if(chunkcoordinates == null)
                                                chunkcoordinates = new ChunkCoordinates(j, j1 + 1, k);
                                            entityliving.setPositionRotation((float)chunkcoordinates.x + 0.5F, chunkcoordinates.y, (float)chunkcoordinates.z + 0.5F, 0.0F, 0.0F);
                                            world.addEntity(entityliving, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.BED);
                                            a(entityliving, world, (float)chunkcoordinates.x + 0.5F, chunkcoordinates.y, (float)chunkcoordinates.z + 0.5F);
                                            entityhuman.a(true, false, false);
                                            entityliving.Z();
                                            flag = true;
                                            flag1 = true;
                                        }
                                    }
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        } while(true);
        return flag;
    }

    public static void a(World world, BiomeBase biomebase, int i, int j, int k, int l, Random random)
    {
        List list = biomebase.a(EnumCreatureType.CREATURE);
        if(!list.isEmpty())
            while(random.nextFloat() < biomebase.d()) 
            {
                BiomeMeta biomemeta = (BiomeMeta)WeightedRandom.a(world.random, list);
                int i1 = biomemeta.b + random.nextInt((1 + biomemeta.c) - biomemeta.b);
                int j1 = i + random.nextInt(k);
                int k1 = j + random.nextInt(l);
                int l1 = j1;
                int i2 = k1;
                int j2 = 0;
                while(j2 < i1) 
                {
                    boolean flag = false;
                    for(int k2 = 0; !flag && k2 < 4; k2++)
                    {
                        int l2 = world.f(j1, k1);
                        if(a(EnumCreatureType.CREATURE, world, j1, l2, k1))
                        {
                            float f = (float)j1 + 0.5F;
                            float f1 = l2;
                            float f2 = (float)k1 + 0.5F;
                            EntityLiving entityliving;
                            try
                            {
                                entityliving = (EntityLiving)biomemeta.a.getConstructor(new Class[] {
                                    net/minecraft/server/World
                                }).newInstance(new Object[] {
                                    world
                                });
                            }
                            catch(Exception exception)
                            {
                                exception.printStackTrace();
                                continue;
                            }
                            entityliving.setPositionRotation(f, f1, f2, random.nextFloat() * 360F, 0.0F);
                            world.addEntity(entityliving, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.NATURAL);
                            a(entityliving, world, f, f1, f2);
                            flag = true;
                        }
                        j1 += random.nextInt(5) - random.nextInt(5);
                        for(k1 += random.nextInt(5) - random.nextInt(5); j1 < i || j1 >= i + k || k1 < j || k1 >= j + k; k1 = (i2 + random.nextInt(5)) - random.nextInt(5))
                            j1 = (l1 + random.nextInt(5)) - random.nextInt(5);

                    }

                    j2++;
                }
            }
    }

    private static Set b = new HashSet();
    protected static final Class a[] = {
        net/minecraft/server/EntitySpider, net/minecraft/server/EntityZombie, net/minecraft/server/EntitySkeleton
    };

}
