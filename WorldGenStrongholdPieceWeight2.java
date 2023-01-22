// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            WorldGenStrongholdDoorType

class WorldGenStrongholdPieceWeight2
{

    static final int a[];

    static 
    {
        a = new int[WorldGenStrongholdDoorType.values().length];
        try
        {
            a[WorldGenStrongholdDoorType.a.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[WorldGenStrongholdDoorType.b.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[WorldGenStrongholdDoorType.c.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            a[WorldGenStrongholdDoorType.d.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
    }
}
