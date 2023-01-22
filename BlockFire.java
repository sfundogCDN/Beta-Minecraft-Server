// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockFire.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.*;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Material, BlockLeaves, BlockLongGrass, 
//            World, IBlockAccess, BlockPortal, AxisAlignedBB

public class BlockFire extends net.minecraft.server.Block
{

    protected BlockFire(int i, int j)
    {
        super(i, j, Material.FIRE);
        a = new int[256];
        b = new int[256];
        a(true);
    }

    public void h()
    {
        a(Block.WOOD.id, 5, 20);
        a(Block.FENCE.id, 5, 20);
        a(Block.WOOD_STAIRS.id, 5, 20);
        a(Block.LOG.id, 5, 5);
        a(Block.LEAVES.id, 30, 60);
        a(Block.BOOKSHELF.id, 30, 20);
        a(Block.TNT.id, 15, 100);
        a(Block.LONG_GRASS.id, 60, 100);
        a(Block.WOOL.id, 30, 60);
        a(Block.VINE.id, 15, 100);
    }

    private void a(int i, int j, int k)
    {
        a[i] = j;
        b[i] = k;
    }

    public AxisAlignedBB e(net.minecraft.server.World world, int i, int j, int l)
    {
        return null;
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public int a(Random random)
    {
        return 0;
    }

    public int c()
    {
        return 40;
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        boolean flag = world.getTypeId(i, j - 1, k) == Block.NETHERRACK.id;
        if(!canPlace(world, i, j, k))
            world.setTypeId(i, j, k, 0);
        if(!flag && world.u() && (world.s(i, j, k) || world.s(i - 1, j, k) || world.s(i + 1, j, k) || world.s(i, j, k - 1) || world.s(i, j, k + 1)))
        {
            world.setTypeId(i, j, k, 0);
        } else
        {
            int l = world.getData(i, j, k);
            if(l < 15)
                world.setRawData(i, j, k, l + random.nextInt(3) / 2);
            world.c(i, j, k, id, c());
            if(!flag && !g(world, i, j, k))
            {
                if(!world.e(i, j - 1, k) || l > 3)
                    world.setTypeId(i, j, k, 0);
            } else
            if(!flag && !b(world, i, j - 1, k) && l == 15 && random.nextInt(4) == 0)
            {
                world.setTypeId(i, j, k, 0);
            } else
            {
                a(world, i + 1, j, k, 300, random, l);
                a(world, i - 1, j, k, 300, random, l);
                a(world, i, j - 1, k, 250, random, l);
                a(world, i, j + 1, k, 250, random, l);
                a(world, i, j, k - 1, 300, random, l);
                a(world, i, j, k + 1, 300, random, l);
                Server server = world.getServer();
                World bworld = world.getWorld();
                org.bukkit.event.block.BlockIgniteEvent.IgniteCause igniteCause = org.bukkit.event.block.BlockIgniteEvent.IgniteCause.SPREAD;
                Block fromBlock = bworld.getBlockAt(i, j, k);
                for(int i1 = i - 1; i1 <= i + 1; i1++)
                {
                    for(int j1 = k - 1; j1 <= k + 1; j1++)
                    {
                        for(int k1 = j - 1; k1 <= j + 4; k1++)
                        {
                            if(i1 == i && k1 == j && j1 == k)
                                continue;
                            int l1 = 100;
                            if(k1 > j + 1)
                                l1 += (k1 - (j + 1)) * 100;
                            int i2 = h(world, i1, k1, j1);
                            if(i2 <= 0)
                                continue;
                            int j2 = (i2 + 40) / (l + 30);
                            if(j2 <= 0 || random.nextInt(l1) > j2 || world.u() && world.s(i1, k1, j1) || world.s(i1 - 1, k1, k) || world.s(i1 + 1, k1, j1) || world.s(i1, k1, j1 - 1) || world.s(i1, k1, j1 + 1))
                                continue;
                            int k2 = l + random.nextInt(5) / 4;
                            if(k2 > 15)
                                k2 = 15;
                            Block block = bworld.getBlockAt(i1, k1, j1);
                            if(block.getTypeId() == Block.FIRE.id)
                                continue;
                            BlockIgniteEvent event = new BlockIgniteEvent(block, igniteCause, null);
                            server.getPluginManager().callEvent(event);
                            if(event.isCancelled())
                                continue;
                            BlockState blockState = bworld.getBlockAt(i1, k1, j1).getState();
                            blockState.setTypeId(id);
                            blockState.setData(new MaterialData(id, (byte)k2));
                            BlockSpreadEvent spreadEvent = new BlockSpreadEvent(blockState.getBlock(), fromBlock, blockState);
                            server.getPluginManager().callEvent(spreadEvent);
                            if(!spreadEvent.isCancelled())
                                blockState.update(true);
                        }

                    }

                }

            }
        }
    }

    private void a(net.minecraft.server.World world, int i, int j, int k, int l, Random random, int i1)
    {
        int j1 = b[world.getTypeId(i, j, k)];
        if(random.nextInt(l) < j1)
        {
            boolean flag = world.getTypeId(i, j, k) == Block.TNT.id;
            Block theBlock = world.getWorld().getBlockAt(i, j, k);
            BlockBurnEvent event = new BlockBurnEvent(theBlock);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return;
            if(random.nextInt(i1 + 10) < 5 && !world.s(i, j, k))
            {
                int k1 = i1 + random.nextInt(5) / 4;
                if(k1 > 15)
                    k1 = 15;
                world.setTypeIdAndData(i, j, k, id, k1);
            } else
            {
                world.setTypeId(i, j, k, 0);
            }
            if(flag)
                Block.TNT.postBreak(world, i, j, k, 1);
        }
    }

    private boolean g(net.minecraft.server.World world, int i, int j, int k)
    {
        return b(world, i + 1, j, k) ? true : b(world, i - 1, j, k) ? true : b(world, i, j - 1, k) ? true : b(world, i, j + 1, k) ? true : b(world, i, j, k - 1) ? true : b(world, i, j, k + 1);
    }

    private int h(net.minecraft.server.World world, int i, int j, int k)
    {
        byte b0 = 0;
        if(!world.isEmpty(i, j, k))
        {
            return 0;
        } else
        {
            int l = f(world, i + 1, j, k, b0);
            l = f(world, i - 1, j, k, l);
            l = f(world, i, j - 1, k, l);
            l = f(world, i, j + 1, k, l);
            l = f(world, i, j, k - 1, l);
            l = f(world, i, j, k + 1, l);
            return l;
        }
    }

    public boolean q_()
    {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return a[iblockaccess.getTypeId(i, j, k)] > 0;
    }

    public int f(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        int i1 = a[world.getTypeId(i, j, k)];
        return i1 <= l ? l : i1;
    }

    public boolean canPlace(net.minecraft.server.World world, int i, int j, int k)
    {
        return world.e(i, j - 1, k) || g(world, i, j, k);
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        if(!world.e(i, j - 1, k) && !g(world, i, j, k))
            world.setTypeId(i, j, k, 0);
    }

    public void a(net.minecraft.server.World world, int i, int j, int k)
    {
        if(world.getTypeId(i, j - 1, k) != Block.OBSIDIAN.id || !Block.PORTAL.b_(world, i, j, k))
            if(!world.e(i, j - 1, k) && !g(world, i, j, k))
                world.setTypeId(i, j, k, 0);
            else
                world.c(i, j, k, id, c());
    }

    private int a[];
    private int b[];
}
