// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, World, Material, IBlockAccess, 
//            MinecartTrackLogic, AxisAlignedBB, Vec3D, MovingObjectPosition

public class BlockMinecartTrack extends Block
{

    public static final boolean g(World world, int i, int j, int k)
    {
        int l = world.getTypeId(i, j, k);
        return l == Block.RAILS.id || l == Block.GOLDEN_RAIL.id || l == Block.DETECTOR_RAIL.id;
    }

    public static final boolean c(int i)
    {
        return i == Block.RAILS.id || i == Block.GOLDEN_RAIL.id || i == Block.DETECTOR_RAIL.id;
    }

    protected BlockMinecartTrack(int i, int j, boolean flag)
    {
        super(i, j, Material.ORIENTABLE);
        a = flag;
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean f()
    {
        return a;
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean a()
    {
        return false;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        a(((IBlockAccess) (world)), i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k);
        if(l >= 2 && l <= 5)
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        else
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public int a(int i, int j)
    {
        if(a)
        {
            if(id == Block.GOLDEN_RAIL.id && (j & 8) == 0)
                return textureId - 16;
        } else
        if(j >= 6)
            return textureId - 16;
        return textureId;
    }

    public boolean b()
    {
        return false;
    }

    public int a(Random random)
    {
        return 1;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return world.e(i, j - 1, k);
    }

    public void a(World world, int i, int j, int k)
    {
        if(!world.isStatic)
            a(world, i, j, k, true);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(world.isStatic)
            return;
        int i1 = world.getData(i, j, k);
        int j1 = i1;
        if(a)
            j1 &= 7;
        boolean flag = false;
        if(!world.e(i, j - 1, k))
            flag = true;
        if(j1 == 2 && !world.e(i + 1, j, k))
            flag = true;
        if(j1 == 3 && !world.e(i - 1, j, k))
            flag = true;
        if(j1 == 4 && !world.e(i, j, k - 1))
            flag = true;
        if(j1 == 5 && !world.e(i, j, k + 1))
            flag = true;
        if(flag)
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        } else
        if(id == Block.GOLDEN_RAIL.id)
        {
            boolean flag1 = world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k);
            flag1 = flag1 || a(world, i, j, k, i1, true, 0) || a(world, i, j, k, i1, false, 0);
            boolean flag2 = false;
            if(flag1 && (i1 & 8) == 0)
            {
                world.setData(i, j, k, j1 | 8);
                flag2 = true;
            } else
            if(!flag1 && (i1 & 8) != 0)
            {
                world.setData(i, j, k, j1);
                flag2 = true;
            }
            if(flag2)
            {
                world.applyPhysics(i, j - 1, k, id);
                if(j1 == 2 || j1 == 3 || j1 == 4 || j1 == 5)
                    world.applyPhysics(i, j + 1, k, id);
            }
        } else
        if(l > 0 && Block.byId[l].isPowerSource() && !a && MinecartTrackLogic.a(new MinecartTrackLogic(this, world, i, j, k)) == 3)
            a(world, i, j, k, false);
    }

    private void a(World world, int i, int j, int k, boolean flag)
    {
        if(world.isStatic)
        {
            return;
        } else
        {
            (new MinecartTrackLogic(this, world, i, j, k)).a(world.isBlockIndirectlyPowered(i, j, k), flag);
            return;
        }
    }

    private boolean a(World world, int i, int j, int k, int l, boolean flag, int i1)
    {
        if(i1 >= 8)
            return false;
        int j1 = l & 7;
        boolean flag1 = true;
        switch(j1)
        {
        case 0: // '\0'
            if(flag)
                k++;
            else
                k--;
            break;

        case 1: // '\001'
            if(flag)
                i--;
            else
                i++;
            break;

        case 2: // '\002'
            if(flag)
            {
                i--;
            } else
            {
                i++;
                j++;
                flag1 = false;
            }
            j1 = 1;
            break;

        case 3: // '\003'
            if(flag)
            {
                i--;
                j++;
                flag1 = false;
            } else
            {
                i++;
            }
            j1 = 1;
            break;

        case 4: // '\004'
            if(flag)
            {
                k++;
            } else
            {
                k--;
                j++;
                flag1 = false;
            }
            j1 = 0;
            break;

        case 5: // '\005'
            if(flag)
            {
                k++;
                j++;
                flag1 = false;
            } else
            {
                k--;
            }
            j1 = 0;
            break;
        }
        if(a(world, i, j, k, flag, i1, j1))
            return true;
        return flag1 && a(world, i, j - 1, k, flag, i1, j1);
    }

    private boolean a(World world, int i, int j, int k, boolean flag, int l, int i1)
    {
        int j1 = world.getTypeId(i, j, k);
        if(j1 == Block.GOLDEN_RAIL.id)
        {
            int k1 = world.getData(i, j, k);
            int l1 = k1 & 7;
            if(i1 == 1 && (l1 == 0 || l1 == 4 || l1 == 5))
                return false;
            if(i1 == 0 && (l1 == 1 || l1 == 2 || l1 == 3))
                return false;
            if((k1 & 8) != 0)
                if(world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k))
                    return true;
                else
                    return a(world, i, j, k, k1, flag, l + 1);
        }
        return false;
    }

    public int e()
    {
        return 0;
    }

    static boolean a(BlockMinecartTrack blockminecarttrack)
    {
        return blockminecarttrack.a;
    }

    private final boolean a;
}
