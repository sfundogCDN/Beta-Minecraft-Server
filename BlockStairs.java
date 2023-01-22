// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockStairs.java

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, World, EntityLiving, MathHelper, 
//            IBlockAccess, AxisAlignedBB, EntityHuman, Entity, 
//            Vec3D

public class BlockStairs extends Block
{

    protected BlockStairs(int i, Block block)
    {
        super(i, block.textureId, block.material);
        a = block;
        c(block.strength);
        b(block.durability / 3F);
        a(block.stepSound);
        f(255);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return super.e(world, i, j, k);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        int l = world.getData(i, j, k);
        if(l == 0)
        {
            a(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(l == 1)
        {
            a(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(l == 2)
        {
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(l == 3)
        {
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        }
        a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        a.b(world, i, j, k, entityhuman);
    }

    public void postBreak(World world, int i, int j, int k, int l)
    {
        a.postBreak(world, i, j, k, l);
    }

    public float a(Entity entity)
    {
        return a.a(entity);
    }

    public int a(int i, Random random)
    {
        return a.a(i, random);
    }

    public int a(Random random)
    {
        return a.a(random);
    }

    public int a(int i, int j)
    {
        return a.a(i, 0);
    }

    public int a(int i)
    {
        return a.a(i, 0);
    }

    public int c()
    {
        return a.c();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d)
    {
        a.a(world, i, j, k, entity, vec3d);
    }

    public boolean q_()
    {
        return a.q_();
    }

    public boolean a(int i, boolean flag)
    {
        return a.a(i, flag);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return a.canPlace(world, i, j, k);
    }

    public void a(World world, int i, int j, int k)
    {
        doPhysics(world, i, j, k, 0);
        a.a(world, i, j, k);
    }

    public void remove(World world, int i, int j, int k)
    {
        a.remove(world, i, j, k);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f)
    {
        a.dropNaturally(world, i, j, k, 0, f);
    }

    public void b(World world, int i, int j, int k, Entity entity)
    {
        a.b(world, i, j, k, entity);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        a.a(world, i, j, k, random);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        return a.interact(world, i, j, k, entityhuman);
    }

    public void a_(World world, int i, int j, int k)
    {
        a.a_(world, i, j, k);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
            world.setData(i, j, k, 2);
        if(l == 1)
            world.setData(i, j, k, 1);
        if(l == 2)
            world.setData(i, j, k, 3);
        if(l == 3)
            world.setData(i, j, k, 0);
    }

    private Block a;
}
