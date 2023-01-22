// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.*;

// Referenced classes of package net.minecraft.server:
//            ChunkBuffer

public class RegionFile
{

    public RegionFile(File file)
    {
        h = 0L;
        b = file;
        b((new StringBuilder()).append("REGION LOAD ").append(b).toString());
        g = 0;
        try
        {
            if(file.exists())
                h = file.lastModified();
            c = new RandomAccessFile(file, "rw");
            if(c.length() < 4096L)
            {
                for(int i = 0; i < 1024; i++)
                    c.writeInt(0);

                for(int j = 0; j < 1024; j++)
                    c.writeInt(0);

                g += 8192;
            }
            if((c.length() & 4095L) != 0L)
            {
                for(int k = 0; (long)k < (c.length() & 4095L); k++)
                    c.write(0);

            }
            int l = (int)c.length() / 4096;
            f = new ArrayList(l);
            for(int i1 = 0; i1 < l; i1++)
                f.add(Boolean.valueOf(true));

            f.set(0, Boolean.valueOf(false));
            f.set(1, Boolean.valueOf(false));
            c.seek(0L);
            for(int j1 = 0; j1 < 1024; j1++)
            {
                int l1 = c.readInt();
                d[j1] = l1;
                if(l1 == 0 || (l1 >> 8) + (l1 & 0xff) > f.size())
                    continue;
                for(int j2 = 0; j2 < (l1 & 0xff); j2++)
                    f.set((l1 >> 8) + j2, Boolean.valueOf(false));

            }

            for(int k1 = 0; k1 < 1024; k1++)
            {
                int i2 = c.readInt();
                e[k1] = i2;
            }

        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public synchronized int a()
    {
        int i = g;
        g = 0;
        return i;
    }

    private void a(String s)
    {
    }

    private void b(String s)
    {
        a((new StringBuilder()).append(s).append("\n").toString());
    }

    private void a(String s, int i, int j, String s1)
    {
        a((new StringBuilder()).append("REGION ").append(s).append(" ").append(b.getName()).append("[").append(i).append(",").append(j).append("] = ").append(s1).toString());
    }

    private void a(String s, int i, int j, int k, String s1)
    {
        a((new StringBuilder()).append("REGION ").append(s).append(" ").append(b.getName()).append("[").append(i).append(",").append(j).append("] ").append(k).append("B = ").append(s1).toString());
    }

    private void b(String s, int i, int j, String s1)
    {
        a(s, i, j, (new StringBuilder()).append(s1).append("\n").toString());
    }

    public synchronized DataInputStream a(int i, int j)
    {
        if(d(i, j))
        {
            b("READ", i, j, "out of bounds");
            return null;
        }
        int k;
        k = e(i, j);
        if(k == 0)
            return null;
        int l;
        int i1;
        l = k >> 8;
        i1 = k & 0xff;
        if(l + i1 > f.size())
        {
            b("READ", i, j, "invalid sector");
            return null;
        }
        int j1;
        c.seek(l * 4096);
        j1 = c.readInt();
        if(j1 > 4096 * i1)
        {
            b("READ", i, j, (new StringBuilder()).append("invalid length: ").append(j1).append(" > 4096 * ").append(i1).toString());
            return null;
        }
        byte byte0;
        byte0 = c.readByte();
        if(byte0 == 1)
        {
            byte abyte0[] = new byte[j1 - 1];
            c.read(abyte0);
            DataInputStream datainputstream = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte0)));
            return datainputstream;
        }
        if(byte0 == 2)
        {
            byte abyte1[] = new byte[j1 - 1];
            c.read(abyte1);
            DataInputStream datainputstream1 = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(abyte1)));
            return datainputstream1;
        }
        try
        {
            b("READ", i, j, (new StringBuilder()).append("unknown version ").append(byte0).toString());
            return null;
        }
        catch(IOException ioexception)
        {
            b("READ", i, j, "exception");
        }
        return null;
    }

    public DataOutputStream b(int i, int j)
    {
        if(d(i, j))
            return null;
        else
            return new DataOutputStream(new DeflaterOutputStream(new ChunkBuffer(this, i, j)));
    }

    protected synchronized void a(int i, int j, byte abyte0[], int k)
    {
        int i1;
        int l1;
        int i2;
        int l = e(i, j);
        i1 = l >> 8;
        l1 = l & 0xff;
        i2 = (k + 5) / 4096 + 1;
        if(i2 >= 256)
            return;
        try
        {
            if(i1 != 0 && l1 == i2)
            {
                a("SAVE", i, j, k, "rewrite");
                a(i1, abyte0, k);
            } else
            {
                for(int j2 = 0; j2 < l1; j2++)
                    f.set(i1 + j2, Boolean.valueOf(true));

                int k2 = f.indexOf(Boolean.valueOf(true));
                int l2 = 0;
                if(k2 != -1)
                {
                    int i3 = k2;
                    do
                    {
                        if(i3 >= f.size())
                            break;
                        if(l2 != 0)
                        {
                            if(((Boolean)f.get(i3)).booleanValue())
                                l2++;
                            else
                                l2 = 0;
                        } else
                        if(((Boolean)f.get(i3)).booleanValue())
                        {
                            k2 = i3;
                            l2 = 1;
                        }
                        if(l2 >= i2)
                            break;
                        i3++;
                    } while(true);
                }
                if(l2 >= i2)
                {
                    a("SAVE", i, j, k, "reuse");
                    int j1 = k2;
                    a(i, j, j1 << 8 | i2);
                    for(int j3 = 0; j3 < i2; j3++)
                        f.set(j1 + j3, Boolean.valueOf(false));

                    a(j1, abyte0, k);
                } else
                {
                    a("SAVE", i, j, k, "grow");
                    c.seek(c.length());
                    int k1 = f.size();
                    for(int k3 = 0; k3 < i2; k3++)
                    {
                        c.write(a);
                        f.add(Boolean.valueOf(false));
                    }

                    g += 4096 * i2;
                    a(k1, abyte0, k);
                    a(i, j, k1 << 8 | i2);
                }
            }
            b(i, j, (int)(System.currentTimeMillis() / 1000L));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return;
    }

    private void a(int i, byte abyte0[], int j)
    {
        b((new StringBuilder()).append(" ").append(i).toString());
        c.seek(i * 4096);
        c.writeInt(j + 1);
        c.writeByte(2);
        c.write(abyte0, 0, j);
    }

    private boolean d(int i, int j)
    {
        return i < 0 || i >= 32 || j < 0 || j >= 32;
    }

    private int e(int i, int j)
    {
        return d[i + j * 32];
    }

    public boolean c(int i, int j)
    {
        return e(i, j) != 0;
    }

    private void a(int i, int j, int k)
    {
        d[i + j * 32] = k;
        c.seek((i + j * 32) * 4);
        c.writeInt(k);
    }

    private void b(int i, int j, int k)
    {
        e[i + j * 32] = k;
        c.seek(4096 + (i + j * 32) * 4);
        c.writeInt(k);
    }

    public void b()
    {
        c.close();
    }

    private static final byte a[] = new byte[4096];
    private final File b;
    private RandomAccessFile c;
    private final int d[] = new int[1024];
    private final int e[] = new int[1024];
    private ArrayList f;
    private int g;
    private long h;

}
