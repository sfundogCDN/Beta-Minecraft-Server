// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemTool, Block, EnumToolMaterial

public class ItemAxe extends ItemTool
{

    protected ItemAxe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 3, enumtoolmaterial, bt);
    }

    private static Block bt[];

    static 
    {
        bt = (new Block[] {
            Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST
        });
    }
}
