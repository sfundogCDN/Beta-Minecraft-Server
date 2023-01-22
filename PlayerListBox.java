// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;
import java.util.Vector;
import javax.swing.JList;

// Referenced classes of package net.minecraft.server:
//            IUpdatePlayerListBox, MinecraftServer, ServerConfigurationManager, EntityPlayer

public class PlayerListBox extends JList
    implements IUpdatePlayerListBox
{

    public PlayerListBox(MinecraftServer minecraftserver)
    {
        b = 0;
        a = minecraftserver;
        minecraftserver.a(this);
    }

    public void a()
    {
        if(b++ % 20 == 0)
        {
            Vector vector = new Vector();
            for(int i = 0; i < a.serverConfigurationManager.players.size(); i++)
                vector.add(((EntityPlayer)a.serverConfigurationManager.players.get(i)).name);

            setListData(vector);
        }
    }

    private MinecraftServer a;
    private int b;
}
