// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WorldMapBase, IDataManager, CompressedStreamTools, NBTTagCompound, 
//            NBTBase, NBTTagShort

public class WorldMapCollection
{

    public WorldMapCollection(IDataManager idatamanager)
    {
        b = new HashMap();
        c = new ArrayList();
        d = new HashMap();
        a = idatamanager;
        b();
    }

    public WorldMapBase a(Class class1, String s)
    {
        WorldMapBase worldmapbase = (WorldMapBase)b.get(s);
        if(worldmapbase != null)
            return worldmapbase;
        if(a != null)
            try
            {
                File file = a.b(s);
                if(file != null && file.exists())
                {
                    try
                    {
                        worldmapbase = (WorldMapBase)class1.getConstructor(new Class[] {
                            java/lang/String
                        }).newInstance(new Object[] {
                            s
                        });
                    }
                    catch(Exception exception1)
                    {
                        throw new RuntimeException((new StringBuilder()).append("Failed to instantiate ").append(class1.toString()).toString(), exception1);
                    }
                    FileInputStream fileinputstream = new FileInputStream(file);
                    NBTTagCompound nbttagcompound = CompressedStreamTools.a(fileinputstream);
                    fileinputstream.close();
                    worldmapbase.a(nbttagcompound.k("data"));
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        if(worldmapbase != null)
        {
            b.put(s, worldmapbase);
            c.add(worldmapbase);
        }
        return worldmapbase;
    }

    public void a(String s, WorldMapBase worldmapbase)
    {
        if(worldmapbase == null)
            throw new RuntimeException("Can't set null data");
        if(b.containsKey(s))
            c.remove(b.remove(s));
        b.put(s, worldmapbase);
        c.add(worldmapbase);
    }

    public void a()
    {
        for(int i = 0; i < c.size(); i++)
        {
            WorldMapBase worldmapbase = (WorldMapBase)c.get(i);
            if(worldmapbase.b())
            {
                a(worldmapbase);
                worldmapbase.a(false);
            }
        }

    }

    private void a(WorldMapBase worldmapbase)
    {
        if(a == null)
            return;
        try
        {
            File file = a.b(worldmapbase.a);
            if(file != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                worldmapbase.b(nbttagcompound);
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.a("data", nbttagcompound);
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                CompressedStreamTools.a(nbttagcompound1, fileoutputstream);
                fileoutputstream.close();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void b()
    {
        d.clear();
        if(a == null)
            return;
        try
        {
            File file = a.b("idcounts");
            if(file != null && file.exists())
            {
                DataInputStream datainputstream = new DataInputStream(new FileInputStream(file));
                NBTTagCompound nbttagcompound = CompressedStreamTools.a(datainputstream);
                datainputstream.close();
                Iterator iterator = nbttagcompound.c().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    NBTBase nbtbase = (NBTBase)iterator.next();
                    if(nbtbase instanceof NBTTagShort)
                    {
                        NBTTagShort nbttagshort = (NBTTagShort)nbtbase;
                        String s = nbttagshort.b();
                        short word0 = nbttagshort.a;
                        d.put(s, Short.valueOf(word0));
                    }
                } while(true);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return;
    }

    public int a(String s)
    {
        Short short1 = (Short)d.get(s);
        if(short1 == null)
        {
            short1 = Short.valueOf((short)0);
        } else
        {
            Short short2 = short1;
            Short short3 = short1 = Short.valueOf((short)(short1.shortValue() + 1));
            Short _tmp = short2;
        }
        d.put(s, short1);
        if(a == null)
            return short1.shortValue();
        try
        {
            File file = a.b("idcounts");
            if(file != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                String s1;
                short word0;
                for(Iterator iterator = d.keySet().iterator(); iterator.hasNext(); nbttagcompound.a(s1, word0))
                {
                    s1 = (String)iterator.next();
                    word0 = ((Short)d.get(s1)).shortValue();
                }

                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file));
                CompressedStreamTools.a(nbttagcompound, dataoutputstream);
                dataoutputstream.close();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return short1.shortValue();
    }

    private IDataManager a;
    private Map b;
    private List c;
    private Map d;
}
