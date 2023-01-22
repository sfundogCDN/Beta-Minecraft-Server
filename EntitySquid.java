// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntitySquid.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.inventory.ItemStack;

// Referenced classes of package net.minecraft.server:
//            EntityWaterAnimal, AxisAlignedBB, Material, World, 
//            MathHelper, NBTTagCompound, EntityHuman

public class EntitySquid extends EntityWaterAnimal
{

    public EntitySquid(World world)
    {
        super(world);
        a = 0.0F;
        b = 0.0F;
        c = 0.0F;
        g = 0.0F;
        h = 0.0F;
        i = 0.0F;
        j = 0.0F;
        k = 0.0F;
        l = 0.0F;
        m = 0.0F;
        n = 0.0F;
        o = 0.0F;
        p = 0.0F;
        q = 0.0F;
        texture = "/mob/squid.png";
        b(0.95F, 0.95F);
        m = (1.0F / (random.nextFloat() + 1.0F)) * 0.2F;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
    }

    protected String h()
    {
        return null;
    }

    protected String i()
    {
        return null;
    }

    protected String j()
    {
        return null;
    }

    protected float l()
    {
        return 0.4F;
    }

    protected int k()
    {
        return 0;
    }

    protected void a(boolean flag)
    {
        List loot = new ArrayList();
        int count = random.nextInt(3) + 1;
        if(count > 0)
            loot.add(new ItemStack(Material.INK_SACK, count));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    public boolean b(EntityHuman entityhuman)
    {
        return false;
    }

    public boolean ao()
    {
        return world.a(boundingBox.b(0.0D, -0.60000002384185791D, 0.0D), Material.WATER, this);
    }

    public void s()
    {
        super.s();
        b = a;
        g = c;
        i = h;
        k = j;
        h += m;
        if(h > 6.283185F)
        {
            h -= 6.283185F;
            if(random.nextInt(10) == 0)
                m = (1.0F / (random.nextFloat() + 1.0F)) * 0.2F;
        }
        if(ao())
        {
            float f;
            if(h < 3.141593F)
            {
                f = h / 3.141593F;
                j = MathHelper.sin(f * f * 3.141593F) * 3.141593F * 0.25F;
                if((double)f > 0.75D)
                {
                    l = 1.0F;
                    n = 1.0F;
                } else
                {
                    n *= 0.8F;
                }
            } else
            {
                j = 0.0F;
                l *= 0.9F;
                n *= 0.99F;
            }
            if(!ai)
            {
                motX = o * l;
                motY = p * l;
                motZ = q * l;
            }
            f = MathHelper.a(motX * motX + motZ * motZ);
            U += ((-(float)Math.atan2(motX, motZ) * 180F) / 3.141593F - U) * 0.1F;
            yaw = U;
            c += 3.141593F * n * 1.5F;
            a += ((-(float)Math.atan2(f, motY) * 180F) / 3.141593F - a) * 0.1F;
        } else
        {
            j = MathHelper.abs(MathHelper.sin(h)) * 3.141593F * 0.25F;
            if(!ai)
            {
                motX = 0.0D;
                motY -= 0.080000000000000002D;
                motY *= 0.98000001907348633D;
                motZ = 0.0D;
            }
            a = (float)((double)a + (double)(-90F - a) * 0.02D);
        }
    }

    public void a(float f, float f1)
    {
        move(motX, motY, motZ);
    }

    protected void c_()
    {
        if(random.nextInt(50) == 0 || !bQ || o == 0.0F && p == 0.0F && q == 0.0F)
        {
            float f = random.nextFloat() * 3.141593F * 2.0F;
            o = MathHelper.cos(f) * 0.2F;
            p = -0.1F + random.nextFloat() * 0.2F;
            q = MathHelper.sin(f) * 0.2F;
        }
        ad();
    }

    public float a;
    public float b;
    public float c;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
}
