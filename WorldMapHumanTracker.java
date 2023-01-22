// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorldMapHumanTracker.java

package net.minecraft.server;

import java.util.ArrayList;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.map.CraftMapView;
import org.bukkit.craftbukkit.map.RenderData;
import org.bukkit.map.MapCursor;

// Referenced classes of package net.minecraft.server:
//            WorldMap, EntityHuman, ItemStack

public class WorldMapHumanTracker
{

    public WorldMapHumanTracker(WorldMap worldmap, EntityHuman entityhuman)
    {
        d = worldmap;
        b = new int[128];
        c = new int[128];
        e = 0;
        f = 0;
        trackee = entityhuman;
        for(int i = 0; i < b.length; i++)
        {
            b[i] = 0;
            c[i] = 127;
        }

    }

    public byte[] a(ItemStack itemstack)
    {
        RenderData render = d.mapView.render((CraftPlayer)trackee.getBukkitEntity());
        if(--f < 0)
        {
            f = 4;
            byte abyte[] = new byte[render.cursors.size() * 3 + 1];
            abyte[0] = 1;
            for(int i = 0; i < render.cursors.size(); i++)
            {
                MapCursor cursor = (MapCursor)render.cursors.get(i);
                if(cursor.isVisible())
                {
                    byte value = (byte)(((cursor.getRawType() != 0 && cursor.getDirection() >= 8 ? cursor.getDirection() - 1 : cursor.getDirection()) & 0xf) * 16);
                    abyte[i * 3 + 1] = (byte)(value | (cursor.getRawType() == 0 || value >= 0 ? cursor.getRawType() : 16 - cursor.getRawType()));
                    abyte[i * 3 + 2] = cursor.getX();
                    abyte[i * 3 + 3] = cursor.getY();
                }
            }

            boolean flag = true;
            if(g != null && g.length == abyte.length)
            {
                int j = 0;
                do
                {
                    if(j >= abyte.length)
                        break;
                    if(abyte[j] != g[j])
                    {
                        flag = false;
                        break;
                    }
                    j++;
                } while(true);
            } else
            {
                flag = false;
            }
            if(!flag)
            {
                g = abyte;
                return abyte;
            }
        }
        for(int k = 0; k < 10; k++)
        {
            int i = (e * 11) % 128;
            e++;
            if(b[i] >= 0)
            {
                int j = (c[i] - b[i]) + 1;
                int l = b[i];
                byte abyte1[] = new byte[j + 3];
                abyte1[0] = 0;
                abyte1[1] = (byte)i;
                abyte1[2] = (byte)l;
                for(int i1 = 0; i1 < abyte1.length - 3; i1++)
                    abyte1[i1 + 3] = render.buffer[(i1 + l) * 128 + i];

                c[i] = -1;
                b[i] = -1;
                return abyte1;
            }
        }

        return null;
    }

    public final EntityHuman trackee;
    public int b[];
    public int c[];
    private int e;
    private int f;
    private byte g[];
    final WorldMap d;
}
