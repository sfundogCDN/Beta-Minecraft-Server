// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockSandStone extends Block
{

    public BlockSandStone(int i)
    {
        super(i, 192, Material.STONE);
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId - 16;
        if(i == 0)
            return textureId + 16;
        else
            return textureId;
    }
}
