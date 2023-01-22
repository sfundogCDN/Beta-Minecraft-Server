// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            Slot, ICrafting, ItemStack, EntityHuman, 
//            InventoryPlayer, IInventory

public abstract class Container
{

    public Container()
    {
        d = new ArrayList();
        e = new ArrayList();
        windowId = 0;
        a = 0;
        listeners = new ArrayList();
        b = new HashSet();
    }

    protected void a(Slot slot)
    {
        slot.b = e.size();
        e.add(slot);
        d.add(null);
    }

    public void a(ICrafting icrafting)
    {
        if(listeners.contains(icrafting))
        {
            throw new IllegalArgumentException("Listener already listening");
        } else
        {
            listeners.add(icrafting);
            icrafting.a(this, b());
            a();
            return;
        }
    }

    public List b()
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < e.size(); i++)
            arraylist.add(((Slot)e.get(i)).getItem());

        return arraylist;
    }

    public void a()
    {
        for(int i = 0; i < e.size(); i++)
        {
            ItemStack itemstack = ((Slot)e.get(i)).getItem();
            ItemStack itemstack1 = (ItemStack)d.get(i);
            if(ItemStack.equals(itemstack1, itemstack))
                continue;
            itemstack1 = itemstack != null ? itemstack.cloneItemStack() : null;
            d.set(i, itemstack1);
            for(int j = 0; j < listeners.size(); j++)
                ((ICrafting)listeners.get(j)).a(this, i, itemstack1);

        }

    }

    public Slot a(IInventory iinventory, int i)
    {
        for(int j = 0; j < e.size(); j++)
        {
            Slot slot = (Slot)e.get(j);
            if(slot.a(iinventory, i))
                return slot;
        }

        return null;
    }

    public Slot b(int i)
    {
        return (Slot)e.get(i);
    }

    public ItemStack a(int i)
    {
        Slot slot = (Slot)e.get(i);
        if(slot != null)
            return slot.getItem();
        else
            return null;
    }

    public ItemStack a(int i, int j, boolean flag, EntityHuman entityhuman)
    {
        ItemStack itemstack = null;
        if(j > 1)
            return null;
        if(j == 0 || j == 1)
        {
            InventoryPlayer inventoryplayer = entityhuman.inventory;
            if(i == -999)
            {
                if(inventoryplayer.l() != null && i == -999)
                {
                    if(j == 0)
                    {
                        entityhuman.b(inventoryplayer.l());
                        inventoryplayer.b(null);
                    }
                    if(j == 1)
                    {
                        entityhuman.b(inventoryplayer.l().a(1));
                        if(inventoryplayer.l().count == 0)
                            inventoryplayer.b(null);
                    }
                }
            } else
            if(flag)
            {
                ItemStack itemstack1 = a(i);
                if(itemstack1 != null)
                {
                    int k = itemstack1.id;
                    itemstack = itemstack1.cloneItemStack();
                    Slot slot1 = (Slot)e.get(i);
                    if(slot1 != null && slot1.getItem() != null && slot1.getItem().id == k)
                        b(i, j, flag, entityhuman);
                }
            } else
            {
                if(i < 0)
                    return null;
                Slot slot = (Slot)e.get(i);
                if(slot != null)
                {
                    slot.c();
                    ItemStack itemstack2 = slot.getItem();
                    ItemStack itemstack3 = inventoryplayer.l();
                    if(itemstack2 != null)
                        itemstack = itemstack2.cloneItemStack();
                    if(itemstack2 == null)
                    {
                        if(itemstack3 != null && slot.isAllowed(itemstack3))
                        {
                            int l = j != 0 ? 1 : itemstack3.count;
                            if(l > slot.d())
                                l = slot.d();
                            slot.c(itemstack3.a(l));
                            if(itemstack3.count == 0)
                                inventoryplayer.b(null);
                        }
                    } else
                    if(itemstack3 == null)
                    {
                        int i1 = j != 0 ? (itemstack2.count + 1) / 2 : itemstack2.count;
                        ItemStack itemstack5 = slot.a(i1);
                        inventoryplayer.b(itemstack5);
                        if(itemstack2.count == 0)
                            slot.c(null);
                        slot.a(inventoryplayer.l());
                    } else
                    if(slot.isAllowed(itemstack3))
                    {
                        if(itemstack2.id != itemstack3.id || itemstack2.usesData() && itemstack2.getData() != itemstack3.getData())
                        {
                            if(itemstack3.count <= slot.d())
                            {
                                ItemStack itemstack4 = itemstack2;
                                slot.c(itemstack3);
                                inventoryplayer.b(itemstack4);
                            }
                        } else
                        {
                            int j1 = j != 0 ? 1 : itemstack3.count;
                            if(j1 > slot.d() - itemstack2.count)
                                j1 = slot.d() - itemstack2.count;
                            if(j1 > itemstack3.getMaxStackSize() - itemstack2.count)
                                j1 = itemstack3.getMaxStackSize() - itemstack2.count;
                            itemstack3.a(j1);
                            if(itemstack3.count == 0)
                                inventoryplayer.b(null);
                            itemstack2.count += j1;
                        }
                    } else
                    if(itemstack2.id == itemstack3.id && itemstack3.getMaxStackSize() > 1 && (!itemstack2.usesData() || itemstack2.getData() == itemstack3.getData()))
                    {
                        int k1 = itemstack2.count;
                        if(k1 > 0 && k1 + itemstack3.count <= itemstack3.getMaxStackSize())
                        {
                            itemstack3.count += k1;
                            itemstack2.a(k1);
                            if(itemstack2.count == 0)
                                slot.c(null);
                            slot.a(inventoryplayer.l());
                        }
                    }
                }
            }
        }
        return itemstack;
    }

    protected void b(int i, int j, boolean flag, EntityHuman entityhuman)
    {
        a(i, j, flag, entityhuman);
    }

    public void a(EntityHuman entityhuman)
    {
        InventoryPlayer inventoryplayer = entityhuman.inventory;
        if(inventoryplayer.l() != null)
        {
            entityhuman.b(inventoryplayer.l());
            inventoryplayer.b(null);
        }
    }

    public void a(IInventory iinventory)
    {
        a();
    }

    public void a(int i, ItemStack itemstack)
    {
        b(i).c(itemstack);
    }

    public boolean c(EntityHuman entityhuman)
    {
        return !b.contains(entityhuman);
    }

    public void a(EntityHuman entityhuman, boolean flag)
    {
        if(flag)
            b.remove(entityhuman);
        else
            b.add(entityhuman);
    }

    public abstract boolean b(EntityHuman entityhuman);

    protected boolean a(ItemStack itemstack, int i, int j, boolean flag)
    {
        boolean flag1 = false;
        int k = i;
        if(flag)
            k = j - 1;
        if(itemstack.isStackable())
            while(itemstack.count > 0 && (!flag && k < j || flag && k >= i)) 
            {
                Slot slot = (Slot)e.get(k);
                ItemStack itemstack1 = slot.getItem();
                if(itemstack1 != null && itemstack1.id == itemstack.id && (!itemstack.usesData() || itemstack.getData() == itemstack1.getData()))
                {
                    int i1 = itemstack1.count + itemstack.count;
                    if(i1 <= itemstack.getMaxStackSize())
                    {
                        itemstack.count = 0;
                        itemstack1.count = i1;
                        slot.c();
                        flag1 = true;
                    } else
                    if(itemstack1.count < itemstack.getMaxStackSize())
                    {
                        itemstack.count -= itemstack.getMaxStackSize() - itemstack1.count;
                        itemstack1.count = itemstack.getMaxStackSize();
                        slot.c();
                        flag1 = true;
                    }
                }
                if(flag)
                    k--;
                else
                    k++;
            }
        if(itemstack.count > 0)
        {
            int l;
            if(flag)
                l = j - 1;
            else
                l = i;
            do
            {
                if((flag || l >= j) && (!flag || l < i))
                    break;
                Slot slot1 = (Slot)e.get(l);
                ItemStack itemstack2 = slot1.getItem();
                if(itemstack2 == null)
                {
                    slot1.c(itemstack.cloneItemStack());
                    slot1.c();
                    itemstack.count = 0;
                    flag1 = true;
                    break;
                }
                if(flag)
                    l--;
                else
                    l++;
            } while(true);
        }
        return flag1;
    }

    public List d;
    public List e;
    public int windowId;
    private short a;
    protected List listeners;
    private Set b;
}
