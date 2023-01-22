// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public class StepSound
{

    public StepSound(String s, float f, float f1)
    {
        a = s;
        b = f;
        c = f1;
    }

    public float getVolume1()
    {
        return b;
    }

    public float getVolume2()
    {
        return c;
    }

    public String getName()
    {
        return (new StringBuilder()).append("step.").append(a).toString();
    }

    public final String a;
    public final float b;
    public final float c;
}
