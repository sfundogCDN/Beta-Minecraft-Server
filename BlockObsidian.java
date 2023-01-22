// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockStone, Block

public class BlockObsidian extends BlockStone
{

    public BlockObsidian(int i, int j)
    {
        super(i, j);
    }

    public int a(Random random)
    {
        return 1;
    }

    public int a(int i, Random random)
    {
        return Block.OBSIDIAN.id;
    }
}
