// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

// Referenced classes of package net.minecraft.server:
//            ServerGUI, MinecraftServer

class ServerGuiCommandListener
    implements ActionListener
{

    ServerGuiCommandListener(ServerGUI servergui, JTextField jtextfield)
    {
        b = servergui;
        a = jtextfield;
        super();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        String s = a.getText().trim();
        if(s.length() > 0)
            ServerGUI.a(b).issueCommand(s, b);
        a.setText("");
    }

    final JTextField a;
    final ServerGUI b;
}
