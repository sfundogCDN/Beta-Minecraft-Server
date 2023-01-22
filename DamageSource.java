// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityDamageSource, EntityDamageSourceIndirect, EntityHuman, StatisticCollector, 
//            EntityLiving, EntityArrow, Entity, EntityFireball

public class DamageSource
{

    public static DamageSource mobAttack(EntityLiving entityliving)
    {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource playerAttack(EntityHuman entityhuman)
    {
        return new EntityDamageSource("player", entityhuman);
    }

    public static DamageSource arrow(EntityArrow entityarrow, Entity entity)
    {
        return new EntityDamageSourceIndirect("arrow", entityarrow, entity);
    }

    public static DamageSource fireball(EntityFireball entityfireball, Entity entity)
    {
        return new EntityDamageSourceIndirect("fireball", entityfireball, entity);
    }

    public static DamageSource projectile(Entity entity, Entity entity1)
    {
        return new EntityDamageSourceIndirect("thrown", entity, entity1);
    }

    public boolean ignoresArmor()
    {
        return n;
    }

    public float c()
    {
        return p;
    }

    public boolean ignoresInvulnerability()
    {
        return o;
    }

    protected DamageSource(String s)
    {
        n = false;
        o = false;
        p = 0.3F;
        m = s;
    }

    public Entity e()
    {
        return getEntity();
    }

    public Entity getEntity()
    {
        return null;
    }

    private DamageSource f()
    {
        n = true;
        p = 0.0F;
        return this;
    }

    private DamageSource g()
    {
        o = true;
        return this;
    }

    public String a(EntityHuman entityhuman)
    {
        return StatisticCollector.a((new StringBuilder()).append("death.").append(m).toString(), new Object[] {
            entityhuman.name
        });
    }

    public static DamageSource FIRE = new DamageSource("inFire");
    public static DamageSource BURN = (new DamageSource("onFire")).f();
    public static DamageSource LAVA = new DamageSource("lava");
    public static DamageSource STUCK = (new DamageSource("inWall")).f();
    public static DamageSource DROWN = (new DamageSource("drown")).f();
    public static DamageSource STARVE = (new DamageSource("starve")).f();
    public static DamageSource CACTUS = new DamageSource("cactus");
    public static DamageSource FALL = new DamageSource("fall");
    public static DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).f().g();
    public static DamageSource GENERIC = (new DamageSource("generic")).f();
    public static DamageSource EXPLOSION = new DamageSource("explosion");
    public static DamageSource MAGIC = (new DamageSource("magic")).f();
    private boolean n;
    private boolean o;
    private float p;
    public String m;

}
