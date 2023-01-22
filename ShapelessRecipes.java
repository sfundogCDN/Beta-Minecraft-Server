// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            CraftingRecipe, InventoryCrafting, ItemStack

public class ShapelessRecipes
    implements CraftingRecipe
{

    public ShapelessRecipes(ItemStack itemstack, List list)
    {
        a = itemstack;
        b = list;
    }

    public ItemStack b()
    {
        return a;
    }

    public boolean a(InventoryCrafting inventorycrafting)
    {
        ArrayList arraylist = new ArrayList(b);
        int i = 0;
        do
        {
            if(i >= 3)
                break;
            for(int j = 0; j < 3; j++)
            {
                ItemStack itemstack = inventorycrafting.b(j, i);
                if(itemstack == null)
                    continue;
                boolean flag = false;
                Iterator iterator = arraylist.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    ItemStack itemstack1 = (ItemStack)iterator.next();
                    if(itemstack.id != itemstack1.id || itemstack1.getData() != -1 && itemstack.getData() != itemstack1.getData())
                        continue;
                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                } while(true);
                if(!flag)
                    return false;
            }

            i++;
        } while(true);
        return arraylist.isEmpty();
    }

    public ItemStack b(InventoryCrafting inventorycrafting)
    {
        return a.cloneItemStack();
    }

    public int a()
    {
        return b.size();
    }

    private final ItemStack a;
    private final List b;
}
