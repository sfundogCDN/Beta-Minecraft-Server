// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InventoryLargeChest.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, IInventory, EntityHuman

public class InventoryLargeChest
    implements IInventory
{

    public ItemStack[] getContents()
    {
        ItemStack result[] = new ItemStack[getSize()];
        for(int i = 0; i < result.length; i++)
            result[i] = getItem(i);

        return result;
    }

    public InventoryLargeChest(String s, IInventory iinventory, IInventory iinventory1)
    {
        a = s;
        if(iinventory == null)
            iinventory = iinventory1;
        if(iinventory1 == null)
            iinventory1 = iinventory;
        b = iinventory;
        c = iinventory1;
    }

    public int getSize()
    {
        return b.getSize() + c.getSize();
    }

    public String getName()
    {
        return a;
    }

    public ItemStack getItem(int i)
    {
        return i < b.getSize() ? b.getItem(i) : c.getItem(i - b.getSize());
    }

    public ItemStack splitStack(int i, int j)
    {
        return i < b.getSize() ? b.splitStack(i, j) : c.splitStack(i - b.getSize(), j);
    }

    public void setItem(int i, ItemStack itemstack)
    {
        if(i >= b.getSize())
            c.setItem(i - b.getSize(), itemstack);
        else
            b.setItem(i, itemstack);
    }

    public int getMaxStackSize()
    {
        return b.getMaxStackSize();
    }

    public void update()
    {
        b.update();
        c.update();
    }

    public boolean a(EntityHuman entityhuman)
    {
        return b.a(entityhuman) && c.a(entityhuman);
    }

    public void e()
    {
        b.e();
        c.e();
    }

    public void t_()
    {
        b.t_();
        c.t_();
    }

    private String a;
    private IInventory b;
    private IInventory c;
}
