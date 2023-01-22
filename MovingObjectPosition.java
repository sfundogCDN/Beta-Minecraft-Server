// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EnumMovingObjectType, Vec3D, Entity

public class MovingObjectPosition
{

    public MovingObjectPosition(int i, int j, int k, int l, Vec3D vec3d)
    {
        type = EnumMovingObjectType.TILE;
        b = i;
        c = j;
        d = k;
        face = l;
        f = Vec3D.create(vec3d.a, vec3d.b, vec3d.c);
    }

    public MovingObjectPosition(Entity entity1)
    {
        type = EnumMovingObjectType.ENTITY;
        entity = entity1;
        f = Vec3D.create(entity1.locX, entity1.locY, entity1.locZ);
    }

    public EnumMovingObjectType type;
    public int b;
    public int c;
    public int d;
    public int face;
    public Vec3D f;
    public Entity entity;
}
