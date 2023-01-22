// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Block, Material, World, EntityTNTPrimed, 
//            ItemStack, EntityHuman, Item

public class BlockTNT extends Block
{

    public BlockTNT(int i, int j)
    {
        super(i, j, Material.TNT);
    }

    public int a(int i)
    {
        if(i == 0)
            return textureId + 2;
        if(i == 1)
            return textureId + 1;
        else
            return textureId;
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        if(world.isBlockIndirectlyPowered(i, j, k))
        {
            postBreak(world, i, j, k, 1);
            world.setTypeId(i, j, k, 0);
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(l > 0 && Block.byId[l].isPowerSource() && world.isBlockIndirectlyPowered(i, j, k))
        {
            postBreak(world, i, j, k, 1);
            world.setTypeId(i, j, k, 0);
        }
    }

    public int a(Random random)
    {
        return 0;
    }

    public void a_(World world, int i, int j, int k)
    {
        EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
        entitytntprimed.fuseTicks = world.random.nextInt(entitytntprimed.fuseTicks / 4) + entitytntprimed.fuseTicks / 8;
        world.addEntity(entitytntprimed);
    }

    public void postBreak(World world, int i, int j, int k, int l)
    {
        if(world.isStatic)
            return;
        if((l & 1) == 0)
        {
            a(world, i, j, k, new ItemStack(Block.TNT.id, 1, 0));
        } else
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
            world.addEntity(entitytntprimed);
            world.makeSound(entitytntprimed, "random.fuse", 1.0F, 1.0F);
        }
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(entityhuman.K() != null && entityhuman.K().id == Item.FLINT_AND_STEEL.id)
            world.setRawData(i, j, k, 1);
        super.b(world, i, j, k, entityhuman);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        return super.interact(world, i, j, k, entityhuman);
    }
}
