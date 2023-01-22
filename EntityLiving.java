// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityLiving.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, EntityExperienceOrb, EntityHuman, EntityWolf, 
//            NBTTagList, MobEffect, NBTTagCompound, AxisAlignedBB, 
//            Vec3D, World, DamageSource, Material, 
//            MobEffectList, MathHelper, Block, StepSound

public abstract class EntityLiving extends Entity
{

    public EntityLiving(World world)
    {
        super(world);
        maxNoDamageTicks = 20;
        U = 0.0F;
        V = 0.0F;
        aa = true;
        texture = "/mob/char.png";
        ac = true;
        ad = 0.0F;
        ae = null;
        af = 1.0F;
        ag = 0;
        ah = 0.0F;
        ai = false;
        aj = 0.1F;
        ak = 0.02F;
        health = 10;
        ar = 0.0F;
        deathTicks = 0;
        attackTicks = 0;
        aw = false;
        ay = -1;
        az = (float)(Math.random() * 0.89999997615814209D + 0.10000000149011612D);
        b = null;
        c = 0;
        aD = 0;
        aE = 0;
        effects = new HashMap();
        aM = 0.0F;
        lastDamage = 0;
        aO = 0;
        aS = false;
        aT = 0.0F;
        aU = 0.7F;
        aV = 0;
        expToDrop = 0;
        aY = true;
        T = (float)(Math.random() + 1.0D) * 0.01F;
        setPosition(locX, locY, locZ);
        S = (float)Math.random() * 12398F;
        yaw = (float)(Math.random() * 3.1415927410125732D * 2D);
        bI = 0.5F;
    }

    protected void b()
    {
    }

    public boolean f(Entity entity)
    {
        return world.a(Vec3D.create(locX, locY + (double)t(), locZ), Vec3D.create(entity.locX, entity.locY + (double)entity.t(), entity.locZ)) == null;
    }

    public boolean r_()
    {
        return !dead;
    }

    public boolean g()
    {
        return !dead;
    }

    public float t()
    {
        return width * 0.85F;
    }

    public int e()
    {
        return 80;
    }

    public void Z()
    {
        String s = h();
        if(s != null)
            world.makeSound(this, s, l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }

    public void aa()
    {
        al = am;
        super.aa();
        if(random.nextInt(1000) < a++)
        {
            a = -e();
            Z();
        }
        if(ac() && O())
        {
            EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.SUFFOCATION, 1);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                damageEntity(DamageSource.STUCK, event.getDamage());
        }
        if(fireProof || world.isStatic)
            fireTicks = 0;
        if(ac() && a(Material.WATER) && !b_() && !effects.containsKey(Integer.valueOf(MobEffectList.WATER_BREATHING.id)))
        {
            airTicks--;
            if(airTicks == -20)
            {
                airTicks = 0;
                for(int i = 0; i < 8; i++)
                {
                    float f = random.nextFloat() - random.nextFloat();
                    float f1 = random.nextFloat() - random.nextFloat();
                    float f2 = random.nextFloat() - random.nextFloat();
                    world.a("bubble", locX + (double)f, locY + (double)f1, locZ + (double)f2, motX, motY, motZ);
                }

                EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.DROWNING, 2);
                world.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled() && event.getDamage() != 0)
                    damageEntity(DamageSource.DROWN, event.getDamage());
            }
            fireTicks = 0;
        } else
        {
            airTicks = maxAirTicks;
        }
        au = av;
        if(attackTicks > 0)
            attackTicks--;
        if(hurtTicks > 0)
            hurtTicks--;
        if(noDamageTicks > 0)
            noDamageTicks--;
        if(health <= 0)
        {
            deathTicks++;
            if(deathTicks > 20)
            {
                if(expToDrop > 0)
                {
                    for(int i = expToDrop; i > 0;)
                    {
                        int j = EntityExperienceOrb.b(i);
                        i -= j;
                        world.addEntity(new EntityExperienceOrb(world, locX, locY, locZ, j));
                    }

                }
                ag();
                die();
                for(int i = 0; i < 20; i++)
                {
                    double d0 = random.nextGaussian() * 0.02D;
                    double d1 = random.nextGaussian() * 0.02D;
                    double d2 = random.nextGaussian() * 0.02D;
                    world.a("explode", (locX + (double)(random.nextFloat() * length * 2.0F)) - (double)length, locY + (double)(random.nextFloat() * width), (locZ + (double)(random.nextFloat() * length * 2.0F)) - (double)length, d0, d1, d2);
                }

            }
        }
        if(c > 0)
            c--;
        else
            b = null;
        aj();
        Z = Y;
        V = U;
        lastYaw = yaw;
        lastPitch = pitch;
    }

    protected int a(EntityHuman entityhuman)
    {
        return ax;
    }

    public int getExpReward()
    {
        int exp = a(b);
        if(c > 0 || X())
            return exp;
        else
            return 0;
    }

    protected boolean X()
    {
        return false;
    }

    public void ab()
    {
        for(int i = 0; i < 20; i++)
        {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = 10D;
            world.a("explode", (locX + (double)(random.nextFloat() * length * 2.0F)) - (double)length - d0 * d3, (locY + (double)(random.nextFloat() * width)) - d1 * d3, (locZ + (double)(random.nextFloat() * length * 2.0F)) - (double)length - d2 * d3, d0, d1, d2);
        }

    }

    public void I()
    {
        super.I();
        W = X;
        X = 0.0F;
    }

    public void s_()
    {
        super.s_();
        if(aD > 0)
        {
            if(aE <= 0)
                aE = 60;
            aE--;
            if(aE <= 0)
                aD--;
        }
        s();
        double d0 = locX - lastX;
        double d1 = locZ - lastZ;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = U;
        float f2 = 0.0F;
        W = X;
        float f3 = 0.0F;
        if(f > 0.05F)
        {
            f3 = 1.0F;
            f2 = f * 3F;
            f1 = ((float)TrigMath.atan2(d1, d0) * 180F) / 3.141593F - 90F;
        }
        if(am > 0.0F)
            f1 = yaw;
        if(!onGround)
            f3 = 0.0F;
        X += (f3 - X) * 0.3F;
        float f4;
        for(f4 = f1 - U; f4 < -180F; f4 += 360F);
        for(; f4 >= 180F; f4 -= 360F);
        U += f4 * 0.3F;
        float f5;
        for(f5 = yaw - U; f5 < -180F; f5 += 360F);
        for(; f5 >= 180F; f5 -= 360F);
        boolean flag = f5 < -90F || f5 >= 90F;
        if(f5 < -75F)
            f5 = -75F;
        if(f5 >= 75F)
            f5 = 75F;
        U = yaw - f5;
        if(f5 * f5 > 2500F)
            U += f5 * 0.2F;
        if(flag)
            f2 *= -1F;
        for(; yaw - lastYaw < -180F; lastYaw -= 360F);
        for(; yaw - lastYaw >= 180F; lastYaw += 360F);
        for(; U - V < -180F; V -= 360F);
        for(; U - V >= 180F; V += 360F);
        for(; pitch - lastPitch < -180F; lastPitch -= 360F);
        for(; pitch - lastPitch >= 180F; lastPitch += 360F);
        Y += f2;
    }

    protected void b(float f, float f1)
    {
        super.b(f, f1);
    }

    public void c(int i)
    {
        c(i, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.CUSTOM);
    }

    public void c(int i, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason regainReason)
    {
        if(health > 0)
        {
            EntityRegainHealthEvent event = new EntityRegainHealthEvent(getBukkitEntity(), i, regainReason);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled())
                health += event.getAmount();
            if(health > 20)
                health = 20;
            noDamageTicks = maxNoDamageTicks / 2;
        }
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(world.isStatic)
            return false;
        aO = 0;
        if(health <= 0)
            return false;
        aB = 1.5F;
        boolean flag = true;
        if((float)noDamageTicks > (float)maxNoDamageTicks / 2.0F)
        {
            if(i <= lastDamage)
                return false;
            b(damagesource, i - lastDamage);
            lastDamage = i;
            flag = false;
        } else
        {
            lastDamage = i;
            ao = health;
            noDamageTicks = maxNoDamageTicks;
            b(damagesource, i);
            hurtTicks = aq = 10;
        }
        ar = 0.0F;
        Entity entity = damagesource.getEntity();
        if(entity != null)
            if(entity instanceof EntityHuman)
            {
                c = 60;
                b = (EntityHuman)entity;
            } else
            if(entity instanceof EntityWolf)
            {
                EntityWolf entitywolf = (EntityWolf)entity;
                if(entitywolf.isTamed())
                {
                    c = 60;
                    b = null;
                }
            }
        if(flag)
        {
            world.a(this, (byte)2);
            aq();
            if(entity != null)
            {
                double d0 = entity.locX - locX;
                double d1;
                for(d1 = entity.locZ - locZ; d0 * d0 + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
                    d0 = (Math.random() - Math.random()) * 0.01D;

                ar = (float)((Math.atan2(d1, d0) * 180D) / 3.1415927410125732D) - yaw;
                a(entity, i, d0, d1);
            } else
            {
                ar = (int)(Math.random() * 2D) * 180;
            }
        }
        if(health <= 0)
        {
            if(flag)
                world.makeSound(this, j(), l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            die(damagesource);
        } else
        if(flag)
            world.makeSound(this, i(), l(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        return true;
    }

    protected void b(DamageSource damagesource, int i)
    {
        health -= i;
    }

    protected float l()
    {
        return 1.0F;
    }

    protected String h()
    {
        return null;
    }

    protected String i()
    {
        return "random.hurt";
    }

    protected String j()
    {
        return "random.hurt";
    }

    public void a(Entity entity, int i, double d0, double d1)
    {
        ca = true;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;
        motX /= 2D;
        motY /= 2D;
        motZ /= 2D;
        motX -= (d0 / (double)f) * (double)f1;
        motY += 0.40000000596046448D;
        motZ -= (d1 / (double)f) * (double)f1;
        if(motY > 0.40000000596046448D)
            motY = 0.40000000596046448D;
    }

    public void die(DamageSource damagesource)
    {
        Entity entity = damagesource.getEntity();
        if(ag >= 0 && entity != null)
            entity.b(this, ag);
        if(entity != null)
            entity.a(this);
        aw = true;
        if(!world.isStatic)
            a(c > 0);
        world.a(this, (byte)3);
    }

    protected void a(boolean flag)
    {
        int i = k();
        List loot = new ArrayList();
        int count = random.nextInt(3);
        if(i > 0 && count > 0)
            loot.add(new ItemStack(i, count));
        CraftEventFactory.callEntityDeathEvent(this, loot);
    }

    protected int k()
    {
        return 0;
    }

    protected void a(float f)
    {
        super.a(f);
        int i = (int)Math.ceil(f - 3F);
        if(i > 0)
        {
            EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL, i);
            world.getServer().getPluginManager().callEvent(event);
            if(!event.isCancelled() && event.getDamage() != 0)
                damageEntity(DamageSource.FALL, event.getDamage());
            int j = world.getTypeId(MathHelper.floor(locX), MathHelper.floor(locY - 0.20000000298023224D - (double)height), MathHelper.floor(locZ));
            if(j > 0)
            {
                StepSound stepsound = Block.byId[j].stepSound;
                world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.5F, stepsound.getVolume2() * 0.75F);
            }
        }
    }

    public void a(float f, float f1)
    {
        double d0;
        if(ao())
        {
            d0 = locY;
            a(f, f1, 0.02F);
            move(motX, motY, motZ);
            motX *= 0.80000001192092896D;
            motY *= 0.80000001192092896D;
            motZ *= 0.80000001192092896D;
            motY -= 0.02D;
            if(positionChanged && d(motX, ((motY + 0.60000002384185791D) - locY) + d0, motZ))
                motY = 0.30000001192092896D;
        } else
        if(ap())
        {
            d0 = locY;
            a(f, f1, 0.02F);
            move(motX, motY, motZ);
            motX *= 0.5D;
            motY *= 0.5D;
            motZ *= 0.5D;
            motY -= 0.02D;
            if(positionChanged && d(motX, ((motY + 0.60000002384185791D) - locY) + d0, motZ))
                motY = 0.30000001192092896D;
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
            float f4 = onGround ? aj * f3 : ak;
            a(f, f1, f4);
            f2 = 0.91F;
            if(onGround)
            {
                f2 = 0.5460001F;
                int j = world.getTypeId(MathHelper.floor(locX), MathHelper.floor(boundingBox.b) - 1, MathHelper.floor(locZ));
                if(j > 0)
                    f2 = Block.byId[j].frictionFactor * 0.91F;
            }
            if(p())
            {
                float f5 = 0.15F;
                if(motX < (double)(-f5))
                    motX = -f5;
                if(motX > (double)f5)
                    motX = f5;
                if(motZ < (double)(-f5))
                    motZ = -f5;
                if(motZ > (double)f5)
                    motZ = f5;
                fallDistance = 0.0F;
                if(motY < -0.14999999999999999D)
                    motY = -0.14999999999999999D;
                if(isSneaking() && motY < 0.0D)
                    motY = 0.0D;
            }
            move(motX, motY, motZ);
            if(positionChanged && p())
                motY = 0.20000000000000001D;
            motY -= 0.080000000000000002D;
            motY *= 0.98000001907348633D;
            motX *= f2;
            motZ *= f2;
        }
        aA = aB;
        d0 = locX - lastX;
        double d1 = locZ - lastZ;
        float f6 = MathHelper.a(d0 * d0 + d1 * d1) * 4F;
        if(f6 > 1.0F)
            f6 = 1.0F;
        aB += (f6 - aB) * 0.4F;
        aC += aB;
    }

    public boolean p()
    {
        int i = MathHelper.floor(locX);
        int j = MathHelper.floor(boundingBox.b);
        int k = MathHelper.floor(locZ);
        return world.getTypeId(i, j, k) == Block.LADDER.id;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Health", (short)health);
        nbttagcompound.a("HurtTime", (short)hurtTicks);
        nbttagcompound.a("DeathTime", (short)deathTicks);
        nbttagcompound.a("AttackTime", (short)attackTicks);
        if(!effects.isEmpty())
        {
            NBTTagList nbttaglist = new NBTTagList();
            NBTTagCompound nbttagcompound1;
            for(Iterator iterator = effects.values().iterator(); iterator.hasNext(); nbttaglist.a(nbttagcompound1))
            {
                MobEffect mobeffect = (MobEffect)iterator.next();
                nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.a("Id", (byte)mobeffect.getEffectId());
                nbttagcompound1.a("Amplifier", (byte)mobeffect.getAmplifier());
                nbttagcompound1.a("Duration", mobeffect.getDuration());
            }

            nbttagcompound.a("ActiveEffects", nbttaglist);
        }
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        health = nbttagcompound.d("Health");
        if(!nbttagcompound.hasKey("Health"))
            health = 10;
        hurtTicks = nbttagcompound.d("HurtTime");
        deathTicks = nbttagcompound.d("DeathTime");
        attackTicks = nbttagcompound.d("AttackTime");
        if(nbttagcompound.hasKey("ActiveEffects"))
        {
            NBTTagList nbttaglist = nbttagcompound.l("ActiveEffects");
            for(int i = 0; i < nbttaglist.c(); i++)
            {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.a(i);
                byte b0 = nbttagcompound1.c("Id");
                byte b1 = nbttagcompound1.c("Amplifier");
                int j = nbttagcompound1.e("Duration");
                effects.put(Integer.valueOf(b0), new MobEffect(b0, j, b1));
            }

        }
    }

    public boolean ac()
    {
        return !dead && health > 0;
    }

    public boolean b_()
    {
        return false;
    }

    public void s()
    {
        if(aG > 0)
        {
            double d0 = locX + (aH - locX) / (double)aG;
            double d1 = locY + (aI - locY) / (double)aG;
            double d2 = locZ + (aJ - locZ) / (double)aG;
            double d3;
            for(d3 = aK - (double)yaw; d3 < -180D; d3 += 360D);
            for(; d3 >= 180D; d3 -= 360D);
            yaw = (float)((double)yaw + d3 / (double)aG);
            pitch = (float)((double)pitch + (aL - (double)pitch) / (double)aG);
            aG--;
            setPosition(d0, d1, d2);
            c(yaw, pitch);
            List list = world.getEntities(this, boundingBox.shrink(0.03125D, 0.0D, 0.03125D));
            if(list.size() > 0)
            {
                double d4 = 0.0D;
                for(int i = 0; i < list.size(); i++)
                {
                    AxisAlignedBB axisalignedbb = (AxisAlignedBB)list.get(i);
                    if(axisalignedbb.e > d4)
                        d4 = axisalignedbb.e;
                }

                d1 += d4 - boundingBox.b;
                setPosition(d0, d1, d2);
            }
        }
        if(H())
        {
            aS = false;
            aP = 0.0F;
            aQ = 0.0F;
            aR = 0.0F;
        } else
        if(!ai)
            c_();
        boolean flag = ao();
        boolean flag1 = ap();
        if(aS)
            if(flag)
                motY += 0.039999999105930328D;
            else
            if(flag1)
                motY += 0.039999999105930328D;
            else
            if(onGround)
                S();
        aP *= 0.98F;
        aQ *= 0.98F;
        aR *= 0.9F;
        float f = aj;
        aj *= D();
        a(aP, aQ);
        aj = f;
        List list1 = world.b(this, boundingBox.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list1 != null && list1.size() > 0)
        {
            for(int j = 0; j < list1.size(); j++)
            {
                Entity entity = (Entity)list1.get(j);
                if(entity.g())
                    entity.collide(this);
            }

        }
    }

    protected boolean H()
    {
        return health <= 0;
    }

    public boolean G()
    {
        return false;
    }

    protected void S()
    {
        motY = 0.41999998688697815D;
        if(isSprinting())
        {
            float f = yaw * 0.01745329F;
            motX -= MathHelper.sin(f) * 0.2F;
            motZ += MathHelper.cos(f) * 0.2F;
        }
        ca = true;
    }

    protected boolean d_()
    {
        return true;
    }

    protected void ad()
    {
        EntityHuman entityhuman = world.findNearbyPlayer(this, -1D);
        if(d_() && entityhuman != null)
        {
            double d0 = entityhuman.locX - locX;
            double d1 = entityhuman.locY - locY;
            double d2 = entityhuman.locZ - locZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if(d3 > 16384D)
                die();
            if(aO > 600 && random.nextInt(800) == 0)
                if(d3 < 1024D)
                    aO = 0;
                else
                    die();
        }
    }

    protected void c_()
    {
        aO++;
        EntityHuman entityhuman = world.findNearbyPlayer(this, -1D);
        ad();
        aP = 0.0F;
        aQ = 0.0F;
        float f = 8F;
        if(random.nextFloat() < 0.02F)
        {
            entityhuman = world.findNearbyPlayer(this, f);
            if(entityhuman != null)
            {
                d = entityhuman;
                aV = 10 + random.nextInt(20);
            } else
            {
                aR = (random.nextFloat() - 0.5F) * 20F;
            }
        }
        if(d != null)
        {
            a(d, 10F, u());
            if(aV-- <= 0 || d.dead || d.h(this) > (double)(f * f))
                d = null;
        } else
        {
            if(random.nextFloat() < 0.05F)
                aR = (random.nextFloat() - 0.5F) * 20F;
            yaw += aR;
            pitch = aT;
        }
        boolean flag = ao();
        boolean flag1 = ap();
        if(flag || flag1)
            aS = random.nextFloat() < 0.8F;
    }

    protected int u()
    {
        return 40;
    }

    public void a(Entity entity, float f, float f1)
    {
        double d0 = entity.locX - locX;
        double d1 = entity.locZ - locZ;
        double d2;
        if(entity instanceof EntityLiving)
        {
            EntityLiving entityliving = (EntityLiving)entity;
            d2 = (locY + (double)t()) - (entityliving.locY + (double)entityliving.t());
        } else
        {
            d2 = (entity.boundingBox.b + entity.boundingBox.e) / 2D - (locY + (double)t());
        }
        double d3 = MathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float)((Math.atan2(d1, d0) * 180D) / 3.1415927410125732D) - 90F;
        float f3 = (float)(-((Math.atan2(d2, d3) * 180D) / 3.1415927410125732D));
        pitch = -b(pitch, f3, f1);
        yaw = b(yaw, f2, f);
    }

    public boolean ae()
    {
        return d != null;
    }

    public Entity af()
    {
        return d;
    }

    private float b(float f, float f1, float f2)
    {
        float f3;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F);
        for(; f3 >= 180F; f3 -= 360F);
        if(f3 > f2)
            f3 = f2;
        if(f3 < -f2)
            f3 = -f2;
        return f + f3;
    }

    public void ag()
    {
    }

    public boolean d()
    {
        return world.containsEntity(boundingBox) && world.getEntities(this, boundingBox).size() == 0 && !world.c(boundingBox);
    }

    protected void ah()
    {
        EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(null, getBukkitEntity(), org.bukkit.event.entity.EntityDamageEvent.DamageCause.VOID, 4);
        world.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled() || event.getDamage() == 0)
        {
            return;
        } else
        {
            damageEntity(DamageSource.OUT_OF_WORLD, event.getDamage());
            return;
        }
    }

    public Vec3D ai()
    {
        return c(1.0F);
    }

    public Vec3D c(float f)
    {
        if(f == 1.0F)
        {
            float f1 = MathHelper.cos(-yaw * 0.01745329F - 3.141593F);
            float f2 = MathHelper.sin(-yaw * 0.01745329F - 3.141593F);
            float f3 = -MathHelper.cos(-pitch * 0.01745329F);
            float f4 = MathHelper.sin(-pitch * 0.01745329F);
            return Vec3D.create(f2 * f3, f4, f1 * f3);
        } else
        {
            float f1 = lastPitch + (pitch - lastPitch) * f;
            float f2 = lastYaw + (yaw - lastYaw) * f;
            float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
            float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
            float f5 = -MathHelper.cos(-f1 * 0.01745329F);
            float f6 = MathHelper.sin(-f1 * 0.01745329F);
            return Vec3D.create(f4 * f5, f6, f3 * f5);
        }
    }

    public int m()
    {
        return 4;
    }

    public boolean isSleeping()
    {
        return false;
    }

    protected void aj()
    {
        Iterator iterator = effects.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Integer integer = (Integer)iterator.next();
            MobEffect mobeffect = (MobEffect)effects.get(integer);
            if(!mobeffect.tick(this) && !world.isStatic)
            {
                iterator.remove();
                c(mobeffect);
            }
        } while(true);
    }

    public Collection getEffects()
    {
        return effects.values();
    }

    public boolean hasEffect(MobEffectList mobeffectlist)
    {
        return effects.containsKey(Integer.valueOf(mobeffectlist.id));
    }

    public MobEffect getEffect(MobEffectList mobeffectlist)
    {
        return (MobEffect)effects.get(Integer.valueOf(mobeffectlist.id));
    }

    public void addEffect(MobEffect mobeffect)
    {
        if(effects.containsKey(Integer.valueOf(mobeffect.getEffectId())))
        {
            ((MobEffect)effects.get(Integer.valueOf(mobeffect.getEffectId()))).a(mobeffect);
            b((MobEffect)effects.get(Integer.valueOf(mobeffect.getEffectId())));
        } else
        {
            effects.put(Integer.valueOf(mobeffect.getEffectId()), mobeffect);
            a(mobeffect);
        }
    }

    protected void a(MobEffect mobeffect1)
    {
    }

    protected void b(MobEffect mobeffect1)
    {
    }

    protected void c(MobEffect mobeffect1)
    {
    }

    protected float D()
    {
        float f = 1.0F;
        if(hasEffect(MobEffectList.FASTER_MOVEMENT))
            f *= 1.0F + 0.2F * (float)(getEffect(MobEffectList.FASTER_MOVEMENT).getAmplifier() + 1);
        if(hasEffect(MobEffectList.SLOWER_MOVEMENT))
            f *= 1.0F - 0.15F * (float)(getEffect(MobEffectList.SLOWER_MOVEMENT).getAmplifier() + 1);
        return f;
    }

    public int maxNoDamageTicks;
    public float S;
    public float T;
    public float U;
    public float V;
    protected float W;
    protected float X;
    protected float Y;
    protected float Z;
    protected boolean aa;
    protected String texture;
    protected boolean ac;
    protected float ad;
    protected String ae;
    protected float af;
    protected int ag;
    protected float ah;
    public boolean ai;
    public float aj;
    public float ak;
    public float al;
    public float am;
    public int health;
    public int ao;
    private int a;
    public int hurtTicks;
    public int aq;
    public float ar;
    public int deathTicks;
    public int attackTicks;
    public float au;
    public float av;
    protected boolean aw;
    protected int ax;
    public int ay;
    public float az;
    public float aA;
    public float aB;
    public float aC;
    private EntityHuman b;
    private int c;
    public int aD;
    public int aE;
    protected HashMap effects;
    protected int aG;
    protected double aH;
    protected double aI;
    protected double aJ;
    protected double aK;
    protected double aL;
    float aM;
    public int lastDamage;
    protected int aO;
    protected float aP;
    protected float aQ;
    protected float aR;
    protected boolean aS;
    protected float aT;
    protected float aU;
    private Entity d;
    protected int aV;
    public int expToDrop;
}
