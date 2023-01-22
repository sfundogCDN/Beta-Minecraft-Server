// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

// Referenced classes of package net.minecraft.server:
//            ICommandListener, ServerWindowAdapter, GuiStatsComponent, PlayerListBox, 
//            GuiLogOutputHandler, ServerGuiCommandListener, ServerGuiFocusAdapter, MinecraftServer

public class ServerGUI extends JComponent
    implements ICommandListener
{

    public static void a(MinecraftServer minecraftserver)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception exception) { }
        ServerGUI servergui = new ServerGUI(minecraftserver);
        JFrame jframe = new JFrame("Minecraft server");
        jframe.add(servergui);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowListener(new ServerWindowAdapter(minecraftserver));
    }

    public ServerGUI(MinecraftServer minecraftserver)
    {
        b = minecraftserver;
        setPreferredSize(new Dimension(854, 480));
        setLayout(new BorderLayout());
        try
        {
            add(c(), "Center");
            add(a(), "West");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private JComponent a()
    {
        JPanel jpanel = new JPanel(new BorderLayout());
        jpanel.add(new GuiStatsComponent(), "North");
        jpanel.add(b(), "Center");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return jpanel;
    }

    private JComponent b()
    {
        PlayerListBox playerlistbox = new PlayerListBox(b);
        JScrollPane jscrollpane = new JScrollPane(playerlistbox, 22, 30);
        jscrollpane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return jscrollpane;
    }

    private JComponent c()
    {
        JPanel jpanel = new JPanel(new BorderLayout());
        JTextArea jtextarea = new JTextArea();
        a.addHandler(new GuiLogOutputHandler(jtextarea));
        JScrollPane jscrollpane = new JScrollPane(jtextarea, 22, 30);
        jtextarea.setEditable(false);
        JTextField jtextfield = new JTextField();
        jtextfield.addActionListener(new ServerGuiCommandListener(this, jtextfield));
        jtextarea.addFocusListener(new ServerGuiFocusAdapter(this));
        jpanel.add(jscrollpane, "Center");
        jpanel.add(jtextfield, "South");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        return jpanel;
    }

    public void sendMessage(String s)
    {
        a.info(s);
    }

    public String getName()
    {
        return "CONSOLE";
    }

    static MinecraftServer a(ServerGUI servergui)
    {
        return servergui.b;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    private MinecraftServer b;

}
