// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumToolMaterial extends Enum
{

    public static EnumToolMaterial[] values()
    {
        return (EnumToolMaterial[])j.clone();
    }

    public static EnumToolMaterial valueOf(String s)
    {
        return (EnumToolMaterial)Enum.valueOf(net/minecraft/server/EnumToolMaterial, s);
    }

    private EnumToolMaterial(String s, int k, int l, int i1, float f1, int j1)
    {
        super(s, k);
        f = l;
        g = i1;
        h = f1;
        i = j1;
    }

    public int a()
    {
        return g;
    }

    public float b()
    {
        return h;
    }

    public int c()
    {
        return i;
    }

    public int d()
    {
        return f;
    }

    public static final EnumToolMaterial WOOD;
    public static final EnumToolMaterial STONE;
    public static final EnumToolMaterial IRON;
    public static final EnumToolMaterial DIAMOND;
    public static final EnumToolMaterial GOLD;
    private final int f;
    private final int g;
    private final float h;
    private final int i;
    private static final EnumToolMaterial j[];

    static 
    {
        WOOD = new EnumToolMaterial("WOOD", 0, 0, 59, 2.0F, 0);
        STONE = new EnumToolMaterial("STONE", 1, 1, 131, 4F, 1);
        IRON = new EnumToolMaterial("IRON", 2, 2, 250, 6F, 2);
        DIAMOND = new EnumToolMaterial("EMERALD", 3, 3, 1561, 8F, 3);
        GOLD = new EnumToolMaterial("GOLD", 4, 0, 32, 12F, 0);
        j = (new EnumToolMaterial[] {
            WOOD, STONE, IRON, DIAMOND, GOLD
        });
    }
}
