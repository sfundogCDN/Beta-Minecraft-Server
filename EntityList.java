// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package net.minecraft.server:
//            EntityListEntry

public class EntityList
{

    public EntityList()
    {
        f = new HashSet();
        c = 12;
        a = new EntityListEntry[16];
    }

    private static int g(int i)
    {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j)
    {
        return i & j - 1;
    }

    public Object a(int i)
    {
        int j = g(i);
        for(EntityListEntry entitylistentry = a[a(j, a.length)]; entitylistentry != null; entitylistentry = entitylistentry.c)
            if(entitylistentry.a == i)
                return entitylistentry.b;

        return null;
    }

    public boolean b(int i)
    {
        return c(i) != null;
    }

    final EntityListEntry c(int i)
    {
        int j = g(i);
        for(EntityListEntry entitylistentry = a[a(j, a.length)]; entitylistentry != null; entitylistentry = entitylistentry.c)
            if(entitylistentry.a == i)
                return entitylistentry;

        return null;
    }

    public void a(int i, Object obj)
    {
        f.add(Integer.valueOf(i));
        int j = g(i);
        int k = a(j, a.length);
        for(EntityListEntry entitylistentry = a[k]; entitylistentry != null; entitylistentry = entitylistentry.c)
            if(entitylistentry.a == i)
                entitylistentry.b = obj;

        e++;
        a(j, i, obj, k);
    }

    private void h(int i)
    {
        EntityListEntry aentitylistentry[] = a;
        int j = aentitylistentry.length;
        if(j == 0x40000000)
        {
            c = 0x7fffffff;
            return;
        } else
        {
            EntityListEntry aentitylistentry1[] = new EntityListEntry[i];
            a(aentitylistentry1);
            a = aentitylistentry1;
            c = (int)((float)i * d);
            return;
        }
    }

    private void a(EntityListEntry aentitylistentry[])
    {
        EntityListEntry aentitylistentry1[] = a;
        int i = aentitylistentry.length;
        for(int j = 0; j < aentitylistentry1.length; j++)
        {
            EntityListEntry entitylistentry = aentitylistentry1[j];
            if(entitylistentry == null)
                continue;
            aentitylistentry1[j] = null;
            do
            {
                EntityListEntry entitylistentry1 = entitylistentry.c;
                int k = a(entitylistentry.d, i);
                entitylistentry.c = aentitylistentry[k];
                aentitylistentry[k] = entitylistentry;
                entitylistentry = entitylistentry1;
            } while(entitylistentry != null);
        }

    }

    public Object d(int i)
    {
        f.remove(Integer.valueOf(i));
        EntityListEntry entitylistentry = e(i);
        return entitylistentry != null ? entitylistentry.b : null;
    }

    final EntityListEntry e(int i)
    {
        int j = g(i);
        int k = a(j, a.length);
        EntityListEntry entitylistentry = a[k];
        EntityListEntry entitylistentry1;
        EntityListEntry entitylistentry2;
        for(entitylistentry1 = entitylistentry; entitylistentry1 != null; entitylistentry1 = entitylistentry2)
        {
            entitylistentry2 = entitylistentry1.c;
            if(entitylistentry1.a == i)
            {
                e++;
                b--;
                if(entitylistentry == entitylistentry1)
                    a[k] = entitylistentry2;
                else
                    entitylistentry.c = entitylistentry2;
                return entitylistentry1;
            }
            entitylistentry = entitylistentry1;
        }

        return entitylistentry1;
    }

    public void a()
    {
        e++;
        EntityListEntry aentitylistentry[] = a;
        for(int i = 0; i < aentitylistentry.length; i++)
            aentitylistentry[i] = null;

        b = 0;
    }

    private void a(int i, int j, Object obj, int k)
    {
        EntityListEntry entitylistentry = a[k];
        a[k] = new EntityListEntry(i, j, obj, entitylistentry);
        if(b++ >= c)
            h(2 * a.length);
    }

    static int f(int i)
    {
        return g(i);
    }

    private transient EntityListEntry a[];
    private transient int b;
    private int c;
    private final float d = 0.75F;
    private volatile transient int e;
    private Set f;
}
