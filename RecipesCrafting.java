// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, Block, CraftingManager, Item

public class RecipesCrafting
{

    public RecipesCrafting()
    {
    }

    public void a(CraftingManager craftingmanager)
    {
        craftingmanager.registerShapedRecipe(new ItemStack(Block.CHEST), new Object[] {
            "###", "# #", "###", Character.valueOf('#'), Block.WOOD
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.FURNACE), new Object[] {
            "###", "# #", "###", Character.valueOf('#'), Block.COBBLESTONE
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.WORKBENCH), new Object[] {
            "##", "##", Character.valueOf('#'), Block.WOOD
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SANDSTONE), new Object[] {
            "##", "##", Character.valueOf('#'), Block.SAND
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SMOOTH_BRICK, 4), new Object[] {
            "##", "##", Character.valueOf('#'), Block.STONE
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.IRON_FENCE, 16), new Object[] {
            "###", "###", Character.valueOf('#'), Item.IRON_INGOT
        });
        craftingmanager.registerShapedRecipe(new ItemStack(Block.THIN_GLASS, 16), new Object[] {
            "###", "###", Character.valueOf('#'), Block.GLASS
        });
    }
}
