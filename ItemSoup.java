// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemFood, ItemStack, Item, World, 
//            EntityHuman

public class ItemSoup extends ItemFood
{

    public ItemSoup(int i, int j)
    {
        super(i, j, false);
        c(1);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        super.b(itemstack, world, entityhuman);
        return new ItemStack(Item.BOWL);
    }
}
