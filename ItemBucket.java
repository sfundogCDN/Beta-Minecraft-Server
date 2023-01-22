// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemBucket.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.material.MaterialData;

// Referenced classes of package net.minecraft.server:
//            Item, ItemStack, EntityCow, EntityHuman, 
//            Vec3D, MathHelper, World, MovingObjectPosition, 
//            EnumMovingObjectType, Material, WorldProvider, Block, 
//            PlayerAbilities, Entity

public class ItemBucket extends Item
{

    public ItemBucket(int i, int j)
    {
        super(i);
        maxStackSize = 1;
        a = j;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        float f = 1.0F;
        float f1 = entityhuman.lastPitch + (entityhuman.pitch - entityhuman.lastPitch) * f;
        float f2 = entityhuman.lastYaw + (entityhuman.yaw - entityhuman.lastYaw) * f;
        double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * (double)f;
        double d1 = (entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * (double)f + 1.6200000000000001D) - (double)entityhuman.height;
        double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * (double)f;
        Vec3D vec3d = Vec3D.create(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5D;
        Vec3D vec3d1 = vec3d.add((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        MovingObjectPosition movingobjectposition = world.rayTrace(vec3d, vec3d1, a == 0);
        if(movingobjectposition == null)
            return itemstack;
        if(movingobjectposition.type == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.b;
            int j = movingobjectposition.c;
            int k = movingobjectposition.d;
            if(!world.a(entityhuman, i, j, k))
                return itemstack;
            if(a == 0)
            {
                if(!entityhuman.c(i, j, k))
                    return itemstack;
                if(world.getMaterial(i, j, k) == Material.WATER && world.getData(i, j, k) == 0)
                {
                    PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, i, j, k, -1, itemstack, Item.WATER_BUCKET);
                    if(event.isCancelled())
                    {
                        return itemstack;
                    } else
                    {
                        CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                        byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                        world.setTypeId(i, j, k, 0);
                        return new ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
                    }
                }
                if(world.getMaterial(i, j, k) == Material.LAVA && world.getData(i, j, k) == 0)
                {
                    PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, i, j, k, -1, itemstack, Item.LAVA_BUCKET);
                    if(event.isCancelled())
                    {
                        return itemstack;
                    } else
                    {
                        CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                        byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                        world.setTypeId(i, j, k, 0);
                        return new ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
                    }
                }
            } else
            {
                if(a < 0)
                {
                    PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, i, j, k, movingobjectposition.face, itemstack);
                    if(event.isCancelled())
                    {
                        return itemstack;
                    } else
                    {
                        CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                        byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                        return new ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
                    }
                }
                int clickedX = i;
                int clickedY = j;
                int clickedZ = k;
                if(movingobjectposition.face == 0)
                    j--;
                if(movingobjectposition.face == 1)
                    j++;
                if(movingobjectposition.face == 2)
                    k--;
                if(movingobjectposition.face == 3)
                    k++;
                if(movingobjectposition.face == 4)
                    i--;
                if(movingobjectposition.face == 5)
                    i++;
                if(!entityhuman.c(i, j, k))
                    return itemstack;
                if(world.isEmpty(i, j, k) || !world.getMaterial(i, j, k).isBuildable())
                {
                    PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, clickedX, clickedY, clickedZ, movingobjectposition.face, itemstack);
                    if(event.isCancelled())
                        return itemstack;
                    if(world.worldProvider.d && a == Block.WATER.id)
                    {
                        world.makeSound(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                        for(int l = 0; l < 8; l++)
                            world.a("largesmoke", (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);

                    } else
                    {
                        world.setTypeIdAndData(i, j, k, a, 0);
                    }
                    if(entityhuman.abilities.canInstantlyBuild)
                    {
                        return itemstack;
                    } else
                    {
                        CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                        byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                        return new ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
                    }
                }
            }
        } else
        if(a == 0 && (movingobjectposition.entity instanceof EntityCow))
        {
            Location loc = movingobjectposition.entity.getBukkitEntity().getLocation();
            PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), -1, itemstack, Item.MILK_BUCKET);
            if(event.isCancelled())
            {
                return itemstack;
            } else
            {
                CraftItemStack itemInHand = (CraftItemStack)event.getItemStack();
                byte data = itemInHand.getData() != null ? itemInHand.getData().getData() : 0;
                return new ItemStack(itemInHand.getTypeId(), itemInHand.getAmount(), data);
            }
        }
        return itemstack;
    }

    private int a;
}
