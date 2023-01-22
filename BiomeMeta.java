// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WeightedRandomChoice

public class BiomeMeta extends WeightedRandomChoice
{

    public BiomeMeta(Class class1, int i, int j, int k)
    {
        super(i);
        a = class1;
        b = j;
        c = k;
    }

    public Class a;
    public int b;
    public int c;
}
