// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockPistonExtension.java

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, PistonBlockTextures, 
//            BlockPiston, IBlockAccess, AxisAlignedBB

public class BlockPistonExtension extends Block
{

    public BlockPistonExtension(int i, int j)
    {
        super(i, j, Material.PISTON);
        a = -1;
        a(h);
        c(0.5F);
    }

    public void remove(World world, int i, int j, int k)
    {
        super.remove(world, i, j, k);
        int l = world.getData(i, j, k);
        if(l > 5 || l < 0)
            return;
        int i1 = PistonBlockTextures.a[b(l)];
        i += PistonBlockTextures.b[i1];
        j += PistonBlockTextures.c[i1];
        k += PistonBlockTextures.d[i1];
        int j1 = world.getTypeId(i, j, k);
        if(j1 == Block.PISTON.id || j1 == Block.PISTON_STICKY.id)
        {
            l = world.getData(i, j, k);
            if(BlockPiston.d(l))
            {
                Block.byId[j1].g(world, i, j, k, l);
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    public int a(int i, int j)
    {
        int k = b(j);
        return i != k ? ((byte)(i != PistonBlockTextures.a[k] ? 108 : 107)) : a < 0 ? (j & 8) == 0 ? textureId : textureId - 1 : a;
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int l)
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int i1, int j1)
    {
        return false;
    }

    public int a(Random random)
    {
        return 0;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        int l = world.getData(i, j, k);
        switch(b(l))
        {
        case 0: // '\0'
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;

        case 1: // '\001'
            a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;

        case 2: // '\002'
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;

        case 3: // '\003'
            a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;

        case 4: // '\004'
            a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;

        case 5: // '\005'
            a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            break;
        }
        a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k);
        switch(b(l))
        {
        case 0: // '\0'
            a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            break;

        case 1: // '\001'
            a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;

        case 2: // '\002'
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            break;

        case 3: // '\003'
            a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            break;

        case 4: // '\004'
            a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            break;

        case 5: // '\005'
            a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        int i1 = b(world.getData(i, j, k));
        if(i1 > 5 || i1 < 0)
            return;
        int j1 = world.getTypeId(i - PistonBlockTextures.b[i1], j - PistonBlockTextures.c[i1], k - PistonBlockTextures.d[i1]);
        if(j1 != Block.PISTON.id && j1 != Block.PISTON_STICKY.id)
            world.setTypeId(i, j, k, 0);
        else
            Block.byId[j1].doPhysics(world, i - PistonBlockTextures.b[i1], j - PistonBlockTextures.c[i1], k - PistonBlockTextures.d[i1], l);
    }

    public static int b(int i)
    {
        return i & 7;
    }

    private int a;
}
