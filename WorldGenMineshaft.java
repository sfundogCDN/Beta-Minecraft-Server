// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructureGenerator, WorldGenMineshaftStart, StructureStart

public class WorldGenMineshaft extends StructureGenerator
{

    public WorldGenMineshaft()
    {
    }

    protected boolean a(int i, int j)
    {
        return c.nextInt(80) == 0 && c.nextInt(80) < Math.max(Math.abs(i), Math.abs(j));
    }

    protected StructureStart b(int i, int j)
    {
        return new WorldGenMineshaftStart(d, c, i, j);
    }
}
