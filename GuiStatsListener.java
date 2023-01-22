// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package net.minecraft.server:
//            GuiStatsComponent

class GuiStatsListener
    implements ActionListener
{

    GuiStatsListener(GuiStatsComponent guistatscomponent)
    {
        a = guistatscomponent;
        super();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        GuiStatsComponent.a(a);
    }

    final GuiStatsComponent a;
}
