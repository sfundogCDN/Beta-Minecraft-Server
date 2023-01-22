// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockPressurePlate.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, Entity, EntityLiving, EntityHuman, 
//            World, EnumMobType, AxisAlignedBB, IBlockAccess, 
//            Material

public class BlockPressurePlate extends Block
{

    protected BlockPressurePlate(int i, int j, EnumMobType enummobtype, Material material)
    {
        super(i, j, material);
        a = enummobtype;
        a(true);
        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int c()
    {
        return 20;
    }

    public AxisAlignedBB e(net.minecraft.server.World world, int i, int j, int l)
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

    public boolean canPlace(net.minecraft.server.World world, int i, int j, int k)
    {
        return world.e(i, j - 1, k) || world.getTypeId(i, j - 1, k) == Block.FENCE.id;
    }

    public void a(net.minecraft.server.World world1, int l, int i1, int j1)
    {
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        boolean flag = false;
        if(!world.e(i, j - 1, k) && world.getTypeId(i, j - 1, k) != Block.FENCE.id)
            flag = true;
        if(flag)
        {
            g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Random random)
    {
        if(!world.isStatic && world.getData(i, j, k) != 0)
            g(world, i, j, k);
    }

    public void a(net.minecraft.server.World world, int i, int j, int k, Entity entity)
    {
        if(!world.isStatic && world.getData(i, j, k) != 1)
            g(world, i, j, k);
    }

    private void g(net.minecraft.server.World world, int i, int j, int k)
    {
        boolean flag;
        boolean flag1;
label0:
        {
            World bworld;
            PluginManager manager;
label1:
            {
                flag = world.getData(i, j, k) == 1;
                flag1 = false;
                float f = 0.125F;
                List list = null;
                if(a == EnumMobType.EVERYTHING)
                    list = world.b((Entity)null, AxisAlignedBB.b((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
                if(a == EnumMobType.MOBS)
                    list = world.a(net/minecraft/server/EntityLiving, AxisAlignedBB.b((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
                if(a == EnumMobType.PLAYERS)
                    list = world.a(net/minecraft/server/EntityHuman, AxisAlignedBB.b((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
                if(list.size() > 0)
                    flag1 = true;
                bworld = world.getWorld();
                manager = world.getServer().getPluginManager();
                if(flag == flag1)
                    break label0;
                if(!flag1)
                    break label1;
                Iterator i$ = list.iterator();
                Cancellable cancellable;
label2:
                do
                {
                    Object object;
                    do
                    {
                        do
                        {
                            if(!i$.hasNext())
                                break label1;
                            object = i$.next();
                        } while(object == null);
                        if(object instanceof EntityHuman)
                        {
                            cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, i, j, k, -1, null);
                            continue label2;
                        }
                    } while(!(object instanceof Entity));
                    cancellable = new EntityInteractEvent(((Entity)object).getBukkitEntity(), bworld.getBlockAt(i, j, k));
                    manager.callEvent((EntityInteractEvent)cancellable);
                } while(!cancellable.isCancelled());
                return;
            }
            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bworld.getBlockAt(i, j, k), flag ? 1 : 0, flag1 ? 1 : 0);
            manager.callEvent(eventRedstone);
            flag1 = eventRedstone.getNewCurrent() > 0;
        }
        if(flag1 && !flag)
        {
            world.setData(i, j, k, 1);
            world.applyPhysics(i, j, k, id);
            world.applyPhysics(i, j - 1, k, id);
            world.b(i, j, k, i, j, k);
            world.makeSound((double)i + 0.5D, (double)j + 0.10000000000000001D, (double)k + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if(!flag1 && flag)
        {
            world.setData(i, j, k, 0);
            world.applyPhysics(i, j, k, id);
            world.applyPhysics(i, j - 1, k, id);
            world.b(i, j, k, i, j, k);
            world.makeSound((double)i + 0.5D, (double)j + 0.10000000000000001D, (double)k + 0.5D, "random.click", 0.3F, 0.5F);
        }
        if(flag1)
            world.c(i, j, k, id, c());
    }

    public void remove(net.minecraft.server.World world, int i, int j, int k)
    {
        int l = world.getData(i, j, k);
        if(l > 0)
        {
            world.applyPhysics(i, j, k, id);
            world.applyPhysics(i, j - 1, k, id);
        }
        super.remove(world, i, j, k);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        boolean flag = iblockaccess.getData(i, j, k) == 1;
        float f = 0.0625F;
        if(flag)
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        else
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return iblockaccess.getData(i, j, k) > 0;
    }

    public boolean d(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        return world.getData(i, j, k) != 0 ? l == 1 : false;
    }

    public boolean isPowerSource()
    {
        return true;
    }

    public int e()
    {
        return 1;
    }

    private EnumMobType a;
}
