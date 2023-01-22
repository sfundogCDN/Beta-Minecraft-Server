// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.logging.*;
import javax.swing.JTextArea;
import javax.swing.text.Document;

// Referenced classes of package net.minecraft.server:
//            GuiLogFormatter

public class GuiLogOutputHandler extends Handler
{

    public GuiLogOutputHandler(JTextArea jtextarea)
    {
        b = new int[1024];
        c = 0;
        a = new GuiLogFormatter(this);
        setFormatter(a);
        d = jtextarea;
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public void publish(LogRecord logrecord)
    {
        int i = d.getDocument().getLength();
        d.append(a.format(logrecord));
        d.setCaretPosition(d.getDocument().getLength());
        int j = d.getDocument().getLength() - i;
        if(b[c] != 0)
            d.replaceRange("", 0, b[c]);
        b[c] = j;
        c = (c + 1) % 1024;
    }

    private int b[];
    private int c;
    Formatter a;
    private JTextArea d;
}
