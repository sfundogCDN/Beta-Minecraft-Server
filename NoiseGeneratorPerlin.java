// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            NoiseGenerator

public class NoiseGeneratorPerlin extends NoiseGenerator
{

    public NoiseGeneratorPerlin()
    {
        this(new Random());
    }

    public NoiseGeneratorPerlin(Random random)
    {
        d = new int[512];
        a = random.nextDouble() * 256D;
        b = random.nextDouble() * 256D;
        c = random.nextDouble() * 256D;
        for(int i = 0; i < 256; i++)
            d[i] = i;

        for(int j = 0; j < 256; j++)
        {
            int k = random.nextInt(256 - j) + j;
            int l = d[j];
            d[j] = d[k];
            d[k] = l;
            d[j + 256] = d[j];
        }

    }

    public double a(double d1, double d2, double d3)
    {
        double d4 = d1 + a;
        double d5 = d2 + b;
        double d6 = d3 + c;
        int i = (int)d4;
        int j = (int)d5;
        int k = (int)d6;
        if(d4 < (double)i)
            i--;
        if(d5 < (double)j)
            j--;
        if(d6 < (double)k)
            k--;
        int l = i & 0xff;
        int i1 = j & 0xff;
        int j1 = k & 0xff;
        d4 -= i;
        d5 -= j;
        d6 -= k;
        double d7 = d4 * d4 * d4 * (d4 * (d4 * 6D - 15D) + 10D);
        double d8 = d5 * d5 * d5 * (d5 * (d5 * 6D - 15D) + 10D);
        double d9 = d6 * d6 * d6 * (d6 * (d6 * 6D - 15D) + 10D);
        int k1 = d[l] + i1;
        int l1 = d[k1] + j1;
        int i2 = d[k1 + 1] + j1;
        int j2 = d[l + 1] + i1;
        int k2 = d[j2] + j1;
        int l2 = d[j2 + 1] + j1;
        return b(d9, b(d8, b(d7, a(d[l1], d4, d5, d6), a(d[k2], d4 - 1.0D, d5, d6)), b(d7, a(d[i2], d4, d5 - 1.0D, d6), a(d[l2], d4 - 1.0D, d5 - 1.0D, d6))), b(d8, b(d7, a(d[l1 + 1], d4, d5, d6 - 1.0D), a(d[k2 + 1], d4 - 1.0D, d5, d6 - 1.0D)), b(d7, a(d[i2 + 1], d4, d5 - 1.0D, d6 - 1.0D), a(d[l2 + 1], d4 - 1.0D, d5 - 1.0D, d6 - 1.0D))));
    }

    public final double b(double d1, double d2, double d3)
    {
        return d2 + d1 * (d3 - d2);
    }

    public final double a(int i, double d1, double d2)
    {
        int j = i & 0xf;
        double d3 = (double)(1 - ((j & 8) >> 3)) * d1;
        double d4 = j >= 4 ? j != 12 && j != 14 ? d2 : d1 : 0.0D;
        return ((j & 1) != 0 ? -d3 : d3) + ((j & 2) != 0 ? -d4 : d4);
    }

    public final double a(int i, double d1, double d2, double d3)
    {
        int j = i & 0xf;
        double d4 = j >= 8 ? d2 : d1;
        double d5 = j >= 4 ? j != 12 && j != 14 ? d3 : d1 : d2;
        return ((j & 1) != 0 ? -d4 : d4) + ((j & 2) != 0 ? -d5 : d5);
    }

    public double a(double d1, double d2)
    {
        return a(d1, d2, 0.0D);
    }

    public void a(double ad[], double d1, double d2, double d3, 
            int i, int j, int k, double d4, double d5, 
            double d6, double d7)
    {
        if(j == 1)
        {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            double d8 = 0.0D;
            double d10 = 0.0D;
            int j2 = 0;
            double d12 = 1.0D / d7;
            for(int l2 = 0; l2 < i; l2++)
            {
                double d13 = d1 + (double)l2 * d4 + a;
                int i3 = (int)d13;
                if(d13 < (double)i3)
                    i3--;
                int j3 = i3 & 0xff;
                d13 -= i3;
                double d15 = d13 * d13 * d13 * (d13 * (d13 * 6D - 15D) + 10D);
                for(int k3 = 0; k3 < k; k3++)
                {
                    double d17 = d3 + (double)k3 * d6 + c;
                    int i4 = (int)d17;
                    if(d17 < (double)i4)
                        i4--;
                    int k4 = i4 & 0xff;
                    d17 -= i4;
                    double d19 = d17 * d17 * d17 * (d17 * (d17 * 6D - 15D) + 10D);
                    int l = d[j3] + 0;
                    int j1 = d[l] + k4;
                    int k1 = d[j3 + 1] + 0;
                    int l1 = d[k1] + k4;
                    double d9 = b(d15, a(d[j1], d13, d17), a(d[l1], d13 - 1.0D, 0.0D, d17));
                    double d11 = b(d15, a(d[j1 + 1], d13, 0.0D, d17 - 1.0D), a(d[l1 + 1], d13 - 1.0D, 0.0D, d17 - 1.0D));
                    double d21 = b(d19, d9, d11);
                    ad[j2++] += d21 * d12;
                }

            }

            return;
        }
        int i1 = 0;
        double d22 = 1.0D / d7;
        int i2 = -1;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag4 = false;
        boolean flag9 = false;
        double d23 = 0.0D;
        double d14 = 0.0D;
        double d24 = 0.0D;
        double d16 = 0.0D;
        for(int l3 = 0; l3 < i; l3++)
        {
            double d18 = d1 + (double)l3 * d4 + a;
            int j4 = (int)d18;
            if(d18 < (double)j4)
                j4--;
            int l4 = j4 & 0xff;
            d18 -= j4;
            double d20 = d18 * d18 * d18 * (d18 * (d18 * 6D - 15D) + 10D);
            for(int j6 = 0; j6 < k; j6++)
            {
                double d25 = d3 + (double)j6 * d6 + c;
                int k6 = (int)d25;
                if(d25 < (double)k6)
                    k6--;
                int l6 = k6 & 0xff;
                d25 -= k6;
                double d26 = d25 * d25 * d25 * (d25 * (d25 * 6D - 15D) + 10D);
                for(int i7 = 0; i7 < j; i7++)
                {
                    double d27 = d2 + (double)i7 * d5 + b;
                    int j7 = (int)d27;
                    if(d27 < (double)j7)
                        j7--;
                    int k7 = j7 & 0xff;
                    d27 -= j7;
                    double d28 = d27 * d27 * d27 * (d27 * (d27 * 6D - 15D) + 10D);
                    if(i7 == 0 || k7 != i2)
                    {
                        i2 = k7;
                        int i5 = d[l4] + k7;
                        int j5 = d[i5] + l6;
                        int k5 = d[i5 + 1] + l6;
                        int l5 = d[l4 + 1] + k7;
                        int k2 = d[l5] + l6;
                        int i6 = d[l5 + 1] + l6;
                        d23 = b(d20, a(d[j5], d18, d27, d25), a(d[k2], d18 - 1.0D, d27, d25));
                        d14 = b(d20, a(d[k5], d18, d27 - 1.0D, d25), a(d[i6], d18 - 1.0D, d27 - 1.0D, d25));
                        d24 = b(d20, a(d[j5 + 1], d18, d27, d25 - 1.0D), a(d[k2 + 1], d18 - 1.0D, d27, d25 - 1.0D));
                        d16 = b(d20, a(d[k5 + 1], d18, d27 - 1.0D, d25 - 1.0D), a(d[i6 + 1], d18 - 1.0D, d27 - 1.0D, d25 - 1.0D));
                    }
                    double d29 = b(d28, d23, d14);
                    double d30 = b(d28, d24, d16);
                    double d31 = b(d26, d29, d30);
                    ad[i1++] += d31 * d22;
                }

            }

        }

    }

    private int d[];
    public double a;
    public double b;
    public double c;
}
