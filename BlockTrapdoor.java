// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockTrapdoor.java

package net.minecraft.server;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, EntityHuman, Material, IBlockAccess, 
//            World, AxisAlignedBB, Vec3D, MovingObjectPosition

public class BlockTrapdoor extends net.minecraft.server.Block
{

    protected BlockTrapdoor(int i, Material material)
    {
        super(i, material);
        textureId = 84;
        if(material == Material.ORE)
            textureId++;
        float f = 0.5F;
        float f1 = 1.0F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public AxisAlignedBB e(net.minecraft.server.World world, int i, int j, int k)
    {
        a(world, i, j, k);
        return super.e(world, i, j, k);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        c(iblockaccess.getData(i, j, k));
    }

    public void c(int i)
    {
        float f = 0.1875F;
        a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
        if(d(i))
        {
            if((i & 3) == 0)
                a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            if((i & 3) == 1)
                a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            if((i & 3) == 2)
                a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            if((i & 3) == 3)
                a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }

    public void b(net.minecraft.server.World world, int i, int j, int k, EntityHuman entityhuman)
    {
        interact(world, i, j, k, entityhuman);
    }

    public boolean interact(net.minecraft.server.World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(material == Material.ORE)
        {
            return true;
        } else
        {
            int l = world.getData(i, j, k);
            world.setData(i, j, k, l ^ 4);
            world.a(entityhuman, 1003, i, j, k, 0);
            return true;
        }
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, boolean flag)
    {
        int l = world.getData(i, j, k);
        boolean flag1 = (l & 4) > 0;
        if(flag1 != flag)
        {
            world.setData(i, j, k, l ^ 4);
            world.a((EntityHuman)null, 1003, i, j, k, 0);
        }
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        if(!world.isStatic)
        {
            int i1 = world.getData(i, j, k);
            int j1 = i;
            int k1 = k;
            if((i1 & 3) == 0)
                k1 = k + 1;
            if((i1 & 3) == 1)
                k1--;
            if((i1 & 3) == 2)
                j1 = i + 1;
            if((i1 & 3) == 3)
                j1--;
            if(!world.e(j1, j, k1))
            {
                world.setTypeId(i, j, k, 0);
                g(world, i, j, k, i1);
            }
            if(l > 0 && Block.byId[l] != null && Block.byId[l].isPowerSource())
            {
                World bworld = world.getWorld();
                Block block = bworld.getBlockAt(i, j, k);
                int power = block.getBlockPower();
                int oldPower = (world.getData(i, j, k) & 4) <= 0 ? 0 : 15;
                if((oldPower == 0) ^ (power == 0))
                {
                    BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
                    world.getServer().getPluginManager().callEvent(eventRedstone);
                    a(world, i, j, k, eventRedstone.getNewCurrent() > 0);
                }
            }
        }
    }

    public MovingObjectPosition a(net.minecraft.server.World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        a(world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public void postPlace(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        byte b0 = 0;
        if(l == 2)
            b0 = 0;
        if(l == 3)
            b0 = 1;
        if(l == 4)
            b0 = 2;
        if(l == 5)
            b0 = 3;
        world.setData(i, j, k, b0);
        doPhysics(world, i, j, k, Block.REDSTONE_WIRE.id);
    }

    public boolean canPlace(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        if(l == 0)
            return false;
        if(l == 1)
            return false;
        if(l == 2)
            k++;
        if(l == 3)
            k--;
        if(l == 4)
            i++;
        if(l == 5)
            i--;
        return world.e(i, j, k);
    }

    public static boolean d(int i)
    {
        return (i & 4) != 0;
    }
}
