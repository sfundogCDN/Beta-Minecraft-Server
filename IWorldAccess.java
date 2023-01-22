// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Entity, TileEntity, EntityHuman

public interface IWorldAccess
{

    public abstract void a(int i, int j, int k);

    public abstract void a(int i, int j, int k, int l, int i1, int j1);

    public abstract void a(String s, double d, double d1, double d2, 
            float f, float f1);

    public abstract void a(String s, double d, double d1, double d2, 
            double d3, double d4, double d5);

    public abstract void a(Entity entity);

    public abstract void b(Entity entity);

    public abstract void a(String s, int i, int j, int k);

    public abstract void a(int i, int j, int k, TileEntity tileentity);

    public abstract void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1);
}
