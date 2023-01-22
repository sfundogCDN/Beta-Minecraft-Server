// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            World, Chunk

public interface IChunkLoader
{

    public abstract Chunk a(World world, int i, int j);

    public abstract void a(World world, Chunk chunk);

    public abstract void b(World world, Chunk chunk);

    public abstract void a();

    public abstract void b();
}
