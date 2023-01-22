// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemBoat.java

package net.minecraft.server;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

// Referenced classes of package net.minecraft.server:
//            Item, EntityBoat, EntityHuman, Vec3D, 
//            MathHelper, World, MovingObjectPosition, EnumMovingObjectType, 
//            Block, ItemStack

public class ItemBoat extends Item
{

    public ItemBoat(int i)
    {
        super(i);
        maxStackSize = 1;
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
        MovingObjectPosition movingobjectposition = world.rayTrace(vec3d, vec3d1, true);
        if(movingobjectposition == null)
            return itemstack;
        if(movingobjectposition.type == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.b;
            int j = movingobjectposition.c;
            int k = movingobjectposition.d;
            if(!world.isStatic)
            {
                PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, movingobjectposition.face, itemstack);
                if(event.isCancelled())
                    return itemstack;
                if(world.getTypeId(i, j, k) == Block.SNOW.id)
                    j--;
                world.addEntity(new EntityBoat(world, (float)i + 0.5F, (float)j + 1.0F, (float)k + 0.5F));
            }
            itemstack.count--;
        }
        return itemstack;
    }
}
