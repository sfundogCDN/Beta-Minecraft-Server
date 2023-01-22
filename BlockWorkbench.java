// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material, World, EntityHuman

public class BlockWorkbench extends Block
{

    protected BlockWorkbench(int i)
    {
        super(i, Material.WOOD);
        textureId = 59;
    }

    public int a(int i)
    {
        if(i == 1)
            return textureId - 16;
        if(i == 0)
            return Block.WOOD.a(0);
        if(i == 2 || i == 4)
            return textureId + 1;
        else
            return textureId;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(world.isStatic)
        {
            return true;
        } else
        {
            entityhuman.b(i, j, k);
            return true;
        }
    }
}
