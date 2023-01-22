// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Slot.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            IInventory, ItemStack

public class Slot
{

    public Slot(IInventory iinventory, int i, int j, int k)
    {
        inventory = iinventory;
        index = i;
        c = j;
        d = k;
    }

    public void a(ItemStack itemstack)
    {
        c();
    }

    public boolean isAllowed(ItemStack itemstack)
    {
        return true;
    }

    public ItemStack getItem()
    {
        return inventory.getItem(index);
    }

    public boolean b()
    {
        return getItem() != null;
    }

    public void c(ItemStack itemstack)
    {
        inventory.setItem(index, itemstack);
        c();
    }

    public void c()
    {
        inventory.update();
    }

    public int d()
    {
        return inventory.getMaxStackSize();
    }

    public ItemStack a(int i)
    {
        return inventory.splitStack(index, i);
    }

    public boolean a(IInventory iinventory, int i)
    {
        return iinventory == inventory && i == index;
    }

    public final int index;
    public final IInventory inventory;
    public int b;
    public int c;
    public int d;
}
