// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenClay, WorldGenSand, Block, WorldGenMinable, 
//            WorldGenFlowers, BlockFlower, WorldGenReed, WorldGenCactus, 
//            World, WorldGenerator, BiomeBase, WorldGenGrass, 
//            BlockLongGrass, WorldGenDeadBush, BlockDeadBush, WorldGenPumpkin, 
//            WorldGenLiquids

public class BiomeDecorator
{

    public BiomeDecorator(BiomeBase biomebase)
    {
        a = new WorldGenClay(4);
        b = new WorldGenSand(7, Block.SAND.id);
        c = new WorldGenSand(6, Block.GRAVEL.id);
        d = new WorldGenMinable(Block.DIRT.id, 32);
        e = new WorldGenMinable(Block.GRAVEL.id, 32);
        f = new WorldGenMinable(Block.COAL_ORE.id, 16);
        g = new WorldGenMinable(Block.IRON_ORE.id, 8);
        h = new WorldGenMinable(Block.GOLD_ORE.id, 8);
        i = new WorldGenMinable(Block.REDSTONE_ORE.id, 7);
        j = new WorldGenMinable(Block.DIAMOND_ORE.id, 7);
        k = new WorldGenMinable(Block.LAPIS_ORE.id, 6);
        l = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
        m = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
        n = new WorldGenFlowers(Block.BROWN_MUSHROOM.id);
        o = new WorldGenFlowers(Block.RED_MUSHROOM.id);
        p = new WorldGenReed();
        q = new WorldGenCactus();
        r = 0;
        s = 2;
        t = 1;
        u = 0;
        v = 0;
        w = 0;
        x = 0;
        y = 1;
        z = 3;
        A = 1;
        F = biomebase;
    }

    public void a(World world, Random random, int i1, int j1)
    {
        if(B != null)
        {
            throw new RuntimeException("Already decorating!!");
        } else
        {
            B = world;
            C = random;
            D = i1;
            E = j1;
            b();
            B = null;
            C = null;
            return;
        }
    }

    private void b()
    {
        a();
        for(int i1 = 0; i1 < z; i1++)
        {
            int i2 = D + C.nextInt(16) + 8;
            int i6 = E + C.nextInt(16) + 8;
            b.a(B, C, i2, B.f(i2, i6), i6);
        }

        for(int j1 = 0; j1 < A; j1++)
        {
            int j2 = D + C.nextInt(16) + 8;
            int j6 = E + C.nextInt(16) + 8;
            a.a(B, C, j2, B.f(j2, j6), j6);
        }

        for(int k1 = 0; k1 < y; k1++)
        {
            int k2 = D + C.nextInt(16) + 8;
            int k6 = E + C.nextInt(16) + 8;
            b.a(B, C, k2, B.f(k2, k6), k6);
        }

        int l1 = r;
        if(C.nextInt(10) == 0)
            l1++;
        for(int l2 = 0; l2 < l1; l2++)
        {
            int l6 = D + C.nextInt(16) + 8;
            int k10 = E + C.nextInt(16) + 8;
            WorldGenerator worldgenerator = F.a(C);
            worldgenerator.a(1.0D, 1.0D, 1.0D);
            worldgenerator.a(B, C, l6, B.getHighestBlockYAt(l6, k10), k10);
        }

        for(int i3 = 0; i3 < s; i3++)
        {
            int i7 = D + C.nextInt(16) + 8;
            B.getClass();
            int l10 = C.nextInt(128);
            int j14 = E + C.nextInt(16) + 8;
            l.a(B, C, i7, l10, j14);
            if(C.nextInt(4) == 0)
            {
                int j7 = D + C.nextInt(16) + 8;
                B.getClass();
                int i11 = C.nextInt(128);
                int k14 = E + C.nextInt(16) + 8;
                m.a(B, C, j7, i11, k14);
            }
        }

        for(int j3 = 0; j3 < t; j3++)
        {
            int k7 = 1;
            int j11 = D + C.nextInt(16) + 8;
            B.getClass();
            int l14 = C.nextInt(128);
            int i17 = E + C.nextInt(16) + 8;
            (new WorldGenGrass(Block.LONG_GRASS.id, k7)).a(B, C, j11, l14, i17);
        }

        for(int k3 = 0; k3 < u; k3++)
        {
            int l7 = D + C.nextInt(16) + 8;
            B.getClass();
            int k11 = C.nextInt(128);
            int i15 = E + C.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.DEAD_BUSH.id)).a(B, C, l7, k11, i15);
        }

        for(int l3 = 0; l3 < v; l3++)
        {
            if(C.nextInt(4) == 0)
            {
                int i8 = D + C.nextInt(16) + 8;
                int l11 = E + C.nextInt(16) + 8;
                int j15 = B.getHighestBlockYAt(i8, l11);
                n.a(B, C, i8, j15, l11);
            }
            if(C.nextInt(8) == 0)
            {
                int j8 = D + C.nextInt(16) + 8;
                int i12 = E + C.nextInt(16) + 8;
                B.getClass();
                int k15 = C.nextInt(128);
                o.a(B, C, j8, k15, i12);
            }
        }

        if(C.nextInt(4) == 0)
        {
            int i4 = D + C.nextInt(16) + 8;
            B.getClass();
            int k8 = C.nextInt(128);
            int j12 = E + C.nextInt(16) + 8;
            n.a(B, C, i4, k8, j12);
        }
        if(C.nextInt(8) == 0)
        {
            int j4 = D + C.nextInt(16) + 8;
            B.getClass();
            int l8 = C.nextInt(128);
            int k12 = E + C.nextInt(16) + 8;
            o.a(B, C, j4, l8, k12);
        }
        for(int k4 = 0; k4 < w; k4++)
        {
            int i9 = D + C.nextInt(16) + 8;
            int l12 = E + C.nextInt(16) + 8;
            B.getClass();
            int l15 = C.nextInt(128);
            p.a(B, C, i9, l15, l12);
        }

        for(int l4 = 0; l4 < 10; l4++)
        {
            int j9 = D + C.nextInt(16) + 8;
            B.getClass();
            int i13 = C.nextInt(128);
            int i16 = E + C.nextInt(16) + 8;
            p.a(B, C, j9, i13, i16);
        }

        if(C.nextInt(32) == 0)
        {
            int i5 = D + C.nextInt(16) + 8;
            B.getClass();
            int k9 = C.nextInt(128);
            int j13 = E + C.nextInt(16) + 8;
            (new WorldGenPumpkin()).a(B, C, i5, k9, j13);
        }
        for(int j5 = 0; j5 < x; j5++)
        {
            int l9 = D + C.nextInt(16) + 8;
            B.getClass();
            int k13 = C.nextInt(128);
            int j16 = E + C.nextInt(16) + 8;
            q.a(B, C, l9, k13, j16);
        }

        for(int k5 = 0; k5 < 50; k5++)
        {
            int i10 = D + C.nextInt(16) + 8;
            B.getClass();
            int l13 = C.nextInt(C.nextInt(128 - 8) + 8);
            int k16 = E + C.nextInt(16) + 8;
            (new WorldGenLiquids(Block.WATER.id)).a(B, C, i10, l13, k16);
        }

        for(int l5 = 0; l5 < 20; l5++)
        {
            int j10 = D + C.nextInt(16) + 8;
            B.getClass();
            int i14 = C.nextInt(C.nextInt(C.nextInt(128 - 16) + 8) + 8);
            int l16 = E + C.nextInt(16) + 8;
            (new WorldGenLiquids(Block.LAVA.id)).a(B, C, j10, i14, l16);
        }

    }

    protected void a(int i1, WorldGenerator worldgenerator, int j1, int k1)
    {
        for(int l1 = 0; l1 < i1; l1++)
        {
            int i2 = D + C.nextInt(16);
            int j2 = C.nextInt(k1 - j1) + j1;
            int k2 = E + C.nextInt(16);
            worldgenerator.a(B, C, i2, j2, k2);
        }

    }

    protected void b(int i1, WorldGenerator worldgenerator, int j1, int k1)
    {
        for(int l1 = 0; l1 < i1; l1++)
        {
            int i2 = D + C.nextInt(16);
            int j2 = C.nextInt(k1) + C.nextInt(k1) + (j1 - k1);
            int k2 = E + C.nextInt(16);
            worldgenerator.a(B, C, i2, j2, k2);
        }

    }

    protected void a()
    {
        B.getClass();
        a(20, d, 0, 128);
        B.getClass();
        a(10, e, 0, 128);
        B.getClass();
        a(20, f, 0, 128);
        B.getClass();
        a(20, g, 0, 128 / 2);
        B.getClass();
        a(2, h, 0, 128 / 4);
        B.getClass();
        a(8, i, 0, 128 / 8);
        B.getClass();
        a(1, j, 0, 128 / 8);
        B.getClass();
        B.getClass();
        b(1, k, 128 / 8, 128 / 8);
    }

    private World B;
    private Random C;
    private int D;
    private int E;
    private BiomeBase F;
    protected WorldGenerator a;
    protected WorldGenerator b;
    protected WorldGenerator c;
    protected WorldGenerator d;
    protected WorldGenerator e;
    protected WorldGenerator f;
    protected WorldGenerator g;
    protected WorldGenerator h;
    protected WorldGenerator i;
    protected WorldGenerator j;
    protected WorldGenerator k;
    protected WorldGenerator l;
    protected WorldGenerator m;
    protected WorldGenerator n;
    protected WorldGenerator o;
    protected WorldGenerator p;
    protected WorldGenerator q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;
    protected int A;
}
