// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, Item

public class BlockClay extends Block
{

    public BlockClay(int i, int j)
    {
        super(i, j, Material.CLAY);
    }

    public int a(int i, Random random)
    {
        return Item.CLAY_BALL.id;
    }

    public int a(Random random)
    {
        return 4;
    }
}
