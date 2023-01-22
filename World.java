// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   World.java

package net.minecraft.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            WorldServer, WorldMapCollection, WorldData, ChunkProviderLoadOrGenerate, 
//            IWorldAccess, EntityHuman, EntityLiving, EntityPlayer, 
//            EntityAnimal, EntityWaterAnimal, EntityMonster, EntityGhast, 
//            EntitySlime, EntityItem, Entity, NextTickListEntry, 
//            TileEntity, Explosion, IProgressUpdate, ChunkCoordIntPair, 
//            EntityWeatherStorm, ChunkCache, Pathfinder, ChunkCoordinates, 
//            IBlockAccess, WorldProvider, IDataManager, WorldChunkManager, 
//            ChunkPosition, IChunkProvider, Chunk, Material, 
//            Block, EnumSkyBlock, Vec3D, MathHelper, 
//            AxisAlignedBB, BlockFire, BlockFluids, SpawnerCreature, 
//            ServerConfigurationManager, BiomeBase, WorldSettings, MovingObjectPosition, 
//            PathEntity, WorldMapBase

public class World
    implements IBlockAccess
{

    public WorldChunkManager getWorldChunkManager()
    {
        return worldProvider.b;
    }

    private boolean canSpawn(int x, int z)
    {
        if(generator != null)
            return generator.canSpawn(getWorld(), x, z);
        else
            return worldProvider.canSpawn(x, z);
    }

    public CraftWorld getWorld()
    {
        return world;
    }

    public CraftServer getServer()
    {
        return (CraftServer)Bukkit.getServer();
    }

    public World(IDataManager idatamanager, String s, WorldSettings worldsettings, WorldProvider worldprovider, ChunkGenerator gen, org.bukkit.Environment env)
    {
        f = false;
        entityList = new ArrayList();
        M = new ArrayList();
        N = new TreeSet();
        O = new HashSet();
        h = new ArrayList();
        P = new ArrayList();
        Q = new ArrayList();
        players = new ArrayList();
        j = new ArrayList();
        R = 0xffffffL;
        k = 0;
        l = (new Random()).nextInt();
        r = 0;
        this.s = 0;
        suppressPhysics = false;
        S = System.currentTimeMillis();
        u = 40;
        random = new Random();
        x = false;
        z = new ArrayList();
        U = new ArrayList();
        allowMonsters = true;
        allowAnimals = true;
        W = new HashSet();
        keepSpawnInMemory = true;
        lastXAccessed = 0x80000000;
        lastZAccessed = 0x80000000;
        generator = gen;
        world = new CraftWorld((WorldServer)this, gen, env);
        X = random.nextInt(12000);
        H = new int[32768];
        Y = new ArrayList();
        isStatic = false;
        B = idatamanager;
        worldMaps = new WorldMapCollection(idatamanager);
        worldData = idatamanager.c();
        x = worldData == null;
        if(worldprovider != null)
            worldProvider = worldprovider;
        else
        if(worldData != null && worldData.h() == -1)
            worldProvider = WorldProvider.byDimension(-1);
        else
            worldProvider = WorldProvider.byDimension(0);
        boolean flag = false;
        if(worldData == null)
        {
            worldData = new WorldData(worldsettings, s);
            flag = true;
        } else
        {
            worldData.a(s);
        }
        worldProvider.a(this);
        chunkProvider = b();
        if(flag)
            c();
        f();
        x();
        getServer().addWorld(world);
    }

    protected IChunkProvider b()
    {
        IChunkLoader ichunkloader = B.a(worldProvider);
        return new ChunkProviderLoadOrGenerate(this, ichunkloader, worldProvider.getChunkProvider());
    }

    protected void c()
    {
        isLoading = true;
        WorldChunkManager worldchunkmanager = getWorldChunkManager();
        List list = worldchunkmanager.a();
        Random random = new Random(getSeed());
        ChunkPosition chunkposition = worldchunkmanager.a(0, 0, 256, list, random);
        int i = 0;
        byte b0 = 64;
        int j = 0;
        if(generator != null)
        {
            Random rand = new Random(getSeed());
            Location spawn = generator.getFixedSpawnLocation(((WorldServer)this).getWorld(), rand);
            if(spawn != null)
                if(spawn.getWorld() != ((WorldServer)this).getWorld())
                {
                    throw new IllegalStateException((new StringBuilder()).append("Cannot set spawn point for ").append(worldData.name).append(" to be in another world (").append(spawn.getWorld().getName()).append(")").toString());
                } else
                {
                    worldData.setSpawn(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
                    isLoading = false;
                    return;
                }
        }
        if(chunkposition != null)
        {
            i = chunkposition.x;
            j = chunkposition.z;
        } else
        {
            System.out.println("Unable to find spawn biome");
        }
        int k = 0;
        do
        {
            if(canSpawn(i, j))
                break;
            i += random.nextInt(64) - random.nextInt(64);
            j += random.nextInt(64) - random.nextInt(64);
        } while(++k != 1000);
        worldData.setSpawn(i, b0, j);
        isLoading = false;
    }

    public int a(int i, int j)
    {
        int k;
        for(k = 63; !isEmpty(i, k + 1, j); k++);
        return getTypeId(i, k, j);
    }

    public void save(boolean flag, IProgressUpdate iprogressupdate)
    {
        if(chunkProvider.canSave())
        {
            if(iprogressupdate != null)
                iprogressupdate.a("Saving level");
            w();
            if(iprogressupdate != null)
                iprogressupdate.b("Saving chunks");
            chunkProvider.saveChunks(flag, iprogressupdate);
        }
    }

    private void w()
    {
        j();
        B.a(worldData, players);
        worldMaps.a();
    }

    public int getTypeId(int i, int j, int k)
    {
        return i < 0xfe363c80 || k < 0xfe363c80 || i >= 0x1c9c380 || k >= 0x1c9c380 ? 0 : j >= 0 ? j < 128 ? getChunkAt(i >> 4, k >> 4).getTypeId(i & 0xf, j, k & 0xf) : 0 : 0;
    }

    public boolean isEmpty(int i, int j, int k)
    {
        return getTypeId(i, j, k) == 0;
    }

    public boolean isLoaded(int i, int j, int k)
    {
        return j < 0 || j >= 128 ? false : isChunkLoaded(i >> 4, k >> 4);
    }

    public boolean areChunksLoaded(int i, int j, int k, int l)
    {
        return a(i - l, j - l, k - l, i + l, j + l, k + l);
    }

    public boolean a(int i, int j, int k, int l, int i1, int j1)
    {
        if(i1 >= 0 && j < 128)
        {
            i >>= 4;
            j >>= 4;
            k >>= 4;
            l >>= 4;
            i1 >>= 4;
            j1 >>= 4;
            for(int k1 = i; k1 <= l; k1++)
            {
                for(int l1 = k; l1 <= j1; l1++)
                    if(!isChunkLoaded(k1, l1))
                        return false;

            }

            return true;
        } else
        {
            return false;
        }
    }

    private boolean isChunkLoaded(int i, int j)
    {
        return chunkProvider.isChunkLoaded(i, j);
    }

    public Chunk getChunkAtWorldCoords(int i, int j)
    {
        return getChunkAt(i >> 4, j >> 4);
    }

    public Chunk getChunkAt(int i, int j)
    {
        Chunk result = null;
        synchronized(chunkLock)
        {
            if(lastChunkAccessed == null || lastXAccessed != i || lastZAccessed != j)
            {
                lastXAccessed = i;
                lastZAccessed = j;
                lastChunkAccessed = chunkProvider.getOrCreateChunk(i, j);
            }
            result = lastChunkAccessed;
        }
        return result;
    }

    public boolean setRawTypeIdAndData(int i, int j, int k, int l, int i1)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            if(j < 0)
                return false;
            if(j >= 128)
            {
                return false;
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, k >> 4);
                boolean flag = chunk.a(i & 0xf, j, k & 0xf, l, i1);
                p(i, j, k);
                return flag;
            }
        } else
        {
            return false;
        }
    }

    public boolean setRawTypeId(int i, int j, int k, int l)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            if(j < 0)
                return false;
            if(j >= 128)
            {
                return false;
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, k >> 4);
                boolean flag = chunk.a(i & 0xf, j, k & 0xf, l);
                p(i, j, k);
                return flag;
            }
        } else
        {
            return false;
        }
    }

    public Material getMaterial(int i, int j, int k)
    {
        int l = getTypeId(i, j, k);
        return l != 0 ? Block.byId[l].material : Material.AIR;
    }

    public int getData(int i, int j, int k)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            if(j < 0)
                return 0;
            if(j >= 128)
            {
                return 0;
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, k >> 4);
                i &= 0xf;
                k &= 0xf;
                return chunk.getData(i, j, k);
            }
        } else
        {
            return 0;
        }
    }

    public void setData(int i, int j, int k, int l)
    {
        if(setRawData(i, j, k, l))
        {
            int i1 = getTypeId(i, j, k);
            if(Block.t[i1 & 0xff])
                update(i, j, k, i1);
            else
                applyPhysics(i, j, k, i1);
        }
    }

    public boolean setRawData(int i, int j, int k, int l)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            if(j < 0)
                return false;
            if(j >= 128)
            {
                return false;
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, k >> 4);
                i &= 0xf;
                k &= 0xf;
                chunk.b(i, j, k, l);
                return true;
            }
        } else
        {
            return false;
        }
    }

    public boolean setTypeId(int i, int j, int k, int l)
    {
        int old = getTypeId(i, j, k);
        if(setRawTypeId(i, j, k, l))
        {
            update(i, j, k, l != 0 ? l : old);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean setTypeIdAndData(int i, int j, int k, int l, int i1)
    {
        int old = getTypeId(i, j, k);
        if(setRawTypeIdAndData(i, j, k, l, i1))
        {
            update(i, j, k, l != 0 ? l : old);
            return true;
        } else
        {
            return false;
        }
    }

    public void notify(int i, int j, int k)
    {
        for(int l = 0; l < z.size(); l++)
            ((IWorldAccess)z.get(l)).a(i, j, k);

    }

    protected void update(int i, int j, int k, int l)
    {
        notify(i, j, k);
        applyPhysics(i, j, k, l);
    }

    public void g(int i, int j, int k, int l)
    {
        if(k > l)
        {
            int i1 = l;
            l = k;
            k = i1;
        }
        for(int i1 = k; i1 <= l; i1++)
            b(EnumSkyBlock.SKY, i, i1, j);

        b(i, k, j, i, l, j);
    }

    public void i(int i, int j, int k)
    {
        for(int l = 0; l < z.size(); l++)
            ((IWorldAccess)z.get(l)).a(i, j, k, i, j, k);

    }

    public void b(int i, int j, int k, int l, int i1, int j1)
    {
        for(int k1 = 0; k1 < z.size(); k1++)
            ((IWorldAccess)z.get(k1)).a(i, j, k, l, i1, j1);

    }

    public void applyPhysics(int i, int j, int k, int l)
    {
        k(i - 1, j, k, l);
        k(i + 1, j, k, l);
        k(i, j - 1, k, l);
        k(i, j + 1, k, l);
        k(i, j, k - 1, l);
        k(i, j, k + 1, l);
    }

    private void k(int i, int j, int k, int l)
    {
        if(!suppressPhysics && !isStatic)
        {
            net.minecraft.server.Block block = Block.byId[getTypeId(i, j, k)];
            if(block != null)
            {
                CraftWorld world = ((WorldServer)this).getWorld();
                if(world != null)
                {
                    BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(i, j, k), l);
                    getServer().getPluginManager().callEvent(event);
                    if(event.isCancelled())
                        return;
                }
                block.doPhysics(this, i, j, k, l);
            }
        }
    }

    public boolean isChunkLoaded(int i, int j, int k)
    {
        return getChunkAt(i >> 4, k >> 4).c(i & 0xf, j, k & 0xf);
    }

    public int k(int i, int j, int k)
    {
        if(j < 0)
            return 0;
        if(j >= 128)
            j = 127;
        return getChunkAt(i >> 4, k >> 4).c(i & 0xf, j, k & 0xf, 0);
    }

    public int getLightLevel(int i, int j, int k)
    {
        return a(i, j, k, true);
    }

    public int a(int i, int j, int k, boolean flag)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            if(flag)
            {
                int l = getTypeId(i, j, k);
                if(l == Block.STEP.id || l == Block.SOIL.id || l == Block.COBBLESTONE_STAIRS.id || l == Block.WOOD_STAIRS.id)
                {
                    int i1 = a(i, j + 1, k, false);
                    int j1 = a(i + 1, j, k, false);
                    int k1 = a(i - 1, j, k, false);
                    int l1 = a(i, j, k + 1, false);
                    int i2 = a(i, j, k - 1, false);
                    if(j1 > i1)
                        i1 = j1;
                    if(k1 > i1)
                        i1 = k1;
                    if(l1 > i1)
                        i1 = l1;
                    if(i2 > i1)
                        i1 = i2;
                    return i1;
                }
            }
            if(j < 0)
                return 0;
            if(j >= 128)
                j = 127;
            Chunk chunk = getChunkAt(i >> 4, k >> 4);
            i &= 0xf;
            k &= 0xf;
            return chunk.c(i, j, k, this.k);
        } else
        {
            return 15;
        }
    }

    public int getHighestBlockYAt(int i, int j)
    {
        if(i >= 0xfe363c80 && j >= 0xfe363c80 && i < 0x1c9c380 && j < 0x1c9c380)
        {
            if(!isChunkLoaded(i >> 4, j >> 4))
            {
                return 0;
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, j >> 4);
                return chunk.b(i & 0xf, j & 0xf);
            }
        } else
        {
            return 0;
        }
    }

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k)
    {
        if(j < 0)
            j = 0;
        if(j >= 128)
            j = 127;
        if(j >= 0 && j < 128 && i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380)
        {
            int l = i >> 4;
            int i1 = k >> 4;
            if(!isChunkLoaded(l, i1))
            {
                return 0;
            } else
            {
                Chunk chunk = getChunkAt(l, i1);
                return chunk.a(enumskyblock, i & 0xf, j, k & 0xf);
            }
        } else
        {
            return enumskyblock.c;
        }
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l)
    {
        if(i >= 0xfe363c80 && k >= 0xfe363c80 && i < 0x1c9c380 && k < 0x1c9c380 && j >= 0 && j < 128 && isChunkLoaded(i >> 4, k >> 4))
        {
            Chunk chunk = getChunkAt(i >> 4, k >> 4);
            chunk.a(enumskyblock, i & 0xf, j, k & 0xf, l);
            for(int i1 = 0; i1 < z.size(); i1++)
                ((IWorldAccess)z.get(i1)).a(i, j, k);

        }
    }

    public float m(int i, int j, int k)
    {
        return worldProvider.f[getLightLevel(i, j, k)];
    }

    public boolean d()
    {
        return k < 4;
    }

    public MovingObjectPosition a(Vec3D vec3d, Vec3D vec3d1)
    {
        return rayTrace(vec3d, vec3d1, false, false);
    }

    public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag)
    {
        return rayTrace(vec3d, vec3d1, flag, false);
    }

    public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag, boolean flag1)
    {
        if(!Double.isNaN(vec3d.a) && !Double.isNaN(vec3d.b) && !Double.isNaN(vec3d.c))
        {
            if(!Double.isNaN(vec3d1.a) && !Double.isNaN(vec3d1.b) && !Double.isNaN(vec3d1.c))
            {
                int i = MathHelper.floor(vec3d1.a);
                int j = MathHelper.floor(vec3d1.b);
                int k = MathHelper.floor(vec3d1.c);
                int l = MathHelper.floor(vec3d.a);
                int i1 = MathHelper.floor(vec3d.b);
                int j1 = MathHelper.floor(vec3d.c);
                int k1 = getTypeId(l, i1, j1);
                int l1 = getData(l, i1, j1);
                net.minecraft.server.Block block = Block.byId[k1];
                if((!flag1 || block == null || block.e(this, l, i1, j1) != null) && k1 > 0 && block.a(l1, flag))
                {
                    MovingObjectPosition movingobjectposition = block.a(this, l, i1, j1, vec3d, vec3d1);
                    if(movingobjectposition != null)
                        return movingobjectposition;
                }
                for(k1 = 200; k1-- >= 0;)
                {
                    if(Double.isNaN(vec3d.a) || Double.isNaN(vec3d.b) || Double.isNaN(vec3d.c))
                        return null;
                    if(l == i && i1 == j && j1 == k)
                        return null;
                    boolean flag2 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999D;
                    double d1 = 999D;
                    double d2 = 999D;
                    if(i > l)
                        d0 = (double)l + 1.0D;
                    else
                    if(i < l)
                        d0 = (double)l + 0.0D;
                    else
                        flag2 = false;
                    if(j > i1)
                        d1 = (double)i1 + 1.0D;
                    else
                    if(j < i1)
                        d1 = (double)i1 + 0.0D;
                    else
                        flag3 = false;
                    if(k > j1)
                        d2 = (double)j1 + 1.0D;
                    else
                    if(k < j1)
                        d2 = (double)j1 + 0.0D;
                    else
                        flag4 = false;
                    double d3 = 999D;
                    double d4 = 999D;
                    double d5 = 999D;
                    double d6 = vec3d1.a - vec3d.a;
                    double d7 = vec3d1.b - vec3d.b;
                    double d8 = vec3d1.c - vec3d.c;
                    if(flag2)
                        d3 = (d0 - vec3d.a) / d6;
                    if(flag3)
                        d4 = (d1 - vec3d.b) / d7;
                    if(flag4)
                        d5 = (d2 - vec3d.c) / d8;
                    boolean flag5 = false;
                    byte b0;
                    if(d3 < d4 && d3 < d5)
                    {
                        if(i > l)
                            b0 = 4;
                        else
                            b0 = 5;
                        vec3d.a = d0;
                        vec3d.b += d7 * d3;
                        vec3d.c += d8 * d3;
                    } else
                    if(d4 < d5)
                    {
                        if(j > i1)
                            b0 = 0;
                        else
                            b0 = 1;
                        vec3d.a += d6 * d4;
                        vec3d.b = d1;
                        vec3d.c += d8 * d4;
                    } else
                    {
                        if(k > j1)
                            b0 = 2;
                        else
                            b0 = 3;
                        vec3d.a += d6 * d5;
                        vec3d.b += d7 * d5;
                        vec3d.c = d2;
                    }
                    Vec3D vec3d2 = Vec3D.create(vec3d.a, vec3d.b, vec3d.c);
                    l = (int)(vec3d2.a = MathHelper.floor(vec3d.a));
                    if(b0 == 5)
                    {
                        l--;
                        vec3d2.a++;
                    }
                    i1 = (int)(vec3d2.b = MathHelper.floor(vec3d.b));
                    if(b0 == 1)
                    {
                        i1--;
                        vec3d2.b++;
                    }
                    j1 = (int)(vec3d2.c = MathHelper.floor(vec3d.c));
                    if(b0 == 3)
                    {
                        j1--;
                        vec3d2.c++;
                    }
                    int i2 = getTypeId(l, i1, j1);
                    int j2 = getData(l, i1, j1);
                    net.minecraft.server.Block block1 = Block.byId[i2];
                    if((!flag1 || block1 == null || block1.e(this, l, i1, j1) != null) && i2 > 0 && block1.a(j2, flag))
                    {
                        MovingObjectPosition movingobjectposition1 = block1.a(this, l, i1, j1, vec3d, vec3d1);
                        if(movingobjectposition1 != null)
                            return movingobjectposition1;
                    }
                }

                return null;
            } else
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    public void makeSound(Entity entity, String s, float f, float f1)
    {
        for(int i = 0; i < z.size(); i++)
            ((IWorldAccess)z.get(i)).a(s, entity.locX, entity.locY - (double)entity.height, entity.locZ, f, f1);

    }

    public void makeSound(double d0, double d1, double d2, String s, 
            float f, float f1)
    {
        for(int i = 0; i < z.size(); i++)
            ((IWorldAccess)z.get(i)).a(s, d0, d1, d2, f, f1);

    }

    public void a(String s, int i, int j, int k)
    {
        for(int l = 0; l < z.size(); l++)
            ((IWorldAccess)z.get(l)).a(s, i, j, k);

    }

    public void a(String s, double d0, double d1, double d2, 
            double d3, double d4, double d5)
    {
        for(int i = 0; i < z.size(); i++)
            ((IWorldAccess)z.get(i)).a(s, d0, d1, d2, d3, d4, d5);

    }

    public boolean strikeLightning(Entity entity)
    {
        j.add(entity);
        return true;
    }

    public boolean addEntity(Entity entity)
    {
        return addEntity(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    public boolean addEntity(Entity entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason spawnReason)
    {
        int i = MathHelper.floor(entity.locX / 16D);
        int j = MathHelper.floor(entity.locZ / 16D);
        boolean flag = false;
        if(entity instanceof EntityHuman)
            flag = true;
        if((entity instanceof EntityLiving) && !(entity instanceof EntityPlayer))
        {
            boolean isAnimal = (entity instanceof EntityAnimal) || (entity instanceof EntityWaterAnimal);
            boolean isMonster = (entity instanceof EntityMonster) || (entity instanceof EntityGhast) || (entity instanceof EntitySlime);
            if((spawnReason == org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.NATURAL || spawnReason == org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SPAWNER || spawnReason == org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.BED || spawnReason == org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.EGG) && (isAnimal && !allowAnimals || isMonster && !allowMonsters))
                return false;
            CreatureSpawnEvent event = CraftEventFactory.callCreatureSpawnEvent((EntityLiving)entity, spawnReason);
            if(event.isCancelled())
                return false;
        } else
        if(entity instanceof EntityItem)
        {
            ItemSpawnEvent event = CraftEventFactory.callItemSpawnEvent((EntityItem)entity);
            if(event.isCancelled())
                return false;
        }
        if(!flag && !isChunkLoaded(i, j))
            return false;
        if(entity instanceof EntityHuman)
        {
            EntityHuman entityhuman = (EntityHuman)entity;
            players.add(entityhuman);
            everyoneSleeping();
        }
        getChunkAt(i, j).a(entity);
        entityList.add(entity);
        c(entity);
        return true;
    }

    protected void c(Entity entity)
    {
        for(int i = 0; i < z.size(); i++)
            ((IWorldAccess)z.get(i)).a(entity);

    }

    protected void d(Entity entity)
    {
        for(int i = 0; i < z.size(); i++)
            ((IWorldAccess)z.get(i)).b(entity);

    }

    public void kill(Entity entity)
    {
        if(entity.passenger != null)
            entity.passenger.mount((Entity)null);
        if(entity.vehicle != null)
            entity.mount((Entity)null);
        entity.die();
        if(entity instanceof EntityHuman)
        {
            players.remove((EntityHuman)entity);
            everyoneSleeping();
        }
    }

    public void removeEntity(Entity entity)
    {
        entity.die();
        if(entity instanceof EntityHuman)
        {
            players.remove((EntityHuman)entity);
            everyoneSleeping();
        }
        int i = entity.bW;
        int j = entity.bY;
        if(entity.bV && isChunkLoaded(i, j))
            getChunkAt(i, j).b(entity);
        entityList.remove(entity);
        d(entity);
    }

    public void addIWorldAccess(IWorldAccess iworldaccess)
    {
        z.add(iworldaccess);
    }

    public List getEntities(Entity entity, AxisAlignedBB axisalignedbb)
    {
        U.clear();
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = i1; l1 < j1; l1++)
            {
                if(!isLoaded(k1, 64, l1))
                    continue;
                for(int i2 = k - 1; i2 < l; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, i2, l1)];
                    if(block != null)
                        block.a(this, k1, i2, l1, axisalignedbb, U);
                }

            }

        }

        double d0 = 0.25D;
        List list = b(entity, axisalignedbb.b(d0, d0, d0));
        for(int j2 = 0; j2 < list.size(); j2++)
        {
            AxisAlignedBB axisalignedbb1 = ((Entity)list.get(j2)).f();
            if(axisalignedbb1 != null && axisalignedbb1.a(axisalignedbb))
                U.add(axisalignedbb1);
            axisalignedbb1 = entity.b((Entity)list.get(j2));
            if(axisalignedbb1 != null && axisalignedbb1.a(axisalignedbb))
                U.add(axisalignedbb1);
        }

        return U;
    }

    public int a(float f)
    {
        float f1 = b(f);
        float f2 = 1.0F - (MathHelper.cos(f1 * 3.141593F * 2.0F) * 2.0F + 0.5F);
        if(f2 < 0.0F)
            f2 = 0.0F;
        if(f2 > 1.0F)
            f2 = 1.0F;
        f2 = 1.0F - f2;
        f2 = (float)((double)f2 * (1.0D - (double)(d(f) * 5F) / 16D));
        f2 = (float)((double)f2 * (1.0D - (double)(c(f) * 5F) / 16D));
        f2 = 1.0F - f2;
        return (int)(f2 * 11F);
    }

    public float b(float f)
    {
        return worldProvider.a(worldData.f(), f) + (float)(J + (K - J) * (double)f);
    }

    public int e(int i, int j)
    {
        return getChunkAtWorldCoords(i, j).c(i & 0xf, j & 0xf);
    }

    public int f(int i, int j)
    {
        Chunk chunk = getChunkAtWorldCoords(i, j);
        int k = 127;
        i &= 0xf;
        j &= 0xf;
        for(; k > 0; k--)
        {
            int l = chunk.getTypeId(i, k, j);
            if(l != 0 && Block.byId[l].material.isSolid() && Block.byId[l].material != Material.LEAVES)
                return k + 1;
        }

        return -1;
    }

    public void c(int i, int j, int k, int l, int i1)
    {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i, j, k, l);
        byte b0 = 8;
        if(f)
        {
            if(a(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0))
            {
                int j1 = getTypeId(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);
                if(j1 == nextticklistentry.d && j1 > 0)
                    Block.byId[j1].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, random);
            }
        } else
        if(a(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0))
        {
            if(l > 0)
                nextticklistentry.a((long)i1 + worldData.f());
            if(!O.contains(nextticklistentry))
            {
                O.add(nextticklistentry);
                N.add(nextticklistentry);
            }
        }
    }

    public void tickEntities()
    {
        for(int i = 0; i < this.j.size(); i++)
        {
            Entity entity = (Entity)this.j.get(i);
            if(entity == null)
                continue;
            entity.s_();
            if(entity.dead)
                this.j.remove(i--);
        }

        entityList.removeAll(M);
        for(int i = 0; i < M.size(); i++)
        {
            Entity entity = (Entity)M.get(i);
            int j = entity.bW;
            int k = entity.bY;
            if(entity.bV && isChunkLoaded(j, k))
                getChunkAt(j, k).b(entity);
        }

        for(int i = 0; i < M.size(); i++)
            d((Entity)M.get(i));

        M.clear();
        for(int i = 0; i < entityList.size(); i++)
        {
            Entity entity = (Entity)entityList.get(i);
            if(entity.vehicle != null)
            {
                if(!entity.vehicle.dead && entity.vehicle.passenger == entity)
                    continue;
                entity.vehicle.passenger = null;
                entity.vehicle = null;
            }
            if(!entity.dead)
                playerJoinedWorld(entity);
            if(!entity.dead)
                continue;
            int j = entity.bW;
            int k = entity.bY;
            if(entity.bV && isChunkLoaded(j, k))
                getChunkAt(j, k).b(entity);
            entityList.remove(i--);
            d(entity);
        }

        V = true;
        Iterator iterator = h.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            TileEntity tileentity = (TileEntity)iterator.next();
            if(!tileentity.m() && tileentity.world != null)
                tileentity.h_();
            if(tileentity.m())
            {
                iterator.remove();
                if(isChunkLoaded(tileentity.x >> 4, tileentity.z >> 4))
                {
                    Chunk chunk = getChunkAt(tileentity.x >> 4, tileentity.z >> 4);
                    if(chunk != null)
                        chunk.e(tileentity.x & 0xf, tileentity.y, tileentity.z & 0xf);
                }
            }
        } while(true);
        V = false;
        if(!Q.isEmpty())
        {
            h.removeAll(Q);
            Q.clear();
        }
        if(!P.isEmpty())
        {
            Iterator iterator1 = P.iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                TileEntity tileentity1 = (TileEntity)iterator1.next();
                if(!tileentity1.m())
                {
                    if(isChunkLoaded(tileentity1.x >> 4, tileentity1.z >> 4))
                    {
                        Chunk chunk1 = getChunkAt(tileentity1.x >> 4, tileentity1.z >> 4);
                        if(chunk1 != null)
                        {
                            chunk1.a(tileentity1.x & 0xf, tileentity1.y, tileentity1.z & 0xf, tileentity1);
                            if(!h.contains(tileentity1))
                                h.add(tileentity1);
                        }
                    }
                    notify(tileentity1.x, tileentity1.y, tileentity1.z);
                }
            } while(true);
            P.clear();
        }
    }

    public void a(Collection collection)
    {
        if(V)
            P.addAll(collection);
        else
            h.addAll(collection);
    }

    public void playerJoinedWorld(Entity entity)
    {
        entityJoinedWorld(entity, true);
    }

    public void entityJoinedWorld(Entity entity, boolean flag)
    {
        int i = MathHelper.floor(entity.locX);
        int j = MathHelper.floor(entity.locZ);
        byte b0 = 32;
        if(!flag || a(i - b0, 0, j - b0, i + b0, 128, j + b0))
        {
            entity.bE = entity.locX;
            entity.bF = entity.locY;
            entity.bG = entity.locZ;
            entity.lastYaw = entity.yaw;
            entity.lastPitch = entity.pitch;
            if(flag && entity.bV)
                if(entity.vehicle != null)
                    entity.I();
                else
                    entity.s_();
            if(Double.isNaN(entity.locX) || Double.isInfinite(entity.locX))
                entity.locX = entity.bE;
            if(Double.isNaN(entity.locY) || Double.isInfinite(entity.locY))
                entity.locY = entity.bF;
            if(Double.isNaN(entity.locZ) || Double.isInfinite(entity.locZ))
                entity.locZ = entity.bG;
            if(Double.isNaN(entity.pitch) || Double.isInfinite(entity.pitch))
                entity.pitch = entity.lastPitch;
            if(Double.isNaN(entity.yaw) || Double.isInfinite(entity.yaw))
                entity.yaw = entity.lastYaw;
            int k = MathHelper.floor(entity.locX / 16D);
            int l = MathHelper.floor(entity.locY / 16D);
            int i1 = MathHelper.floor(entity.locZ / 16D);
            if(!entity.bV || entity.bW != k || entity.bX != l || entity.bY != i1)
            {
                if(entity.bV && isChunkLoaded(entity.bW, entity.bY))
                    getChunkAt(entity.bW, entity.bY).a(entity, entity.bX);
                if(isChunkLoaded(k, i1))
                {
                    entity.bV = true;
                    getChunkAt(k, i1).a(entity);
                } else
                {
                    entity.bV = false;
                }
            }
            if(flag && entity.bV && entity.passenger != null)
                if(!entity.passenger.dead && entity.passenger.vehicle == entity)
                {
                    playerJoinedWorld(entity.passenger);
                } else
                {
                    entity.passenger.vehicle = null;
                    entity.passenger = null;
                }
        }
    }

    public boolean containsEntity(AxisAlignedBB axisalignedbb)
    {
        List list = b((Entity)null, axisalignedbb);
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(!entity.dead && entity.aY)
                return false;
        }

        return true;
    }

    public boolean b(AxisAlignedBB axisalignedbb)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        if(axisalignedbb.a < 0.0D)
            i--;
        if(axisalignedbb.b < 0.0D)
            k--;
        if(axisalignedbb.c < 0.0D)
            i1--;
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, l1, i2)];
                    if(block != null)
                        return true;
                }

            }

        }

        return false;
    }

    public boolean c(AxisAlignedBB axisalignedbb)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        if(axisalignedbb.a < 0.0D)
            i--;
        if(axisalignedbb.b < 0.0D)
            k--;
        if(axisalignedbb.c < 0.0D)
            i1--;
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, l1, i2)];
                    if(block != null && block.material.isLiquid())
                        return true;
                }

            }

        }

        return false;
    }

    public boolean d(AxisAlignedBB axisalignedbb)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        if(a(i, k, i1, j, l, j1))
        {
            for(int k1 = i; k1 < j; k1++)
            {
                for(int l1 = k; l1 < l; l1++)
                {
                    for(int i2 = i1; i2 < j1; i2++)
                    {
                        int j2 = getTypeId(k1, l1, i2);
                        if(j2 == Block.FIRE.id || j2 == Block.LAVA.id || j2 == Block.STATIONARY_LAVA.id)
                            return true;
                    }

                }

            }

        }
        return false;
    }

    public boolean a(AxisAlignedBB axisalignedbb, Material material, Entity entity)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        if(!a(i, k, i1, j, l, j1))
            return false;
        boolean flag = false;
        Vec3D vec3d = Vec3D.create(0.0D, 0.0D, 0.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, l1, i2)];
                    if(block == null || block.material != material)
                        continue;
                    double d0 = (float)(l1 + 1) - BlockFluids.c(getData(k1, l1, i2));
                    if((double)l >= d0)
                    {
                        flag = true;
                        block.a(this, k1, l1, i2, entity, vec3d);
                    }
                }

            }

        }

        if(vec3d.c() > 0.0D)
        {
            vec3d = vec3d.b();
            double d1 = 0.014D;
            entity.motX += vec3d.a * d1;
            entity.motY += vec3d.b * d1;
            entity.motZ += vec3d.c * d1;
        }
        return flag;
    }

    public boolean a(AxisAlignedBB axisalignedbb, Material material)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, l1, i2)];
                    if(block != null && block.material == material)
                        return true;
                }

            }

        }

        return false;
    }

    public boolean b(AxisAlignedBB axisalignedbb, Material material)
    {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
        for(int k1 = i; k1 < j; k1++)
        {
            for(int l1 = k; l1 < l; l1++)
            {
                for(int i2 = i1; i2 < j1; i2++)
                {
                    net.minecraft.server.Block block = Block.byId[getTypeId(k1, l1, i2)];
                    if(block == null || block.material != material)
                        continue;
                    int j2 = getData(k1, l1, i2);
                    double d0 = l1 + 1;
                    if(j2 < 8)
                        d0 = (double)(l1 + 1) - (double)j2 / 8D;
                    if(d0 >= axisalignedbb.b)
                        return true;
                }

            }

        }

        return false;
    }

    public Explosion a(Entity entity, double d0, double d1, double d2, 
            float f)
    {
        return createExplosion(entity, d0, d1, d2, f, false);
    }

    public Explosion createExplosion(Entity entity, double d0, double d1, double d2, 
            float f, boolean flag)
    {
        Explosion explosion = new Explosion(this, entity, d0, d1, d2, f);
        explosion.a = flag;
        explosion.a();
        explosion.a(true);
        return explosion;
    }

    public float a(Vec3D vec3d, AxisAlignedBB axisalignedbb)
    {
        double d0 = 1.0D / ((axisalignedbb.d - axisalignedbb.a) * 2D + 1.0D);
        double d1 = 1.0D / ((axisalignedbb.e - axisalignedbb.b) * 2D + 1.0D);
        double d2 = 1.0D / ((axisalignedbb.f - axisalignedbb.c) * 2D + 1.0D);
        int i = 0;
        int j = 0;
        for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0))
        {
            for(float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1))
            {
                for(float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2))
                {
                    double d3 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * (double)f;
                    double d4 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * (double)f1;
                    double d5 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * (double)f2;
                    if(a(Vec3D.create(d3, d4, d5), vec3d) == null)
                        i++;
                    j++;
                }

            }

        }

        return (float)i / (float)j;
    }

    public void douseFire(EntityHuman entityhuman, int i, int j, int k, int l)
    {
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
        if(getTypeId(i, j, k) == Block.FIRE.id)
        {
            a(entityhuman, 1004, i, j, k, 0);
            setTypeId(i, j, k, 0);
        }
    }

    public TileEntity getTileEntity(int i, int j, int k)
    {
        Chunk chunk = getChunkAt(i >> 4, k >> 4);
        return chunk == null ? null : chunk.d(i & 0xf, j, k & 0xf);
    }

    public void setTileEntity(int i, int j, int k, TileEntity tileentity)
    {
        if(tileentity != null && !tileentity.m())
            if(V)
            {
                tileentity.x = i;
                tileentity.y = j;
                tileentity.z = k;
                P.add(tileentity);
            } else
            {
                Chunk chunk = getChunkAt(i >> 4, k >> 4);
                if(chunk != null)
                {
                    chunk.a(i & 0xf, j, k & 0xf, tileentity);
                    h.add(tileentity);
                }
            }
    }

    public void n(int i, int j, int k)
    {
        TileEntity tileentity = getTileEntity(i, j, k);
        if(tileentity != null && V)
        {
            tileentity.i();
        } else
        {
            if(tileentity != null)
                h.remove(tileentity);
            Chunk chunk = getChunkAt(i >> 4, k >> 4);
            if(chunk != null)
                chunk.e(i & 0xf, j, k & 0xf);
        }
    }

    public void a(TileEntity tileentity)
    {
        Q.add(tileentity);
    }

    public boolean o(int i, int j, int k)
    {
        net.minecraft.server.Block block = Block.byId[getTypeId(i, j, k)];
        return block != null ? block.a() : false;
    }

    public boolean e(int i, int j, int k)
    {
        net.minecraft.server.Block block = Block.byId[getTypeId(i, j, k)];
        return block != null ? block.material.j() && block.b() : false;
    }

    public void f()
    {
        int i = a(1.0F);
        if(i != k)
            k = i;
    }

    public void setSpawnFlags(boolean flag, boolean flag1)
    {
        allowMonsters = flag;
        allowAnimals = flag1;
    }

    public void doTick()
    {
        J = K;
        K += L;
        L *= 0.97999999999999998D;
        getWorldChunkManager().b();
        h();
        long i;
        if(everyoneDeeplySleeping())
        {
            boolean flag = false;
            if(allowMonsters && difficulty >= 1)
                flag = SpawnerCreature.a(this, players);
            if(!flag)
            {
                i = worldData.f() + 24000L;
                worldData.a(i - i % 24000L);
                r();
            }
        }
        if((allowMonsters || allowAnimals) && (this instanceof WorldServer) && getServer().getHandle().players.size() > 0)
            SpawnerCreature.spawnEntities(this, allowMonsters, allowAnimals && worldData.f() % 400L == 0L);
        chunkProvider.unloadChunks();
        int j = a(1.0F);
        if(j != k)
            k = j;
        i = worldData.f() + 1L;
        if(i % (long)u == 0L)
            save(false, (IProgressUpdate)null);
        worldData.a(i);
        a(false);
        i();
    }

    private void x()
    {
        if(worldData.hasStorm())
        {
            o = 1.0F;
            if(worldData.isThundering())
                q = 1.0F;
        }
    }

    protected void h()
    {
        if(!worldProvider.e)
        {
            if(r > 0)
                r--;
            int i = worldData.getThunderDuration();
            if(i <= 0)
            {
                if(worldData.isThundering())
                    worldData.setThunderDuration(random.nextInt(12000) + 3600);
                else
                    worldData.setThunderDuration(random.nextInt(0x29040) + 12000);
            } else
            {
                i--;
                worldData.setThunderDuration(i);
                if(i <= 0)
                {
                    ThunderChangeEvent thunder = new ThunderChangeEvent(getWorld(), !worldData.isThundering());
                    getServer().getPluginManager().callEvent(thunder);
                    if(!thunder.isCancelled())
                        worldData.setThundering(!worldData.isThundering());
                }
            }
            int j = worldData.getWeatherDuration();
            if(j <= 0)
            {
                if(worldData.hasStorm())
                    worldData.setWeatherDuration(random.nextInt(12000) + 12000);
                else
                    worldData.setWeatherDuration(random.nextInt(0x29040) + 12000);
            } else
            {
                j--;
                worldData.setWeatherDuration(j);
                if(j <= 0)
                {
                    WeatherChangeEvent weather = new WeatherChangeEvent(getWorld(), !worldData.hasStorm());
                    getServer().getPluginManager().callEvent(weather);
                    if(!weather.isCancelled())
                        worldData.setStorm(!worldData.hasStorm());
                }
            }
            n = o;
            if(worldData.hasStorm())
                o = (float)((double)o + 0.01D);
            else
                o = (float)((double)o - 0.01D);
            if(o < 0.0F)
                o = 0.0F;
            if(o > 1.0F)
                o = 1.0F;
            p = q;
            if(worldData.isThundering())
                q = (float)((double)q + 0.01D);
            else
                q = (float)((double)q - 0.01D);
            if(q < 0.0F)
                q = 0.0F;
            if(q > 1.0F)
                q = 1.0F;
        }
    }

    private void y()
    {
        WeatherChangeEvent weather = new WeatherChangeEvent(getWorld(), false);
        getServer().getPluginManager().callEvent(weather);
        ThunderChangeEvent thunder = new ThunderChangeEvent(getWorld(), false);
        getServer().getPluginManager().callEvent(thunder);
        if(!weather.isCancelled())
        {
            worldData.setWeatherDuration(0);
            worldData.setStorm(false);
        }
        if(!thunder.isCancelled())
        {
            worldData.setThunderDuration(0);
            worldData.setThundering(false);
        }
    }

    protected void i()
    {
        W.clear();
        for(int i1 = 0; i1 < players.size(); i1++)
        {
            EntityHuman entityhuman = (EntityHuman)players.get(i1);
            int i = MathHelper.floor(entityhuman.locX / 16D);
            int j = MathHelper.floor(entityhuman.locZ / 16D);
            byte b0 = 9;
            for(int k = -b0; k <= b0; k++)
            {
                for(int l = -b0; l <= b0; l++)
                    W.add(new ChunkCoordIntPair(k + i, l + j));

            }

        }

        if(X > 0)
            X--;
        for(Iterator iterator = W.iterator(); iterator.hasNext();)
        {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator.next();
            int i = chunkcoordintpair.x * 16;
            int j = chunkcoordintpair.z * 16;
            Chunk chunk = getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z);
            chunk.h();
            int k;
            if(X == 0)
            {
                this.l = this.l * 3 + 0x3c6ef35f;
                k = this.l >> 2;
                int l = k & 0xf;
                int j1 = k >> 8 & 0xf;
                int k1 = k >> 16 & 0x7f;
                int l1 = chunk.getTypeId(l, k1, j1);
                l += i;
                j1 += j;
                if(l1 == 0 && k(l, k1, j1) <= random.nextInt(8) && a(EnumSkyBlock.SKY, l, k1, j1) <= 0)
                {
                    EntityHuman entityhuman1 = a((double)l + 0.5D, (double)k1 + 0.5D, (double)j1 + 0.5D, 8D);
                    if(entityhuman1 != null && entityhuman1.e((double)l + 0.5D, (double)k1 + 0.5D, (double)j1 + 0.5D) > 4D)
                    {
                        makeSound((double)l + 0.5D, (double)k1 + 0.5D, (double)j1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + random.nextFloat() * 0.2F);
                        X = random.nextInt(12000) + 6000;
                    }
                }
            }
            if(random.nextInt(0x186a0) == 0 && u() && t())
            {
                this.l = this.l * 3 + 0x3c6ef35f;
                k = this.l >> 2;
                int l = i + (k & 0xf);
                int j1 = j + (k >> 8 & 0xf);
                int k1 = e(l, j1);
                if(s(l, k1, j1))
                {
                    strikeLightning(new EntityWeatherStorm(this, l, k1, j1));
                    r = 2;
                }
            }
            if(random.nextInt(16) == 0)
            {
                this.l = this.l * 3 + 0x3c6ef35f;
                k = this.l >> 2;
                int l = k & 0xf;
                int j1 = k >> 8 & 0xf;
                int k1 = e(l + i, j1 + j);
                if(getWorldChunkManager().getBiome(l + i, j1 + j).b() && k1 >= 0 && k1 < 128 && chunk.a(EnumSkyBlock.BLOCK, l, k1, j1) < 10)
                {
                    int l1 = chunk.getTypeId(l, k1 - 1, j1);
                    int i2 = chunk.getTypeId(l, k1, j1);
                    if(u() && i2 == 0 && Block.SNOW.canPlace(this, l + i, k1, j1 + j) && l1 != 0 && l1 != Block.ICE.id && Block.byId[l1].material.isSolid())
                    {
                        BlockState blockState = getWorld().getBlockAt(l + i, k1, j1 + j).getState();
                        blockState.setTypeId(Block.SNOW.id);
                        BlockFormEvent snow = new BlockFormEvent(blockState.getBlock(), blockState);
                        getServer().getPluginManager().callEvent(snow);
                        if(!snow.isCancelled())
                            blockState.update(true);
                    }
                    if(l1 == Block.STATIONARY_WATER.id && chunk.getData(l, k1 - 1, j1) == 0)
                    {
                        boolean flag = true;
                        if(flag && getMaterial((l + i) - 1, k1 - 1, j1 + j) != Material.WATER)
                            flag = false;
                        if(flag && getMaterial(l + i + 1, k1 - 1, j1 + j) != Material.WATER)
                            flag = false;
                        if(flag && getMaterial(l + i, k1 - 1, (j1 + j) - 1) != Material.WATER)
                            flag = false;
                        if(flag && getMaterial(l + i, k1 - 1, j1 + j + 1) != Material.WATER)
                            flag = false;
                        if(!flag)
                        {
                            BlockState blockState = getWorld().getBlockAt(l + i, k1 - 1, j1 + j).getState();
                            blockState.setTypeId(Block.ICE.id);
                            BlockFormEvent iceBlockForm = new BlockFormEvent(blockState.getBlock(), blockState);
                            getServer().getPluginManager().callEvent(iceBlockForm);
                            if(!iceBlockForm.isCancelled())
                                blockState.update(true);
                        }
                    }
                }
            }
            p(i + random.nextInt(16), random.nextInt(128), j + random.nextInt(16));
            k = 0;
            while(k < 80) 
            {
                this.l = this.l * 3 + 0x3c6ef35f;
                int l = this.l >> 2;
                int j1 = l & 0xf;
                int k1 = l >> 8 & 0xf;
                int l1 = l >> 16 & 0x7f;
                int i2 = chunk.b[j1 << 11 | k1 << 7 | l1] & 0xff;
                if(Block.n[i2])
                    Block.byId[i2].a(this, j1 + i, l1, k1 + j, random);
                k++;
            }
        }

    }

    public void p(int i, int j, int k)
    {
        b(EnumSkyBlock.SKY, i, j, k);
        b(EnumSkyBlock.BLOCK, i, j, k);
    }

    private int d(int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = 0;
        if(isChunkLoaded(j, k, l))
        {
            k1 = 15;
        } else
        {
            if(j1 == 0)
                j1 = 1;
            for(int l1 = 0; l1 < 6; l1++)
            {
                int i2 = (l1 % 2) * 2 - 1;
                int j2 = j + (((l1 / 2) % 3) / 2) * i2;
                int k2 = k + (((l1 / 2 + 1) % 3) / 2) * i2;
                int l2 = l + (((l1 / 2 + 2) % 3) / 2) * i2;
                int i3 = a(EnumSkyBlock.SKY, j2, k2, l2) - j1;
                if(i3 > k1)
                    k1 = i3;
            }

        }
        return k1;
    }

    private int e(int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = Block.s[i1];
        int l1 = a(EnumSkyBlock.BLOCK, j - 1, k, l) - j1;
        int i2 = a(EnumSkyBlock.BLOCK, j + 1, k, l) - j1;
        int j2 = a(EnumSkyBlock.BLOCK, j, k - 1, l) - j1;
        int k2 = a(EnumSkyBlock.BLOCK, j, k + 1, l) - j1;
        int l2 = a(EnumSkyBlock.BLOCK, j, k, l - 1) - j1;
        int i3 = a(EnumSkyBlock.BLOCK, j, k, l + 1) - j1;
        if(l1 > k1)
            k1 = l1;
        if(i2 > k1)
            k1 = i2;
        if(j2 > k1)
            k1 = j2;
        if(k2 > k1)
            k1 = k2;
        if(l2 > k1)
            k1 = l2;
        if(i3 > k1)
            k1 = i3;
        return k1;
    }

    public void b(EnumSkyBlock enumskyblock, int i, int j, int k)
    {
        if(areChunksLoaded(i, j, k, 17))
        {
            int l = 0;
            int i1 = 0;
            int j1 = a(enumskyblock, i, j, k);
            boolean flag = false;
            int k1 = getTypeId(i, j, k);
            int l1 = Block.q[k1];
            if(l1 == 0)
                l1 = 1;
            boolean flag1 = false;
            int i2;
            if(enumskyblock == EnumSkyBlock.SKY)
                i2 = d(j1, i, j, k, k1, l1);
            else
                i2 = e(j1, i, j, k, k1, l1);
            if(i2 > j1)
                H[i1++] = 0x20820;
            else
            if(i2 < j1)
            {
                if(enumskyblock == EnumSkyBlock.BLOCK);
                H[i1++] = 0x20820 + (j1 << 18);
                do
                {
                    if(l >= i1)
                        break;
                    int j2 = H[l++];
                    k1 = ((j2 & 0x3f) - 32) + i;
                    l1 = ((j2 >> 6 & 0x3f) - 32) + j;
                    i2 = ((j2 >> 12 & 0x3f) - 32) + k;
                    int k2 = j2 >> 18 & 0xf;
                    int l2 = a(enumskyblock, k1, l1, i2);
                    if(l2 == k2)
                    {
                        a(enumskyblock, k1, l1, i2, 0);
                        if(--k2 > 0)
                        {
                            int i3 = k1 - i;
                            int k3 = l1 - j;
                            int j3 = i2 - k;
                            if(i3 < 0)
                                i3 = -i3;
                            if(k3 < 0)
                                k3 = -k3;
                            if(j3 < 0)
                                j3 = -j3;
                            if(i3 + k3 + j3 < 17)
                            {
                                int l3 = 0;
                                while(l3 < 6) 
                                {
                                    int i4 = (l3 % 2) * 2 - 1;
                                    int j4 = k1 + (((l3 / 2) % 3) / 2) * i4;
                                    int k4 = l1 + (((l3 / 2 + 1) % 3) / 2) * i4;
                                    int l4 = i2 + (((l3 / 2 + 2) % 3) / 2) * i4;
                                    l2 = a(enumskyblock, j4, k4, l4);
                                    if(l2 == k2)
                                        H[i1++] = (j4 - i) + 32 + ((k4 - j) + 32 << 6) + ((l4 - k) + 32 << 12) + (k2 << 18);
                                    l3++;
                                }
                            }
                        }
                    }
                } while(true);
                l = 0;
            }
            do
            {
                if(l >= i1)
                    break;
                j1 = H[l++];
                int i5 = ((j1 & 0x3f) - 32) + i;
                int j2 = ((j1 >> 6 & 0x3f) - 32) + j;
                k1 = ((j1 >> 12 & 0x3f) - 32) + k;
                l1 = a(enumskyblock, i5, j2, k1);
                i2 = getTypeId(i5, j2, k1);
                int k2 = Block.q[i2];
                if(k2 == 0)
                    k2 = 1;
                boolean flag2 = false;
                int l2;
                if(enumskyblock == EnumSkyBlock.SKY)
                    l2 = d(l1, i5, j2, k1, i2, k2);
                else
                    l2 = e(l1, i5, j2, k1, i2, k2);
                if(l2 != l1)
                {
                    a(enumskyblock, i5, j2, k1, l2);
                    if(l2 > l1)
                    {
                        int i3 = i5 - i;
                        int k3 = j2 - j;
                        int j3 = k1 - k;
                        if(i3 < 0)
                            i3 = -i3;
                        if(k3 < 0)
                            k3 = -k3;
                        if(j3 < 0)
                            j3 = -j3;
                        if(i3 + k3 + j3 < 17 && i1 < H.length - 6)
                        {
                            if(a(enumskyblock, i5 - 1, j2, k1) < l2)
                                H[i1++] = (i5 - 1 - i) + 32 + ((j2 - j) + 32 << 6) + ((k1 - k) + 32 << 12);
                            if(a(enumskyblock, i5 + 1, j2, k1) < l2)
                                H[i1++] = ((i5 + 1) - i) + 32 + ((j2 - j) + 32 << 6) + ((k1 - k) + 32 << 12);
                            if(a(enumskyblock, i5, j2 - 1, k1) < l2)
                                H[i1++] = (i5 - i) + 32 + ((j2 - 1 - j) + 32 << 6) + ((k1 - k) + 32 << 12);
                            if(a(enumskyblock, i5, j2 + 1, k1) < l2)
                                H[i1++] = (i5 - i) + 32 + (((j2 + 1) - j) + 32 << 6) + ((k1 - k) + 32 << 12);
                            if(a(enumskyblock, i5, j2, k1 - 1) < l2)
                                H[i1++] = (i5 - i) + 32 + ((j2 - j) + 32 << 6) + ((k1 - 1 - k) + 32 << 12);
                            if(a(enumskyblock, i5, j2, k1 + 1) < l2)
                                H[i1++] = (i5 - i) + 32 + ((j2 - j) + 32 << 6) + (((k1 + 1) - k) + 32 << 12);
                        }
                    }
                }
            } while(true);
        }
    }

    public boolean a(boolean flag)
    {
        int i = N.size();
        if(i != O.size())
            throw new IllegalStateException("TickNextTick list out of synch");
        if(i > 1000)
            i = 1000;
        for(int j = 0; j < i; j++)
        {
            NextTickListEntry nextticklistentry = (NextTickListEntry)N.first();
            if(!flag && nextticklistentry.e > worldData.f())
                break;
            N.remove(nextticklistentry);
            O.remove(nextticklistentry);
            byte b0 = 8;
            if(!a(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0))
                continue;
            int k = getTypeId(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);
            if(k == nextticklistentry.d && k > 0)
                Block.byId[k].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, random);
        }

        return N.size() != 0;
    }

    public List b(Entity entity, AxisAlignedBB axisalignedbb)
    {
        Y.clear();
        int i = MathHelper.floor((axisalignedbb.a - 2D) / 16D);
        int j = MathHelper.floor((axisalignedbb.d + 2D) / 16D);
        int k = MathHelper.floor((axisalignedbb.c - 2D) / 16D);
        int l = MathHelper.floor((axisalignedbb.f + 2D) / 16D);
        for(int i1 = i; i1 <= j; i1++)
        {
            for(int j1 = k; j1 <= l; j1++)
                if(isChunkLoaded(i1, j1))
                    getChunkAt(i1, j1).a(entity, axisalignedbb, Y);

        }

        return Y;
    }

    public List a(Class oclass, AxisAlignedBB axisalignedbb)
    {
        int i = MathHelper.floor((axisalignedbb.a - 2D) / 16D);
        int j = MathHelper.floor((axisalignedbb.d + 2D) / 16D);
        int k = MathHelper.floor((axisalignedbb.c - 2D) / 16D);
        int l = MathHelper.floor((axisalignedbb.f + 2D) / 16D);
        ArrayList arraylist = new ArrayList();
        for(int i1 = i; i1 <= j; i1++)
        {
            for(int j1 = k; j1 <= l; j1++)
                if(isChunkLoaded(i1, j1))
                    getChunkAt(i1, j1).a(oclass, axisalignedbb, arraylist);

        }

        return arraylist;
    }

    public void b(int i, int j, int k, TileEntity tileentity)
    {
        if(isLoaded(i, j, k))
            getChunkAtWorldCoords(i, k).f();
        for(int l = 0; l < z.size(); l++)
            ((IWorldAccess)z.get(l)).a(i, j, k, tileentity);

    }

    public int a(Class oclass)
    {
        int i = 0;
        for(int j = 0; j < entityList.size(); j++)
        {
            Entity entity = (Entity)entityList.get(j);
            if(oclass.isAssignableFrom(entity.getClass()))
                i++;
        }

        return i;
    }

    public void a(List list)
    {
        Entity entity = null;
        for(int i = 0; i < list.size(); i++)
        {
            entity = (Entity)list.get(i);
            if(entity != null)
            {
                entityList.add(entity);
                c((Entity)list.get(i));
            }
        }

    }

    public void b(List list)
    {
        M.addAll(list);
    }

    public boolean a(int i, int j, int k, int l, boolean flag, int i1)
    {
        int j1 = getTypeId(j, k, l);
        net.minecraft.server.Block block = Block.byId[j1];
        net.minecraft.server.Block block1 = Block.byId[i];
        AxisAlignedBB axisalignedbb = block1.e(this, j, k, l);
        if(flag)
            axisalignedbb = null;
        boolean defaultReturn;
        if(axisalignedbb != null && !containsEntity(axisalignedbb))
        {
            defaultReturn = false;
        } else
        {
            if(block == Block.WATER || block == Block.STATIONARY_WATER || block == Block.LAVA || block == Block.STATIONARY_LAVA || block == Block.FIRE || block == Block.SNOW || block == Block.VINE)
                block = null;
            defaultReturn = i > 0 && block == null && block1.canPlace(this, j, k, l, i1);
        }
        BlockCanBuildEvent event = new BlockCanBuildEvent(getWorld().getBlockAt(j, k, l), i, defaultReturn);
        getServer().getPluginManager().callEvent(event);
        return event.isBuildable();
    }

    public PathEntity findPath(Entity entity, Entity entity1, float f)
    {
        int i = MathHelper.floor(entity.locX);
        int j = MathHelper.floor(entity.locY);
        int k = MathHelper.floor(entity.locZ);
        int l = (int)(f + 16F);
        int i1 = i - l;
        int j1 = j - l;
        int k1 = k - l;
        int l1 = i + l;
        int i2 = j + l;
        int j2 = k + l;
        ChunkCache chunkcache = new ChunkCache(this, i1, j1, k1, l1, i2, j2);
        return (new Pathfinder(chunkcache)).a(entity, entity1, f);
    }

    public PathEntity a(Entity entity, int i, int j, int k, float f)
    {
        int l = MathHelper.floor(entity.locX);
        int i1 = MathHelper.floor(entity.locY);
        int j1 = MathHelper.floor(entity.locZ);
        int k1 = (int)(f + 8F);
        int l1 = l - k1;
        int i2 = i1 - k1;
        int j2 = j1 - k1;
        int k2 = l + k1;
        int l2 = i1 + k1;
        int i3 = j1 + k1;
        ChunkCache chunkcache = new ChunkCache(this, l1, i2, j2, k2, l2, i3);
        return (new Pathfinder(chunkcache)).a(entity, i, j, k, f);
    }

    public boolean isBlockFacePowered(int i, int j, int k, int l)
    {
        int i1 = getTypeId(i, j, k);
        return i1 != 0 ? Block.byId[i1].d(this, i, j, k, l) : false;
    }

    public boolean isBlockPowered(int i, int j, int k)
    {
        return isBlockFacePowered(i, j - 1, k, 0) ? true : isBlockFacePowered(i, j + 1, k, 1) ? true : isBlockFacePowered(i, j, k - 1, 2) ? true : isBlockFacePowered(i, j, k + 1, 3) ? true : isBlockFacePowered(i - 1, j, k, 4) ? true : isBlockFacePowered(i + 1, j, k, 5);
    }

    public boolean isBlockFaceIndirectlyPowered(int i, int j, int k, int l)
    {
        if(e(i, j, k))
        {
            return isBlockPowered(i, j, k);
        } else
        {
            int i1 = getTypeId(i, j, k);
            return i1 != 0 ? Block.byId[i1].a(this, i, j, k, l) : false;
        }
    }

    public boolean isBlockIndirectlyPowered(int i, int j, int k)
    {
        return isBlockFaceIndirectlyPowered(i, j - 1, k, 0) ? true : isBlockFaceIndirectlyPowered(i, j + 1, k, 1) ? true : isBlockFaceIndirectlyPowered(i, j, k - 1, 2) ? true : isBlockFaceIndirectlyPowered(i, j, k + 1, 3) ? true : isBlockFaceIndirectlyPowered(i - 1, j, k, 4) ? true : isBlockFaceIndirectlyPowered(i + 1, j, k, 5);
    }

    public EntityHuman findNearbyPlayer(Entity entity, double d0)
    {
        return a(entity.locX, entity.locY, entity.locZ, d0);
    }

    public EntityHuman a(double d0, double d1, double d2, double d3)
    {
        double d4 = -1D;
        EntityHuman entityhuman = null;
        for(int i = 0; i < players.size(); i++)
        {
            EntityHuman entityhuman1 = (EntityHuman)players.get(i);
            if(entityhuman1 == null || entityhuman1.dead)
                continue;
            double d5 = entityhuman1.e(d0, d1, d2);
            if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
            {
                d4 = d5;
                entityhuman = entityhuman1;
            }
        }

        return entityhuman;
    }

    public EntityHuman a(String s)
    {
        for(int i = 0; i < players.size(); i++)
            if(s.equals(((EntityHuman)players.get(i)).name))
                return (EntityHuman)players.get(i);

        return null;
    }

    public byte[] getMultiChunkData(int i, int j, int k, int l, int i1, int j1)
    {
        byte abyte[] = new byte[(l * i1 * j1 * 5) / 2];
        int k1 = i >> 4;
        int l1 = k >> 4;
        int i2 = (i + l) - 1 >> 4;
        int j2 = (k + j1) - 1 >> 4;
        int k2 = 0;
        int l2 = j;
        int i3 = j + i1;
        if(j < 0)
            l2 = 0;
        if(i3 > 128)
            i3 = 128;
        for(int j3 = k1; j3 <= i2; j3++)
        {
            int k3 = i - j3 * 16;
            int l3 = (i + l) - j3 * 16;
            if(k3 < 0)
                k3 = 0;
            if(l3 > 16)
                l3 = 16;
            for(int i4 = l1; i4 <= j2; i4++)
            {
                int j4 = k - i4 * 16;
                int k4 = (k + j1) - i4 * 16;
                if(j4 < 0)
                    j4 = 0;
                if(k4 > 16)
                    k4 = 16;
                k2 = getChunkAt(j3, i4).getData(abyte, k3, l2, j4, l3, i3, k4, k2);
            }

        }

        return abyte;
    }

    public void j()
    {
        B.b();
    }

    public void setTime(long i)
    {
        worldData.a(i);
    }

    public void setTimeAndFixTicklists(long i)
    {
        long j = i - worldData.f();
        for(Iterator iterator = O.iterator(); iterator.hasNext();)
        {
            NextTickListEntry nextticklistentry = (NextTickListEntry)iterator.next();
            nextticklistentry.e += j;
        }

        setTime(i);
    }

    public long getSeed()
    {
        return worldData.getSeed();
    }

    public long getTime()
    {
        return worldData.f();
    }

    public ChunkCoordinates getSpawn()
    {
        return new ChunkCoordinates(worldData.c(), worldData.d(), worldData.e());
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int i1)
    {
        return true;
    }

    public void a(Entity entity1, byte byte0)
    {
    }

    public IChunkProvider n()
    {
        return chunkProvider;
    }

    public void playNote(int i, int j, int k, int l, int i1)
    {
        int j1 = getTypeId(i, j, k);
        if(j1 > 0)
            Block.byId[j1].a(this, i, j, k, l, i1);
    }

    public IDataManager o()
    {
        return B;
    }

    public WorldData p()
    {
        return worldData;
    }

    public void everyoneSleeping()
    {
        T = !players.isEmpty();
        Iterator iterator = players.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            EntityHuman entityhuman = (EntityHuman)iterator.next();
            if(entityhuman.isSleeping() || entityhuman.fauxSleeping)
                continue;
            T = false;
            break;
        } while(true);
    }

    public void checkSleepStatus()
    {
        if(!isStatic)
            everyoneSleeping();
    }

    protected void r()
    {
        T = false;
        Iterator iterator = players.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            EntityHuman entityhuman = (EntityHuman)iterator.next();
            if(entityhuman.isSleeping())
                entityhuman.a(false, false, true);
        } while(true);
        y();
    }

    public boolean everyoneDeeplySleeping()
    {
        if(T && !isStatic)
        {
            Iterator iterator = players.iterator();
            boolean foundActualSleepers = false;
            EntityHuman entityhuman;
            do
            {
                if(!iterator.hasNext())
                    return foundActualSleepers;
                entityhuman = (EntityHuman)iterator.next();
                if(entityhuman.isDeeplySleeping())
                    foundActualSleepers = true;
            } while(entityhuman.isDeeplySleeping() || entityhuman.fauxSleeping);
            return false;
        } else
        {
            return false;
        }
    }

    public float c(float f)
    {
        return (p + (q - p) * f) * d(f);
    }

    public float d(float f)
    {
        return n + (o - n) * f;
    }

    public boolean t()
    {
        return (double)c(1.0F) > 0.90000000000000002D;
    }

    public boolean u()
    {
        return (double)d(1.0F) > 0.20000000000000001D;
    }

    public boolean s(int i, int j, int k)
    {
        if(!u())
            return false;
        if(!isChunkLoaded(i, j, k))
            return false;
        if(e(i, k) > j)
        {
            return false;
        } else
        {
            BiomeBase biomebase = getWorldChunkManager().getBiome(i, k);
            return biomebase.b() ? false : biomebase.c();
        }
    }

    public void a(String s, WorldMapBase worldmapbase)
    {
        worldMaps.a(s, worldmapbase);
    }

    public WorldMapBase a(Class oclass, String s)
    {
        return worldMaps.a(oclass, s);
    }

    public int b(String s)
    {
        return worldMaps.a(s);
    }

    public void e(int i, int j, int k, int l, int i1)
    {
        a((EntityHuman)null, i, j, k, l, i1);
    }

    public void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1)
    {
        for(int j1 = 0; j1 < z.size(); j1++)
            ((IWorldAccess)z.get(j1)).a(entityhuman, i, j, k, l, i1);

    }

    public Random t(int i, int j, int k)
    {
        long l = (long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L + p().getSeed() + (long)k;
        random.setSeed(l);
        return random;
    }

    public boolean v()
    {
        return false;
    }

    public void a(EnumSkyBlock enumskyblock1, int k1, int l1, int i2, int j2, int k2, int l2)
    {
    }

    public UUID getUUID()
    {
        return B.getUUID();
    }

    public final int heightBits = 7;
    public final int heightBitsPlusFour = 11;
    public final int height = 128;
    public final int heightMinusOne = 127;
    public final int seaLevel = 63;
    public boolean f;
    public List entityList;
    private List M;
    private TreeSet N;
    private Set O;
    public List h;
    private List P;
    private List Q;
    public List players;
    public List j;
    private long R;
    public int k;
    protected int l;
    protected final int m = 0x3c6ef35f;
    protected float n;
    protected float o;
    protected float p;
    protected float q;
    protected int r;
    public int s;
    public boolean suppressPhysics;
    private long S;
    protected int u;
    public int difficulty;
    public Random random;
    public boolean x;
    public WorldProvider worldProvider;
    protected List z;
    public IChunkProvider chunkProvider;
    protected final IDataManager B;
    public WorldData worldData;
    public boolean isLoading;
    private boolean T;
    public WorldMapCollection worldMaps;
    private ArrayList U;
    private boolean V;
    public boolean allowMonsters;
    public boolean allowAnimals;
    private Set W;
    private int X;
    int H[];
    private List Y;
    public boolean isStatic;
    public double J;
    public double K;
    public double L;
    private final CraftWorld world;
    public boolean pvpMode;
    public boolean keepSpawnInMemory;
    public ChunkGenerator generator;
    Chunk lastChunkAccessed;
    int lastXAccessed;
    int lastZAccessed;
    final Object chunkLock = new Object();
}
