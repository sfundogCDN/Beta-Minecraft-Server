// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Item, ItemStack, CraftingManager

public class RecipesTools
{

    public RecipesTools()
    {
        b = (new Object[][] {
            new Object[] {
                Block.WOOD, Block.COBBLESTONE, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_INGOT
            }, new Object[] {
                Item.WOOD_PICKAXE, Item.STONE_PICKAXE, Item.IRON_PICKAXE, Item.DIAMOND_PICKAXE, Item.GOLD_PICKAXE
            }, new Object[] {
                Item.WOOD_SPADE, Item.STONE_SPADE, Item.IRON_SPADE, Item.DIAMOND_SPADE, Item.GOLD_SPADE
            }, new Object[] {
                Item.WOOD_AXE, Item.STONE_AXE, Item.IRON_AXE, Item.DIAMOND_AXE, Item.GOLD_AXE
            }, new Object[] {
                Item.WOOD_HOE, Item.STONE_HOE, Item.IRON_HOE, Item.DIAMOND_HOE, Item.GOLD_HOE
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

        craftingmanager.registerShapedRecipe(new ItemStack(Item.SHEARS), new Object[] {
            " #", "# ", Character.valueOf('#'), Item.IRON_INGOT
        });
    }

    private String a[][] = {
        {
            "XXX", " # ", " # "
        }, {
            "X", "#", "#"
        }, {
            "XX", "X#", " #"
        }, {
            "XX", " #", " #"
        }
    };
    private Object b[][];
}
