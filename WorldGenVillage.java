// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructureGenerator, World, WorldChunkManager, WorldGenVillageStart, 
//            BiomeBase, StructureStart

public class WorldGenVillage extends StructureGenerator
{

    public WorldGenVillage()
    {
    }

    protected boolean a(int i, int j)
    {
        byte byte0 = 32;
        byte byte1 = 8;
        int k = i;
        int l = j;
        if(i < 0)
            i -= byte0 - 1;
        if(j < 0)
            j -= byte0 - 1;
        int i1 = i / byte0;
        int j1 = j / byte0;
        Random random = d.t(i1, j1, 0x9e7f70);
        i1 *= byte0;
        j1 *= byte0;
        i1 += random.nextInt(byte0 - byte1);
        j1 += random.nextInt(byte0 - byte1);
        i = k;
        j = l;
        if(i == i1 && j == j1)
        {
            boolean flag = d.getWorldChunkManager().a(i * 16 + 8, j * 16 + 8, 0, a);
            if(flag)
                return true;
        }
        return false;
    }

    protected StructureStart b(int i, int j)
    {
        return new WorldGenVillageStart(d, c, i, j);
    }

    public static List a;

    static 
    {
        a = Arrays.asList(new BiomeBase[] {
            BiomeBase.PLAINS, BiomeBase.DESERT
        });
    }
}
