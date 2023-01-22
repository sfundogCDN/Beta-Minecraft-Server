// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            TileEntity, NBTTagCompound

public class TileEntityRecordPlayer extends TileEntity
{

    public TileEntityRecordPlayer()
    {
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        a = nbttagcompound.e("Record");
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        if(a > 0)
            nbttagcompound.a("Record", a);
    }

    public int a;
}
