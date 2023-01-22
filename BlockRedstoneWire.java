// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockRedstoneWire.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, ChunkPosition, Material, World, 
//            Item, IBlockAccess, Direction, AxisAlignedBB

public class BlockRedstoneWire extends Block
{

    public BlockRedstoneWire(int i, int j)
    {
        super(i, j, Material.ORIENTABLE);
        a = true;
        b = new HashSet();
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public int a(int i, int j)
    {
        return textureId;
    }

    public AxisAlignedBB e(World world, int i, int j, int l)
    {
        return null;
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        return world.e(i, j - 1, k);
    }

    private void g(World world, int i, int j, int k)
    {
        a(world, i, j, k, i, j, k);
        ArrayList arraylist = new ArrayList(b);
        b.clear();
        for(int l = 0; l < arraylist.size(); l++)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l);
            world.applyPhysics(chunkposition.x, chunkposition.y, chunkposition.z, id);
        }

    }

    private void a(World world, int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = world.getData(i, j, k);
        int l1 = 0;
        a = false;
        boolean flag = world.isBlockIndirectlyPowered(i, j, k);
        a = true;
        if(flag)
        {
            l1 = 15;
        } else
        {
            for(int i2 = 0; i2 < 4; i2++)
            {
                int j2 = i;
                int k2 = k;
                if(i2 == 0)
                    j2 = i - 1;
                if(i2 == 1)
                    j2++;
                if(i2 == 2)
                    k2 = k - 1;
                if(i2 == 3)
                    k2++;
                if(j2 != l || j != i1 || k2 != j1)
                    l1 = getPower(world, j2, j, k2, l1);
                if(world.e(j2, j, k2) && !world.e(i, j + 1, k))
                {
                    if(j2 != l || j + 1 != i1 || k2 != j1)
                        l1 = getPower(world, j2, j + 1, k2, l1);
                    continue;
                }
                if(!world.e(j2, j, k2) && (j2 != l || j - 1 != i1 || k2 != j1))
                    l1 = getPower(world, j2, j - 1, k2, l1);
            }

            if(l1 > 0)
                l1--;
            else
                l1 = 0;
        }
        if(k1 != l1)
        {
            BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(i, j, k), k1, l1);
            world.getServer().getPluginManager().callEvent(event);
            l1 = event.getNewCurrent();
        }
        if(k1 != l1)
        {
            world.suppressPhysics = true;
            world.setData(i, j, k, l1);
            world.b(i, j, k, i, j, k);
            world.suppressPhysics = false;
            for(int i2 = 0; i2 < 4; i2++)
            {
                int j2 = i;
                int k2 = k;
                int l2 = j - 1;
                if(i2 == 0)
                    j2 = i - 1;
                if(i2 == 1)
                    j2++;
                if(i2 == 2)
                    k2 = k - 1;
                if(i2 == 3)
                    k2++;
                if(world.e(j2, j, k2))
                    l2 += 2;
                boolean flag1 = false;
                int i3 = getPower(world, j2, j, k2, -1);
                l1 = world.getData(i, j, k);
                if(l1 > 0)
                    l1--;
                if(i3 >= 0 && i3 != l1)
                    a(world, j2, j, k2, i, j, k);
                i3 = getPower(world, j2, l2, k2, -1);
                l1 = world.getData(i, j, k);
                if(l1 > 0)
                    l1--;
                if(i3 >= 0 && i3 != l1)
                    a(world, j2, l2, k2, i, j, k);
            }

            if(k1 == 0 || l1 == 0)
            {
                b.add(new ChunkPosition(i, j, k));
                b.add(new ChunkPosition(i - 1, j, k));
                b.add(new ChunkPosition(i + 1, j, k));
                b.add(new ChunkPosition(i, j - 1, k));
                b.add(new ChunkPosition(i, j + 1, k));
                b.add(new ChunkPosition(i, j, k - 1));
                b.add(new ChunkPosition(i, j, k + 1));
            }
        }
    }

    private void h(World world, int i, int j, int k)
    {
        if(world.getTypeId(i, j, k) == id)
        {
            world.applyPhysics(i, j, k, id);
            world.applyPhysics(i - 1, j, k, id);
            world.applyPhysics(i + 1, j, k, id);
            world.applyPhysics(i, j, k - 1, id);
            world.applyPhysics(i, j, k + 1, id);
            world.applyPhysics(i, j - 1, k, id);
            world.applyPhysics(i, j + 1, k, id);
        }
    }

    public void a(World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        if(!world.isStatic)
        {
            g(world, i, j, k);
            world.applyPhysics(i, j + 1, k, id);
            world.applyPhysics(i, j - 1, k, id);
            h(world, i - 1, j, k);
            h(world, i + 1, j, k);
            h(world, i, j, k - 1);
            h(world, i, j, k + 1);
            if(world.e(i - 1, j, k))
                h(world, i - 1, j + 1, k);
            else
                h(world, i - 1, j - 1, k);
            if(world.e(i + 1, j, k))
                h(world, i + 1, j + 1, k);
            else
                h(world, i + 1, j - 1, k);
            if(world.e(i, j, k - 1))
                h(world, i, j + 1, k - 1);
            else
                h(world, i, j - 1, k - 1);
            if(world.e(i, j, k + 1))
                h(world, i, j + 1, k + 1);
            else
                h(world, i, j - 1, k + 1);
        }
    }

    public void remove(World world, int i, int j, int k)
    {
        super.remove(world, i, j, k);
        if(!world.isStatic)
        {
            world.applyPhysics(i, j + 1, k, id);
            world.applyPhysics(i, j - 1, k, id);
            g(world, i, j, k);
            h(world, i - 1, j, k);
            h(world, i + 1, j, k);
            h(world, i, j, k - 1);
            h(world, i, j, k + 1);
            if(world.e(i - 1, j, k))
                h(world, i - 1, j + 1, k);
            else
                h(world, i - 1, j - 1, k);
            if(world.e(i + 1, j, k))
                h(world, i + 1, j + 1, k);
            else
                h(world, i + 1, j - 1, k);
            if(world.e(i, j, k - 1))
                h(world, i, j + 1, k - 1);
            else
                h(world, i, j - 1, k - 1);
            if(world.e(i, j, k + 1))
                h(world, i, j + 1, k + 1);
            else
                h(world, i, j - 1, k + 1);
        }
    }

    public int getPower(World world, int i, int j, int k, int l)
    {
        if(world.getTypeId(i, j, k) != id)
        {
            return l;
        } else
        {
            int i1 = world.getData(i, j, k);
            return i1 <= l ? l : i1;
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!world.isStatic)
        {
            int i1 = world.getData(i, j, k);
            boolean flag = canPlace(world, i, j, k);
            if(!flag)
            {
                g(world, i, j, k, i1);
                world.setTypeId(i, j, k, 0);
            } else
            {
                g(world, i, j, k);
            }
            super.doPhysics(world, i, j, k, l);
        }
    }

    public int a(int i, Random random)
    {
        return Item.REDSTONE.id;
    }

    public boolean d(World world, int i, int j, int k, int l)
    {
        return a ? a(world, i, j, k, l) : false;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!a)
            return false;
        if(iblockaccess.getData(i, j, k) == 0)
            return false;
        if(l == 1)
            return true;
        boolean flag = c(iblockaccess, i - 1, j, k, 1) || !iblockaccess.e(i - 1, j, k) && c(iblockaccess, i - 1, j - 1, k, -1);
        boolean flag1 = c(iblockaccess, i + 1, j, k, 3) || !iblockaccess.e(i + 1, j, k) && c(iblockaccess, i + 1, j - 1, k, -1);
        boolean flag2 = c(iblockaccess, i, j, k - 1, 2) || !iblockaccess.e(i, j, k - 1) && c(iblockaccess, i, j - 1, k - 1, -1);
        boolean flag3 = c(iblockaccess, i, j, k + 1, 0) || !iblockaccess.e(i, j, k + 1) && c(iblockaccess, i, j - 1, k + 1, -1);
        if(!iblockaccess.e(i, j + 1, k))
        {
            if(iblockaccess.e(i - 1, j, k) && c(iblockaccess, i - 1, j + 1, k, -1))
                flag = true;
            if(iblockaccess.e(i + 1, j, k) && c(iblockaccess, i + 1, j + 1, k, -1))
                flag1 = true;
            if(iblockaccess.e(i, j, k - 1) && c(iblockaccess, i, j + 1, k - 1, -1))
                flag2 = true;
            if(iblockaccess.e(i, j, k + 1) && c(iblockaccess, i, j + 1, k + 1, -1))
                flag3 = true;
        }
        return flag2 || flag1 || flag || flag3 || l < 2 || l > 5 ? l != 2 || !flag2 || flag || flag1 ? l != 3 || !flag3 || flag || flag1 ? l != 4 || !flag || flag2 || flag3 ? l == 5 && flag1 && !flag2 && !flag3 : true : true : true : true;
    }

    public boolean isPowerSource()
    {
        return a;
    }

    public static boolean c(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        int i1 = iblockaccess.getTypeId(i, j, k);
        if(i1 == Block.REDSTONE_WIRE.id)
            return true;
        if(i1 == 0)
            return false;
        if(Block.byId[i1].isPowerSource())
            return true;
        if(i1 != Block.DIODE_OFF.id && i1 != Block.DIODE_ON.id)
        {
            return false;
        } else
        {
            int j1 = iblockaccess.getData(i, j, k);
            return l == Direction.e[j1 & 3];
        }
    }

    private boolean a;
    private Set b;
}
