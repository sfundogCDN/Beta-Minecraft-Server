// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyManager.java

package net.minecraft.server;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import joptsimple.OptionSet;

public class PropertyManager
{

    public PropertyManager(File file1)
    {
        properties = new Properties();
        options = null;
        c = file1;
        if(file1.exists())
        {
            try
            {
                properties.load(new FileInputStream(file1));
            }
            catch(Exception exception)
            {
                a.log(Level.WARNING, (new StringBuilder()).append("Failed to load ").append(file1).toString(), exception);
                a();
            }
        } else
        {
            a.log(Level.WARNING, (new StringBuilder()).append(file1).append(" does not exist").toString());
            a();
        }
    }

    public PropertyManager(OptionSet options)
    {
        this((File)options.valueOf("config"));
        this.options = options;
    }

    private Object getOverride(String name, Object value)
    {
        if(options != null && options.has(name))
            return options.valueOf(name);
        else
            return value;
    }

    public void a()
    {
        a.log(Level.INFO, "Generating new properties file");
        savePropertiesFile();
    }

    public void savePropertiesFile()
    {
        try
        {
            properties.store(new FileOutputStream(c), "Minecraft server properties");
        }
        catch(Exception exception)
        {
            a.log(Level.WARNING, (new StringBuilder()).append("Failed to save ").append(c).toString(), exception);
            a();
        }
    }

    public String getString(String s, String s1)
    {
        if(!properties.containsKey(s))
        {
            s1 = (String)getOverride(s, s1);
            properties.setProperty(s, s1);
            savePropertiesFile();
        }
        return (String)getOverride(s, properties.getProperty(s, s1));
    }

    public int getInt(String s, int i)
    {
        try
        {
            return ((Integer)getOverride(s, Integer.valueOf(Integer.parseInt(getString(s, (new StringBuilder()).append("").append(i).toString()))))).intValue();
        }
        catch(Exception exception)
        {
            i = ((Integer)getOverride(s, Integer.valueOf(i))).intValue();
        }
        properties.setProperty(s, (new StringBuilder()).append("").append(i).toString());
        return i;
    }

    public boolean getBoolean(String s, boolean flag)
    {
        try
        {
            return ((Boolean)getOverride(s, Boolean.valueOf(Boolean.parseBoolean(getString(s, (new StringBuilder()).append("").append(flag).toString()))))).booleanValue();
        }
        catch(Exception exception)
        {
            flag = ((Boolean)getOverride(s, Boolean.valueOf(flag))).booleanValue();
        }
        properties.setProperty(s, (new StringBuilder()).append("").append(flag).toString());
        return flag;
    }

    public void setBoolean(String s, boolean flag)
    {
        properties.setProperty(s, (new StringBuilder()).append("").append(flag).toString());
        savePropertiesFile();
    }

    public static Logger a = Logger.getLogger("Minecraft");
    public Properties properties;
    private File c;
    private OptionSet options;

}
