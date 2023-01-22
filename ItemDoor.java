// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemDoor.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, Material, Block, EntityHuman, 
//            MathHelper, ItemStack, World

public class ItemDoor extends Item
{

    public ItemDoor(int i, Material material)
    {
        super(i);
        a = material;
        maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(l != 1)
            return false;
        j++;
        net.minecraft.server.Block block;
        if(a == Material.WOOD)
            block = Block.WOODEN_DOOR;
        else
            block = Block.IRON_DOOR_BLOCK;
        if(entityhuman.c(i, j, k) && entityhuman.c(i, j + 1, k))
        {
            if(!block.canPlace(world, i, j, k))
                return false;
            int i1 = MathHelper.floor((double)(((entityhuman.yaw + 180F) * 4F) / 360F) - 0.5D) & 3;
            if(a(world, i, j, k, i1, block, entityhuman))
            {
                itemstack.count--;
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public static void a(World world, int i, int j, int k, int l, net.minecraft.server.Block block)
    {
        a(world, i, j, k, l, block, ((EntityHuman) (null)));
    }

    public static boolean a(World world, int i, int j, int k, int l, net.minecraft.server.Block block, EntityHuman entityhuman)
    {
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
        byte b0 = 0;
        byte b1 = 0;
        if(l == 0)
            b1 = 1;
        if(l == 1)
            b0 = -1;
        if(l == 2)
            b1 = -1;
        if(l == 3)
            b0 = 1;
        int i1 = (world.e(i - b0, j, k - b1) ? 1 : 0) + (world.e(i - b0, j + 1, k - b1) ? 1 : 0);
        int j1 = (world.e(i + b0, j, k + b1) ? 1 : 0) + (world.e(i + b0, j + 1, k + b1) ? 1 : 0);
        boolean flag = world.getTypeId(i - b0, j, k - b1) == block.id || world.getTypeId(i - b0, j + 1, k - b1) == block.id;
        boolean flag1 = world.getTypeId(i + b0, j, k + b1) == block.id || world.getTypeId(i + b0, j + 1, k + b1) == block.id;
        boolean flag2 = false;
        if(flag && !flag1)
            flag2 = true;
        else
        if(j1 > i1)
            flag2 = true;
        if(flag2)
        {
            l = l - 1 & 3;
            l += 4;
        }
        CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
        world.suppressPhysics = true;
        world.setTypeIdAndData(i, j, k, block.id, l);
        world.suppressPhysics = false;
        world.applyPhysics(i, j, k, block.id);
        if(entityhuman != null)
        {
            BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, block);
            if(event.isCancelled() || !event.canBuild())
            {
                event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
                return false;
            }
        }
        world.suppressPhysics = true;
        world.setTypeIdAndData(i, j + 1, k, block.id, l + 8);
        world.suppressPhysics = false;
        world.applyPhysics(i, j + 1, k, block.id);
        return true;
    }

    private Material a;
}
