// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            BlockContainer, Material, Block, World, 
//            TileEntityNote, EntityHuman, TileEntity

public class BlockNote extends BlockContainer
{

    public BlockNote(int i)
    {
        super(i, 74, Material.WOOD);
    }

    public int a(int i)
    {
        return textureId;
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(l > 0 && Block.byId[l].isPowerSource())
        {
            boolean flag = world.isBlockPowered(i, j, k);
            TileEntityNote tileentitynote = (TileEntityNote)world.getTileEntity(i, j, k);
            if(tileentitynote != null && tileentitynote.b != flag)
            {
                if(flag)
                    tileentitynote.play(world, i, j, k);
                tileentitynote.b = flag;
            }
        }
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
            return true;
        TileEntityNote tileentitynote = (TileEntityNote)world.getTileEntity(i, j, k);
        if(tileentitynote != null)
        {
            tileentitynote.a();
            tileentitynote.play(world, i, j, k);
        }
        return true;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
            return;
        TileEntityNote tileentitynote = (TileEntityNote)world.getTileEntity(i, j, k);
        if(tileentitynote != null)
            tileentitynote.play(world, i, j, k);
    }

    public TileEntity a_()
    {
        return new TileEntityNote();
    }

    public void a(World world, int i, int j, int k, int l, int i1)
    {
        float f = (float)Math.pow(2D, (double)(i1 - 12) / 12D);
        String s = "harp";
        if(l == 1)
            s = "bd";
        if(l == 2)
            s = "snare";
        if(l == 3)
            s = "hat";
        if(l == 4)
            s = "bassattack";
        world.makeSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, (new StringBuilder()).append("note.").append(s).toString(), 3F, f);
        world.a("note", (double)i + 0.5D, (double)j + 1.2D, (double)k + 0.5D, (double)i1 / 24D, 0.0D, 0.0D);
    }
}
