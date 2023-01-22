// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockLever.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, IBlockAccess, 
//            AxisAlignedBB, EntityHuman

public class BlockLever extends Block
{

    protected BlockLever(int i, int j)
    {
        super(i, j, Material.ORIENTABLE);
    }

    public AxisAlignedBB e(World world, int i, int j, int l)
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

    public boolean canPlace(World world, int i, int j, int k, int l)
    {
        return l != 1 || !world.e(i, j - 1, k) ? l != 2 || !world.e(i, j, k + 1) ? l != 3 || !world.e(i, j, k - 1) ? l != 4 || !world.e(i + 1, j, k) ? l == 5 && world.e(i - 1, j, k) : true : true : true : true;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return world.e(i - 1, j, k) ? true : world.e(i + 1, j, k) ? true : world.e(i, j, k - 1) ? true : world.e(i, j, k + 1) ? true : world.e(i, j - 1, k);
    }

    public void postPlace(World world, int i, int j, int k, int l)
    {
        int i1 = world.getData(i, j, k);
        int j1 = i1 & 8;
        i1 &= 7;
        i1 = -1;
        if(l == 1 && world.e(i, j - 1, k))
            i1 = 5 + world.random.nextInt(2);
        if(l == 2 && world.e(i, j, k + 1))
            i1 = 4;
        if(l == 3 && world.e(i, j, k - 1))
            i1 = 3;
        if(l == 4 && world.e(i + 1, j, k))
            i1 = 2;
        if(l == 5 && world.e(i - 1, j, k))
            i1 = 1;
        if(i1 == -1)
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        } else
        {
            world.setData(i, j, k, i1 + j1);
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(g(world, i, j, k))
        {
            int i1 = world.getData(i, j, k) & 7;
            boolean flag = false;
            if(!world.e(i - 1, j, k) && i1 == 1)
                flag = true;
            if(!world.e(i + 1, j, k) && i1 == 2)
                flag = true;
            if(!world.e(i, j, k - 1) && i1 == 3)
                flag = true;
            if(!world.e(i, j, k + 1) && i1 == 4)
                flag = true;
            if(!world.e(i, j - 1, k) && i1 == 5)
                flag = true;
            if(!world.e(i, j - 1, k) && i1 == 6)
                flag = true;
            if(flag)
            {
                g(world, i, j, k, world.getData(i, j, k));
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    private boolean g(World world, int i, int j, int k)
    {
        if(!canPlace(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
            return false;
        } else
        {
            return true;
        }
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k) & 7;
        float f = 0.1875F;
        if(l == 1)
            a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        else
        if(l == 2)
            a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        else
        if(l == 3)
            a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        else
        if(l == 4)
        {
            a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else
        {
            f = 0.25F;
            a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        interact(world, i, j, k, entityhuman);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
            return true;
        int l = world.getData(i, j, k);
        int i1 = l & 7;
        int j1 = 8 - (l & 8);
        org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
        int old = j1 == 8 ? 0 : 1;
        int current = j1 != 8 ? 0 : 1;
        BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
        world.getServer().getPluginManager().callEvent(eventRedstone);
        if((eventRedstone.getNewCurrent() > 0) != (j1 == 8))
            return true;
        world.setData(i, j, k, i1 + j1);
        world.b(i, j, k, i, j, k);
        world.makeSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.click", 0.3F, j1 <= 0 ? 0.5F : 0.6F);
        world.applyPhysics(i, j, k, id);
        if(i1 == 1)
            world.applyPhysics(i - 1, j, k, id);
        else
        if(i1 == 2)
            world.applyPhysics(i + 1, j, k, id);
        else
        if(i1 == 3)
            world.applyPhysics(i, j, k - 1, id);
        else
        if(i1 == 4)
            world.applyPhysics(i, j, k + 1, id);
        else
            world.applyPhysics(i, j - 1, k, id);
        return true;
    }

    public void remove(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        if((l & 8) > 0)
        {
            world.applyPhysics(i, j, k, id);
            int i1 = l & 7;
            if(i1 == 1)
                world.applyPhysics(i - 1, j, k, id);
            else
            if(i1 == 2)
                world.applyPhysics(i + 1, j, k, id);
            else
            if(i1 == 3)
                world.applyPhysics(i, j, k - 1, id);
            else
            if(i1 == 4)
                world.applyPhysics(i, j, k + 1, id);
            else
                world.applyPhysics(i, j - 1, k, id);
        }
        super.remove(world, i, j, k);
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return (iblockaccess.getData(i, j, k) & 8) > 0;
    }

    public boolean d(World world, int i, int j, int k, int l)
    {
        int i1 = world.getData(i, j, k);
        if((i1 & 8) == 0)
        {
            return false;
        } else
        {
            int j1 = i1 & 7;
            return j1 != 6 || l != 1 ? j1 != 5 || l != 1 ? j1 != 4 || l != 2 ? j1 != 3 || l != 3 ? j1 != 2 || l != 4 ? j1 == 1 && l == 5 : true : true : true : true : true;
        }
    }

    public boolean isPowerSource()
    {
        return true;
    }
}
