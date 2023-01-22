// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BiomeBase, BiomeDecorator, WorldGenerator

public class BiomeSwamp extends BiomeBase
{

    protected BiomeSwamp(int i)
    {
        super(i);
        u.r = 2;
        u.s = -999;
        u.u = 1;
        u.v = 8;
        u.w = 10;
        u.A = 1;
    }

    public WorldGenerator a(Random random)
    {
        return C;
    }
}
