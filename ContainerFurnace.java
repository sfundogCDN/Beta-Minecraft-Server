// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, Slot, SlotResult2, InventoryPlayer, 
//            TileEntityFurnace, ICrafting, ItemStack, EntityHuman

public class ContainerFurnace extends Container
{

    public ContainerFurnace(InventoryPlayer inventoryplayer, TileEntityFurnace tileentityfurnace)
    {
        b = 0;
        c = 0;
        h = 0;
        a = tileentityfurnace;
        a(new Slot(tileentityfurnace, 0, 56, 17));
        a(new Slot(tileentityfurnace, 1, 56, 53));
        a(new SlotResult2(inventoryplayer.d, tileentityfurnace, 2, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
                a(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));

        }

        for(int j = 0; j < 9; j++)
            a(new Slot(inventoryplayer, j, 8 + j * 18, 142));

    }

    public void a(ICrafting icrafting)
    {
        super.a(icrafting);
        icrafting.a(this, 0, a.cookTime);
        icrafting.a(this, 1, a.burnTime);
        icrafting.a(this, 2, a.ticksForCurrentFuel);
    }

    public void a()
    {
        super.a();
        for(int i = 0; i < listeners.size(); i++)
        {
            ICrafting icrafting = (ICrafting)listeners.get(i);
            if(b != a.cookTime)
                icrafting.a(this, 0, a.cookTime);
            if(c != a.burnTime)
                icrafting.a(this, 1, a.burnTime);
            if(h != a.ticksForCurrentFuel)
                icrafting.a(this, 2, a.ticksForCurrentFuel);
        }

        b = a.cookTime;
        c = a.burnTime;
        h = a.ticksForCurrentFuel;
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
            if(i == 2)
            {
                if(!a(itemstack1, 3, 39, true))
                    return null;
            } else
            if(i >= 3 && i < 30)
            {
                if(!a(itemstack1, 30, 39, false))
                    return null;
            } else
            if(i >= 30 && i < 39)
            {
                if(!a(itemstack1, 3, 30, false))
                    return null;
            } else
            if(!a(itemstack1, 3, 39, false))
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

    private TileEntityFurnace a;
    private int b;
    private int c;
    private int h;
}
