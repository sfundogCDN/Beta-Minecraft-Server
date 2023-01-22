// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemTool, Block, EnumToolMaterial

public class ItemSpade extends ItemTool
{

    public ItemSpade(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 1, enumtoolmaterial, bt);
    }

    public boolean a(Block block)
    {
        if(block == Block.SNOW)
            return true;
        return block == Block.SNOW_BLOCK;
    }

    private static Block bt[];

    static 
    {
        bt = (new Block[] {
            Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY, Block.SOIL
        });
    }
}
