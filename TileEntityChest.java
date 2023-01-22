// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityChest.java

package net.minecraft.server;

import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.craftbukkit.CraftServer;

// Referenced classes of package net.minecraft.server:
//            TileEntity, ItemStack, NBTTagCompound, NBTTagList, 
//            IInventory, World, EntityHuman, Block

public class TileEntityChest extends TileEntity
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public TileEntityChest()
    {
        items = new ItemStack[27];
        a = false;
    }

    public int getSize()
    {
        return 27;
    }

    public ItemStack getItem(int i)
    {
        return items[i];
    }

    public ItemStack splitStack(int i, int j)
    {
        if(items[i] != null)
        {
            ItemStack itemstack;
            if(items[i].count <= j)
            {
                itemstack = items[i];
                items[i] = null;
                update();
                return itemstack;
            }
            itemstack = items[i].a(j);
            if(items[i].count == 0)
                items[i] = null;
            update();
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack)
    {
        items[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxStackSize())
            itemstack.count = getMaxStackSize();
        update();
    }

    public String getName()
    {
        return "Chest";
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.l("Items");
        items = new ItemStack[getSize()];
        for(int i = 0; i < nbttaglist.c(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(i);
            int j = nbttagcompound1.c("Slot") & 0xff;
            if(j >= 0 && j < items.length)
                items[j] = ItemStack.a(nbttagcompound1);
        }

    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.a("Slot", (byte)i);
                items[i].b(nbttagcompound1);
                nbttaglist.a(nbttagcompound1);
            }

        nbttagcompound.a("Items", nbttaglist);
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public boolean a(EntityHuman entityhuman)
    {
        if(world == null)
            return true;
        else
            return world.getTileEntity(x, y, z) == this ? entityhuman.e((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D) <= 64D : false;
    }

    public void g()
    {
        super.g();
        a = false;
    }

    public void h()
    {
        if(!a)
        {
            a = true;
            b = null;
            c = null;
            d = null;
            e = null;
            if(world.getTypeId(x - 1, y, z) == Block.CHEST.id)
                d = (TileEntityChest)world.getTileEntity(x - 1, y, z);
            if(world.getTypeId(x + 1, y, z) == Block.CHEST.id)
                c = (TileEntityChest)world.getTileEntity(x + 1, y, z);
            if(world.getTypeId(x, y, z - 1) == Block.CHEST.id)
                b = (TileEntityChest)world.getTileEntity(x, y, z - 1);
            if(world.getTypeId(x, y, z + 1) == Block.CHEST.id)
                e = (TileEntityChest)world.getTileEntity(x, y, z + 1);
            if(b != null)
                b.g();
            if(e != null)
                e.g();
            if(c != null)
                c.g();
            if(d != null)
                d.g();
        }
    }

    private TileEntityChest getTileEntity(int x, int y, int z)
    {
        if(world == null)
            return null;
        TileEntity entity = world.getTileEntity(x, y, z);
        if(entity instanceof TileEntityChest)
            return (TileEntityChest)entity;
        String name = "null";
        if(entity != null)
            name = entity.toString();
        world.getServer().getLogger().severe((new StringBuilder()).append("Block at ").append(x).append(",").append(y).append(",").append(z).append(" is a chest but has a ").append(name).toString());
        return null;
    }

    public void h_()
    {
        super.h_();
        if(world == null)
            return;
        h();
        if(++q % 80 == 0)
            world.playNote(x, y, z, 1, h);
        g = this.f;
        float f = 0.1F;
        if(h > 0 && this.f == 0.0F && b == null && d == null)
        {
            double d0 = (double)x + 0.5D;
            double d1 = (double)z + 0.5D;
            if(e != null)
                d1 += 0.5D;
            if(c != null)
                d0 += 0.5D;
            world.makeSound(d0, (double)y + 0.5D, d1, "random.door_open", 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
        }
        if(h == 0 && this.f > 0.0F || h > 0 && this.f < 1.0F)
        {
            if(h > 0)
                this.f += f;
            else
                this.f -= f;
            if(this.f > 1.0F)
                this.f = 1.0F;
            if(this.f < 0.0F)
            {
                this.f = 0.0F;
                if(b == null && d == null)
                {
                    double d0 = (double)x + 0.5D;
                    double d1 = (double)z + 0.5D;
                    if(e != null)
                        d1 += 0.5D;
                    if(c != null)
                        d0 += 0.5D;
                    world.makeSound(d0, (double)y + 0.5D, d1, "random.door_close", 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                }
            }
        }
    }

    public void b(int i, int j)
    {
        if(i == 1)
            h = j;
    }

    public void e()
    {
        h++;
        if(world == null)
        {
            return;
        } else
        {
            world.playNote(x, y, z, 1, h);
            return;
        }
    }

    public void t_()
    {
        h--;
        if(world == null)
        {
            return;
        } else
        {
            world.playNote(x, y, z, 1, h);
            return;
        }
    }

    public void i()
    {
        g();
        h();
        super.i();
    }

    private ItemStack items[];
    public boolean a;
    public TileEntityChest b;
    public TileEntityChest c;
    public TileEntityChest d;
    public TileEntityChest e;
    public float f;
    public float g;
    public int h;
    private int q;
}
