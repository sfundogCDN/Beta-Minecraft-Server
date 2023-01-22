// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FoodMetaData.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            ItemFood, EntityHuman, World, DamageSource, 
//            NBTTagCompound

public class FoodMetaData
{

    public FoodMetaData()
    {
        foodLevel = 20;
        saturationLevel = 5F;
        foodTickTimer = 0;
        e = 20;
    }

    public void a(int i, float f)
    {
        foodLevel = Math.min(i + foodLevel, 20);
        saturationLevel = Math.min(saturationLevel + (float)i * f * 2.0F, foodLevel);
    }

    public void a(ItemFood itemfood)
    {
        a(itemfood.k(), itemfood.l());
    }

    public void a(EntityHuman entityhuman)
    {
        int i = entityhuman.world.difficulty;
        e = foodLevel;
        if(exhaustionLevel > 4F)
        {
            exhaustionLevel -= 4F;
            if(saturationLevel > 0.0F)
                saturationLevel = Math.max(saturationLevel - 1.0F, 0.0F);
            else
            if(i > 0)
            {
                FoodLevelChangeEvent event = new FoodLevelChangeEvent(entityhuman.getBukkitEntity(), Math.max(foodLevel - 1, 0));
                entityhuman.world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled())
                    foodLevel = event.getFoodLevel();
            }
        }
        if(foodLevel >= 18 && entityhuman.W())
        {
            foodTickTimer++;
            if(foodTickTimer >= 80)
            {
                entityhuman.c(1, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.SATIATED);
                foodTickTimer = 0;
            }
        } else
        if(foodLevel <= 0)
        {
            foodTickTimer++;
            if(foodTickTimer >= 80)
            {
                if(entityhuman.health > 10 || i >= 3 || entityhuman.health > 1 && i >= 2)
                {
                    EntityDamageEvent event = new EntityDamageEvent(entityhuman.getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.STARVATION, 1);
                    entityhuman.world.getServer().getPluginManager().callEvent(event);
                    if(!event.isCancelled())
                        entityhuman.damageEntity(DamageSource.STARVE, event.getDamage());
                }
                foodTickTimer = 0;
            }
        } else
        {
            foodTickTimer = 0;
        }
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        if(nbttagcompound.hasKey("foodLevel"))
        {
            foodLevel = nbttagcompound.e("foodLevel");
            foodTickTimer = nbttagcompound.e("foodTickTimer");
            saturationLevel = nbttagcompound.g("foodSaturationLevel");
            exhaustionLevel = nbttagcompound.g("foodExhaustionLevel");
        }
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("foodLevel", foodLevel);
        nbttagcompound.a("foodTickTimer", foodTickTimer);
        nbttagcompound.a("foodSaturationLevel", saturationLevel);
        nbttagcompound.a("foodExhaustionLevel", exhaustionLevel);
    }

    public int a()
    {
        return foodLevel;
    }

    public boolean b()
    {
        return foodLevel < 20;
    }

    public void a(float f)
    {
        exhaustionLevel = Math.min(exhaustionLevel + f, 40F);
    }

    public float c()
    {
        return saturationLevel;
    }

    public int foodLevel;
    public float saturationLevel;
    public float exhaustionLevel;
    public int foodTickTimer;
    private int e;
}
