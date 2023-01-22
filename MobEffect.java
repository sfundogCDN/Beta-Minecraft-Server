// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.PrintStream;

// Referenced classes of package net.minecraft.server:
//            MobEffectList, EntityLiving

public class MobEffect
{

    public MobEffect(int i, int j, int k)
    {
        effectId = i;
        duration = j;
        amplification = k;
    }

    public void a(MobEffect mobeffect)
    {
        if(effectId != mobeffect.effectId)
            System.err.println("This method should only be called for matching effects!");
        if(mobeffect.amplification >= amplification)
        {
            amplification = mobeffect.amplification;
            duration = mobeffect.duration;
        }
    }

    public int getEffectId()
    {
        return effectId;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getAmplifier()
    {
        return amplification;
    }

    public boolean tick(EntityLiving entityliving)
    {
        if(duration > 0)
        {
            if(MobEffectList.byId[effectId].a(duration, amplification))
                b(entityliving);
            d();
        }
        return duration > 0;
    }

    private int d()
    {
        return --duration;
    }

    public void b(EntityLiving entityliving)
    {
        if(duration > 0)
            MobEffectList.byId[effectId].tick(entityliving, amplification);
    }

    public int hashCode()
    {
        return effectId;
    }

    private int effectId;
    private int duration;
    private int amplification;
}
