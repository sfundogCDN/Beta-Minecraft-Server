// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemFishingRod.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Item, EntityFishingHook, EntityHuman, ItemStack, 
//            World

public class ItemFishingRod extends Item
{

    public ItemFishingRod(int i)
    {
        super(i);
        d(64);
        c(1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        if(entityhuman.hookedFish != null)
        {
            int i = entityhuman.hookedFish.i();
            itemstack.damage(i, entityhuman);
            entityhuman.v();
        } else
        {
            PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, org.bukkit.event.player.PlayerFishEvent.State.FISHING);
            world.getServer().getPluginManager().callEvent(playerFishEvent);
            if(playerFishEvent.isCancelled())
                return itemstack;
            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
            if(!world.isStatic)
                world.addEntity(new EntityFishingHook(world, entityhuman));
            entityhuman.v();
        }
        return itemstack;
    }
}
