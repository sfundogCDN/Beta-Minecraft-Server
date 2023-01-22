// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityPainting.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Painting;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, EnumArt, EntityItem, ItemStack, 
//            AxisAlignedBB, World, Material, Item, 
//            MathHelper, DamageSource, EntityWeatherStorm, NBTTagCompound

public class EntityPainting extends Entity
{

    public EntityPainting(World world)
    {
        super(world);
        f = 0;
        a = 0;
        height = 0.0F;
        b(0.5F, 0.5F);
        e = EnumArt.values()[random.nextInt(EnumArt.values().length)];
    }

    public EntityPainting(World world, int i, int j, int k, int l)
    {
        this(world);
        b = i;
        c = j;
        d = k;
        ArrayList arraylist = new ArrayList();
        EnumArt aenumart[] = EnumArt.values();
        int i1 = aenumart.length;
        for(int j1 = 0; j1 < i1; j1++)
        {
            EnumArt enumart = aenumart[j1];
            e = enumart;
            b(l);
            if(i())
                arraylist.add(enumart);
        }

        if(arraylist.size() > 0)
            e = (EnumArt)arraylist.get(random.nextInt(arraylist.size()));
        b(l);
    }

    protected void b()
    {
    }

    public void b(int i)
    {
        a = i;
        lastYaw = yaw = i * 90;
        float f = e.B;
        float f1 = e.C;
        float f2 = e.B;
        if(i != 0 && i != 2)
            f = 0.5F;
        else
            f2 = 0.5F;
        f /= 32F;
        f1 /= 32F;
        f2 /= 32F;
        float f3 = (float)b + 0.5F;
        float f4 = (float)c + 0.5F;
        float f5 = (float)d + 0.5F;
        float f6 = 0.5625F;
        if(i == 0)
            f5 -= f6;
        if(i == 1)
            f3 -= f6;
        if(i == 2)
            f5 += f6;
        if(i == 3)
            f3 += f6;
        if(i == 0)
            f3 -= c(e.B);
        if(i == 1)
            f5 += c(e.B);
        if(i == 2)
            f3 += c(e.B);
        if(i == 3)
            f5 -= c(e.B);
        f4 += c(e.C);
        setPosition(f3, f4, f5);
        float f7 = -0.00625F;
        boundingBox.c(f3 - f - f7, f4 - f1 - f7, f5 - f2 - f7, f3 + f + f7, f4 + f1 + f7, f5 + f2 + f7);
    }

    private float c(int i)
    {
        return i != 32 ? i != 64 ? 0.0F : 0.5F : 0.5F;
    }

    public void s_()
    {
        if(f++ == 100 && !world.isStatic)
        {
            f = 0;
            if(!i())
            {
                Material material = world.getMaterial((int)locX, (int)locY, (int)locZ);
                org.bukkit.event.painting.PaintingBreakEvent.RemoveCause cause;
                if(material.equals(Material.WATER))
                    cause = org.bukkit.event.painting.PaintingBreakEvent.RemoveCause.WATER;
                else
                if(!material.equals(Material.AIR))
                    cause = org.bukkit.event.painting.PaintingBreakEvent.RemoveCause.OBSTRUCTION;
                else
                    cause = org.bukkit.event.painting.PaintingBreakEvent.RemoveCause.PHYSICS;
                PaintingBreakEvent event = new PaintingBreakEvent((Painting)getBukkitEntity(), cause);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return;
                if(!dead)
                {
                    die();
                    world.addEntity(new EntityItem(world, locX, locY, locZ, new ItemStack(Item.PAINTING)));
                }
            }
        }
    }

    public boolean i()
    {
        if(world.getEntities(this, boundingBox).size() > 0)
            return false;
        int i = e.B / 16;
        int j = e.C / 16;
        int k = b;
        int l = c;
        int i1 = d;
        if(a == 0)
            k = MathHelper.floor(locX - (double)((float)e.B / 32F));
        if(a == 1)
            i1 = MathHelper.floor(locZ - (double)((float)e.B / 32F));
        if(a == 2)
            k = MathHelper.floor(locX - (double)((float)e.B / 32F));
        if(a == 3)
            i1 = MathHelper.floor(locZ - (double)((float)e.B / 32F));
        l = MathHelper.floor(locY - (double)((float)e.C / 32F));
        for(int k1 = 0; k1 < i; k1++)
        {
            for(int j1 = 0; j1 < j; j1++)
            {
                Material material;
                if(a != 0 && a != 2)
                    material = world.getMaterial(b, l + j1, i1 + k1);
                else
                    material = world.getMaterial(k + k1, l + j1, d);
                if(!material.isBuildable())
                    return false;
            }

        }

        List list = world.b(this, boundingBox);
        for(int j1 = 0; j1 < list.size(); j1++)
            if(list.get(j1) instanceof EntityPainting)
                return false;

        return true;
    }

    public boolean r_()
    {
        return true;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(!dead && !world.isStatic)
        {
            PaintingBreakEvent event = null;
            if(damagesource.getEntity() != null)
                event = new PaintingBreakByEntityEvent((Painting)getBukkitEntity(), damagesource.getEntity() != null ? damagesource.getEntity().getBukkitEntity() : null);
            else
            if(damagesource == DamageSource.FIRE)
                event = new PaintingBreakEvent((Painting)getBukkitEntity(), org.bukkit.event.painting.PaintingBreakEvent.RemoveCause.FIRE);
            if(event != null)
            {
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return true;
            }
            if(!dead)
            {
                die();
                aq();
                world.addEntity(new EntityItem(world, locX, locY, locZ, new ItemStack(Item.PAINTING)));
            }
        }
        return true;
    }

    public void a(EntityWeatherStorm entityweatherstorm)
    {
        PaintingBreakByEntityEvent event = new PaintingBreakByEntityEvent((Painting)getBukkitEntity(), entityweatherstorm.getBukkitEntity());
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        fireTicks++;
        if(fireTicks == 0)
            fireTicks = 300;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Dir", (byte)a);
        nbttagcompound.setString("Motive", e.A);
        nbttagcompound.a("TileX", b);
        nbttagcompound.a("TileY", c);
        nbttagcompound.a("TileZ", d);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        a = nbttagcompound.c("Dir");
        b = nbttagcompound.e("TileX");
        c = nbttagcompound.e("TileY");
        d = nbttagcompound.e("TileZ");
        String s = nbttagcompound.getString("Motive");
        EnumArt aenumart[] = EnumArt.values();
        int i = aenumart.length;
        for(int j = 0; j < i; j++)
        {
            EnumArt enumart = aenumart[j];
            if(enumart.A.equals(s))
                e = enumart;
        }

        if(e == null)
            e = EnumArt.KEBAB;
        b(a);
    }

    public void move(double d0, double d1, double d2)
    {
        if(!world.isStatic && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D && !dead)
        {
            die();
            world.addEntity(new EntityItem(world, locX, locY, locZ, new ItemStack(Item.PAINTING)));
        }
    }

    public void b(double d0, double d1, double d2)
    {
        if(!world.isStatic && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D && !dead)
        {
            die();
            world.addEntity(new EntityItem(world, locX, locY, locZ, new ItemStack(Item.PAINTING)));
        }
    }

    private int f;
    public int a;
    public int b;
    public int c;
    public int d;
    public EnumArt e;
}
