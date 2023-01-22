// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, IBlockAccess, World, 
//            Direction, EntityHuman, ItemStack, Item, 
//            ItemShears, StatisticList, AxisAlignedBB

public class BlockVine extends Block
{

    public BlockVine(int i)
    {
        super(i, 143, Material.REPLACEABLE_PLANT);
        a(true);
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k);
        float f = 1.0F;
        float f1 = 1.0F;
        float f2 = 1.0F;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        boolean flag = l > 0;
        if((l & 2) != 0)
        {
            f3 = Math.max(f3, 0.0625F);
            f = 0.0F;
            f1 = 0.0F;
            f4 = 1.0F;
            f2 = 0.0F;
            f5 = 1.0F;
            flag = true;
        }
        if((l & 8) != 0)
        {
            f = Math.min(f, 0.9375F);
            f3 = 1.0F;
            f1 = 0.0F;
            f4 = 1.0F;
            f2 = 0.0F;
            f5 = 1.0F;
            flag = true;
        }
        if((l & 4) != 0)
        {
            f5 = Math.max(f5, 0.0625F);
            f2 = 0.0F;
            f = 0.0F;
            f3 = 1.0F;
            f1 = 0.0F;
            f4 = 1.0F;
            flag = true;
        }
        if((l & 1) != 0)
        {
            f2 = Math.min(f2, 0.9375F);
            f5 = 1.0F;
            f = 0.0F;
            f3 = 1.0F;
            f1 = 0.0F;
            f4 = 1.0F;
            flag = true;
        }
        if(!flag && c(iblockaccess.getTypeId(i, j + 1, k)))
        {
            f1 = Math.min(f1, 0.9375F);
            f4 = 1.0F;
            f = 0.0F;
            f3 = 1.0F;
            f2 = 0.0F;
            f5 = 1.0F;
        }
        a(f, f1, f2, f3, f4, f5);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean canPlace(World world, int i, int j, int k, int l)
    {
        switch(l)
        {
        default:
            return false;

        case 1: // '\001'
            return c(world.getTypeId(i, j + 1, k));

        case 2: // '\002'
            return c(world.getTypeId(i, j, k + 1));

        case 3: // '\003'
            return c(world.getTypeId(i, j, k - 1));

        case 5: // '\005'
            return c(world.getTypeId(i - 1, j, k));

        case 4: // '\004'
            return c(world.getTypeId(i + 1, j, k));
        }
    }

    private boolean c(int i)
    {
        if(i == 0)
            return false;
        Block block = Block.byId[i];
        return block.b() && block.material.isSolid();
    }

    private boolean g(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        int i1 = l;
        if(i1 > 0)
        {
            for(int j1 = 0; j1 <= 3; j1++)
            {
                int k1 = 1 << j1;
                if((l & k1) != 0 && !c(world.getTypeId(i + Direction.a[j1], j, k + Direction.b[j1])) && (world.getTypeId(i, j + 1, k) != id || (world.getData(i, j + 1, k) & k1) == 0))
                    i1 &= ~k1;
            }

        }
        if(i1 == 0 && !c(world.getTypeId(i, j + 1, k)))
            return false;
        if(i1 != l)
            world.setData(i, j, k, i1);
        return true;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!world.isStatic && !g(world, i, j, k))
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public void a(World world, int i, int j, int k, Random random)
    {
label0:
        {
            if(world.isStatic || world.random.nextInt(16) != 0)
                break label0;
            byte byte0 = 4;
            int l = 5;
            boolean flag = false;
            int i1 = i - byte0;
label1:
            do
            {
                if(i1 > i + byte0)
                    break;
label2:
                for(int j1 = k - byte0; j1 <= k + byte0; j1++)
                {
                    int l1 = j - 1;
                    do
                    {
                        if(l1 > j + 1)
                            continue label2;
                        if(world.getTypeId(i1, l1, j1) == id && --l <= 0)
                        {
                            flag = true;
                            break label1;
                        }
                        l1++;
                    } while(true);
                }

                i1++;
            } while(true);
            i1 = world.getData(i, j, k);
            int k1 = world.random.nextInt(6);
            int i2 = Direction.d[k1];
            if(k1 == 1)
            {
                world.getClass();
                if(j < 128 - 1 && world.isEmpty(i, j + 1, k))
                {
                    if(flag)
                        return;
                    int j2 = world.random.nextInt(16) & i1;
                    if(j2 > 0)
                    {
                        for(int i3 = 0; i3 <= 3; i3++)
                            if(!c(world.getTypeId(i + Direction.a[i3], j + 1, k + Direction.b[i3])))
                                j2 &= ~(1 << i3);

                        if(j2 > 0)
                            world.setTypeIdAndData(i, j + 1, k, id, j2);
                    }
                    break label0;
                }
            }
            if(k1 >= 2 && k1 <= 5 && (i1 & 1 << i2) == 0)
            {
                if(flag)
                    return;
                int k2 = world.getTypeId(i + Direction.a[i2], j, k + Direction.b[i2]);
                if(k2 == 0 || Block.byId[k2] == null)
                {
                    int j3 = i2 + 1 & 3;
                    int i4 = i2 + 3 & 3;
                    if((i1 & 1 << j3) != 0 && c(world.getTypeId(i + Direction.a[i2] + Direction.a[j3], j, k + Direction.b[i2] + Direction.b[j3])))
                        world.setTypeIdAndData(i + Direction.a[i2], j, k + Direction.b[i2], id, 1 << j3);
                    else
                    if((i1 & 1 << i4) != 0 && c(world.getTypeId(i + Direction.a[i2] + Direction.a[i4], j, k + Direction.b[i2] + Direction.b[i4])))
                        world.setTypeIdAndData(i + Direction.a[i2], j, k + Direction.b[i2], id, 1 << i4);
                    else
                    if((i1 & 1 << j3) != 0 && world.isEmpty(i + Direction.a[i2] + Direction.a[j3], j, k + Direction.b[i2] + Direction.b[j3]) && c(world.getTypeId(i + Direction.a[j3], j, k + Direction.b[j3])))
                        world.setTypeIdAndData(i + Direction.a[i2] + Direction.a[j3], j, k + Direction.b[i2] + Direction.b[j3], id, 1 << (i2 + 2 & 3));
                    else
                    if((i1 & 1 << i4) != 0 && world.isEmpty(i + Direction.a[i2] + Direction.a[i4], j, k + Direction.b[i2] + Direction.b[i4]) && c(world.getTypeId(i + Direction.a[i4], j, k + Direction.b[i4])))
                        world.setTypeIdAndData(i + Direction.a[i2] + Direction.a[i4], j, k + Direction.b[i2] + Direction.b[i4], id, 1 << (i2 + 2 & 3));
                    else
                    if(c(world.getTypeId(i + Direction.a[i2], j + 1, k + Direction.b[i2])))
                        world.setTypeIdAndData(i + Direction.a[i2], j, k + Direction.b[i2], id, 0);
                } else
                if(Block.byId[k2].material.j() && Block.byId[k2].b())
                    world.setData(i, j, k, i1 | 1 << i2);
            } else
            if(j > 1)
            {
                int l2 = world.getTypeId(i, j - 1, k);
                if(l2 == 0)
                {
                    int k3 = world.random.nextInt(16) & i1;
                    if(k3 > 0)
                        world.setTypeIdAndData(i, j - 1, k, id, k3);
                } else
                if(l2 == id)
                {
                    int l3 = world.random.nextInt(16) & i1;
                    int j4 = world.getData(i, j - 1, k);
                    if(j4 != (j4 | l3))
                        world.setData(i, j - 1, k, j4 | l3);
                }
            }
        }
    }

    public void postPlace(World world, int i, int j, int k, int l)
    {
        byte byte0 = 0;
        switch(l)
        {
        case 2: // '\002'
            byte0 = 1;
            break;

        case 3: // '\003'
            byte0 = 4;
            break;

        case 4: // '\004'
            byte0 = 8;
            break;

        case 5: // '\005'
            byte0 = 2;
            break;
        }
        if(byte0 != 0)
            world.setData(i, j, k, byte0);
    }

    public int a(int i, Random random)
    {
        return 0;
    }

    public int a(Random random)
    {
        return 0;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
    {
        if(!world.isStatic && entityhuman.K() != null && entityhuman.K().id == Item.SHEARS.id)
        {
            entityhuman.a(StatisticList.C[id], 1);
            a(world, i, j, k, new ItemStack(Block.VINE, 1, l));
        } else
        {
            super.a(world, entityhuman, i, j, k, l);
        }
    }
}
