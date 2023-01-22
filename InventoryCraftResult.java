// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InventoryCraftResult.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, IInventory, EntityHuman

public class InventoryCraftResult
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public InventoryCraftResult()
    {
        items = new ItemStack[1];
    }

    public int getSize()
    {
        return 1;
    }

    public ItemStack getItem(int i)
    {
        return items[i];
    }

    public String getName()
    {
        return "Result";
    }

    public ItemStack splitStack(int i, int j)
    {
        if(items[i] != null)
        {
            ItemStack itemstack = items[i];
            items[i] = null;
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack)
    {
        items[i] = itemstack;
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public void update()
    {
    }

    public boolean a(EntityHuman entityhuman)
    {
        return true;
    }

    public void e()
    {
    }

    public void t_()
    {
    }

    private ItemStack items[];
}
