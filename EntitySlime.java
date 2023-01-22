// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySlime.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityLiving, IMonster, DataWatcher, NBTTagCompound, 
//            MathHelper, AxisAlignedBB, World, DamageSource, 
//            EntityHuman, Item, Chunk

public class EntitySlime extends EntityLiving
    implements IMonster
{

    public EntitySlime(World world)
    {
        super(world);
        size = 0;
        texture = "/mob/slime.png";
        int i = 1 << random.nextInt(3);
        height = 0.0F;
        size = random.nextInt(20) + 10;
        setSize(i);
        ax = i;
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, new Byte((byte)1));
    }

    public void setSize(int i)
    {
        datawatcher.watch(16, new Byte((byte)i));
        b(0.6F * (float)i, 0.6F * (float)i);
        health = i * i;
        setPosition(locX, locY, locZ);
    }

    public int getSize()
    {
        return datawatcher.getByte(16);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Size", getSize() - 1);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setSize(nbttagcompound.e("Size") + 1);
    }

    public void s_()
    {
        b = a;
        boolean flag = onGround;
        super.s_();
        if(onGround && !flag)
        {
            int i = getSize();
            for(int j = 0; j < i * 8; j++)
            {
                float f = random.nextFloat() * 3.141593F * 2.0F;
                float f1 = random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                world.a("slime", locX + (double)f2, boundingBox.b, locZ + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            if(i > 2)
                world.makeSound(this, "mob.slime", l(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            a = -0.5F;
        }
        a *= 0.6F;
    }

    protected void c_()
    {
        ad();
        EntityHuman entityhuman = world.findNearbyPlayer(this, 16D);
        if(entityhuman != null)
            a(entityhuman, 10F, 20F);
        if(onGround && size-- <= 0)
        {
            size = random.nextInt(20) + 10;
            if(entityhuman != null)
                size /= 3;
            aS = true;
            if(getSize() > 1)
                world.makeSound(this, "mob.slime", l(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            a = 1.0F;
            aP = 1.0F - random.nextFloat() * 2.0F;
            aQ = 1 * getSize();
        } else
        {
            aS = false;
            if(onGround)
                aP = aQ = 0.0F;
        }
    }

    public void die()
    {
        int i = getSize();
        if(!world.isStatic && i > 1 && health == 0)
        {
            SlimeSplitEvent event = new SlimeSplitEvent(getBukkitEntity(), 4);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled() && event.getCount() > 0)
            {
                for(int j = 0; j < event.getCount(); j++)
                {
                    float f = (((float)(j % 2) - 0.5F) * (float)i) / 4F;
                    float f1 = (((float)(j / 2) - 0.5F) * (float)i) / 4F;
                    EntitySlime entityslime = new EntitySlime(world);
                    entityslime.setSize(i / 2);
                    entityslime.setPositionRotation(locX + (double)f, locY + 0.5D, locZ + (double)f1, random.nextFloat() * 360F, 0.0F);
                    world.addEntity(entityslime);
                }

            }
        }
        super.die();
    }

    public void a_(EntityHuman entityhuman)
    {
        int i = getSize();
        if(i > 1 && f(entityhuman) && (double)g(entityhuman) < 0.59999999999999998D * (double)i && entityhuman.damageEntity(DamageSource.mobAttack(this), i))
            world.makeSound(this, "mob.slimeattack", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }

    protected String i()
    {
        return "mob.slime";
    }

    protected String j()
    {
        return "mob.slime";
    }

    protected int k()
    {
        return getSize() != 1 ? 0 : Item.SLIME_BALL.id;
    }

    public boolean d()
    {
        Chunk chunk = world.getChunkAtWorldCoords(MathHelper.floor(locX), MathHelper.floor(locZ));
        return (getSize() == 1 || world.difficulty > 0) && random.nextInt(10) == 0 && chunk.a(0x3ad8025fL).nextInt(10) == 0 && locY < 16D;
    }

    protected float l()
    {
        return 0.6F;
    }

    public float a;
    public float b;
    private int size;
}
