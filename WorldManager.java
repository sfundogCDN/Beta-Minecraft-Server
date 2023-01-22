// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldManager.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Packet61, IWorldAccess, WorldServer, MinecraftServer, 
//            EntityTracker, ServerConfigurationManager, Entity, TileEntity, 
//            EntityHuman

public class WorldManager
    implements IWorldAccess
{

    public WorldManager(MinecraftServer minecraftserver, WorldServer worldserver)
    {
        server = minecraftserver;
        world = worldserver;
    }

    public void a(String s1, double d, double d6, double d7, 
            double d8, double d9, double d10)
    {
    }

    public void a(Entity entity)
    {
        server.getTracker(world.dimension).track(entity);
    }

    public void b(Entity entity)
    {
        server.getTracker(world.dimension).untrackEntity(entity);
    }

    public void a(String s1, double d, double d3, double d4, 
            float f2, float f3)
    {
    }

    public void a(int k1, int l1, int i2, int j2, int k2, int l2)
    {
    }

    public void a(int i, int j, int k)
    {
        server.serverConfigurationManager.flagDirty(i, j, k, world.dimension);
    }

    public void a(String s1, int l, int i1, int j1)
    {
    }

    public void a(int i, int j, int k, TileEntity tileentity)
    {
        server.serverConfigurationManager.a(i, j, k, tileentity);
    }

    public void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1)
    {
        server.serverConfigurationManager.sendPacketNearby(entityhuman, j, k, l, 64D, world.dimension, new Packet61(i, j, k, l, i1));
    }

    private MinecraftServer server;
    public WorldServer world;
}
