// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenerator, World, Material, Block, 
//            TileEntityChest, TileEntityMobSpawner, ItemStack, Item

public class WorldGenDungeons extends WorldGenerator
{

    public WorldGenDungeons()
    {
    }

    public boolean a(World world, Random random, int i, int j, int k)
    {
        byte byte0 = 3;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        int j1 = 0;
        for(int k1 = i - l - 1; k1 <= i + l + 1; k1++)
        {
            for(int j2 = j - 1; j2 <= j + byte0 + 1; j2++)
            {
                for(int i3 = k - i1 - 1; i3 <= k + i1 + 1; i3++)
                {
                    Material material = world.getMaterial(k1, j2, i3);
                    if(j2 == j - 1 && !material.isBuildable())
                        return false;
                    if(j2 == j + byte0 + 1 && !material.isBuildable())
                        return false;
                    if((k1 == i - l - 1 || k1 == i + l + 1 || i3 == k - i1 - 1 || i3 == k + i1 + 1) && j2 == j && world.isEmpty(k1, j2, i3) && world.isEmpty(k1, j2 + 1, i3))
                        j1++;
                }

            }

        }

        if(j1 < 1 || j1 > 5)
            return false;
        for(int l1 = i - l - 1; l1 <= i + l + 1; l1++)
        {
            for(int k2 = j + byte0; k2 >= j - 1; k2--)
            {
                for(int j3 = k - i1 - 1; j3 <= k + i1 + 1; j3++)
                    if(l1 == i - l - 1 || k2 == j - 1 || j3 == k - i1 - 1 || l1 == i + l + 1 || k2 == j + byte0 + 1 || j3 == k + i1 + 1)
                    {
                        if(k2 >= 0 && !world.getMaterial(l1, k2 - 1, j3).isBuildable())
                        {
                            world.setTypeId(l1, k2, j3, 0);
                            continue;
                        }
                        if(!world.getMaterial(l1, k2, j3).isBuildable())
                            continue;
                        if(k2 == j - 1 && random.nextInt(4) != 0)
                            world.setTypeId(l1, k2, j3, Block.MOSSY_COBBLESTONE.id);
                        else
                            world.setTypeId(l1, k2, j3, Block.COBBLESTONE.id);
                    } else
                    {
                        world.setTypeId(l1, k2, j3, 0);
                    }

            }

        }

        for(int i2 = 0; i2 < 2; i2++)
        {
label0:
            for(int l2 = 0; l2 < 3; l2++)
            {
                int k3 = (i + random.nextInt(l * 2 + 1)) - l;
                int l3 = j;
                int i4 = (k + random.nextInt(i1 * 2 + 1)) - i1;
                if(!world.isEmpty(k3, l3, i4))
                    continue;
                int j4 = 0;
                if(world.getMaterial(k3 - 1, l3, i4).isBuildable())
                    j4++;
                if(world.getMaterial(k3 + 1, l3, i4).isBuildable())
                    j4++;
                if(world.getMaterial(k3, l3, i4 - 1).isBuildable())
                    j4++;
                if(world.getMaterial(k3, l3, i4 + 1).isBuildable())
                    j4++;
                if(j4 != 1)
                    continue;
                world.setTypeId(k3, l3, i4, Block.CHEST.id);
                TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(k3, l3, i4);
                if(tileentitychest == null)
                    break;
                int k4 = 0;
                do
                {
                    if(k4 >= 8)
                        break label0;
                    ItemStack itemstack = a(random);
                    if(itemstack != null)
                        tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), itemstack);
                    k4++;
                } while(true);
            }

        }

        world.setTypeId(i, j, k, Block.MOB_SPAWNER.id);
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(i, j, k);
        tileentitymobspawner.a(b(random));
        return true;
    }

    private ItemStack a(Random random)
    {
        int i = random.nextInt(11);
        if(i == 0)
            return new ItemStack(Item.SADDLE);
        if(i == 1)
            return new ItemStack(Item.IRON_INGOT, random.nextInt(4) + 1);
        if(i == 2)
            return new ItemStack(Item.BREAD);
        if(i == 3)
            return new ItemStack(Item.WHEAT, random.nextInt(4) + 1);
        if(i == 4)
            return new ItemStack(Item.SULPHUR, random.nextInt(4) + 1);
        if(i == 5)
            return new ItemStack(Item.STRING, random.nextInt(4) + 1);
        if(i == 6)
            return new ItemStack(Item.BUCKET);
        if(i == 7 && random.nextInt(100) == 0)
            return new ItemStack(Item.GOLDEN_APPLE);
        if(i == 8 && random.nextInt(2) == 0)
            return new ItemStack(Item.REDSTONE, random.nextInt(4) + 1);
        if(i == 9 && random.nextInt(10) == 0)
            return new ItemStack(Item.byId[Item.GOLD_RECORD.id + random.nextInt(2)]);
        if(i == 10)
            return new ItemStack(Item.INK_SACK, 1, 3);
        else
            return null;
    }

    private String b(Random random)
    {
        int i = random.nextInt(4);
        if(i == 0)
            return "Skeleton";
        if(i == 1)
            return "Zombie";
        if(i == 2)
            return "Zombie";
        if(i == 3)
            return "Spider";
        else
            return "";
    }
}
