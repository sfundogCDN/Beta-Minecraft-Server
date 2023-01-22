// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemBlock, Block

public class ItemColoredBlock extends ItemBlock
{

    public ItemColoredBlock(int i, boolean flag)
    {
        super(i);
        a = Block.byId[a()];
        if(flag)
        {
            d(0);
            a(true);
        }
    }

    public int filterData(int i)
    {
        return i;
    }

    private final Block a;
}
