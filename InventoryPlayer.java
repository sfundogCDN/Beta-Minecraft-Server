// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InventoryPlayer.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, NBTTagCompound, ItemArmor, IInventory, 
//            EntityHuman, NBTTagList, Block, Material, 
//            Entity

public class InventoryPlayer
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public ItemStack[] getArmorContents()
    {
        return armor;
    }

    public InventoryPlayer(EntityHuman entityhuman)
    {
        items = new ItemStack[36];
        armor = new ItemStack[4];
        itemInHandIndex = 0;
        e = false;
        d = entityhuman;
    }

    public ItemStack getItemInHand()
    {
        return itemInHandIndex >= 9 || itemInHandIndex < 0 ? null : items[itemInHandIndex];
    }

    public static int g()
    {
        return 9;
    }

    private int e(int i)
    {
        for(int j = 0; j < items.length; j++)
            if(items[j] != null && items[j].id == i)
                return j;

        return -1;
    }

    private int firstPartial(ItemStack itemstack)
    {
        for(int i = 0; i < items.length; i++)
            if(items[i] != null && items[i].id == itemstack.id && items[i].isStackable() && items[i].count < items[i].getMaxStackSize() && items[i].count < getMaxStackSize() && (!items[i].usesData() || items[i].getData() == itemstack.getData()))
                return i;

        return -1;
    }

    public int canHold(ItemStack itemstack)
    {
        int remains = itemstack.count;
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] == null)
                return itemstack.count;
            if(items[i] != null && items[i].id == itemstack.id && items[i].isStackable() && items[i].count < items[i].getMaxStackSize() && items[i].count < getMaxStackSize() && (!items[i].usesData() || items[i].getData() == itemstack.getData()))
                remains -= (items[i].getMaxStackSize() >= getMaxStackSize() ? getMaxStackSize() : items[i].getMaxStackSize()) - items[i].count;
            if(remains <= 0)
                return itemstack.count;
        }

        return itemstack.count - remains;
    }

    private int m()
    {
        for(int i = 0; i < items.length; i++)
            if(items[i] == null)
                return i;

        return -1;
    }

    private int e(ItemStack itemstack)
    {
        int i = itemstack.id;
        int j = itemstack.count;
        int k = firstPartial(itemstack);
        if(k < 0)
            k = m();
        if(k < 0)
            return j;
        if(items[k] == null)
            items[k] = new ItemStack(i, 0, itemstack.getData());
        int l = j;
        if(j > items[k].getMaxStackSize() - items[k].count)
            l = items[k].getMaxStackSize() - items[k].count;
        if(l > getMaxStackSize() - items[k].count)
            l = getMaxStackSize() - items[k].count;
        if(l == 0)
        {
            return j;
        } else
        {
            j -= l;
            items[k].count += l;
            items[k].b = 5;
            return j;
        }
    }

    public void h()
    {
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
                items[i].a(d.world, d, i, itemInHandIndex == i);

    }

    public boolean b(int i)
    {
        int j = e(i);
        if(j < 0)
            return false;
        if(--items[j].count <= 0)
            items[j] = null;
        return true;
    }

    public boolean c(int i)
    {
        int j = e(i);
        return j >= 0;
    }

    public boolean pickup(ItemStack itemstack)
    {
        int i;
        if(itemstack.f())
        {
            i = m();
            if(i >= 0)
            {
                items[i] = ItemStack.b(itemstack);
                items[i].b = 5;
                itemstack.count = 0;
                return true;
            } else
            {
                return false;
            }
        }
        do
        {
            i = itemstack.count;
            itemstack.count = e(itemstack);
        } while(itemstack.count > 0 && itemstack.count < i);
        return itemstack.count < i;
    }

    public ItemStack splitStack(int i, int j)
    {
        ItemStack aitemstack[] = items;
        if(i >= items.length)
        {
            aitemstack = armor;
            i -= items.length;
        }
        if(aitemstack[i] != null)
        {
            ItemStack itemstack;
            if(aitemstack[i].count <= j)
            {
                itemstack = aitemstack[i];
                aitemstack[i] = null;
                return itemstack;
            }
            itemstack = aitemstack[i].a(j);
            if(aitemstack[i].count == 0)
                aitemstack[i] = null;
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack)
    {
        ItemStack aitemstack[] = items;
        if(i >= aitemstack.length)
        {
            i -= aitemstack.length;
            aitemstack = armor;
        }
        aitemstack[i] = itemstack;
    }

    public float a(Block block)
    {
        float f = 1.0F;
        if(items[itemInHandIndex] != null)
            f *= items[itemInHandIndex].a(block);
        return f;
    }

    public NBTTagList a(NBTTagList nbttaglist)
    {
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte)i);
                items[i].b(nbttagcompound);
                nbttaglist.a(nbttagcompound);
            }

        for(int i = 0; i < armor.length; i++)
            if(armor[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte)(i + 100));
                armor[i].b(nbttagcompound);
                nbttaglist.a(nbttagcompound);
            }

        return nbttaglist;
    }

    public void b(NBTTagList nbttaglist)
    {
        items = new ItemStack[36];
        armor = new ItemStack[4];
        for(int i = 0; i < nbttaglist.c(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.a(i);
            int j = nbttagcompound.c("Slot") & 0xff;
            ItemStack itemstack = ItemStack.a(nbttagcompound);
            if(itemstack == null)
                continue;
            if(j >= 0 && j < items.length)
                items[j] = itemstack;
            if(j >= 100 && j < armor.length + 100)
                armor[j - 100] = itemstack;
        }

    }

    public int getSize()
    {
        return items.length + 4;
    }

    public ItemStack getItem(int i)
    {
        ItemStack aitemstack[] = items;
        if(i >= aitemstack.length)
        {
            i -= aitemstack.length;
            aitemstack = armor;
        }
        return aitemstack[i];
    }

    public String getName()
    {
        return "Inventory";
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public int a(Entity entity)
    {
        ItemStack itemstack = getItem(itemInHandIndex);
        return itemstack == null ? 1 : itemstack.a(entity);
    }

    public boolean b(Block block)
    {
        if(block.material.k())
        {
            return true;
        } else
        {
            ItemStack itemstack = getItem(itemInHandIndex);
            return itemstack == null ? false : itemstack.b(block);
        }
    }

    public int i()
    {
        int i = 0;
        int j = 0;
        int k = 0;
        for(int l = 0; l < armor.length; l++)
            if(armor[l] != null && (armor[l].getItem() instanceof ItemArmor))
            {
                int i1 = armor[l].i();
                int j1 = armor[l].g();
                int k1 = i1 - j1;
                j += k1;
                k += i1;
                int l1 = ((ItemArmor)armor[l].getItem()).bu;
                i += l1;
            }

        if(k == 0)
            return 0;
        else
            return ((i - 1) * j) / k + 1;
    }

    public void d(int i)
    {
        for(int j = 0; j < armor.length; j++)
        {
            if(armor[j] == null || !(armor[j].getItem() instanceof ItemArmor))
                continue;
            armor[j].damage(i, d);
            if(armor[j].count == 0)
            {
                armor[j].a(d);
                armor[j] = null;
            }
        }

    }

    public void j()
    {
        for(int i = 0; i < items.length; i++)
            if(items[i] != null)
            {
                d.a(items[i], true);
                items[i] = null;
            }

        for(int i = 0; i < armor.length; i++)
            if(armor[i] != null)
            {
                d.a(armor[i], true);
                armor[i] = null;
            }

    }

    public void update()
    {
        e = true;
    }

    public void b(ItemStack itemstack)
    {
        f = itemstack;
        d.a(itemstack);
    }

    public ItemStack l()
    {
        return f;
    }

    public boolean a(EntityHuman entityhuman)
    {
        return d.dead ? false : entityhuman.h(d) <= 64D;
    }

    public boolean c(ItemStack itemstack)
    {
        for(int i = 0; i < armor.length; i++)
            if(armor[i] != null && armor[i].c(itemstack))
                return true;

        for(int i = 0; i < items.length; i++)
            if(items[i] != null && items[i].c(itemstack))
                return true;

        return false;
    }

    public void e()
    {
    }

    public void t_()
    {
    }

    public ItemStack items[];
    public ItemStack armor[];
    public int itemInHandIndex;
    public EntityHuman d;
    private ItemStack f;
    public boolean e;
}
