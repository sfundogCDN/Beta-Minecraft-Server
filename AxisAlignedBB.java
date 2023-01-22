// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Vec3D, MovingObjectPosition

public class AxisAlignedBB
{

    public static AxisAlignedBB a(double d1, double d2, double d3, double d4, 
            double d5, double d6)
    {
        return new AxisAlignedBB(d1, d2, d3, d4, d5, d6);
    }

    public static void a()
    {
        h = 0;
    }

    public static AxisAlignedBB b(double d1, double d2, double d3, double d4, 
            double d5, double d6)
    {
        if(h >= g.size())
            g.add(a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D));
        return ((AxisAlignedBB)g.get(h++)).c(d1, d2, d3, d4, d5, d6);
    }

    private AxisAlignedBB(double d1, double d2, double d3, double d4, double d5, double d6)
    {
        a = d1;
        b = d2;
        c = d3;
        d = d4;
        e = d5;
        f = d6;
    }

    public AxisAlignedBB c(double d1, double d2, double d3, double d4, double d5, double d6)
    {
        a = d1;
        b = d2;
        c = d3;
        d = d4;
        e = d5;
        f = d6;
        return this;
    }

    public AxisAlignedBB a(double d1, double d2, double d3)
    {
        double d4 = a;
        double d5 = b;
        double d6 = c;
        double d7 = d;
        double d8 = e;
        double d9 = f;
        if(d1 < 0.0D)
            d4 += d1;
        if(d1 > 0.0D)
            d7 += d1;
        if(d2 < 0.0D)
            d5 += d2;
        if(d2 > 0.0D)
            d8 += d2;
        if(d3 < 0.0D)
            d6 += d3;
        if(d3 > 0.0D)
            d9 += d3;
        return b(d4, d5, d6, d7, d8, d9);
    }

    public AxisAlignedBB b(double d1, double d2, double d3)
    {
        double d4 = a - d1;
        double d5 = b - d2;
        double d6 = c - d3;
        double d7 = d + d1;
        double d8 = e + d2;
        double d9 = f + d3;
        return b(d4, d5, d6, d7, d8, d9);
    }

    public AxisAlignedBB c(double d1, double d2, double d3)
    {
        return b(a + d1, b + d2, c + d3, d + d1, e + d2, f + d3);
    }

    public double a(AxisAlignedBB axisalignedbb, double d1)
    {
        if(axisalignedbb.e <= b || axisalignedbb.b >= e)
            return d1;
        if(axisalignedbb.f <= c || axisalignedbb.c >= f)
            return d1;
        if(d1 > 0.0D && axisalignedbb.d <= a)
        {
            double d2 = a - axisalignedbb.d;
            if(d2 < d1)
                d1 = d2;
        }
        if(d1 < 0.0D && axisalignedbb.a >= d)
        {
            double d3 = d - axisalignedbb.a;
            if(d3 > d1)
                d1 = d3;
        }
        return d1;
    }

    public double b(AxisAlignedBB axisalignedbb, double d1)
    {
        if(axisalignedbb.d <= a || axisalignedbb.a >= d)
            return d1;
        if(axisalignedbb.f <= c || axisalignedbb.c >= f)
            return d1;
        if(d1 > 0.0D && axisalignedbb.e <= b)
        {
            double d2 = b - axisalignedbb.e;
            if(d2 < d1)
                d1 = d2;
        }
        if(d1 < 0.0D && axisalignedbb.b >= e)
        {
            double d3 = e - axisalignedbb.b;
            if(d3 > d1)
                d1 = d3;
        }
        return d1;
    }

    public double c(AxisAlignedBB axisalignedbb, double d1)
    {
        if(axisalignedbb.d <= a || axisalignedbb.a >= d)
            return d1;
        if(axisalignedbb.e <= b || axisalignedbb.b >= e)
            return d1;
        if(d1 > 0.0D && axisalignedbb.f <= c)
        {
            double d2 = c - axisalignedbb.f;
            if(d2 < d1)
                d1 = d2;
        }
        if(d1 < 0.0D && axisalignedbb.c >= f)
        {
            double d3 = f - axisalignedbb.c;
            if(d3 > d1)
                d1 = d3;
        }
        return d1;
    }

    public boolean a(AxisAlignedBB axisalignedbb)
    {
        if(axisalignedbb.d <= a || axisalignedbb.a >= d)
            return false;
        if(axisalignedbb.e <= b || axisalignedbb.b >= e)
            return false;
        return axisalignedbb.f > c && axisalignedbb.c < f;
    }

    public AxisAlignedBB d(double d1, double d2, double d3)
    {
        a += d1;
        b += d2;
        c += d3;
        d += d1;
        e += d2;
        f += d3;
        return this;
    }

    public boolean a(Vec3D vec3d)
    {
        if(vec3d.a <= a || vec3d.a >= d)
            return false;
        if(vec3d.b <= b || vec3d.b >= e)
            return false;
        return vec3d.c > c && vec3d.c < f;
    }

    public AxisAlignedBB shrink(double d1, double d2, double d3)
    {
        double d4 = a + d1;
        double d5 = b + d2;
        double d6 = c + d3;
        double d7 = d - d1;
        double d8 = e - d2;
        double d9 = f - d3;
        return b(d4, d5, d6, d7, d8, d9);
    }

    public AxisAlignedBB clone()
    {
        return b(a, b, c, d, e, f);
    }

    public MovingObjectPosition a(Vec3D vec3d, Vec3D vec3d1)
    {
        Vec3D vec3d2 = vec3d.a(vec3d1, a);
        Vec3D vec3d3 = vec3d.a(vec3d1, d);
        Vec3D vec3d4 = vec3d.b(vec3d1, b);
        Vec3D vec3d5 = vec3d.b(vec3d1, e);
        Vec3D vec3d6 = vec3d.c(vec3d1, c);
        Vec3D vec3d7 = vec3d.c(vec3d1, f);
        if(!b(vec3d2))
            vec3d2 = null;
        if(!b(vec3d3))
            vec3d3 = null;
        if(!c(vec3d4))
            vec3d4 = null;
        if(!c(vec3d5))
            vec3d5 = null;
        if(!d(vec3d6))
            vec3d6 = null;
        if(!d(vec3d7))
            vec3d7 = null;
        Vec3D vec3d8 = null;
        if(vec3d2 != null && (vec3d8 == null || vec3d.c(vec3d2) < vec3d.c(vec3d8)))
            vec3d8 = vec3d2;
        if(vec3d3 != null && (vec3d8 == null || vec3d.c(vec3d3) < vec3d.c(vec3d8)))
            vec3d8 = vec3d3;
        if(vec3d4 != null && (vec3d8 == null || vec3d.c(vec3d4) < vec3d.c(vec3d8)))
            vec3d8 = vec3d4;
        if(vec3d5 != null && (vec3d8 == null || vec3d.c(vec3d5) < vec3d.c(vec3d8)))
            vec3d8 = vec3d5;
        if(vec3d6 != null && (vec3d8 == null || vec3d.c(vec3d6) < vec3d.c(vec3d8)))
            vec3d8 = vec3d6;
        if(vec3d7 != null && (vec3d8 == null || vec3d.c(vec3d7) < vec3d.c(vec3d8)))
            vec3d8 = vec3d7;
        if(vec3d8 == null)
            return null;
        byte byte0 = -1;
        if(vec3d8 == vec3d2)
            byte0 = 4;
        if(vec3d8 == vec3d3)
            byte0 = 5;
        if(vec3d8 == vec3d4)
            byte0 = 0;
        if(vec3d8 == vec3d5)
            byte0 = 1;
        if(vec3d8 == vec3d6)
            byte0 = 2;
        if(vec3d8 == vec3d7)
            byte0 = 3;
        return new MovingObjectPosition(0, 0, 0, byte0, vec3d8);
    }

    private boolean b(Vec3D vec3d)
    {
        if(vec3d == null)
            return false;
        else
            return vec3d.b >= b && vec3d.b <= e && vec3d.c >= c && vec3d.c <= f;
    }

    private boolean c(Vec3D vec3d)
    {
        if(vec3d == null)
            return false;
        else
            return vec3d.a >= a && vec3d.a <= d && vec3d.c >= c && vec3d.c <= f;
    }

    private boolean d(Vec3D vec3d)
    {
        if(vec3d == null)
            return false;
        else
            return vec3d.a >= a && vec3d.a <= d && vec3d.b >= b && vec3d.b <= e;
    }

    public void b(AxisAlignedBB axisalignedbb)
    {
        a = axisalignedbb.a;
        b = axisalignedbb.b;
        c = axisalignedbb.c;
        d = axisalignedbb.d;
        e = axisalignedbb.e;
        f = axisalignedbb.f;
    }

    public String toString()
    {
        return (new StringBuilder()).append("box[").append(a).append(", ").append(b).append(", ").append(c).append(" -> ").append(d).append(", ").append(e).append(", ").append(f).append("]").toString();
    }

    private static List g = new ArrayList();
    private static int h = 0;
    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public double f;

}
