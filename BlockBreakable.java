// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockBreakable extends Block
{

    protected BlockBreakable(int i, int j, Material material, boolean flag)
    {
        super(i, j, material);
        a = flag;
    }

    public boolean a()
    {
        return false;
    }

    private boolean a;
}
