// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, IInventory, Slot, ItemStack, 
//            EntityHuman

public class ContainerChest extends Container
{

    public ContainerChest(IInventory iinventory, IInventory iinventory1)
    {
        a = iinventory1;
        b = iinventory1.getSize() / 9;
        iinventory1.e();
        int i = (b - 4) * 18;
        for(int j = 0; j < b; j++)
        {
            for(int i1 = 0; i1 < 9; i1++)
                a(new Slot(iinventory1, i1 + j * 9, 8 + i1 * 18, 18 + j * 18));

        }

        for(int k = 0; k < 3; k++)
        {
            for(int j1 = 0; j1 < 9; j1++)
                a(new Slot(iinventory, j1 + k * 9 + 9, 8 + j1 * 18, 103 + k * 18 + i));

        }

        for(int l = 0; l < 9; l++)
            a(new Slot(iinventory, l, 8 + l * 18, 161 + i));

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
            if(i < b * 9)
            {
                if(!a(itemstack1, b * 9, e.size(), true))
                    return null;
            } else
            if(!a(itemstack1, 0, b * 9, false))
                return null;
            if(itemstack1.count == 0)
                slot.c(null);
            else
                slot.c();
        }
        return itemstack;
    }

    public void a(EntityHuman entityhuman)
    {
        super.a(entityhuman);
        a.t_();
    }

    private IInventory a;
    private int b;
}
