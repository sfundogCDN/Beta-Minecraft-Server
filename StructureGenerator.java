// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            MapGenBase, ChunkCoordIntPair, StructureStart, StructureBoundingBox, 
//            IChunkProvider, World

public abstract class StructureGenerator extends MapGenBase
{

    public StructureGenerator()
    {
        e = new HashMap();
    }

    public void a(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        super.a(ichunkprovider, world, i, j, abyte0);
    }

    protected void a(World world, int i, int j, int k, int l, byte abyte0[])
    {
        if(e.containsKey(Long.valueOf(ChunkCoordIntPair.a(i, j))))
            return;
        c.nextInt();
        if(a(i, j))
        {
            StructureStart structurestart = b(i, j);
            e.put(Long.valueOf(ChunkCoordIntPair.a(i, j)), structurestart);
        }
    }

    public boolean a(World world, Random random, int i, int j)
    {
        int k = (i << 4) + 8;
        int l = (j << 4) + 8;
        boolean flag = false;
        Iterator iterator = e.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StructureStart structurestart = (StructureStart)iterator.next();
            if(structurestart.a() && structurestart.b().a(k, l, k + 15, l + 15))
            {
                structurestart.a(world, random, new StructureBoundingBox(k, l, k + 15, l + 15));
                flag = true;
            }
        } while(true);
        return flag;
    }

    protected abstract boolean a(int i, int j);

    protected abstract StructureStart b(int i, int j);

    protected HashMap e;
}
