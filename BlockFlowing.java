// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockFlowing.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            BlockFluids, World, Material, WorldProvider, 
//            Block

public class BlockFlowing extends BlockFluids
{

    protected BlockFlowing(int i, Material material)
    {
        super(i, material);
        a = 0;
        b = new boolean[4];
        c = new int[4];
    }

    private void i(net.minecraft.server.World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        world.setRawTypeIdAndData(i, j, k, id + 1, l);
        world.b(i, j, k, i, j, k);
        world.notify(i, j, k);
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        World bworld = world.getWorld();
        Server server = world.getServer();
        org.bukkit.block.Block source = bworld != null ? bworld.getBlockAt(i, j, k) : null;
        int l = g(world, i, j, k);
        byte b0 = 1;
        if(material == Material.LAVA && !world.worldProvider.d)
            b0 = 2;
        boolean flag = true;
        if(l > 0)
        {
            byte b1 = -100;
            a = 0;
            int j1 = f(world, i - 1, j, k, b1);
            j1 = f(world, i + 1, j, k, j1);
            j1 = f(world, i, j, k - 1, j1);
            j1 = f(world, i, j, k + 1, j1);
            int i1 = j1 + b0;
            if(i1 >= 8 || j1 < 0)
                i1 = -1;
            if(g(world, i, j + 1, k) >= 0)
            {
                int k1 = g(world, i, j + 1, k);
                if(k1 >= 8)
                    i1 = k1;
                else
                    i1 = k1 + 8;
            }
            if(a >= 2 && material == Material.WATER)
                if(world.getMaterial(i, j - 1, k).isBuildable())
                    i1 = 0;
                else
                if(world.getMaterial(i, j - 1, k) == material && world.getData(i, j, k) == 0)
                    i1 = 0;
            if(material == Material.LAVA && l < 8 && i1 < 8 && i1 > l && random.nextInt(4) != 0)
            {
                i1 = l;
                flag = false;
            }
            if(i1 != l)
            {
                l = i1;
                if(i1 < 0)
                {
                    world.setTypeId(i, j, k, 0);
                } else
                {
                    world.setData(i, j, k, i1);
                    world.c(i, j, k, id, c());
                    world.applyPhysics(i, j, k, id);
                }
            } else
            if(flag)
                i(world, i, j, k);
        } else
        {
            i(world, i, j, k);
        }
        if(l(world, i, j - 1, k))
        {
            BlockFromToEvent event = new BlockFromToEvent(source, BlockFace.DOWN);
            if(server != null)
                server.getPluginManager().callEvent(event);
            if(!event.isCancelled())
                if(l >= 8)
                    world.setTypeIdAndData(i, j - 1, k, id, l);
                else
                    world.setTypeIdAndData(i, j - 1, k, id, l + 8);
        } else
        if(l >= 0 && (l == 0 || k(world, i, j - 1, k)))
        {
            boolean aboolean[] = j(world, i, j, k);
            int i1 = l + b0;
            if(l >= 8)
                i1 = 1;
            if(i1 >= 8)
                return;
            BlockFace faces[] = {
                BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST
            };
            int index = 0;
            BlockFace arr$[] = faces;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                BlockFace currentFace = arr$[i$];
                if(aboolean[index])
                {
                    BlockFromToEvent event = new BlockFromToEvent(source, currentFace);
                    if(server != null)
                        server.getPluginManager().callEvent(event);
                    if(!event.isCancelled())
                        flow(world, i + currentFace.getModX(), j, k + currentFace.getModZ(), i1);
                }
                index++;
            }

        }
    }

    private void flow(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        if(l(world, i, j, k))
        {
            int i1 = world.getTypeId(i, j, k);
            if(i1 > 0)
                if(material == Material.LAVA)
                    h(world, i, j, k);
                else
                    Block.byId[i1].g(world, i, j, k, world.getData(i, j, k));
            world.setTypeIdAndData(i, j, k, id, l);
        }
    }

    private int b(net.minecraft.server.World world, int i, int j, int k, int l, int i1)
    {
        int j1 = 1000;
        for(int k1 = 0; k1 < 4; k1++)
        {
            if(k1 == 0 && i1 == 1 || k1 == 1 && i1 == 0 || k1 == 2 && i1 == 3 || k1 == 3 && i1 == 2)
                continue;
            int l1 = i;
            int i2 = k;
            if(k1 == 0)
                l1 = i - 1;
            if(k1 == 1)
                l1++;
            if(k1 == 2)
                i2 = k - 1;
            if(k1 == 3)
                i2++;
            if(k(world, l1, j, i2) || world.getMaterial(l1, j, i2) == material && world.getData(l1, j, i2) == 0)
                continue;
            if(!k(world, l1, j - 1, i2))
                return l;
            if(l >= 4)
                continue;
            int j2 = b(world, l1, j, i2, l + 1, k1);
            if(j2 < j1)
                j1 = j2;
        }

        return j1;
    }

    private boolean[] j(net.minecraft.server.World world, int i, int j, int k)
    {
        int l;
        for(l = 0; l < 4; l++)
        {
            c[l] = 1000;
            int i1 = i;
            int j1 = k;
            if(l == 0)
                i1 = i - 1;
            if(l == 1)
                i1++;
            if(l == 2)
                j1 = k - 1;
            if(l == 3)
                j1++;
            if(k(world, i1, j, j1) || world.getMaterial(i1, j, j1) == material && world.getData(i1, j, j1) == 0)
                continue;
            if(!k(world, i1, j - 1, j1))
                c[l] = 0;
            else
                c[l] = b(world, i1, j, j1, 1, l);
        }

        l = c[0];
        for(int i1 = 1; i1 < 4; i1++)
            if(c[i1] < l)
                l = c[i1];

        for(int i1 = 0; i1 < 4; i1++)
            b[i1] = c[i1] == l;

        return b;
    }

    private boolean k(net.minecraft.server.World world, int i, int j, int k)
    {
        int l = world.getTypeId(i, j, k);
        if(l != Block.WOODEN_DOOR.id && l != Block.IRON_DOOR_BLOCK.id && l != Block.SIGN_POST.id && l != Block.LADDER.id && l != Block.SUGAR_CANE_BLOCK.id)
        {
            if(l == 0)
            {
                return false;
            } else
            {
                Material material = Block.byId[l].material;
                return material.isSolid();
            }
        } else
        {
            return true;
        }
    }

    protected int f(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        int i1 = g(world, i, j, k);
        if(i1 < 0)
            return l;
        if(i1 == 0)
            a++;
        if(i1 >= 8)
            i1 = 0;
        return l < 0 || i1 < l ? i1 : l;
    }

    private boolean l(net.minecraft.server.World world, int i, int j, int k)
    {
        Material material = world.getMaterial(i, j, k);
        return material != this.material ? material != Material.LAVA ? !k(world, i, j, k) : false : false;
    }

    public void a(net.minecraft.server.World world, int i, int j, int k)
    {
        super.a(world, i, j, k);
        if(world.getTypeId(i, j, k) == id)
            world.c(i, j, k, id, c());
    }

    int a;
    boolean b[];
    int c[];
}
