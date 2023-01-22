// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class WorldGenStrongholdDoorType extends Enum
{

    public static WorldGenStrongholdDoorType[] values()
    {
        return (WorldGenStrongholdDoorType[])e.clone();
    }

    public static WorldGenStrongholdDoorType valueOf(String s)
    {
        return (WorldGenStrongholdDoorType)Enum.valueOf(net/minecraft/server/WorldGenStrongholdDoorType, s);
    }

    private WorldGenStrongholdDoorType(String s, int i)
    {
        super(s, i);
    }

    public static final WorldGenStrongholdDoorType a;
    public static final WorldGenStrongholdDoorType b;
    public static final WorldGenStrongholdDoorType c;
    public static final WorldGenStrongholdDoorType d;
    private static final WorldGenStrongholdDoorType e[];

    static 
    {
        a = new WorldGenStrongholdDoorType("OPENING", 0);
        b = new WorldGenStrongholdDoorType("WOOD_DOOR", 1);
        c = new WorldGenStrongholdDoorType("GRATES", 2);
        d = new WorldGenStrongholdDoorType("IRON_DOOR", 3);
        e = (new WorldGenStrongholdDoorType[] {
            a, b, c, d
        });
    }
}
