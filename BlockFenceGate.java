// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material, World, AxisAlignedBB, 
//            EntityLiving, MathHelper, EntityHuman

public class BlockFenceGate extends Block
{

    public BlockFenceGate(int i, int j)
    {
        super(i, j, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        if(!world.getMaterial(i, j - 1, k).isBuildable())
            return false;
        else
            return super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        if(c(l))
            return null;
        else
            return AxisAlignedBB.b(i, j, k, i + 1, (float)j + 1.5F, k + 1);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = (MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 0.5D) & 3) % 4;
        world.setData(i, j, k, l);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        int l = world.getData(i, j, k);
        if(c(l))
        {
            world.setData(i, j, k, l & -5);
        } else
        {
            int i1 = (MathHelper.floor((double)((entityhuman.yaw * 4F) / 360F) + 0.5D) & 3) % 4;
            int j1 = d(l);
            if(j1 == (i1 + 2) % 4)
                l = i1;
            world.setData(i, j, k, l | 4);
        }
        world.a(entityhuman, 1003, i, j, k, 0);
        return true;
    }

    public static boolean c(int i)
    {
        return (i & 4) != 0;
    }

    public static int d(int i)
    {
        return i & 3;
    }
}
