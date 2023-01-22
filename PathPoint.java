// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            MathHelper

public class PathPoint
{

    public PathPoint(int k, int l, int i1)
    {
        d = -1;
        i = false;
        a = k;
        b = l;
        c = i1;
        j = a(k, l, i1);
    }

    public static int a(int k, int l, int i1)
    {
        return l & 0xff | (k & 0x7fff) << 8 | (i1 & 0x7fff) << 24 | (k >= 0 ? 0 : 0x80000000) | (i1 >= 0 ? 0 : 0x8000);
    }

    public float a(PathPoint pathpoint)
    {
        float f1 = pathpoint.a - a;
        float f2 = pathpoint.b - b;
        float f3 = pathpoint.c - c;
        return MathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof PathPoint)
        {
            PathPoint pathpoint = (PathPoint)obj;
            return j == pathpoint.j && a == pathpoint.a && b == pathpoint.b && c == pathpoint.c;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return j;
    }

    public boolean a()
    {
        return d >= 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append(a).append(", ").append(b).append(", ").append(c).toString();
    }

    public final int a;
    public final int b;
    public final int c;
    private final int j;
    int d;
    float e;
    float f;
    float g;
    PathPoint h;
    public boolean i;
}
