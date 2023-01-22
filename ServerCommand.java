// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ICommandListener

public class ServerCommand
{

    public ServerCommand(String s, ICommandListener icommandlistener)
    {
        command = s;
        b = icommandlistener;
    }

    public final String command;
    public final ICommandListener b;
}
