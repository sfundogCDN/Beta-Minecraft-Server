// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

public class MathHelper
{

    public MathHelper()
    {
    }

    public static final float sin(float f)
    {
        return a[(int)(f * 10430.38F) & 0xffff];
    }

    public static final float cos(float f)
    {
        return a[(int)(f * 10430.38F + 16384F) & 0xffff];
    }

    public static final float c(float f)
    {
        return (float)Math.sqrt(f);
    }

    public static final float a(double d1)
    {
        return (float)Math.sqrt(d1);
    }

    public static int d(float f)
    {
        int i = (int)f;
        return f >= (float)i ? i : i - 1;
    }

    public static int floor(double d1)
    {
        int i = (int)d1;
        return d1 >= (double)i ? i : i - 1;
    }

    public static long c(double d1)
    {
        long l = (long)d1;
        return d1 >= (double)l ? l : l - 1L;
    }

    public static float abs(float f)
    {
        return f < 0.0F ? -f : f;
    }

    public static int a(int i)
    {
        return i < 0 ? -i : i;
    }

    public static double a(double d1, double d2)
    {
        if(d1 < 0.0D)
            d1 = -d1;
        if(d2 < 0.0D)
            d2 = -d2;
        return d1 <= d2 ? d2 : d1;
    }

    public static int a(Random random, int i, int j)
    {
        if(i >= j)
            return i;
        else
            return random.nextInt((j - i) + 1) + i;
    }

    private static float a[];

    static 
    {
        a = new float[0x10000];
        for(int i = 0; i < 0x10000; i++)
            a[i] = (float)Math.sin(((double)i * 3.1415926535897931D * 2D) / 65536D);

    }
}
