// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Referenced classes of package net.minecraft.server:
//            ServerGUI

class ServerGuiFocusAdapter extends FocusAdapter
{

    ServerGuiFocusAdapter(ServerGUI servergui)
    {
        a = servergui;
        super();
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    final ServerGUI a;
}
