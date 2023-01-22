// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, Material, World, EntityLiving, 
//            MathHelper, Block, TileEntityChest, IInventory, 
//            ItemStack, EntityItem, InventoryLargeChest, EntityHuman, 
//            TileEntity

public class BlockChest extends BlockContainer
{

    protected BlockChest(int i)
    {
        super(i, Material.WOOD);
        a = new Random();
        textureId = 26;
    }

    public boolean a()
    {
        return false;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        b(world, i, j, k);
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        if(l == id)
            b(world, i, j, k - 1);
        if(i1 == id)
            b(world, i, j, k + 1);
        if(j1 == id)
            b(world, i - 1, j, k);
        if(k1 == id)
            b(world, i + 1, j, k);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        byte byte0 = 0;
        int l1 = MathHelper.floor((double)((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if(l1 == 0)
            byte0 = 2;
        if(l1 == 1)
            byte0 = 5;
        if(l1 == 2)
            byte0 = 3;
        if(l1 == 3)
            byte0 = 4;
        if(l != id && i1 != id && j1 != id && k1 != id)
        {
            world.setData(i, j, k, byte0);
        } else
        {
            if((l == id || i1 == id) && (byte0 == 4 || byte0 == 5))
            {
                if(l == id)
                    world.setData(i, j, k - 1, byte0);
                else
                    world.setData(i, j, k + 1, byte0);
                world.setData(i, j, k, byte0);
            }
            if((j1 == id || k1 == id) && (byte0 == 2 || byte0 == 3))
            {
                if(j1 == id)
                    world.setData(i - 1, j, k, byte0);
                else
                    world.setData(i + 1, j, k, byte0);
                world.setData(i, j, k, byte0);
            }
        }
    }

    public void b(World world, int i, int j, int k)
    {
        if(world.isStatic)
            return;
        int l = world.getTypeId(i, j, k - 1);
        int i1 = world.getTypeId(i, j, k + 1);
        int j1 = world.getTypeId(i - 1, j, k);
        int k1 = world.getTypeId(i + 1, j, k);
        byte byte0 = 4;
        if(l == id || i1 == id)
        {
            int l1 = world.getTypeId(i - 1, j, l != id ? k + 1 : k - 1);
            int j2 = world.getTypeId(i + 1, j, l != id ? k + 1 : k - 1);
            byte0 = 5;
            int l2 = -1;
            if(l == id)
                l2 = world.getData(i, j, k - 1);
            else
                l2 = world.getData(i, j, k + 1);
            if(l2 == 4)
                byte0 = 4;
            if((Block.o[j1] || Block.o[l1]) && !Block.o[k1] && !Block.o[j2])
                byte0 = 5;
            if((Block.o[k1] || Block.o[j2]) && !Block.o[j1] && !Block.o[l1])
                byte0 = 4;
        } else
        if(j1 == id || k1 == id)
        {
            int i2 = world.getTypeId(j1 != id ? i + 1 : i - 1, j, k - 1);
            int k2 = world.getTypeId(j1 != id ? i + 1 : i - 1, j, k + 1);
            byte0 = 3;
            int i3 = -1;
            if(j1 == id)
                i3 = world.getData(i - 1, j, k);
            else
                i3 = world.getData(i + 1, j, k);
            if(i3 == 2)
                byte0 = 2;
            if((Block.o[l] || Block.o[i2]) && !Block.o[i1] && !Block.o[k2])
                byte0 = 3;
            if((Block.o[i1] || Block.o[k2]) && !Block.o[l] && !Block.o[i2])
                byte0 = 2;
        } else
        {
            byte0 = 3;
            if(Block.o[l] && !Block.o[i1])
                byte0 = 3;
            if(Block.o[i1] && !Block.o[l])
                byte0 = 2;
            if(Block.o[j1] && !Block.o[k1])
                byte0 = 5;
            if(Block.o[k1] && !Block.o[j1])
                byte0 = 4;
        }
        world.setData(i, j, k, byte0);
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId - 1;
        if(i == 0)
            return textureId - 1;
        if(i == 3)
            return textureId + 1;
        else
            return textureId;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        int l = 0;
        if(world.getTypeId(i - 1, j, k) == id)
            l++;
        if(world.getTypeId(i + 1, j, k) == id)
            l++;
        if(world.getTypeId(i, j, k - 1) == id)
            l++;
        if(world.getTypeId(i, j, k + 1) == id)
            l++;
        if(l > 1)
            return false;
        if(g(world, i - 1, j, k))
            return false;
        if(g(world, i + 1, j, k))
            return false;
        if(g(world, i, j, k - 1))
            return false;
        return !g(world, i, j, k + 1);
    }

    private boolean g(World world, int i, int j, int k)
    {
        if(world.getTypeId(i, j, k) != id)
            return false;
        if(world.getTypeId(i - 1, j, k) == id)
            return true;
        if(world.getTypeId(i + 1, j, k) == id)
            return true;
        if(world.getTypeId(i, j, k - 1) == id)
            return true;
        return world.getTypeId(i, j, k + 1) == id;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        super.doPhysics(world, i, j, k, l);
        TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(i, j, k);
        if(tileentitychest != null)
            tileentitychest.g();
    }

    public void remove(World world, int i, int j, int k)
    {
        TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(i, j, k);
        if(tileentitychest != null)
        {
label0:
            for(int l = 0; l < tileentitychest.getSize(); l++)
            {
                ItemStack itemstack = tileentitychest.getItem(l);
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
        super.remove(world, i, j, k);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        Object obj = (TileEntityChest)world.getTileEntity(i, j, k);
        if(obj == null)
            return true;
        if(world.e(i, j + 1, k))
            return true;
        if(world.getTypeId(i - 1, j, k) == id && world.e(i - 1, j + 1, k))
            return true;
        if(world.getTypeId(i + 1, j, k) == id && world.e(i + 1, j + 1, k))
            return true;
        if(world.getTypeId(i, j, k - 1) == id && world.e(i, j + 1, k - 1))
            return true;
        if(world.getTypeId(i, j, k + 1) == id && world.e(i, j + 1, k + 1))
            return true;
        if(world.getTypeId(i - 1, j, k) == id)
            obj = new InventoryLargeChest("Large chest", (TileEntityChest)world.getTileEntity(i - 1, j, k), ((IInventory) (obj)));
        if(world.getTypeId(i + 1, j, k) == id)
            obj = new InventoryLargeChest("Large chest", ((IInventory) (obj)), (TileEntityChest)world.getTileEntity(i + 1, j, k));
        if(world.getTypeId(i, j, k - 1) == id)
            obj = new InventoryLargeChest("Large chest", (TileEntityChest)world.getTileEntity(i, j, k - 1), ((IInventory) (obj)));
        if(world.getTypeId(i, j, k + 1) == id)
            obj = new InventoryLargeChest("Large chest", ((IInventory) (obj)), (TileEntityChest)world.getTileEntity(i, j, k + 1));
        if(world.isStatic)
        {
            return true;
        } else
        {
            entityhuman.a(((IInventory) (obj)));
            return true;
        }
    }

    public TileEntity a_()
    {
        return new TileEntityChest();
    }

    private Random a;
}
