// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePIeceBlockSelector, Block, WorldGenStrongholdUnknown

class WorldGenStrongholdStones extends StructurePIeceBlockSelector
{

    private WorldGenStrongholdStones()
    {
    }

    public void a(Random random, int i, int j, int k, boolean flag)
    {
        if(!flag)
        {
            a = 0;
            b = 0;
        } else
        {
            a = Block.SMOOTH_BRICK.id;
            float f = random.nextFloat();
            if(f < 0.2F)
                b = 2;
            else
            if(f < 0.5F)
                b = 1;
            else
            if(f < 0.55F)
            {
                a = Block.MONSTER_EGGS.id;
                b = 2;
            } else
            {
                b = 0;
            }
        }
    }

    WorldGenStrongholdStones(WorldGenStrongholdUnknown worldgenstrongholdunknown)
    {
        this();
    }
}
