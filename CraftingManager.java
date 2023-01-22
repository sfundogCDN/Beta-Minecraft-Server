// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CraftingManager.java

package net.minecraft.server;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            RecipesTools, RecipesWeapons, RecipeIngots, RecipesFood, 
//            RecipesCrafting, RecipesArmor, RecipesDyes, ItemStack, 
//            RecipeSorter, Item, Block, ShapedRecipes, 
//            ShapelessRecipes, CraftingRecipe, InventoryCrafting

public class CraftingManager
{

    public static final CraftingManager getInstance()
    {
        return a;
    }

    private CraftingManager()
    {
        b = new ArrayList();
        (new RecipesTools()).a(this);
        (new RecipesWeapons()).a(this);
        (new RecipeIngots()).a(this);
        (new RecipesFood()).a(this);
        (new RecipesCrafting()).a(this);
        (new RecipesArmor()).a(this);
        (new RecipesDyes()).a(this);
        registerShapedRecipe(new ItemStack(Item.PAPER, 3), new Object[] {
            "###", Character.valueOf('#'), Item.SUGAR_CANE
        });
        registerShapedRecipe(new ItemStack(Item.BOOK, 1), new Object[] {
            "#", "#", "#", Character.valueOf('#'), Item.PAPER
        });
        registerShapedRecipe(new ItemStack(Block.FENCE, 2), new Object[] {
            "###", "###", Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Block.FENCE_GATE, 1), new Object[] {
            "#W#", "#W#", Character.valueOf('#'), Item.STICK, Character.valueOf('W'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.JUKEBOX, 1), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.DIAMOND
        });
        registerShapedRecipe(new ItemStack(Block.NOTE_BLOCK, 1), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.REDSTONE
        });
        registerShapedRecipe(new ItemStack(Block.BOOKSHELF, 1), new Object[] {
            "###", "XXX", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.BOOK
        });
        registerShapedRecipe(new ItemStack(Block.SNOW_BLOCK, 1), new Object[] {
            "##", "##", Character.valueOf('#'), Item.SNOW_BALL
        });
        registerShapedRecipe(new ItemStack(Block.CLAY, 1), new Object[] {
            "##", "##", Character.valueOf('#'), Item.CLAY_BALL
        });
        registerShapedRecipe(new ItemStack(Block.BRICK, 1), new Object[] {
            "##", "##", Character.valueOf('#'), Item.CLAY_BRICK
        });
        registerShapedRecipe(new ItemStack(Block.GLOWSTONE, 1), new Object[] {
            "##", "##", Character.valueOf('#'), Item.GLOWSTONE_DUST
        });
        registerShapedRecipe(new ItemStack(Block.WOOL, 1), new Object[] {
            "##", "##", Character.valueOf('#'), Item.STRING
        });
        registerShapedRecipe(new ItemStack(Block.TNT, 1), new Object[] {
            "X#X", "#X#", "X#X", Character.valueOf('X'), Item.SULPHUR, Character.valueOf('#'), Block.SAND
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 3), new Object[] {
            "###", Character.valueOf('#'), Block.COBBLESTONE
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 0), new Object[] {
            "###", Character.valueOf('#'), Block.STONE
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 1), new Object[] {
            "###", Character.valueOf('#'), Block.SANDSTONE
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 2), new Object[] {
            "###", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 4), new Object[] {
            "###", Character.valueOf('#'), Block.BRICK
        });
        registerShapedRecipe(new ItemStack(Block.STEP, 3, 5), new Object[] {
            "###", Character.valueOf('#'), Block.SMOOTH_BRICK
        });
        registerShapedRecipe(new ItemStack(Block.LADDER, 2), new Object[] {
            "# #", "###", "# #", Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Item.WOOD_DOOR, 1), new Object[] {
            "##", "##", "##", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.TRAP_DOOR, 2), new Object[] {
            "###", "###", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Item.IRON_DOOR, 1), new Object[] {
            "##", "##", "##", Character.valueOf('#'), Item.IRON_INGOT
        });
        registerShapedRecipe(new ItemStack(Item.SIGN, 1), new Object[] {
            "###", "###", " X ", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Item.CAKE, 1), new Object[] {
            "AAA", "BEB", "CCC", Character.valueOf('A'), Item.MILK_BUCKET, Character.valueOf('B'), Item.SUGAR, Character.valueOf('C'), Item.WHEAT, Character.valueOf('E'), 
            Item.EGG
        });
        registerShapedRecipe(new ItemStack(Item.SUGAR, 1), new Object[] {
            "#", Character.valueOf('#'), Item.SUGAR_CANE
        });
        registerShapedRecipe(new ItemStack(Block.WOOD, 4), new Object[] {
            "#", Character.valueOf('#'), Block.LOG
        });
        registerShapedRecipe(new ItemStack(Item.STICK, 4), new Object[] {
            "#", "#", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.TORCH, 4), new Object[] {
            "X", "#", Character.valueOf('X'), Item.COAL, Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Block.TORCH, 4), new Object[] {
            "X", "#", Character.valueOf('X'), new ItemStack(Item.COAL, 1, 1), Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Item.BOWL, 4), new Object[] {
            "# #", " # ", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.RAILS, 16), new Object[] {
            "X X", "X#X", "X X", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Block.GOLDEN_RAIL, 6), new Object[] {
            "X X", "X#X", "XRX", Character.valueOf('X'), Item.GOLD_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Block.DETECTOR_RAIL, 6), new Object[] {
            "X X", "X#X", "XRX", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Block.STONE_PLATE
        });
        registerShapedRecipe(new ItemStack(Item.MINECART, 1), new Object[] {
            "# #", "###", Character.valueOf('#'), Item.IRON_INGOT
        });
        registerShapedRecipe(new ItemStack(Block.JACK_O_LANTERN, 1), new Object[] {
            "A", "B", Character.valueOf('A'), Block.PUMPKIN, Character.valueOf('B'), Block.TORCH
        });
        registerShapedRecipe(new ItemStack(Item.STORAGE_MINECART, 1), new Object[] {
            "A", "B", Character.valueOf('A'), Block.CHEST, Character.valueOf('B'), Item.MINECART
        });
        registerShapedRecipe(new ItemStack(Item.POWERED_MINECART, 1), new Object[] {
            "A", "B", Character.valueOf('A'), Block.FURNACE, Character.valueOf('B'), Item.MINECART
        });
        registerShapedRecipe(new ItemStack(Item.BOAT, 1), new Object[] {
            "# #", "###", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Item.BUCKET, 1), new Object[] {
            "# #", " # ", Character.valueOf('#'), Item.IRON_INGOT
        });
        registerShapedRecipe(new ItemStack(Item.FLINT_AND_STEEL, 1), new Object[] {
            "A ", " B", Character.valueOf('A'), Item.IRON_INGOT, Character.valueOf('B'), Item.FLINT
        });
        registerShapedRecipe(new ItemStack(Item.BREAD, 1), new Object[] {
            "###", Character.valueOf('#'), Item.WHEAT
        });
        registerShapedRecipe(new ItemStack(Block.WOOD_STAIRS, 4), new Object[] {
            "#  ", "## ", "###", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Item.FISHING_ROD, 1), new Object[] {
            "  #", " #X", "# X", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.STRING
        });
        registerShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, 4), new Object[] {
            "#  ", "## ", "###", Character.valueOf('#'), Block.COBBLESTONE
        });
        registerShapedRecipe(new ItemStack(Block.BRICK_STAIRS, 4), new Object[] {
            "#  ", "## ", "###", Character.valueOf('#'), Block.BRICK
        });
        registerShapedRecipe(new ItemStack(Block.STONE_STAIRS, 4), new Object[] {
            "#  ", "## ", "###", Character.valueOf('#'), Block.SMOOTH_BRICK
        });
        registerShapedRecipe(new ItemStack(Item.PAINTING, 1), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Block.WOOL
        });
        registerShapedRecipe(new ItemStack(Item.GOLDEN_APPLE, 1), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Block.GOLD_BLOCK, Character.valueOf('X'), Item.APPLE
        });
        registerShapedRecipe(new ItemStack(Block.LEVER, 1), new Object[] {
            "X", "#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.STICK
        });
        registerShapedRecipe(new ItemStack(Block.REDSTONE_TORCH_ON, 1), new Object[] {
            "X", "#", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.REDSTONE
        });
        registerShapedRecipe(new ItemStack(Item.DIODE, 1), new Object[] {
            "#X#", "III", Character.valueOf('#'), Block.REDSTONE_TORCH_ON, Character.valueOf('X'), Item.REDSTONE, Character.valueOf('I'), Block.STONE
        });
        registerShapedRecipe(new ItemStack(Item.WATCH, 1), new Object[] {
            " # ", "#X#", " # ", Character.valueOf('#'), Item.GOLD_INGOT, Character.valueOf('X'), Item.REDSTONE
        });
        registerShapedRecipe(new ItemStack(Item.COMPASS, 1), new Object[] {
            " # ", "#X#", " # ", Character.valueOf('#'), Item.IRON_INGOT, Character.valueOf('X'), Item.REDSTONE
        });
        registerShapedRecipe(new ItemStack(Item.MAP, 1), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Item.PAPER, Character.valueOf('X'), Item.COMPASS
        });
        registerShapedRecipe(new ItemStack(Block.STONE_BUTTON, 1), new Object[] {
            "#", "#", Character.valueOf('#'), Block.STONE
        });
        registerShapedRecipe(new ItemStack(Block.STONE_PLATE, 1), new Object[] {
            "##", Character.valueOf('#'), Block.STONE
        });
        registerShapedRecipe(new ItemStack(Block.WOOD_PLATE, 1), new Object[] {
            "##", Character.valueOf('#'), Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.DISPENSER, 1), new Object[] {
            "###", "#X#", "#R#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.BOW, Character.valueOf('R'), Item.REDSTONE
        });
        registerShapedRecipe(new ItemStack(Block.PISTON, 1), new Object[] {
            "TTT", "#X#", "#R#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('T'), 
            Block.WOOD
        });
        registerShapedRecipe(new ItemStack(Block.PISTON_STICKY, 1), new Object[] {
            "S", "P", Character.valueOf('S'), Item.SLIME_BALL, Character.valueOf('P'), Block.PISTON
        });
        registerShapedRecipe(new ItemStack(Item.BED, 1), new Object[] {
            "###", "XXX", Character.valueOf('#'), Block.WOOL, Character.valueOf('X'), Block.WOOD
        });
        Collections.sort(b, new RecipeSorter(this));
        System.out.println((new StringBuilder()).append(b.size()).append(" recipes").toString());
    }

    public transient void registerShapedRecipe(ItemStack itemstack, Object aobject[])
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
        if(aobject[i] instanceof String[])
        {
            String astring[] = (String[])(String[])(String[])aobject[i++];
            for(int l = 0; l < astring.length; l++)
            {
                String s1 = astring[l];
                k++;
                j = s1.length();
                s = (new StringBuilder()).append(s).append(s1).toString();
            }

        } else
        {
            while(aobject[i] instanceof String) 
            {
                String s2 = (String)aobject[i++];
                k++;
                j = s2.length();
                s = (new StringBuilder()).append(s).append(s2).toString();
            }
        }
        HashMap hashmap = new HashMap();
        for(; i < aobject.length; i += 2)
        {
            Character character = (Character)aobject[i];
            ItemStack itemstack1 = null;
            if(aobject[i + 1] instanceof Item)
                itemstack1 = new ItemStack((Item)aobject[i + 1]);
            else
            if(aobject[i + 1] instanceof Block)
                itemstack1 = new ItemStack((Block)aobject[i + 1], 1, -1);
            else
            if(aobject[i + 1] instanceof ItemStack)
                itemstack1 = (ItemStack)aobject[i + 1];
            hashmap.put(character, itemstack1);
        }

        ItemStack aitemstack[] = new ItemStack[j * k];
        for(int i1 = 0; i1 < j * k; i1++)
        {
            char c0 = s.charAt(i1);
            if(hashmap.containsKey(Character.valueOf(c0)))
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).cloneItemStack();
            else
                aitemstack[i1] = null;
        }

        b.add(new ShapedRecipes(j, k, aitemstack, itemstack));
    }

    public transient void registerShapelessRecipe(ItemStack itemstack, Object aobject[])
    {
        ArrayList arraylist = new ArrayList();
        Object aobject1[] = aobject;
        int i = aobject.length;
        for(int j = 0; j < i; j++)
        {
            Object object = aobject1[j];
            if(object instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object).cloneItemStack());
                continue;
            }
            if(object instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object));
                continue;
            }
            if(!(object instanceof Block))
                throw new RuntimeException("Invalid shapeless recipy!");
            arraylist.add(new ItemStack((Block)object));
        }

        b.add(new ShapelessRecipes(itemstack, arraylist));
    }

    public ItemStack craft(InventoryCrafting inventorycrafting)
    {
        for(int i = 0; i < b.size(); i++)
        {
            CraftingRecipe craftingrecipe = (CraftingRecipe)b.get(i);
            if(craftingrecipe.a(inventorycrafting))
                return craftingrecipe.b(inventorycrafting);
        }

        return null;
    }

    public List b()
    {
        return b;
    }

    private static final CraftingManager a = new CraftingManager();
    private List b;

}
