// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemFood.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Item, MobEffect, ItemStack, EntityHuman, 
//            FoodMetaData, World, EnumAnimation

public class ItemFood extends Item
{

    public ItemFood(int i, int j, float f, boolean flag)
    {
        super(i);
        a = 32;
        bt = j;
        bv = flag;
        bu = f;
    }

    public ItemFood(int i, int j, boolean flag)
    {
        this(i, j, 0.6F, flag);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        itemstack.count--;
        int oldFoodLevel = entityhuman.getFoodData().foodLevel;
        FoodLevelChangeEvent event = new FoodLevelChangeEvent(entityhuman.getBukkitEntity(), Math.min(k() + entityhuman.getFoodData().foodLevel, 20));
        entityhuman.world.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled())
            entityhuman.getFoodData().a(event.getFoodLevel() - oldFoodLevel, l());
        if(!world.isStatic && bx > 0 && world.random.nextFloat() < bA)
            entityhuman.addEffect(new MobEffect(bx, by * 20, bz));
        return itemstack;
    }

    public int c(ItemStack itemstack)
    {
        return 32;
    }

    public EnumAnimation b(ItemStack itemstack)
    {
        return EnumAnimation.b;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        if(entityhuman.c(bw))
            entityhuman.a(itemstack, c(itemstack));
        return itemstack;
    }

    public int k()
    {
        return bt;
    }

    public float l()
    {
        return bu;
    }

    public boolean m()
    {
        return bv;
    }

    public ItemFood a(int i, int j, int k, float f)
    {
        bx = i;
        by = j;
        bz = k;
        bA = f;
        return this;
    }

    public ItemFood n()
    {
        bw = true;
        return this;
    }

    public Item a(String s)
    {
        return super.a(s);
    }

    public final int a;
    private final int bt;
    private final float bu;
    private final boolean bv;
    private boolean bw;
    private int bx;
    private int by;
    private int bz;
    private float bA;
}
