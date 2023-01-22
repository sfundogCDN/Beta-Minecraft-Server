// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityCow.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

// Referenced classes of package net.minecraft.server:
//            EntityAnimal, ItemStack, Item, EntityHuman, 
//            InventoryPlayer, World, NBTTagCompound

public class EntityCow extends EntityAnimal
{

    public EntityCow(World world)
    {
        super(world);
        texture = "/mob/cow.png";
        b(0.9F, 1.3F);
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
        return "mob.cow";
    }

    protected String i()
    {
        return "mob.cowhurt";
    }

    protected String j()
    {
        return "mob.cowhurt";
    }

    protected float l()
    {
        return 0.4F;
    }

    protected int k()
    {
        return Item.LEATHER.id;
    }

    protected void a(boolean flag)
    {
        List loot = new ArrayList();
        int count = random.nextInt(3);
        if(count > 0)
            loot.add(new ItemStack(Item.LEATHER.id, count));
        count = random.nextInt(3) + 1;
        if(count > 0)
            loot.add(new ItemStack(fireTicks <= 0 ? Item.RAW_BEEF.id : Item.COOKED_BEEF.id, count));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    public boolean b(EntityHuman entityhuman)
    {
        net.minecraft.server.ItemStack itemstack = entityhuman.inventory.getItemInHand();
        if(itemstack != null && itemstack.id == Item.BUCKET.id)
        {
            Location loc = getBukkitEntity().getLocation();
            PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), -1, itemstack, Item.MILK_BUCKET);
            if(event.isCancelled())
            {
                return false;
            } else
            {
                CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                itemstack = new net.minecraft.server.ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, itemstack);
                return true;
            }
        } else
        {
            return false;
        }
    }
}
