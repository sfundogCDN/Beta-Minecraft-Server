// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemStack.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityHuman, Block, Item, StatisticList, 
//            NBTTagCompound, World, Entity, EntityLiving, 
//            EnumAnimation

public final class ItemStack
{

    public ItemStack(Block block)
    {
        this(block, 1);
    }

    public ItemStack(Block block, int i)
    {
        this(block.id, i, 0);
    }

    public ItemStack(Block block, int i, int j)
    {
        this(block.id, i, j);
    }

    public ItemStack(Item item)
    {
        this(item.id, 1, 0);
    }

    public ItemStack(Item item, int i)
    {
        this(item.id, i, 0);
    }

    public ItemStack(Item item, int i, int j)
    {
        this(item.id, i, j);
    }

    public ItemStack(int i, int j, int k)
    {
        count = 0;
        id = i;
        count = j;
        b(k);
    }

    public static ItemStack a(NBTTagCompound nbttagcompound)
    {
        ItemStack itemstack = new ItemStack();
        itemstack.c(nbttagcompound);
        return itemstack.getItem() == null ? null : itemstack;
    }

    private ItemStack()
    {
        count = 0;
    }

    public ItemStack a(int i)
    {
        count -= i;
        return new ItemStack(id, i, damage);
    }

    public Item getItem()
    {
        return Item.byId[id];
    }

    public boolean placeItem(EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        boolean flag = getItem().a(this, entityhuman, world, i, j, k, l);
        if(flag)
            entityhuman.a(StatisticList.E[id], 1);
        return flag;
    }

    public float a(Block block)
    {
        return getItem().a(this, block);
    }

    public ItemStack a(World world, EntityHuman entityhuman)
    {
        return getItem().a(this, world, entityhuman);
    }

    public ItemStack b(World world, EntityHuman entityhuman)
    {
        return getItem().b(this, world, entityhuman);
    }

    public NBTTagCompound b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("id", (short)id);
        nbttagcompound.a("Count", (byte)count);
        nbttagcompound.a("Damage", (short)damage);
        return nbttagcompound;
    }

    public void c(NBTTagCompound nbttagcompound)
    {
        id = nbttagcompound.d("id");
        count = nbttagcompound.c("Count");
        damage = nbttagcompound.d("Damage");
    }

    public int getMaxStackSize()
    {
        return getItem().getMaxStackSize();
    }

    public boolean isStackable()
    {
        return getMaxStackSize() > 1 && (!d() || !f());
    }

    public boolean d()
    {
        return Item.byId[id].getMaxDurability() > 0;
    }

    public boolean usesData()
    {
        return Item.byId[id].d();
    }

    public boolean f()
    {
        return d() && damage > 0;
    }

    public int g()
    {
        return damage;
    }

    public int getData()
    {
        return damage;
    }

    public void b(int i)
    {
        damage = id <= 0 || id >= 256 ? i : Item.byId[id].filterData(i);
    }

    public int i()
    {
        return Item.byId[id].getMaxDurability();
    }

    public void damage(int i, Entity entity)
    {
        if(d())
        {
            damage += i;
            if(damage > i())
            {
                if(entity instanceof EntityHuman)
                    ((EntityHuman)entity).a(StatisticList.F[id], 1);
                count--;
                if(count < 0)
                    count = 0;
                damage = 0;
            }
        }
    }

    public void a(EntityLiving entityliving, EntityHuman entityhuman)
    {
        boolean flag = Item.byId[id].a(this, entityliving, entityhuman);
        if(flag)
            entityhuman.a(StatisticList.E[id], 1);
    }

    public void a(int i, int j, int k, int l, EntityHuman entityhuman)
    {
        boolean flag = Item.byId[id].a(this, i, j, k, l, entityhuman);
        if(flag)
            entityhuman.a(StatisticList.E[id], 1);
    }

    public int a(Entity entity)
    {
        return Item.byId[id].a(entity);
    }

    public boolean b(Block block)
    {
        return Item.byId[id].a(block);
    }

    public void a(EntityHuman entityhuman1)
    {
    }

    public void a(EntityLiving entityliving)
    {
        Item.byId[id].a(this, entityliving);
    }

    public ItemStack cloneItemStack()
    {
        return new ItemStack(id, count, damage);
    }

    public static boolean equals(ItemStack itemstack, ItemStack itemstack1)
    {
        return itemstack != null || itemstack1 != null ? itemstack == null || itemstack1 == null ? false : itemstack.d(itemstack1) : true;
    }

    private boolean d(ItemStack itemstack)
    {
        return count == itemstack.count ? id == itemstack.id ? damage == itemstack.damage : false : false;
    }

    public boolean doMaterialsMatch(ItemStack itemstack)
    {
        return id == itemstack.id && damage == itemstack.damage;
    }

    public String k()
    {
        return Item.byId[id].a(this);
    }

    public static ItemStack b(ItemStack itemstack)
    {
        return itemstack != null ? itemstack.cloneItemStack() : null;
    }

    public String toString()
    {
        return (new StringBuilder()).append(count).append("x").append(Item.byId[id].b()).append("@").append(damage).toString();
    }

    public void a(World world, Entity entity, int i, boolean flag)
    {
        if(b > 0)
            b--;
        Item.byId[id].a(this, world, entity, i, flag);
    }

    public void c(World world, EntityHuman entityhuman)
    {
        entityhuman.a(StatisticList.D[id], count);
        Item.byId[id].d(this, world, entityhuman);
    }

    public boolean c(ItemStack itemstack)
    {
        return id == itemstack.id && count == itemstack.count && damage == itemstack.damage;
    }

    public int l()
    {
        return getItem().c(this);
    }

    public EnumAnimation m()
    {
        return getItem().b(this);
    }

    public void a(World world, EntityHuman entityhuman, int i)
    {
        getItem().a(this, world, entityhuman, i);
    }

    public int count;
    public int b;
    public int id;
    private int damage;
}
