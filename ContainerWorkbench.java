// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContainerWorkbench.java

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, InventoryCrafting, InventoryCraftResult, SlotResult, 
//            Slot, EntityPlayer, Packet103SetSlot, ItemStack, 
//            InventoryPlayer, CraftingManager, IInventory, NetServerHandler, 
//            World, EntityHuman, Block

public class ContainerWorkbench extends Container
{

    public ContainerWorkbench(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        craftInventory = new InventoryCrafting(this, 3, 3);
        resultInventory = new InventoryCraftResult();
        c = world;
        h = i;
        this.i = j;
        this.j = k;
        a(new SlotResult(inventoryplayer.d, craftInventory, resultInventory, 0, 124, 35));
        for(int l = 0; l < 3; l++)
        {
            for(int i1 = 0; i1 < 3; i1++)
                a(new Slot(craftInventory, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));

        }

        for(int l = 0; l < 3; l++)
        {
            for(int i1 = 0; i1 < 9; i1++)
                a(new Slot(inventoryplayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));

        }

        for(int l = 0; l < 9; l++)
            a(new Slot(inventoryplayer, l, 8 + l * 18, 142));

        a(craftInventory);
    }

    public void a(IInventory iinventory)
    {
        ItemStack craftResult = CraftingManager.getInstance().craft(craftInventory);
        resultInventory.setItem(0, craftResult);
        if(super.listeners.size() < 1)
        {
            return;
        } else
        {
            EntityPlayer player = (EntityPlayer)super.listeners.get(0);
            player.netServerHandler.sendPacket(new Packet103SetSlot(player.activeContainer.windowId, 0, craftResult));
            return;
        }
    }

    public void a(EntityHuman entityhuman)
    {
        super.a(entityhuman);
        if(!c.isStatic)
        {
            for(int i = 0; i < 9; i++)
            {
                ItemStack itemstack = craftInventory.getItem(i);
                if(itemstack != null)
                    entityhuman.b(itemstack);
            }

        }
    }

    public boolean b(EntityHuman entityhuman)
    {
        return c.getTypeId(h, i, j) == Block.WORKBENCH.id ? entityhuman.e((double)h + 0.5D, (double)i + 0.5D, (double)j + 0.5D) <= 64D : false;
    }

    public ItemStack a(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)e.get(i);
        if(slot != null && slot.b())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();
            if(i == 0)
            {
                if(!a(itemstack1, 10, 46, true))
                    return null;
            } else
            if(i >= 10 && i < 37)
            {
                if(!a(itemstack1, 37, 46, false))
                    return null;
            } else
            if(i >= 37 && i < 46)
            {
                if(!a(itemstack1, 10, 37, false))
                    return null;
            } else
            if(!a(itemstack1, 10, 46, false))
                return null;
            if(itemstack1.count == 0)
                slot.c((ItemStack)null);
            else
                slot.c();
            if(itemstack1.count == itemstack.count)
                return null;
            slot.a(itemstack1);
        }
        return itemstack;
    }

    public InventoryCrafting craftInventory;
    public IInventory resultInventory;
    private World c;
    private int h;
    private int i;
    private int j;
}
