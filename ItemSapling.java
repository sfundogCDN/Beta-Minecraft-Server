// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemBlock

public class ItemSapling extends ItemBlock
{

    public ItemSapling(int i)
    {
        super(i);
        d(0);
        a(true);
    }

    public int filterData(int i)
    {
        return i;
    }
}
