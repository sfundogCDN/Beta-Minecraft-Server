// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityMonster, World

public class EntityGiantZombie extends EntityMonster
{

    public EntityGiantZombie(World world)
    {
        super(world);
        texture = "/mob/zombie.png";
        aU = 0.5F;
        damage = 50;
        health *= 10;
        height *= 6F;
        b(length * 6F, width * 6F);
    }

    protected float a(int i, int j, int k)
    {
        return world.m(i, j, k) - 0.5F;
    }
}
