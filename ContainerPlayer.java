// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContainerPlayer.java

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, InventoryCrafting, InventoryCraftResult, SlotResult, 
//            Slot, SlotArmor, EntityPlayer, Packet103SetSlot, 
//            ItemStack, InventoryPlayer, CraftingManager, IInventory, 
//            NetServerHandler, EntityHuman

public class ContainerPlayer extends Container
{

    public ContainerPlayer(InventoryPlayer inventoryplayer)
    {
        this(inventoryplayer, true);
    }

    public ContainerPlayer(InventoryPlayer inventoryplayer, boolean flag)
    {
        craftInventory = new InventoryCrafting(this, 2, 2);
        resultInventory = new InventoryCraftResult();
        c = false;
        c = flag;
        a(new SlotResult(inventoryplayer.d, craftInventory, resultInventory, 0, 144, 36));
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
                a(new Slot(craftInventory, j + i * 2, 88 + j * 18, 26 + i * 18));

        }

        for(int i = 0; i < 4; i++)
            a(new SlotArmor(this, inventoryplayer, inventoryplayer.getSize() - 1 - i, 8, 8 + i * 18, i));

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
                a(new Slot(inventoryplayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));

        }

        for(int i = 0; i < 9; i++)
            a(new Slot(inventoryplayer, i, 8 + i * 18, 142));

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
        for(int i = 0; i < 4; i++)
        {
            ItemStack itemstack = craftInventory.getItem(i);
            if(itemstack != null)
            {
                entityhuman.b(itemstack);
                craftInventory.setItem(i, (ItemStack)null);
            }
        }

    }

    public boolean b(EntityHuman entityhuman)
    {
        return true;
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
                if(!a(itemstack1, 9, 45, true))
                    return null;
            } else
            if(i >= 9 && i < 36)
            {
                if(!a(itemstack1, 36, 45, false))
                    return null;
            } else
            if(i >= 36 && i < 45)
            {
                if(!a(itemstack1, 9, 36, false))
                    return null;
            } else
            if(!a(itemstack1, 9, 45, false))
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
    public boolean c;
}
