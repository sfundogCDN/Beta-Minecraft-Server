// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            TileEntity, NBTTagCompound, World, Material

public class TileEntityNote extends TileEntity
{

    public TileEntityNote()
    {
        note = 0;
        b = false;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("note", note);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        note = nbttagcompound.c("note");
        if(note < 0)
            note = 0;
        if(note > 24)
            note = 24;
    }

    public void a()
    {
        note = (byte)((note + 1) % 25);
        update();
    }

    public void play(World world, int i, int j, int k)
    {
        if(world.getMaterial(i, j + 1, k) != Material.AIR)
            return;
        Material material = world.getMaterial(i, j - 1, k);
        byte byte0 = 0;
        if(material == Material.STONE)
            byte0 = 1;
        if(material == Material.SAND)
            byte0 = 2;
        if(material == Material.SHATTERABLE)
            byte0 = 3;
        if(material == Material.WOOD)
            byte0 = 4;
        world.playNote(i, j, k, byte0, note);
    }

    public byte note;
    public boolean b;
}
