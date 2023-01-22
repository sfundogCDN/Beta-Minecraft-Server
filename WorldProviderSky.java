// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WorldProvider, WorldChunkManagerHell, BiomeBase, ChunkProviderSky, 
//            World, Block, Material, IChunkProvider

public class WorldProviderSky extends WorldProvider
{

    public WorldProviderSky()
    {
    }

    public void a()
    {
        b = new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F);
        dimension = 1;
    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderSky(a, a.getSeed());
    }

    public float a(long l, float f)
    {
        return 0.0F;
    }

    public boolean canSpawn(int i, int j)
    {
        int k = a.a(i, j);
        if(k == 0)
            return false;
        else
            return Block.byId[k].material.isSolid();
    }
}
