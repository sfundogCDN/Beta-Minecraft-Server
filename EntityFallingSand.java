// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityFallingSand.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Entity, MathHelper, World, BlockSand, 
//            NBTTagCompound

public class EntityFallingSand extends Entity
{

    public EntityFallingSand(World world)
    {
        super(world);
        b = 0;
    }

    public EntityFallingSand(World world, double d0, double d1, double d2, 
            int i, int data)
    {
        super(world);
        b = 0;
        a = i;
        this.data = data;
        aY = true;
        b(0.98F, 0.98F);
        height = width / 2.0F;
        setPosition(d0, d1, d2);
        motX = 0.0D;
        motY = 0.0D;
        motZ = 0.0D;
        lastX = d0;
        lastY = d1;
        lastZ = d2;
    }

    protected boolean e_()
    {
        return false;
    }

    protected void b()
    {
    }

    public boolean r_()
    {
        return !dead;
    }

    public void s_()
    {
        if(a == 0)
        {
            die();
        } else
        {
            lastX = locX;
            lastY = locY;
            lastZ = locZ;
            b++;
            motY -= 0.039999999105930328D;
            move(motX, motY, motZ);
            motX *= 0.98000001907348633D;
            motY *= 0.98000001907348633D;
            motZ *= 0.98000001907348633D;
            int i = MathHelper.floor(locX);
            int j = MathHelper.floor(locY);
            int k = MathHelper.floor(locZ);
            if(world.getTypeId(i, j, k) == a)
                world.setTypeId(i, j, k, 0);
            if(onGround)
            {
                motX *= 0.69999998807907104D;
                motZ *= 0.69999998807907104D;
                motY *= -0.5D;
                die();
                if((!world.a(a, i, j, k, true, 1) || BlockSand.d_(world, i, j - 1, k) || !world.setTypeIdAndData(i, j, k, a, data)) && !world.isStatic)
                    b(a, 1);
            } else
            if(b > 100 && !world.isStatic)
            {
                b(a, 1);
                die();
            }
        }
    }

    protected void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Tile", (byte)a);
        nbttagcompound.a("Data", (byte)data);
    }

    protected void a(NBTTagCompound nbttagcompound)
    {
        a = nbttagcompound.c("Tile") & 0xff;
        data = nbttagcompound.c("Data") & 0xf;
    }

    public int a;
    public int data;
    public int b;
}
