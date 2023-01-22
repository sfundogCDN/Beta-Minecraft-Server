// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PortalTravelAgent.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.*;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, MathHelper, World, Block, 
//            BlockPortal, Material

public class PortalTravelAgent
{

    public PortalTravelAgent()
    {
        a = new Random();
    }

    public void a(net.minecraft.server.World world, Entity entity)
    {
        if(!b(world, entity))
        {
            c(world, entity);
            b(world, entity);
        }
    }

    public boolean b(net.minecraft.server.World world, Entity entity)
    {
        short short1 = 128;
        double d0 = -1D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor(entity.locX);
        int i1 = MathHelper.floor(entity.locZ);
        for(int j1 = l - short1; j1 <= l + short1; j1++)
        {
            double d2 = ((double)j1 + 0.5D) - entity.locX;
            for(int k1 = i1 - short1; k1 <= i1 + short1; k1++)
            {
                double d3 = ((double)k1 + 0.5D) - entity.locZ;
                world.getClass();
                for(int l1 = 127; l1 >= 0; l1--)
                {
                    if(world.getTypeId(j1, l1, k1) != Block.PORTAL.id)
                        continue;
                    for(; world.getTypeId(j1, l1 - 1, k1) == Block.PORTAL.id; l1--);
                    double d1 = ((double)l1 + 0.5D) - entity.locY;
                    double d4 = d2 * d2 + d1 * d1 + d3 * d3;
                    if(d0 < 0.0D || d4 < d0)
                    {
                        d0 = d4;
                        i = j1;
                        j = l1;
                        k = k1;
                    }
                }

            }

        }

        if(d0 >= 0.0D)
        {
            double d5 = (double)i + 0.5D;
            double d6 = (double)j + 0.5D;
            double d1 = (double)k + 0.5D;
            if(world.getTypeId(i - 1, j, k) == Block.PORTAL.id)
                d5 -= 0.5D;
            if(world.getTypeId(i + 1, j, k) == Block.PORTAL.id)
                d5 += 0.5D;
            if(world.getTypeId(i, j, k - 1) == Block.PORTAL.id)
                d1 -= 0.5D;
            if(world.getTypeId(i, j, k + 1) == Block.PORTAL.id)
                d1 += 0.5D;
            entity.setPositionRotation(d5, d6, d1, entity.yaw, 0.0F);
            entity.motX = entity.motY = entity.motZ = 0.0D;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean c(net.minecraft.server.World world, Entity entity)
    {
        byte b0 = 16;
        double d0 = -1D;
        int i = MathHelper.floor(entity.locX);
        int j = MathHelper.floor(entity.locY);
        int k = MathHelper.floor(entity.locZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = a.nextInt(4);
        int j2;
        for(int i2 = i - b0; i2 <= i + b0; i2++)
        {
            double d1 = ((double)i2 + 0.5D) - entity.locX;
            for(j2 = k - b0; j2 <= k + b0; j2++)
            {
                double d2 = ((double)j2 + 0.5D) - entity.locZ;
                world.getClass();
                for(int l2 = 127; l2 >= 0; l2--)
                {
                    if(!world.isEmpty(i2, l2, j2))
                        continue;
                    for(; l2 > 0 && world.isEmpty(i2, l2 - 1, j2); l2--);
label0:
                    for(int k2 = l1; k2 < l1 + 4; k2++)
                    {
                        int j3 = k2 % 2;
                        int i3 = 1 - j3;
                        if(k2 % 4 >= 2)
                        {
                            j3 = -j3;
                            i3 = -i3;
                        }
                        for(int l3 = 0; l3 < 3; l3++)
                        {
                            for(int k3 = 0; k3 < 4; k3++)
                            {
                                for(int j4 = -1; j4 < 4; j4++)
                                {
                                    int i4 = i2 + (k3 - 1) * j3 + l3 * i3;
                                    int k4 = l2 + j4;
                                    int l4 = (j2 + (k3 - 1) * i3) - l3 * j3;
                                    if(j4 < 0 && !world.getMaterial(i4, k4, l4).isBuildable() || j4 >= 0 && !world.isEmpty(i4, k4, l4))
                                        break label0;
                                }

                            }

                        }

                        double d3 = ((double)l2 + 0.5D) - entity.locY;
                        double d4 = d1 * d1 + d3 * d3 + d2 * d2;
                        if(d0 < 0.0D || d4 < d0)
                        {
                            d0 = d4;
                            l = i2;
                            i1 = l2;
                            j1 = j2;
                            k1 = k2 % 4;
                        }
                    }

                }

            }

        }

        if(d0 < 0.0D)
        {
            for(int i2 = i - b0; i2 <= i + b0; i2++)
            {
                double d1 = ((double)i2 + 0.5D) - entity.locX;
                for(j2 = k - b0; j2 <= k + b0; j2++)
                {
                    double d2 = ((double)j2 + 0.5D) - entity.locZ;
                    world.getClass();
                    for(int l2 = 127; l2 >= 0; l2--)
                    {
                        if(!world.isEmpty(i2, l2, j2))
                            continue;
                        for(; l2 > 0 && world.isEmpty(i2, l2 - 1, j2); l2--);
label1:
                        for(int k2 = l1; k2 < l1 + 2; k2++)
                        {
                            int j3 = k2 % 2;
                            int i3 = 1 - j3;
                            for(int l3 = 0; l3 < 4; l3++)
                            {
                                for(int k3 = -1; k3 < 4; k3++)
                                {
                                    int j4 = i2 + (l3 - 1) * j3;
                                    int i4 = l2 + k3;
                                    int k4 = j2 + (l3 - 1) * i3;
                                    if(k3 < 0 && !world.getMaterial(j4, i4, k4).isBuildable() || k3 >= 0 && !world.isEmpty(j4, i4, k4))
                                        break label1;
                                }

                            }

                            double d3 = ((double)l2 + 0.5D) - entity.locY;
                            double d4 = d1 * d1 + d3 * d3 + d2 * d2;
                            if(d0 < 0.0D || d4 < d0)
                            {
                                d0 = d4;
                                l = i2;
                                i1 = l2;
                                j1 = j2;
                                k1 = k2 % 2;
                            }
                        }

                    }

                }

            }

        }
        int i5 = l;
        int j5 = i1;
        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;
        if(k1 % 4 >= 2)
        {
            k5 = -k5;
            l5 = -l5;
        }
        Collection blocks = new HashSet();
        World bworld = world.getWorld();
        if(d0 < 0.0D)
        {
            if(i1 < 70)
                i1 = 70;
            if(i1 > 118)
                i1 = 118;
            j5 = i1;
            for(int l2 = -1; l2 <= 1; l2++)
            {
                for(int k2 = 1; k2 < 3; k2++)
                {
                    for(int j3 = -1; j3 < 3; j3++)
                    {
                        int i3 = i5 + (k2 - 1) * k5 + l2 * l5;
                        int l3 = j5 + j3;
                        int k3 = (j2 + (k2 - 1) * l5) - l2 * k5;
                        blocks.add(bworld.getBlockAt(i3, l3, k3));
                    }

                }

            }

        }
        for(int l2 = 0; l2 < 4; l2++)
        {
            for(int k2 = 0; k2 < 4; k2++)
            {
                for(int j3 = -1; j3 < 4; j3++)
                {
                    int i3 = i5 + (k2 - 1) * k5;
                    int l3 = j5 + j3;
                    int k3 = j2 + (k2 - 1) * l5;
                    blocks.add(bworld.getBlockAt(i3, l3, k3));
                }

            }

        }

        PortalCreateEvent event = new PortalCreateEvent(blocks, bworld);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            return true;
        if(d0 < 0.0D)
        {
            if(i1 < 70)
                i1 = 70;
            world.getClass();
            if(i1 > 118)
            {
                world.getClass();
                i1 = 118;
            }
            j5 = i1;
            for(int l2 = -1; l2 <= 1; l2++)
            {
                for(int k2 = 1; k2 < 3; k2++)
                {
                    for(int j3 = -1; j3 < 3; j3++)
                    {
                        int i3 = i5 + (k2 - 1) * k5 + l2 * l5;
                        int l3 = j5 + j3;
                        int k3 = (j2 + (k2 - 1) * l5) - l2 * k5;
                        boolean flag = j3 < 0;
                        world.setTypeId(i3, l3, k3, flag ? Block.OBSIDIAN.id : 0);
                    }

                }

            }

        }
        for(int l2 = 0; l2 < 4; l2++)
        {
            world.suppressPhysics = true;
            for(int k2 = 0; k2 < 4; k2++)
            {
                for(int j3 = -1; j3 < 4; j3++)
                {
                    int i3 = i5 + (k2 - 1) * k5;
                    int l3 = j5 + j3;
                    int k3 = j2 + (k2 - 1) * l5;
                    boolean flag = k2 == 0 || k2 == 3 || j3 == -1 || j3 == 3;
                    world.setTypeId(i3, l3, k3, flag ? Block.OBSIDIAN.id : Block.PORTAL.id);
                }

            }

            world.suppressPhysics = false;
            for(int k2 = 0; k2 < 4; k2++)
            {
                for(int j3 = -1; j3 < 4; j3++)
                {
                    int i3 = i5 + (k2 - 1) * k5;
                    int l3 = j5 + j3;
                    int k3 = j2 + (k2 - 1) * l5;
                    world.applyPhysics(i3, l3, k3, world.getTypeId(i3, l3, k3));
                }

            }

        }

        return true;
    }

    private Random a;
}
