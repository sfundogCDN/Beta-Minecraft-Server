// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityMobSpawner.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.event.entity.CreatureSpawnEvent;

// Referenced classes of package net.minecraft.server:
//            TileEntity, EntityLiving, World, EntityTypes, 
//            AxisAlignedBB, Entity, NBTTagCompound

public class TileEntityMobSpawner extends TileEntity
{

    public TileEntityMobSpawner()
    {
        spawnDelay = -1;
        mobName = "Pig";
        c = 0.0D;
        spawnDelay = 20;
    }

    public void a(String s)
    {
        mobName = s;
    }

    public boolean a()
    {
        return world.a((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 16D) != null;
    }

    public int getId()
    {
        return EntityTypes.getIdFromClass(EntityTypes.getClassFromName(mobName));
    }

    public void setId(int id)
    {
        mobName = EntityTypes.getNameFromClass(EntityTypes.getClassFromId(id));
        if(mobName == null || mobName.length() == 0)
            mobName = "Pig";
        if(EntityTypes.a(mobName, world) == null)
            mobName = "Pig";
    }

    public void h_()
    {
        c = b;
        if(a())
        {
            double d0 = (float)x + world.random.nextFloat();
            double d1 = (float)y + world.random.nextFloat();
            double d2 = (float)z + world.random.nextFloat();
            world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            world.a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            for(b += 1000F / ((float)spawnDelay + 200F); b > 360D;)
            {
                b -= 360D;
                c -= 360D;
            }

            if(!world.isStatic)
            {
                if(spawnDelay == -1)
                    c();
                if(spawnDelay > 0)
                {
                    spawnDelay--;
                    return;
                }
                byte b0 = 4;
                for(int i = 0; i < b0; i++)
                {
                    Entity entityliving = EntityTypes.a(mobName, world);
                    if(entityliving == null)
                        return;
                    int j = world.a(entityliving.getClass(), AxisAlignedBB.b(x, y, z, x + 1, y + 1, z + 1).b(8D, 4D, 8D)).size();
                    if(j >= 6)
                    {
                        c();
                        return;
                    }
                    if(entityliving == null)
                        continue;
                    double d3 = (double)x + (world.random.nextDouble() - world.random.nextDouble()) * 4D;
                    double d4 = (y + world.random.nextInt(3)) - 1;
                    double d5 = (double)z + (world.random.nextDouble() - world.random.nextDouble()) * 4D;
                    entityliving.setPositionRotation(d3, d4, d5, world.random.nextFloat() * 360F, 0.0F);
                    if((!(entityliving instanceof EntityLiving) || !((EntityLiving)entityliving).d()) && ((entityliving instanceof EntityLiving) || !entityliving.world.containsEntity(entityliving.boundingBox) || entityliving.world.getEntities(entityliving, entityliving.boundingBox).size() != 0 || entityliving.world.c(entityliving.boundingBox)))
                        continue;
                    world.addEntity(entityliving, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SPAWNER);
                    for(int k = 0; k < 20; k++)
                    {
                        d0 = (double)x + 0.5D + ((double)world.random.nextFloat() - 0.5D) * 2D;
                        d1 = (double)y + 0.5D + ((double)world.random.nextFloat() - 0.5D) * 2D;
                        d2 = (double)z + 0.5D + ((double)world.random.nextFloat() - 0.5D) * 2D;
                        world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                        world.a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }

                    c();
                }

            }
            super.h_();
        }
    }

    private void c()
    {
        spawnDelay = 200 + world.random.nextInt(600);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        mobName = nbttagcompound.getString("EntityId");
        spawnDelay = nbttagcompound.d("Delay");
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.setString("EntityId", mobName);
        nbttagcompound.a("Delay", (short)spawnDelay);
    }

    public int spawnDelay;
    public String mobName;
    public double b;
    public double c;
}
