// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityDamageSourceIndirect.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityDamageSource, Entity, EntityHuman, StatisticCollector

public class EntityDamageSourceIndirect extends EntityDamageSource
{

    public EntityDamageSourceIndirect(String s, Entity entity, Entity entity1)
    {
        super(s, entity);
        n = entity1;
    }

    public Entity getEntity()
    {
        return n;
    }

    public String a(EntityHuman entityhuman)
    {
        String source = n != null ? n.Y() : "Herobrine";
        return StatisticCollector.a((new StringBuilder()).append("death.").append(m).toString(), new Object[] {
            entityhuman.name, source
        });
    }

    private Entity n;
}
