// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumAnimation extends Enum
{

    public static EnumAnimation[] values()
    {
        return (EnumAnimation[])e.clone();
    }

    public static EnumAnimation valueOf(String s)
    {
        return (EnumAnimation)Enum.valueOf(net/minecraft/server/EnumAnimation, s);
    }

    private EnumAnimation(String s, int i)
    {
        super(s, i);
    }

    public static final EnumAnimation a;
    public static final EnumAnimation b;
    public static final EnumAnimation c;
    public static final EnumAnimation d;
    private static final EnumAnimation e[];

    static 
    {
        a = new EnumAnimation("none", 0);
        b = new EnumAnimation("eat", 1);
        c = new EnumAnimation("block", 2);
        d = new EnumAnimation("bow", 3);
        e = (new EnumAnimation[] {
            a, b, c, d
        });
    }
}
