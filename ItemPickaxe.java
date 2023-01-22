// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemTool, Block, EnumToolMaterial, Material

public class ItemPickaxe extends ItemTool
{

    protected ItemPickaxe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 2, enumtoolmaterial, bt);
    }

    public boolean a(Block block)
    {
        if(block == Block.OBSIDIAN)
            return a.d() == 3;
        if(block == Block.DIAMOND_BLOCK || block == Block.DIAMOND_ORE)
            return a.d() >= 2;
        if(block == Block.GOLD_BLOCK || block == Block.GOLD_ORE)
            return a.d() >= 2;
        if(block == Block.IRON_BLOCK || block == Block.IRON_ORE)
            return a.d() >= 1;
        if(block == Block.LAPIS_BLOCK || block == Block.LAPIS_ORE)
            return a.d() >= 1;
        if(block == Block.REDSTONE_ORE || block == Block.GLOWING_REDSTONE_ORE)
            return a.d() >= 2;
        if(block.material == Material.STONE)
            return true;
        return block.material == Material.ORE;
    }

    private static Block bt[];

    static 
    {
        bt = (new Block[] {
            Block.COBBLESTONE, Block.DOUBLE_STEP, Block.STEP, Block.STONE, Block.SANDSTONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, 
            Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.NETHERRACK, Block.LAPIS_ORE, Block.LAPIS_BLOCK
        });
    }
}
