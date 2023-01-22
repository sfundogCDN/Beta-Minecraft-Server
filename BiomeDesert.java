// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            BiomeBase, Block, BiomeDecorator

public class BiomeDesert extends BiomeBase
{

    public BiomeDesert(int i)
    {
        super(i);
        w.clear();
        n = (byte)Block.SAND.id;
        o = (byte)Block.SAND.id;
        u.r = -999;
        u.u = 2;
        u.w = 50;
        u.x = 10;
    }
}
