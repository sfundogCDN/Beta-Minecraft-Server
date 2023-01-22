// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Slot, EntityHuman, ItemStack, Block, 
//            AchievementList, Item, IInventory

public class SlotResult extends Slot
{

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k)
    {
        super(iinventory1, i, j, k);
        f = entityhuman;
        e = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack)
    {
        return false;
    }

    public void a(ItemStack itemstack)
    {
        itemstack.c(f.world, f);
        if(itemstack.id == Block.WORKBENCH.id)
            f.a(AchievementList.h, 1);
        else
        if(itemstack.id == Item.WOOD_PICKAXE.id)
            f.a(AchievementList.i, 1);
        else
        if(itemstack.id == Block.FURNACE.id)
            f.a(AchievementList.j, 1);
        else
        if(itemstack.id == Item.WOOD_HOE.id)
            f.a(AchievementList.l, 1);
        else
        if(itemstack.id == Item.BREAD.id)
            f.a(AchievementList.m, 1);
        else
        if(itemstack.id == Item.CAKE.id)
            f.a(AchievementList.n, 1);
        else
        if(itemstack.id == Item.STONE_PICKAXE.id)
            f.a(AchievementList.o, 1);
        else
        if(itemstack.id == Item.WOOD_SWORD.id)
            f.a(AchievementList.r, 1);
        for(int i = 0; i < e.getSize(); i++)
        {
            ItemStack itemstack1 = e.getItem(i);
            if(itemstack1 == null)
                continue;
            e.splitStack(i, 1);
            if(itemstack1.getItem().i())
                e.setItem(i, new ItemStack(itemstack1.getItem().h()));
        }

    }

    private final IInventory e;
    private EntityHuman f;
}
