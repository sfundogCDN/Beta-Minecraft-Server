// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IInventory.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemStack, EntityHuman

public interface IInventory
{

    public abstract int getSize();

    public abstract ItemStack getItem(int i);

    public abstract ItemStack splitStack(int i, int j);

    public abstract void setItem(int i, ItemStack itemstack);

    public abstract String getName();

    public abstract int getMaxStackSize();

    public abstract void update();

    public abstract boolean a(EntityHuman entityhuman);

    public abstract void e();

    public abstract void t_();

    public abstract ItemStack[] getContents();
}
