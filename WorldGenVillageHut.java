// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenVillagePiece, StructureBoundingBox, StructurePiece, Block, 
//            World

public class WorldGenVillageHut extends WorldGenVillagePiece
{

    public WorldGenVillageHut(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
        b = random.nextBoolean();
        c = random.nextInt(3);
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenVillageHut a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 4, 6, 5, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenVillageHut(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 6) - 1, 0);
        }
        a(world, structureboundingbox, 1, 1, 1, 3, 5, 4, 0, 0, false);
        a(world, structureboundingbox, 0, 0, 0, 3, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
        a(world, structureboundingbox, 1, 0, 1, 2, 0, 3, Block.DIRT.id, Block.DIRT.id, false);
        if(b)
            a(world, structureboundingbox, 1, 4, 1, 2, 4, 3, Block.LOG.id, Block.LOG.id, false);
        else
            a(world, structureboundingbox, 1, 5, 1, 2, 5, 3, Block.LOG.id, Block.LOG.id, false);
        a(world, Block.LOG.id, 0, 1, 4, 0, structureboundingbox);
        a(world, Block.LOG.id, 0, 2, 4, 0, structureboundingbox);
        a(world, Block.LOG.id, 0, 1, 4, 4, structureboundingbox);
        a(world, Block.LOG.id, 0, 2, 4, 4, structureboundingbox);
        a(world, Block.LOG.id, 0, 0, 4, 1, structureboundingbox);
        a(world, Block.LOG.id, 0, 0, 4, 2, structureboundingbox);
        a(world, Block.LOG.id, 0, 0, 4, 3, structureboundingbox);
        a(world, Block.LOG.id, 0, 3, 4, 1, structureboundingbox);
        a(world, Block.LOG.id, 0, 3, 4, 2, structureboundingbox);
        a(world, Block.LOG.id, 0, 3, 4, 3, structureboundingbox);
        a(world, structureboundingbox, 0, 1, 0, 0, 3, 0, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 3, 1, 0, 3, 3, 0, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 0, 1, 4, 0, 3, 4, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 3, 1, 4, 3, 3, 4, Block.LOG.id, Block.LOG.id, false);
        a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 3, 1, 1, 3, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
        a(world, structureboundingbox, 1, 1, 4, 2, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
        a(world, Block.THIN_GLASS.id, 0, 0, 2, 2, structureboundingbox);
        a(world, Block.THIN_GLASS.id, 0, 3, 2, 2, structureboundingbox);
        if(c > 0)
        {
            a(world, Block.FENCE.id, 0, c, 1, 3, structureboundingbox);
            a(world, Block.WOOD_PLATE.id, 0, c, 2, 3, structureboundingbox);
        }
        a(world, 0, 0, 1, 1, 0, structureboundingbox);
        a(world, 0, 0, 1, 2, 0, structureboundingbox);
        a(world, structureboundingbox, random, 1, 1, 0, c(Block.WOODEN_DOOR.id, 1));
        if(a(world, 1, 0, -1, structureboundingbox) == 0 && a(world, 1, -1, -1, structureboundingbox) != 0)
            a(world, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, structureboundingbox);
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                b(world, j, 6, i, structureboundingbox);
                b(world, Block.COBBLESTONE.id, 0, j, -1, i, structureboundingbox);
            }

        }

        return true;
    }

    private int a;
    private final boolean b;
    private final int c;
}
