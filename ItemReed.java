// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemReed.java

package net.minecraft.server;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, Block, World, EntityHuman, 
//            ItemStack, StepSound

public class ItemReed extends Item
{

    public ItemReed(int i, Block block)
    {
        super(i);
        id = block.id;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        int clickedX = i;
        int clickedY = j;
        int clickedZ = k;
        int i1 = world.getTypeId(i, j, k);
        if(i1 == Block.SNOW.id)
            l = 0;
        else
        if(i1 != Block.VINE.id)
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
        }
        if(!entityhuman.c(i, j, k))
            return false;
        if(itemstack.count == 0)
            return false;
        if(world.a(id, i, j, k, false, l))
        {
            Block block = Block.byId[id];
            CraftBlockState replacedBlockState = CraftBlockState.getBlockState(world, i, j, k);
            if(world.setRawTypeId(i, j, k, id))
            {
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, replacedBlockState, clickedX, clickedY, clickedZ, block);
                if(event.isCancelled() || !event.canBuild())
                {
                    world.setTypeIdAndData(i, j, k, replacedBlockState.getTypeId(), replacedBlockState.getRawData());
                    return true;
                }
                world.update(i, j, k, id);
                if(world.getTypeId(i, j, k) == id)
                {
                    Block.byId[id].postPlace(world, i, j, k, l);
                    Block.byId[id].postPlace(world, i, j, k, entityhuman);
                }
                world.makeSound((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
                itemstack.count--;
            }
        }
        return true;
    }

    private int id;
}
