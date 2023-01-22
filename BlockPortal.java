// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockPortal.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockBreakable, Material, IBlockAccess, World, 
//            Block, BlockFire, Entity, AxisAlignedBB

public class BlockPortal extends BlockBreakable
{

    public BlockPortal(int i, int j)
    {
        super(i, j, Material.PORTAL, false);
    }

    public AxisAlignedBB e(net.minecraft.server.World world, int i, int j, int l)
    {
        return null;
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if(iblockaccess.getTypeId(i - 1, j, k) != id && iblockaccess.getTypeId(i + 1, j, k) != id)
        {
            float f = 0.125F;
            float f1 = 0.5F;
            a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        } else
        {
            float f = 0.5F;
            float f1 = 0.125F;
            a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        }
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public boolean b_(net.minecraft.server.World world, int i, int j, int k)
    {
        byte b0 = 0;
        byte b1 = 0;
        if(world.getTypeId(i - 1, j, k) == Block.OBSIDIAN.id || world.getTypeId(i + 1, j, k) == Block.OBSIDIAN.id)
            b0 = 1;
        if(world.getTypeId(i, j, k - 1) == Block.OBSIDIAN.id || world.getTypeId(i, j, k + 1) == Block.OBSIDIAN.id)
            b1 = 1;
        if(b0 == b1)
            return false;
        Collection blocks = new HashSet();
        World bworld = world.getWorld();
        if(world.getTypeId(i - b0, j, k - b1) == 0)
        {
            i -= b0;
            k -= b1;
        }
        for(int l = -1; l <= 2; l++)
        {
            for(int i1 = -1; i1 <= 3; i1++)
            {
                boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
                if((l == -1 || l == 2) && (i1 == -1 || i1 == 3))
                    continue;
                int j1 = world.getTypeId(i + b0 * l, j + i1, k + b1 * l);
                if(flag)
                {
                    if(j1 != Block.OBSIDIAN.id)
                        return false;
                    blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));
                    continue;
                }
                if(j1 != 0 && j1 != Block.FIRE.id)
                    return false;
            }

        }

        for(int l = 0; l < 2; l++)
        {
            for(int i1 = 0; i1 < 3; i1++)
                blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));

        }

        PortalCreateEvent event = new PortalCreateEvent(blocks, bworld);
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            return false;
        world.suppressPhysics = true;
        for(int l = 0; l < 2; l++)
        {
            for(int i1 = 0; i1 < 3; i1++)
                world.setTypeId(i + b0 * l, j + i1, k + b1 * l, Block.PORTAL.id);

        }

        world.suppressPhysics = false;
        return true;
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        byte b0 = 0;
        byte b1 = 1;
        if(world.getTypeId(i - 1, j, k) == id || world.getTypeId(i + 1, j, k) == id)
        {
            b0 = 1;
            b1 = 0;
        }
        int i1;
        for(i1 = j; world.getTypeId(i, i1 - 1, k) == id; i1--);
        if(world.getTypeId(i, i1 - 1, k) != Block.OBSIDIAN.id)
        {
            world.setTypeId(i, j, k, 0);
        } else
        {
            int j1;
            for(j1 = 1; j1 < 4 && world.getTypeId(i, i1 + j1, k) == id; j1++);
            if(j1 == 3 && world.getTypeId(i, i1 + j1, k) == Block.OBSIDIAN.id)
            {
                boolean flag = world.getTypeId(i - 1, j, k) == id || world.getTypeId(i + 1, j, k) == id;
                boolean flag1 = world.getTypeId(i, j, k - 1) == id || world.getTypeId(i, j, k + 1) == id;
                if(flag && flag1)
                    world.setTypeId(i, j, k, 0);
                else
                if((world.getTypeId(i + b0, j, k + b1) != Block.OBSIDIAN.id || world.getTypeId(i - b0, j, k - b1) != id) && (world.getTypeId(i - b0, j, k - b1) != Block.OBSIDIAN.id || world.getTypeId(i + b0, j, k + b1) != id))
                    world.setTypeId(i, j, k, 0);
            } else
            {
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    public int a(Random random)
    {
        return 0;
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Entity entity)
    {
        if(entity.vehicle == null && entity.passenger == null)
        {
            EntityPortalEnterEvent event = new EntityPortalEnterEvent(entity.getBukkitEntity(), new Location(world.getWorld(), i, j, k));
            world.getServer().getPluginManager().callEvent(event);
            entity.T();
        }
    }
}
