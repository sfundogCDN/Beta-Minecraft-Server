// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Statistic, ItemStack, StatisticCollector, AchievementList, 
//            Item, Block

public class Achievement extends Statistic
{

    public Achievement(int i, String s, int j, int k, Item item, Achievement achievement)
    {
        this(i, s, j, k, new ItemStack(item), achievement);
    }

    public Achievement(int i, String s, int j, int k, Block block, Achievement achievement)
    {
        this(i, s, j, k, new ItemStack(block), achievement);
    }

    public Achievement(int i, String s, int j, int k, ItemStack itemstack, Achievement achievement)
    {
        super(0x500000 + i, StatisticCollector.a((new StringBuilder()).append("achievement.").append(s).toString()));
        d = itemstack;
        l = StatisticCollector.a((new StringBuilder()).append("achievement.").append(s).append(".desc").toString());
        a = j;
        b = k;
        if(j < AchievementList.a)
            AchievementList.a = j;
        if(k < AchievementList.b)
            AchievementList.b = k;
        if(j > AchievementList.c)
            AchievementList.c = j;
        if(k > AchievementList.d)
            AchievementList.d = k;
        c = achievement;
    }

    public Achievement a()
    {
        g = true;
        return this;
    }

    public Achievement b()
    {
        m = true;
        return this;
    }

    public Achievement c()
    {
        super.d();
        AchievementList.e.add(this);
        return this;
    }

    public volatile Statistic d()
    {
        return c();
    }

    public volatile Statistic e()
    {
        return a();
    }

    public final int a;
    public final int b;
    public final Achievement c;
    private final String l;
    public final ItemStack d;
    private boolean m;
}
