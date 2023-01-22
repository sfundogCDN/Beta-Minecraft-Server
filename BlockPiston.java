// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockPiston.java

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, EntityHuman, TileEntityPiston, Material, 
//            PistonBlockTextures, World, BlockPistonMoving, IBlockAccess, 
//            MathHelper, BlockPistonExtension, EntityLiving, AxisAlignedBB

public class BlockPiston extends Block
{

    public BlockPiston(int i, int j, boolean flag)
    {
        super(i, j, Material.PISTON);
        a = flag;
        a(h);
        c(0.5F);
    }

    public int a(int i, int j)
    {
        int k = c(j);
        return k <= 5 ? i != k ? ((int) (i != PistonBlockTextures.a[k] ? 108 : 109)) : d(j) || minX > 0.0D || minY > 0.0D || minZ > 0.0D || maxX < 1.0D || maxY < 1.0D || maxZ < 1.0D ? 110 : textureId : textureId;
    }

    public boolean a()
    {
        return false;
    }

    public boolean interact(World world, int i, int j, int l, EntityHuman entityhuman1)
    {
        return false;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = c(world, i, j, k, (EntityHuman)entityliving);
        world.setData(i, j, k, l);
        if(!world.isStatic)
            g(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l)
    {
        if(!world.isStatic && !b)
            g(world, i, j, k);
    }

    public void a(World world, int i, int j, int k)
    {
        if(!world.isStatic && world.getTileEntity(i, j, k) == null)
            g(world, i, j, k);
    }

    private void g(World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        int i1 = c(l);
        boolean flag = f(world, i, j, k, i1);
        if(l != 7)
            if(flag && !d(l))
            {
                int length = h(world, i, j, k, i1);
                if(length >= 0)
                {
                    org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
                    BlockPistonExtendEvent event = new BlockPistonExtendEvent(block, length, CraftBlock.notchToBlockFace(i1));
                    world.getServer().getPluginManager().callEvent(event);
                    if(event.isCancelled())
                        return;
                    world.setRawData(i, j, k, i1 | 8);
                    world.playNote(i, j, k, 0, i1);
                }
            } else
            if(!flag && d(l))
            {
                org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
                BlockPistonRetractEvent event = new BlockPistonRetractEvent(block, CraftBlock.notchToBlockFace(i1));
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
                world.setRawData(i, j, k, i1);
                world.playNote(i, j, k, 1, i1);
            }
    }

    private boolean f(World world, int i, int j, int k, int l)
    {
        return l == 0 || !world.isBlockFaceIndirectlyPowered(i, j - 1, k, 0) ? l == 1 || !world.isBlockFaceIndirectlyPowered(i, j + 1, k, 1) ? l == 2 || !world.isBlockFaceIndirectlyPowered(i, j, k - 1, 2) ? l == 3 || !world.isBlockFaceIndirectlyPowered(i, j, k + 1, 3) ? l == 5 || !world.isBlockFaceIndirectlyPowered(i + 1, j, k, 5) ? l == 4 || !world.isBlockFaceIndirectlyPowered(i - 1, j, k, 4) ? world.isBlockFaceIndirectlyPowered(i, j, k, 0) ? true : world.isBlockFaceIndirectlyPowered(i, j + 2, k, 1) ? true : world.isBlockFaceIndirectlyPowered(i, j + 1, k - 1, 2) ? true : world.isBlockFaceIndirectlyPowered(i, j + 1, k + 1, 3) ? true : world.isBlockFaceIndirectlyPowered(i - 1, j + 1, k, 4) ? true : world.isBlockFaceIndirectlyPowered(i + 1, j + 1, k, 5) : true : true : true : true : true : true;
    }

    public void a(World world, int i, int j, int k, int l, int i1)
    {
        b = true;
        if(l == 0)
        {
            if(i(world, i, j, k, i1))
            {
                world.setData(i, j, k, i1 | 8);
                world.makeSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "tile.piston.out", 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
            }
        } else
        if(l == 1)
        {
            TileEntity tileentity = world.getTileEntity(i + PistonBlockTextures.b[i1], j + PistonBlockTextures.c[i1], k + PistonBlockTextures.d[i1]);
            if(tileentity != null && (tileentity instanceof TileEntityPiston))
                ((TileEntityPiston)tileentity).e();
            world.setRawTypeIdAndData(i, j, k, Block.PISTON_MOVING.id, i1);
            world.setTileEntity(i, j, k, BlockPistonMoving.a(id, i1, i1, false, true));
            if(a)
            {
                int j1 = i + PistonBlockTextures.b[i1] * 2;
                int k1 = j + PistonBlockTextures.c[i1] * 2;
                int l1 = k + PistonBlockTextures.d[i1] * 2;
                int i2 = world.getTypeId(j1, k1, l1);
                int j2 = world.getData(j1, k1, l1);
                boolean flag = false;
                if(i2 == Block.PISTON_MOVING.id)
                {
                    TileEntity tileentity1 = world.getTileEntity(j1, k1, l1);
                    if(tileentity1 != null && (tileentity1 instanceof TileEntityPiston))
                    {
                        TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity1;
                        if(tileentitypiston.d() == i1 && tileentitypiston.c())
                        {
                            tileentitypiston.e();
                            i2 = tileentitypiston.a();
                            j2 = tileentitypiston.j();
                            flag = true;
                        }
                    }
                }
                if(!flag && i2 > 0 && a(i2, world, j1, k1, l1, false) && (Block.byId[i2].e() == 0 || i2 == Block.PISTON.id || i2 == Block.PISTON_STICKY.id))
                {
                    b = false;
                    world.setTypeId(j1, k1, l1, 0);
                    b = true;
                    i += PistonBlockTextures.b[i1];
                    j += PistonBlockTextures.c[i1];
                    k += PistonBlockTextures.d[i1];
                    world.setRawTypeIdAndData(i, j, k, Block.PISTON_MOVING.id, j2);
                    world.setTileEntity(i, j, k, BlockPistonMoving.a(i2, j2, i1, false, false));
                } else
                if(!flag)
                {
                    b = false;
                    world.setTypeId(i + PistonBlockTextures.b[i1], j + PistonBlockTextures.c[i1], k + PistonBlockTextures.d[i1], 0);
                    b = true;
                }
            } else
            {
                b = false;
                world.setTypeId(i + PistonBlockTextures.b[i1], j + PistonBlockTextures.c[i1], k + PistonBlockTextures.d[i1], 0);
                b = true;
            }
            world.makeSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "tile.piston.in", 0.5F, world.random.nextFloat() * 0.15F + 0.6F);
        }
        b = false;
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getData(i, j, k);
        if(d(l))
            switch(c(l))
            {
            case 0: // '\0'
                a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;

            case 1: // '\001'
                a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                break;

            case 2: // '\002'
                a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                break;

            case 3: // '\003'
                a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                break;

            case 4: // '\004'
                a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;

            case 5: // '\005'
                a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
                break;
            }
        else
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, arraylist);
    }

    public boolean b()
    {
        return false;
    }

    public static int c(int i)
    {
        return i & 7;
    }

    public static boolean d(int i)
    {
        return (i & 8) != 0;
    }

    private static int c(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(MathHelper.abs((float)entityhuman.locX - (float)i) < 2.0F && MathHelper.abs((float)entityhuman.locZ - (float)k) < 2.0F)
        {
            double d0 = (entityhuman.locY + 1.8200000000000001D) - (double)entityhuman.height;
            if(d0 - (double)j > 2D)
                return 1;
            if((double)j - d0 > 0.0D)
                return 0;
        }
        int l = MathHelper.floor((double)((entityhuman.yaw * 4F) / 360F) + 0.5D) & 3;
        return l != 0 ? l != 1 ? l != 2 ? ((byte)(l != 3 ? 0 : 4)) : 3 : 5 : 2;
    }

    private static boolean a(int i, World world, int j, int k, int l, boolean flag)
    {
        if(i == Block.OBSIDIAN.id)
            return false;
        if(i != Block.PISTON.id && i != Block.PISTON_STICKY.id)
        {
            if(Block.byId[i].j() == -1F)
                return false;
            if(Block.byId[i].e() == 2)
                return false;
            if(!flag && Block.byId[i].e() == 1)
                return false;
        } else
        if(d(world.getData(j, k, l)))
            return false;
        TileEntity tileentity = world.getTileEntity(j, k, l);
        return tileentity == null;
    }

    private static int h(World world, int i, int j, int k, int l)
    {
        int l1;
label0:
        {
label1:
            {
                int i1 = i + PistonBlockTextures.b[l];
                int j1 = j + PistonBlockTextures.c[l];
                int k1 = k + PistonBlockTextures.d[l];
                l1 = 0;
                do
                {
                    if(l1 >= 13)
                        break label0;
                    if(j1 <= 0)
                        break label1;
                    world.getClass();
                    if(j1 >= 127)
                        break label1;
                    int i2 = world.getTypeId(i1, j1, k1);
                    if(i2 == 0)
                        break;
                    if(!a(i2, world, i1, j1, k1, true))
                        return -1;
                    if(Block.byId[i2].e() == 1)
                        break;
                    if(l1 == 12)
                        return -1;
                    i1 += PistonBlockTextures.b[l];
                    j1 += PistonBlockTextures.c[l];
                    k1 += PistonBlockTextures.d[l];
                    l1++;
                } while(true);
                return l1;
            }
            return -1;
        }
        return l1;
    }

    private boolean i(World world, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
label0:
        {
            i1 = i + PistonBlockTextures.b[l];
            j1 = j + PistonBlockTextures.c[l];
            k1 = k + PistonBlockTextures.d[l];
            int l1 = 0;
            do
            {
                if(l1 >= 13)
                    break label0;
                if(j1 <= 0)
                    break;
                world.getClass();
                if(j1 >= 127)
                    break;
                int i2 = world.getTypeId(i1, j1, k1);
                if(i2 == 0)
                    break label0;
                if(!a(i2, world, i1, j1, k1, true))
                    return false;
                if(Block.byId[i2].e() != 1)
                {
                    if(l1 == 12)
                        return false;
                    i1 += PistonBlockTextures.b[l];
                    j1 += PistonBlockTextures.c[l];
                    k1 += PistonBlockTextures.d[l];
                    l1++;
                } else
                {
                    Block.byId[i2].g(world, i1, j1, k1, world.getData(i1, j1, k1));
                    world.setTypeId(i1, j1, k1, 0);
                    break label0;
                }
            } while(true);
            return false;
        }
        int j2;
        for(; i1 != i || j1 != j || k1 != k; k1 = j2)
        {
            int l1 = i1 - PistonBlockTextures.b[l];
            int i2 = j1 - PistonBlockTextures.c[l];
            j2 = k1 - PistonBlockTextures.d[l];
            int k2 = world.getTypeId(l1, i2, j2);
            int l2 = world.getData(l1, i2, j2);
            if(k2 == id && l1 == i && i2 == j && j2 == k)
            {
                world.setRawTypeIdAndData(i1, j1, k1, Block.PISTON_MOVING.id, l | (a ? 8 : 0));
                world.setTileEntity(i1, j1, k1, BlockPistonMoving.a(Block.PISTON_EXTENSION.id, l | (a ? 8 : 0), l, true, false));
            } else
            {
                world.setRawTypeIdAndData(i1, j1, k1, Block.PISTON_MOVING.id, l2);
                world.setTileEntity(i1, j1, k1, BlockPistonMoving.a(k2, l2, l, true, false));
            }
            i1 = l1;
            j1 = i2;
        }

        return true;
    }

    private boolean a;
    private boolean b;
}
