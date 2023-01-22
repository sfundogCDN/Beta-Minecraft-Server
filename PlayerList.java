// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            PlayerListEntry

public class PlayerList
{

    public PlayerList()
    {
        c = 12;
        entries = new PlayerListEntry[16];
    }

    private static int g(long l)
    {
        return a((int)(l ^ l >>> 32));
    }

    private static int a(int i)
    {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j)
    {
        return i & j - 1;
    }

    public Object getEntry(long l)
    {
        int i = g(l);
        for(PlayerListEntry playerlistentry = entries[a(i, entries.length)]; playerlistentry != null; playerlistentry = playerlistentry.c)
            if(playerlistentry.a == l)
                return playerlistentry.b;

        return null;
    }

    public boolean contains(long l)
    {
        return c(l) != null;
    }

    final PlayerListEntry c(long l)
    {
        int i = g(l);
        for(PlayerListEntry playerlistentry = entries[a(i, entries.length)]; playerlistentry != null; playerlistentry = playerlistentry.c)
            if(playerlistentry.a == l)
                return playerlistentry;

        return null;
    }

    public void put(long l, Object obj)
    {
        int i = g(l);
        int j = a(i, entries.length);
        for(PlayerListEntry playerlistentry = entries[j]; playerlistentry != null; playerlistentry = playerlistentry.c)
            if(playerlistentry.a == l)
                playerlistentry.b = obj;

        e++;
        a(i, l, obj, j);
    }

    private void b(int i)
    {
        PlayerListEntry aplayerlistentry[] = entries;
        int j = aplayerlistentry.length;
        if(j == 0x40000000)
        {
            c = 0x7fffffff;
            return;
        } else
        {
            PlayerListEntry aplayerlistentry1[] = new PlayerListEntry[i];
            a(aplayerlistentry1);
            entries = aplayerlistentry1;
            c = (int)((float)i * d);
            return;
        }
    }

    private void a(PlayerListEntry aplayerlistentry[])
    {
        PlayerListEntry aplayerlistentry1[] = entries;
        int i = aplayerlistentry.length;
        for(int j = 0; j < aplayerlistentry1.length; j++)
        {
            PlayerListEntry playerlistentry = aplayerlistentry1[j];
            if(playerlistentry == null)
                continue;
            aplayerlistentry1[j] = null;
            do
            {
                PlayerListEntry playerlistentry1 = playerlistentry.c;
                int k = a(playerlistentry.d, i);
                playerlistentry.c = aplayerlistentry[k];
                aplayerlistentry[k] = playerlistentry;
                playerlistentry = playerlistentry1;
            } while(playerlistentry != null);
        }

    }

    public Object remove(long l)
    {
        PlayerListEntry playerlistentry = e(l);
        return playerlistentry != null ? playerlistentry.b : null;
    }

    final PlayerListEntry e(long l)
    {
        int i = g(l);
        int j = a(i, entries.length);
        PlayerListEntry playerlistentry = entries[j];
        PlayerListEntry playerlistentry1;
        PlayerListEntry playerlistentry2;
        for(playerlistentry1 = playerlistentry; playerlistentry1 != null; playerlistentry1 = playerlistentry2)
        {
            playerlistentry2 = playerlistentry1.c;
            if(playerlistentry1.a == l)
            {
                e++;
                count--;
                if(playerlistentry == playerlistentry1)
                    entries[j] = playerlistentry2;
                else
                    playerlistentry.c = playerlistentry2;
                return playerlistentry1;
            }
            playerlistentry = playerlistentry1;
        }

        return playerlistentry1;
    }

    private void a(int i, long l, Object obj, int j)
    {
        PlayerListEntry playerlistentry = entries[j];
        entries[j] = new PlayerListEntry(i, l, obj, playerlistentry);
        if(count++ >= c)
            b(2 * entries.length);
    }

    static int f(long l)
    {
        return g(l);
    }

    private transient PlayerListEntry entries[];
    private transient int count;
    private int c;
    private final float d = 0.75F;
    private volatile transient int e;
}
