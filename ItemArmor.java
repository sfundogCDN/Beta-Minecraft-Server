// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item

public class ItemArmor extends Item
{

    public ItemArmor(int i, int j, int k, int l)
    {
        super(i);
        a = j;
        bt = l;
        bv = k;
        bu = bw[l];
        d(bx[l] * 3 << j);
        maxStackSize = 1;
    }

    private static final int bw[] = {
        3, 8, 6, 3
    };
    private static final int bx[] = {
        11, 16, 15, 13
    };
    public final int a;
    public final int bt;
    public final int bu;
    public final int bv;

}
