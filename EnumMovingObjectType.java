// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumMovingObjectType extends Enum
{

    public static EnumMovingObjectType[] values()
    {
        return (EnumMovingObjectType[])c.clone();
    }

    public static EnumMovingObjectType valueOf(String s)
    {
        return (EnumMovingObjectType)Enum.valueOf(net/minecraft/server/EnumMovingObjectType, s);
    }

    private EnumMovingObjectType(String s, int i)
    {
        super(s, i);
    }

    public static final EnumMovingObjectType TILE;
    public static final EnumMovingObjectType ENTITY;
    private static final EnumMovingObjectType c[];

    static 
    {
        TILE = new EnumMovingObjectType("TILE", 0);
        ENTITY = new EnumMovingObjectType("ENTITY", 1);
        c = (new EnumMovingObjectType[] {
            TILE, ENTITY
        });
    }
}
