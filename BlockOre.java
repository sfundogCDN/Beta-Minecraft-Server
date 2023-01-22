// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, Item

public class BlockOre extends Block
{

    public BlockOre(int i, int j)
    {
        super(i, j, Material.STONE);
    }

    public int a(int i, Random random)
    {
        if(id == Block.COAL_ORE.id)
            return Item.COAL.id;
        if(id == Block.DIAMOND_ORE.id)
            return Item.DIAMOND.id;
        if(id == Block.LAPIS_ORE.id)
            return Item.INK_SACK.id;
        else
            return id;
    }

    public int a(Random random)
    {
        if(id == Block.LAPIS_ORE.id)
            return 4 + random.nextInt(5);
        else
            return 1;
    }

    protected int a_(int i)
    {
        return id != Block.LAPIS_ORE.id ? 0 : 4;
    }
}
