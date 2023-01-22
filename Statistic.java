// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StatisticList, AchievementMap, UnknownCounter, TimeCounter, 
//            DistancesCounter, Counter

public class Statistic
{

    public Statistic(int l, String s, Counter counter)
    {
        g = false;
        e = l;
        f = s;
        a = counter;
    }

    public Statistic(int l, String s)
    {
        this(l, s, i);
    }

    public Statistic e()
    {
        g = true;
        return this;
    }

    public Statistic d()
    {
        if(StatisticList.a.containsKey(Integer.valueOf(e)))
        {
            throw new RuntimeException((new StringBuilder()).append("Duplicate stat id: \"").append(((Statistic)StatisticList.a.get(Integer.valueOf(e))).f).append("\" and \"").append(f).append("\" at id ").append(e).toString());
        } else
        {
            StatisticList.b.add(this);
            StatisticList.a.put(Integer.valueOf(e), this);
            h = AchievementMap.a(e);
            return this;
        }
    }

    public String toString()
    {
        return f;
    }

    public final int e;
    public final String f;
    public boolean g;
    public String h;
    private final Counter a;
    private static NumberFormat b;
    public static Counter i = new UnknownCounter();
    private static DecimalFormat c = new DecimalFormat("########0.00");
    public static Counter j = new TimeCounter();
    public static Counter k = new DistancesCounter();

    static 
    {
        b = NumberFormat.getIntegerInstance(Locale.US);
    }
}
