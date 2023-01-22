// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityFurnace.java

package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            TileEntity, ItemStack, NBTTagCompound, NBTTagList, 
//            IInventory, World, BlockFurnace, FurnaceRecipes, 
//            Item, Block, Material, EntityHuman

public class TileEntityFurnace extends TileEntity
    implements IInventory
{

    public net.minecraft.server.ItemStack[] getContents()
    {
        return items;
    }

    public TileEntityFurnace()
    {
        items = new net.minecraft.server.ItemStack[3];
        burnTime = 0;
        ticksForCurrentFuel = 0;
        cookTime = 0;
        lastTick = (int)(System.currentTimeMillis() / 50L);
    }

    public int getSize()
    {
        return items.length;
    }

    public net.minecraft.server.ItemStack getItem(int i)
    {
        return items[i];
    }

    public net.minecraft.server.ItemStack splitStack(int i, int j)
    {
        if(items[i] != null)
        {
            net.minecraft.server.ItemStack itemstack;
            if(items[i].count <= j)
            {
                itemstack = items[i];
                items[i] = null;
                return itemstack;
            }
            itemstack = items[i].a(j);
            if(items[i].count == 0)
                items[i] = null;
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, net.minecraft.server.ItemStack itemstack)
    {
        items[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxStackSize())
            itemstack.count = getMaxStackSize();
    }

    public String getName()
    {
        return "Furnace";
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.l("Items");
        items = new net.minecraft.server.ItemStack[getSize()];
        for(int i = 0; i < nbttaglist.c(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(i);
            byte b0 = nbttagcompound1.c("Slot");
            if(b0 >= 0 && b0 < items.length)
                items[b0] = ItemStack.a(nbttagcompound1);
        }

        burnTime = nbttagcompound.d("BurnTime");
        cookTime = nbttagcompound.d("CookTime");
        ticksForCurrentFuel = fuelTime(items[1]);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("BurnTime", (short)burnTime);
        nbttagcompound.a("CookTime", (short)cookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.a("Slot", (byte)i);
                items[i].b(nbttagcompound1);
                nbttaglist.a(nbttagcompound1);
            }

        nbttagcompound.a("Items", nbttaglist);
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public boolean isBurning()
    {
        return burnTime > 0;
    }

    public void h_()
    {
        boolean flag = burnTime > 0;
        boolean flag1 = false;
        int currentTick = (int)(System.currentTimeMillis() / 50L);
        int elapsedTicks = currentTick - lastTick;
        lastTick = currentTick;
        if(isBurning() && canBurn())
        {
            cookTime += elapsedTicks;
            if(cookTime >= 200)
            {
                cookTime %= 200;
                burn();
                flag1 = true;
            }
        } else
        {
            cookTime = 0;
        }
        if(burnTime > 0)
            burnTime -= elapsedTicks;
        if(!world.isStatic)
        {
            if(burnTime <= 0 && canBurn() && items[1] != null)
            {
                CraftItemStack fuel = new CraftItemStack(items[1]);
                FurnaceBurnEvent furnaceBurnEvent = new FurnaceBurnEvent(world.getWorld().getBlockAt(x, y, z), fuel, fuelTime(items[1]));
                world.getServer().getPluginManager().callEvent(furnaceBurnEvent);
                if(furnaceBurnEvent.isCancelled())
                    return;
                ticksForCurrentFuel = furnaceBurnEvent.getBurnTime();
                burnTime += ticksForCurrentFuel;
                if(burnTime > 0 && furnaceBurnEvent.isBurning())
                {
                    flag1 = true;
                    if(items[1] != null)
                    {
                        items[1].count--;
                        if(items[1].count == 0)
                            items[1] = null;
                    }
                }
            }
            if(flag != (burnTime > 0))
            {
                flag1 = true;
                BlockFurnace.a(burnTime > 0, world, x, y, z);
            }
        }
        if(flag1)
            update();
    }

    private boolean canBurn()
    {
        if(items[0] == null)
        {
            return false;
        } else
        {
            net.minecraft.server.ItemStack itemstack = FurnaceRecipes.getInstance().a(items[0].getItem().id);
            return itemstack != null ? items[2] != null ? items[2].doMaterialsMatch(itemstack) ? items[2].count + itemstack.count > getMaxStackSize() || items[2].count >= items[2].getMaxStackSize() ? items[2].count + itemstack.count <= itemstack.getMaxStackSize() : true : false : true : false;
        }
    }

    public void burn()
    {
        if(canBurn())
        {
            net.minecraft.server.ItemStack itemstack = FurnaceRecipes.getInstance().a(items[0].getItem().id);
            CraftItemStack source = new CraftItemStack(items[0]);
            CraftItemStack result = new CraftItemStack(itemstack.cloneItemStack());
            FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(world.getWorld().getBlockAt(x, y, z), source, result);
            world.getServer().getPluginManager().callEvent(furnaceSmeltEvent);
            if(furnaceSmeltEvent.isCancelled())
                return;
            ItemStack oldResult = furnaceSmeltEvent.getResult();
            net.minecraft.server.ItemStack newResult = new net.minecraft.server.ItemStack(oldResult.getTypeId(), oldResult.getAmount(), oldResult.getDurability());
            itemstack = newResult;
            if(items[2] == null)
                items[2] = itemstack.cloneItemStack();
            else
            if(items[2].id == itemstack.id && items[2].getData() == itemstack.getData())
                items[2].count += itemstack.count;
            items[0].count--;
            if(items[0].count <= 0)
                items[0] = null;
        }
    }

    private int fuelTime(net.minecraft.server.ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        } else
        {
            int i = itemstack.getItem().id;
            return i >= 256 || Block.byId[i].material != Material.WOOD ? i != Item.STICK.id ? i != Item.COAL.id ? i != Item.LAVA_BUCKET.id ? ((char) (i != Block.SAPLING.id ? '\0' : 'd')) : '\u4E20' : '\u0640' : 100 : 300;
        }
    }

    public boolean a(EntityHuman entityhuman)
    {
        return world.getTileEntity(x, y, z) == this ? entityhuman.e((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D) <= 64D : false;
    }

    public void e()
    {
    }

    public void t_()
    {
    }

    private net.minecraft.server.ItemStack items[];
    public int burnTime;
    public int ticksForCurrentFuel;
    public int cookTime;
    private int lastTick;
}
