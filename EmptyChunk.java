// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            Chunk, World, EnumSkyBlock, Entity, 
//            TileEntity, AxisAlignedBB

public class EmptyChunk extends Chunk
{

    public EmptyChunk(World world, int i, int j)
    {
        super(world, i, j);
        r = true;
    }

    public EmptyChunk(World world, byte abyte0[], int i, int j)
    {
        super(world, abyte0, i, j);
        r = true;
    }

    public boolean a(int i, int j)
    {
        return i == x && j == z;
    }

    public int b(int i, int j)
    {
        return 0;
    }

    public void a()
    {
    }

    public void initLighting()
    {
    }

    public void loadNOP()
    {
    }

    public int getTypeId(int i, int j, int k)
    {
        return 0;
    }

    public boolean a(int i, int j, int k, int l, int i1)
    {
        return true;
    }

    public boolean a(int i, int j, int k, int l)
    {
        return true;
    }

    public int getData(int i, int j, int k)
    {
        return 0;
    }

    public void b(int i, int j, int k, int l)
    {
    }

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k)
    {
        return 0;
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l)
    {
    }

    public int c(int i, int j, int k, int l)
    {
        return 0;
    }

    public void a(Entity entity)
    {
    }

    public void b(Entity entity)
    {
    }

    public void a(Entity entity, int i)
    {
    }

    public boolean c(int i, int j, int k)
    {
        return false;
    }

    public TileEntity d(int i, int j, int k)
    {
        return null;
    }

    public void a(TileEntity tileentity)
    {
    }

    public void a(int i, int j, int k, TileEntity tileentity)
    {
    }

    public void e(int i, int j, int k)
    {
    }

    public void addEntities()
    {
    }

    public void removeEntities()
    {
    }

    public void f()
    {
    }

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list)
    {
    }

    public void a(Class class1, AxisAlignedBB axisalignedbb, List list)
    {
    }

    public boolean a(boolean flag)
    {
        return false;
    }

    public int getData(byte abyte0[], int i, int j, int k, int l, int i1, int j1, 
            int k1)
    {
        int l1 = l - i;
        int i2 = i1 - j;
        int j2 = j1 - k;
        int k2 = l1 * i2 * j2;
        int l2 = k2 + (k2 / 2) * 3;
        Arrays.fill(abyte0, k1, k1 + l2, (byte)0);
        return l2;
    }

    public Random a(long l)
    {
        return new Random(world.getSeed() + (long)(x * x * 0x4c1906) + (long)(x * 0x5ac0db) + (long)(z * z) * 0x4307a7L + (long)(z * 0x5f24f) ^ l);
    }
}
