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

public class WorldGenVillageLight extends WorldGenVillagePiece
{

    public WorldGenVillageLight(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        a = -1;
        h = j;
        g = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 4, 2, l);
        if(StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return structureboundingbox;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a < 0)
        {
            a = b(world, structureboundingbox);
            if(a < 0)
                return true;
            g.a(0, ((a - g.e) + 4) - 1, 0);
        }
        a(world, structureboundingbox, 0, 0, 0, 2, 3, 1, 0, 0, false);
        a(world, Block.FENCE.id, 0, 1, 0, 0, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 1, 0, structureboundingbox);
        a(world, Block.FENCE.id, 0, 1, 2, 0, structureboundingbox);
        a(world, Block.WOOL.id, 15, 1, 3, 0, structureboundingbox);
        a(world, Block.TORCH.id, 15, 0, 3, 0, structureboundingbox);
        a(world, Block.TORCH.id, 15, 1, 3, 1, structureboundingbox);
        a(world, Block.TORCH.id, 15, 2, 3, 0, structureboundingbox);
        a(world, Block.TORCH.id, 15, 1, 3, -1, structureboundingbox);
        return true;
    }

    private int a;
}
