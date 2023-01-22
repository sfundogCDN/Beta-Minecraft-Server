// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, ItemStack, Item, CraftingManager

public class RecipeIngots
{

    public RecipeIngots()
    {
        a = (new Object[][] {
            new Object[] {
                Block.GOLD_BLOCK, new ItemStack(Item.GOLD_INGOT, 9)
            }, new Object[] {
                Block.IRON_BLOCK, new ItemStack(Item.IRON_INGOT, 9)
            }, new Object[] {
                Block.DIAMOND_BLOCK, new ItemStack(Item.DIAMOND, 9)
            }, new Object[] {
                Block.LAPIS_BLOCK, new ItemStack(Item.INK_SACK, 9, 4)
            }
        });
    }

    public void a(CraftingManager craftingmanager)
    {
        for(int i = 0; i < a.length; i++)
        {
            Block block = (Block)a[i][0];
            ItemStack itemstack = (ItemStack)a[i][1];
            craftingmanager.registerShapedRecipe(new ItemStack(block), new Object[] {
                "###", "###", "###", Character.valueOf('#'), itemstack
            });
            craftingmanager.registerShapedRecipe(itemstack, new Object[] {
                "#", Character.valueOf('#'), block
            });
        }

    }

    private Object a[][];
}
