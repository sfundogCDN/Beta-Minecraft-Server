// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, StructureBoundingBox, StructurePiece, WorldGenStrongholdPieces, 
//            Block, StructurePieceTreasure, Item, ItemWorldMap, 
//            WorldGenStrongholdDoorType, World

public class WorldGenStrongholdLibrary extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdLibrary(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
        c = structureboundingbox.c() > 6;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
    }

    public static WorldGenStrongholdLibrary a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 11, 15, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
        {
            structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 6, 15, l);
            if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
                return null;
        }
        return new WorldGenStrongholdLibrary(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        byte byte0 = 11;
        if(!c)
            byte0 = 6;
        a(world, structureboundingbox, 0, 0, 0, 13, byte0 - 1, 14, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 4, 1, 0);
        a(world, structureboundingbox, random, 0.07F, 2, 1, 1, 11, 4, 13, Block.WEB.id, Block.WEB.id, false);
        for(int i = 1; i <= 13; i++)
        {
            if((i - 1) % 4 == 0)
            {
                a(world, structureboundingbox, 1, 1, i, 1, 4, i, Block.WOOD.id, Block.WOOD.id, false);
                a(world, structureboundingbox, 12, 1, i, 12, 4, i, Block.WOOD.id, Block.WOOD.id, false);
                a(world, Block.TORCH.id, 0, 2, 3, i, structureboundingbox);
                a(world, Block.TORCH.id, 0, 11, 3, i, structureboundingbox);
                if(c)
                {
                    a(world, structureboundingbox, 1, 6, i, 1, 9, i, Block.WOOD.id, Block.WOOD.id, false);
                    a(world, structureboundingbox, 12, 6, i, 12, 9, i, Block.WOOD.id, Block.WOOD.id, false);
                }
                continue;
            }
            a(world, structureboundingbox, 1, 1, i, 1, 4, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            a(world, structureboundingbox, 12, 1, i, 12, 4, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            if(c)
            {
                a(world, structureboundingbox, 1, 6, i, 1, 9, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
                a(world, structureboundingbox, 12, 6, i, 12, 9, i, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            }
        }

        for(int j = 3; j < 12; j += 2)
        {
            a(world, structureboundingbox, 3, 1, j, 4, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            a(world, structureboundingbox, 6, 1, j, 7, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
            a(world, structureboundingbox, 9, 1, j, 10, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
        }

        if(c)
        {
            a(world, structureboundingbox, 1, 5, 1, 3, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
            a(world, structureboundingbox, 10, 5, 1, 12, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
            a(world, structureboundingbox, 4, 5, 1, 9, 5, 2, Block.WOOD.id, Block.WOOD.id, false);
            a(world, structureboundingbox, 4, 5, 12, 9, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
            a(world, Block.WOOD.id, 0, 9, 5, 11, structureboundingbox);
            a(world, Block.WOOD.id, 0, 8, 5, 11, structureboundingbox);
            a(world, Block.WOOD.id, 0, 9, 5, 10, structureboundingbox);
            a(world, structureboundingbox, 3, 6, 2, 3, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
            a(world, structureboundingbox, 10, 6, 2, 10, 6, 10, Block.FENCE.id, Block.FENCE.id, false);
            a(world, structureboundingbox, 4, 6, 2, 9, 6, 2, Block.FENCE.id, Block.FENCE.id, false);
            a(world, structureboundingbox, 4, 6, 12, 8, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
            a(world, Block.FENCE.id, 0, 9, 6, 11, structureboundingbox);
            a(world, Block.FENCE.id, 0, 8, 6, 11, structureboundingbox);
            a(world, Block.FENCE.id, 0, 9, 6, 10, structureboundingbox);
            int k = c(Block.LADDER.id, 3);
            a(world, Block.LADDER.id, k, 10, 1, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 2, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 3, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 4, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 5, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 6, 13, structureboundingbox);
            a(world, Block.LADDER.id, k, 10, 7, 13, structureboundingbox);
            byte byte1 = 7;
            byte byte2 = 7;
            a(world, Block.FENCE.id, 0, byte1 - 1, 9, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1, 9, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 - 1, 8, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1, 8, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 - 1, 7, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1, 7, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 - 2, 7, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 + 1, 7, byte2, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 - 1, 7, byte2 - 1, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1 - 1, 7, byte2 + 1, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1, 7, byte2 - 1, structureboundingbox);
            a(world, Block.FENCE.id, 0, byte1, 7, byte2 + 1, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1 - 2, 8, byte2, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1 + 1, 8, byte2, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1 - 1, 8, byte2 - 1, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1 - 1, 8, byte2 + 1, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1, 8, byte2 - 1, structureboundingbox);
            a(world, Block.TORCH.id, 0, byte1, 8, byte2 + 1, structureboundingbox);
        }
        a(world, structureboundingbox, random, 3, 3, 5, b, 1 + random.nextInt(4));
        if(c)
        {
            a(world, 0, 0, 12, 9, 1, structureboundingbox);
            a(world, structureboundingbox, random, 12, 8, 1, b, 1 + random.nextInt(4));
        }
        return true;
    }

    private static final StructurePieceTreasure b[];
    protected final WorldGenStrongholdDoorType a;
    private final boolean c;

    static 
    {
        b = (new StructurePieceTreasure[] {
            new StructurePieceTreasure(Item.BOOK.id, 0, 1, 3, 20), new StructurePieceTreasure(Item.PAPER.id, 0, 2, 7, 20), new StructurePieceTreasure(Item.MAP.id, 0, 1, 1, 1), new StructurePieceTreasure(Item.COMPASS.id, 0, 1, 1, 1)
        });
    }
}
