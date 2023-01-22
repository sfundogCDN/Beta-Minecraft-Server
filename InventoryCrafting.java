// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InventoryCrafting.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, IInventory, Container, EntityHuman

public class InventoryCrafting
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public InventoryCrafting(Container container, int i, int j)
    {
        int k = i * j;
        items = new ItemStack[k];
        c = container;
        b = i;
    }

    public int getSize()
    {
        return items.length;
    }

    public ItemStack getItem(int i)
    {
        return i < getSize() ? items[i] : null;
    }

    public ItemStack b(int i, int j)
    {
        if(i >= 0 && i < b)
        {
            int k = i + j * b;
            return getItem(k);
        } else
        {
            return null;
        }
    }

    public String getName()
    {
        return "Crafting";
    }

    public ItemStack splitStack(int i, int j)
    {
        if(items[i] != null)
        {
            ItemStack itemstack;
            if(items[i].count <= j)
            {
                itemstack = items[i];
                items[i] = null;
                c.a(this);
                return itemstack;
            }
            itemstack = items[i].a(j);
            if(items[i].count == 0)
                items[i] = null;
            c.a(this);
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack)
    {
        items[i] = itemstack;
        c.a(this);
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
    private int b;
    private Container c;
}
