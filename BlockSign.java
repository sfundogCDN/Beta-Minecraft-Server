// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockSign.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, TileEntity, Material, IBlockAccess, 
//            Item, World, Block, AxisAlignedBB

public class BlockSign extends BlockContainer
{

    protected BlockSign(int i, Class oclass, boolean flag)
    {
        super(i, Material.WOOD);
        b = flag;
        textureId = 4;
        a = oclass;
        float f = 0.25F;
        float f1 = 1.0F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public AxisAlignedBB e(World world, int i, int j, int l)
    {
        return null;
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if(!b)
        {
            int l = iblockaccess.getData(i, j, k);
            float f = 0.28125F;
            float f1 = 0.78125F;
            float f2 = 0.0F;
            float f3 = 1.0F;
            float f4 = 0.125F;
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            if(l == 2)
                a(f2, f, 1.0F - f4, f3, f1, 1.0F);
            if(l == 3)
                a(f2, f, 0.0F, f3, f1, f4);
            if(l == 4)
                a(1.0F - f4, f, f2, 1.0F, f1, f3);
            if(l == 5)
                a(0.0F, f, f2, f4, f1, f3);
        }
    }

    public boolean b()
    {
        return false;
    }

    public boolean a()
    {
        return false;
    }

    public TileEntity a_()
    {
        try
        {
            return (TileEntity)a.newInstance();
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    public int a(int i, Random random)
    {
        return Item.SIGN.id;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        boolean flag = false;
        if(b)
        {
            if(!world.getMaterial(i, j - 1, k).isBuildable())
                flag = true;
        } else
        {
            int i1 = world.getData(i, j, k);
            flag = true;
            if(i1 == 2 && world.getMaterial(i, j, k + 1).isBuildable())
                flag = false;
            if(i1 == 3 && world.getMaterial(i, j, k - 1).isBuildable())
                flag = false;
            if(i1 == 4 && world.getMaterial(i + 1, j, k).isBuildable())
                flag = false;
            if(i1 == 5 && world.getMaterial(i - 1, j, k).isBuildable())
                flag = false;
        }
        if(flag)
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
        super.doPhysics(world, i, j, k, l);
        if(Block.byId[l] != null && Block.byId[l].isPowerSource())
        {
            Block block = world.getWorld().getBlockAt(i, j, k);
            int power = block.getBlockPower();
            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, power, power);
            world.getServer().getPluginManager().callEvent(eventRedstone);
        }
    }

    private Class a;
    private boolean b;
}
