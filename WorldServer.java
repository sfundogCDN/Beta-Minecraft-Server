// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldServer.java

package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.generator.*;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            World, EntityList, PlayerManager, TileEntityChest, 
//            TileEntityFurnace, TileEntityDispenser, TileEntityRecordPlayer, TileEntityNote, 
//            TileEntityMobSpawner, TileEntitySign, BlockContainer, EntityHuman, 
//            WorldProviderHell, WorldProviderSky, ChunkProviderServer, TileEntity, 
//            Entity, Packet71Weather, Packet38EntityStatus, Packet60Explosion, 
//            Packet54PlayNoteBlock, EntityPlayer, Packet70Bed, WorldProvider, 
//            MinecraftServer, PropertyManager, Block, IDataManager, 
//            WorldData, MathHelper, ServerConfigurationManager, EntityTracker, 
//            Explosion, NetServerHandler, WorldSettings, IChunkProvider

public class WorldServer extends net.minecraft.server.World
    implements BlockChangeDelegate
{

    public WorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, org.bukkit.World.Environment env, ChunkGenerator gen)
    {
        super(idatamanager, s, worldsettings, WorldProvider.byDimension(env.getId()), gen, env);
        weirdIsOpCache = false;
        Q = new EntityList();
        server = minecraftserver;
        dimension = i;
        pvpMode = minecraftserver.pvpMode;
        manager = new PlayerManager(minecraftserver, dimension, minecraftserver.propertyManager.getInt("view-distance", 10));
    }

    public int getHeight()
    {
        return 128;
    }

    public TileEntity getTileEntity(int i, int j, int k)
    {
        TileEntity result = super.getTileEntity(i, j, k);
        int type = getTypeId(i, j, k);
        if(type == Block.CHEST.id)
        {
            if(!(result instanceof TileEntityChest))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if(type == Block.FURNACE.id)
        {
            if(!(result instanceof TileEntityFurnace))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if(type == Block.DISPENSER.id)
        {
            if(!(result instanceof TileEntityDispenser))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if(type == Block.JUKEBOX.id)
        {
            if(!(result instanceof TileEntityRecordPlayer))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if(type == Block.NOTE_BLOCK.id)
        {
            if(!(result instanceof TileEntityNote))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if(type == Block.MOB_SPAWNER.id)
        {
            if(!(result instanceof TileEntityMobSpawner))
                result = fixTileEntity(i, j, k, type, result);
        } else
        if((type == Block.SIGN_POST.id || type == Block.WALL_SIGN.id) && !(result instanceof TileEntitySign))
            result = fixTileEntity(i, j, k, type, result);
        return result;
    }

    private TileEntity fixTileEntity(int x, int y, int z, int type, TileEntity found)
    {
        getServer().getLogger().severe((new StringBuilder()).append("Block at ").append(x).append(",").append(y).append(",").append(z).append(" is ").append(Material.getMaterial(type).toString()).append(" but has ").append(found).append(". ").append("Bukkit will attempt to fix this, but there may be additional damage that we cannot recover.").toString());
        if(Block.byId[type] instanceof BlockContainer)
        {
            TileEntity replacement = ((BlockContainer)Block.byId[type]).a_();
            setTileEntity(x, y, z, replacement);
            return replacement;
        } else
        {
            getServer().getLogger().severe("Don't know how to fix for this type... Can't do anything! :(");
            return found;
        }
    }

    public void entityJoinedWorld(Entity entity, boolean flag)
    {
        if(entity.passenger == null || !(entity.passenger instanceof EntityHuman))
            super.entityJoinedWorld(entity, flag);
    }

    public void vehicleEnteredWorld(Entity entity, boolean flag)
    {
        super.entityJoinedWorld(entity, flag);
    }

    protected IChunkProvider b()
    {
        IChunkLoader ichunkloader = B.a(worldProvider);
        org.bukkit.craftbukkit.generator.InternalChunkGenerator gen;
        if(generator != null)
            gen = new CustomChunkGenerator(this, getSeed(), generator);
        else
        if(worldProvider instanceof WorldProviderHell)
            gen = new NetherChunkGenerator(this, getSeed());
        else
        if(worldProvider instanceof WorldProviderSky)
            gen = new SkyLandsChunkGenerator(this, getSeed());
        else
            gen = new NormalChunkGenerator(this, getSeed());
        chunkProviderServer = new ChunkProviderServer(this, ichunkloader, gen);
        return chunkProviderServer;
    }

    public List getTileEntities(int i, int j, int k, int l, int i1, int j1)
    {
        ArrayList arraylist = new ArrayList();
        for(int k1 = 0; k1 < h.size(); k1++)
        {
            TileEntity tileentity = (TileEntity)h.get(k1);
            if(tileentity.x >= i && tileentity.y >= j && tileentity.z >= k && tileentity.x < l && tileentity.y < i1 && tileentity.z < j1)
                arraylist.add(tileentity);
        }

        return arraylist;
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int k)
    {
        int l = MathHelper.a(i - worldData.c());
        int i1 = MathHelper.a(k - worldData.e());
        if(l > i1)
            i1 = l;
        return i1 > getServer().getSpawnRadius() || server.serverConfigurationManager.isOp(entityhuman.name);
    }

    protected void c(Entity entity)
    {
        super.c(entity);
        Q.a(entity.id, entity);
    }

    protected void d(Entity entity)
    {
        super.d(entity);
        Q.d(entity.id);
    }

    public Entity getEntity(int i)
    {
        return (Entity)Q.a(i);
    }

    public boolean strikeLightning(Entity entity)
    {
        LightningStrikeEvent lightning = new LightningStrikeEvent(getWorld(), (LightningStrike)entity.getBukkitEntity());
        getServer().getPluginManager().callEvent(lightning);
        if(lightning.isCancelled())
            return false;
        if(super.strikeLightning(entity))
        {
            server.serverConfigurationManager.sendPacketNearby(entity.locX, entity.locY, entity.locZ, 512D, dimension, new Packet71Weather(entity));
            return true;
        } else
        {
            return false;
        }
    }

    public void a(Entity entity, byte b0)
    {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);
        server.getTracker(dimension).sendPacketToEntity(entity, packet38entitystatus);
    }

    public Explosion createExplosion(Entity entity, double d0, double d1, double d2, 
            float f, boolean flag)
    {
        Explosion explosion = super.createExplosion(entity, d0, d1, d2, f, flag);
        if(explosion.wasCanceled)
        {
            return explosion;
        } else
        {
            server.serverConfigurationManager.sendPacketNearby(d0, d1, d2, 64D, dimension, new Packet60Explosion(d0, d1, d2, f, explosion.blocks));
            return explosion;
        }
    }

    public void playNote(int i, int j, int k, int l, int i1)
    {
        super.playNote(i, j, k, l, i1);
        server.serverConfigurationManager.sendPacketNearby(i, j, k, 64D, dimension, new Packet54PlayNoteBlock(i, j, k, l, i1));
    }

    public void saveLevel()
    {
        B.e();
    }

    protected void h()
    {
        boolean flag = u();
        super.h();
        if(flag != u())
        {
            for(int i = 0; i < players.size(); i++)
                if(((EntityPlayer)players.get(i)).world == this)
                    ((EntityPlayer)players.get(i)).netServerHandler.sendPacket(new Packet70Bed(flag ? 2 : 1, 0));

        }
    }

    public ChunkProviderServer chunkProviderServer;
    public boolean weirdIsOpCache;
    public boolean savingDisabled;
    public final MinecraftServer server;
    private EntityList Q;
    public final int dimension;
    public EntityTracker tracker;
    public PlayerManager manager;
}
