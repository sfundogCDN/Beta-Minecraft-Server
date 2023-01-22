// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockSmoothBrick extends Block
{

    public BlockSmoothBrick(int i)
    {
        super(i, 54, Material.STONE);
    }

    public int a(int i, int j)
    {
        switch(j)
        {
        default:
            return 54;

        case 1: // '\001'
            return 100;

        case 2: // '\002'
            return 101;
        }
    }

    protected int a_(int i)
    {
        return i;
    }
}
