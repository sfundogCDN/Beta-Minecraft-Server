// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            WatchableObject, Packet, ItemStack, Item, 
//            ChunkCoordinates

public class DataWatcher
{

    public DataWatcher()
    {
    }

    public void a(int i, Object obj)
    {
        Integer integer = (Integer)a.get(obj.getClass());
        if(integer == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown data type: ").append(obj.getClass()).toString());
        if(i > 31)
            throw new IllegalArgumentException((new StringBuilder()).append("Data value id is too big with ").append(i).append("! (Max is ").append(31).append(")").toString());
        if(b.containsKey(Integer.valueOf(i)))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate id value for ").append(i).append("!").toString());
        } else
        {
            WatchableObject watchableobject = new WatchableObject(integer.intValue(), i, obj);
            b.put(Integer.valueOf(i), watchableobject);
            return;
        }
    }

    public byte getByte(int i)
    {
        return ((Byte)((WatchableObject)b.get(Integer.valueOf(i))).b()).byteValue();
    }

    public int getInt(int i)
    {
        return ((Integer)((WatchableObject)b.get(Integer.valueOf(i))).b()).intValue();
    }

    public String getString(int i)
    {
        return (String)((WatchableObject)b.get(Integer.valueOf(i))).b();
    }

    public void watch(int i, Object obj)
    {
        WatchableObject watchableobject = (WatchableObject)b.get(Integer.valueOf(i));
        if(!obj.equals(watchableobject.b()))
        {
            watchableobject.a(obj);
            watchableobject.a(true);
            c = true;
        }
    }

    public boolean a()
    {
        return c;
    }

    public static void a(List list, DataOutputStream dataoutputstream)
    {
        if(list != null)
        {
            WatchableObject watchableobject;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); a(dataoutputstream, watchableobject))
                watchableobject = (WatchableObject)iterator.next();

        }
        dataoutputstream.writeByte(127);
    }

    public ArrayList b()
    {
        ArrayList arraylist = null;
        if(c)
        {
            Iterator iterator = b.values().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WatchableObject watchableobject = (WatchableObject)iterator.next();
                if(watchableobject.d())
                {
                    watchableobject.a(false);
                    if(arraylist == null)
                        arraylist = new ArrayList();
                    arraylist.add(watchableobject);
                }
            } while(true);
        }
        c = false;
        return arraylist;
    }

    public void a(DataOutputStream dataoutputstream)
    {
        WatchableObject watchableobject;
        for(Iterator iterator = b.values().iterator(); iterator.hasNext(); a(dataoutputstream, watchableobject))
            watchableobject = (WatchableObject)iterator.next();

        dataoutputstream.writeByte(127);
    }

    private static void a(DataOutputStream dataoutputstream, WatchableObject watchableobject)
    {
        int i = (watchableobject.c() << 5 | watchableobject.a() & 0x1f) & 0xff;
        dataoutputstream.writeByte(i);
        switch(watchableobject.c())
        {
        case 0: // '\0'
            dataoutputstream.writeByte(((Byte)watchableobject.b()).byteValue());
            break;

        case 1: // '\001'
            dataoutputstream.writeShort(((Short)watchableobject.b()).shortValue());
            break;

        case 2: // '\002'
            dataoutputstream.writeInt(((Integer)watchableobject.b()).intValue());
            break;

        case 3: // '\003'
            dataoutputstream.writeFloat(((Float)watchableobject.b()).floatValue());
            break;

        case 4: // '\004'
            Packet.a((String)watchableobject.b(), dataoutputstream);
            break;

        case 5: // '\005'
            ItemStack itemstack = (ItemStack)watchableobject.b();
            dataoutputstream.writeShort(itemstack.getItem().id);
            dataoutputstream.writeByte(itemstack.count);
            dataoutputstream.writeShort(itemstack.getData());
            break;

        case 6: // '\006'
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates)watchableobject.b();
            dataoutputstream.writeInt(chunkcoordinates.x);
            dataoutputstream.writeInt(chunkcoordinates.y);
            dataoutputstream.writeInt(chunkcoordinates.z);
            break;
        }
    }

    public static List a(DataInputStream datainputstream)
    {
        ArrayList arraylist = null;
        for(byte byte0 = datainputstream.readByte(); byte0 != 127; byte0 = datainputstream.readByte())
        {
            if(arraylist == null)
                arraylist = new ArrayList();
            int i = (byte0 & 0xe0) >> 5;
            int j = byte0 & 0x1f;
            WatchableObject watchableobject = null;
            switch(i)
            {
            case 0: // '\0'
                watchableobject = new WatchableObject(i, j, Byte.valueOf(datainputstream.readByte()));
                break;

            case 1: // '\001'
                watchableobject = new WatchableObject(i, j, Short.valueOf(datainputstream.readShort()));
                break;

            case 2: // '\002'
                watchableobject = new WatchableObject(i, j, Integer.valueOf(datainputstream.readInt()));
                break;

            case 3: // '\003'
                watchableobject = new WatchableObject(i, j, Float.valueOf(datainputstream.readFloat()));
                break;

            case 4: // '\004'
                watchableobject = new WatchableObject(i, j, Packet.a(datainputstream, 64));
                break;

            case 5: // '\005'
                short word0 = datainputstream.readShort();
                byte byte1 = datainputstream.readByte();
                short word1 = datainputstream.readShort();
                watchableobject = new WatchableObject(i, j, new ItemStack(word0, byte1, word1));
                break;

            case 6: // '\006'
                int k = datainputstream.readInt();
                int l = datainputstream.readInt();
                int i1 = datainputstream.readInt();
                watchableobject = new WatchableObject(i, j, new ChunkCoordinates(k, l, i1));
                break;
            }
            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    private static final HashMap a;
    private final Map b = new HashMap();
    private boolean c;

    static 
    {
        a = new HashMap();
        a.put(java/lang/Byte, Integer.valueOf(0));
        a.put(java/lang/Short, Integer.valueOf(1));
        a.put(java/lang/Integer, Integer.valueOf(2));
        a.put(java/lang/Float, Integer.valueOf(3));
        a.put(java/lang/String, Integer.valueOf(4));
        a.put(net/minecraft/server/ItemStack, Integer.valueOf(5));
        a.put(net/minecraft/server/ChunkCoordinates, Integer.valueOf(6));
    }
}
