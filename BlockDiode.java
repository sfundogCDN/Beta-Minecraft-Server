// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, IBlockAccess, 
//            EntityLiving, MathHelper, Item, EntityHuman

public class BlockDiode extends Block
{

    protected BlockDiode(int i, boolean flag)
    {
        super(i, 6, Material.ORIENTABLE);
        c = flag;
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean b()
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        if(!world.e(i, j - 1, k))
            return false;
        else
            return super.canPlace(world, i, j, k);
    }

    public boolean f(World world, int i, int j, int k)
    {
        if(!world.e(i, j - 1, k))
            return false;
        else
            return super.f(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        int l = world.getData(i, j, k);
        boolean flag = f(world, i, j, k, l);
        if(c && !flag)
            world.setTypeIdAndData(i, j, k, Block.DIODE_OFF.id, l);
        else
        if(!c)
        {
            world.setTypeIdAndData(i, j, k, Block.DIODE_ON.id, l);
            if(!flag)
            {
                int i1 = (l & 0xc) >> 2;
                world.c(i, j, k, Block.DIODE_ON.id, b[i1] * 2);
            }
        }
    }

    public int a(int i, int j)
    {
        if(i == 0)
            return !c ? 115 : 99;
        if(i == 1)
            return !c ? 131 : 147;
        else
            return 5;
    }

    public int a(int i)
    {
        return a(i, 0);
    }

    public boolean d(World world, int i, int j, int k, int l)
    {
        return a(world, i, j, k, l);
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!c)
            return false;
        int i1 = iblockaccess.getData(i, j, k) & 3;
        if(i1 == 0 && l == 3)
            return true;
        if(i1 == 1 && l == 4)
            return true;
        if(i1 == 2 && l == 2)
            return true;
        return i1 == 3 && l == 5;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!f(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
            return;
        }
        int i1 = world.getData(i, j, k);
        boolean flag = f(world, i, j, k, i1);
        int j1 = (i1 & 0xc) >> 2;
        if(c && !flag)
            world.c(i, j, k, id, b[j1] * 2);
        else
        if(!c && flag)
            world.c(i, j, k, id, b[j1] * 2);
    }

    private boolean f(World world, int i, int j, int k, int l)
    {
        int i1 = l & 3;
        switch(i1)
        {
        case 0: // '\0'
            return world.isBlockFaceIndirectlyPowered(i, j, k + 1, 3) || world.getTypeId(i, j, k + 1) == Block.REDSTONE_WIRE.id && world.getData(i, j, k + 1) > 0;

        case 2: // '\002'
            return world.isBlockFaceIndirectlyPowered(i, j, k - 1, 2) || world.getTypeId(i, j, k - 1) == Block.REDSTONE_WIRE.id && world.getData(i, j, k - 1) > 0;

        case 3: // '\003'
            return world.isBlockFaceIndirectlyPowered(i + 1, j, k, 5) || world.getTypeId(i + 1, j, k) == Block.REDSTONE_WIRE.id && world.getData(i + 1, j, k) > 0;

        case 1: // '\001'
            return world.isBlockFaceIndirectlyPowered(i - 1, j, k, 4) || world.getTypeId(i - 1, j, k) == Block.REDSTONE_WIRE.id && world.getData(i - 1, j, k) > 0;
        }
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        int l = world.getData(i, j, k);
        int i1 = (l & 0xc) >> 2;
        i1 = i1 + 1 << 2 & 0xc;
        world.setData(i, j, k, i1 | l & 3);
        return true;
    }

    public boolean isPowerSource()
    {
        return false;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = ((MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 0.5D) & 3) + 2) % 4;
        world.setData(i, j, k, l);
        boolean flag = f(world, i, j, k, l);
        if(flag)
            world.c(i, j, k, id, 1);
    }

    public void a(World world, int i, int j, int k)
    {
        world.applyPhysics(i + 1, j, k, id);
        world.applyPhysics(i - 1, j, k, id);
        world.applyPhysics(i, j, k + 1, id);
        world.applyPhysics(i, j, k - 1, id);
        world.applyPhysics(i, j - 1, k, id);
        world.applyPhysics(i, j + 1, k, id);
    }

    public boolean a()
    {
        return false;
    }

    public int a(int i, Random random)
    {
        return Item.DIODE.id;
    }

    public static final double a[] = {
        -0.0625D, 0.0625D, 0.1875D, 0.3125D
    };
    private static final int b[] = {
        1, 2, 3, 4
    };
    private final boolean c;

}
