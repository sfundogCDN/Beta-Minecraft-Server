// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, Material, World, TileEntityPiston, 
//            Block, PistonBlockTextures, AxisAlignedBB, IBlockAccess, 
//            TileEntity, EntityHuman

public class BlockPistonMoving extends BlockContainer
{

    public BlockPistonMoving(int i)
    {
        super(i, Material.PISTON);
        c(-1F);
    }

    public TileEntity a_()
    {
        return null;
    }

    public void a(World world, int i, int j, int k)
    {
    }

    public void remove(World world, int i, int j, int k)
    {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if(tileentity != null && (tileentity instanceof TileEntityPiston))
            ((TileEntityPiston)tileentity).e();
        else
            super.remove(world, i, j, k);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k, int l)
    {
        return false;
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(!world.isStatic && world.getTileEntity(i, j, k) == null)
        {
            world.setTypeId(i, j, k, 0);
            return true;
        } else
        {
            return false;
        }
    }

    public int a(int i, Random random)
    {
        return 0;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f)
    {
        if(world.isStatic)
            return;
        TileEntityPiston tileentitypiston = b(world, i, j, k);
        if(tileentitypiston == null)
        {
            return;
        } else
        {
            Block.byId[tileentitypiston.a()].g(world, i, j, k, tileentitypiston.j());
            return;
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!world.isStatic)
            if(world.getTileEntity(i, j, k) != null);
    }

    public static TileEntity a(int i, int j, int k, boolean flag, boolean flag1)
    {
        return new TileEntityPiston(i, j, k, flag, flag1);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        TileEntityPiston tileentitypiston = b(world, i, j, k);
        if(tileentitypiston == null)
            return null;
        float f = tileentitypiston.a(0.0F);
        if(tileentitypiston.c())
            f = 1.0F - f;
        return a(world, i, j, k, tileentitypiston.a(), f, tileentitypiston.d());
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        TileEntityPiston tileentitypiston = b(iblockaccess, i, j, k);
        if(tileentitypiston != null)
        {
            Block block = Block.byId[tileentitypiston.a()];
            if(block == null || block == this)
                return;
            block.a(iblockaccess, i, j, k);
            float f = tileentitypiston.a(0.0F);
            if(tileentitypiston.c())
                f = 1.0F - f;
            int l = tileentitypiston.d();
            minX = block.minX - (double)((float)PistonBlockTextures.b[l] * f);
            minY = block.minY - (double)((float)PistonBlockTextures.c[l] * f);
            minZ = block.minZ - (double)((float)PistonBlockTextures.d[l] * f);
            maxX = block.maxX - (double)((float)PistonBlockTextures.b[l] * f);
            maxY = block.maxY - (double)((float)PistonBlockTextures.c[l] * f);
            maxZ = block.maxZ - (double)((float)PistonBlockTextures.d[l] * f);
        }
    }

    public AxisAlignedBB a(World world, int i, int j, int k, int l, float f, int i1)
    {
        if(l == 0 || l == id)
            return null;
        AxisAlignedBB axisalignedbb = Block.byId[l].e(world, i, j, k);
        if(axisalignedbb == null)
        {
            return null;
        } else
        {
            axisalignedbb.a -= (float)PistonBlockTextures.b[i1] * f;
            axisalignedbb.d -= (float)PistonBlockTextures.b[i1] * f;
            axisalignedbb.b -= (float)PistonBlockTextures.c[i1] * f;
            axisalignedbb.e -= (float)PistonBlockTextures.c[i1] * f;
            axisalignedbb.c -= (float)PistonBlockTextures.d[i1] * f;
            axisalignedbb.f -= (float)PistonBlockTextures.d[i1] * f;
            return axisalignedbb;
        }
    }

    private TileEntityPiston b(IBlockAccess iblockaccess, int i, int j, int k)
    {
        TileEntity tileentity = iblockaccess.getTileEntity(i, j, k);
        if(tileentity != null && (tileentity instanceof TileEntityPiston))
            return (TileEntityPiston)tileentity;
        else
            return null;
    }
}
