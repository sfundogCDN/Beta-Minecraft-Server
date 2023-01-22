// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Statistic

public class CraftingStatistic extends Statistic
{

    public CraftingStatistic(int i, String s, int j)
    {
        super(i, s);
        a = j;
    }

    private final int a;
}
