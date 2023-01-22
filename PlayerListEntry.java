// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            PlayerList

class PlayerListEntry
{

    PlayerListEntry(int i, long l, Object obj, PlayerListEntry playerlistentry)
    {
        b = obj;
        c = playerlistentry;
        a = l;
        d = i;
    }

    public final long a()
    {
        return a;
    }

    public final Object b()
    {
        return b;
    }

    public final boolean equals(Object obj)
    {
        if(!(obj instanceof PlayerListEntry))
            return false;
        PlayerListEntry playerlistentry = (PlayerListEntry)obj;
        Long long1 = Long.valueOf(a());
        Long long2 = Long.valueOf(playerlistentry.a());
        if(long1 == long2 || long1 != null && long1.equals(long2))
        {
            Object obj1 = b();
            Object obj2 = playerlistentry.b();
            if(obj1 == obj2 || obj1 != null && obj1.equals(obj2))
                return true;
        }
        return false;
    }

    public final int hashCode()
    {
        return PlayerList.f(a);
    }

    public final String toString()
    {
        return (new StringBuilder()).append(a()).append("=").append(b()).toString();
    }

    final long a;
    Object b;
    PlayerListEntry c;
    final int d;
}
