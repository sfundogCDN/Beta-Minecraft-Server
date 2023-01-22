// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockSand, Item

public class BlockGravel extends BlockSand
{

    public BlockGravel(int i, int j)
    {
        super(i, j);
    }

    public int a(int i, Random random)
    {
        if(random.nextInt(10) == 0)
            return Item.FLINT.id;
        else
            return id;
    }
}
