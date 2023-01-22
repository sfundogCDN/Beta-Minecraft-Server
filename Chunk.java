// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chunk.java

package net.minecraft.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.craftbukkit.CraftWorld;

// Referenced classes of package net.minecraft.server:
//            EmptyChunk, NibbleArray, BlockContainer, ChunkPosition, 
//            TileEntity, Entity, EntityPlayer, World, 
//            Block, WorldProvider, EnumSkyBlock, MathHelper, 
//            AxisAlignedBB, BlockRegister, IChunkProvider, Material

public class Chunk
{

    public Chunk(World world, int i, int j)
    {
        c = new int[256];
        d = new boolean[256];
        tileEntities = new HashMap();
        done = false;
        q = false;
        s = false;
        t = 0L;
        u = false;
        world.getClass();
        entitySlices = new List[8];
        this.world = world;
        x = i;
        z = j;
        heightMap = new byte[256];
        for(int k = 0; k < entitySlices.length; k++)
            entitySlices[k] = new ArrayList();

        Arrays.fill(c, -999);
        if(!(this instanceof EmptyChunk))
        {
            CraftWorld cworld = this.world.getWorld();
            bukkitChunk = cworld != null ? cworld.popPreservedChunk(i, j) : null;
            if(bukkitChunk == null)
                bukkitChunk = new CraftChunk(this);
        }
    }

    public Chunk(World world, byte abyte[], int i, int j)
    {
        this(world, i, j);
        b = abyte;
        int k = abyte.length;
        g = new NibbleArray(k, 7);
        h = new NibbleArray(k, 7);
        this.i = new NibbleArray(k, 7);
    }

    public boolean a(int i, int j)
    {
        return i == x && j == z;
    }

    public int b(int i, int j)
    {
        return heightMap[j << 4 | i] & 0xff;
    }

    public void a()
    {
    }

    public void initLighting()
    {
        int i = 127;
        for(int j = 0; j < 16; j++)
        {
            for(int k = 0; k < 16; k++)
            {
                int l = 127;
                int i1 = j << 11;
                int j1;
                for(j1 = i1 | k << 7; l > 0 && Block.q[b[(j1 + l) - 1] & 0xff] == 0; l--);
                heightMap[k << 4 | j] = (byte)l;
                if(l < i)
                    i = l;
                if(world.worldProvider.e)
                    continue;
                int k1 = 15;
                int l1 = 127;
                do
                {
                    k1 -= Block.q[b[j1 + l1] & 0xff];
                    if(k1 > 0)
                        h.a(j, l1, k, k1);
                } while(--l1 > 0 && k1 > 0);
            }

        }

        this.k = i;
        for(int j = 0; j < 16; j++)
        {
            for(int k = 0; k < 16; k++)
                d(j, k);

        }

        q = true;
    }

    public void loadNOP()
    {
    }

    private void d(int i, int j)
    {
        d[i + j * 16] = true;
    }

    private void i()
    {
        World j0000 = world;
        int j0001 = x * 16 + 8;
        if(j0000.areChunksLoaded(j0001, 64, z * 16 + 8, 16))
        {
            for(int j = 0; j < 16; j++)
            {
                for(int k = 0; k < 16; k++)
                {
                    if(!d[j + k * 16])
                        continue;
                    d[j + k * 16] = false;
                    int l = b(j, k);
                    int i1 = x * 16 + j;
                    int j1 = z * 16 + k;
                    int k1 = world.getHighestBlockYAt(i1 - 1, j1);
                    int l1 = world.getHighestBlockYAt(i1 + 1, j1);
                    int i2 = world.getHighestBlockYAt(i1, j1 - 1);
                    int j2 = world.getHighestBlockYAt(i1, j1 + 1);
                    if(l1 < k1)
                        k1 = l1;
                    if(i2 < k1)
                        k1 = i2;
                    if(j2 < k1)
                        k1 = j2;
                    u = true;
                    f(i1, j1, k1);
                    u = true;
                    f(i1 - 1, j1, l);
                    f(i1 + 1, j1, l);
                    f(i1, j1 - 1, l);
                    f(i1, j1 + 1, l);
                }

            }

        }
    }

    private void f(int i, int j, int k)
    {
        int l = world.getHighestBlockYAt(i, j);
        if(l > k)
            d(i, j, k, l + 1);
        else
        if(l < k)
            d(i, j, l, k + 1);
    }

    private void d(int i, int j, int k, int l)
    {
        if(l > k)
        {
            World world = this.world;
            if(world.areChunksLoaded(i, 64, j, 16))
            {
                for(int i1 = k; i1 < l; i1++)
                    this.world.b(EnumSkyBlock.SKY, i, i1, j);

                q = true;
            }
        }
    }

    private void g(int i, int j, int k)
    {
        int l = heightMap[k << 4 | i] & 0xff;
        int i1 = l;
        if(j > l)
            i1 = j;
        world.getClass();
        int j1 = i << 11;
        for(int k1 = j1 | k << 7; i1 > 0 && Block.q[b[(k1 + i1) - 1] & 0xff] == 0; i1--);
        if(i1 != l)
        {
            world.g(i, k, i1, l);
            heightMap[k << 4 | i] = (byte)i1;
            int l1;
            int i2;
            if(i1 < this.k)
            {
                this.k = i1;
            } else
            {
                l1 = 127;
                for(i2 = 0; i2 < 16; i2++)
                {
                    for(int j2 = 0; j2 < 16; j2++)
                        if((heightMap[j2 << 4 | i2] & 0xff) < l1)
                            l1 = heightMap[j2 << 4 | i2] & 0xff;

                }

                this.k = l1;
            }
            l1 = x * 16 + i;
            i2 = z * 16 + k;
            if(i1 < l)
            {
                for(int j2 = i1; j2 < l; j2++)
                    h.a(i, j2, k, 15);

            } else
            {
                for(int j2 = l; j2 < i1; j2++)
                    h.a(i, j2, k, 0);

            }
            for(int j2 = 15; i1 > 0 && j2 > 0;)
            {
                i1--;
                int k2 = Block.q[getTypeId(i, i1, k)];
                if(k2 == 0)
                    k2 = 1;
                j2 -= k2;
                if(j2 < 0)
                    j2 = 0;
                h.a(i, i1, k, j2);
            }

            byte b0 = heightMap[k << 4 | i];
            int l2 = l;
            int i3 = b0;
            if(b0 < l)
            {
                l2 = b0;
                i3 = l;
            }
            d(l1 - 1, i2, l2, i3);
            d(l1 + 1, i2, l2, i3);
            d(l1, i2 - 1, l2, i3);
            d(l1, i2 + 1, l2, i3);
            d(l1, i2, l2, i3);
            q = true;
        }
    }

    public int getTypeId(int i, int j, int k)
    {
        byte abyte[] = b;
        int l = i << 11;
        return abyte[l | k << 7 | j] & 0xff;
    }

    public boolean a(int i, int j, int k, int l, int i1)
    {
        byte b0 = (byte)l;
        int j1 = k << 4 | i;
        if(j >= c[j1] - 1)
            c[j1] = -999;
        int k1 = heightMap[k << 4 | i] & 0xff;
        byte j2000[] = b;
        int j2001 = i << 11;
        int i2 = j2000[j2001 | k << 7 | j] & 0xff;
        if(i2 == l && g.a(i, j, k) == i1)
            return false;
        int j2 = x * 16 + i;
        int k2 = z * 16 + k;
        j2000 = b;
        j2001 = i << 11;
        j2000[j2001 | k << 7 | j] = (byte)(b0 & 0xff);
        if(i2 != 0 && !world.isStatic)
            Block.byId[i2].remove(world, j2, j, k2);
        g.a(i, j, k, i1);
        if(!world.worldProvider.e)
        {
            if(Block.q[b0 & 0xff] != 0)
            {
                if(j >= k1)
                    g(i, j + 1, k);
            } else
            if(j == k1 - 1)
                g(i, j, k);
            world.a(EnumSkyBlock.SKY, j2, j, k2, j2, j, k2);
        }
        world.a(EnumSkyBlock.BLOCK, j2, j, k2, j2, j, k2);
        d(i, k);
        g.a(i, j, k, i1);
        if(l != 0)
        {
            if(!world.isStatic)
                Block.byId[l].a(world, j2, j, k2);
            if(Block.byId[l] instanceof BlockContainer)
            {
                TileEntity tileentity = d(i, j, k);
                if(tileentity == null)
                {
                    tileentity = ((BlockContainer)Block.byId[l]).a_();
                    world.setTileEntity(i, j, k, tileentity);
                }
                if(tileentity != null)
                    tileentity.g();
            }
        } else
        if(i2 > 0 && (Block.byId[i2] instanceof BlockContainer))
        {
            TileEntity tileentity = d(i, j, k);
            if(tileentity != null)
                tileentity.g();
        }
        q = true;
        return true;
    }

    public boolean a(int i, int j, int k, int l)
    {
        byte b0 = (byte)l;
        int i1 = k << 4 | i;
        if(j >= c[i1] - 1)
            c[i1] = -999;
        int j1 = heightMap[i1] & 0xff;
        byte j2000[] = b;
        int j2001 = i << 11;
        int l1 = j2000[j2001 | k << 7 | j] & 0xff;
        if(l1 == l)
            return false;
        int i2 = x * 16 + i;
        int j2 = z * 16 + k;
        j2000 = b;
        j2001 = i << 11;
        j2000[j2001 | k << 7 | j] = (byte)(b0 & 0xff);
        if(l1 != 0)
            Block.byId[l1].remove(world, i2, j, j2);
        g.a(i, j, k, 0);
        if(Block.q[b0 & 0xff] != 0)
        {
            if(j >= j1)
                g(i, j + 1, k);
        } else
        if(j == j1 - 1)
            g(i, j, k);
        world.a(EnumSkyBlock.SKY, i2, j, j2, i2, j, j2);
        world.a(EnumSkyBlock.BLOCK, i2, j, j2, i2, j, j2);
        d(i, k);
        if(l != 0)
        {
            if(!world.isStatic)
                Block.byId[l].a(world, i2, j, j2);
            if(l > 0 && (Block.byId[l] instanceof BlockContainer))
            {
                TileEntity tileentity = d(i, j, k);
                if(tileentity == null)
                {
                    tileentity = ((BlockContainer)Block.byId[l]).a_();
                    world.setTileEntity(i, j, k, tileentity);
                }
                if(tileentity != null)
                    tileentity.g();
            }
        } else
        if(l1 > 0 && (Block.byId[l1] instanceof BlockContainer))
        {
            TileEntity tileentity = d(i, j, k);
            if(tileentity != null)
                tileentity.g();
        }
        q = true;
        return true;
    }

    public int getData(int i, int j, int k)
    {
        return g.a(i, j, k);
    }

    public void b(int i, int j, int k, int l)
    {
        q = true;
        g.a(i, j, k, l);
        int i1 = getTypeId(i, j, k);
        if(i1 > 0 && (Block.byId[i1] instanceof BlockContainer))
        {
            TileEntity tileentity = d(i, j, k);
            if(tileentity != null)
            {
                tileentity.g();
                tileentity.n = l;
            }
        }
    }

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k)
    {
        return enumskyblock != EnumSkyBlock.SKY ? enumskyblock != EnumSkyBlock.BLOCK ? 0 : this.i.a(i, j, k) : h.a(i, j, k);
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l)
    {
        q = true;
        if(enumskyblock == EnumSkyBlock.SKY)
        {
            h.a(i, j, k, l);
        } else
        {
            if(enumskyblock != EnumSkyBlock.BLOCK)
                return;
            this.i.a(i, j, k, l);
        }
    }

    public int c(int i, int j, int k, int l)
    {
        int i1 = h.a(i, j, k);
        if(i1 > 0)
            a = true;
        i1 -= l;
        int j1 = this.i.a(i, j, k);
        if(j1 > i1)
            i1 = j1;
        return i1;
    }

    public void a(Entity entity)
    {
        s = true;
        int i = MathHelper.floor(entity.locX / 16D);
        int j = MathHelper.floor(entity.locZ / 16D);
        if(i != x || j != z)
        {
            Bukkit.getLogger().warning((new StringBuilder()).append("Wrong location for ").append(entity).append(" in world '").append(world.getWorld().getName()).append("'!").toString());
            Bukkit.getLogger().warning((new StringBuilder()).append("Entity is at ").append(entity.locX).append(",").append(entity.locZ).append(" (chunk ").append(i).append(",").append(j).append(") but was stored in chunk ").append(x).append(",").append(z).toString());
        }
        int k = MathHelper.floor(entity.locY / 16D);
        if(k < 0)
            k = 0;
        if(k >= entitySlices.length)
            k = entitySlices.length - 1;
        entity.bV = true;
        entity.bW = x;
        entity.bX = k;
        entity.bY = z;
        entitySlices[k].add(entity);
    }

    public void b(Entity entity)
    {
        a(entity, entity.bX);
    }

    public void a(Entity entity, int i)
    {
        if(i < 0)
            i = 0;
        if(i >= entitySlices.length)
            i = entitySlices.length - 1;
        entitySlices[i].remove(entity);
    }

    public boolean c(int i, int j, int k)
    {
        return j >= (heightMap[k << 4 | i] & 0xff);
    }

    public TileEntity d(int i, int j, int k)
    {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);
        TileEntity tileentity = (TileEntity)tileEntities.get(chunkposition);
        if(tileentity == null)
        {
            int l = getTypeId(i, j, k);
            if(!Block.isTileEntity[l])
                return null;
            if(tileentity == null)
            {
                tileentity = ((BlockContainer)Block.byId[l]).a_();
                world.setTileEntity(x * 16 + i, j, z * 16 + k, tileentity);
            }
            tileentity = (TileEntity)tileEntities.get(chunkposition);
        }
        if(tileentity != null && tileentity.m())
        {
            tileEntities.remove(chunkposition);
            return null;
        } else
        {
            return tileentity;
        }
    }

    public void a(TileEntity tileentity)
    {
        int i = tileentity.x - x * 16;
        int j = tileentity.y;
        int k = tileentity.z - z * 16;
        a(i, j, k, tileentity);
        if(e)
            world.h.add(tileentity);
    }

    public void a(int i, int j, int k, TileEntity tileentity)
    {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);
        tileentity.world = world;
        tileentity.x = x * 16 + i;
        tileentity.y = j;
        tileentity.z = z * 16 + k;
        if(getTypeId(i, j, k) != 0 && (Block.byId[getTypeId(i, j, k)] instanceof BlockContainer))
        {
            tileentity.n();
            tileEntities.put(chunkposition, tileentity);
        } else
        {
            System.out.println((new StringBuilder()).append("Attempted to place a tile entity (").append(tileentity).append(") at ").append(tileentity.x).append(",").append(tileentity.y).append(",").append(tileentity.z).append(" (").append(Material.getMaterial(getTypeId(i, j, k))).append(") where there was no entity tile!").toString());
        }
    }

    public void e(int i, int j, int k)
    {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);
        if(e)
        {
            TileEntity tileentity = (TileEntity)tileEntities.remove(chunkposition);
            if(tileentity != null)
                tileentity.i();
        }
    }

    public void addEntities()
    {
        e = true;
        world.a(tileEntities.values());
        for(int i = 0; i < entitySlices.length; i++)
            world.a(entitySlices[i]);

    }

    public void removeEntities()
    {
        e = false;
        TileEntity tileentity;
        for(Iterator iterator = tileEntities.values().iterator(); iterator.hasNext(); world.a(tileentity))
            tileentity = (TileEntity)iterator.next();

        for(int i = 0; i < entitySlices.length; i++)
        {
            Iterator iter = entitySlices[i].iterator();
            do
            {
                if(!iter.hasNext())
                    break;
                Entity entity = (Entity)iter.next();
                int cx = Location.locToBlock(entity.locX) >> 4;
                int cz = Location.locToBlock(entity.locZ) >> 4;
                if((entity instanceof EntityPlayer) && (cx != x || cz != z))
                    iter.remove();
            } while(true);
            world.b(entitySlices[i]);
        }

    }

    public void f()
    {
        q = true;
    }

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list)
    {
        int i = MathHelper.floor((axisalignedbb.b - 2D) / 16D);
        int j = MathHelper.floor((axisalignedbb.e + 2D) / 16D);
        if(i < 0)
            i = 0;
        if(j >= entitySlices.length)
            j = entitySlices.length - 1;
        for(int k = i; k <= j; k++)
        {
            List list1 = entitySlices[k];
            for(int l = 0; l < list1.size(); l++)
            {
                Entity entity1 = (Entity)list1.get(l);
                if(entity1 != entity && entity1.boundingBox.a(axisalignedbb))
                    list.add(entity1);
            }

        }

    }

    public void a(Class oclass, AxisAlignedBB axisalignedbb, List list)
    {
        int i = MathHelper.floor((axisalignedbb.b - 2D) / 16D);
        int j = MathHelper.floor((axisalignedbb.e + 2D) / 16D);
        if(i < 0)
            i = 0;
        if(j >= entitySlices.length)
            j = entitySlices.length - 1;
        for(int k = i; k <= j; k++)
        {
            List list1 = entitySlices[k];
            for(int l = 0; l < list1.size(); l++)
            {
                Entity entity = (Entity)list1.get(l);
                if(oclass.isAssignableFrom(entity.getClass()) && entity.boundingBox.a(axisalignedbb))
                    list.add(entity);
            }

        }

    }

    public boolean a(boolean flag)
    {
        if(r)
            return false;
        if(flag)
        {
            if(s && world.getTime() != t)
                return true;
        } else
        if(s && world.getTime() >= t + 600L)
            return true;
        return q;
    }

    public int getData(byte abyte[], int i, int j, int k, int l, int i1, int j1, 
            int k1)
    {
        int l1 = l - i;
        int i2 = i1 - j;
        int j2 = j1 - k;
        if(l1 * i2 * j2 == b.length)
        {
            System.arraycopy(b, 0, abyte, k1, b.length);
            k1 += b.length;
            System.arraycopy(g.a, 0, abyte, k1, g.a.length);
            k1 += g.a.length;
            System.arraycopy(this.i.a, 0, abyte, k1, this.i.a.length);
            k1 += this.i.a.length;
            System.arraycopy(h.a, 0, abyte, k1, h.a.length);
            k1 += h.a.length;
            return k1;
        }
        for(int k2 = i; k2 < l; k2++)
        {
            for(int i3 = k; i3 < j1; i3++)
            {
                int l2 = k2 << 11;
                int j3 = l2 | i3 << 7 | j;
                int k3 = i1 - j;
                System.arraycopy(b, j3, abyte, k1, k3);
                k1 += k3;
            }

        }

        for(int k2 = i; k2 < l; k2++)
        {
            for(int i3 = k; i3 < j1; i3++)
            {
                int l2 = k2 << 11;
                int j3 = (l2 | i3 << 7 | j) >> 1;
                int k3 = (i1 - j) / 2;
                System.arraycopy(g.a, j3, abyte, k1, k3);
                k1 += k3;
            }

        }

        for(int k2 = i; k2 < l; k2++)
        {
            for(int i3 = k; i3 < j1; i3++)
            {
                int l2 = k2 << 11;
                int j3 = (l2 | i3 << 7 | j) >> 1;
                int k3 = (i1 - j) / 2;
                System.arraycopy(this.i.a, j3, abyte, k1, k3);
                k1 += k3;
            }

        }

        for(int k2 = i; k2 < l; k2++)
        {
            for(int i3 = k; i3 < j1; i3++)
            {
                int l2 = k2 << 11;
                int j3 = (l2 | i3 << 7 | j) >> 1;
                int k3 = (i1 - j) / 2;
                System.arraycopy(h.a, j3, abyte, k1, k3);
                k1 += k3;
            }

        }

        return k1;
    }

    public Random a(long i)
    {
        return new Random(world.getSeed() + (long)(x * x * 0x4c1906) + (long)(x * 0x5ac0db) + (long)(z * z) * 0x4307a7L + (long)(z * 0x5f24f) ^ i);
    }

    public void g()
    {
        BlockRegister.a(b);
    }

    public void a(IChunkProvider ichunkprovider, IChunkProvider ichunkprovider1, int i, int j)
    {
        if(!done && ichunkprovider.isChunkLoaded(i + 1, j + 1) && ichunkprovider.isChunkLoaded(i, j + 1) && ichunkprovider.isChunkLoaded(i + 1, j))
            ichunkprovider.getChunkAt(ichunkprovider1, i, j);
        if(ichunkprovider.isChunkLoaded(i - 1, j) && !ichunkprovider.getOrCreateChunk(i - 1, j).done && ichunkprovider.isChunkLoaded(i - 1, j + 1) && ichunkprovider.isChunkLoaded(i, j + 1) && ichunkprovider.isChunkLoaded(i - 1, j + 1))
            ichunkprovider.getChunkAt(ichunkprovider1, i - 1, j);
        if(ichunkprovider.isChunkLoaded(i, j - 1) && !ichunkprovider.getOrCreateChunk(i, j - 1).done && ichunkprovider.isChunkLoaded(i + 1, j - 1) && ichunkprovider.isChunkLoaded(i + 1, j - 1) && ichunkprovider.isChunkLoaded(i + 1, j))
            ichunkprovider.getChunkAt(ichunkprovider1, i, j - 1);
        if(ichunkprovider.isChunkLoaded(i - 1, j - 1) && !ichunkprovider.getOrCreateChunk(i - 1, j - 1).done && ichunkprovider.isChunkLoaded(i, j - 1) && ichunkprovider.isChunkLoaded(i - 1, j))
            ichunkprovider.getChunkAt(ichunkprovider1, i - 1, j - 1);
    }

    public int c(int i, int j)
    {
        int k = i | j << 4;
        int l = c[k];
        if(l == -999)
        {
            int i1 = 127;
            for(l = -1; i1 > 0 && l == -1;)
            {
                int j1 = getTypeId(i, i1, j);
                net.minecraft.server.Material material = j1 != 0 ? Block.byId[j1].material : Material.AIR;
                if(!material.isSolid() && !material.isLiquid())
                    i1--;
                else
                    l = i1 + 1;
            }

            c[k] = l;
        }
        return l;
    }

    public void h()
    {
        i();
    }

    public static boolean a;
    public byte b[];
    public int c[];
    public boolean d[];
    public boolean e;
    public World world;
    public NibbleArray g;
    public NibbleArray h;
    public NibbleArray i;
    public byte heightMap[];
    public int k;
    public final int x;
    public final int z;
    public Map tileEntities;
    public List entitySlices[];
    public boolean done;
    public boolean q;
    public boolean r;
    public boolean s;
    public long t;
    boolean u;
    public org.bukkit.Chunk bukkitChunk;
}
