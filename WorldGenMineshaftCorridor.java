// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, WorldGenMineshaftPieces, Block, 
//            World, TileEntityMobSpawner

public class WorldGenMineshaftCorridor extends StructurePiece
{

    public WorldGenMineshaftCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        h = j;
        g = structureboundingbox;
        a = random.nextInt(3) == 0;
        b = !a && random.nextInt(23) == 0;
        if(h == 2 || h == 0)
            d = structureboundingbox.d() / 5;
        else
            d = structureboundingbox.b() / 5;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);
        int i1 = random.nextInt(3) + 2;
        do
        {
            if(i1 <= 0)
                break;
            int j1 = i1 * 5;
            switch(l)
            {
            case 2: // '\002'
                structureboundingbox.d = i + 2;
                structureboundingbox.c = k - (j1 - 1);
                break;

            case 0: // '\0'
                structureboundingbox.d = i + 2;
                structureboundingbox.f = k + (j1 - 1);
                break;

            case 1: // '\001'
                structureboundingbox.a = i - (j1 - 1);
                structureboundingbox.f = k + 2;
                break;

            case 3: // '\003'
                structureboundingbox.d = i + (j1 - 1);
                structureboundingbox.f = k + 2;
                break;
            }
            if(StructurePiece.a(list, structureboundingbox) == null)
                break;
            i1--;
        } while(true);
        if(i1 > 0)
            return structureboundingbox;
        else
            return null;
    }

    public void a(StructurePiece structurepiece, List list, Random random)
    {
        int i = c();
        int j = random.nextInt(4);
        switch(h)
        {
        case 2: // '\002'
            if(j <= 1)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, (g.b - 1) + random.nextInt(3), g.c - 1, h, i);
            else
            if(j == 2)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, (g.b - 1) + random.nextInt(3), g.c, 1, i);
            else
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, (g.b - 1) + random.nextInt(3), g.c, 3, i);
            break;

        case 0: // '\0'
            if(j <= 1)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, (g.b - 1) + random.nextInt(3), g.f + 1, h, i);
            else
            if(j == 2)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, (g.b - 1) + random.nextInt(3), g.f - 3, 1, i);
            else
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, (g.b - 1) + random.nextInt(3), g.f - 3, 3, i);
            break;

        case 1: // '\001'
            if(j <= 1)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, (g.b - 1) + random.nextInt(3), g.c, h, i);
            else
            if(j == 2)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, (g.b - 1) + random.nextInt(3), g.c - 1, 2, i);
            else
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.a, (g.b - 1) + random.nextInt(3), g.f + 1, 0, i);
            break;

        case 3: // '\003'
            if(j <= 1)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, (g.b - 1) + random.nextInt(3), g.c, h, i);
            else
            if(j == 2)
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d - 3, (g.b - 1) + random.nextInt(3), g.c - 1, 2, i);
            else
                WorldGenMineshaftPieces.a(structurepiece, list, random, g.d - 3, (g.b - 1) + random.nextInt(3), g.f + 1, 0, i);
            break;
        }
        if(i < 10)
            if(h == 2 || h == 0)
            {
                for(int k = g.c + 3; k + 3 <= g.f; k += 5)
                {
                    int i1 = random.nextInt(5);
                    if(i1 == 0)
                        WorldGenMineshaftPieces.a(structurepiece, list, random, g.a - 1, g.b, k, 1, i + 1);
                    else
                    if(i1 == 1)
                        WorldGenMineshaftPieces.a(structurepiece, list, random, g.d + 1, g.b, k, 3, i + 1);
                }

            } else
            {
                for(int l = g.a + 3; l + 3 <= g.d; l += 5)
                {
                    int j1 = random.nextInt(5);
                    if(j1 == 0)
                    {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, l, g.b, g.c - 1, 2, i + 1);
                        continue;
                    }
                    if(j1 == 1)
                        WorldGenMineshaftPieces.a(structurepiece, list, random, l, g.b, g.f + 1, 0, i + 1);
                }

            }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(a(world, structureboundingbox))
            return false;
        int i = d * 5 - 1;
        a(world, structureboundingbox, 0, 0, 0, 2, 1, i, 0, 0, false);
        a(world, structureboundingbox, random, 0.8F, 0, 2, 0, 2, 2, i, 0, 0, false);
        if(b)
            a(world, structureboundingbox, random, 0.6F, 0, 0, 0, 2, 1, i, Block.WEB.id, 0, false);
        for(int j = 0; j < d; j++)
        {
            int l = 2 + j * 5;
            a(world, structureboundingbox, 0, 0, l, 0, 1, l, Block.FENCE.id, 0, false);
            a(world, structureboundingbox, 2, 0, l, 2, 1, l, Block.FENCE.id, 0, false);
            if(random.nextInt(4) != 0)
            {
                a(world, structureboundingbox, 0, 2, l, 2, 2, l, Block.WOOD.id, 0, false);
            } else
            {
                a(world, structureboundingbox, 0, 2, l, 0, 2, l, Block.WOOD.id, 0, false);
                a(world, structureboundingbox, 2, 2, l, 2, 2, l, Block.WOOD.id, 0, false);
            }
            a(world, structureboundingbox, random, 0.1F, 0, 2, l - 1, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.1F, 2, 2, l - 1, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.1F, 0, 2, l + 1, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.1F, 2, 2, l + 1, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.05F, 0, 2, l - 2, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.05F, 2, 2, l - 2, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.05F, 0, 2, l + 2, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.05F, 2, 2, l + 2, Block.WEB.id, 0);
            a(world, structureboundingbox, random, 0.05F, 1, 2, l - 1, Block.TORCH.id, 0);
            a(world, structureboundingbox, random, 0.05F, 1, 2, l + 1, Block.TORCH.id, 0);
            if(random.nextInt(100) == 0)
                a(world, structureboundingbox, random, 2, 0, l - 1, WorldGenMineshaftPieces.a(), 3 + random.nextInt(4));
            if(random.nextInt(100) == 0)
                a(world, structureboundingbox, random, 0, 0, l + 1, WorldGenMineshaftPieces.a(), 3 + random.nextInt(4));
            if(!b || c)
                continue;
            int j1 = a(0);
            int k1 = (l - 1) + random.nextInt(3);
            int l1 = a(1, k1);
            k1 = b(1, k1);
            if(!structureboundingbox.b(l1, j1, k1))
                continue;
            c = true;
            world.setTypeId(l1, j1, k1, Block.MOB_SPAWNER.id);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(l1, j1, k1);
            if(tileentitymobspawner != null)
                tileentitymobspawner.a("CaveSpider");
        }

        if(a)
        {
            for(int k = 0; k <= i; k++)
            {
                int i1 = a(world, 1, -1, k, structureboundingbox);
                if(i1 > 0 && Block.o[i1])
                    a(world, structureboundingbox, random, 0.7F, 1, 0, k, Block.RAILS.id, c(Block.RAILS.id, 0));
            }

        }
        return true;
    }

    private final boolean a;
    private final boolean b;
    private boolean c;
    private int d;
}
