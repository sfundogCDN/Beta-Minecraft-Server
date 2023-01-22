// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Block

public class BlockRegister
{

    public BlockRegister()
    {
    }

    public static void a(byte abyte0[])
    {
        for(int i = 0; i < abyte0.length; i++)
            abyte0[i] = a[abyte0[i] & 0xff];

    }

    private static byte a[];

    static 
    {
        a = new byte[256];
        try
        {
            for(int i = 0; i < 256; i++)
            {
                byte byte0 = (byte)i;
                if(byte0 != 0 && Block.byId[byte0 & 0xff] == null)
                    byte0 = 0;
                a[i] = byte0;
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
