// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, World, TileEntity, Material

public abstract class BlockContainer extends Block
{

    protected BlockContainer(int i, Material material)
    {
        super(i, material);
        isTileEntity[id] = true;
    }

    protected BlockContainer(int i, int j, Material material)
    {
        super(i, j, material);
        isTileEntity[id] = true;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        world.setTileEntity(i, j, k, a_());
    }

    public void remove(World world, int i, int j, int k)
    {
        super.remove(world, i, j, k);
        world.n(i, j, k);
    }

    public abstract TileEntity a_();

    public void a(World world, int i, int j, int k, int l, int i1)
    {
        super.a(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if(tileentity != null)
            tileentity.b(l, i1);
    }
}
