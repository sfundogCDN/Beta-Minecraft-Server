// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockDoor.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Block, EntityHuman, Material, IBlockAccess, 
//            World, Item, AxisAlignedBB, Vec3D, 
//            MovingObjectPosition

public class BlockDoor extends net.minecraft.server.Block
{

    protected BlockDoor(int i, Material material)
    {
        super(i, material);
        textureId = 97;
        if(material == Material.ORE)
            textureId++;
        float f = 0.5F;
        float f1 = 1.0F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public int a(int i, int j)
    {
        if(i != 0 && i != 1)
        {
            int k = d(j);
            if((k == 0 || k == 2) ^ (i <= 3))
                return textureId;
            int l = k / 2 + (i & 1 ^ k);
            l += (j & 4) / 4;
            int i1 = textureId - (j & 8) * 2;
            if((l & 1) != 0)
                i1 = -i1;
            return i1;
        } else
        {
            return textureId;
        }
    }

    public boolean a()
    {
        return false;
    }

    public boolean b()
    {
        return false;
    }

    public AxisAlignedBB e(net.minecraft.server.World world, int i, int j, int k)
    {
        a(world, i, j, k);
        return super.e(world, i, j, k);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k)
    {
        c(d(iblockaccess.getData(i, j, k)));
    }

    public void c(int i)
    {
        float f = 0.1875F;
        a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        if(i == 0)
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        if(i == 1)
            a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        if(i == 2)
            a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        if(i == 3)
            a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
    }

    public void b(net.minecraft.server.World world, int i, int j, int k, EntityHuman entityhuman)
    {
        interact(world, i, j, k, entityhuman);
    }

    public boolean interact(net.minecraft.server.World world, int i, int j, int k, EntityHuman entityhuman)
    {
        if(material == Material.ORE)
            return true;
        int l = world.getData(i, j, k);
        if((l & 8) != 0)
        {
            if(world.getTypeId(i, j - 1, k) == id)
                interact(world, i, j - 1, k, entityhuman);
            return true;
        }
        if(world.getTypeId(i, j + 1, k) == id)
            world.setData(i, j + 1, k, (l ^ 4) + 8);
        world.setData(i, j, k, l ^ 4);
        world.b(i, j - 1, k, i, j, k);
        world.a(entityhuman, 1003, i, j, k, 0);
        return true;
    }

    public void setDoor(net.minecraft.server.World world, int i, int j, int k, boolean flag)
    {
        int l = world.getData(i, j, k);
        if((l & 8) != 0)
        {
            if(world.getTypeId(i, j - 1, k) == id)
                setDoor(world, i, j - 1, k, flag);
        } else
        {
            boolean flag1 = (world.getData(i, j, k) & 4) > 0;
            if(flag1 != flag)
            {
                if(world.getTypeId(i, j + 1, k) == id)
                    world.setData(i, j + 1, k, (l ^ 4) + 8);
                world.setData(i, j, k, l ^ 4);
                world.b(i, j - 1, k, i, j, k);
                world.a((EntityHuman)null, 1003, i, j, k, 0);
            }
        }
    }

    public void doPhysics(net.minecraft.server.World world, int i, int j, int k, int l)
    {
        int i1 = world.getData(i, j, k);
        if((i1 & 8) != 0)
        {
            if(world.getTypeId(i, j - 1, k) != id)
                world.setTypeId(i, j, k, 0);
            if(l > 0 && Block.byId[l].isPowerSource())
                doPhysics(world, i, j - 1, k, l);
        } else
        {
            boolean flag = false;
            if(world.getTypeId(i, j + 1, k) != id)
            {
                world.setTypeId(i, j, k, 0);
                flag = true;
            }
            if(!world.e(i, j - 1, k))
            {
                world.setTypeId(i, j, k, 0);
                flag = true;
                if(world.getTypeId(i, j + 1, k) == id)
                    world.setTypeId(i, j + 1, k, 0);
            }
            if(flag)
            {
                if(!world.isStatic)
                    g(world, i, j, k, i1);
            } else
            if(l > 0 && Block.byId[l].isPowerSource())
            {
                World bworld = world.getWorld();
                Block block = bworld.getBlockAt(i, j, k);
                Block blockTop = bworld.getBlockAt(i, j + 1, k);
                int power = block.getBlockPower();
                int powerTop = blockTop.getBlockPower();
                if(powerTop > power)
                    power = powerTop;
                int oldPower = (world.getData(i, j, k) & 4) <= 0 ? 0 : 15;
                if((oldPower == 0) ^ (power == 0))
                {
                    BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
                    world.getServer().getPluginManager().callEvent(eventRedstone);
                    setDoor(world, i, j, k, eventRedstone.getNewCurrent() > 0);
                }
            }
        }
    }

    public int a(int i, Random random)
    {
        return (i & 8) == 0 ? material != Material.ORE ? Item.WOOD_DOOR.id : Item.IRON_DOOR.id : 0;
    }

    public MovingObjectPosition a(net.minecraft.server.World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        a(world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public int d(int i)
    {
        return (i & 4) != 0 ? i & 3 : i - 1 & 3;
    }

    public boolean canPlace(net.minecraft.server.World world, int i, int j, int k)
    {
        world.getClass();
        return j < 127 ? world.e(i, j - 1, k) && super.canPlace(world, i, j, k) && super.canPlace(world, i, j + 1, k) : false;
    }

    public static boolean e(int i)
    {
        return (i & 4) != 0;
    }

    public int e()
    {
        return 1;
    }
}
