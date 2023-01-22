// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.LinkedList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StructureStart, WorldGenMineshaftRoom, World

public class WorldGenMineshaftStart extends StructureStart
{

    public WorldGenMineshaftStart(World world, Random random, int i, int j)
    {
        WorldGenMineshaftRoom worldgenmineshaftroom = new WorldGenMineshaftRoom(0, random, (i << 4) + 2, (j << 4) + 2);
        a.add(worldgenmineshaftroom);
        worldgenmineshaftroom.a(worldgenmineshaftroom, a, random);
        c();
        a(world, random, 10);
    }
}
