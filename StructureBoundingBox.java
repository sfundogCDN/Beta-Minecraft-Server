// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class StructureBoundingBox
{

    public StructureBoundingBox()
    {
    }

    public static StructureBoundingBox a()
    {
        return new StructureBoundingBox(0x7fffffff, 0x7fffffff, 0x7fffffff, 0x80000000, 0x80000000, 0x80000000);
    }

    public static StructureBoundingBox a(int i, int j, int k, int l, int i1, int j1, int k1, int l1, 
            int i2, int j2)
    {
        switch(j2)
        {
        default:
            return new StructureBoundingBox(i + l, j + i1, k + j1, ((i + k1) - 1) + l, ((j + l1) - 1) + i1, ((k + i2) - 1) + j1);

        case 2: // '\002'
            return new StructureBoundingBox(i + l, j + i1, (k - i2) + 1 + j1, ((i + k1) - 1) + l, ((j + l1) - 1) + i1, k + j1);

        case 0: // '\0'
            return new StructureBoundingBox(i + l, j + i1, k + j1, ((i + k1) - 1) + l, ((j + l1) - 1) + i1, ((k + i2) - 1) + j1);

        case 1: // '\001'
            return new StructureBoundingBox((i - i2) + 1 + j1, j + i1, k + l, i + j1, ((j + l1) - 1) + i1, ((k + k1) - 1) + l);

        case 3: // '\003'
            return new StructureBoundingBox(i + j1, j + i1, k + l, ((i + i2) - 1) + j1, ((j + l1) - 1) + i1, ((k + k1) - 1) + l);
        }
    }

    public StructureBoundingBox(StructureBoundingBox structureboundingbox)
    {
        a = structureboundingbox.a;
        b = structureboundingbox.b;
        c = structureboundingbox.c;
        d = structureboundingbox.d;
        e = structureboundingbox.e;
        f = structureboundingbox.f;
    }

    public StructureBoundingBox(int i, int j, int k, int l, int i1, int j1)
    {
        a = i;
        b = j;
        c = k;
        d = l;
        e = i1;
        f = j1;
    }

    public StructureBoundingBox(int i, int j, int k, int l)
    {
        a = i;
        b = 0;
        c = j;
        d = k;
        e = 0x10000;
        f = l;
    }

    public boolean a(StructureBoundingBox structureboundingbox)
    {
        return d >= structureboundingbox.a && a <= structureboundingbox.d && f >= structureboundingbox.c && c <= structureboundingbox.f && e >= structureboundingbox.b && b <= structureboundingbox.e;
    }

    public boolean a(int i, int j, int k, int l)
    {
        return d >= i && a <= k && f >= j && c <= l;
    }

    public void b(StructureBoundingBox structureboundingbox)
    {
        a = Math.min(a, structureboundingbox.a);
        b = Math.min(b, structureboundingbox.b);
        c = Math.min(c, structureboundingbox.c);
        d = Math.max(d, structureboundingbox.d);
        e = Math.max(e, structureboundingbox.e);
        f = Math.max(f, structureboundingbox.f);
    }

    public void a(int i, int j, int k)
    {
        a += i;
        b += j;
        c += k;
        d += i;
        e += j;
        f += k;
    }

    public boolean b(int i, int j, int k)
    {
        return i >= a && i <= d && k >= c && k <= f && j >= b && j <= e;
    }

    public int b()
    {
        return (d - a) + 1;
    }

    public int c()
    {
        return (e - b) + 1;
    }

    public int d()
    {
        return (f - c) + 1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("(").append(a).append(", ").append(b).append(", ").append(c).append("; ").append(d).append(", ").append(e).append(", ").append(f).append(")").toString();
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
}
