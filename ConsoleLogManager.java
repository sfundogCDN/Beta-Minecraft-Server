// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsoleLogManager.java

package net.minecraft.server;

import java.util.logging.*;
import joptsimple.OptionSet;
import org.bukkit.craftbukkit.util.ShortConsoleLogFormatter;
import org.bukkit.craftbukkit.util.TerminalConsoleHandler;

// Referenced classes of package net.minecraft.server:
//            ConsoleLogFormatter, MinecraftServer

public class ConsoleLogManager
{

    public ConsoleLogManager()
    {
    }

    public static void init(MinecraftServer server)
    {
        ConsoleLogFormatter consolelogformatter = new ConsoleLogFormatter();
        a.setUseParentHandlers(false);
        ConsoleHandler consolehandler = new TerminalConsoleHandler(server.reader);
        java.util.logging.Handler arr$[] = global.getHandlers();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            java.util.logging.Handler handler = arr$[i$];
            global.removeHandler(handler);
        }

        consolehandler.setFormatter(new ShortConsoleLogFormatter(server));
        global.addHandler(consolehandler);
        a.addHandler(consolehandler);
        try
        {
            String pattern = (String)server.options.valueOf("log-pattern");
            int limit = ((Integer)server.options.valueOf("log-limit")).intValue();
            int count = ((Integer)server.options.valueOf("log-count")).intValue();
            boolean append = ((Boolean)server.options.valueOf("log-append")).booleanValue();
            FileHandler filehandler = new FileHandler(pattern, limit, count, append);
            filehandler.setFormatter(consolelogformatter);
            a.addHandler(filehandler);
            global.addHandler(filehandler);
        }
        catch(Exception exception)
        {
            a.log(Level.WARNING, "Failed to log to server.log", exception);
        }
    }

    public static Logger a = Logger.getLogger("Minecraft");
    public static Logger global = Logger.getLogger("");

}
