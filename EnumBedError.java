// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumBedError extends Enum
{

    public static EnumBedError[] values()
    {
        return (EnumBedError[])f.clone();
    }

    public static EnumBedError valueOf(String s)
    {
        return (EnumBedError)Enum.valueOf(net/minecraft/server/EnumBedError, s);
    }

    private EnumBedError(String s, int i)
    {
        super(s, i);
    }

    public static final EnumBedError OK;
    public static final EnumBedError NOT_POSSIBLE_HERE;
    public static final EnumBedError NOT_POSSIBLE_NOW;
    public static final EnumBedError TOO_FAR_AWAY;
    public static final EnumBedError OTHER_PROBLEM;
    private static final EnumBedError f[];

    static 
    {
        OK = new EnumBedError("OK", 0);
        NOT_POSSIBLE_HERE = new EnumBedError("NOT_POSSIBLE_HERE", 1);
        NOT_POSSIBLE_NOW = new EnumBedError("NOT_POSSIBLE_NOW", 2);
        TOO_FAR_AWAY = new EnumBedError("TOO_FAR_AWAY", 3);
        OTHER_PROBLEM = new EnumBedError("OTHER_PROBLEM", 4);
        f = (new EnumBedError[] {
            OK, NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW, TOO_FAR_AWAY, OTHER_PROBLEM
        });
    }
}
