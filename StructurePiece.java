// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructureBoundingBox, World, Block, Material, 
//            StructurePIeceBlockSelector, TileEntityChest, WeightedRandom, StructurePieceTreasure, 
//            Item, ItemStack, ItemDoor

public abstract class StructurePiece
{

    protected StructurePiece(int j)
    {
        i = j;
        h = -1;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public abstract boolean a(World world, Random random, StructureBoundingBox structureboundingbox);

    public StructureBoundingBox b()
    {
        return g;
    }

    public int c()
    {
        return i;
    }

    public static StructurePiece a(List list, StructureBoundingBox structureboundingbox)
    {
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            StructurePiece structurepiece = (StructurePiece)iterator.next();
            if(structurepiece.b() != null && structurepiece.b().a(structureboundingbox))
                return structurepiece;
        }

        return null;
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox)
    {
        int j = Math.max(g.a - 1, structureboundingbox.a);
        int k = Math.max(g.b - 1, structureboundingbox.b);
        int l = Math.max(g.c - 1, structureboundingbox.c);
        int i1 = Math.min(g.d + 1, structureboundingbox.d);
        int j1 = Math.min(g.e + 1, structureboundingbox.e);
        int k1 = Math.min(g.f + 1, structureboundingbox.f);
        for(int l1 = j; l1 <= i1; l1++)
        {
            for(int k2 = l; k2 <= k1; k2++)
            {
                int j3 = world.getTypeId(l1, k, k2);
                if(j3 > 0 && Block.byId[j3].material.isLiquid())
                    return true;
                j3 = world.getTypeId(l1, j1, k2);
                if(j3 > 0 && Block.byId[j3].material.isLiquid())
                    return true;
            }

        }

        for(int i2 = j; i2 <= i1; i2++)
        {
            for(int l2 = k; l2 <= j1; l2++)
            {
                int k3 = world.getTypeId(i2, l2, l);
                if(k3 > 0 && Block.byId[k3].material.isLiquid())
                    return true;
                k3 = world.getTypeId(i2, l2, k1);
                if(k3 > 0 && Block.byId[k3].material.isLiquid())
                    return true;
            }

        }

        for(int j2 = l; j2 <= k1; j2++)
        {
            for(int i3 = k; i3 <= j1; i3++)
            {
                int l3 = world.getTypeId(j, i3, j2);
                if(l3 > 0 && Block.byId[l3].material.isLiquid())
                    return true;
                l3 = world.getTypeId(i1, i3, j2);
                if(l3 > 0 && Block.byId[l3].material.isLiquid())
                    return true;
            }

        }

        return false;
    }

    protected int a(int j, int k)
    {
        switch(h)
        {
        case 0: // '\0'
        case 2: // '\002'
            return g.a + j;

        case 1: // '\001'
            return g.d - k;

        case 3: // '\003'
            return g.a + k;
        }
        return j;
    }

    protected int a(int j)
    {
        if(h == -1)
            return j;
        else
            return j + g.b;
    }

    protected int b(int j, int k)
    {
        switch(h)
        {
        case 2: // '\002'
            return g.f - k;

        case 0: // '\0'
            return g.c + k;

        case 1: // '\001'
        case 3: // '\003'
            return g.c + j;
        }
        return k;
    }

    protected int c(int j, int k)
    {
        if(j == Block.RAILS.id)
        {
            if(h == 1 || h == 3)
                return k != 1 ? 1 : 0;
        } else
        if(j == Block.WOODEN_DOOR.id || j == Block.IRON_DOOR_BLOCK.id)
        {
            if(h == 0)
            {
                if(k == 0)
                    return 2;
                if(k == 2)
                    return 0;
            } else
            {
                if(h == 1)
                    return k + 1 & 3;
                if(h == 3)
                    return k + 3 & 3;
            }
        } else
        if(j == Block.COBBLESTONE_STAIRS.id || j == Block.WOOD_STAIRS.id)
        {
            if(h == 0)
            {
                if(k == 2)
                    return 3;
                if(k == 3)
                    return 2;
            } else
            if(h == 1)
            {
                if(k == 0)
                    return 2;
                if(k == 1)
                    return 3;
                if(k == 2)
                    return 0;
                if(k == 3)
                    return 1;
            } else
            if(h == 3)
            {
                if(k == 0)
                    return 2;
                if(k == 1)
                    return 3;
                if(k == 2)
                    return 1;
                if(k == 3)
                    return 0;
            }
        } else
        if(j == Block.LADDER.id)
        {
            if(h == 0)
            {
                if(k == 2)
                    return 3;
                if(k == 3)
                    return 2;
            } else
            if(h == 1)
            {
                if(k == 2)
                    return 4;
                if(k == 3)
                    return 5;
                if(k == 4)
                    return 2;
                if(k == 5)
                    return 3;
            } else
            if(h == 3)
            {
                if(k == 2)
                    return 5;
                if(k == 3)
                    return 4;
                if(k == 4)
                    return 2;
                if(k == 5)
                    return 3;
            }
        } else
        if(j == Block.STONE_BUTTON.id)
            if(h == 0)
            {
                if(k == 3)
                    return 4;
                if(k == 4)
                    return 3;
            } else
            if(h == 1)
            {
                if(k == 3)
                    return 1;
                if(k == 4)
                    return 2;
                if(k == 2)
                    return 3;
                if(k == 1)
                    return 4;
            } else
            if(h == 3)
            {
                if(k == 3)
                    return 2;
                if(k == 4)
                    return 1;
                if(k == 2)
                    return 3;
                if(k == 1)
                    return 4;
            }
        return k;
    }

    protected void a(World world, int j, int k, int l, int i1, int j1, StructureBoundingBox structureboundingbox)
    {
        int k1 = a(l, j1);
        int l1 = a(i1);
        int i2 = b(l, j1);
        if(!structureboundingbox.b(k1, l1, i2))
        {
            return;
        } else
        {
            world.setRawTypeIdAndData(k1, l1, i2, j, k);
            return;
        }
    }

    protected int a(World world, int j, int k, int l, StructureBoundingBox structureboundingbox)
    {
        int i1 = a(j, l);
        int j1 = a(k);
        int k1 = b(j, l);
        if(!structureboundingbox.b(i1, j1, k1))
            return 0;
        else
            return world.getTypeId(i1, j1, k1);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2, boolean flag)
    {
        for(int j2 = k; j2 <= j1; j2++)
        {
            for(int k2 = j; k2 <= i1; k2++)
            {
                for(int l2 = l; l2 <= k1; l2++)
                {
                    if(flag && a(world, k2, j2, l2, structureboundingbox) == 0)
                        continue;
                    if(j2 == k || j2 == j1 || k2 == j || k2 == i1 || l2 == l || l2 == k1)
                        a(world, l1, 0, k2, j2, l2, structureboundingbox);
                    else
                        a(world, i2, 0, k2, j2, l2, structureboundingbox);
                }

            }

        }

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int j, int k, int l, int i1, int j1, 
            int k1, boolean flag, Random random, StructurePIeceBlockSelector structurepieceblockselector)
    {
        for(int l1 = k; l1 <= j1; l1++)
        {
            for(int i2 = j; i2 <= i1; i2++)
            {
                for(int j2 = l; j2 <= k1; j2++)
                    if(!flag || a(world, i2, l1, j2, structureboundingbox) != 0)
                    {
                        structurepieceblockselector.a(random, i2, l1, j2, l1 == k || l1 == j1 || i2 == j || i2 == i1 || j2 == l || j2 == k1);
                        a(world, structurepieceblockselector.a(), structurepieceblockselector.b(), i2, l1, j2, structureboundingbox);
                    }

            }

        }

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int j, int k, int l, 
            int i1, int j1, int k1, int l1, int i2, boolean flag)
    {
        for(int j2 = k; j2 <= j1; j2++)
        {
            for(int k2 = j; k2 <= i1; k2++)
            {
                for(int l2 = l; l2 <= k1; l2++)
                {
                    if(random.nextFloat() > f || flag && a(world, k2, j2, l2, structureboundingbox) == 0)
                        continue;
                    if(j2 == k || j2 == j1 || k2 == j || k2 == i1 || l2 == l || l2 == k1)
                        a(world, l1, 0, k2, j2, l2, structureboundingbox);
                    else
                        a(world, i2, 0, k2, j2, l2, structureboundingbox);
                }

            }

        }

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, float f, int j, int k, int l, 
            int i1, int j1)
    {
        if(random.nextFloat() < f)
            a(world, i1, j1, j, k, l, structureboundingbox);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int j, int k, int l, int i1, int j1, 
            int k1, int l1, boolean flag)
    {
        float f = (i1 - j) + 1;
        float f1 = (j1 - k) + 1;
        float f2 = (k1 - l) + 1;
        float f3 = (float)j + f / 2.0F;
        float f4 = (float)l + f2 / 2.0F;
        for(int i2 = k; i2 <= j1; i2++)
        {
            float f5 = (float)(i2 - k) / f1;
            for(int j2 = j; j2 <= i1; j2++)
            {
                float f6 = ((float)j2 - f3) / (f * 0.5F);
                for(int k2 = l; k2 <= k1; k2++)
                {
                    float f7 = ((float)k2 - f4) / (f2 * 0.5F);
                    if(flag && a(world, j2, i2, k2, structureboundingbox) == 0)
                        continue;
                    float f8 = f6 * f6 + f5 * f5 + f7 * f7;
                    if(f8 <= 1.05F)
                        a(world, l1, 0, j2, i2, k2, structureboundingbox);
                }

            }

        }

    }

    protected void b(World world, int j, int k, int l, StructureBoundingBox structureboundingbox)
    {
        int i1 = a(j, l);
        int j1 = a(k);
        int k1 = b(j, l);
        if(!structureboundingbox.b(i1, j1, k1))
            return;
        do
        {
            if(world.isEmpty(i1, j1, k1))
                break;
            world.getClass();
            if(j1 >= 127)
                break;
            world.setRawTypeIdAndData(i1, j1, k1, 0, 0);
            j1++;
        } while(true);
    }

    protected void b(World world, int j, int k, int l, int i1, int j1, StructureBoundingBox structureboundingbox)
    {
        int k1 = a(l, j1);
        int l1 = a(i1);
        int i2 = b(l, j1);
        if(!structureboundingbox.b(k1, l1, i2))
            return;
        for(; (world.isEmpty(k1, l1, i2) || world.getMaterial(k1, l1, i2).isLiquid()) && l1 > 1; l1--)
            world.setRawTypeIdAndData(k1, l1, i2, j, k);

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int j, int k, int l, StructurePieceTreasure astructurepiecetreasure[], 
            int i1)
    {
        int j1 = a(j, l);
        int k1 = a(k);
        int l1 = b(j, l);
        if(structureboundingbox.b(j1, k1, l1) && world.getTypeId(j1, k1, l1) != Block.CHEST.id)
        {
            world.setTypeId(j1, k1, l1, Block.CHEST.id);
            TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(j1, k1, l1);
            if(tileentitychest != null)
                a(random, astructurepiecetreasure, tileentitychest, i1);
        }
    }

    private static void a(Random random, StructurePieceTreasure astructurepiecetreasure[], TileEntityChest tileentitychest, int j)
    {
        for(int k = 0; k < j; k++)
        {
            StructurePieceTreasure structurepiecetreasure = (StructurePieceTreasure)WeightedRandom.a(random, astructurepiecetreasure);
            int l = structurepiecetreasure.c + random.nextInt((structurepiecetreasure.e - structurepiecetreasure.c) + 1);
            if(Item.byId[structurepiecetreasure.a].getMaxStackSize() >= l)
            {
                tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.a, l, structurepiecetreasure.b));
                continue;
            }
            for(int i1 = 0; i1 < l; i1++)
                tileentitychest.setItem(random.nextInt(tileentitychest.getSize()), new ItemStack(structurepiecetreasure.a, 1, structurepiecetreasure.b));

        }

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, Random random, int j, int k, int l, int i1)
    {
        int j1 = a(j, l);
        int k1 = a(k);
        int l1 = b(j, l);
        if(structureboundingbox.b(j1, k1, l1))
            ItemDoor.a(world, j1, k1, l1, i1, Block.WOODEN_DOOR);
    }

    protected StructureBoundingBox g;
    protected int h;
    protected int i;
}
