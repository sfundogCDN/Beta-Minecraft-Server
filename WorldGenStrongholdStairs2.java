// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdStairs, WorldGenStrongholdPieceWeight

public class WorldGenStrongholdStairs2 extends WorldGenStrongholdStairs
{

    public WorldGenStrongholdStairs2(int i, Random random, int j, int k)
    {
        super(0, random, j, k);
        b = new ArrayList();
    }

    public WorldGenStrongholdPieceWeight a;
    public ArrayList b;
}
