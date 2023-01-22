// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AchievementMap
{

    private AchievementMap()
    {
        b = new HashMap();
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(net/minecraft/server/AchievementMap.getResourceAsStream("/achievement/map.txt")));
            String s;
            while((s = bufferedreader.readLine()) != null) 
            {
                String as[] = s.split(",");
                int i = Integer.parseInt(as[0]);
                b.put(Integer.valueOf(i), as[1]);
            }
            bufferedreader.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static String a(int i)
    {
        return (String)a.b.get(Integer.valueOf(i));
    }

    public static AchievementMap a = new AchievementMap();
    private Map b;

}
