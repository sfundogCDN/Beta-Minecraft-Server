// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemHoe.java

package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

// Referenced classes of package net.minecraft.server:
//            Item, EnumToolMaterial, EntityHuman, World, 
//            Block, BlockGrass, StepSound, ItemStack

public class ItemHoe extends Item
{

    public ItemHoe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i);
        maxStackSize = 1;
        d(enumtoolmaterial.a());
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(!entityhuman.c(i, j, k))
            return false;
        int i1 = world.getTypeId(i, j, k);
        int j1 = world.getTypeId(i, j + 1, k);
        if((l == 0 || j1 != 0 || i1 != Block.GRASS.id) && i1 != Block.DIRT.id)
            return false;
        net.minecraft.server.Block block = Block.SOIL;
        world.makeSound((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
        if(world.isStatic)
            return true;
        CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
        world.setTypeId(i, j, k, block.id);
        BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k, block);
        if(event.isCancelled() || !event.canBuild())
        {
            event.getBlockPlaced().setTypeId(blockState.getTypeId());
            return false;
        } else
        {
            itemstack.damage(1, entityhuman);
            return true;
        }
    }
}
