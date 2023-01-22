// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDataManager.java

package net.minecraft.server;

import java.io.File;
import java.util.List;
import java.util.UUID;

// Referenced classes of package net.minecraft.server:
//            WorldData, WorldProvider, IChunkLoader, PlayerFileData

public interface IDataManager
{

    public abstract WorldData c();

    public abstract void b();

    public abstract IChunkLoader a(WorldProvider worldprovider);

    public abstract void a(WorldData worlddata, List list);

    public abstract void a(WorldData worlddata);

    public abstract PlayerFileData d();

    public abstract void e();

    public abstract File b(String s);

    public abstract UUID getUUID();
}
