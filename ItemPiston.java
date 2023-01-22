// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemBlock

public class ItemPiston extends ItemBlock
{

    public ItemPiston(int i)
    {
        super(i);
    }

    public int filterData(int i)
    {
        return 7;
    }
}
