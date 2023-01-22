// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockStone extends Block
{

    public BlockStone(int i, int j)
    {
        super(i, j, Material.STONE);
    }

    public int a(int i, Random random)
    {
        return Block.COBBLESTONE.id;
    }
}
