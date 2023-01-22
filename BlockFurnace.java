// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, Material, Block, World, 
//            TileEntityFurnace, EntityHuman, TileEntity, EntityLiving, 
//            MathHelper, IInventory, ItemStack, EntityItem

public class BlockFurnace extends BlockContainer
{

    protected BlockFurnace(int i, boolean flag)
    {
        super(i, Material.STONE);
        a = new Random();
        b = flag;
        textureId = 45;
    }

    public int a(int i, Random random)
    {
        return Block.FURNACE.id;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        g(world, i, j, k);
    }

    private void g(World world, int i, int j, int k)
    {
        if(world.isStatic)
            return;
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        byte byte0 = 3;
        if(Block.o[l] && !Block.o[i1])
            byte0 = 3;
        if(Block.o[i1] && !Block.o[l])
            byte0 = 2;
        if(Block.o[j1] && !Block.o[k1])
            byte0 = 5;
        if(Block.o[k1] && !Block.o[j1])
            byte0 = 4;
        world.setData(i, j, k, byte0);
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId + 17;
        if(i == 0)
            return textureId + 17;
        if(i == 3)
            return textureId - 1;
        else
            return textureId;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
            return true;
        TileEntityFurnace tileentityfurnace = (TileEntityFurnace)world.getTileEntity(i, j, k);
        if(tileentityfurnace != null)
            entityhuman.a(tileentityfurnace);
        return true;
    }

    public static void a(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        TileEntity tileentity = world.getTileEntity(i, j, k);
        c = true;
        if(flag)
            world.setTypeId(i, j, k, Block.BURNING_FURNACE.id);
        else
            world.setTypeId(i, j, k, Block.FURNACE.id);
        c = false;
        world.setData(i, j, k, l);
        if(tileentity != null)
        {
            tileentity.n();
            world.setTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity a_()
    {
        return new TileEntityFurnace();
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
            world.setData(i, j, k, 2);
        if(l == 1)
            world.setData(i, j, k, 5);
        if(l == 2)
            world.setData(i, j, k, 3);
        if(l == 3)
            world.setData(i, j, k, 4);
    }

    public void remove(World world, int i, int j, int k)
    {
        if(!c)
        {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace)world.getTileEntity(i, j, k);
            if(tileentityfurnace != null)
            {
label0:
                for(int l = 0; l < tileentityfurnace.getSize(); l++)
                {
                    ItemStack itemstack = tileentityfurnace.getItem(l);
                    if(itemstack == null)
                        continue;
                    float f = a.nextFloat() * 0.8F + 0.1F;
                    float f1 = a.nextFloat() * 0.8F + 0.1F;
                    float f2 = a.nextFloat() * 0.8F + 0.1F;
                    do
                    {
                        if(itemstack.count <= 0)
                            continue label0;
                        int i1 = a.nextInt(21) + 10;
                        if(i1 > itemstack.count)
                            i1 = itemstack.count;
                        itemstack.count -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.id, i1, itemstack.getData()));
                        float f3 = 0.05F;
                        entityitem.motX = (float)a.nextGaussian() * f3;
                        entityitem.motY = (float)a.nextGaussian() * f3 + 0.2F;
                        entityitem.motZ = (float)a.nextGaussian() * f3;
                        world.addEntity(entityitem);
                    } while(true);
                }

            }
        }
        super.remove(world, i, j, k);
    }

    private Random a;
    private final boolean b;
    private static boolean c = false;

}
