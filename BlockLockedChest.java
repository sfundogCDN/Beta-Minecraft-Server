// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World

public class BlockLockedChest extends Block
{

    protected BlockLockedChest(int i)
    {
        super(i, Material.WOOD);
        textureId = 26;
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId - 1;
        if(i == 0)
            return textureId - 1;
        if(i == 3)
            return textureId + 1;
        else
            return textureId;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        world.setTypeId(i, j, k, 0);
    }
}
