// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockLeaves.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockLeavesBase, ItemStack, Material, World, 
//            Block, EntityHuman, Item, ItemShears, 
//            StatisticList, Entity

public class BlockLeaves extends BlockLeavesBase
{

    protected BlockLeaves(int i, int j)
    {
        super(i, j, Material.LEAVES, false);
        c = j;
        a(true);
    }

    public void remove(World world, int i, int j, int k)
    {
        byte b0 = 1;
        int l = b0 + 1;
        if(world.a(i - l, j - l, k - l, i + l, j + l, k + l))
        {
            for(int i1 = -b0; i1 <= b0; i1++)
            {
                for(int j1 = -b0; j1 <= b0; j1++)
                {
                    for(int k1 = -b0; k1 <= b0; k1++)
                    {
                        int l1 = world.getTypeId(i + i1, j + j1, k + k1);
                        if(l1 == Block.LEAVES.id)
                        {
                            int i2 = world.getData(i + i1, j + j1, k + k1);
                            world.setRawData(i + i1, j + j1, k + k1, i2 | 8);
                        }
                    }

                }

            }

        }
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(!world.isStatic)
        {
            int l = world.getData(i, j, k);
            if((l & 8) != 0 && (l & 4) == 0)
            {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;
                if(a == null)
                    a = new int[b1 * b1 * b1];
                int l1;
                if(world.a(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
                {
                    for(l1 = -b0; l1 <= b0; l1++)
                    {
                        for(int i2 = -b0; i2 <= b0; i2++)
                        {
                            for(int j2 = -b0; j2 <= b0; j2++)
                            {
                                int k2 = world.getTypeId(i + l1, j + i2, k + j2);
                                if(k2 == Block.LOG.id)
                                {
                                    a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                    continue;
                                }
                                if(k2 == Block.LEAVES.id)
                                    a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                else
                                    a[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                            }

                        }

                    }

                    for(l1 = 1; l1 <= 4; l1++)
                    {
                        for(int i2 = -b0; i2 <= b0; i2++)
                        {
                            for(int j2 = -b0; j2 <= b0; j2++)
                            {
                                for(int k2 = -b0; k2 <= b0; k2++)
                                {
                                    if(a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] != l1 - 1)
                                        continue;
                                    if(a[((i2 + k1) - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        a[((i2 + k1) - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                    if(a[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        a[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                    if(a[(i2 + k1) * j1 + ((j2 + k1) - 1) * b1 + k2 + k1] == -2)
                                        a[(i2 + k1) * j1 + ((j2 + k1) - 1) * b1 + k2 + k1] = l1;
                                    if(a[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
                                        a[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                    if(a[(i2 + k1) * j1 + (j2 + k1) * b1 + ((k2 + k1) - 1)] == -2)
                                        a[(i2 + k1) * j1 + (j2 + k1) * b1 + ((k2 + k1) - 1)] = l1;
                                    if(a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
                                        a[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                }

                            }

                        }

                    }

                }
                l1 = a[k1 * j1 + k1 * b1 + k1];
                if(l1 >= 0)
                    world.setRawData(i, j, k, l & -9);
                else
                    g(world, i, j, k);
            }
        }
    }

    private void g(World world, int i, int j, int k)
    {
        LeavesDecayEvent event = new LeavesDecayEvent(world.getWorld().getBlockAt(i, j, k));
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
        {
            return;
        } else
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
            return;
        }
    }

    public int a(Random random)
    {
        return random.nextInt(20) != 0 ? 0 : 1;
    }

    public int a(int i, Random random)
    {
        return Block.SAPLING.id;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
    {
        if(!world.isStatic && entityhuman.K() != null && entityhuman.K().id == Item.SHEARS.id)
        {
            entityhuman.a(StatisticList.C[id], 1);
            a(world, i, j, k, new ItemStack(Block.LEAVES.id, 1, l & 3));
        } else
        {
            super.a(world, entityhuman, i, j, k, l);
        }
    }

    protected int a_(int i)
    {
        return i & 3;
    }

    public boolean a()
    {
        return !b;
    }

    public int a(int i, int j)
    {
        return (j & 3) != 1 ? textureId : textureId + 80;
    }

    public void b(World world, int i, int j, int k, Entity entity)
    {
        super.b(world, i, j, k, entity);
    }

    private int c;
    int a[];
}
