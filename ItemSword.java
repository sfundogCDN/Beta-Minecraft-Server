// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, EnumToolMaterial, Block, ItemStack, 
//            EnumAnimation, EntityHuman, EntityLiving, Entity, 
//            World

public class ItemSword extends Item
{

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i);
        maxStackSize = 1;
        d(enumtoolmaterial.a());
        a = 4 + enumtoolmaterial.c() * 2;
    }

    public float a(ItemStack itemstack, Block block)
    {
        return block.id != Block.WEB.id ? 1.5F : 15F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damage(1, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damage(2, entityliving);
        return true;
    }

    public int a(Entity entity)
    {
        return a;
    }

    public EnumAnimation b(ItemStack itemstack)
    {
        return EnumAnimation.c;
    }

    public int c(ItemStack itemstack)
    {
        return 0x11940;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        entityhuman.a(itemstack, c(itemstack));
        return itemstack;
    }

    public boolean a(Block block)
    {
        return block.id == Block.WEB.id;
    }

    private int a;
}
