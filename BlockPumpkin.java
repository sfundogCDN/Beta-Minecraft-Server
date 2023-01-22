// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockPumpkin.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, EntityLiving, 
//            MathHelper

public class BlockPumpkin extends net.minecraft.server.Block
{

    protected BlockPumpkin(int i, int j, boolean flag)
    {
        super(i, Material.PUMPKIN);
        textureId = j;
        a(true);
        a = flag;
    }

    public int a(int i, int j)
    {
        if(i == 1)
            return textureId;
        if(i == 0)
            return textureId;
        int k = textureId + 1 + 16;
        if(a)
            k++;
        return j != 2 || i != 2 ? j != 3 || i != 5 ? j != 0 || i != 3 ? j != 1 || i != 4 ? textureId + 16 : k : k : k : k;
    }

    public int a(int i)
    {
        return i != 1 ? i != 0 ? i != 3 ? textureId + 16 : textureId + 1 + 16 : textureId : textureId;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        int l = world.getTypeId(i, j, k);
        return (l == 0 || Block.byId[l].material.isReplacable()) && world.e(i, j - 1, k);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 2.5D) & 3;
        world.setData(i, j, k, l);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(Block.byId[l] != null && Block.byId[l].isPowerSource())
        {
            Block block = world.getWorld().getBlockAt(i, j, k);
            int power = block.getBlockPower();
            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, power, power);
            world.getServer().getPluginManager().callEvent(eventRedstone);
        }
    }

    private boolean a;
}
