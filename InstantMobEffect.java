// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            MobEffectList

public class InstantMobEffect extends MobEffectList
{

    public InstantMobEffect(int i)
    {
        super(i);
    }

    public boolean a(int i, int j)
    {
        return i >= 1;
    }
}
