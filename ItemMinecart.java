// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemMinecart.java

package net.minecraft.server;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

// Referenced classes of package net.minecraft.server:
//            Item, EntityMinecart, World, BlockMinecartTrack, 
//            ItemStack, EntityHuman

public class ItemMinecart extends Item
{

    public ItemMinecart(int i, int j)
    {
        super(i);
        maxStackSize = 1;
        a = j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        int i1 = world.getTypeId(i, j, k);
        if(BlockMinecartTrack.c(i1))
        {
            if(!world.isStatic)
            {
                PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);
                if(event.isCancelled())
                    return false;
                world.addEntity(new EntityMinecart(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, a));
            }
            itemstack.count--;
            return true;
        } else
        {
            return false;
        }
    }

    public int a;
}
