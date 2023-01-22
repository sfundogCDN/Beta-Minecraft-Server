// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityChicken.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.inventory.ItemStack;

// Referenced classes of package net.minecraft.server:
//            EntityAnimal, World, Item, NBTTagCompound

public class EntityChicken extends EntityAnimal
{

    public EntityChicken(World world)
    {
        super(world);
        a = false;
        b = 0.0F;
        c = 0.0F;
        i = 1.0F;
        texture = "/mob/chicken.png";
        b(0.3F, 0.7F);
        health = 4;
        j = random.nextInt(6000) + 6000;
    }

    public void s()
    {
        super.s();
        h = b;
        g = c;
        c = (float)((double)c + (double)(onGround ? -1 : 4) * 0.29999999999999999D);
        if(c < 0.0F)
            c = 0.0F;
        if(c > 1.0F)
            c = 1.0F;
        if(!onGround && i < 1.0F)
            i = 1.0F;
        i = (float)((double)i * 0.90000000000000002D);
        if(!onGround && motY < 0.0D)
            motY *= 0.59999999999999998D;
        b += i * 2.0F;
        if(!world.isStatic && --j <= 0)
        {
            world.makeSound(this, "mob.chickenplop", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            b(Item.EGG.id, 1);
            j = random.nextInt(6000) + 6000;
        }
    }

    protected void a(float f1)
    {
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    protected String h()
    {
        return "mob.chicken";
    }

    protected String i()
    {
        return "mob.chickenhurt";
    }

    protected String j()
    {
        return "mob.chickenhurt";
    }

    protected int k()
    {
        return Item.FEATHER.id;
    }

    protected void a(boolean flag)
    {
        List loot = new ArrayList();
        int count = random.nextInt(3);
        if(count > 0)
        {
            loot.add(new ItemStack(Item.FEATHER.id, count));
            loot.add(new ItemStack(fireTicks <= 0 ? Item.RAW_CHICKEN.id : Item.COOKED_CHICKEN.id, 1));
        }
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    public boolean a;
    public float b;
    public float c;
    public float g;
    public float h;
    public float i;
    public int j;
}
