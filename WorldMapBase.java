// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            NBTTagCompound

public abstract class WorldMapBase
{

    public WorldMapBase(String s)
    {
        a = s;
    }

    public abstract void a(NBTTagCompound nbttagcompound);

    public abstract void b(NBTTagCompound nbttagcompound);

    public void a()
    {
        a(true);
    }

    public void a(boolean flag)
    {
        b = flag;
    }

    public boolean b()
    {
        return b;
    }

    public final String a;
    private boolean b;
}
