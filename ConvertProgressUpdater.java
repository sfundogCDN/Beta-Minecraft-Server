// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.logging.Logger;

// Referenced classes of package net.minecraft.server:
//            IProgressUpdate, MinecraftServer

public class ConvertProgressUpdater
    implements IProgressUpdate
{

    public ConvertProgressUpdater(MinecraftServer minecraftserver)
    {
        a = minecraftserver;
        super();
        b = System.currentTimeMillis();
    }

    public void a(String s)
    {
    }

    public void a(int i)
    {
        if(System.currentTimeMillis() - b >= 1000L)
        {
            b = System.currentTimeMillis();
            MinecraftServer.log.info((new StringBuilder()).append("Converting... ").append(i).append("%").toString());
        }
    }

    public void b(String s)
    {
    }

    private long b;
    final MinecraftServer a;
}
