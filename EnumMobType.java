// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumMobType extends Enum
{

    public static EnumMobType[] values()
    {
        return (EnumMobType[])d.clone();
    }

    public static EnumMobType valueOf(String s)
    {
        return (EnumMobType)Enum.valueOf(net/minecraft/server/EnumMobType, s);
    }

    private EnumMobType(String s, int i)
    {
        super(s, i);
    }

    public static final EnumMobType EVERYTHING;
    public static final EnumMobType MOBS;
    public static final EnumMobType PLAYERS;
    private static final EnumMobType d[];

    static 
    {
        EVERYTHING = new EnumMobType("everything", 0);
        MOBS = new EnumMobType("mobs", 1);
        PLAYERS = new EnumMobType("players", 2);
        d = (new EnumMobType[] {
            EVERYTHING, MOBS, PLAYERS
        });
    }
}
