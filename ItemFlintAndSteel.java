// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemFlintAndSteel.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Item, EntityHuman, World, ItemStack, 
//            Block, BlockFire

public class ItemFlintAndSteel extends Item
{

    public ItemFlintAndSteel(int i)
    {
        super(i);
        maxStackSize = 1;
        d(64);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
        if(l == 0)
            j--;
        if(l == 1)
            j++;
        if(l == 2)
            k--;
        if(l == 3)
            k++;
        if(l == 4)
            i--;
        if(l == 5)
            i++;
        if(!entityhuman.c(i, j, k))
            return false;
        int i1 = world.getTypeId(i, j, k);
        if(i1 == 0)
        {
            Block blockClicked = world.getWorld().getBlockAt(i, j, k);
            Player thePlayer = (Player)entityhuman.getBukkitEntity();
            BlockIgniteEvent eventIgnite = new BlockIgniteEvent(blockClicked, org.bukkit.event.block.BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL, thePlayer);
            world.getServer().getPluginManager().callEvent(eventIgnite);
            if(eventIgnite.isCancelled())
            {
                itemstack.damage(1, entityhuman);
                return false;
            }
            CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
            world.makeSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
            world.setTypeId(i, j, k, Block.FIRE.id);
            BlockPlaceEvent placeEvent = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, Block.FIRE.id);
            if(placeEvent.isCancelled() || !placeEvent.canBuild())
            {
                placeEvent.getBlockPlaced().setTypeIdAndData(0, (byte)0, false);
                return false;
            }
        }
        itemstack.damage(1, entityhuman);
        return true;
    }
}
