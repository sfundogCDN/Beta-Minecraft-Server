// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.server:
//            World, Block, BlockMinecartTrack, ChunkPosition

class MinecartTrackLogic
{

    public MinecartTrackLogic(BlockMinecartTrack blockminecarttrack, World world, int i, int j, int k)
    {
        a = blockminecarttrack;
        super();
        g = new ArrayList();
        b = world;
        c = i;
        d = j;
        e = k;
        int l = world.getTypeId(i, j, k);
        int i1 = world.getData(i, j, k);
        if(BlockMinecartTrack.a((BlockMinecartTrack)Block.byId[l]))
        {
            f = true;
            i1 &= -9;
        } else
        {
            f = false;
        }
        a(i1);
    }

    private void a(int i)
    {
        g.clear();
        if(i == 0)
        {
            g.add(new ChunkPosition(c, d, e - 1));
            g.add(new ChunkPosition(c, d, e + 1));
        } else
        if(i == 1)
        {
            g.add(new ChunkPosition(c - 1, d, e));
            g.add(new ChunkPosition(c + 1, d, e));
        } else
        if(i == 2)
        {
            g.add(new ChunkPosition(c - 1, d, e));
            g.add(new ChunkPosition(c + 1, d + 1, e));
        } else
        if(i == 3)
        {
            g.add(new ChunkPosition(c - 1, d + 1, e));
            g.add(new ChunkPosition(c + 1, d, e));
        } else
        if(i == 4)
        {
            g.add(new ChunkPosition(c, d + 1, e - 1));
            g.add(new ChunkPosition(c, d, e + 1));
        } else
        if(i == 5)
        {
            g.add(new ChunkPosition(c, d, e - 1));
            g.add(new ChunkPosition(c, d + 1, e + 1));
        } else
        if(i == 6)
        {
            g.add(new ChunkPosition(c + 1, d, e));
            g.add(new ChunkPosition(c, d, e + 1));
        } else
        if(i == 7)
        {
            g.add(new ChunkPosition(c - 1, d, e));
            g.add(new ChunkPosition(c, d, e + 1));
        } else
        if(i == 8)
        {
            g.add(new ChunkPosition(c - 1, d, e));
            g.add(new ChunkPosition(c, d, e - 1));
        } else
        if(i == 9)
        {
            g.add(new ChunkPosition(c + 1, d, e));
            g.add(new ChunkPosition(c, d, e - 1));
        }
    }

    private void a()
    {
        for(int i = 0; i < g.size(); i++)
        {
            MinecartTrackLogic minecarttracklogic = a((ChunkPosition)g.get(i));
            if(minecarttracklogic == null || !minecarttracklogic.b(this))
                g.remove(i--);
            else
                g.set(i, new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
        }

    }

    private boolean a(int i, int j, int k)
    {
        if(BlockMinecartTrack.g(b, i, j, k))
            return true;
        if(BlockMinecartTrack.g(b, i, j + 1, k))
            return true;
        return BlockMinecartTrack.g(b, i, j - 1, k);
    }

    private MinecartTrackLogic a(ChunkPosition chunkposition)
    {
        if(BlockMinecartTrack.g(b, chunkposition.x, chunkposition.y, chunkposition.z))
            return new MinecartTrackLogic(a, b, chunkposition.x, chunkposition.y, chunkposition.z);
        if(BlockMinecartTrack.g(b, chunkposition.x, chunkposition.y + 1, chunkposition.z))
            return new MinecartTrackLogic(a, b, chunkposition.x, chunkposition.y + 1, chunkposition.z);
        if(BlockMinecartTrack.g(b, chunkposition.x, chunkposition.y - 1, chunkposition.z))
            return new MinecartTrackLogic(a, b, chunkposition.x, chunkposition.y - 1, chunkposition.z);
        else
            return null;
    }

    private boolean b(MinecartTrackLogic minecarttracklogic)
    {
        for(int i = 0; i < g.size(); i++)
        {
            ChunkPosition chunkposition = (ChunkPosition)g.get(i);
            if(chunkposition.x == minecarttracklogic.c && chunkposition.z == minecarttracklogic.e)
                return true;
        }

        return false;
    }

    private boolean b(int i, int j, int k)
    {
        for(int l = 0; l < g.size(); l++)
        {
            ChunkPosition chunkposition = (ChunkPosition)g.get(l);
            if(chunkposition.x == i && chunkposition.z == k)
                return true;
        }

        return false;
    }

    private int b()
    {
        int i = 0;
        if(a(c, d, e - 1))
            i++;
        if(a(c, d, e + 1))
            i++;
        if(a(c - 1, d, e))
            i++;
        if(a(c + 1, d, e))
            i++;
        return i;
    }

    private boolean c(MinecartTrackLogic minecarttracklogic)
    {
        if(b(minecarttracklogic))
            return true;
        if(g.size() == 2)
            return false;
        if(g.size() == 0)
            return true;
        ChunkPosition chunkposition = (ChunkPosition)g.get(0);
        return minecarttracklogic.d != d || chunkposition.y != d ? true : true;
    }

    private void d(MinecartTrackLogic minecarttracklogic)
    {
        g.add(new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
        boolean flag = b(c, d, e - 1);
        boolean flag1 = b(c, d, e + 1);
        boolean flag2 = b(c - 1, d, e);
        boolean flag3 = b(c + 1, d, e);
        byte byte0 = -1;
        if(flag || flag1)
            byte0 = 0;
        if(flag2 || flag3)
            byte0 = 1;
        if(!f)
        {
            if(flag1 && flag3 && !flag && !flag2)
                byte0 = 6;
            if(flag1 && flag2 && !flag && !flag3)
                byte0 = 7;
            if(flag && flag2 && !flag1 && !flag3)
                byte0 = 8;
            if(flag && flag3 && !flag1 && !flag2)
                byte0 = 9;
        }
        if(byte0 == 0)
        {
            if(BlockMinecartTrack.g(b, c, d + 1, e - 1))
                byte0 = 4;
            if(BlockMinecartTrack.g(b, c, d + 1, e + 1))
                byte0 = 5;
        }
        if(byte0 == 1)
        {
            if(BlockMinecartTrack.g(b, c + 1, d + 1, e))
                byte0 = 2;
            if(BlockMinecartTrack.g(b, c - 1, d + 1, e))
                byte0 = 3;
        }
        if(byte0 < 0)
            byte0 = 0;
        int i = byte0;
        if(f)
            i = b.getData(c, d, e) & 8 | byte0;
        b.setData(c, d, e, i);
    }

    private boolean c(int i, int j, int k)
    {
        MinecartTrackLogic minecarttracklogic = a(new ChunkPosition(i, j, k));
        if(minecarttracklogic == null)
        {
            return false;
        } else
        {
            minecarttracklogic.a();
            return minecarttracklogic.c(this);
        }
    }

    public void a(boolean flag, boolean flag1)
    {
        boolean flag2 = c(c, d, e - 1);
        boolean flag3 = c(c, d, e + 1);
        boolean flag4 = c(c - 1, d, e);
        boolean flag5 = c(c + 1, d, e);
        byte byte0 = -1;
        if((flag2 || flag3) && !flag4 && !flag5)
            byte0 = 0;
        if((flag4 || flag5) && !flag2 && !flag3)
            byte0 = 1;
        if(!f)
        {
            if(flag3 && flag5 && !flag2 && !flag4)
                byte0 = 6;
            if(flag3 && flag4 && !flag2 && !flag5)
                byte0 = 7;
            if(flag2 && flag4 && !flag3 && !flag5)
                byte0 = 8;
            if(flag2 && flag5 && !flag3 && !flag4)
                byte0 = 9;
        }
        if(byte0 == -1)
        {
            if(flag2 || flag3)
                byte0 = 0;
            if(flag4 || flag5)
                byte0 = 1;
            if(!f)
                if(flag)
                {
                    if(flag3 && flag5)
                        byte0 = 6;
                    if(flag4 && flag3)
                        byte0 = 7;
                    if(flag5 && flag2)
                        byte0 = 9;
                    if(flag2 && flag4)
                        byte0 = 8;
                } else
                {
                    if(flag2 && flag4)
                        byte0 = 8;
                    if(flag5 && flag2)
                        byte0 = 9;
                    if(flag4 && flag3)
                        byte0 = 7;
                    if(flag3 && flag5)
                        byte0 = 6;
                }
        }
        if(byte0 == 0)
        {
            if(BlockMinecartTrack.g(b, c, d + 1, e - 1))
                byte0 = 4;
            if(BlockMinecartTrack.g(b, c, d + 1, e + 1))
                byte0 = 5;
        }
        if(byte0 == 1)
        {
            if(BlockMinecartTrack.g(b, c + 1, d + 1, e))
                byte0 = 2;
            if(BlockMinecartTrack.g(b, c - 1, d + 1, e))
                byte0 = 3;
        }
        if(byte0 < 0)
            byte0 = 0;
        a(byte0);
        int i = byte0;
        if(f)
            i = b.getData(c, d, e) & 8 | byte0;
        if(flag1 || b.getData(c, d, e) != i)
        {
            b.setData(c, d, e, i);
            for(int j = 0; j < g.size(); j++)
            {
                MinecartTrackLogic minecarttracklogic = a((ChunkPosition)g.get(j));
                if(minecarttracklogic == null)
                    continue;
                minecarttracklogic.a();
                if(minecarttracklogic.c(this))
                    minecarttracklogic.d(this);
            }

        }
    }

    static int a(MinecartTrackLogic minecarttracklogic)
    {
        return minecarttracklogic.b();
    }

    private World b;
    private int c;
    private int d;
    private int e;
    private final boolean f;
    private List g;
    final BlockMinecartTrack a;
}
