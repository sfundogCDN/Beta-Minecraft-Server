// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, Slot, TileEntityDispenser, ItemStack, 
//            IInventory, EntityHuman

public class ContainerDispenser extends Container
{

    public ContainerDispenser(IInventory iinventory, TileEntityDispenser tileentitydispenser)
    {
        a = tileentitydispenser;
        for(int i = 0; i < 3; i++)
        {
            for(int l = 0; l < 3; l++)
                a(new Slot(tileentitydispenser, l + i * 3, 62 + l * 18, 17 + i * 18));

        }

        for(int j = 0; j < 3; j++)
        {
            for(int i1 = 0; i1 < 9; i1++)
                a(new Slot(iinventory, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));

        }

        for(int k = 0; k < 9; k++)
            a(new Slot(iinventory, k, 8 + k * 18, 142));

    }

    public boolean b(EntityHuman entityhuman)
    {
        return a.a(entityhuman);
    }

    public ItemStack a(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)e.get(i);
        if(slot != null && slot.b())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();
            if(i < 9)
            {
                if(!a(itemstack1, 9, 45, true))
                    return null;
            } else
            if(!a(itemstack1, 0, 9, false))
                return null;
            if(itemstack1.count == 0)
                slot.c(null);
            else
                slot.c();
            if(itemstack1.count != itemstack.count)
                slot.a(itemstack1);
            else
                return null;
        }
        return itemstack;
    }

    private TileEntityDispenser a;
}
