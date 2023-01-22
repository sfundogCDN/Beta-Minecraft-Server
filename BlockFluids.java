// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, IBlockAccess, 
//            Vec3D, AxisAlignedBB, Entity

public abstract class BlockFluids extends Block
{

    protected BlockFluids(int j, Material material)
    {
        super(j, (material != Material.LAVA ? 12 : 14) * 16 + 13, material);
        float f = 0.0F;
        float f1 = 0.0F;
        a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        a(true);
    }

    public static float c(int j)
    {
        if(j >= 8)
            j = 0;
        float f = (float)(j + 1) / 9F;
        return f;
    }

    public int a(int j)
    {
        if(j == 0 || j == 1)
            return textureId;
        else
            return textureId + 1;
    }

    protected int g(World world, int j, int k, int l)
    {
        if(world.getMaterial(j, k, l) != material)
            return -1;
        else
            return world.getData(j, k, l);
    }

    protected int b(IBlockAccess iblockaccess, int j, int k, int l)
    {
        if(iblockaccess.getMaterial(j, k, l) != material)
            return -1;
        int i1 = iblockaccess.getData(j, k, l);
        if(i1 >= 8)
            i1 = 0;
        return i1;
    }

    public boolean b()
    {
        return false;
    }

    public boolean a()
    {
        return false;
    }

    public boolean a(int j, boolean flag)
    {
        return flag && j == 0;
    }

    public boolean b(IBlockAccess iblockaccess, int j, int k, int l, int i1)
    {
        Material material = iblockaccess.getMaterial(j, k, l);
        if(material == this.material)
            return false;
        if(i1 == 1)
            return true;
        if(material == Material.ICE)
            return false;
        else
            return super.b(iblockaccess, j, k, l, i1);
    }

    public AxisAlignedBB e(World world, int j, int k, int l)
    {
        return null;
    }

    public int a(int j, Random random)
    {
        return 0;
    }

    public int a(Random random)
    {
        return 0;
    }

    private Vec3D c(IBlockAccess iblockaccess, int j, int k, int l)
    {
        Vec3D vec3d = Vec3D.create(0.0D, 0.0D, 0.0D);
        int i1 = b(iblockaccess, j, k, l);
        for(int j1 = 0; j1 < 4; j1++)
        {
            int k1 = j;
            int l1 = k;
            int i2 = l;
            if(j1 == 0)
                k1--;
            if(j1 == 1)
                i2--;
            if(j1 == 2)
                k1++;
            if(j1 == 3)
                i2++;
            int j2 = b(iblockaccess, k1, l1, i2);
            if(j2 < 0)
            {
                if(iblockaccess.getMaterial(k1, l1, i2).isSolid())
                    continue;
                j2 = b(iblockaccess, k1, l1 - 1, i2);
                if(j2 >= 0)
                {
                    int k2 = j2 - (i1 - 8);
                    vec3d = vec3d.add((k1 - j) * k2, (l1 - k) * k2, (i2 - l) * k2);
                }
                continue;
            }
            if(j2 >= 0)
            {
                int l2 = j2 - i1;
                vec3d = vec3d.add((k1 - j) * l2, (l1 - k) * l2, (i2 - l) * l2);
            }
        }

        if(iblockaccess.getData(j, k, l) >= 8)
        {
            boolean flag = false;
            if(flag || b(iblockaccess, j, k, l - 1, 2))
                flag = true;
            if(flag || b(iblockaccess, j, k, l + 1, 3))
                flag = true;
            if(flag || b(iblockaccess, j - 1, k, l, 4))
                flag = true;
            if(flag || b(iblockaccess, j + 1, k, l, 5))
                flag = true;
            if(flag || b(iblockaccess, j, k + 1, l - 1, 2))
                flag = true;
            if(flag || b(iblockaccess, j, k + 1, l + 1, 3))
                flag = true;
            if(flag || b(iblockaccess, j - 1, k + 1, l, 4))
                flag = true;
            if(flag || b(iblockaccess, j + 1, k + 1, l, 5))
                flag = true;
            if(flag)
                vec3d = vec3d.b().add(0.0D, -6D, 0.0D);
        }
        vec3d = vec3d.b();
        return vec3d;
    }

    public void a(World world, int j, int k, int l, Entity entity, Vec3D vec3d)
    {
        Vec3D vec3d1 = c(world, j, k, l);
        vec3d.a += vec3d1.a;
        vec3d.b += vec3d1.b;
        vec3d.c += vec3d1.c;
    }

    public int c()
    {
        if(material == Material.WATER)
            return 5;
        return material != Material.LAVA ? 0 : 30;
    }

    public void a(World world, int j, int k, int l, Random random)
    {
        super.a(world, j, k, l, random);
    }

    public void a(World world, int j, int k, int l)
    {
        i(world, j, k, l);
    }

    public void doPhysics(World world, int j, int k, int l, int i1)
    {
        i(world, j, k, l);
    }

    private void i(World world, int j, int k, int l)
    {
        if(world.getTypeId(j, k, l) != id)
            return;
        if(material == Material.LAVA)
        {
            boolean flag = false;
            if(flag || world.getMaterial(j, k, l - 1) == Material.WATER)
                flag = true;
            if(flag || world.getMaterial(j, k, l + 1) == Material.WATER)
                flag = true;
            if(flag || world.getMaterial(j - 1, k, l) == Material.WATER)
                flag = true;
            if(flag || world.getMaterial(j + 1, k, l) == Material.WATER)
                flag = true;
            if(flag || world.getMaterial(j, k + 1, l) == Material.WATER)
                flag = true;
            if(flag)
            {
                int i1 = world.getData(j, k, l);
                if(i1 == 0)
                    world.setTypeId(j, k, l, Block.OBSIDIAN.id);
                else
                if(i1 <= 4)
                    world.setTypeId(j, k, l, Block.COBBLESTONE.id);
                h(world, j, k, l);
            }
        }
    }

    protected void h(World world, int j, int k, int l)
    {
        world.makeSound((float)j + 0.5F, (float)k + 0.5F, (float)l + 0.5F, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
        for(int i1 = 0; i1 < 8; i1++)
            world.a("largesmoke", (double)j + Math.random(), (double)k + 1.2D, (double)l + Math.random(), 0.0D, 0.0D, 0.0D);

    }
}
