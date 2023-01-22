// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntitySign.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            TileEntity, Packet130UpdateSign, NBTTagCompound, Packet

public class TileEntitySign extends TileEntity
{

    public TileEntitySign()
    {
        b = -1;
        isEditable = true;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.setString("Text1", lines[0]);
        nbttagcompound.setString("Text2", lines[1]);
        nbttagcompound.setString("Text3", lines[2]);
        nbttagcompound.setString("Text4", lines[3]);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        isEditable = false;
        super.a(nbttagcompound);
        for(int i = 0; i < 4; i++)
        {
            lines[i] = nbttagcompound.getString((new StringBuilder()).append("Text").append(i + 1).toString());
            if(lines[i].length() > 15)
                lines[i] = lines[i].substring(0, 15);
        }

    }

    public Packet l()
    {
        String astring[] = new String[4];
        for(int i = 0; i < 4; i++)
        {
            astring[i] = lines[i];
            if(lines[i].length() > 15)
                astring[i] = lines[i].substring(0, 15);
        }

        return new Packet130UpdateSign(x, y, z, astring);
    }

    public boolean a()
    {
        return isEditable;
    }

    public String lines[] = {
        "", "", "", ""
    };
    public int b;
    public boolean isEditable;
}
