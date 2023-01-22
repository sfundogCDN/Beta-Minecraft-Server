// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlayerInstance.java

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            ChunkCoordIntPair, Packet50PreChunk, EntityPlayer, Packet53BlockChange, 
//            Packet51MapChunk, TileEntity, Packet52MultiBlockChange, PlayerManager, 
//            WorldServer, ChunkProviderServer, NetServerHandler, PlayerList, 
//            Block, Packet

class PlayerInstance
{

    public PlayerInstance(PlayerManager playermanager, int i, int j)
    {
        playerManager = playermanager;
        b = new ArrayList();
        dirtyBlocks = new short[10];
        dirtyCount = 0;
        chunkX = i;
        chunkZ = j;
        location = new ChunkCoordIntPair(i, j);
        playermanager.a().chunkProviderServer.getChunkAt(i, j);
    }

    public void a(EntityPlayer entityplayer)
    {
        if(b.contains(entityplayer))
            throw new IllegalStateException((new StringBuilder()).append("Failed to add player. ").append(entityplayer).append(" already is in chunk ").append(chunkX).append(", ").append(chunkZ).toString());
        if(entityplayer.playerChunkCoordIntPairs.add(location))
            entityplayer.netServerHandler.sendPacket(new Packet50PreChunk(location.x, location.z, true));
        b.add(entityplayer);
        entityplayer.chunkCoordIntPairQueue.add(location);
    }

    public void b(EntityPlayer entityplayer)
    {
        if(b.contains(entityplayer))
        {
            b.remove(entityplayer);
            if(b.size() == 0)
            {
                long i = (long)chunkX + 0x7fffffffL | (long)chunkZ + 0x7fffffffL << 32;
                PlayerManager.a(playerManager).remove(i);
                if(dirtyCount > 0)
                    PlayerManager.b(playerManager).remove(this);
                playerManager.a().chunkProviderServer.queueUnload(chunkX, chunkZ);
            }
            entityplayer.chunkCoordIntPairQueue.remove(location);
            if(entityplayer.playerChunkCoordIntPairs.remove(location))
                entityplayer.netServerHandler.sendPacket(new Packet50PreChunk(chunkX, chunkZ, false));
        }
    }

    public void a(int i, int j, int k)
    {
        if(dirtyCount == 0)
        {
            PlayerManager.b(playerManager).add(this);
            h = this.i = i;
            this.j = this.k = j;
            this.l = m = k;
        }
        if(h > i)
            h = i;
        if(this.i < i)
            this.i = i;
        if(this.j > j)
            this.j = j;
        if(this.k < j)
            this.k = j;
        if(this.l > k)
            this.l = k;
        if(m < k)
            m = k;
        if(dirtyCount < 10)
        {
            short short1 = (short)(i << 12 | k << 8 | j);
            for(int l = 0; l < dirtyCount; l++)
                if(dirtyBlocks[l] == short1)
                    return;

            dirtyBlocks[dirtyCount++] = short1;
        }
    }

    public void sendAll(Packet packet)
    {
        for(int i = 0; i < b.size(); i++)
        {
            EntityPlayer entityplayer = (EntityPlayer)b.get(i);
            if(entityplayer.playerChunkCoordIntPairs.contains(location))
                entityplayer.netServerHandler.sendPacket(packet);
        }

    }

    public void a()
    {
        WorldServer worldserver = playerManager.a();
        if(dirtyCount != 0)
        {
            if(dirtyCount == 1)
            {
                int i = chunkX * 16 + h;
                int j = this.j;
                int k = chunkZ * 16 + this.l;
                sendAll(new Packet53BlockChange(i, j, k, worldserver));
                if(Block.isTileEntity[worldserver.getTypeId(i, j, k)])
                    sendTileEntity(worldserver.getTileEntity(i, j, k));
            } else
            if(dirtyCount == 10)
            {
                this.j = (this.j / 2) * 2;
                this.k = (this.k / 2 + 1) * 2;
                int i = h + chunkX * 16;
                int j = this.j;
                int k = this.l + chunkZ * 16;
                int l = (this.i - h) + 1;
                int i1 = (this.k - this.j) + 2;
                int j1 = (m - this.l) + 1;
                sendAll(new Packet51MapChunk(i, j, k, l, i1, j1, worldserver));
                List list = worldserver.getTileEntities(i, j, k, i + l, j + i1, k + j1);
                for(int k1 = 0; k1 < list.size(); k1++)
                    sendTileEntity((TileEntity)list.get(k1));

            } else
            {
                sendAll(new Packet52MultiBlockChange(chunkX, chunkZ, dirtyBlocks, dirtyCount, worldserver));
                for(int i = 0; i < dirtyCount; i++)
                {
                    int j = chunkX * 16 + (dirtyBlocks[i] >> 12 & 0xf);
                    int k = dirtyBlocks[i] & 0xff;
                    int l = chunkZ * 16 + (dirtyBlocks[i] >> 8 & 0xf);
                    if(Block.isTileEntity[worldserver.getTypeId(j, k, l)])
                        sendTileEntity(worldserver.getTileEntity(j, k, l));
                }

            }
            dirtyCount = 0;
        }
    }

    private void sendTileEntity(TileEntity tileentity)
    {
        if(tileentity != null)
        {
            Packet packet = tileentity.l();
            if(packet != null)
                sendAll(packet);
        }
    }

    private List b;
    private int chunkX;
    private int chunkZ;
    private ChunkCoordIntPair location;
    private short dirtyBlocks[];
    private int dirtyCount;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    final PlayerManager playerManager;
}
