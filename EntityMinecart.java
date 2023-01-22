// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityMinecart.java

package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.*;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, ItemStack, EntityItem, BlockMinecartTrack, 
//            NBTTagList, NBTTagCompound, EntityLiving, EntityHuman, 
//            IInventory, World, DamageSource, Item, 
//            Block, MathHelper, Vec3D, AxisAlignedBB, 
//            InventoryPlayer

public class EntityMinecart extends Entity
    implements IInventory
{

    public ItemStack[] getContents()
    {
        return items;
    }

    public EntityMinecart(World world)
    {
        super(world);
        slowWhenEmpty = true;
        derailedX = 0.5D;
        derailedY = 0.5D;
        derailedZ = 0.5D;
        flyingX = 0.94999999999999996D;
        flyingY = 0.94999999999999996D;
        flyingZ = 0.94999999999999996D;
        maxSpeed = 0.40000000000000002D;
        items = new ItemStack[27];
        damage = 0;
        b = 0;
        c = 1;
        i = false;
        aY = true;
        b(0.98F, 0.7F);
        height = width / 2.0F;
    }

    protected boolean e_()
    {
        return false;
    }

    protected void b()
    {
    }

    public AxisAlignedBB b(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB f()
    {
        return null;
    }

    public boolean g()
    {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2, 
            int i)
    {
        this(world);
        setPosition(d0, d1 + (double)height, d2);
        motX = 0.0D;
        motY = 0.0D;
        motZ = 0.0D;
        lastX = d0;
        lastY = d1;
        lastZ = d2;
        type = i;
        this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
    }

    public double n()
    {
        return (double)width * 0.0D - 0.30000001192092896D;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(!world.isStatic && !dead)
        {
            Vehicle vehicle = (Vehicle)getBukkitEntity();
            org.bukkit.entity.Entity passenger = damagesource.getEntity() != null ? damagesource.getEntity().getBukkitEntity() : null;
            VehicleDamageEvent event = new VehicleDamageEvent(vehicle, passenger, i);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return true;
            i = event.getDamage();
            c = -c;
            b = 10;
            aq();
            damage += i * 10;
            if(damage > 40)
            {
                if(this.passenger != null)
                    this.passenger.mount(this);
                VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, passenger);
                world.getServer().getPluginManager().callEvent(destroyEvent);
                if(destroyEvent.isCancelled())
                {
                    damage = 40;
                    return true;
                }
                die();
                a(Item.MINECART.id, 1, 0.0F);
                if(type == 1)
                {
                    EntityMinecart entityminecart = this;
label0:
                    for(int j = 0; j < entityminecart.getSize(); j++)
                    {
                        ItemStack itemstack = entityminecart.getItem(j);
                        if(itemstack == null)
                            continue;
                        float f = random.nextFloat() * 0.8F + 0.1F;
                        float f1 = random.nextFloat() * 0.8F + 0.1F;
                        float f2 = random.nextFloat() * 0.8F + 0.1F;
                        do
                        {
                            if(itemstack.count <= 0)
                                continue label0;
                            int k = random.nextInt(21) + 10;
                            if(k > itemstack.count)
                                k = itemstack.count;
                            itemstack.count -= k;
                            EntityItem entityitem = new EntityItem(world, locX + (double)f, locY + (double)f1, locZ + (double)f2, new ItemStack(itemstack.id, k, itemstack.getData()));
                            float f3 = 0.05F;
                            entityitem.motX = (float)random.nextGaussian() * f3;
                            entityitem.motY = (float)random.nextGaussian() * f3 + 0.2F;
                            entityitem.motZ = (float)random.nextGaussian() * f3;
                            world.addEntity(entityitem);
                        } while(true);
                    }

                    a(Block.CHEST.id, 1, 0.0F);
                } else
                if(type == 2)
                    a(Block.FURNACE.id, 1, 0.0F);
            }
            return true;
        } else
        {
            return true;
        }
    }

    public boolean r_()
    {
        return !dead;
    }

    public void die()
    {
label0:
        for(int i = 0; i < getSize(); i++)
        {
            ItemStack itemstack = getItem(i);
            if(itemstack == null)
                continue;
            float f = random.nextFloat() * 0.8F + 0.1F;
            float f1 = random.nextFloat() * 0.8F + 0.1F;
            float f2 = random.nextFloat() * 0.8F + 0.1F;
            do
            {
                if(itemstack.count <= 0)
                    continue label0;
                int j = random.nextInt(21) + 10;
                if(j > itemstack.count)
                    j = itemstack.count;
                itemstack.count -= j;
                EntityItem entityitem = new EntityItem(world, locX + (double)f, locY + (double)f1, locZ + (double)f2, new ItemStack(itemstack.id, j, itemstack.getData()));
                float f3 = 0.05F;
                entityitem.motX = (float)random.nextGaussian() * f3;
                entityitem.motY = (float)random.nextGaussian() * f3 + 0.2F;
                entityitem.motZ = (float)random.nextGaussian() * f3;
                world.addEntity(entityitem);
            } while(true);
        }

        super.die();
    }

    public void s_()
    {
        double prevX = locX;
        double prevY = locY;
        double prevZ = locZ;
        float prevYaw = yaw;
        float prevPitch = pitch;
        if(b > 0)
            b--;
        if(damage > 0)
            damage--;
        if(world.isStatic && this.k > 0)
        {
            if(this.k > 0)
            {
                double d1 = locX + (this.l - locX) / (double)this.k;
                double d2 = locY + (m - locY) / (double)this.k;
                double d3 = locZ + (n - locZ) / (double)this.k;
                double d0;
                for(d0 = o - (double)yaw; d0 < -180D; d0 += 360D);
                for(; d0 >= 180D; d0 -= 360D);
                yaw = (float)((double)yaw + d0 / (double)this.k);
                pitch = (float)((double)pitch + (p - (double)pitch) / (double)this.k);
                this.k--;
                setPosition(d1, d2, d3);
                c(yaw, pitch);
            } else
            {
                setPosition(locX, locY, locZ);
                c(yaw, pitch);
            }
        } else
        {
            lastX = locX;
            lastY = locY;
            lastZ = locZ;
            motY -= 0.039999999105930328D;
            int i = MathHelper.floor(locX);
            int j = MathHelper.floor(locY);
            int k = MathHelper.floor(locZ);
            if(BlockMinecartTrack.g(world, i, j - 1, k))
                j--;
            double d4 = maxSpeed;
            boolean flag = false;
            double d0 = 0.0078125D;
            int l = world.getTypeId(i, j, k);
            if(BlockMinecartTrack.c(l))
            {
                Vec3D vec3d = h(locX, locY, locZ);
                int i1 = world.getData(i, j, k);
                locY = j;
                boolean flag1 = false;
                boolean flag2 = false;
                if(l == Block.GOLDEN_RAIL.id)
                {
                    flag1 = (i1 & 8) != 0;
                    flag2 = !flag1;
                }
                if(((BlockMinecartTrack)Block.byId[l]).f())
                    i1 &= 7;
                if(i1 >= 2 && i1 <= 5)
                    locY = j + 1;
                if(i1 == 2)
                    motX -= d0;
                if(i1 == 3)
                    motX += d0;
                if(i1 == 4)
                    motZ += d0;
                if(i1 == 5)
                    motZ -= d0;
                int aint[][] = matrix[i1];
                double d5 = aint[1][0] - aint[0][0];
                double d6 = aint[1][2] - aint[0][2];
                double d7 = Math.sqrt(d5 * d5 + d6 * d6);
                double d8 = motX * d5 + motZ * d6;
                if(d8 < 0.0D)
                {
                    d5 = -d5;
                    d6 = -d6;
                }
                double d9 = Math.sqrt(motX * motX + motZ * motZ);
                motX = (d9 * d5) / d7;
                motZ = (d9 * d6) / d7;
                double d10;
                if(flag2)
                {
                    d10 = Math.sqrt(motX * motX + motZ * motZ);
                    if(d10 < 0.029999999999999999D)
                    {
                        motX *= 0.0D;
                        motY *= 0.0D;
                        motZ *= 0.0D;
                    } else
                    {
                        motX *= 0.5D;
                        motY *= 0.0D;
                        motZ *= 0.5D;
                    }
                }
                d10 = 0.0D;
                double d11 = (double)i + 0.5D + (double)aint[0][0] * 0.5D;
                double d12 = (double)k + 0.5D + (double)aint[0][2] * 0.5D;
                double d13 = (double)i + 0.5D + (double)aint[1][0] * 0.5D;
                double d14 = (double)k + 0.5D + (double)aint[1][2] * 0.5D;
                d5 = d13 - d11;
                d6 = d14 - d12;
                double d15;
                double d16;
                if(d5 == 0.0D)
                {
                    locX = (double)i + 0.5D;
                    d10 = locZ - (double)k;
                } else
                if(d6 == 0.0D)
                {
                    locZ = (double)k + 0.5D;
                    d10 = locX - (double)i;
                } else
                {
                    d16 = locX - d11;
                    d15 = locZ - d12;
                    double d17 = (d16 * d5 + d15 * d6) * 2D;
                    d10 = d17;
                }
                locX = d11 + d5 * d10;
                locZ = d12 + d6 * d10;
                setPosition(locX, locY + (double)height, locZ);
                d16 = motX;
                d15 = motZ;
                if(passenger != null)
                {
                    d16 *= 0.75D;
                    d15 *= 0.75D;
                }
                if(d16 < -d4)
                    d16 = -d4;
                if(d16 > d4)
                    d16 = d4;
                if(d15 < -d4)
                    d15 = -d4;
                if(d15 > d4)
                    d15 = d4;
                move(d16, 0.0D, d15);
                if(aint[0][1] != 0 && MathHelper.floor(locX) - i == aint[0][0] && MathHelper.floor(locZ) - k == aint[0][2])
                    setPosition(locX, locY + (double)aint[0][1], locZ);
                else
                if(aint[1][1] != 0 && MathHelper.floor(locX) - i == aint[1][0] && MathHelper.floor(locZ) - k == aint[1][2])
                    setPosition(locX, locY + (double)aint[1][1], locZ);
                if(passenger != null || !slowWhenEmpty)
                {
                    motX *= 0.99699997901916504D;
                    motY *= 0.0D;
                    motZ *= 0.99699997901916504D;
                } else
                {
                    if(type == 2)
                    {
                        double d17 = MathHelper.a(f * f + g * g);
                        if(d17 > 0.01D)
                        {
                            flag = true;
                            f /= d17;
                            g /= d17;
                            double d18 = 0.040000000000000001D;
                            motX *= 0.80000001192092896D;
                            motY *= 0.0D;
                            motZ *= 0.80000001192092896D;
                            motX += f * d18;
                            motZ += g * d18;
                        } else
                        {
                            motX *= 0.89999997615814209D;
                            motY *= 0.0D;
                            motZ *= 0.89999997615814209D;
                        }
                    }
                    motX *= 0.95999997854232788D;
                    motY *= 0.0D;
                    motZ *= 0.95999997854232788D;
                }
                Vec3D vec3d1 = h(locX, locY, locZ);
                if(vec3d1 != null && vec3d != null)
                {
                    double d19 = (vec3d.b - vec3d1.b) * 0.050000000000000003D;
                    d9 = Math.sqrt(motX * motX + motZ * motZ);
                    if(d9 > 0.0D)
                    {
                        motX = (motX / d9) * (d9 + d19);
                        motZ = (motZ / d9) * (d9 + d19);
                    }
                    setPosition(locX, vec3d1.b, locZ);
                }
                int j1 = MathHelper.floor(locX);
                int k1 = MathHelper.floor(locZ);
                if(j1 != i || k1 != k)
                {
                    d9 = Math.sqrt(motX * motX + motZ * motZ);
                    motX = d9 * (double)(j1 - i);
                    motZ = d9 * (double)(k1 - k);
                }
                if(type == 2)
                {
                    double d20 = MathHelper.a(f * f + g * g);
                    if(d20 > 0.01D && motX * motX + motZ * motZ > 0.001D)
                    {
                        f /= d20;
                        g /= d20;
                        if(f * motX + g * motZ < 0.0D)
                        {
                            f = 0.0D;
                            g = 0.0D;
                        } else
                        {
                            f = motX;
                            g = motZ;
                        }
                    }
                }
                if(flag1)
                {
                    double d20 = Math.sqrt(motX * motX + motZ * motZ);
                    if(d20 > 0.01D)
                    {
                        double d21 = 0.059999999999999998D;
                        motX += (motX / d20) * d21;
                        motZ += (motZ / d20) * d21;
                    } else
                    if(i1 == 1)
                    {
                        if(world.e(i - 1, j, k))
                            motX = 0.02D;
                        else
                        if(world.e(i + 1, j, k))
                            motX = -0.02D;
                    } else
                    if(i1 == 0)
                        if(world.e(i, j, k - 1))
                            motZ = 0.02D;
                        else
                        if(world.e(i, j, k + 1))
                            motZ = -0.02D;
                }
            } else
            {
                if(motX < -d4)
                    motX = -d4;
                if(motX > d4)
                    motX = d4;
                if(motZ < -d4)
                    motZ = -d4;
                if(motZ > d4)
                    motZ = d4;
                if(onGround)
                {
                    motX *= derailedX;
                    motY *= derailedY;
                    motZ *= derailedZ;
                }
                move(motX, motY, motZ);
                if(!onGround)
                {
                    motX *= flyingX;
                    motY *= flyingY;
                    motZ *= flyingZ;
                }
            }
            pitch = 0.0F;
            double d22 = lastX - locX;
            double d23 = lastZ - locZ;
            if(d22 * d22 + d23 * d23 > 0.001D)
            {
                yaw = (float)((Math.atan2(d23, d22) * 180D) / 3.1415926535897931D);
                if(this.i)
                    yaw += 180F;
            }
            double d24;
            for(d24 = yaw - lastYaw; d24 >= 180D; d24 -= 360D);
            for(; d24 < -180D; d24 += 360D);
            if(d24 < -170D || d24 >= 170D)
            {
                yaw += 180F;
                this.i = !this.i;
            }
            c(yaw, pitch);
            org.bukkit.World bworld = world.getWorld();
            Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
            Location to = new Location(bworld, locX, locY, locZ, yaw, pitch);
            Vehicle vehicle = (Vehicle)getBukkitEntity();
            world.getServer().getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
            if(!from.equals(to))
                world.getServer().getPluginManager().callEvent(new VehicleMoveEvent(vehicle, from, to));
            List list = world.b(this, boundingBox.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            if(list != null && list.size() > 0)
            {
                for(int l1 = 0; l1 < list.size(); l1++)
                {
                    Entity entity = (Entity)list.get(l1);
                    if(entity != passenger && entity.g() && (entity instanceof EntityMinecart))
                        entity.collide(this);
                }

            }
            if(passenger != null && passenger.dead)
            {
                passenger.vehicle = null;
                passenger = null;
            }
            if(flag && random.nextInt(4) == 0)
            {
                e--;
                if(e < 0)
                    f = g = 0.0D;
                world.a("largesmoke", locX, locY + 0.80000000000000004D, locZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public Vec3D h(double d0, double d1, double d2)
    {
        int i = MathHelper.floor(d0);
        int j = MathHelper.floor(d1);
        int k = MathHelper.floor(d2);
        if(BlockMinecartTrack.g(world, i, j - 1, k))
            j--;
        int l = world.getTypeId(i, j, k);
        if(BlockMinecartTrack.c(l))
        {
            int i1 = world.getData(i, j, k);
            d1 = j;
            if(((BlockMinecartTrack)Block.byId[l]).f())
                i1 &= 7;
            if(i1 >= 2 && i1 <= 5)
                d1 = j + 1;
            int aint[][] = matrix[i1];
            double d3 = 0.0D;
            double d4 = (double)i + 0.5D + (double)aint[0][0] * 0.5D;
            double d5 = (double)j + 0.5D + (double)aint[0][1] * 0.5D;
            double d6 = (double)k + 0.5D + (double)aint[0][2] * 0.5D;
            double d7 = (double)i + 0.5D + (double)aint[1][0] * 0.5D;
            double d8 = (double)j + 0.5D + (double)aint[1][1] * 0.5D;
            double d9 = (double)k + 0.5D + (double)aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2D;
            double d12 = d9 - d6;
            if(d10 == 0.0D)
            {
                d0 = (double)i + 0.5D;
                d3 = d2 - (double)k;
            } else
            if(d12 == 0.0D)
            {
                d2 = (double)k + 0.5D;
                d3 = d0 - (double)i;
            } else
            {
                double d13 = d0 - d4;
                double d14 = d2 - d6;
                double d15 = (d13 * d10 + d14 * d12) * 2D;
                d3 = d15;
            }
            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if(d11 < 0.0D)
                d1++;
            if(d11 > 0.0D)
                d1 += 0.5D;
            return Vec3D.create(d0, d1, d2);
        } else
        {
            return null;
        }
    }

    protected void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Type", type);
        if(type == 2)
        {
            nbttagcompound.a("PushX", f);
            nbttagcompound.a("PushZ", g);
            nbttagcompound.a("Fuel", (short)e);
        } else
        if(type == 1)
        {
            NBTTagList nbttaglist = new NBTTagList();
            for(int i = 0; i < items.length; i++)
                if(items[i] != null)
                {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.a("Slot", (byte)i);
                    items[i].b(nbttagcompound1);
                    nbttaglist.a(nbttagcompound1);
                }

            nbttagcompound.a("Items", nbttaglist);
        }
    }

    protected void a(NBTTagCompound nbttagcompound)
    {
        type = nbttagcompound.e("Type");
        if(type == 2)
        {
            f = nbttagcompound.h("PushX");
            g = nbttagcompound.h("PushZ");
            e = nbttagcompound.d("Fuel");
        } else
        if(type == 1)
        {
            NBTTagList nbttaglist = nbttagcompound.l("Items");
            items = new ItemStack[getSize()];
            for(int i = 0; i < nbttaglist.c(); i++)
            {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(i);
                int j = nbttagcompound1.c("Slot") & 0xff;
                if(j >= 0 && j < items.length)
                    items[j] = ItemStack.a(nbttagcompound1);
            }

        }
    }

    public void collide(Entity entity)
    {
        if(!world.isStatic && entity != passenger)
        {
            Vehicle vehicle = (Vehicle)getBukkitEntity();
            org.bukkit.entity.Entity hitEntity = entity != null ? entity.getBukkitEntity() : null;
            VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, hitEntity);
            world.getServer().getPluginManager().callEvent(collisionEvent);
            if(collisionEvent.isCancelled())
                return;
            if((entity instanceof EntityLiving) && !(entity instanceof EntityHuman) && type == 0 && motX * motX + motZ * motZ > 0.01D && passenger == null && entity.vehicle == null && !collisionEvent.isPickupCancelled())
            {
                VehicleEnterEvent enterEvent = new VehicleEnterEvent(vehicle, hitEntity);
                world.getServer().getPluginManager().callEvent(enterEvent);
                if(!enterEvent.isCancelled())
                    entity.mount(this);
            }
            double d0 = entity.locX - locX;
            double d1 = entity.locZ - locZ;
            double d2 = d0 * d0 + d1 * d1;
            if(d2 >= 9.9999997473787516E-005D && !collisionEvent.isCollisionCancelled())
            {
                d2 = MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;
                if(d3 > 1.0D)
                    d3 = 1.0D;
                d0 *= d3;
                d1 *= d3;
                d0 *= 0.10000000149011612D;
                d1 *= 0.10000000149011612D;
                d0 *= 1.0F - bK;
                d1 *= 1.0F - bK;
                d0 *= 0.5D;
                d1 *= 0.5D;
                if(entity instanceof EntityMinecart)
                {
                    double d4 = entity.locX - locX;
                    double d5 = entity.locZ - locZ;
                    double d6 = d4 * entity.motZ + d5 * entity.lastX;
                    d6 *= d6;
                    if(d6 > 5D)
                        return;
                    double d7 = entity.motX + motX;
                    double d8 = entity.motZ + motZ;
                    if(((EntityMinecart)entity).type == 2 && type != 2)
                    {
                        motX *= 0.20000000298023224D;
                        motZ *= 0.20000000298023224D;
                        b(entity.motX - d0, 0.0D, entity.motZ - d1);
                        entity.motX *= 0.69999998807907104D;
                        entity.motZ *= 0.69999998807907104D;
                    } else
                    if(((EntityMinecart)entity).type != 2 && type == 2)
                    {
                        entity.motX *= 0.20000000298023224D;
                        entity.motZ *= 0.20000000298023224D;
                        entity.b(motX + d0, 0.0D, motZ + d1);
                        motX *= 0.69999998807907104D;
                        motZ *= 0.69999998807907104D;
                    } else
                    {
                        d7 /= 2D;
                        d8 /= 2D;
                        motX *= 0.20000000298023224D;
                        motZ *= 0.20000000298023224D;
                        b(d7 - d0, 0.0D, d8 - d1);
                        entity.motX *= 0.20000000298023224D;
                        entity.motZ *= 0.20000000298023224D;
                        entity.b(d7 + d0, 0.0D, d8 + d1);
                    }
                } else
                {
                    b(-d0, 0.0D, -d1);
                    entity.b(d0 / 4D, 0.0D, d1 / 4D);
                }
            }
        }
    }

    public int getSize()
    {
        return 27;
    }

    public ItemStack getItem(int i)
    {
        return items[i];
    }

    public ItemStack splitStack(int i, int j)
    {
        if(items[i] != null)
        {
            ItemStack itemstack;
            if(items[i].count <= j)
            {
                itemstack = items[i];
                items[i] = null;
                return itemstack;
            }
            itemstack = items[i].a(j);
            if(items[i].count == 0)
                items[i] = null;
            return itemstack;
        } else
        {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack)
    {
        items[i] = itemstack;
        if(itemstack != null && itemstack.count > getMaxStackSize())
            itemstack.count = getMaxStackSize();
    }

    public String getName()
    {
        return "Minecart";
    }

    public int getMaxStackSize()
    {
        return 64;
    }

    public void update()
    {
    }

    public boolean b(EntityHuman entityhuman)
    {
        if(type == 0)
        {
            if(passenger != null && (passenger instanceof EntityHuman) && passenger != entityhuman)
                return true;
            if(!world.isStatic)
            {
                org.bukkit.entity.Entity player = entityhuman != null ? entityhuman.getBukkitEntity() : null;
                VehicleEnterEvent event = new VehicleEnterEvent((Vehicle)getBukkitEntity(), player);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled())
                    return true;
                entityhuman.mount(this);
            }
        } else
        if(type == 1)
        {
            if(!world.isStatic)
                entityhuman.a(this);
        } else
        if(type == 2)
        {
            ItemStack itemstack = entityhuman.inventory.getItemInHand();
            if(itemstack != null && itemstack.id == Item.COAL.id)
            {
                if(--itemstack.count == 0)
                    entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
                e += 1200;
            }
            f = locX - entityhuman.locX;
            g = locZ - entityhuman.locZ;
        }
        return true;
    }

    public boolean a(EntityHuman entityhuman)
    {
        return dead ? false : entityhuman.h(this) <= 64D;
    }

    public void e()
    {
    }

    public void t_()
    {
    }

    private ItemStack items[];
    public int damage;
    public int b;
    public int c;
    private boolean i;
    public int type;
    public int e;
    public double f;
    public double g;
    private static final int matrix[][][] = {
        {
            {
                0, 0, -1
            }, {
                0, 0, 1
            }
        }, {
            {
                -1, 0, 0
            }, {
                1, 0, 0
            }
        }, {
            {
                -1, -1, 0
            }, {
                1, 0, 0
            }
        }, {
            {
                -1, 0, 0
            }, {
                1, -1, 0
            }
        }, {
            {
                0, 0, -1
            }, {
                0, -1, 1
            }
        }, {
            {
                0, -1, -1
            }, {
                0, 0, 1
            }
        }, {
            {
                0, 0, 1
            }, {
                1, 0, 0
            }
        }, {
            {
                0, 0, 1
            }, {
                -1, 0, 0
            }
        }, {
            {
                0, 0, -1
            }, {
                -1, 0, 0
            }
        }, {
            {
                0, 0, -1
            }, {
                1, 0, 0
            }
        }
    };
    private int k;
    private double l;
    private double m;
    private double n;
    private double o;
    private double p;
    public boolean slowWhenEmpty;
    public double derailedX;
    public double derailedY;
    public double derailedZ;
    public double flyingX;
    public double flyingY;
    public double flyingZ;
    public double maxSpeed;

}
