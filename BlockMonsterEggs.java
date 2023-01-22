// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, EntitySilverfish, 
//            EntityHuman

public class BlockMonsterEggs extends Block
{

    public BlockMonsterEggs(int i)
    {
        super(i, 1, Material.CLAY);
        c(0.0F);
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
    {
        super.a(world, entityhuman, i, j, k, l);
    }

    public int a(int i, int j)
    {
        if(j == 1)
            return Block.COBBLESTONE.textureId;
        if(j == 2)
            return Block.SMOOTH_BRICK.textureId;
        else
            return Block.STONE.textureId;
    }

    public void postBreak(World world, int i, int j, int k, int l)
    {
        if(!world.isStatic)
        {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);
            entitysilverfish.setPositionRotation((double)i + 0.5D, j, (double)k + 0.5D, 0.0F, 0.0F);
            world.addEntity(entitysilverfish);
            entitysilverfish.ab();
        }
        super.postBreak(world, i, j, k, l);
    }

    public int a(Random random)
    {
        return 0;
    }

    public static boolean c(int i)
    {
        return i == Block.STONE.id || i == Block.COBBLESTONE.id || i == Block.SMOOTH_BRICK.id;
    }

    public static int d(int i)
    {
        if(i == Block.COBBLESTONE.id)
            return 1;
        return i != Block.SMOOTH_BRICK.id ? 0 : 2;
    }
}
