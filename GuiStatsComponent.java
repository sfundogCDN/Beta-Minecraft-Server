// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.Timer;

// Referenced classes of package net.minecraft.server:
//            GuiStatsListener, NetworkManager

public class GuiStatsComponent extends JComponent
{

    public GuiStatsComponent()
    {
        a = new int[256];
        b = 0;
        c = new String[10];
        setPreferredSize(new Dimension(256, 196));
        setMinimumSize(new Dimension(256, 196));
        setMaximumSize(new Dimension(256, 196));
        (new Timer(500, new GuiStatsListener(this))).start();
        setBackground(Color.BLACK);
    }

    private void a()
    {
        long l = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.gc();
        c[0] = (new StringBuilder()).append("Memory use: ").append(l / 1024L / 1024L).append(" mb (").append((Runtime.getRuntime().freeMemory() * 100L) / Runtime.getRuntime().maxMemory()).append("% free)").toString();
        c[1] = (new StringBuilder()).append("Threads: ").append(NetworkManager.b).append(" + ").append(NetworkManager.c).toString();
        a[b++ & 0xff] = (int)((l * 100L) / Runtime.getRuntime().maxMemory());
        repaint();
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(0xffffff));
        g.fillRect(0, 0, 256, 192);
        for(int i = 0; i < 256; i++)
        {
            int k = a[i + b & 0xff];
            g.setColor(new Color(k + 28 << 16));
            g.fillRect(i, 100 - k, 1, k);
        }

        g.setColor(Color.BLACK);
        for(int j = 0; j < c.length; j++)
        {
            String s = c[j];
            if(s != null)
                g.drawString(s, 32, 116 + j * 16);
        }

    }

    static void a(GuiStatsComponent guistatscomponent)
    {
        guistatscomponent.a();
    }

    private int a[];
    private int b;
    private String c[];
}
