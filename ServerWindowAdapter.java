// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Referenced classes of package net.minecraft.server:
//            MinecraftServer

final class ServerWindowAdapter extends WindowAdapter
{

    ServerWindowAdapter(MinecraftServer minecraftserver)
    {
        a = minecraftserver;
        super();
    }

    public void windowClosing(WindowEvent windowevent)
    {
        a.safeShutdown();
        while(!a.isStopped) 
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
        System.exit(0);
    }

    final MinecraftServer a;
}
