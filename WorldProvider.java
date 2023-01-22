// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WorldChunkManager, ChunkProviderGenerate, World, WorldData, 
//            Block, BlockGrass, WorldProviderHell, WorldProviderNormal, 
//            WorldProviderSky, IChunkProvider

public abstract class WorldProvider
{

    public WorldProvider()
    {
        c = false;
        d = false;
        e = false;
        f = new float[16];
        dimension = 0;
        h = new float[4];
    }

    public final void a(World world)
    {
        a = world;
        a();
        c();
    }

    protected void c()
    {
        float f1 = 0.0F;
        for(int i = 0; i <= 15; i++)
        {
            float f2 = 1.0F - (float)i / 15F;
            f[i] = ((1.0F - f2) / (f2 * 3F + 1.0F)) * (1.0F - f1) + f1;
        }

    }

    protected void a()
    {
        b = new WorldChunkManager(a);
    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderGenerate(a, a.getSeed(), a.p().o());
    }

    public boolean canSpawn(int i, int j)
    {
        int k = a.a(i, j);
        return k == Block.GRASS.id;
    }

    public float a(long l, float f1)
    {
        int i = (int)(l % 24000L);
        float f2 = ((float)i + f1) / 24000F - 0.25F;
        if(f2 < 0.0F)
            f2++;
        if(f2 > 1.0F)
            f2--;
        float f3 = f2;
        f2 = 1.0F - (float)((Math.cos((double)f2 * 3.1415926535897931D) + 1.0D) / 2D);
        f2 = f3 + (f2 - f3) / 3F;
        return f2;
    }

    public boolean d()
    {
        return true;
    }

    public static WorldProvider byDimension(int i)
    {
        if(i == -1)
            return new WorldProviderHell();
        if(i == 0)
            return new WorldProviderNormal();
        if(i == 1)
            return new WorldProviderSky();
        else
            return null;
    }

    public World a;
    public WorldChunkManager b;
    public boolean c;
    public boolean d;
    public boolean e;
    public float f[];
    public int dimension;
    private float h[];
}
