// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityExperienceOrb.java

package net.minecraft.server;

import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            Entity, MathHelper, World, Material, 
//            AxisAlignedBB, EntityHuman, Block, DamageSource, 
//            NBTTagCompound

public class EntityExperienceOrb extends Entity
{

    public EntityExperienceOrb(World world, double d0, double d1, double d2, 
            int i)
    {
        super(world);
        b = 0;
        e = 5;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        b(0.5F, 0.5F);
        height = width / 2.0F;
        setPosition(d0, d1, d2);
        yaw = (float)(Math.random() * 360D);
        motX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F;
        motY = (float)(Math.random() * 0.20000000000000001D) * 2.0F;
        motZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F;
        value = i;
    }

    protected boolean e_()
    {
        return false;
    }

    public EntityExperienceOrb(World world)
    {
        super(world);
        b = 0;
        e = 5;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        b(0.25F, 0.25F);
        height = width / 2.0F;
    }

    protected void b()
    {
    }

    public void s_()
    {
        super.s_();
        if(c > 0)
            c--;
        lastX = locX;
        lastY = locY;
        lastZ = locZ;
        motY -= 0.029999999329447746D;
        if(world.getMaterial(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ)) == Material.LAVA)
        {
            motY = 0.20000000298023224D;
            motX = (random.nextFloat() - random.nextFloat()) * 0.2F;
            motZ = (random.nextFloat() - random.nextFloat()) * 0.2F;
            world.makeSound(this, "random.fizz", 0.4F, 2.0F + random.nextFloat() * 0.4F);
        }
        g(locX, (boundingBox.b + boundingBox.e) / 2D, locZ);
        double d0 = 8D;
        EntityHuman entityhuman = world.findNearbyPlayer(this, d0);
        if(entityhuman != null)
        {
            double d1 = (entityhuman.locX - locX) / d0;
            double d2 = ((entityhuman.locY + (double)entityhuman.t()) - locY) / d0;
            double d3 = (entityhuman.locZ - locZ) / d0;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;
            if(d5 > 0.0D)
            {
                d5 *= d5;
                motX += (d1 / d4) * d5 * 0.10000000000000001D;
                motY += (d2 / d4) * d5 * 0.10000000000000001D;
                motZ += (d3 / d4) * d5 * 0.10000000000000001D;
            }
        }
        move(motX, motY, motZ);
        float f = 0.98F;
        if(onGround)
        {
            f = 0.5880001F;
            int i = world.getTypeId(MathHelper.floor(locX), MathHelper.floor(boundingBox.b) - 1, MathHelper.floor(locZ));
            if(i > 0)
                f = Block.byId[i].frictionFactor * 0.98F;
        }
        motX *= f;
        motY *= 0.98000001907348633D;
        motZ *= f;
        if(onGround)
            motY *= -0.89999997615814209D;
        a++;
        b++;
        if(b >= 6000)
            die();
    }

    public boolean f_()
    {
        return world.a(boundingBox, Material.WATER, this);
    }

    protected void burn(int i)
    {
        damageEntity(DamageSource.FIRE, i);
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        aq();
        e -= i;
        if(e <= 0)
            die();
        return false;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Health", (byte)e);
        nbttagcompound.a("Age", (short)b);
        nbttagcompound.a("Value", (short)value);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        e = nbttagcompound.d("Health") & 0xff;
        b = nbttagcompound.d("Age");
        value = nbttagcompound.d("Value");
    }

    public void a_(EntityHuman entityhuman)
    {
        if(!world.isStatic && c == 0 && entityhuman.w == 0)
        {
            entityhuman.w = 2;
            world.makeSound(this, "random.pop", 0.2F, 0.5F * ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F));
            entityhuman.receive(this, 1);
            entityhuman.d(value);
            die();
        }
    }

    public int j_()
    {
        return value;
    }

    public static int b(int i)
    {
        if(i > 0x9b22631)
            return i - 0x186a0;
        if(i > 0x4d91317)
            return 0x4d91317;
        if(i > 0x26c8987)
            return 0x26c8987;
        if(i > 0x13644bf)
            return 0x13644bf;
        if(i > 0x9b2249)
            return 0x9b2249;
        if(i > 0x4d911f)
            return 0x4d911f;
        if(i > 0x26c885)
            return 0x26c885;
        if(i > 0x136441)
            return 0x136441;
        if(i > 0x9b215)
            return 0x9b215;
        if(i > 0x4d905)
            return 0x4d905;
        if(i > 0x26c81)
            return 0x26c81;
        if(i > 0x1363f)
            return 0x1363f;
        if(i > 39709)
            return 39709;
        if(i > 19853)
            return 19853;
        if(i > 9923)
            return 9923;
        if(i > 4957)
            return 4957;
        else
            return i < 2477 ? i < 1237 ? i < 617 ? i < 307 ? i < 149 ? ((char) (i < 73 ? ((char) (i < 37 ? ((char) (i < 17 ? ((char) (i < 7 ? ((char) (i < 3 ? '\001' : '\003')) : '\007')) : '\021')) : '%')) : 'I')) : '\225' : '\u0133' : '\u0269' : '\u04D5' : 2477;
    }

    public int a;
    public int b;
    public int c;
    private int e;
    public int value;
    public float d;
}
