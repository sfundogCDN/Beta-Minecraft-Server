// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            IBlockAccess, World, Chunk, Material, 
//            Block, TileEntity

public class ChunkCache
    implements IBlockAccess
{

    public ChunkCache(World world, int i, int j, int k, int l, int i1, int j1)
    {
        d = world;
        a = i >> 4;
        b = k >> 4;
        int k1 = l >> 4;
        int l1 = j1 >> 4;
        c = new Chunk[(k1 - a) + 1][(l1 - b) + 1];
        for(int i2 = a; i2 <= k1; i2++)
        {
            for(int j2 = b; j2 <= l1; j2++)
                c[i2 - a][j2 - b] = world.getChunkAt(i2, j2);

        }

    }

    public int getTypeId(int i, int j, int k)
    {
        if(j < 0)
            return 0;
        d.getClass();
        if(j >= 128)
            return 0;
        int l = (i >> 4) - a;
        int i1 = (k >> 4) - b;
        if(l < 0 || l >= c.length || i1 < 0 || i1 >= c[l].length)
            return 0;
        Chunk chunk = c[l][i1];
        if(chunk == null)
            return 0;
        else
            return chunk.getTypeId(i & 0xf, j, k & 0xf);
    }

    public TileEntity getTileEntity(int i, int j, int k)
    {
        int l = (i >> 4) - a;
        int i1 = (k >> 4) - b;
        return c[l][i1].d(i & 0xf, j, k & 0xf);
    }

    public int getData(int i, int j, int k)
    {
        if(j < 0)
            return 0;
        d.getClass();
        if(j >= 128)
        {
            return 0;
        } else
        {
            int l = (i >> 4) - a;
            int i1 = (k >> 4) - b;
            return c[l][i1].getData(i & 0xf, j, k & 0xf);
        }
    }

    public Material getMaterial(int i, int j, int k)
    {
        int l = getTypeId(i, j, k);
        if(l == 0)
            return Material.AIR;
        else
            return Block.byId[l].material;
    }

    public boolean e(int i, int j, int k)
    {
        Block block = Block.byId[getTypeId(i, j, k)];
        if(block == null)
            return false;
        else
            return block.material.isSolid() && block.b();
    }

    private int a;
    private int b;
    private Chunk c[][];
    private World d;
}
