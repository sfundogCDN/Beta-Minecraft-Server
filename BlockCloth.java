// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material

public class BlockCloth extends Block
{

    public BlockCloth()
    {
        super(35, 64, Material.CLOTH);
    }

    public int a(int i, int j)
    {
        if(j == 0)
        {
            return textureId;
        } else
        {
            j = ~(j & 0xf);
            return 113 + ((j & 8) >> 3) + (j & 7) * 16;
        }
    }

    protected int a_(int i)
    {
        return i;
    }

    public static int c(int i)
    {
        return ~i & 0xf;
    }

    public static int d(int i)
    {
        return ~i & 0xf;
    }
}
