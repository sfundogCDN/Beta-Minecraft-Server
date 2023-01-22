// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityList

class EntityListEntry
{

    EntityListEntry(int i, int j, Object obj, EntityListEntry entitylistentry)
    {
        b = obj;
        c = entitylistentry;
        a = j;
        d = i;
    }

    public final int a()
    {
        return a;
    }

    public final Object b()
    {
        return b;
    }

    public final boolean equals(Object obj)
    {
        if(!(obj instanceof EntityListEntry))
            return false;
        EntityListEntry entitylistentry = (EntityListEntry)obj;
        Integer integer = Integer.valueOf(a());
        Integer integer1 = Integer.valueOf(entitylistentry.a());
        if(integer == integer1 || integer != null && integer.equals(integer1))
        {
            Object obj1 = b();
            Object obj2 = entitylistentry.b();
            if(obj1 == obj2 || obj1 != null && obj1.equals(obj2))
                return true;
        }
        return false;
    }

    public final int hashCode()
    {
        return EntityList.f(a);
    }

    public final String toString()
    {
        return (new StringBuilder()).append(a()).append("=").append(b()).toString();
    }

    final int a;
    Object b;
    EntityListEntry c;
    final int d;
}
