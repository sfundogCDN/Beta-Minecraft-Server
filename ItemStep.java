// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemBlock, ItemStack, BlockStep

public class ItemStep extends ItemBlock
{

    public ItemStep(int i)
    {
        super(i);
        d(0);
        a(true);
    }

    public int filterData(int i)
    {
        return i;
    }

    public String a(ItemStack itemstack)
    {
        int i = itemstack.getData();
        if(i < 0 || i >= BlockStep.a.length)
            i = 0;
        return (new StringBuilder()).append(super.b()).append(".").append(BlockStep.a[i]).toString();
    }
}
