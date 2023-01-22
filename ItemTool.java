// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, EnumToolMaterial, ItemStack, Block, 
//            EntityLiving, Entity

public class ItemTool extends Item
{

    protected ItemTool(int i, int j, EnumToolMaterial enumtoolmaterial, Block ablock[])
    {
        super(i);
        bu = 4F;
        a = enumtoolmaterial;
        bt = ablock;
        maxStackSize = 1;
        d(enumtoolmaterial.a());
        bu = enumtoolmaterial.b();
        bv = j + enumtoolmaterial.c();
    }

    public float a(ItemStack itemstack, Block block)
    {
        for(int i = 0; i < bt.length; i++)
            if(bt[i] == block)
                return bu;

        return 1.0F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damage(2, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damage(1, entityliving);
        return true;
    }

    public int a(Entity entity)
    {
        return bv;
    }

    private Block bt[];
    private float bu;
    private int bv;
    protected EnumToolMaterial a;
}
