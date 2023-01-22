// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockDispenser.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

// Referenced classes of package net.minecraft.server:
//            BlockContainer, TileEntityDispenser, ItemStack, EntityArrow, 
//            EntityEgg, EntitySnowball, EntityItem, Material, 
//            Block, World, EntityHuman, Item, 
//            EntityLiving, MathHelper, TileEntity

public class BlockDispenser extends BlockContainer
{

    protected BlockDispenser(int i)
    {
        super(i, Material.STONE);
        a = new Random();
        textureId = 45;
    }

    public int c()
    {
        return 4;
    }

    public int a(int i, Random random)
    {
        return Block.DISPENSER.id;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        g(world, i, j, k);
    }

    private void g(World world, int i, int j, int k)
    {
        if(!world.isStatic)
        {
            int l = world.getTypeId(i, j, k - 1);
            int i1 = world.getTypeId(i, j, k + 1);
            int j1 = world.getTypeId(i - 1, j, k);
            int k1 = world.getTypeId(i + 1, j, k);
            byte b0 = 3;
            if(Block.o[l] && !Block.o[i1])
                b0 = 3;
            if(Block.o[i1] && !Block.o[l])
                b0 = 2;
            if(Block.o[j1] && !Block.o[k1])
                b0 = 5;
            if(Block.o[k1] && !Block.o[j1])
                b0 = 4;
            world.setData(i, j, k, b0);
        }
    }

    public int a(int i)
    {
        return i != 1 ? i != 0 ? i != 3 ? textureId : textureId + 1 : textureId + 17 : textureId + 17;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
            return true;
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
        if(tileentitydispenser != null)
            entityhuman.a(tileentitydispenser);
        return true;
    }

    public void dispense(World world, int i, int j, int k, Random random)
    {
        int l = world.getData(i, j, k);
        byte b0 = 0;
        byte b1 = 0;
        if(l == 3)
            b1 = 1;
        else
        if(l == 2)
            b1 = -1;
        else
        if(l == 5)
            b0 = 1;
        else
            b0 = -1;
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
        if(tileentitydispenser != null)
        {
            int dispenseSlot = tileentitydispenser.findDispenseSlot();
            net.minecraft.server.ItemStack itemstack = null;
            if(dispenseSlot > -1)
            {
                itemstack = tileentitydispenser.getContents()[dispenseSlot];
                itemstack = new net.minecraft.server.ItemStack(itemstack.id, 1, itemstack.getData());
            }
            double d0 = (double)i + (double)b0 * 0.59999999999999998D + 0.5D;
            double d1 = (double)j + 0.5D;
            double d2 = (double)k + (double)b1 * 0.59999999999999998D + 0.5D;
            if(itemstack == null)
            {
                world.e(1001, i, j, k, 0);
            } else
            {
                double d3 = random.nextDouble() * 0.10000000000000001D + 0.20000000000000001D;
                double motX = (double)b0 * d3;
                double motY = 0.20000000298023224D;
                double motZ = (double)b1 * d3;
                motX += random.nextGaussian() * 0.0074999998323619366D * 6D;
                motY += random.nextGaussian() * 0.0074999998323619366D * 6D;
                motZ += random.nextGaussian() * 0.0074999998323619366D * 6D;
                org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
                ItemStack bukkitItem = (new CraftItemStack(itemstack)).clone();
                BlockDispenseEvent event = new BlockDispenseEvent(block, bukkitItem, new Vector(motX, motY, motZ));
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
                tileentitydispenser.splitStack(dispenseSlot, 1);
                motX = event.getVelocity().getX();
                motY = event.getVelocity().getY();
                motZ = event.getVelocity().getZ();
                itemstack = new net.minecraft.server.ItemStack(event.getItem().getTypeId(), event.getItem().getAmount(), event.getItem().getDurability());
                if(itemstack.id == Item.ARROW.id)
                {
                    EntityArrow entityarrow = new EntityArrow(world, d0, d1, d2);
                    entityarrow.a(b0, 0.10000000149011612D, b1, 1.1F, 6F);
                    entityarrow.fromPlayer = true;
                    world.addEntity(entityarrow);
                    world.e(1002, i, j, k, 0);
                } else
                if(itemstack.id == Item.EGG.id)
                {
                    EntityEgg entityegg = new EntityEgg(world, d0, d1, d2);
                    entityegg.a(b0, 0.10000000149011612D, b1, 1.1F, 6F);
                    world.addEntity(entityegg);
                    world.e(1002, i, j, k, 0);
                } else
                if(itemstack.id == Item.SNOW_BALL.id)
                {
                    EntitySnowball entitysnowball = new EntitySnowball(world, d0, d1, d2);
                    entitysnowball.a(b0, 0.10000000149011612D, b1, 1.1F, 6F);
                    world.addEntity(entitysnowball);
                    world.e(1002, i, j, k, 0);
                } else
                {
                    EntityItem entityitem = new EntityItem(world, d0, d1 - 0.29999999999999999D, d2, itemstack);
                    entityitem.motX = motX;
                    entityitem.motY = motY;
                    entityitem.motZ = motZ;
                    world.addEntity(entityitem);
                    world.e(1000, i, j, k, 0);
                }
                world.e(2000, i, j, k, b0 + 1 + (b1 + 1) * 3);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(l > 0 && Block.byId[l].isPowerSource())
        {
            boolean flag = world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k);
            if(flag)
                world.c(i, j, k, id, c());
        }
    }

    public void a(World world, int i, int j, int k, Random random)
    {
        if(world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k))
            dispense(world, i, j, k, random);
    }

    public TileEntity a_()
    {
        return new TileEntityDispenser();
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
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
        if(tileentitydispenser != null)
        {
label0:
            for(int l = 0; l < tileentitydispenser.getSize(); l++)
            {
                net.minecraft.server.ItemStack itemstack = tileentitydispenser.getItem(l);
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
                    EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new net.minecraft.server.ItemStack(itemstack.id, i1, itemstack.getData()));
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

    private Random a;
}
