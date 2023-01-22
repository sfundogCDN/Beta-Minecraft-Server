// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block, Material, World

public class BlockSponge extends Block
{

    protected BlockSponge(int i)
    {
        super(i, Material.SPONGE);
        textureId = 48;
    }

    public void a(World world, int i, int j, int k)
    {
        byte byte0 = 2;
        for(int l = i - byte0; l <= i + byte0; l++)
        {
            for(int i1 = j - byte0; i1 <= j + byte0; i1++)
            {
                for(int j1 = k - byte0; j1 <= k + byte0; j1++)
                    if(world.getMaterial(l, i1, j1) != Material.WATER);

            }

        }

    }

    public void remove(World world, int i, int j, int k)
    {
        byte byte0 = 2;
        for(int l = i - byte0; l <= i + byte0; l++)
        {
            for(int i1 = j - byte0; i1 <= j + byte0; i1++)
            {
                for(int j1 = k - byte0; j1 <= k + byte0; j1++)
                    world.applyPhysics(l, i1, j1, world.getTypeId(l, i1, j1));

            }

        }

    }
}
