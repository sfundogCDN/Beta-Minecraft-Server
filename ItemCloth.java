// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemBlock, ItemDye, ItemStack, BlockCloth

public class ItemCloth extends ItemBlock
{

    public ItemCloth(int i)
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
        return (new StringBuilder()).append(super.b()).append(".").append(ItemDye.a[BlockCloth.c(itemstack.getData())]).toString();
    }
}
