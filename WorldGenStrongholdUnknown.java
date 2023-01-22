// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdPieceWeight

final class WorldGenStrongholdUnknown extends WorldGenStrongholdPieceWeight
{

    WorldGenStrongholdUnknown(Class class1, int i, int j)
    {
        super(class1, i, j);
    }

    public boolean a(int i)
    {
        return super.a(i) && i > 4;
    }
}
