// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySilverfish.java

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            EntityMonster, EntityDamageSource, World, Entity, 
//            AxisAlignedBB, DamageSource, MathHelper, Block, 
//            PistonBlockTextures, BlockMonsterEggs, NBTTagCompound

public class EntitySilverfish extends EntityMonster
{

    public EntitySilverfish(World world)
    {
        super(world);
        texture = "/mob/silverfish.png";
        b(0.3F, 0.7F);
        aU = 0.6F;
        damage = 1;
    }

    protected boolean e_()
    {
        return false;
    }

    protected Entity findTarget()
    {
        double d0 = 8D;
        return world.findNearbyPlayer(this, d0);
    }

    protected String h()
    {
        return "mob.spider";
    }

    protected String i()
    {
        return "mob.spider";
    }

    protected String j()
    {
        return "mob.spiderdeath";
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(a <= 0 && (damagesource instanceof EntityDamageSource))
            a = 20;
        return super.damageEntity(damagesource, i);
    }

    protected void a(Entity entity, float f)
    {
        if(attackTicks <= 0 && f < 1.2F && entity.boundingBox.e > boundingBox.b && entity.boundingBox.b < boundingBox.e)
        {
            attackTicks = 20;
            entity.damageEntity(DamageSource.mobAttack(this), damage);
        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    protected int k()
    {
        return 0;
    }

    public void s_()
    {
        U = yaw;
        super.s_();
    }

    protected void c_()
    {
        super.c_();
        if(!world.isStatic)
        {
            if(a > 0)
            {
                a--;
                if(a == 0)
                {
                    int i = MathHelper.floor(locX);
                    int j = MathHelper.floor(locY);
                    int k = MathHelper.floor(locZ);
                    boolean flag = false;
                    for(int l = 0; !flag && l <= 5 && l >= -5; l = l > 0 ? 0 - l : 1 - l)
                    {
                        for(int i1 = 0; !flag && i1 <= 10 && i1 >= -10; i1 = i1 > 0 ? 0 - i1 : 1 - i1)
                        {
                            for(int j1 = 0; !flag && j1 <= 10 && j1 >= -10; j1 = j1 > 0 ? 0 - j1 : 1 - j1)
                            {
                                int k1 = world.getTypeId(i + i1, j + l, k + j1);
                                if(k1 != Block.MONSTER_EGGS.id)
                                    continue;
                                world.e(2001, i + i1, j + l, k + j1, Block.MONSTER_EGGS.id + world.getData(i + i1, j + l, k + j1) * 256);
                                world.setTypeId(i + i1, j + l, k + j1, 0);
                                Block.MONSTER_EGGS.postBreak(world, i + i1, j + l, k + j1, 0);
                                if(!random.nextBoolean())
                                    continue;
                                flag = true;
                                break;
                            }

                        }

                    }

                }
            }
            if(target == null && !B())
            {
                int i = MathHelper.floor(locX);
                int j = MathHelper.floor(locY + 0.5D);
                int k = MathHelper.floor(locZ);
                int l1 = random.nextInt(6);
                int l = world.getTypeId(i + PistonBlockTextures.b[l1], j + PistonBlockTextures.c[l1], k + PistonBlockTextures.d[l1]);
                if(BlockMonsterEggs.c(l))
                {
                    world.setTypeIdAndData(i + PistonBlockTextures.b[l1], j + PistonBlockTextures.c[l1], k + PistonBlockTextures.d[l1], Block.MONSTER_EGGS.id, BlockMonsterEggs.d(l));
                    ab();
                    die();
                } else
                {
                    A();
                }
            } else
            if(target != null && !B())
                target = null;
        }
    }

    protected float a(int i, int j, int k)
    {
        return world.getTypeId(i, j - 1, k) != Block.STONE.id ? super.a(i, j, k) : 10F;
    }

    private int a;
}
