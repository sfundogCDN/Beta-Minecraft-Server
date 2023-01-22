// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.File;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            PlayerNBTManager, WorldProviderHell, ChunkRegionLoader, WorldData, 
//            RegionFileCache, WorldProvider, IChunkLoader

public class ServerNBTManager extends PlayerNBTManager
{

    public ServerNBTManager(File file, String s, boolean flag)
    {
        super(file, s, flag);
    }

    public IChunkLoader a(WorldProvider worldprovider)
    {
        File file = a();
        if(worldprovider instanceof WorldProviderHell)
        {
            File file1 = new File(file, "DIM-1");
            file1.mkdirs();
            return new ChunkRegionLoader(file1);
        } else
        {
            return new ChunkRegionLoader(file);
        }
    }

    public void a(WorldData worlddata, List list)
    {
        worlddata.a(19132);
        super.a(worlddata, list);
    }

    public void e()
    {
        RegionFileCache.a();
    }
}
