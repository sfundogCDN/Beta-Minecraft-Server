// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class MaterialMapColor
{

    private MaterialMapColor(int i1, int j1)
    {
        q = i1;
        p = j1;
        a[i1] = this;
    }

    public static final MaterialMapColor a[] = new MaterialMapColor[16];
    public static final MaterialMapColor b = new MaterialMapColor(0, 0);
    public static final MaterialMapColor c = new MaterialMapColor(1, 0x7fb238);
    public static final MaterialMapColor d = new MaterialMapColor(2, 0xf7e9a3);
    public static final MaterialMapColor e = new MaterialMapColor(3, 0xa7a7a7);
    public static final MaterialMapColor f = new MaterialMapColor(4, 0xff0000);
    public static final MaterialMapColor g = new MaterialMapColor(5, 0xa0a0ff);
    public static final MaterialMapColor h = new MaterialMapColor(6, 0xa7a7a7);
    public static final MaterialMapColor i = new MaterialMapColor(7, 31744);
    public static final MaterialMapColor j = new MaterialMapColor(8, 0xffffff);
    public static final MaterialMapColor k = new MaterialMapColor(9, 0xa4a8b8);
    public static final MaterialMapColor l = new MaterialMapColor(10, 0xb76a2f);
    public static final MaterialMapColor m = new MaterialMapColor(11, 0x707070);
    public static final MaterialMapColor n = new MaterialMapColor(12, 0x4040ff);
    public static final MaterialMapColor o = new MaterialMapColor(13, 0x685332);
    public final int p;
    public final int q;

}
