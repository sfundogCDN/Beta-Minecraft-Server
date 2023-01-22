// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Item, ItemStack, CraftingManager

public class RecipesWeapons
{

    public RecipesWeapons()
    {
        b = (new Object[][] {
            new Object[] {
                Block.WOOD, Block.COBBLESTONE, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_INGOT
            }, new Object[] {
                Item.WOOD_SWORD, Item.STONE_SWORD, Item.IRON_SWORD, Item.DIAMOND_SWORD, Item.GOLD_SWORD
            }
        });
    }

    public void a(CraftingManager craftingmanager)
    {
        for(int i = 0; i < b[0].length; i++)
        {
            Object obj = b[0][i];
            for(int j = 0; j < b.length - 1; j++)
            {
                Item item = (Item)b[j + 1][i];
                craftingmanager.registerShapedRecipe(new ItemStack(item), new Object[] {
                    a[j], Character.valueOf('#'), Item.STICK, Character.valueOf('X'), obj
                });
            }

        }

        craftingmanager.registerShapedRecipe(new ItemStack(Item.BOW, 1), new Object[] {
            " #X", "# X", " #X", Character.valueOf('X'), Item.STRING, Character.valueOf('#'), Item.STICK
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Item.ARROW, 4), new Object[] {
            "X", "#", "Y", Character.valueOf('Y'), Item.FEATHER, Character.valueOf('X'), Item.FLINT, Character.valueOf('#'), Item.STICK
        });
    }

    private String a[][] = {
        {
            "X", "X", "#"
        }
    };
    private Object b[][];
}
