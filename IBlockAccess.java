// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            TileEntity, Material

public interface IBlockAccess
{

    public abstract int getTypeId(int i, int j, int k);

    public abstract TileEntity getTileEntity(int i, int j, int k);

    public abstract int getData(int i, int j, int k);

    public abstract Material getMaterial(int i, int j, int k);

    public abstract boolean e(int i, int j, int k);
}
