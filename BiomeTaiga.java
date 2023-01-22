// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BiomeBase, BiomeMeta, EntityWolf, BiomeDecorator, 
//            WorldGenTaiga1, WorldGenTaiga2, WorldGenerator

public class BiomeTaiga extends BiomeBase
{

    public BiomeTaiga(int i)
    {
        super(i);
        w.add(new BiomeMeta(net/minecraft/server/EntityWolf, 8, 4, 4));
        u.r = 10;
        u.t = 1;
    }

    public WorldGenerator a(Random random)
    {
        if(random.nextInt(3) == 0)
            return new WorldGenTaiga1();
        else
            return new WorldGenTaiga2();
    }
}
