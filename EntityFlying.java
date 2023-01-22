// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            EntityLiving, MathHelper, AxisAlignedBB, World, 
//            Block

public abstract class EntityFlying extends EntityLiving
{

    public EntityFlying(World world)
    {
        super(world);
    }

    protected void a(float f)
    {
    }

    public void a(float f, float f1)
    {
        if(ao())
        {
            a(f, f1, 0.02F);
            move(motX, motY, motZ);
            motX *= 0.80000001192092896D;
            motY *= 0.80000001192092896D;
            motZ *= 0.80000001192092896D;
        } else
        if(ap())
        {
            a(f, f1, 0.02F);
            move(motX, motY, motZ);
            motX *= 0.5D;
            motY *= 0.5D;
            motZ *= 0.5D;
        } else
        {
            float f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int i = world.getTypeId(MathHelper.floor(locX), MathHelper.floor(boundingBox.b) - 1, MathHelper.floor(locZ));
                if(i > 0)
                    f2 = Block.byId[i].frictionFactor * 0.91F;
            }
            float f3 = 0.1627714F / (f2 * f2 * f2);
            a(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = world.getTypeId(MathHelper.floor(locX), MathHelper.floor(boundingBox.b) - 1, MathHelper.floor(locZ));
                if(j > 0)
                    f2 = Block.byId[j].frictionFactor * 0.91F;
            }
            move(motX, motY, motZ);
            motX *= f2;
            motY *= f2;
            motZ *= f2;
        }
        aA = aB;
        double d = locX - lastX;
        double d1 = locZ - lastZ;
        float f4 = MathHelper.a(d * d + d1 * d1) * 4F;
        if(f4 > 1.0F)
            f4 = 1.0F;
        aB += (f4 - aB) * 0.4F;
        aC += aB;
    }

    public boolean p()
    {
        return false;
    }
}
