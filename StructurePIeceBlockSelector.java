// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

public abstract class StructurePIeceBlockSelector
{

    protected StructurePIeceBlockSelector()
    {
    }

    public abstract void a(Random random, int i, int j, int k, boolean flag);

    public int a()
    {
        return a;
    }

    public int b()
    {
        return b;
    }

    protected int a;
    protected int b;
}
