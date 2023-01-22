// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockOreBlock extends Block
{

    public BlockOreBlock(int i, int j)
    {
        super(i, Material.ORE);
        textureId = j;
    }

    public int a(int i)
    {
        return textureId;
    }
}
