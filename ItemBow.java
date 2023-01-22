// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Item, EntityHuman, InventoryPlayer, EntityArrow, 
//            World, EnumAnimation, ItemStack

public class ItemBow extends Item
{

    public ItemBow(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i)
    {
        if(entityhuman.inventory.c(Item.ARROW.id))
        {
            int j = c(itemstack) - i;
            float f = (float)j / 20F;
            f = (f * f + f * 2.0F) / 3F;
            if((double)f < 0.10000000000000001D)
                return;
            if(f > 1.0F)
                f = 1.0F;
            EntityArrow entityarrow = new EntityArrow(world, entityhuman, f * 2.0F);
            if(f == 1.0F)
                entityarrow.d = true;
            world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (b.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            entityhuman.inventory.b(Item.ARROW.id);
            if(!world.isStatic)
                world.addEntity(entityarrow);
        }
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        return itemstack;
    }

    public int c(ItemStack itemstack)
    {
        return 0x11940;
    }

    public EnumAnimation b(ItemStack itemstack)
    {
        return EnumAnimation.d;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        if(entityhuman.inventory.c(Item.ARROW.id))
            entityhuman.a(itemstack, c(itemstack));
        return itemstack;
    }
}
