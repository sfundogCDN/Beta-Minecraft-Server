// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockBookshelf extends Block
{

    public BlockBookshelf(int i, int j)
    {
        super(i, j, Material.WOOD);
    }

    public int a(int i)
    {
        if(i <= 1)
            return 4;
        else
            return textureId;
    }

    public int a(Random random)
    {
        return 0;
    }
}
