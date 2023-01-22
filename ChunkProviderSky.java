// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            IChunkProvider, MapGenCaves, NoiseGeneratorOctaves, Block, 
//            BiomeBase, Chunk, World, WorldChunkManager, 
//            MapGenBase, BlockSand, WorldGenLakes, WorldGenDungeons, 
//            WorldGenClay, WorldGenMinable, WorldGenerator, WorldGenFlowers, 
//            BlockFlower, WorldGenReed, WorldGenPumpkin, WorldGenCactus, 
//            WorldGenLiquids, IProgressUpdate

public class ChunkProviderSky
    implements IChunkProvider
{

    public ChunkProviderSky(World world, long l1)
    {
        r = new double[256];
        s = new double[256];
        t = new double[256];
        u = new MapGenCaves();
        i = new int[32][32];
        p = world;
        j = new Random(l1);
        k = new NoiseGeneratorOctaves(j, 16);
        l = new NoiseGeneratorOctaves(j, 16);
        m = new NoiseGeneratorOctaves(j, 8);
        n = new NoiseGeneratorOctaves(j, 4);
        o = new NoiseGeneratorOctaves(j, 4);
        a = new NoiseGeneratorOctaves(j, 10);
        b = new NoiseGeneratorOctaves(j, 16);
        c = new NoiseGeneratorOctaves(j, 8);
    }

    public void a(int i1, int j1, byte abyte0[], BiomeBase abiomebase[])
    {
        byte byte0 = 2;
        int k1 = byte0 + 1;
        p.getClass();
        int l1 = 128 / 4 + 1;
        int i2 = byte0 + 1;
        q = a(q, i1 * byte0, 0, j1 * byte0, k1, l1, i2);
label0:
        for(int j2 = 0; j2 < byte0; j2++)
        {
            int k2 = 0;
            do
            {
label1:
                {
                    if(k2 >= byte0)
                        continue label0;
                    int l2 = 0;
                    do
                    {
                        p.getClass();
                        if(l2 >= 128 / 4)
                            break label1;
                        double d1 = 0.25D;
                        double d2 = q[((j2 + 0) * i2 + (k2 + 0)) * l1 + (l2 + 0)];
                        double d3 = q[((j2 + 0) * i2 + (k2 + 1)) * l1 + (l2 + 0)];
                        double d4 = q[((j2 + 1) * i2 + (k2 + 0)) * l1 + (l2 + 0)];
                        double d5 = q[((j2 + 1) * i2 + (k2 + 1)) * l1 + (l2 + 0)];
                        double d6 = (q[((j2 + 0) * i2 + (k2 + 0)) * l1 + (l2 + 1)] - d2) * d1;
                        double d7 = (q[((j2 + 0) * i2 + (k2 + 1)) * l1 + (l2 + 1)] - d3) * d1;
                        double d8 = (q[((j2 + 1) * i2 + (k2 + 0)) * l1 + (l2 + 1)] - d4) * d1;
                        double d9 = (q[((j2 + 1) * i2 + (k2 + 1)) * l1 + (l2 + 1)] - d5) * d1;
                        for(int i3 = 0; i3 < 4; i3++)
                        {
                            double d10 = 0.125D;
                            double d11 = d2;
                            double d12 = d3;
                            double d13 = (d4 - d2) * d10;
                            double d14 = (d5 - d3) * d10;
                            for(int j3 = 0; j3 < 8; j3++)
                            {
                                p.getClass();
                                p.getClass();
                                int k3 = j3 + j2 * 8 << 11 | 0 + k2 * 8 << 7 | l2 * 4 + i3;
                                p.getClass();
                                int l3 = 1 << 7;
                                double d15 = 0.125D;
                                double d16 = d11;
                                double d17 = (d12 - d11) * d15;
                                for(int i4 = 0; i4 < 8; i4++)
                                {
                                    int j4 = 0;
                                    if(d16 > 0.0D)
                                        j4 = Block.STONE.id;
                                    abyte0[k3] = (byte)j4;
                                    k3 += l3;
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

                        l2++;
                    } while(true);
                }
                k2++;
            } while(true);
        }

    }

    public void b(int i1, int j1, byte abyte0[], BiomeBase abiomebase[])
    {
        double d1 = 0.03125D;
        r = n.a(r, i1 * 16, j1 * 16, 0, 16, 16, 1, d1, d1, 1.0D);
        s = n.a(s, i1 * 16, 109, j1 * 16, 16, 1, 16, d1, 1.0D, d1);
        t = o.a(t, i1 * 16, j1 * 16, 0, 16, 16, 1, d1 * 2D, d1 * 2D, d1 * 2D);
        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int l1 = 0; l1 < 16; l1++)
            {
                BiomeBase biomebase = abiomebase[k1 + l1 * 16];
                int i2 = (int)(t[k1 + l1 * 16] / 3D + 3D + j.nextDouble() * 0.25D);
                int j2 = -1;
                byte byte0 = biomebase.n;
                byte byte1 = biomebase.o;
                p.getClass();
                for(int k2 = 127; k2 >= 0; k2--)
                {
                    p.getClass();
                    int l2 = (l1 * 16 + k1) * 128 + k2;
                    byte byte2 = abyte0[l2];
                    if(byte2 == 0)
                    {
                        j2 = -1;
                        continue;
                    }
                    if(byte2 != Block.STONE.id)
                        continue;
                    if(j2 == -1)
                    {
                        if(i2 <= 0)
                        {
                            byte0 = 0;
                            byte1 = (byte)Block.STONE.id;
                        }
                        j2 = i2;
                        if(k2 >= 0)
                            abyte0[l2] = byte0;
                        else
                            abyte0[l2] = byte1;
                        continue;
                    }
                    if(j2 <= 0)
                        continue;
                    j2--;
                    abyte0[l2] = byte1;
                    if(j2 == 0 && byte1 == Block.SAND.id)
                    {
                        j2 = j.nextInt(4);
                        byte1 = (byte)Block.SANDSTONE.id;
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
        j.setSeed((long)i1 * 0x4f9939f508L + (long)j1 * 0x1ef1565bd5L);
        p.getClass();
        byte abyte0[] = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(p, abyte0, i1, j1);
        v = p.getWorldChunkManager().a(v, i1 * 16, j1 * 16, 16, 16);
        a(i1, j1, abyte0, v);
        b(i1, j1, abyte0, v);
        u.a(this, p, i1, j1, abyte0);
        chunk.initLighting();
        return chunk;
    }

    private double[] a(double ad[], int i1, int j1, int k1, int l1, int i2, int j2)
    {
        if(ad == null)
            ad = new double[l1 * i2 * j2];
        double d1 = 684.41200000000003D;
        double d2 = 684.41200000000003D;
        g = a.a(g, i1, k1, l1, j2, 1.121D, 1.121D, 0.5D);
        h = b.a(h, i1, k1, l1, j2, 200D, 200D, 0.5D);
        d1 *= 2D;
        d = m.a(d, i1, j1, k1, l1, i2, j2, d1 / 80D, d2 / 160D, d1 / 80D);
        e = k.a(e, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        f = l.a(f, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        int k2 = 0;
        int l2 = 0;
        for(int i3 = 0; i3 < l1; i3++)
        {
            for(int j3 = 0; j3 < j2; j3++)
            {
                double d3 = (g[l2] + 256D) / 512D;
                if(d3 > 1.0D)
                    d3 = 1.0D;
                double d4 = h[l2] / 8000D;
                if(d4 < 0.0D)
                    d4 = -d4 * 0.29999999999999999D;
                d4 = d4 * 3D - 2D;
                if(d4 > 1.0D)
                    d4 = 1.0D;
                d4 /= 8D;
                d4 = 0.0D;
                if(d3 < 0.0D)
                    d3 = 0.0D;
                d3 += 0.5D;
                d4 = (d4 * (double)i2) / 16D;
                l2++;
                double d5 = (double)i2 / 2D;
                for(int k3 = 0; k3 < i2; k3++)
                {
                    double d6 = 0.0D;
                    double d7 = (((double)k3 - d5) * 8D) / d3;
                    if(d7 < 0.0D)
                        d7 *= -1D;
                    double d8 = e[k2] / 512D;
                    double d9 = f[k2] / 512D;
                    double d10 = (d[k2] / 10D + 1.0D) / 2D;
                    if(d10 < 0.0D)
                        d6 = d8;
                    else
                    if(d10 > 1.0D)
                        d6 = d9;
                    else
                        d6 = d8 + (d9 - d8) * d10;
                    d6 -= 8D;
                    int l3 = 32;
                    if(k3 > i2 - l3)
                    {
                        double d11 = (float)(k3 - (i2 - l3)) / ((float)l3 - 1.0F);
                        d6 = d6 * (1.0D - d11) + -30D * d11;
                    }
                    l3 = 8;
                    if(k3 < l3)
                    {
                        double d12 = (float)(l3 - k3) / ((float)l3 - 1.0F);
                        d6 = d6 * (1.0D - d12) + -30D * d12;
                    }
                    ad[k2] = d6;
                    k2++;
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
        BiomeBase biomebase = p.getWorldChunkManager().getBiome(k1 + 16, l1 + 16);
        j.setSeed(p.getSeed());
        long l2 = (j.nextLong() / 2L) * 2L + 1L;
        long l3 = (j.nextLong() / 2L) * 2L + 1L;
        j.setSeed((long)i1 * l2 + (long)j1 * l3 ^ p.getSeed());
        double d1 = 0.25D;
        if(j.nextInt(4) == 0)
        {
            int i2 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int l5 = j.nextInt(128);
            int i9 = l1 + j.nextInt(16) + 8;
            (new WorldGenLakes(Block.STATIONARY_WATER.id)).a(p, j, i2, l5, i9);
        }
        if(j.nextInt(8) == 0)
        {
            int j2 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int i6 = j.nextInt(j.nextInt(128 - 8) + 8);
            int j9 = l1 + j.nextInt(16) + 8;
            if(i6 < 64 || j.nextInt(10) == 0)
                (new WorldGenLakes(Block.STATIONARY_LAVA.id)).a(p, j, j2, i6, j9);
        }
        for(int k2 = 0; k2 < 8; k2++)
        {
            int j6 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int k9 = j.nextInt(128);
            int i14 = l1 + j.nextInt(16) + 8;
            (new WorldGenDungeons()).a(p, j, j6, k9, i14);
        }

        for(int i3 = 0; i3 < 10; i3++)
        {
            int k6 = k1 + j.nextInt(16);
            p.getClass();
            int l9 = j.nextInt(128);
            int j14 = l1 + j.nextInt(16);
            (new WorldGenClay(32)).a(p, j, k6, l9, j14);
        }

        for(int j3 = 0; j3 < 20; j3++)
        {
            int l6 = k1 + j.nextInt(16);
            p.getClass();
            int i10 = j.nextInt(128);
            int k14 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.DIRT.id, 32)).a(p, j, l6, i10, k14);
        }

        for(int k3 = 0; k3 < 10; k3++)
        {
            int i7 = k1 + j.nextInt(16);
            p.getClass();
            int j10 = j.nextInt(128);
            int l14 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.GRAVEL.id, 32)).a(p, j, i7, j10, l14);
        }

        for(int i4 = 0; i4 < 20; i4++)
        {
            int j7 = k1 + j.nextInt(16);
            p.getClass();
            int k10 = j.nextInt(128);
            int i15 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.COAL_ORE.id, 16)).a(p, j, j7, k10, i15);
        }

        for(int j4 = 0; j4 < 20; j4++)
        {
            int k7 = k1 + j.nextInt(16);
            p.getClass();
            int l10 = j.nextInt(128 / 2);
            int j15 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.IRON_ORE.id, 8)).a(p, j, k7, l10, j15);
        }

        for(int k4 = 0; k4 < 2; k4++)
        {
            int l7 = k1 + j.nextInt(16);
            p.getClass();
            int i11 = j.nextInt(128 / 4);
            int k15 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.GOLD_ORE.id, 8)).a(p, j, l7, i11, k15);
        }

        for(int l4 = 0; l4 < 8; l4++)
        {
            int i8 = k1 + j.nextInt(16);
            p.getClass();
            int j11 = j.nextInt(128 / 8);
            int l15 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.REDSTONE_ORE.id, 7)).a(p, j, i8, j11, l15);
        }

        for(int i5 = 0; i5 < 1; i5++)
        {
            int j8 = k1 + j.nextInt(16);
            p.getClass();
            int k11 = j.nextInt(128 / 8);
            int i16 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.DIAMOND_ORE.id, 7)).a(p, j, j8, k11, i16);
        }

        for(int j5 = 0; j5 < 1; j5++)
        {
            int k8 = k1 + j.nextInt(16);
            p.getClass();
            p.getClass();
            int l11 = j.nextInt(128 / 8) + j.nextInt(128 / 8);
            int j16 = l1 + j.nextInt(16);
            (new WorldGenMinable(Block.LAPIS_ORE.id, 6)).a(p, j, k8, l11, j16);
        }

        d1 = 0.5D;
        int k5 = (int)((c.a((double)k1 * d1, (double)l1 * d1) / 8D + j.nextDouble() * 4D + 4D) / 3D);
        int l8 = 0;
        if(j.nextInt(10) == 0)
            l8++;
        if(biomebase == BiomeBase.FOREST)
            l8 += k5 + 5;
        if(biomebase == BiomeBase.DESERT)
            l8 -= 20;
        if(biomebase == BiomeBase.PLAINS)
            l8 -= 20;
        for(int i12 = 0; i12 < l8; i12++)
        {
            int k16 = k1 + j.nextInt(16) + 8;
            int i19 = l1 + j.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomebase.a(j);
            worldgenerator.a(1.0D, 1.0D, 1.0D);
            worldgenerator.a(p, j, k16, p.getHighestBlockYAt(k16, i19), i19);
        }

        for(int j12 = 0; j12 < 2; j12++)
        {
            int l16 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int j19 = j.nextInt(128);
            int k21 = l1 + j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.YELLOW_FLOWER.id)).a(p, j, l16, j19, k21);
        }

        if(j.nextInt(2) == 0)
        {
            int k12 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int i17 = j.nextInt(128);
            int k19 = l1 + j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_ROSE.id)).a(p, j, k12, i17, k19);
        }
        if(j.nextInt(4) == 0)
        {
            int l12 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int j17 = j.nextInt(128);
            int l19 = l1 + j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(p, j, l12, j17, l19);
        }
        if(j.nextInt(8) == 0)
        {
            int i13 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int k17 = j.nextInt(128);
            int i20 = l1 + j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(p, j, i13, k17, i20);
        }
        for(int j13 = 0; j13 < 10; j13++)
        {
            int l17 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int j20 = j.nextInt(128);
            int l21 = l1 + j.nextInt(16) + 8;
            (new WorldGenReed()).a(p, j, l17, j20, l21);
        }

        if(j.nextInt(32) == 0)
        {
            int k13 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int i18 = j.nextInt(128);
            int k20 = l1 + j.nextInt(16) + 8;
            (new WorldGenPumpkin()).a(p, j, k13, i18, k20);
        }
        int l13 = 0;
        if(biomebase == BiomeBase.DESERT)
            l13 += 10;
        for(int j18 = 0; j18 < l13; j18++)
        {
            int l20 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int i22 = j.nextInt(128);
            int l22 = l1 + j.nextInt(16) + 8;
            (new WorldGenCactus()).a(p, j, l20, i22, l22);
        }

        for(int k18 = 0; k18 < 50; k18++)
        {
            int i21 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int j22 = j.nextInt(j.nextInt(128 - 8) + 8);
            int i23 = l1 + j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.WATER.id)).a(p, j, i21, j22, i23);
        }

        for(int l18 = 0; l18 < 20; l18++)
        {
            int j21 = k1 + j.nextInt(16) + 8;
            p.getClass();
            int k22 = j.nextInt(j.nextInt(j.nextInt(128 - 16) + 8) + 8);
            int j23 = l1 + j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.LAVA.id)).a(p, j, j21, k22, j23);
        }

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

    private Random j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGeneratorOctaves m;
    private NoiseGeneratorOctaves n;
    private NoiseGeneratorOctaves o;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World p;
    private double q[];
    private double r[];
    private double s[];
    private double t[];
    private MapGenBase u;
    private BiomeBase v[];
    double d[];
    double e[];
    double f[];
    double g[];
    double h[];
    int i[][];
}
