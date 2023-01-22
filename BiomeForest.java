// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BiomeBase, BiomeMeta, EntityWolf, BiomeDecorator, 
//            WorldGenerator

public class BiomeForest extends BiomeBase
{

    public BiomeForest(int i)
    {
        super(i);
        w.add(new BiomeMeta(net/minecraft/server/EntityWolf, 5, 4, 4));
        u.r = 10;
        u.t = 2;
    }

    public WorldGenerator a(Random random)
    {
        if(random.nextInt(5) == 0)
            return B;
        if(random.nextInt(10) == 0)
            return A;
        else
            return z;
    }
}
