// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            World

public abstract class WorldGenerator
{

    public WorldGenerator()
    {
    }

    public abstract boolean a(World world, Random random, int i, int j, int k);

    public void a(double d, double d1, double d2)
    {
    }
}
