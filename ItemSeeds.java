// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemSeeds.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, EntityHuman, World, Block, 
//            ItemStack

public class ItemSeeds extends Item
{

    public ItemSeeds(int i, int j)
    {
        super(i);
        id = j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(l != 1)
            return false;
        if(entityhuman.c(i, j, k) && entityhuman.c(i, j + 1, k))
        {
            int i1 = world.getTypeId(i, j, k);
            if(i1 == Block.SOIL.id && world.isEmpty(i, j + 1, k))
            {
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j + 1, k);
                world.setTypeId(i, j + 1, k, id);
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k, id);
                if(event.isCancelled() || !event.canBuild())
                {
                    event.getBlockPlaced().setTypeId(0);
                    return false;
                } else
                {
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

    private int id;
}
