// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Comparator;

// Referenced classes of package net.minecraft.server:
//            ShapelessRecipes, ShapedRecipes, CraftingRecipe, CraftingManager

class RecipeSorter
    implements Comparator
{

    RecipeSorter(CraftingManager craftingmanager)
    {
        a = craftingmanager;
        super();
    }

    public int a(CraftingRecipe craftingrecipe, CraftingRecipe craftingrecipe1)
    {
        if((craftingrecipe instanceof ShapelessRecipes) && (craftingrecipe1 instanceof ShapedRecipes))
            return 1;
        if((craftingrecipe1 instanceof ShapelessRecipes) && (craftingrecipe instanceof ShapedRecipes))
            return -1;
        if(craftingrecipe1.a() < craftingrecipe.a())
            return -1;
        return craftingrecipe1.a() <= craftingrecipe.a() ? 0 : 1;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return a((CraftingRecipe)obj, (CraftingRecipe)obj1);
    }

    final CraftingManager a;
}
