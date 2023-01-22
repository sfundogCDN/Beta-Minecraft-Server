// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, Material, World, TileEntityRecordPlayer, 
//            EntityItem, ItemStack, EntityHuman, TileEntity

public class BlockJukeBox extends BlockContainer
{

    protected BlockJukeBox(int i, int j)
    {
        super(i, j, Material.WOOD);
    }

    public int a(int i)
    {
        return textureId + (i != 1 ? 0 : 1);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.getData(i, j, k) == 0)
        {
            return false;
        } else
        {
            c_(world, i, j, k);
            return true;
        }
    }

    public void f(World world, int i, int j, int k, int l)
    {
        if(world.isStatic)
            return;
        TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer)world.getTileEntity(i, j, k);
        if(tileentityrecordplayer == null)
        {
            return;
        } else
        {
            tileentityrecordplayer.a = l;
            tileentityrecordplayer.update();
            world.setData(i, j, k, 1);
            return;
        }
    }

    public void c_(World world, int i, int j, int k)
    {
        if(world.isStatic)
            return;
        TileEntityRecordPlayer tileentityrecordplayer = (TileEntityRecordPlayer)world.getTileEntity(i, j, k);
        if(tileentityrecordplayer == null)
            return;
        int l = tileentityrecordplayer.a;
        if(l == 0)
        {
            return;
        } else
        {
            world.e(1005, i, j, k, 0);
            world.a(null, i, j, k);
            tileentityrecordplayer.a = 0;
            tileentityrecordplayer.update();
            world.setData(i, j, k, 0);
            int i1 = l;
            float f1 = 0.7F;
            double d = (double)(world.random.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
            double d1 = (double)(world.random.nextFloat() * f1) + (double)(1.0F - f1) * 0.20000000000000001D + 0.59999999999999998D;
            double d2 = (double)(world.random.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
            EntityItem entityitem = new EntityItem(world, (double)i + d, (double)j + d1, (double)k + d2, new ItemStack(i1, 1, 0));
            entityitem.pickupDelay = 10;
            world.addEntity(entityitem);
            return;
        }
    }

    public void remove(World world, int i, int j, int k)
    {
        c_(world, i, j, k);
        super.remove(world, i, j, k);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f1)
    {
        if(world.isStatic)
        {
            return;
        } else
        {
            super.dropNaturally(world, i, j, k, l, f1);
            return;
        }
    }

    public TileEntity a_()
    {
        return new TileEntityRecordPlayer();
    }
}
