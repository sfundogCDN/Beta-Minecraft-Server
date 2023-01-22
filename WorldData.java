// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldData.java

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            NBTTagCompound, EntityHuman, WorldSettings

public class WorldData
{

    public WorldData(NBTTagCompound nbttagcompound)
    {
        seed = nbttagcompound.getLong("RandomSeed");
        gameType = nbttagcompound.e("GameType");
        if(nbttagcompound.hasKey("MapFeatures"))
            useMapFeatures = nbttagcompound.m("MapFeatures");
        else
            useMapFeatures = true;
        spawnX = nbttagcompound.e("SpawnX");
        spawnY = nbttagcompound.e("SpawnY");
        spawnZ = nbttagcompound.e("SpawnZ");
        time = nbttagcompound.getLong("Time");
        lastPlayed = nbttagcompound.getLong("LastPlayed");
        sizeOnDisk = nbttagcompound.getLong("SizeOnDisk");
        name = nbttagcompound.getString("LevelName");
        version = nbttagcompound.e("version");
        rainTicks = nbttagcompound.e("rainTime");
        isRaining = nbttagcompound.m("raining");
        thunderTicks = nbttagcompound.e("thunderTime");
        isThundering = nbttagcompound.m("thundering");
        if(nbttagcompound.hasKey("Player"))
        {
            playerData = nbttagcompound.k("Player");
            dimension = playerData.e("Dimension");
        }
    }

    public WorldData(WorldSettings worldsettings, String s)
    {
        seed = worldsettings.a();
        gameType = worldsettings.b();
        useMapFeatures = worldsettings.c();
        name = s;
    }

    public WorldData(WorldData worlddata)
    {
        seed = worlddata.seed;
        gameType = worlddata.gameType;
        useMapFeatures = worlddata.useMapFeatures;
        spawnX = worlddata.spawnX;
        spawnY = worlddata.spawnY;
        spawnZ = worlddata.spawnZ;
        time = worlddata.time;
        lastPlayed = worlddata.lastPlayed;
        sizeOnDisk = worlddata.sizeOnDisk;
        playerData = worlddata.playerData;
        dimension = worlddata.dimension;
        name = worlddata.name;
        version = worlddata.version;
        rainTicks = worlddata.rainTicks;
        isRaining = worlddata.isRaining;
        thunderTicks = worlddata.thunderTicks;
        isThundering = worlddata.isThundering;
    }

    public NBTTagCompound a()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        a(nbttagcompound, playerData);
        return nbttagcompound;
    }

    public NBTTagCompound a(List list)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        EntityHuman entityhuman = null;
        NBTTagCompound nbttagcompound1 = null;
        if(list.size() > 0)
            entityhuman = (EntityHuman)list.get(0);
        if(entityhuman != null)
        {
            nbttagcompound1 = new NBTTagCompound();
            entityhuman.d(nbttagcompound1);
        }
        a(nbttagcompound, nbttagcompound1);
        return nbttagcompound;
    }

    private void a(NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1)
    {
        nbttagcompound.setLong("RandomSeed", seed);
        nbttagcompound.a("GameType", gameType);
        nbttagcompound.a("MapFeatures", useMapFeatures);
        nbttagcompound.a("SpawnX", spawnX);
        nbttagcompound.a("SpawnY", spawnY);
        nbttagcompound.a("SpawnZ", spawnZ);
        nbttagcompound.setLong("Time", time);
        nbttagcompound.setLong("SizeOnDisk", sizeOnDisk);
        nbttagcompound.setLong("LastPlayed", System.currentTimeMillis());
        nbttagcompound.setString("LevelName", name);
        nbttagcompound.a("version", version);
        nbttagcompound.a("rainTime", rainTicks);
        nbttagcompound.a("raining", isRaining);
        nbttagcompound.a("thunderTime", thunderTicks);
        nbttagcompound.a("thundering", isThundering);
        if(nbttagcompound1 != null)
            nbttagcompound.a("Player", nbttagcompound1);
    }

    public long getSeed()
    {
        return seed;
    }

    public int c()
    {
        return spawnX;
    }

    public int d()
    {
        return spawnY;
    }

    public int e()
    {
        return spawnZ;
    }

    public long f()
    {
        return time;
    }

    public long g()
    {
        return sizeOnDisk;
    }

    public int h()
    {
        return dimension;
    }

    public void a(long i)
    {
        time = i;
    }

    public void b(long i)
    {
        sizeOnDisk = i;
    }

    public void setSpawn(int i, int j, int k)
    {
        spawnX = i;
        spawnY = j;
        spawnZ = k;
    }

    public void a(String s)
    {
        name = s;
    }

    public int i()
    {
        return version;
    }

    public void a(int i)
    {
        version = i;
    }

    public boolean isThundering()
    {
        return isThundering;
    }

    public void setThundering(boolean flag)
    {
        isThundering = flag;
    }

    public int getThunderDuration()
    {
        return thunderTicks;
    }

    public void setThunderDuration(int i)
    {
        thunderTicks = i;
    }

    public boolean hasStorm()
    {
        return isRaining;
    }

    public void setStorm(boolean flag)
    {
        isRaining = flag;
    }

    public int getWeatherDuration()
    {
        return rainTicks;
    }

    public void setWeatherDuration(int i)
    {
        rainTicks = i;
    }

    public int getGameType()
    {
        return gameType;
    }

    public boolean o()
    {
        return useMapFeatures;
    }

    public void setGameType(int i)
    {
        gameType = i;
    }

    private long seed;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long time;
    private long lastPlayed;
    private long sizeOnDisk;
    private NBTTagCompound playerData;
    private int dimension;
    public String name;
    private int version;
    private boolean isRaining;
    private int rainTicks;
    private boolean isThundering;
    private int thunderTicks;
    private int gameType;
    private boolean useMapFeatures;
}
