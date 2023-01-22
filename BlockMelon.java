// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, Item

public class BlockMelon extends Block
{

    protected BlockMelon(int i)
    {
        super(i, Material.PUMPKIN);
        textureId = 136;
    }

    public int a(int i, int j)
    {
        return i != 1 && i != 0 ? 136 : 137;
    }

    public int a(int i)
    {
        return i != 1 && i != 0 ? 136 : 137;
    }

    public int a(int i, Random random)
    {
        return Item.MELON.id;
    }

    public int a(Random random)
    {
        return 3 + random.nextInt(5);
    }
}
