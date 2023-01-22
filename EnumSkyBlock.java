// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumSkyBlock extends Enum
{

    public static EnumSkyBlock[] values()
    {
        return (EnumSkyBlock[])d.clone();
    }

    public static EnumSkyBlock valueOf(String s)
    {
        return (EnumSkyBlock)Enum.valueOf(net/minecraft/server/EnumSkyBlock, s);
    }

    private EnumSkyBlock(String s, int i, int j)
    {
        super(s, i);
        c = j;
    }

    public static final EnumSkyBlock SKY;
    public static final EnumSkyBlock BLOCK;
    public final int c;
    private static final EnumSkyBlock d[];

    static 
    {
        SKY = new EnumSkyBlock("Sky", 0, 15);
        BLOCK = new EnumSkyBlock("Block", 1, 0);
        d = (new EnumSkyBlock[] {
            SKY, BLOCK
        });
    }
}
