// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material, World, AxisAlignedBB

public class BlockFence extends Block
{

    public BlockFence(int i, int j)
    {
        super(i, j, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        if(world.getTypeId(i, j - 1, k) == id)
            return true;
        if(!world.getMaterial(i, j - 1, k).isBuildable())
            return false;
        else
            return super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
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
}
