// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            StructurePiece, StructureBoundingBox, World

public abstract class StructureStart
{

    protected StructureStart()
    {
        a = new LinkedList();
    }

    public StructureBoundingBox b()
    {
        return b;
    }

    public void a(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        Iterator iterator = a.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            StructurePiece structurepiece = (StructurePiece)iterator.next();
            if(structurepiece.b().a(structureboundingbox) && !structurepiece.a(world, random, structureboundingbox))
                iterator.remove();
        } while(true);
    }

    protected void c()
    {
        b = StructureBoundingBox.a();
        StructurePiece structurepiece;
        for(Iterator iterator = a.iterator(); iterator.hasNext(); b.b(structurepiece.b()))
            structurepiece = (StructurePiece)iterator.next();

    }

    protected void a(World world, Random random, int i)
    {
        world.getClass();
        int j = 63 - i;
        int k = b.c() + 1;
        if(k < j)
            k += random.nextInt(j - k);
        int l = k - b.e;
        b.a(0, l, 0);
        StructurePiece structurepiece;
        for(Iterator iterator = a.iterator(); iterator.hasNext(); structurepiece.b().a(0, l, 0))
            structurepiece = (StructurePiece)iterator.next();

    }

    public boolean a()
    {
        return true;
    }

    protected LinkedList a;
    protected StructureBoundingBox b;
}
