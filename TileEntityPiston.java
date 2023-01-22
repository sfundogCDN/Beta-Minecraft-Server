// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityPiston.java

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            TileEntity, Entity, Block, BlockPistonMoving, 
//            World, PistonBlockTextures, NBTTagCompound

public class TileEntityPiston extends TileEntity
{

    public TileEntityPiston()
    {
    }

    public TileEntityPiston(int i, int j, int k, boolean flag, boolean flag1)
    {
        a = i;
        b = j;
        c = k;
        d = flag;
        e = flag1;
    }

    public int a()
    {
        return a;
    }

    public int j()
    {
        return b;
    }

    public boolean c()
    {
        return d;
    }

    public int d()
    {
        return c;
    }

    public float a(float f)
    {
        if(f > 1.0F)
            f = 1.0F;
        return g + (this.f - g) * f;
    }

    private void a(float f, float f1)
    {
        if(!d)
            f--;
        else
            f = 1.0F - f;
        AxisAlignedBB axisalignedbb = Block.PISTON_MOVING.a(world, x, y, z, a, f, c);
        if(axisalignedbb != null)
        {
            List list = world.b((Entity)null, axisalignedbb);
            if(!list.isEmpty())
            {
                h.addAll(list);
                Entity entity;
                for(Iterator iterator = h.iterator(); iterator.hasNext(); entity.move(f1 * (float)PistonBlockTextures.b[c], f1 * (float)PistonBlockTextures.c[c], f1 * (float)PistonBlockTextures.d[c]))
                    entity = (Entity)iterator.next();

                h.clear();
            }
        }
    }

    public void e()
    {
        if(g < 1.0F)
        {
            g = f = 1.0F;
            world.n(x, y, z);
            i();
            if(world.getTypeId(x, y, z) == Block.PISTON_MOVING.id)
                world.setTypeIdAndData(x, y, z, a, b);
        }
    }

    public void h_()
    {
        if(world == null)
            return;
        g = f;
        if(g >= 1.0F)
        {
            a(1.0F, 0.25F);
            world.n(x, y, z);
            i();
            if(world.getTypeId(x, y, z) == Block.PISTON_MOVING.id)
                world.setTypeIdAndData(x, y, z, a, b);
        } else
        {
            f += 0.5F;
            if(f >= 1.0F)
                f = 1.0F;
            if(d)
                a(f, (f - g) + 0.0625F);
        }
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        a = nbttagcompound.e("blockId");
        b = nbttagcompound.e("blockData");
        c = nbttagcompound.e("facing");
        g = f = nbttagcompound.g("progress");
        d = nbttagcompound.m("extending");
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("blockId", a);
        nbttagcompound.a("blockData", b);
        nbttagcompound.a("facing", c);
        nbttagcompound.a("progress", g);
        nbttagcompound.a("extending", d);
    }

    private int a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private float f;
    private float g;
    private static List h = new ArrayList();

}
