// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            DamageSource, EntityHuman, Entity, StatisticCollector

public class EntityDamageSource extends DamageSource
{

    public EntityDamageSource(String s, Entity entity)
    {
        super(s);
        n = entity;
    }

    public Entity getEntity()
    {
        return n;
    }

    public String a(EntityHuman entityhuman)
    {
        return StatisticCollector.a((new StringBuilder()).append("death.").append(m).toString(), new Object[] {
            entityhuman.name, n.Y()
        });
    }

    private Entity n;
}
