// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlayerManager.java

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            PlayerList, PlayerInstance, MinecraftServer, EntityPlayer, 
//            WorldServer, ChunkCoordIntPair

public class PlayerManager
{

    public PlayerManager(MinecraftServer minecraftserver, int i, int j)
    {
        managedPlayers = new ArrayList();
        b = new PlayerList();
        c = new ArrayList();
        if(j > 15)
            throw new IllegalArgumentException("Too big view radius!");
        if(j < 3)
        {
            throw new IllegalArgumentException("Too small view radius!");
        } else
        {
            f = j;
            server = minecraftserver;
            e = i;
            return;
        }
    }

    public WorldServer a()
    {
        return server.getWorldServer(e);
    }

    public void flush()
    {
        for(int i = 0; i < c.size(); i++)
            ((PlayerInstance)c.get(i)).a();

        c.clear();
    }

    private PlayerInstance a(int i, int j, boolean flag)
    {
        long k = (long)i + 0x7fffffffL | (long)j + 0x7fffffffL << 32;
        PlayerInstance playerinstance = (PlayerInstance)b.getEntry(k);
        if(playerinstance == null && flag)
        {
            playerinstance = new PlayerInstance(this, i, j);
            b.put(k, playerinstance);
        }
        return playerinstance;
    }

    public void flagDirty(int i, int j, int k)
    {
        int l = i >> 4;
        int i1 = k >> 4;
        PlayerInstance playerinstance = a(l, i1, false);
        if(playerinstance != null)
            playerinstance.a(i & 0xf, j, k & 0xf);
    }

    public void addPlayer(EntityPlayer entityplayer)
    {
        int i = (int)entityplayer.locX >> 4;
        int j = (int)entityplayer.locZ >> 4;
        entityplayer.d = entityplayer.locX;
        entityplayer.e = entityplayer.locZ;
        int k = 0;
        int l = f;
        int i1 = 0;
        int j1 = 0;
        a(i, j, true).a(entityplayer);
        for(int k1 = 1; k1 <= l * 2; k1++)
        {
            for(int l1 = 0; l1 < 2; l1++)
            {
                int aint[] = g[k++ % 4];
                for(int i2 = 0; i2 < k1; i2++)
                {
                    i1 += aint[0];
                    j1 += aint[1];
                    a(i + i1, j + j1, true).a(entityplayer);
                }

            }

        }

        k %= 4;
        for(int k1 = 0; k1 < l * 2; k1++)
        {
            i1 += g[k][0];
            j1 += g[k][1];
            a(i + i1, j + j1, true).a(entityplayer);
        }

        managedPlayers.add(entityplayer);
    }

    public void removePlayer(EntityPlayer entityplayer)
    {
        int i = (int)entityplayer.d >> 4;
        int j = (int)entityplayer.e >> 4;
        for(int k = i - f; k <= i + f; k++)
        {
            for(int l = j - f; l <= j + f; l++)
            {
                PlayerInstance playerinstance = a(k, l, false);
                if(playerinstance != null)
                    playerinstance.b(entityplayer);
            }

        }

        managedPlayers.remove(entityplayer);
    }

    private boolean a(int i, int j, int k, int l)
    {
        int i1 = i - k;
        int j1 = j - l;
        return i1 < -f || i1 > f ? false : j1 >= -f && j1 <= f;
    }

    public void movePlayer(EntityPlayer entityplayer)
    {
        int i = (int)entityplayer.locX >> 4;
        int j = (int)entityplayer.locZ >> 4;
        double d0 = entityplayer.d - entityplayer.locX;
        double d1 = entityplayer.e - entityplayer.locZ;
        double d2 = d0 * d0 + d1 * d1;
        if(d2 >= 64D)
        {
            int k = (int)entityplayer.d >> 4;
            int l = (int)entityplayer.e >> 4;
            int i1 = i - k;
            int j1 = j - l;
            if(i1 != 0 || j1 != 0)
            {
                for(int k1 = i - f; k1 <= i + f; k1++)
                {
                    for(int l1 = j - f; l1 <= j + f; l1++)
                    {
                        if(!a(k1, l1, k, l))
                            a(k1, l1, true).a(entityplayer);
                        if(a(k1 - i1, l1 - j1, i, j))
                            continue;
                        PlayerInstance playerinstance = a(k1 - i1, l1 - j1, false);
                        if(playerinstance != null)
                            playerinstance.b(entityplayer);
                    }

                }

                entityplayer.d = entityplayer.locX;
                entityplayer.e = entityplayer.locZ;
                if(i1 > 1 || i1 < -1 || j1 > 1 || j1 < -1)
                {
                    final int x = i;
                    final int z = j;
                    List chunksToSend = entityplayer.chunkCoordIntPairQueue;
                    Collections.sort(chunksToSend, new Comparator() {

                        public int compare(ChunkCoordIntPair a, ChunkCoordIntPair b)
                        {
                            return Math.max(Math.abs(a.x - x), Math.abs(a.z - z)) - Math.max(Math.abs(b.x - x), Math.abs(b.z - z));
                        }

                        public volatile int compare(Object x0, Object x1)
                        {
                            return compare((ChunkCoordIntPair)x0, (ChunkCoordIntPair)x1);
                        }

                        final int val$x;
                        final int val$z;
                        final PlayerManager this$0;

            
            {
                this$0 = PlayerManager.this;
                x = i;
                z = j;
                super();
            }
                    }
);
                }
            }
        }
    }

    public int getFurthestViewableBlock()
    {
        return f * 16 - 16;
    }

    static PlayerList a(PlayerManager playermanager)
    {
        return playermanager.b;
    }

    static List b(PlayerManager playermanager)
    {
        return playermanager.c;
    }

    public List managedPlayers;
    private PlayerList b;
    private List c;
    private MinecraftServer server;
    private int e;
    private int f;
    private final int g[][] = {
        {
            1, 0
        }, {
            0, 1
        }, {
            -1, 0
        }, {
            0, -1
        }
    };
}
