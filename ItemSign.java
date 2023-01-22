// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemSign.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, TileEntitySign, World, Material, 
//            EntityHuman, Block, MathHelper, ItemStack

public class ItemSign extends Item
{

    public ItemSign(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(l == 0)
            return false;
        if(!world.getMaterial(i, j, k).isBuildable())
            return false;
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
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
        if(!Block.SIGN_POST.canPlace(world, i, j, k))
            return false;
        CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
        if(l == 1)
        {
            int i1 = MathHelper.floor((double)(((entityhuman.yaw + 180F) * 16F) / 360F) + 0.5D) & 0xf;
            world.setTypeIdAndData(i, j, k, Block.SIGN_POST.id, i1);
        } else
        {
            world.setTypeIdAndData(i, j, k, Block.WALL_SIGN.id, l);
        }
        BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, l != 1 ? Block.WALL_SIGN : Block.SIGN_POST);
        if(event.isCancelled() || !event.canBuild())
        {
            event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
            return false;
        }
        itemstack.count--;
        TileEntitySign tileentitysign = (TileEntitySign)world.getTileEntity(i, j, k);
        if(tileentitysign != null)
            entityhuman.a(tileentitysign);
        return true;
    }
}
