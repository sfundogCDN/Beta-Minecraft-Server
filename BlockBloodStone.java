// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockBloodStone.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World

public class BlockBloodStone extends net.minecraft.server.Block
{

    public BlockBloodStone(int i, int j)
    {
        super(i, j, Material.STONE);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(Block.byId[l] != null && Block.byId[l].isPowerSource())
        {
            Block block = world.getWorld().getBlockAt(i, j, k);
            int power = block.getBlockPower();
            BlockRedstoneEvent event = new BlockRedstoneEvent(block, power, power);
            world.getServer().getPluginManager().callEvent(event);
        }
    }
}
