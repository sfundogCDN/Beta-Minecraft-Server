// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Explosion.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            ChunkPosition, Entity, World, MathHelper, 
//            Block, AxisAlignedBB, Vec3D, DamageSource, 
//            BlockFire

public class Explosion
{

    public Explosion(net.minecraft.server.World world, Entity entity, double d0, double d1, double d2, float f)
    {
        a = false;
        h = new Random();
        blocks = new HashSet();
        wasCanceled = false;
        this.world = world;
        source = entity;
        size = f;
        posX = d0;
        posY = d1;
        posZ = d2;
    }

    public void a()
    {
        float f = size;
        byte b0 = 16;
        int i;
        int j;
        int k;
        for(i = 0; i < b0; i++)
            for(j = 0; j < b0; j++)
label0:
                for(k = 0; k < b0; k++)
                {
                    if(i != 0 && i != b0 - 1 && j != 0 && j != b0 - 1 && k != 0 && k != b0 - 1)
                        continue;
                    double d3 = ((float)i / ((float)b0 - 1.0F)) * 2.0F - 1.0F;
                    double d4 = ((float)j / ((float)b0 - 1.0F)) * 2.0F - 1.0F;
                    double d5 = ((float)k / ((float)b0 - 1.0F)) * 2.0F - 1.0F;
                    double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    float f1 = size * (0.7F + world.random.nextFloat() * 0.6F);
                    double d0 = posX;
                    double d1 = posY;
                    double d2 = posZ;
                    float f2 = 0.3F;
                    do
                    {
                        if(f1 <= 0.0F)
                            continue label0;
                        int l = MathHelper.floor(d0);
                        int i1 = MathHelper.floor(d1);
                        int j1 = MathHelper.floor(d2);
                        int k1 = world.getTypeId(l, i1, j1);
                        if(k1 > 0)
                            f1 -= (Block.byId[k1].a(source) + 0.3F) * f2;
                        if(f1 > 0.0F)
                            blocks.add(new ChunkPosition(l, i1, j1));
                        d0 += d3 * (double)f2;
                        d1 += d4 * (double)f2;
                        d2 += d5 * (double)f2;
                        f1 -= f2 * 0.75F;
                    } while(true);
                }



        size *= 2.0F;
        i = MathHelper.floor(posX - (double)size - 1.0D);
        j = MathHelper.floor(posX + (double)size + 1.0D);
        k = MathHelper.floor(posY - (double)size - 1.0D);
        int l1 = MathHelper.floor(posY + (double)size + 1.0D);
        int i2 = MathHelper.floor(posZ - (double)size - 1.0D);
        int j2 = MathHelper.floor(posZ + (double)size + 1.0D);
        List list = world.b(source, AxisAlignedBB.b(i, k, i2, j, l1, j2));
        Vec3D vec3d = Vec3D.create(posX, posY, posZ);
        for(int k2 = 0; k2 < list.size(); k2++)
        {
            Entity entity = (Entity)list.get(k2);
            double d7 = entity.f(posX, posY, posZ) / (double)size;
            if(d7 > 1.0D)
                continue;
            double d0 = entity.locX - posX;
            double d1 = entity.locY - posY;
            double d2 = entity.locZ - posZ;
            double d8 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
            d0 /= d8;
            d1 /= d8;
            d2 /= d8;
            double d9 = world.a(vec3d, entity.boundingBox);
            double d10 = (1.0D - d7) * d9;
            Server server = world.getServer();
            org.bukkit.entity.Entity damagee = entity != null ? entity.getBukkitEntity() : null;
            int damageDone = (int)(((d10 * d10 + d10) / 2D) * 8D * (double)size + 1.0D);
            if(damagee == null)
                continue;
            EntityDamageByEntityEvent event;
            if(source == null)
            {
                event = new EntityDamageByBlockEvent(null, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, damageDone);
                server.getPluginManager().callEvent(event);
                if(!event.isCancelled())
                {
                    entity.damageEntity(DamageSource.EXPLOSION, event.getDamage());
                    entity.motX += d0 * d10;
                    entity.motY += d1 * d10;
                    entity.motZ += d2 * d10;
                }
                continue;
            }
            event = new EntityDamageByEntityEvent(source.getBukkitEntity(), damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, damageDone);
            server.getPluginManager().callEvent(event);
            if(!event.isCancelled())
            {
                entity.damageEntity(DamageSource.EXPLOSION, event.getDamage());
                entity.motX += d0 * d10;
                entity.motY += d1 * d10;
                entity.motZ += d2 * d10;
            }
        }

        size = f;
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(blocks);
        if(a)
        {
            for(int l2 = arraylist.size() - 1; l2 >= 0; l2--)
            {
                ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l2);
                int i3 = chunkposition.x;
                int j3 = chunkposition.y;
                int k3 = chunkposition.z;
                int l3 = world.getTypeId(i3, j3, k3);
                int i4 = world.getTypeId(i3, j3 - 1, k3);
                if(l3 == 0 && Block.o[i4] && h.nextInt(3) == 0)
                    world.setTypeId(i3, j3, k3, Block.FIRE.id);
            }

        }
    }

    public void a(boolean flag)
    {
        world.makeSound(posX, posY, posZ, "random.explode", 4F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
        world.a("hugeexplosion", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(blocks);
        World bworld = world.getWorld();
        org.bukkit.entity.Entity explode = source != null ? source.getBukkitEntity() : null;
        Location location = new Location(bworld, posX, posY, posZ);
        List blockList = new ArrayList();
        for(int j = arraylist.size() - 1; j >= 0; j--)
        {
            ChunkPosition cpos = (ChunkPosition)arraylist.get(j);
            Block block = bworld.getBlockAt(cpos.x, cpos.y, cpos.z);
            if(block.getType() != Material.AIR)
                blockList.add(block);
        }

        EntityExplodeEvent event = new EntityExplodeEvent(explode, location, blockList);
        world.getServer().getPluginManager().callEvent(event);
        arraylist.clear();
        blocks.clear();
        ChunkPosition coords;
        for(Iterator i$ = event.blockList().iterator(); i$.hasNext(); blocks.add(coords))
        {
            Block block = (Block)i$.next();
            coords = new ChunkPosition(block.getX(), block.getY(), block.getZ());
            arraylist.add(coords);
        }

        if(event.isCancelled())
        {
            wasCanceled = true;
            return;
        }
        for(int i = arraylist.size() - 1; i >= 0; i--)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(i);
            int j = chunkposition.x;
            int k = chunkposition.y;
            int l = chunkposition.z;
            int i1 = world.getTypeId(j, k, l);
            if(flag)
            {
                double d0 = (float)j + world.random.nextFloat();
                double d1 = (float)k + world.random.nextFloat();
                double d2 = (float)l + world.random.nextFloat();
                double d3 = d0 - posX;
                double d4 = d1 - posY;
                double d5 = d2 - posZ;
                double d6 = MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
                d3 /= d6;
                d4 /= d6;
                d5 /= d6;
                double d7 = 0.5D / (d6 / (double)size + 0.10000000000000001D);
                d7 *= world.random.nextFloat() * world.random.nextFloat() + 0.3F;
                d3 *= d7;
                d4 *= d7;
                d5 *= d7;
                world.a("explode", (d0 + posX * 1.0D) / 2D, (d1 + posY * 1.0D) / 2D, (d2 + posZ * 1.0D) / 2D, d3, d4, d5);
                world.a("smoke", d0, d1, d2, d3, d4, d5);
            }
            if(i1 > 0 && i1 != Block.FIRE.id)
            {
                Block.byId[i1].dropNaturally(world, j, k, l, world.getData(j, k, l), event.getYield());
                world.setTypeId(j, k, l, 0);
                Block.byId[i1].a_(world, j, k, l);
            }
        }

    }

    public boolean a;
    private Random h;
    private net.minecraft.server.World world;
    public double posX;
    public double posY;
    public double posZ;
    public Entity source;
    public float size;
    public Set blocks;
    public boolean wasCanceled;
}
