// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            BiomeBase, BiomeMeta, EntityGhast, EntityPigZombie

public class BiomeHell extends BiomeBase
{

    public BiomeHell(int i)
    {
        super(i);
        v.clear();
        w.clear();
        x.clear();
        v.add(new BiomeMeta(net/minecraft/server/EntityGhast, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntityPigZombie, 10, 4, 4));
    }
}
