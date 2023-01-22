// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, Block, BlockLeaves, BlockLongGrass, 
//            ItemStack, EntityLiving

public class ItemShears extends Item
{

    public ItemShears(int i)
    {
        super(i);
        c(1);
        d(238);
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        if(i == Block.LEAVES.id || i == Block.WEB.id || i == Block.LONG_GRASS.id || i == Block.VINE.id)
        {
            itemstack.damage(1, entityliving);
            return true;
        } else
        {
            return super.a(itemstack, i, j, k, l, entityliving);
        }
    }

    public boolean a(Block block)
    {
        return block.id == Block.WEB.id;
    }

    public float a(ItemStack itemstack, Block block)
    {
        if(block.id == Block.WEB.id || block.id == Block.LEAVES.id)
            return 15F;
        if(block.id == Block.WOOL.id)
            return 5F;
        else
            return super.a(itemstack, block);
    }
}
