// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            IChunkProvider, MapGenCaves, WorldGenStronghold, WorldGenVillage, 
//            WorldGenMineshaft, WorldGenCanyon, NoiseGeneratorOctaves, World, 
//            WorldChunkManager, Block, BiomeBase, Chunk, 
//            MapGenBase, MathHelper, BlockSand, WorldGenLakes, 
//            WorldGenDungeons, SpawnerCreature, IProgressUpdate

public class ChunkProviderGenerate
    implements IChunkProvider
{

    public ChunkProviderGenerate(World world, long l1, boolean flag)
    {
        v = new double[256];
        w = new MapGenCaves();
        d = new WorldGenStronghold();
        e = new WorldGenVillage();
        f = new WorldGenMineshaft();
        x = new WorldGenCanyon();
        m = new int[32][32];
        s = world;
        t = flag;
        n = new Random(l1);
        o = new NoiseGeneratorOctaves(n, 16);
        p = new NoiseGeneratorOctaves(n, 16);
        q = new NoiseGeneratorOctaves(n, 8);
        r = new NoiseGeneratorOctaves(n, 4);
        a = new NoiseGeneratorOctaves(n, 10);
        b = new NoiseGeneratorOctaves(n, 16);
        c = new NoiseGeneratorOctaves(n, 8);
    }

    public void a(int i1, int j1, byte abyte0[])
    {
        byte byte0 = 4;
        s.getClass();
        int k1 = 128 / 8;
        s.getClass();
        byte byte1 = 63;
        int l1 = byte0 + 1;
        s.getClass();
        int i2 = 128 / 8 + 1;
        int j2 = byte0 + 1;
        y = s.getWorldChunkManager().getBiomes(y, i1 * 4 - 2, j1 * 4 - 2, l1 + 5, j2 + 5);
        u = a(u, i1 * byte0, 0, j1 * byte0, l1, i2, j2);
        for(int k2 = 0; k2 < byte0; k2++)
        {
            for(int l2 = 0; l2 < byte0; l2++)
            {
                for(int i3 = 0; i3 < k1; i3++)
                {
                    double d1 = 0.125D;
                    double d2 = u[((k2 + 0) * j2 + (l2 + 0)) * i2 + (i3 + 0)];
                    double d3 = u[((k2 + 0) * j2 + (l2 + 1)) * i2 + (i3 + 0)];
                    double d4 = u[((k2 + 1) * j2 + (l2 + 0)) * i2 + (i3 + 0)];
                    double d5 = u[((k2 + 1) * j2 + (l2 + 1)) * i2 + (i3 + 0)];
                    double d6 = (u[((k2 + 0) * j2 + (l2 + 0)) * i2 + (i3 + 1)] - d2) * d1;
                    double d7 = (u[((k2 + 0) * j2 + (l2 + 1)) * i2 + (i3 + 1)] - d3) * d1;
                    double d8 = (u[((k2 + 1) * j2 + (l2 + 0)) * i2 + (i3 + 1)] - d4) * d1;
                    double d9 = (u[((k2 + 1) * j2 + (l2 + 1)) * i2 + (i3 + 1)] - d5) * d1;
                    for(int j3 = 0; j3 < 8; j3++)
                    {
                        double d10 = 0.25D;
                        double d11 = d2;
                        double d12 = d3;
                        double d13 = (d4 - d2) * d10;
                        double d14 = (d5 - d3) * d10;
                        for(int k3 = 0; k3 < 4; k3++)
                        {
                            s.getClass();
                            s.getClass();
                            int l3 = k3 + k2 * 4 << 11 | 0 + l2 * 4 << 7 | i3 * 8 + j3;
                            s.getClass();
                            int i4 = 1 << 7;
                            double d15 = 0.25D;
                            double d16 = d11;
                            double d17 = (d12 - d11) * d15;
                            for(int j4 = 0; j4 < 4; j4++)
                            {
                                int k4 = 0;
                                if(i3 * 8 + j3 < byte1)
                                    k4 = Block.STATIONARY_WATER.id;
                                if(d16 > 0.0D)
                                    k4 = Block.STONE.id;
                                abyte0[l3] = (byte)k4;
                                l3 += i4;
                                d16 += d17;
                            }

                            d11 += d13;
                            d12 += d14;
                        }

                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                        d5 += d9;
                    }

                }

            }

        }

    }

    public void a(int i1, int j1, byte abyte0[], BiomeBase abiomebase[])
    {
        s.getClass();
        byte byte0 = 63;
        double d1 = 0.03125D;
        v = r.a(v, i1 * 16, j1 * 16, 0, 16, 16, 1, d1 * 2D, d1 * 2D, d1 * 2D);
        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int l1 = 0; l1 < 16; l1++)
            {
                BiomeBase biomebase = abiomebase[l1 + k1 * 16];
                int i2 = (int)(v[k1 + l1 * 16] / 3D + 3D + n.nextDouble() * 0.25D);
                int j2 = -1;
                byte byte1 = biomebase.n;
                byte byte2 = biomebase.o;
                s.getClass();
                for(int k2 = 127; k2 >= 0; k2--)
                {
                    s.getClass();
                    int l2 = (l1 * 16 + k1) * 128 + k2;
                    if(k2 <= 0 + n.nextInt(5))
                    {
                        abyte0[l2] = (byte)Block.BEDROCK.id;
                        continue;
                    }
                    byte byte3 = abyte0[l2];
                    if(byte3 == 0)
                    {
                        j2 = -1;
                        continue;
                    }
                    if(byte3 != Block.STONE.id)
                        continue;
                    if(j2 == -1)
                    {
                        if(i2 <= 0)
                        {
                            byte1 = 0;
                            byte2 = (byte)Block.STONE.id;
                        } else
                        if(k2 >= byte0 - 4 && k2 <= byte0 + 1)
                        {
                            byte1 = biomebase.n;
                            byte2 = biomebase.o;
                        }
                        if(k2 < byte0 && byte1 == 0)
                            byte1 = (byte)Block.STATIONARY_WATER.id;
                        j2 = i2;
                        if(k2 >= byte0 - 1)
                            abyte0[l2] = byte1;
                        else
                            abyte0[l2] = byte2;
                        continue;
                    }
                    if(j2 <= 0)
                        continue;
                    j2--;
                    abyte0[l2] = byte2;
                    if(j2 == 0 && byte2 == Block.SAND.id)
                    {
                        j2 = n.nextInt(4);
                        byte2 = (byte)Block.SANDSTONE.id;
                    }
                }

            }

        }

    }

    public Chunk getChunkAt(int i1, int j1)
    {
        return getOrCreateChunk(i1, j1);
    }

    public Chunk getOrCreateChunk(int i1, int j1)
    {
        n.setSeed((long)i1 * 0x4f9939f508L + (long)j1 * 0x1ef1565bd5L);
        s.getClass();
        byte abyte0[] = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(s, abyte0, i1, j1);
        a(i1, j1, abyte0);
        y = s.getWorldChunkManager().a(y, i1 * 16, j1 * 16, 16, 16);
        a(i1, j1, abyte0, y);
        w.a(this, s, i1, j1, abyte0);
        if(t)
        {
            d.a(this, s, i1, j1, abyte0);
            f.a(this, s, i1, j1, abyte0);
            e.a(this, s, i1, j1, abyte0);
        }
        x.a(this, s, i1, j1, abyte0);
        chunk.initLighting();
        return chunk;
    }

    private double[] a(double ad[], int i1, int j1, int k1, int l1, int i2, int j2)
    {
        if(ad == null)
            ad = new double[l1 * i2 * j2];
        if(l == null)
        {
            l = new float[25];
            for(int k2 = -2; k2 <= 2; k2++)
            {
                for(int l2 = -2; l2 <= 2; l2++)
                {
                    float f1 = 10F / MathHelper.c((float)(k2 * k2 + l2 * l2) + 0.2F);
                    l[k2 + 2 + (l2 + 2) * 5] = f1;
                }

            }

        }
        double d1 = 684.41200000000003D;
        double d2 = 684.41200000000003D;
        j = a.a(j, i1, k1, l1, j2, 1.121D, 1.121D, 0.5D);
        k = b.a(k, i1, k1, l1, j2, 200D, 200D, 0.5D);
        g = q.a(g, i1, j1, k1, l1, i2, j2, d1 / 80D, d2 / 160D, d1 / 80D);
        h = o.a(h, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        i = p.a(i, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        i1 = k1 = 0;
        int i3 = 0;
        int j3 = 0;
        for(int k3 = 0; k3 < l1; k3++)
        {
            for(int l3 = 0; l3 < j2; l3++)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                byte byte0 = 2;
                BiomeBase biomebase = y[k3 + 2 + (l3 + 2) * (l1 + 5)];
                for(int i4 = -byte0; i4 <= byte0; i4++)
                {
                    for(int j4 = -byte0; j4 <= byte0; j4++)
                    {
                        BiomeBase biomebase1 = y[k3 + i4 + 2 + (l3 + j4 + 2) * (l1 + 5)];
                        float f5 = l[i4 + 2 + (j4 + 2) * 5] / (biomebase1.q + 2.0F);
                        if(biomebase1.q > biomebase.q)
                            f5 /= 2.0F;
                        f2 += biomebase1.r * f5;
                        f3 += biomebase1.q * f5;
                        f4 += f5;
                    }

                }

                f2 /= f4;
                f3 /= f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4F - 1.0F) / 8F;
                double d3 = k[j3] / 8000D;
                if(d3 < 0.0D)
                    d3 = -d3 * 0.29999999999999999D;
                d3 = d3 * 3D - 2D;
                if(d3 < 0.0D)
                {
                    d3 /= 2D;
                    if(d3 < -1D)
                        d3 = -1D;
                    d3 /= 1.3999999999999999D;
                    d3 /= 2D;
                } else
                {
                    if(d3 > 1.0D)
                        d3 = 1.0D;
                    d3 /= 8D;
                }
                j3++;
                for(int k4 = 0; k4 < i2; k4++)
                {
                    double d4 = f3;
                    double d5 = f2;
                    d4 += d3 * 0.20000000000000001D;
                    d4 = (d4 * (double)i2) / 16D;
                    double d6 = (double)i2 / 2D + d4 * 4D;
                    double d7 = 0.0D;
                    s.getClass();
                    double d8 = (((double)k4 - d6) * 12D * 128D) / 128D / d5;
                    if(d8 < 0.0D)
                        d8 *= 4D;
                    double d9 = h[i3] / 512D;
                    double d10 = i[i3] / 512D;
                    double d11 = (g[i3] / 10D + 1.0D) / 2D;
                    if(d11 < 0.0D)
                        d7 = d9;
                    else
                    if(d11 > 1.0D)
                        d7 = d10;
                    else
                        d7 = d9 + (d10 - d9) * d11;
                    d7 -= d8;
                    if(k4 > i2 - 4)
                    {
                        double d12 = (float)(k4 - (i2 - 4)) / 3F;
                        d7 = d7 * (1.0D - d12) + -10D * d12;
                    }
                    ad[i3] = d7;
                    i3++;
                }

            }

        }

        return ad;
    }

    public boolean isChunkLoaded(int i1, int j1)
    {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i1, int j1)
    {
        BlockSand.instaFall = true;
        int k1 = i1 * 16;
        int l1 = j1 * 16;
        BiomeBase biomebase = s.getWorldChunkManager().getBiome(k1 + 16, l1 + 16);
        n.setSeed(s.getSeed());
        long l2 = (n.nextLong() / 2L) * 2L + 1L;
        long l3 = (n.nextLong() / 2L) * 2L + 1L;
        n.setSeed((long)i1 * l2 + (long)j1 * l3 ^ s.getSeed());
        boolean flag = false;
        if(t)
        {
            d.a(s, n, i1, j1);
            f.a(s, n, i1, j1);
            flag = e.a(s, n, i1, j1);
        }
        if(!flag && n.nextInt(4) == 0)
        {
            int i2 = k1 + n.nextInt(16) + 8;
            s.getClass();
            int i3 = n.nextInt(128);
            int i4 = l1 + n.nextInt(16) + 8;
            (new WorldGenLakes(Block.STATIONARY_WATER.id)).a(s, n, i2, i3, i4);
        }
        if(!flag && n.nextInt(8) == 0)
        {
            int j2 = k1 + n.nextInt(16) + 8;
            s.getClass();
            int j3 = n.nextInt(n.nextInt(128 - 8) + 8);
            int j4 = l1 + n.nextInt(16) + 8;
            s.getClass();
            if(j3 < 63 || n.nextInt(10) == 0)
                (new WorldGenLakes(Block.STATIONARY_LAVA.id)).a(s, n, j2, j3, j4);
        }
        for(int k2 = 0; k2 < 8; k2++)
        {
            int k3 = k1 + n.nextInt(16) + 8;
            s.getClass();
            int k4 = n.nextInt(128);
            int l4 = l1 + n.nextInt(16) + 8;
            if(!(new WorldGenDungeons()).a(s, n, k3, k4, l4));
        }

        biomebase.a(s, n, k1, l1);
        SpawnerCreature.a(s, biomebase, k1 + 8, l1 + 8, 16, 16, n);
        BlockSand.instaFall = false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean unloadChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    private Random n;
    private NoiseGeneratorOctaves o;
    private NoiseGeneratorOctaves p;
    private NoiseGeneratorOctaves q;
    private NoiseGeneratorOctaves r;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World s;
    private final boolean t;
    private double u[];
    private double v[];
    private MapGenBase w;
    public WorldGenStronghold d;
    public WorldGenVillage e;
    public WorldGenMineshaft f;
    private MapGenBase x;
    private BiomeBase y[];
    double g[];
    double h[];
    double i[];
    double j[];
    double k[];
    float l[];
    int m[][];
}
