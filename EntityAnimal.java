// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            EntityCreature, IAnimal, World, Block, 
//            BlockGrass, MathHelper, AxisAlignedBB, DamageSource, 
//            NBTTagCompound, EntityHuman

public abstract class EntityAnimal extends EntityCreature
    implements IAnimal
{

    public EntityAnimal(World world)
    {
        super(world);
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        f = 60;
        return super.damageEntity(damagesource, i);
    }

    protected float a(int i, int j, int k)
    {
        if(world.getTypeId(i, j - 1, k) == Block.GRASS.id)
            return 10F;
        else
            return world.m(i, j, k) - 0.5F;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    public boolean d()
    {
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(boundingBox.b);
        int k = MathHelper.floor(locZ);
        return world.getTypeId(i, j - 1, k) == Block.GRASS.id && world.k(i, j, k) > 8 && super.d();
    }

    public int e()
    {
        return 120;
    }

    protected boolean d_()
    {
        return false;
    }

    protected int a(EntityHuman entityhuman)
    {
        return 1 + world.random.nextInt(3);
    }
}
