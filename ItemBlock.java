// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemBlock.java

package net.minecraft.server;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, Block, World, ItemStack, 
//            EntityHuman, Material, StepSound

public class ItemBlock extends Item
{

    public ItemBlock(int i)
    {
        super(i);
        id = i + 256;
        b(Block.byId[i + 256].a(2));
    }

    public int a()
    {
        return id;
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
        if(itemstack.count == 0)
            return false;
        if(!entityhuman.c(i, j, k))
            return false;
        world.getClass();
        if(j == 127 && Block.byId[id].material.isBuildable())
            return false;
        if(world.a(id, i, j, k, false, l))
        {
            Block block = Block.byId[id];
            CraftBlockState replacedBlockState = CraftBlockState.getBlockState(world, i, j, k);
            CraftBlockState blockStateBelow = null;
            boolean eventUseBlockBelow = false;
            if((world.getTypeId(i, j - 1, k) == Block.STEP.id || world.getTypeId(i, j - 1, k) == Block.DOUBLE_STEP.id) && (itemstack.id == Block.DOUBLE_STEP.id || itemstack.id == Block.STEP.id))
            {
                blockStateBelow = CraftBlockState.getBlockState(world, i, j - 1, k);
                eventUseBlockBelow = itemstack.id == Block.STEP.id && blockStateBelow.getTypeId() == Block.STEP.id;
            }
            if(world.setRawTypeIdAndData(i, j, k, id, filterData(itemstack.getData())))
            {
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, eventUseBlockBelow ? ((org.bukkit.block.BlockState) (blockStateBelow)) : ((org.bukkit.block.BlockState) (replacedBlockState)), clickedX, clickedY, clickedZ, block);
                if(event.isCancelled() || !event.canBuild())
                {
                    if(blockStateBelow != null)
                    {
                        world.setTypeIdAndData(i, j, k, replacedBlockState.getTypeId(), replacedBlockState.getRawData());
                        world.setTypeIdAndData(i, j - 1, k, blockStateBelow.getTypeId(), blockStateBelow.getRawData());
                    } else
                    {
                        if(id == Block.ICE.id)
                            world.setTypeId(i, j, k, 20);
                        world.setTypeIdAndData(i, j, k, replacedBlockState.getTypeId(), replacedBlockState.getRawData());
                    }
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
            return true;
        } else
        {
            return false;
        }
    }

    public String a(ItemStack itemstack)
    {
        return Block.byId[id].l();
    }

    public String b()
    {
        return Block.byId[id].l();
    }

    private int id;
}
