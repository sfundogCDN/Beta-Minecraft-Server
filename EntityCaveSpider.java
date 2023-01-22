// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntitySpider, EntityLiving, World, MobEffect, 
//            MobEffectList, Entity

public class EntityCaveSpider extends EntitySpider
{

    public EntityCaveSpider(World world)
    {
        super(world);
        texture = "/mob/cavespider.png";
        b(0.7F, 0.5F);
    }

    protected boolean c(Entity entity)
    {
        if(super.c(entity))
        {
            if(entity instanceof EntityLiving)
            {
                byte byte0 = 0;
                if(world.difficulty > 1)
                    if(world.difficulty == 2)
                        byte0 = 7;
                    else
                    if(world.difficulty == 3)
                        byte0 = 15;
                if(byte0 > 0)
                    ((EntityLiving)entity).addEffect(new MobEffect(MobEffectList.POISON.id, byte0 * 20, 0));
            }
            return true;
        } else
        {
            return false;
        }
    }
}
