// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Slot, ItemStack, ItemArmor, Item, 
//            Block, ContainerPlayer, IInventory

class SlotArmor extends Slot
{

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l)
    {
        f = containerplayer;
        e = l;
        super(iinventory, i, j, k);
    }

    public int d()
    {
        return 1;
    }

    public boolean isAllowed(ItemStack itemstack)
    {
        if(itemstack.getItem() instanceof ItemArmor)
            return ((ItemArmor)itemstack.getItem()).bt == e;
        if(itemstack.getItem().id == Block.PUMPKIN.id)
            return e == 0;
        else
            return false;
    }

    final int e;
    final ContainerPlayer f;
}
