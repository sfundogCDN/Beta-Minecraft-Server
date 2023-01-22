// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, ItemStack

public class ItemCoal extends Item
{

    public ItemCoal(int i)
    {
        super(i);
        a(true);
        d(0);
    }

    public String a(ItemStack itemstack)
    {
        if(itemstack.getData() == 1)
            return "item.charcoal";
        else
            return "item.coal";
    }
}
