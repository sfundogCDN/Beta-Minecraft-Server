// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, BlockFlower, Material

public class BlockHugeMushroom extends Block
{

    public BlockHugeMushroom(int i, Material material, int j, int k)
    {
        super(i, j, material);
        a = k;
    }

    public int a(int i, int j)
    {
        if(j == 10 && i > 1)
            return textureId - 1;
        if(j >= 1 && j <= 9 && i == 1)
            return textureId - 16 - a;
        if(j >= 1 && j <= 3 && i == 2)
            return textureId - 16 - a;
        if(j >= 7 && j <= 9 && i == 3)
            return textureId - 16 - a;
        if((j == 1 || j == 4 || j == 7) && i == 4)
            return textureId - 16 - a;
        if((j == 3 || j == 6 || j == 9) && i == 5)
            return textureId - 16 - a;
        else
            return textureId;
    }

    public int a(Random random)
    {
        int i = random.nextInt(10) - 7;
        if(i < 0)
            i = 0;
        return i;
    }

    public int a(int i, Random random)
    {
        return Block.BROWN_MUSHROOM.id + a;
    }

    private int a;
}
