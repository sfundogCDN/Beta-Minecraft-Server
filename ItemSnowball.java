// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Item, ItemStack, World, EntitySnowball, 
//            EntityHuman

public class ItemSnowball extends Item
{

    public ItemSnowball(int i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        itemstack.count--;
        world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
        if(!world.isStatic)
            world.addEntity(new EntitySnowball(world, entityhuman));
        return itemstack;
    }
}
