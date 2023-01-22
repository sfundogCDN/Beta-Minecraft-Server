// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetworkManager.java

package net.minecraft.server;

import java.io.*;
import java.net.*;
import java.util.*;

// Referenced classes of package net.minecraft.server:
//            NetworkReaderThread, NetworkWriterThread, Packet, NetworkMasterThread, 
//            ThreadMonitorConnection, NetHandler

public class NetworkManager
{

    public NetworkManager(Socket socket, String s, NetHandler nethandler)
    {
        g = new Object();
        l = true;
        m = Collections.synchronizedList(new ArrayList());
        highPriorityQueue = Collections.synchronizedList(new ArrayList());
        lowPriorityQueue = Collections.synchronizedList(new ArrayList());
        q = false;
        t = false;
        u = "";
        w = 0;
        x = 0;
        f = 0;
        lowPriorityQueueDelay = 50;
        this.socket = socket;
        i = socket.getRemoteSocketAddress();
        p = nethandler;
        try
        {
            socket.setTrafficClass(24);
        }
        catch(SocketException e) { }
        try
        {
            socket.setSoTimeout(30000);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
        }
        catch(IOException socketexception)
        {
            System.err.println(socketexception.getMessage());
        }
        this.s = new NetworkReaderThread(this, (new StringBuilder()).append(s).append(" read thread").toString());
        r = new NetworkWriterThread(this, (new StringBuilder()).append(s).append(" write thread").toString());
        this.s.start();
        r.start();
    }

    public void a(NetHandler nethandler)
    {
        p = nethandler;
    }

    public void queue(Packet packet)
    {
        if(!q)
        {
            Object object = g;
            synchronized(g)
            {
                x += packet.a() + 1;
                if(packet.k)
                    lowPriorityQueue.add(packet);
                else
                    highPriorityQueue.add(packet);
            }
        }
    }

    private boolean g()
    {
        boolean flag = false;
        try
        {
            if(!highPriorityQueue.isEmpty() && (f == 0 || System.currentTimeMillis() - ((Packet)highPriorityQueue.get(0)).timestamp >= (long)f))
            {
                Object object = g;
                Packet packet;
                synchronized(g)
                {
                    packet = (Packet)highPriorityQueue.remove(0);
                    x -= packet.a() + 1;
                }
                Packet.a(packet, output);
                int aint[] = e;
                int i = packet.b();
                aint[i] += packet.a() + 1;
                flag = true;
            }
            if((flag || lowPriorityQueueDelay-- <= 0) && !lowPriorityQueue.isEmpty() && (highPriorityQueue.isEmpty() || ((Packet)highPriorityQueue.get(0)).timestamp > ((Packet)lowPriorityQueue.get(0)).timestamp))
            {
                Object object = g;
                Packet packet;
                synchronized(g)
                {
                    packet = (Packet)lowPriorityQueue.remove(0);
                    x -= packet.a() + 1;
                }
                Packet.a(packet, output);
                int aint[] = e;
                int i = packet.b();
                aint[i] += packet.a() + 1;
                lowPriorityQueueDelay = 0;
                flag = true;
            }
            return flag;
        }
        catch(Exception exception)
        {
            if(!t)
                a(exception);
        }
        return false;
    }

    public void a()
    {
        s.interrupt();
        r.interrupt();
    }

    private boolean h()
    {
        boolean flag = false;
        try
        {
            Packet packet = Packet.a(input, p.c());
            if(packet != null)
            {
                int aint[] = d;
                int i = packet.b();
                aint[i] += packet.a() + 1;
                m.add(packet);
                flag = true;
            } else
            {
                a("disconnect.endOfStream", new Object[0]);
            }
            return flag;
        }
        catch(Exception exception)
        {
            if(!t)
                a(exception);
        }
        return false;
    }

    private void a(Exception exception)
    {
        exception.printStackTrace();
        a("disconnect.genericReason", new Object[] {
            (new StringBuilder()).append("Internal exception: ").append(exception.toString()).toString()
        });
    }

    public transient void a(String s, Object aobject[])
    {
        if(l)
        {
            t = true;
            u = s;
            v = aobject;
            (new NetworkMasterThread(this)).start();
            l = false;
            try
            {
                input.close();
                input = null;
            }
            catch(Throwable throwable) { }
            try
            {
                output.close();
                output = null;
            }
            catch(Throwable throwable1) { }
            try
            {
                socket.close();
                socket = null;
            }
            catch(Throwable throwable2) { }
        }
    }

    public void b()
    {
        if(x > 0x100000)
            a("disconnect.overflow", new Object[0]);
        if(m.isEmpty())
        {
            if(w++ == 1200)
                a("disconnect.timeout", new Object[0]);
        } else
        {
            w = 0;
        }
        Packet packet;
        for(int i = 1000; !m.isEmpty() && i-- >= 0; packet.a(p))
            packet = (Packet)m.remove(0);

        a();
        if(t && m.isEmpty())
            p.a(u, v);
    }

    public SocketAddress getSocketAddress()
    {
        return i;
    }

    public void d()
    {
        a();
        q = true;
        s.interrupt();
        (new ThreadMonitorConnection(this)).start();
    }

    public int e()
    {
        return lowPriorityQueue.size();
    }

    public Socket f()
    {
        return socket;
    }

    static boolean a(NetworkManager networkmanager)
    {
        return networkmanager.l;
    }

    static boolean b(NetworkManager networkmanager)
    {
        return networkmanager.q;
    }

    static boolean c(NetworkManager networkmanager)
    {
        return networkmanager.h();
    }

    static boolean d(NetworkManager networkmanager)
    {
        return networkmanager.g();
    }

    static DataOutputStream e(NetworkManager networkmanager)
    {
        return networkmanager.output;
    }

    static boolean f(NetworkManager networkmanager)
    {
        return networkmanager.t;
    }

    static void a(NetworkManager networkmanager, Exception exception)
    {
        networkmanager.a(exception);
    }

    static Thread g(NetworkManager networkmanager)
    {
        return networkmanager.s;
    }

    static Thread h(NetworkManager networkmanager)
    {
        return networkmanager.r;
    }

    public static final Object a = new Object();
    public static int b;
    public static int c;
    private Object g;
    public Socket socket;
    private final SocketAddress i;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean l;
    private List m;
    private List highPriorityQueue;
    private List lowPriorityQueue;
    private NetHandler p;
    private boolean q;
    private Thread r;
    private Thread s;
    private boolean t;
    private String u;
    private Object v[];
    private int w;
    private int x;
    public static int d[] = new int[256];
    public static int e[] = new int[256];
    public int f;
    private int lowPriorityQueueDelay;

}
