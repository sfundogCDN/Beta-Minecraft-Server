// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockFlower, Block

public class BlockDeadBush extends BlockFlower
{

    protected BlockDeadBush(int i, int j)
    {
        super(i, j);
        float f = 0.4F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    protected boolean c(int i)
    {
        return i == Block.SAND.id;
    }

    public int a(int i, int j)
    {
        return textureId;
    }

    public int a(int i, Random random)
    {
        return -1;
    }
}
