// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SecondaryWorldServer.java

package net.minecraft.server;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

// Referenced classes of package net.minecraft.server:
//            WorldServer, MinecraftServer, IDataManager, WorldSettings

public class SecondaryWorldServer extends WorldServer
{

    public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, WorldServer worldserver, org.bukkit.World.Environment env, 
            ChunkGenerator gen)
    {
        super(minecraftserver, idatamanager, s, i, worldsettings, env, gen);
        worldMaps = worldserver.worldMaps;
    }
}
