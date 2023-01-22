// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySheep.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.inventory.ItemStack;

// Referenced classes of package net.minecraft.server:
//            EntityAnimal, ItemStack, DataWatcher, Block, 
//            EntityHuman, InventoryPlayer, Item, ItemShears, 
//            World, EntityItem, NBTTagCompound

public class EntitySheep extends EntityAnimal
{

    public EntitySheep(World world)
    {
        super(world);
        texture = "/mob/sheep.png";
        b(0.9F, 1.3F);
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, new Byte((byte)0));
    }

    protected void a(boolean flag)
    {
        List loot = new ArrayList();
        if(!isSheared())
            loot.add(new ItemStack(Material.WOOL, 1, (short)0, Byte.valueOf((byte)getColor())));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    protected int k()
    {
        return Block.WOOL.id;
    }

    public boolean b(EntityHuman entityhuman)
    {
        net.minecraft.server.ItemStack itemstack = entityhuman.inventory.getItemInHand();
        if(itemstack != null && itemstack.id == Item.SHEARS.id && !isSheared())
        {
            if(!world.isStatic)
            {
                setSheared(true);
                int i = 2 + random.nextInt(3);
                for(int j = 0; j < i; j++)
                {
                    EntityItem entityitem = a(new net.minecraft.server.ItemStack(Block.WOOL.id, 1, getColor()), 1.0F);
                    entityitem.motY += random.nextFloat() * 0.05F;
                    entityitem.motX += (random.nextFloat() - random.nextFloat()) * 0.1F;
                    entityitem.motZ += (random.nextFloat() - random.nextFloat()) * 0.1F;
                }

            }
            itemstack.damage(1, entityhuman);
        }
        return false;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Sheared", isSheared());
        nbttagcompound.a("Color", (byte)getColor());
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setSheared(nbttagcompound.m("Sheared"));
        setColor(nbttagcompound.c("Color"));
    }

    protected String h()
    {
        return "mob.sheep";
    }

    protected String i()
    {
        return "mob.sheep";
    }

    protected String j()
    {
        return "mob.sheep";
    }

    public int getColor()
    {
        return datawatcher.getByte(16) & 0xf;
    }

    public void setColor(int i)
    {
        byte b0 = datawatcher.getByte(16);
        datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xf0 | i & 0xf)));
    }

    public boolean isSheared()
    {
        return (datawatcher.getByte(16) & 0x10) != 0;
    }

    public void setSheared(boolean flag)
    {
        byte b0 = datawatcher.getByte(16);
        if(flag)
            datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x10)));
        else
            datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xffffffef)));
    }

    public static int a(Random random)
    {
        int i = random.nextInt(100);
        return i >= 5 ? i >= 10 ? i >= 15 ? i >= 18 ? ((byte)(random.nextInt(500) != 0 ? 0 : 6)) : 12 : 8 : 7 : 15;
    }

    public static final float a[][] = {
        {
            1.0F, 1.0F, 1.0F
        }, {
            0.95F, 0.7F, 0.2F
        }, {
            0.9F, 0.5F, 0.85F
        }, {
            0.6F, 0.7F, 0.95F
        }, {
            0.9F, 0.9F, 0.2F
        }, {
            0.5F, 0.8F, 0.1F
        }, {
            0.95F, 0.7F, 0.8F
        }, {
            0.3F, 0.3F, 0.3F
        }, {
            0.6F, 0.6F, 0.6F
        }, {
            0.3F, 0.6F, 0.7F
        }, {
            0.7F, 0.4F, 0.9F
        }, {
            0.2F, 0.4F, 0.8F
        }, {
            0.5F, 0.4F, 0.3F
        }, {
            0.4F, 0.5F, 0.2F
        }, {
            0.8F, 0.3F, 0.3F
        }, {
            0.1F, 0.1F, 0.1F
        }
    };

}
