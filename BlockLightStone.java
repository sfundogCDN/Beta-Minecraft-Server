// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Item, Material

public class BlockLightStone extends Block
{

    public BlockLightStone(int i, int j, Material material)
    {
        super(i, j, material);
    }

    public int a(Random random)
    {
        return 2 + random.nextInt(3);
    }

    public int a(int i, Random random)
    {
        return Item.GLOWSTONE_DUST.id;
    }
}
