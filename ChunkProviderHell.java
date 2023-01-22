// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            IChunkProvider, MapGenCavesHell, NoiseGeneratorOctaves, Block, 
//            MapGenBase, Chunk, BlockSand, WorldGenHellLava, 
//            WorldGenFire, WorldGenLightStone2, WorldGenLightStone1, WorldGenFlowers, 
//            BlockFlower, World, IProgressUpdate

public class ChunkProviderHell
    implements IChunkProvider
{

    public ChunkProviderHell(World world, long l1)
    {
        p = new double[256];
        q = new double[256];
        r = new double[256];
        s = new MapGenCavesHell();
        n = world;
        h = new Random(l1);
        i = new NoiseGeneratorOctaves(h, 16);
        j = new NoiseGeneratorOctaves(h, 16);
        k = new NoiseGeneratorOctaves(h, 8);
        l = new NoiseGeneratorOctaves(h, 4);
        m = new NoiseGeneratorOctaves(h, 4);
        a = new NoiseGeneratorOctaves(h, 10);
        b = new NoiseGeneratorOctaves(h, 16);
    }

    public void a(int i1, int j1, byte abyte0[])
    {
        byte byte0 = 4;
        byte byte1 = 32;
        int k1 = byte0 + 1;
        n.getClass();
        int l1 = 128 / 8 + 1;
        int i2 = byte0 + 1;
        o = a(o, i1 * byte0, 0, j1 * byte0, k1, l1, i2);
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
                        n.getClass();
                        if(l2 >= 128 / 8)
                            break label1;
                        double d1 = 0.125D;
                        double d2 = o[((j2 + 0) * i2 + (k2 + 0)) * l1 + (l2 + 0)];
                        double d3 = o[((j2 + 0) * i2 + (k2 + 1)) * l1 + (l2 + 0)];
                        double d4 = o[((j2 + 1) * i2 + (k2 + 0)) * l1 + (l2 + 0)];
                        double d5 = o[((j2 + 1) * i2 + (k2 + 1)) * l1 + (l2 + 0)];
                        double d6 = (o[((j2 + 0) * i2 + (k2 + 0)) * l1 + (l2 + 1)] - d2) * d1;
                        double d7 = (o[((j2 + 0) * i2 + (k2 + 1)) * l1 + (l2 + 1)] - d3) * d1;
                        double d8 = (o[((j2 + 1) * i2 + (k2 + 0)) * l1 + (l2 + 1)] - d4) * d1;
                        double d9 = (o[((j2 + 1) * i2 + (k2 + 1)) * l1 + (l2 + 1)] - d5) * d1;
                        for(int i3 = 0; i3 < 8; i3++)
                        {
                            double d10 = 0.25D;
                            double d11 = d2;
                            double d12 = d3;
                            double d13 = (d4 - d2) * d10;
                            double d14 = (d5 - d3) * d10;
                            for(int j3 = 0; j3 < 4; j3++)
                            {
                                n.getClass();
                                n.getClass();
                                int k3 = j3 + j2 * 4 << 11 | 0 + k2 * 4 << 7 | l2 * 8 + i3;
                                n.getClass();
                                int l3 = 1 << 7;
                                double d15 = 0.25D;
                                double d16 = d11;
                                double d17 = (d12 - d11) * d15;
                                for(int i4 = 0; i4 < 4; i4++)
                                {
                                    int j4 = 0;
                                    if(l2 * 8 + i3 < byte1)
                                        j4 = Block.STATIONARY_LAVA.id;
                                    if(d16 > 0.0D)
                                        j4 = Block.NETHERRACK.id;
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

    public void b(int i1, int j1, byte abyte0[])
    {
        n.getClass();
        int k1 = 128 - 64;
        double d1 = 0.03125D;
        p = l.a(p, i1 * 16, j1 * 16, 0, 16, 16, 1, d1, d1, 1.0D);
        q = l.a(q, i1 * 16, 109, j1 * 16, 16, 1, 16, d1, 1.0D, d1);
        r = m.a(r, i1 * 16, j1 * 16, 0, 16, 16, 1, d1 * 2D, d1 * 2D, d1 * 2D);
        for(int l1 = 0; l1 < 16; l1++)
        {
            for(int i2 = 0; i2 < 16; i2++)
            {
                boolean flag = p[l1 + i2 * 16] + h.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = q[l1 + i2 * 16] + h.nextDouble() * 0.20000000000000001D > 0.0D;
                int j2 = (int)(r[l1 + i2 * 16] / 3D + 3D + h.nextDouble() * 0.25D);
                int k2 = -1;
                byte byte0 = (byte)Block.NETHERRACK.id;
                byte byte1 = (byte)Block.NETHERRACK.id;
                n.getClass();
                for(int l2 = 127; l2 >= 0; l2--)
                {
                    n.getClass();
                    int i3 = (i2 * 16 + l1) * 128 + l2;
                    n.getClass();
                    if(l2 >= 127 - h.nextInt(5))
                    {
                        abyte0[i3] = (byte)Block.BEDROCK.id;
                        continue;
                    }
                    if(l2 <= 0 + h.nextInt(5))
                    {
                        abyte0[i3] = (byte)Block.BEDROCK.id;
                        continue;
                    }
                    byte byte2 = abyte0[i3];
                    if(byte2 == 0)
                    {
                        k2 = -1;
                        continue;
                    }
                    if(byte2 != Block.NETHERRACK.id)
                        continue;
                    if(k2 == -1)
                    {
                        if(j2 <= 0)
                        {
                            byte0 = 0;
                            byte1 = (byte)Block.NETHERRACK.id;
                        } else
                        if(l2 >= k1 - 4 && l2 <= k1 + 1)
                        {
                            byte0 = (byte)Block.NETHERRACK.id;
                            byte1 = (byte)Block.NETHERRACK.id;
                            if(flag1)
                                byte0 = (byte)Block.GRAVEL.id;
                            if(flag1)
                                byte1 = (byte)Block.NETHERRACK.id;
                            if(flag)
                                byte0 = (byte)Block.SOUL_SAND.id;
                            if(flag)
                                byte1 = (byte)Block.SOUL_SAND.id;
                        }
                        if(l2 < k1 && byte0 == 0)
                            byte0 = (byte)Block.STATIONARY_LAVA.id;
                        k2 = j2;
                        if(l2 >= k1 - 1)
                            abyte0[i3] = byte0;
                        else
                            abyte0[i3] = byte1;
                        continue;
                    }
                    if(k2 > 0)
                    {
                        k2--;
                        abyte0[i3] = byte1;
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
        h.setSeed((long)i1 * 0x4f9939f508L + (long)j1 * 0x1ef1565bd5L);
        n.getClass();
        byte abyte0[] = new byte[16 * 128 * 16];
        a(i1, j1, abyte0);
        b(i1, j1, abyte0);
        s.a(this, n, i1, j1, abyte0);
        Chunk chunk = new Chunk(n, abyte0, i1, j1);
        return chunk;
    }

    private double[] a(double ad[], int i1, int j1, int k1, int l1, int i2, int j2)
    {
        if(ad == null)
            ad = new double[l1 * i2 * j2];
        double d1 = 684.41200000000003D;
        double d2 = 2053.2359999999999D;
        f = a.a(f, i1, j1, k1, l1, 1, j2, 1.0D, 0.0D, 1.0D);
        g = b.a(g, i1, j1, k1, l1, 1, j2, 100D, 0.0D, 100D);
        c = k.a(c, i1, j1, k1, l1, i2, j2, d1 / 80D, d2 / 60D, d1 / 80D);
        d = i.a(d, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        e = j.a(e, i1, j1, k1, l1, i2, j2, d1, d2, d1);
        int k2 = 0;
        int l2 = 0;
        double ad1[] = new double[i2];
        for(int i3 = 0; i3 < i2; i3++)
        {
            ad1[i3] = Math.cos(((double)i3 * 3.1415926535897931D * 6D) / (double)i2) * 2D;
            double d3 = i3;
            if(i3 > i2 / 2)
                d3 = i2 - 1 - i3;
            if(d3 < 4D)
            {
                d3 = 4D - d3;
                ad1[i3] -= d3 * d3 * d3 * 10D;
            }
        }

        for(int j3 = 0; j3 < l1; j3++)
        {
            for(int k3 = 0; k3 < j2; k3++)
            {
                double d4 = (f[l2] + 256D) / 512D;
                if(d4 > 1.0D)
                    d4 = 1.0D;
                double d5 = 0.0D;
                double d6 = g[l2] / 8000D;
                if(d6 < 0.0D)
                    d6 = -d6;
                d6 = d6 * 3D - 3D;
                if(d6 < 0.0D)
                {
                    d6 /= 2D;
                    if(d6 < -1D)
                        d6 = -1D;
                    d6 /= 1.3999999999999999D;
                    d6 /= 2D;
                    d4 = 0.0D;
                } else
                {
                    if(d6 > 1.0D)
                        d6 = 1.0D;
                    d6 /= 6D;
                }
                d4 += 0.5D;
                d6 = (d6 * (double)i2) / 16D;
                l2++;
                for(int l3 = 0; l3 < i2; l3++)
                {
                    double d7 = 0.0D;
                    double d8 = ad1[l3];
                    double d9 = d[k2] / 512D;
                    double d10 = e[k2] / 512D;
                    double d11 = (c[k2] / 10D + 1.0D) / 2D;
                    if(d11 < 0.0D)
                        d7 = d9;
                    else
                    if(d11 > 1.0D)
                        d7 = d10;
                    else
                        d7 = d9 + (d10 - d9) * d11;
                    d7 -= d8;
                    if(l3 > i2 - 4)
                    {
                        double d12 = (float)(l3 - (i2 - 4)) / 3F;
                        d7 = d7 * (1.0D - d12) + -10D * d12;
                    }
                    if((double)l3 < d5)
                    {
                        double d13 = (d5 - (double)l3) / 4D;
                        if(d13 < 0.0D)
                            d13 = 0.0D;
                        if(d13 > 1.0D)
                            d13 = 1.0D;
                        d7 = d7 * (1.0D - d13) + -10D * d13;
                    }
                    ad[k2] = d7;
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
        for(int i2 = 0; i2 < 8; i2++)
        {
            int k2 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int i4 = h.nextInt(128 - 8) + 4;
            int k5 = l1 + h.nextInt(16) + 8;
            (new WorldGenHellLava(Block.LAVA.id)).a(n, h, k2, i4, k5);
        }

        int j2 = h.nextInt(h.nextInt(10) + 1) + 1;
        for(int l2 = 0; l2 < j2; l2++)
        {
            int j4 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int l5 = h.nextInt(128 - 8) + 4;
            int i7 = l1 + h.nextInt(16) + 8;
            (new WorldGenFire()).a(n, h, j4, l5, i7);
        }

        j2 = h.nextInt(h.nextInt(10) + 1);
        for(int i3 = 0; i3 < j2; i3++)
        {
            int k4 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int i6 = h.nextInt(128 - 8) + 4;
            int j7 = l1 + h.nextInt(16) + 8;
            (new WorldGenLightStone2()).a(n, h, k4, i6, j7);
        }

        for(int j3 = 0; j3 < 10; j3++)
        {
            int l4 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int j6 = h.nextInt(128);
            int k7 = l1 + h.nextInt(16) + 8;
            (new WorldGenLightStone1()).a(n, h, l4, j6, k7);
        }

        if(h.nextInt(1) == 0)
        {
            int k3 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int i5 = h.nextInt(128);
            int k6 = l1 + h.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(n, h, k3, i5, k6);
        }
        if(h.nextInt(1) == 0)
        {
            int l3 = k1 + h.nextInt(16) + 8;
            n.getClass();
            int j5 = h.nextInt(128);
            int l6 = l1 + h.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(n, h, l3, j5, l6);
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

    private Random h;
    private NoiseGeneratorOctaves i;
    private NoiseGeneratorOctaves j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGeneratorOctaves m;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    private World n;
    private double o[];
    private double p[];
    private double q[];
    private double r[];
    private MapGenBase s;
    double c[];
    double d[];
    double e[];
    double f[];
    double g[];
}
