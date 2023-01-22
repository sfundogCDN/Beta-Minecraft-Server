// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Item, ItemStack, EntityHuman, World, 
//            Block, BlockSapling, BlockFlower, BlockMushroom, 
//            BlockStem, BlockCrops, BlockGrass, BlockLongGrass, 
//            EntitySheep, BlockCloth, EntityLiving

public class ItemDye extends Item
{

    public ItemDye(int i)
    {
        super(i);
        a(true);
        d(0);
    }

    public String a(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.b()).append(".").append(a[itemstack.getData()]).toString();
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(!entityhuman.c(i, j, k))
            return false;
        if(itemstack.getData() == 15)
        {
            int i1 = world.getTypeId(i, j, k);
            if(i1 == Block.SAPLING.id)
            {
                if(!world.isStatic)
                {
                    ((BlockSapling)Block.SAPLING).b(world, i, j, k, world.random);
                    itemstack.count--;
                }
                return true;
            }
            if(i1 == Block.BROWN_MUSHROOM.id || i1 == Block.RED_MUSHROOM.id)
            {
                if(!world.isStatic && ((BlockMushroom)Block.byId[i1]).b(world, i, j, k, world.random))
                    itemstack.count--;
                return true;
            }
            if(i1 == Block.MELON_STEM.id || i1 == Block.PUMPKIN_STEM.id)
            {
                if(!world.isStatic)
                {
                    ((BlockStem)Block.byId[i1]).f_(world, i, j, k);
                    itemstack.count--;
                }
                return true;
            }
            if(i1 == Block.CROPS.id)
            {
                if(!world.isStatic)
                {
                    ((BlockCrops)Block.CROPS).e_(world, i, j, k);
                    itemstack.count--;
                }
                return true;
            }
            if(i1 == Block.GRASS.id)
            {
                if(!world.isStatic)
                {
                    itemstack.count--;
label0:
                    for(int j1 = 0; j1 < 128; j1++)
                    {
                        int k1 = i;
                        int l1 = j + 1;
                        int i2 = k;
                        for(int j2 = 0; j2 < j1 / 16; j2++)
                        {
                            k1 += b.nextInt(3) - 1;
                            l1 += ((b.nextInt(3) - 1) * b.nextInt(3)) / 2;
                            i2 += b.nextInt(3) - 1;
                            if(world.getTypeId(k1, l1 - 1, i2) != Block.GRASS.id || world.e(k1, l1, i2))
                                continue label0;
                        }

                        if(world.getTypeId(k1, l1, i2) != 0)
                            continue;
                        if(b.nextInt(10) != 0)
                        {
                            world.setTypeIdAndData(k1, l1, i2, Block.LONG_GRASS.id, 1);
                            continue;
                        }
                        if(b.nextInt(3) != 0)
                            world.setTypeId(k1, l1, i2, Block.YELLOW_FLOWER.id);
                        else
                            world.setTypeId(k1, l1, i2, Block.RED_ROSE.id);
                    }

                }
                return true;
            }
        }
        return false;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving)
    {
        if(entityliving instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)entityliving;
            int i = BlockCloth.c(itemstack.getData());
            if(!entitysheep.isSheared() && entitysheep.getColor() != i)
            {
                entitysheep.setColor(i);
                itemstack.count--;
            }
        }
    }

    public static final String a[] = {
        "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", 
        "lime", "yellow", "lightBlue", "magenta", "orange", "white"
    };
    public static final int bt[] = {
        0x1e1b1b, 0xb3312c, 0x3b511a, 0x51301a, 0x253192, 0x7b2fbe, 0x287697, 0x287697, 0x434343, 0xd88198, 
        0x41cd34, 0xdecf2a, 0x6689d3, 0xc354cd, 0xeb8844, 0xf0f0f0
    };

}
