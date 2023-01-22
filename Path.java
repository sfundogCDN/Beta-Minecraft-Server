// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            PathPoint

public class Path
{

    public Path()
    {
        a = new PathPoint[1024];
        b = 0;
    }

    public PathPoint a(PathPoint pathpoint)
    {
        if(pathpoint.d >= 0)
            throw new IllegalStateException("OW KNOWS!");
        if(b == a.length)
        {
            PathPoint apathpoint[] = new PathPoint[b << 1];
            System.arraycopy(a, 0, apathpoint, 0, b);
            a = apathpoint;
        }
        a[b] = pathpoint;
        pathpoint.d = b;
        a(b++);
        return pathpoint;
    }

    public void a()
    {
        b = 0;
    }

    public PathPoint b()
    {
        PathPoint pathpoint = a[0];
        a[0] = a[--b];
        a[b] = null;
        if(b > 0)
            b(0);
        pathpoint.d = -1;
        return pathpoint;
    }

    public void a(PathPoint pathpoint, float f)
    {
        float f1 = pathpoint.g;
        pathpoint.g = f;
        if(f < f1)
            a(pathpoint.d);
        else
            b(pathpoint.d);
    }

    private void a(int i)
    {
        PathPoint pathpoint = a[i];
        float f = pathpoint.g;
        do
        {
            if(i <= 0)
                break;
            int j = i - 1 >> 1;
            PathPoint pathpoint1 = a[j];
            if(f >= pathpoint1.g)
                break;
            a[i] = pathpoint1;
            pathpoint1.d = i;
            i = j;
        } while(true);
        a[i] = pathpoint;
        pathpoint.d = i;
    }

    private void b(int i)
    {
        PathPoint pathpoint = a[i];
        float f = pathpoint.g;
        do
        {
            int j = 1 + (i << 1);
            int k = j + 1;
            if(j >= b)
                break;
            PathPoint pathpoint1 = a[j];
            float f1 = pathpoint1.g;
            PathPoint pathpoint2;
            float f2;
            if(k >= b)
            {
                pathpoint2 = null;
                f2 = (1.0F / 0.0F);
            } else
            {
                pathpoint2 = a[k];
                f2 = pathpoint2.g;
            }
            if(f1 < f2)
            {
                if(f1 >= f)
                    break;
                a[i] = pathpoint1;
                pathpoint1.d = i;
                i = j;
                continue;
            }
            if(f2 >= f)
                break;
            a[i] = pathpoint2;
            pathpoint2.d = i;
            i = k;
        } while(true);
        a[i] = pathpoint;
        pathpoint.d = i;
    }

    public boolean c()
    {
        return b == 0;
    }

    private PathPoint a[];
    private int b;
}
