// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPiece, WorldGenStrongholdStairs2, StructureBoundingBox, StructurePiece, 
//            WorldGenStrongholdPieces, Block, StructurePieceTreasure, Item, 
//            WorldGenStrongholdDoorType, World

public class WorldGenStrongholdRoomCrossing extends WorldGenStrongholdPiece
{

    public WorldGenStrongholdRoomCrossing(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        a = a(random);
        g = structureboundingbox;
        b = random.nextInt(5);
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        a((WorldGenStrongholdStairs2)structurepiece, list, random, 4, 1);
        b((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 4);
        c((WorldGenStrongholdStairs2)structurepiece, list, random, 1, 4);
    }

    public static WorldGenStrongholdRoomCrossing a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 7, 11, l);
        if(!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null)
            return null;
        else
            return new WorldGenStrongholdRoomCrossing(i1, random, structureboundingbox, l);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        a(world, structureboundingbox, 0, 0, 0, 10, 6, 10, true, random, ((StructurePIeceBlockSelector) (WorldGenStrongholdPieces.b())));
        a(world, random, structureboundingbox, a, 4, 1, 0);
        a(world, structureboundingbox, 4, 1, 10, 6, 3, 10, 0, 0, false);
        a(world, structureboundingbox, 0, 1, 4, 0, 3, 6, 0, 0, false);
        a(world, structureboundingbox, 10, 1, 4, 10, 3, 6, 0, 0, false);
        switch(b)
        {
        default:
            break;

        case 0: // '\0'
            a(world, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, structureboundingbox);
            a(world, Block.TORCH.id, 0, 4, 3, 5, structureboundingbox);
            a(world, Block.TORCH.id, 0, 6, 3, 5, structureboundingbox);
            a(world, Block.TORCH.id, 0, 5, 3, 4, structureboundingbox);
            a(world, Block.TORCH.id, 0, 5, 3, 6, structureboundingbox);
            a(world, Block.STEP.id, 0, 4, 1, 4, structureboundingbox);
            a(world, Block.STEP.id, 0, 4, 1, 5, structureboundingbox);
            a(world, Block.STEP.id, 0, 4, 1, 6, structureboundingbox);
            a(world, Block.STEP.id, 0, 6, 1, 4, structureboundingbox);
            a(world, Block.STEP.id, 0, 6, 1, 5, structureboundingbox);
            a(world, Block.STEP.id, 0, 6, 1, 6, structureboundingbox);
            a(world, Block.STEP.id, 0, 5, 1, 4, structureboundingbox);
            a(world, Block.STEP.id, 0, 5, 1, 6, structureboundingbox);
            break;

        case 1: // '\001'
            for(int i = 0; i < 5; i++)
            {
                a(world, Block.SMOOTH_BRICK.id, 0, 3, 1, 3 + i, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 7, 1, 3 + i, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 3, structureboundingbox);
                a(world, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 7, structureboundingbox);
            }

            a(world, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, structureboundingbox);
            a(world, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, structureboundingbox);
            a(world, Block.WATER.id, 0, 5, 4, 5, structureboundingbox);
            break;

        case 2: // '\002'
            for(int j = 1; j <= 9; j++)
            {
                a(world, Block.COBBLESTONE.id, 0, 1, 3, j, structureboundingbox);
                a(world, Block.COBBLESTONE.id, 0, 9, 3, j, structureboundingbox);
            }

            for(int k = 1; k <= 9; k++)
            {
                a(world, Block.COBBLESTONE.id, 0, k, 3, 1, structureboundingbox);
                a(world, Block.COBBLESTONE.id, 0, k, 3, 9, structureboundingbox);
            }

            a(world, Block.COBBLESTONE.id, 0, 5, 1, 4, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 5, 1, 6, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 5, 3, 4, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 5, 3, 6, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 4, 1, 5, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 6, 1, 5, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 4, 3, 5, structureboundingbox);
            a(world, Block.COBBLESTONE.id, 0, 6, 3, 5, structureboundingbox);
            for(int l = 1; l <= 3; l++)
            {
                a(world, Block.COBBLESTONE.id, 0, 4, l, 4, structureboundingbox);
                a(world, Block.COBBLESTONE.id, 0, 6, l, 4, structureboundingbox);
                a(world, Block.COBBLESTONE.id, 0, 4, l, 6, structureboundingbox);
                a(world, Block.COBBLESTONE.id, 0, 6, l, 6, structureboundingbox);
            }

            a(world, Block.TORCH.id, 0, 5, 3, 5, structureboundingbox);
            for(int i1 = 2; i1 <= 8; i1++)
            {
                a(world, Block.WOOD.id, 0, 2, 3, i1, structureboundingbox);
                a(world, Block.WOOD.id, 0, 3, 3, i1, structureboundingbox);
                if(i1 <= 3 || i1 >= 7)
                {
                    a(world, Block.WOOD.id, 0, 4, 3, i1, structureboundingbox);
                    a(world, Block.WOOD.id, 0, 5, 3, i1, structureboundingbox);
                    a(world, Block.WOOD.id, 0, 6, 3, i1, structureboundingbox);
                }
                a(world, Block.WOOD.id, 0, 7, 3, i1, structureboundingbox);
                a(world, Block.WOOD.id, 0, 8, 3, i1, structureboundingbox);
            }

            a(world, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 1, 3, structureboundingbox);
            a(world, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 2, 3, structureboundingbox);
            a(world, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 3, 3, structureboundingbox);
            a(world, structureboundingbox, random, 3, 4, 8, c, 1 + random.nextInt(4));
            break;
        }
        return true;
    }

    private static final StructurePieceTreasure c[];
    protected final WorldGenStrongholdDoorType a;
    protected final int b;

    static 
    {
        c = (new StructurePieceTreasure[] {
            new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.COAL.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1)
        });
    }
}
