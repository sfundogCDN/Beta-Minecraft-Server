// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockRedstoneTorch.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockTorch, RedstoneUpdateInfo, Block, World, 
//            IBlockAccess

public class BlockRedstoneTorch extends BlockTorch
{

    public int a(int i, int j)
    {
        return i != 1 ? super.a(i, j) : Block.REDSTONE_WIRE.a(i, j);
    }

    private boolean a(World world, int i, int j, int k, boolean flag)
    {
        if(flag)
            b.add(new RedstoneUpdateInfo(i, j, k, world.getTime()));
        int l = 0;
        for(int i1 = 0; i1 < b.size(); i1++)
        {
            RedstoneUpdateInfo redstoneupdateinfo = (RedstoneUpdateInfo)b.get(i1);
            if(redstoneupdateinfo.a == i && redstoneupdateinfo.b == j && redstoneupdateinfo.c == k && ++l >= 8)
                return true;
        }

        return false;
    }

    protected BlockRedstoneTorch(int i, int j, boolean flag)
    {
        super(i, j);
        isOn = false;
        isOn = flag;
        a(true);
    }

    public int c()
    {
        return 2;
    }

    public void a(World world, int i, int j, int k)
    {
        if(world.getData(i, j, k) == 0)
            super.a(world, i, j, k);
        if(isOn)
        {
            world.applyPhysics(i, j - 1, k, id);
            world.applyPhysics(i, j + 1, k, id);
            world.applyPhysics(i - 1, j, k, id);
            world.applyPhysics(i + 1, j, k, id);
            world.applyPhysics(i, j, k - 1, id);
            world.applyPhysics(i, j, k + 1, id);
        }
    }

    public void remove(World world, int i, int j, int k)
    {
        if(isOn)
        {
            world.applyPhysics(i, j - 1, k, id);
            world.applyPhysics(i, j + 1, k, id);
            world.applyPhysics(i - 1, j, k, id);
            world.applyPhysics(i + 1, j, k, id);
            world.applyPhysics(i, j, k - 1, id);
            world.applyPhysics(i, j, k + 1, id);
        }
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!isOn)
        {
            return false;
        } else
        {
            int i1 = iblockaccess.getData(i, j, k);
            return i1 != 5 || l != 1 ? i1 != 3 || l != 3 ? i1 != 4 || l != 2 ? i1 != 1 || l != 5 ? i1 != 2 || l != 4 : false : false : false : false;
        }
    }

    private boolean g(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        return l != 5 || !world.isBlockFaceIndirectlyPowered(i, j - 1, k, 0) ? l != 3 || !world.isBlockFaceIndirectlyPowered(i, j, k - 1, 2) ? l != 4 || !world.isBlockFaceIndirectlyPowered(i, j, k + 1, 3) ? l != 1 || !world.isBlockFaceIndirectlyPowered(i - 1, j, k, 4) ? l == 2 && world.isBlockFaceIndirectlyPowered(i + 1, j, k, 5) : true : true : true : true;
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        boolean flag = g(world, i, j, k);
        for(; b.size() > 0 && world.getTime() - ((RedstoneUpdateInfo)b.get(0)).d > 100L; b.remove(0));
        PluginManager manager = world.getServer().getPluginManager();
        org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
        int oldCurrent = isOn ? 15 : 0;
        BlockRedstoneEvent event = new BlockRedstoneEvent(block, oldCurrent, oldCurrent);
        if(isOn)
        {
            if(flag)
            {
                if(oldCurrent != 0)
                {
                    event.setNewCurrent(0);
                    manager.callEvent(event);
                    if(event.getNewCurrent() != 0)
                        return;
                }
                world.setTypeIdAndData(i, j, k, Block.REDSTONE_TORCH_OFF.id, world.getData(i, j, k));
                if(a(world, i, j, k, true))
                {
                    world.makeSound((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                    for(int l = 0; l < 5; l++)
                    {
                        double d0 = (double)i + random.nextDouble() * 0.59999999999999998D + 0.20000000000000001D;
                        double d1 = (double)j + random.nextDouble() * 0.59999999999999998D + 0.20000000000000001D;
                        double d2 = (double)k + random.nextDouble() * 0.59999999999999998D + 0.20000000000000001D;
                        world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }

                }
            }
        } else
        if(!flag && !a(world, i, j, k, false))
        {
            if(oldCurrent != 15)
            {
                event.setNewCurrent(15);
                manager.callEvent(event);
                if(event.getNewCurrent() != 15)
                    return;
            }
            world.setTypeIdAndData(i, j, k, Block.REDSTONE_TORCH_ON.id, world.getData(i, j, k));
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        super.doPhysics(world, i, j, k, l);
        world.c(i, j, k, id, c());
    }

    public boolean d(World world, int i, int j, int k, int l)
    {
        return l != 0 ? false : a(world, i, j, k, l);
    }

    public int a(int i, Random random)
    {
        return Block.REDSTONE_TORCH_ON.id;
    }

    public boolean isPowerSource()
    {
        return true;
    }

    private boolean isOn;
    private static List b = new ArrayList();

}
