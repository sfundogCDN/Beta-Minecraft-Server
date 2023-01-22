// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material, AxisAlignedBB, Entity, 
//            World

public class BlockSlowSand extends Block
{

    public BlockSlowSand(int i, int j)
    {
        super(i, j, Material.SAND);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        float f = 0.125F;
        return AxisAlignedBB.b(i, j, k, i + 1, (float)(j + 1) - f, k + 1);
    }

    public void a(World world, int i, int j, int k, Entity entity)
    {
        entity.motX *= 0.40000000000000002D;
        entity.motZ *= 0.40000000000000002D;
    }
}
