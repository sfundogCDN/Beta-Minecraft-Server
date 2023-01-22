// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            PathPoint, Entity, Vec3D

public class PathEntity
{

    public PathEntity(PathPoint apathpoint[])
    {
        b = apathpoint;
        a = apathpoint.length;
    }

    public void a()
    {
        c++;
    }

    public boolean b()
    {
        return c >= b.length;
    }

    public PathPoint c()
    {
        if(a > 0)
            return b[a - 1];
        else
            return null;
    }

    public Vec3D a(Entity entity)
    {
        double d = (double)b[c].a + (double)(int)(entity.length + 1.0F) * 0.5D;
        double d1 = b[c].b;
        double d2 = (double)b[c].c + (double)(int)(entity.length + 1.0F) * 0.5D;
        return Vec3D.create(d, d1, d2);
    }

    private final PathPoint b[];
    public final int a;
    private int c;
}
