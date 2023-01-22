// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            EntityCreature, IAnimal, World, NBTTagCompound, 
//            EntityHuman

public abstract class EntityWaterAnimal extends EntityCreature
    implements IAnimal
{

    public EntityWaterAnimal(World world)
    {
        super(world);
    }

    public boolean b_()
    {
        return true;
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
        return world.containsEntity(boundingBox);
    }

    public int e()
    {
        return 120;
    }

    protected int a(EntityHuman entityhuman)
    {
        return 1 + world.random.nextInt(3);
    }
}
