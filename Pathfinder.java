// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Path, EntityList, PathPoint, Entity, 
//            AxisAlignedBB, MathHelper, IBlockAccess, Block, 
//            BlockDoor, Material, PathEntity

public class Pathfinder
{

    public Pathfinder(IBlockAccess iblockaccess)
    {
        b = new Path();
        c = new EntityList();
        d = new PathPoint[32];
        a = iblockaccess;
    }

    public PathEntity a(Entity entity, Entity entity1, float f)
    {
        return a(entity, entity1.locX, entity1.boundingBox.b, entity1.locZ, f);
    }

    public PathEntity a(Entity entity, int i, int j, int k, float f)
    {
        return a(entity, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, f);
    }

    private PathEntity a(Entity entity, double d1, double d2, double d3, 
            float f)
    {
        b.a();
        c.a();
        PathPoint pathpoint = a(MathHelper.floor(entity.boundingBox.a), MathHelper.floor(entity.boundingBox.b), MathHelper.floor(entity.boundingBox.c));
        PathPoint pathpoint1 = a(MathHelper.floor(d1 - (double)(entity.length / 2.0F)), MathHelper.floor(d2), MathHelper.floor(d3 - (double)(entity.length / 2.0F)));
        PathPoint pathpoint2 = new PathPoint(MathHelper.d(entity.length + 1.0F), MathHelper.d(entity.width + 1.0F), MathHelper.d(entity.length + 1.0F));
        PathEntity pathentity = a(entity, pathpoint, pathpoint1, pathpoint2, f);
        return pathentity;
    }

    private PathEntity a(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f)
    {
        pathpoint.e = 0.0F;
        pathpoint.f = pathpoint.a(pathpoint1);
        pathpoint.g = pathpoint.f;
        b.a();
        b.a(pathpoint);
        PathPoint pathpoint3 = pathpoint;
        while(!b.c()) 
        {
            PathPoint pathpoint4 = b.b();
            if(pathpoint4.equals(pathpoint1))
                return a(pathpoint, pathpoint1);
            if(pathpoint4.a(pathpoint1) < pathpoint3.a(pathpoint1))
                pathpoint3 = pathpoint4;
            pathpoint4.i = true;
            int i = b(entity, pathpoint4, pathpoint2, pathpoint1, f);
            int j = 0;
            while(j < i) 
            {
                PathPoint pathpoint5 = d[j];
                float f1 = pathpoint4.e + pathpoint4.a(pathpoint5);
                if(!pathpoint5.a() || f1 < pathpoint5.e)
                {
                    pathpoint5.h = pathpoint4;
                    pathpoint5.e = f1;
                    pathpoint5.f = pathpoint5.a(pathpoint1);
                    if(pathpoint5.a())
                    {
                        b.a(pathpoint5, pathpoint5.e + pathpoint5.f);
                    } else
                    {
                        pathpoint5.g = pathpoint5.e + pathpoint5.f;
                        b.a(pathpoint5);
                    }
                }
                j++;
            }
        }
        if(pathpoint3 == pathpoint)
            return null;
        else
            return a(pathpoint, pathpoint3);
    }

    private int b(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f)
    {
        int i = 0;
        int j = 0;
        if(a(entity, pathpoint.a, pathpoint.b + 1, pathpoint.c, pathpoint1) == 1)
            j = 1;
        PathPoint pathpoint3 = a(entity, pathpoint.a, pathpoint.b, pathpoint.c + 1, pathpoint1, j);
        PathPoint pathpoint4 = a(entity, pathpoint.a - 1, pathpoint.b, pathpoint.c, pathpoint1, j);
        PathPoint pathpoint5 = a(entity, pathpoint.a + 1, pathpoint.b, pathpoint.c, pathpoint1, j);
        PathPoint pathpoint6 = a(entity, pathpoint.a, pathpoint.b, pathpoint.c - 1, pathpoint1, j);
        if(pathpoint3 != null && !pathpoint3.i && pathpoint3.a(pathpoint2) < f)
            d[i++] = pathpoint3;
        if(pathpoint4 != null && !pathpoint4.i && pathpoint4.a(pathpoint2) < f)
            d[i++] = pathpoint4;
        if(pathpoint5 != null && !pathpoint5.i && pathpoint5.a(pathpoint2) < f)
            d[i++] = pathpoint5;
        if(pathpoint6 != null && !pathpoint6.i && pathpoint6.a(pathpoint2) < f)
            d[i++] = pathpoint6;
        return i;
    }

    private PathPoint a(Entity entity, int i, int j, int k, PathPoint pathpoint, int l)
    {
        PathPoint pathpoint1 = null;
        if(a(entity, i, j, k, pathpoint) == 1)
            pathpoint1 = a(i, j, k);
        if(pathpoint1 == null && l > 0 && a(entity, i, j + l, k, pathpoint) == 1)
        {
            pathpoint1 = a(i, j + l, k);
            j += l;
        }
        if(pathpoint1 != null)
        {
            int i1 = 0;
            int j1 = 0;
            do
            {
                if(j <= 0 || (j1 = a(entity, i, j - 1, k, pathpoint)) != 1)
                    break;
                if(++i1 >= 4)
                    return null;
                if(--j > 0)
                    pathpoint1 = a(i, j, k);
            } while(true);
            if(j1 == -2)
                return null;
        }
        return pathpoint1;
    }

    private final PathPoint a(int i, int j, int k)
    {
        int l = PathPoint.a(i, j, k);
        PathPoint pathpoint = (PathPoint)c.a(l);
        if(pathpoint == null)
        {
            pathpoint = new PathPoint(i, j, k);
            c.a(l, pathpoint);
        }
        return pathpoint;
    }

    private int a(Entity entity, int i, int j, int k, PathPoint pathpoint)
    {
        for(int l = i; l < i + pathpoint.a; l++)
        {
            for(int i1 = j; i1 < j + pathpoint.b; i1++)
            {
                for(int j1 = k; j1 < k + pathpoint.c; j1++)
                {
                    int k1 = a.getTypeId(l, i1, j1);
                    if(k1 <= 0)
                        continue;
                    if(k1 == Block.IRON_DOOR_BLOCK.id || k1 == Block.WOODEN_DOOR.id)
                    {
                        int l1 = a.getData(l, i1, j1);
                        if(!BlockDoor.e(l1))
                            return 0;
                        continue;
                    }
                    Material material = Block.byId[k1].material;
                    if(material.isSolid())
                        return 0;
                    if(material == Material.WATER)
                        return -1;
                    if(material == Material.LAVA)
                        return -2;
                }

            }

        }

        return 1;
    }

    private PathEntity a(PathPoint pathpoint, PathPoint pathpoint1)
    {
        int i = 1;
        for(PathPoint pathpoint2 = pathpoint1; pathpoint2.h != null; pathpoint2 = pathpoint2.h)
            i++;

        PathPoint apathpoint[] = new PathPoint[i];
        PathPoint pathpoint3 = pathpoint1;
        for(apathpoint[--i] = pathpoint3; pathpoint3.h != null; apathpoint[--i] = pathpoint3)
            pathpoint3 = pathpoint3.h;

        return new PathEntity(apathpoint);
    }

    private IBlockAccess a;
    private Path b;
    private EntityList c;
    private PathPoint d[];
}
