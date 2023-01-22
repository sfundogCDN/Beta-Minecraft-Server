// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldGenBigTree.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.BlockChangeDelegate;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, MathHelper, World

public class WorldGenBigTree extends WorldGenerator
{

    public WorldGenBigTree()
    {
        b = new Random();
        e = 0;
        g = 0.61799999999999999D;
        h = 1.0D;
        i = 0.38100000000000001D;
        j = 1.0D;
        k = 1.0D;
        l = 1;
        m = 12;
        n = 4;
    }

    void a()
    {
        this.f = (int)((double)e * g);
        if(this.f >= e)
            this.f = e - 1;
        int i = (int)(1.3819999999999999D + Math.pow((this.k * (double)e) / 13D, 2D));
        if(i < 1)
            i = 1;
        int aint[][] = new int[i * e][4];
        int j = (d[1] + e) - n;
        int k = 1;
        int l = d[1] + this.f;
        int i1 = j - d[1];
        aint[0][0] = d[0];
        aint[0][1] = j;
        aint[0][2] = d[2];
        aint[0][3] = l;
        j--;
        while(i1 >= 0) 
        {
            int j1 = 0;
            float f = a(i1);
            if(f < 0.0F)
            {
                j--;
                i1--;
            } else
            {
                double d0 = 0.5D;
                for(; j1 < i; j1++)
                {
                    double d1 = this.j * (double)f * ((double)b.nextFloat() + 0.32800000000000001D);
                    double d2 = (double)b.nextFloat() * 2D * 3.1415899999999999D;
                    int k1 = MathHelper.floor(d1 * Math.sin(d2) + (double)d[0] + d0);
                    int l1 = MathHelper.floor(d1 * Math.cos(d2) + (double)d[2] + d0);
                    int aint1[] = {
                        k1, j, l1
                    };
                    int aint2[] = {
                        k1, j + n, l1
                    };
                    if(a(aint1, aint2) != -1)
                        continue;
                    int aint3[] = {
                        d[0], d[1], d[2]
                    };
                    double d3 = Math.sqrt(Math.pow(Math.abs(d[0] - aint1[0]), 2D) + Math.pow(Math.abs(d[2] - aint1[2]), 2D));
                    double d4 = d3 * this.i;
                    if((double)aint1[1] - d4 > (double)l)
                        aint3[1] = l;
                    else
                        aint3[1] = (int)((double)aint1[1] - d4);
                    if(a(aint3, aint1) == -1)
                    {
                        aint[k][0] = k1;
                        aint[k][1] = j;
                        aint[k][2] = l1;
                        aint[k][3] = aint3[1];
                        k++;
                    }
                }

                j--;
                i1--;
            }
        }
        o = new int[k][4];
        System.arraycopy(aint, 0, o, 0, k);
    }

    void a(int i, int j, int k, float f, byte b0, int l)
    {
        int i1 = (int)((double)f + 0.61799999999999999D);
        byte b1 = a[b0];
        byte b2 = a[b0 + 3];
        int aint[] = {
            i, j, k
        };
        int aint1[] = {
            0, 0, 0
        };
        int j1 = -i1;
        int k1 = -i1;
        aint1[b0] = aint[b0];
        for(; j1 <= i1; j1++)
        {
            aint1[b1] = aint[b1] + j1;
            for(k1 = -i1; k1 <= i1;)
            {
                double d0 = Math.sqrt(Math.pow((double)Math.abs(j1) + 0.5D, 2D) + Math.pow((double)Math.abs(k1) + 0.5D, 2D));
                if(d0 > (double)f)
                {
                    k1++;
                } else
                {
                    aint1[b2] = aint[b2] + k1;
                    int l1 = c.getTypeId(aint1[0], aint1[1], aint1[2]);
                    if(l1 != 0 && l1 != 18)
                    {
                        k1++;
                    } else
                    {
                        c.setRawTypeId(aint1[0], aint1[1], aint1[2], l);
                        k1++;
                    }
                }
            }

        }

    }

    float a(int i)
    {
        if((double)i < (double)(float)e * 0.29999999999999999D)
            return -1.618F;
        float f = (float)e / 2.0F;
        float f1 = (float)e / 2.0F - (float)i;
        float f2;
        if(f1 == 0.0F)
            f2 = f;
        else
        if(Math.abs(f1) >= f)
            f2 = 0.0F;
        else
            f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2D) - Math.pow(Math.abs(f1), 2D));
        f2 *= 0.5F;
        return f2;
    }

    float b(int i)
    {
        return i < 0 || i >= n ? -1F : i == 0 || i == n - 1 ? 2.0F : 3F;
    }

    void a(int i, int j, int k)
    {
        int l = j;
        for(int i1 = j + n; l < i1; l++)
        {
            float f = b(l - j);
            a(i, l, k, f, (byte)1, 18);
        }

    }

    void a(int aint[], int aint1[], int i)
    {
        int aint2[] = {
            0, 0, 0
        };
        byte b0 = 0;
        byte b1 = 0;
        for(; b0 < 3; b0++)
        {
            aint2[b0] = aint1[b0] - aint[b0];
            if(Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
                b1 = b0;
        }

        if(aint2[b1] != 0)
        {
            byte b2 = a[b1];
            byte b3 = a[b1 + 3];
            byte b4;
            if(aint2[b1] > 0)
                b4 = 1;
            else
                b4 = -1;
            double d0 = (double)aint2[b2] / (double)aint2[b1];
            double d1 = (double)aint2[b3] / (double)aint2[b1];
            int aint3[] = {
                0, 0, 0
            };
            int j = 0;
            for(int k = aint2[b1] + b4; j != k; j += b4)
            {
                aint3[b1] = MathHelper.floor((double)(aint[b1] + j) + 0.5D);
                aint3[b2] = MathHelper.floor((double)aint[b2] + (double)j * d0 + 0.5D);
                aint3[b3] = MathHelper.floor((double)aint[b3] + (double)j * d1 + 0.5D);
                c.setRawTypeId(aint3[0], aint3[1], aint3[2], i);
            }

        }
    }

    void b()
    {
        int i = 0;
        for(int j = o.length; i < j; i++)
        {
            int k = o[i][0];
            int l = o[i][1];
            int i1 = o[i][2];
            a(k, l, i1);
        }

    }

    boolean c(int i)
    {
        return (double)i >= (double)e * 0.20000000000000001D;
    }

    void c()
    {
        int i = d[0];
        int j = d[1];
        int k = d[1] + f;
        int l = d[2];
        int aint[] = {
            i, j, l
        };
        int aint1[] = {
            i, k, l
        };
        a(aint, aint1, 17);
        if(this.l == 2)
        {
            aint[0]++;
            aint1[0]++;
            a(aint, aint1, 17);
            aint[2]++;
            aint1[2]++;
            a(aint, aint1, 17);
            aint[0]--;
            aint1[0]--;
            a(aint, aint1, 17);
        }
    }

    void d()
    {
        int i = 0;
        int j = o.length;
        int aint[] = {
            d[0], d[1], d[2]
        };
        for(; i < j; i++)
        {
            int aint1[] = o[i];
            int aint2[] = {
                aint1[0], aint1[1], aint1[2]
            };
            aint[1] = aint1[3];
            int k = aint[1] - d[1];
            if(c(k))
                a(aint, aint2, 17);
        }

    }

    int a(int aint[], int aint1[])
    {
        int aint2[] = {
            0, 0, 0
        };
        byte b0 = 0;
        byte b1 = 0;
        for(; b0 < 3; b0++)
        {
            aint2[b0] = aint1[b0] - aint[b0];
            if(Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
                b1 = b0;
        }

        if(aint2[b1] == 0)
            return -1;
        byte b2 = a[b1];
        byte b3 = a[b1 + 3];
        byte b4;
        if(aint2[b1] > 0)
            b4 = 1;
        else
            b4 = -1;
        double d0 = (double)aint2[b2] / (double)aint2[b1];
        double d1 = (double)aint2[b3] / (double)aint2[b1];
        int aint3[] = {
            0, 0, 0
        };
        int i = 0;
        int j = aint2[b1] + b4;
        do
        {
            if(i == j)
                break;
            aint3[b1] = aint[b1] + i;
            aint3[b2] = MathHelper.floor((double)aint[b2] + (double)i * d0);
            aint3[b3] = MathHelper.floor((double)aint[b3] + (double)i * d1);
            int k = c.getTypeId(aint3[0], aint3[1], aint3[2]);
            if(k != 0 && k != 18)
                break;
            i += b4;
        } while(true);
        return i != j ? Math.abs(i) : -1;
    }

    boolean e()
    {
        int aint[] = {
            d[0], d[1], d[2]
        };
        int aint1[] = {
            d[0], (d[1] + e) - 1, d[2]
        };
        int i = c.getTypeId(d[0], d[1] - 1, d[2]);
        if(i != 2 && i != 3)
            return false;
        int j = a(aint, aint1);
        if(j == -1)
            return true;
        if(j < 6)
        {
            return false;
        } else
        {
            e = j;
            return true;
        }
    }

    public void a(double d0, double d1, double d2)
    {
        m = (int)(d0 * 12D);
        if(d0 > 0.5D)
            n = 5;
        j = d1;
        k = d2;
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        return generate((BlockChangeDelegate)world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
    {
        c = world;
        long l = random.nextLong();
        b.setSeed(l);
        d[0] = i;
        d[1] = j;
        d[2] = k;
        if(e == 0)
            e = 5 + b.nextInt(m);
        if(!e())
        {
            return false;
        } else
        {
            a();
            b();
            c();
            d();
            return true;
        }
    }

    static final byte a[] = {
        2, 0, 0, 1, 2, 1
    };
    Random b;
    BlockChangeDelegate c;
    int d[] = {
        0, 0, 0
    };
    int e;
    int f;
    double g;
    double h;
    double i;
    double j;
    double k;
    int l;
    int m;
    int n;
    int o[][];

}
