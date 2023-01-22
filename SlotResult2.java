// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Slot, EntityHuman, ItemStack, Item, 
//            AchievementList, IInventory

public class SlotResult2 extends Slot
{

    public SlotResult2(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        e = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack)
    {
        return false;
    }

    public void a(ItemStack itemstack)
    {
        itemstack.c(e.world, e);
        if(itemstack.id == Item.IRON_INGOT.id)
            e.a(AchievementList.k, 1);
        if(itemstack.id == Item.COOKED_FISH.id)
            e.a(AchievementList.p, 1);
        super.a(itemstack);
    }

    private EntityHuman e;
}
