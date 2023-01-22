// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.IOException;
import java.util.Properties;

public class StatisticStorage
{

    private StatisticStorage()
    {
        b = new Properties();
        try
        {
            b.load(net/minecraft/server/StatisticStorage.getResourceAsStream("/lang/en_US.lang"));
            b.load(net/minecraft/server/StatisticStorage.getResourceAsStream("/lang/stats_US.lang"));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static StatisticStorage a()
    {
        return a;
    }

    public String a(String s)
    {
        return b.getProperty(s, s);
    }

    public transient String a(String s, Object aobj[])
    {
        String s1 = b.getProperty(s, s);
        return String.format(s1, aobj);
    }

    private static StatisticStorage a = new StatisticStorage();
    private Properties b;

}
