// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemBed.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, BlockBed, Block, EntityHuman, 
//            MathHelper, World, ItemStack

public class ItemBed extends Item
{

    public ItemBed(int i)
    {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(l != 1)
            return false;
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
        j++;
        BlockBed blockbed = (BlockBed)Block.BED;
        int i1 = MathHelper.floor((double)((entityhuman.yaw * 4F) / 360F) + 0.5D) & 3;
        byte b0 = 0;
        byte b1 = 0;
        if(i1 == 0)
            b1 = 1;
        if(i1 == 1)
            b0 = -1;
        if(i1 == 2)
            b1 = -1;
        if(i1 == 3)
            b0 = 1;
        if(entityhuman.c(i, j, k) && entityhuman.c(i + b0, j, k + b1))
        {
            if(world.isEmpty(i, j, k) && world.isEmpty(i + b0, j, k + b1) && world.e(i, j - 1, k) && world.e(i + b0, j - 1, k + b1))
            {
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
                world.setTypeIdAndData(i, j, k, blockbed.id, i1);
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, blockbed);
                if(event.isCancelled() || !event.canBuild())
                {
                    event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
                    return false;
                } else
                {
                    world.setTypeIdAndData(i + b0, j, k + b1, blockbed.id, i1 + 8);
                    itemstack.count--;
                    return true;
                }
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }
}
