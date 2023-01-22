// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemPainting.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Item, EntityPainting, EntityHuman, World, 
//            ItemStack

public class ItemPainting extends Item
{

    public ItemPainting(int i)
    {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(l == 0)
            return false;
        if(l == 1)
            return false;
        byte b0 = 0;
        if(l == 4)
            b0 = 1;
        if(l == 3)
            b0 = 2;
        if(l == 5)
            b0 = 3;
        if(!entityhuman.c(i, j, k))
            return false;
        EntityPainting entitypainting = new EntityPainting(world, i, j, k, b0);
        if(entitypainting.i())
        {
            if(!world.isStatic)
            {
                Player who = entityhuman != null ? (Player)entityhuman.getBukkitEntity() : null;
                org.bukkit.block.Block blockClicked = world.getWorld().getBlockAt(i, j, k);
                org.bukkit.block.BlockFace blockFace = CraftBlock.notchToBlockFace(l);
                PaintingPlaceEvent event = new PaintingPlaceEvent((Painting)entitypainting.getBukkitEntity(), who, blockClicked, blockFace);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return false;
                world.addEntity(entitypainting);
            }
            itemstack.count--;
        }
        return true;
    }
}
