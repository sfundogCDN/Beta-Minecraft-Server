// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Chunk, IProgressUpdate

public interface IChunkProvider
{

    public abstract boolean isChunkLoaded(int i, int j);

    public abstract Chunk getOrCreateChunk(int i, int j);

    public abstract Chunk getChunkAt(int i, int j);

    public abstract void getChunkAt(IChunkProvider ichunkprovider, int i, int j);

    public abstract boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate);

    public abstract boolean unloadChunks();

    public abstract boolean canSave();
}
