// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, Entity, Item, 
//            World, AxisAlignedBB

public class BlockWeb extends Block
{

    public BlockWeb(int i, int j)
    {
        super(i, j, Material.WEB);
    }

    public void a(World world, int i, int j, int k, Entity entity)
    {
        entity.q();
    }

    public boolean a()
    {
        return false;
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean b()
    {
        return false;
    }

    public int a(int i, Random random)
    {
        return Item.STRING.id;
    }
}
