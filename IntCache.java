// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class IntCache
{

    public IntCache()
    {
    }

    public static int[] a(int i)
    {
        if(i <= 256)
            if(b.size() == 0)
            {
                int ai[] = new int[256];
                c.add(ai);
                return ai;
            } else
            {
                int ai1[] = (int[])b.remove(b.size() - 1);
                c.add(ai1);
                return ai1;
            }
        if(i > a)
        {
            System.out.println((new StringBuilder()).append("New max size: ").append(i).toString());
            a = i;
            d.clear();
            e.clear();
            int ai2[] = new int[a];
            e.add(ai2);
            return ai2;
        }
        if(d.size() == 0)
        {
            int ai3[] = new int[a];
            e.add(ai3);
            return ai3;
        } else
        {
            int ai4[] = (int[])d.remove(d.size() - 1);
            e.add(ai4);
            return ai4;
        }
    }

    public static void a()
    {
        if(d.size() > 0)
            d.remove(d.size() - 1);
        if(b.size() > 0)
            b.remove(b.size() - 1);
        d.addAll(e);
        b.addAll(c);
        e.clear();
        c.clear();
    }

    private static int a = 256;
    private static List b = new ArrayList();
    private static List c = new ArrayList();
    private static List d = new ArrayList();
    private static List e = new ArrayList();

}
