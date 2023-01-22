// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemRedstone.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, World, Block, EntityHuman, 
//            ItemStack

public class ItemRedstone extends Item
{

    public ItemRedstone(int i)
    {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
        if(world.getTypeId(i, j, k) != Block.SNOW.id)
        {
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
            if(!world.isEmpty(i, j, k))
                return false;
        }
        if(!entityhuman.c(i, j, k))
            return false;
        if(Block.REDSTONE_WIRE.canPlace(world, i, j, k))
        {
            CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
            world.setRawTypeId(i, j, k, Block.REDSTONE_WIRE.id);
            BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, Block.REDSTONE_WIRE);
            if(event.isCancelled() || !event.canBuild())
            {
                event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
                return false;
            }
            world.update(i, j, k, Block.REDSTONE_WIRE.id);
            itemstack.count--;
        }
        return true;
    }
}
