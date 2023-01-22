// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.server:
//            NBTTagCompound, World, TileEntityFurnace, TileEntityChest, 
//            TileEntityRecordPlayer, TileEntityDispenser, TileEntitySign, TileEntityMobSpawner, 
//            TileEntityNote, TileEntityPiston, Block, Packet

public class TileEntity
{

    public TileEntity()
    {
        n = -1;
    }

    private static void a(Class class1, String s)
    {
        if(b.containsKey(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate id: ").append(s).toString());
        } else
        {
            a.put(s, class1);
            b.put(class1, s);
            return;
        }
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        x = nbttagcompound.e("x");
        y = nbttagcompound.e("y");
        z = nbttagcompound.e("z");
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        String s = (String)b.get(getClass());
        if(s == null)
        {
            throw new RuntimeException((new StringBuilder()).append(getClass()).append(" is missing a mapping! This is a bug!").toString());
        } else
        {
            nbttagcompound.setString("id", s);
            nbttagcompound.a("x", x);
            nbttagcompound.a("y", y);
            nbttagcompound.a("z", z);
            return;
        }
    }

    public void h_()
    {
    }

    public static TileEntity c(NBTTagCompound nbttagcompound)
    {
        TileEntity tileentity = null;
        try
        {
            Class class1 = (Class)a.get(nbttagcompound.getString("id"));
            if(class1 != null)
                tileentity = (TileEntity)class1.newInstance();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(tileentity != null)
            tileentity.a(nbttagcompound);
        else
            System.out.println((new StringBuilder()).append("Skipping TileEntity with id ").append(nbttagcompound.getString("id")).toString());
        return tileentity;
    }

    public int j()
    {
        if(n == -1)
            n = world.getData(x, y, z);
        return n;
    }

    public void update()
    {
        if(world != null)
        {
            n = world.getData(x, y, z);
            world.b(x, y, z, this);
        }
    }

    public Packet l()
    {
        return null;
    }

    public boolean m()
    {
        return m;
    }

    public void i()
    {
        m = true;
    }

    public void n()
    {
        m = false;
    }

    public void b(int k, int i1)
    {
    }

    public void g()
    {
        o = null;
        n = -1;
    }

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public World world;
    public int x;
    public int y;
    public int z;
    protected boolean m;
    public int n;
    public Block o;

    static 
    {
        a(net/minecraft/server/TileEntityFurnace, "Furnace");
        a(net/minecraft/server/TileEntityChest, "Chest");
        a(net/minecraft/server/TileEntityRecordPlayer, "RecordPlayer");
        a(net/minecraft/server/TileEntityDispenser, "Trap");
        a(net/minecraft/server/TileEntitySign, "Sign");
        a(net/minecraft/server/TileEntityMobSpawner, "MobSpawner");
        a(net/minecraft/server/TileEntityNote, "Music");
        a(net/minecraft/server/TileEntityPiston, "Piston");
    }
}
