// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Statistic, StatisticList, Counter

public class CounterStatistic extends Statistic
{

    public CounterStatistic(int i, String s, Counter counter)
    {
        super(i, s, counter);
    }

    public CounterStatistic(int i, String s)
    {
        super(i, s);
    }

    public Statistic d()
    {
        super.d();
        StatisticList.c.add(this);
        return this;
    }
}
