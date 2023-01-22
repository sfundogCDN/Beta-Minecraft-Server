// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityDispenser.java

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            TileEntity, ItemStack, NBTTagCompound, NBTTagList, 
//            IInventory, World, EntityHuman

public class TileEntityDispenser extends TileEntity
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public TileEntityDispenser()
    {
        items = new ItemStack[9];
        b = new Random();
    }

    public int getSize()
    {
        return 9;
    }

    public ItemStack getItem(int i)
    {
        return items[i];
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
                update();
                return itemstack;
            }
            itemstack = items[i].a(j);
            if(items[i].count == 0)
                items[i] = null;
            update();
            return itemstack;
        } else
        {
            return null;
        }
    }

    public int findDispenseSlot()
    {
        int i = -1;
        int j = 1;
        for(int k = 0; k < items.length; k++)
            if(items[k] != null && b.nextInt(j++) == 0 && items[k].count != 0)
                i = k;

        return i;
    }

    public ItemStack b()
    {
        int i = findDispenseSlot();
        if(i >= 0)
            return splitStack(i, 1);
        else
            return null;
    }

    public void setItem(int i, ItemStack itemstack)
    {
        items[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxStackSize())
            itemstack.count = getMaxStackSize();
        update();
    }

    public String getName()
    {
        return "Trap";
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.l("Items");
        items = new ItemStack[getSize()];
        for(int i = 0; i < nbttaglist.c(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(i);
            int j = nbttagcompound1.c("Slot") & 0xff;
            if(j >= 0 && j < items.length)
                items[j] = ItemStack.a(nbttagcompound1);
        }

    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.a("Slot", (byte)i);
                items[i].b(nbttagcompound1);
                nbttaglist.a(nbttagcompound1);
            }

        nbttagcompound.a("Items", nbttaglist);
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public boolean a(EntityHuman entityhuman)
    {
        return world.getTileEntity(x, y, z) == this ? entityhuman.e((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D) <= 64D : false;
    }

    public void e()
    {
    }

    public void t_()
    {
    }

    private ItemStack items[];
    private Random b;
}
