// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.server:
//            Block, ItemStack, Item

public class FurnaceRecipes
{

    public static final FurnaceRecipes getInstance()
    {
        return a;
    }

    private FurnaceRecipes()
    {
        b = new HashMap();
        registerRecipe(Block.IRON_ORE.id, new ItemStack(Item.IRON_INGOT));
        registerRecipe(Block.GOLD_ORE.id, new ItemStack(Item.GOLD_INGOT));
        registerRecipe(Block.DIAMOND_ORE.id, new ItemStack(Item.DIAMOND));
        registerRecipe(Block.SAND.id, new ItemStack(Block.GLASS));
        registerRecipe(Item.PORK.id, new ItemStack(Item.GRILLED_PORK));
        registerRecipe(Item.RAW_BEEF.id, new ItemStack(Item.COOKED_BEEF));
        registerRecipe(Item.RAW_CHICKEN.id, new ItemStack(Item.COOKED_CHICKEN));
        registerRecipe(Item.RAW_FISH.id, new ItemStack(Item.COOKED_FISH));
        registerRecipe(Block.COBBLESTONE.id, new ItemStack(Block.STONE));
        registerRecipe(Item.CLAY_BALL.id, new ItemStack(Item.CLAY_BRICK));
        registerRecipe(Block.CACTUS.id, new ItemStack(Item.INK_SACK, 1, 2));
        registerRecipe(Block.LOG.id, new ItemStack(Item.COAL, 1, 1));
    }

    public void registerRecipe(int i, ItemStack itemstack)
    {
        b.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack a(int i)
    {
        return (ItemStack)b.get(Integer.valueOf(i));
    }

    public Map b()
    {
        return b;
    }

    private static final FurnaceRecipes a = new FurnaceRecipes();
    private Map b;

}
