// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructureGenerator, BiomeBase, ChunkCoordIntPair, World, 
//            WorldChunkManager, ChunkPosition, WorldGenStrongholdStart, StructureStart

public class WorldGenStronghold extends StructureGenerator
{

    public WorldGenStronghold()
    {
        a = (new BiomeBase[] {
            BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND
        });
        g = new ChunkCoordIntPair[3];
    }

    protected boolean a(int i, int j)
    {
        if(!f)
        {
            c.setSeed(this.d.getSeed());
            double d = c.nextDouble() * 3.1415926535897931D * 2D;
            for(int k = 0; k < g.length; k++)
            {
                double d1 = (1.25D + c.nextDouble()) * 32D;
                int i1 = (int)Math.round(Math.cos(d) * d1);
                int j1 = (int)Math.round(Math.sin(d) * d1);
                ArrayList arraylist = new ArrayList();
                BiomeBase abiomebase[] = a;
                int k1 = abiomebase.length;
                for(int l1 = 0; l1 < k1; l1++)
                {
                    BiomeBase biomebase = abiomebase[l1];
                    arraylist.add(biomebase);
                }

                ChunkPosition chunkposition = this.d.getWorldChunkManager().a((i1 << 4) + 8, (j1 << 4) + 8, 112, arraylist, c);
                if(chunkposition != null)
                {
                    i1 = chunkposition.x >> 4;
                    j1 = chunkposition.z >> 4;
                } else
                {
                    System.out.println((new StringBuilder()).append("Placed stronghold in INVALID biome at (").append(i1).append(", ").append(j1).append(")").toString());
                }
                g[k] = new ChunkCoordIntPair(i1, j1);
                d += 6.2831853071795862D / (double)g.length;
            }

            f = true;
        }
        ChunkCoordIntPair achunkcoordintpair[] = g;
        int i2 = achunkcoordintpair.length;
        for(int l = 0; l < i2; l++)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[l];
            if(i == chunkcoordintpair.x && j == chunkcoordintpair.z)
                return true;
        }

        return false;
    }

    protected StructureStart b(int i, int j)
    {
        return new WorldGenStrongholdStart(d, c, i, j);
    }

    private BiomeBase a[];
    private boolean f;
    private ChunkCoordIntPair g[];
}
