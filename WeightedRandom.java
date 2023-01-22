// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WeightedRandomChoice

public class WeightedRandom
{

    public WeightedRandom()
    {
    }

    public static int a(Collection collection)
    {
        int i = 0;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            WeightedRandomChoice weightedrandomchoice = (WeightedRandomChoice)iterator.next();
            i += weightedrandomchoice.d;
        }

        return i;
    }

    public static WeightedRandomChoice a(Random random, Collection collection, int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException();
        int j = random.nextInt(i);
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            WeightedRandomChoice weightedrandomchoice = (WeightedRandomChoice)iterator.next();
            j -= weightedrandomchoice.d;
            if(j < 0)
                return weightedrandomchoice;
        }

        return null;
    }

    public static WeightedRandomChoice a(Random random, Collection collection)
    {
        return a(random, collection, a(collection));
    }

    public static int a(WeightedRandomChoice aweightedrandomchoice[])
    {
        int i = 0;
        WeightedRandomChoice aweightedrandomchoice1[] = aweightedrandomchoice;
        int j = aweightedrandomchoice1.length;
        for(int k = 0; k < j; k++)
        {
            WeightedRandomChoice weightedrandomchoice = aweightedrandomchoice1[k];
            i += weightedrandomchoice.d;
        }

        return i;
    }

    public static WeightedRandomChoice a(Random random, WeightedRandomChoice aweightedrandomchoice[], int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException();
        int j = random.nextInt(i);
        WeightedRandomChoice aweightedrandomchoice1[] = aweightedrandomchoice;
        int k = aweightedrandomchoice1.length;
        for(int l = 0; l < k; l++)
        {
            WeightedRandomChoice weightedrandomchoice = aweightedrandomchoice1[l];
            j -= weightedrandomchoice.d;
            if(j < 0)
                return weightedrandomchoice;
        }

        return null;
    }

    public static WeightedRandomChoice a(Random random, WeightedRandomChoice aweightedrandomchoice[])
    {
        return a(random, aweightedrandomchoice, a(aweightedrandomchoice));
    }
}
