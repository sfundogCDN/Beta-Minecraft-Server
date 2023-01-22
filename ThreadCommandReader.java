// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadCommandReader.java

package net.minecraft.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.ConsoleReader;
import org.bukkit.craftbukkit.Main;

// Referenced classes of package net.minecraft.server:
//            MinecraftServer

public class ThreadCommandReader extends Thread
{

    public ThreadCommandReader(MinecraftServer minecraftserver)
    {
        server = minecraftserver;
    }

    public void run()
    {
        ConsoleReader bufferedreader = server.reader;
        String s = null;
        try
        {
            do
            {
                if(server.isStopped || !MinecraftServer.isRunning(server))
                    break;
                if(Main.useJline)
                    s = bufferedreader.readLine(">", null);
                else
                    s = bufferedreader.readLine();
                if(s != null)
                    server.issueCommand(s, server);
            } while(true);
        }
        catch(IOException ioexception)
        {
            Logger.getLogger(net/minecraft/server/ThreadCommandReader.getName()).log(Level.SEVERE, null, ioexception);
        }
    }

    final MinecraftServer server;
}
