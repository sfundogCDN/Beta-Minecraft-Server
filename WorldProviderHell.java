// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WorldProvider, WorldChunkManagerHell, BiomeBase, ChunkProviderHell, 
//            World, Block, IChunkProvider

public class WorldProviderHell extends WorldProvider
{

    public WorldProviderHell()
    {
    }

    public void a()
    {
        b = new WorldChunkManagerHell(BiomeBase.HELL, 1.0F, 0.0F);
        c = true;
        d = true;
        e = true;
        dimension = -1;
    }

    protected void c()
    {
        float f = 0.1F;
        for(int i = 0; i <= 15; i++)
        {
            float f1 = 1.0F - (float)i / 15F;
            this.f[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }

    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderHell(a, a.getSeed());
    }

    public boolean canSpawn(int i, int j)
    {
        int k = a.a(i, j);
        if(k == Block.BEDROCK.id)
            return false;
        if(k == 0)
            return false;
        return Block.o[k];
    }

    public float a(long l, float f)
    {
        return 0.5F;
    }

    public boolean d()
    {
        return false;
    }
}
