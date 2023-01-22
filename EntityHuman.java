// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityHuman.java

package net.minecraft.server;

import java.util.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.*;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            EntityLiving, InventoryPlayer, FoodMetaData, PlayerAbilities, 
//            ContainerPlayer, Entity, ItemStack, EntityItem, 
//            ChunkCoordinates, NBTTagList, EntityMonster, EntityArrow, 
//            EntityCreeper, EntityGhast, EntityWolf, EntityMinecart, 
//            EntityBoat, EntityPig, World, DataWatcher, 
//            Item, EnumAnimation, Container, StatisticList, 
//            Vec3D, MobEffectList, MobEffect, MathHelper, 
//            AxisAlignedBB, Material, NBTTagCompound, DamageSource, 
//            EnumBedError, WorldProvider, BlockBed, Block, 
//            IChunkProvider, WorldData, AchievementList, EntityFishingHook, 
//            IInventory, TileEntityFurnace, TileEntityDispenser, TileEntitySign, 
//            Statistic

public abstract class EntityHuman extends EntityLiving
{

    public EntityHuman(net.minecraft.server.World world)
    {
        super(world);
        inventory = new InventoryPlayer(this);
        foodData = new FoodMetaData();
        n = 0;
        o = 0;
        p = 0;
        s = false;
        t = 0;
        w = 0;
        spawnWorld = "";
        H = 20;
        I = false;
        abilities = new PlayerAbilities();
        O = 0.1F;
        P = 0.02F;
        f = 0;
        hookedFish = null;
        defaultContainer = new ContainerPlayer(inventory, !world.isStatic);
        activeContainer = defaultContainer;
        height = 1.62F;
        ChunkCoordinates chunkcoordinates = world.getSpawn();
        setPositionRotation((double)chunkcoordinates.x + 0.5D, chunkcoordinates.y + 1, (double)chunkcoordinates.z + 0.5D, 0.0F, 0.0F);
        health = 20;
        ae = "humanoid";
        ad = 180F;
        maxFireTicks = 20;
        texture = "/mob/char.png";
    }

    protected void b()
    {
        super.b();
        datawatcher.a(16, Byte.valueOf((byte)0));
        datawatcher.a(17, Byte.valueOf((byte)0));
    }

    public boolean o_()
    {
        return d != null;
    }

    public void E()
    {
        if(d != null)
            d.a(world, this, e);
        F();
    }

    public void F()
    {
        d = null;
        e = 0;
        if(!world.isStatic)
            h(false);
    }

    public boolean G()
    {
        return o_() && Item.byId[d.id].b(d) == EnumAnimation.c;
    }

    public void s_()
    {
        if(d != null)
        {
            net.minecraft.server.ItemStack itemstack = inventory.getItemInHand();
            if(itemstack != d)
            {
                F();
            } else
            {
                if(e <= 25 && e % 4 == 0)
                    b(itemstack, 5);
                if(--e == 0 && !world.isStatic)
                    C();
            }
        }
        if(w > 0)
            w--;
        if(isSleeping())
        {
            sleepTicks++;
            if(sleepTicks > 100)
                sleepTicks = 100;
            if(!world.isStatic)
                if(!w())
                    a(true, true, false);
                else
                if(world.d())
                    a(false, true, true);
        } else
        if(sleepTicks > 0)
        {
            sleepTicks++;
            if(sleepTicks >= 110)
                sleepTicks = 0;
        }
        super.s_();
        if(!world.isStatic && activeContainer != null && !activeContainer.b(this))
        {
            closeInventory();
            activeContainer = defaultContainer;
        }
        if(abilities.isFlying)
        {
            for(int i = 0; i < 8; i++);
        }
        if(fireTicks > 0 && abilities.isInvulnerable)
            fireTicks = 0;
        x = A;
        y = B;
        z = C;
        double d0 = locX - A;
        double d1 = locY - B;
        double d2 = locZ - C;
        double d3 = 10D;
        if(d0 > d3)
            x = A = locX;
        if(d2 > d3)
            z = C = locZ;
        if(d1 > d3)
            y = B = locY;
        if(d0 < -d3)
            x = A = locX;
        if(d2 < -d3)
            z = C = locZ;
        if(d1 < -d3)
            y = B = locY;
        A += d0 * 0.25D;
        C += d2 * 0.25D;
        B += d1 * 0.25D;
        a(StatisticList.k, 1);
        if(vehicle == null)
            c = null;
        if(!world.isStatic)
            foodData.a(this);
    }

    protected void b(net.minecraft.server.ItemStack itemstack, int i)
    {
        if(itemstack.m() == EnumAnimation.b)
        {
            for(int j = 0; j < i; j++)
            {
                Vec3D vec3d = Vec3D.create(((double)random.nextFloat() - 0.5D) * 0.10000000000000001D, Math.random() * 0.10000000000000001D + 0.10000000000000001D, 0.0D);
                vec3d.a((-pitch * 3.141593F) / 180F);
                vec3d.b((-yaw * 3.141593F) / 180F);
                Vec3D vec3d1 = Vec3D.create(((double)random.nextFloat() - 0.5D) * 0.29999999999999999D, (double)(-random.nextFloat()) * 0.59999999999999998D - 0.29999999999999999D, 0.59999999999999998D);
                vec3d1.a((-pitch * 3.141593F) / 180F);
                vec3d1.b((-yaw * 3.141593F) / 180F);
                vec3d1 = vec3d1.add(locX, locY + (double)t(), locZ);
                world.a((new StringBuilder()).append("iconcrack_").append(itemstack.getItem().id).toString(), vec3d1.a, vec3d1.b, vec3d1.c, vec3d.a, vec3d.b + 0.050000000000000003D, vec3d.c);
            }

            world.makeSound(this, "mob.eat", 0.5F + 0.5F * (float)random.nextInt(2), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void C()
    {
        if(d != null)
        {
            b(d, 16);
            int i = d.count;
            net.minecraft.server.ItemStack itemstack = d.b(world, this);
            if(itemstack != d || itemstack != null && itemstack.count != i)
            {
                inventory.items[inventory.itemInHandIndex] = itemstack;
                if(itemstack.count == 0)
                    inventory.items[inventory.itemInHandIndex] = null;
            }
            F();
        }
    }

    protected boolean H()
    {
        return health <= 0 || isSleeping();
    }

    protected void closeInventory()
    {
        activeContainer = defaultContainer;
    }

    public void I()
    {
        double d0 = locX;
        double d1 = locY;
        double d2 = locZ;
        super.I();
        q = r;
        r = 0.0F;
        h(locX - d0, locY - d1, locZ - d2);
    }

    private int o()
    {
        return hasEffect(MobEffectList.FASTER_DIG) ? 6 - (1 + getEffect(MobEffectList.FASTER_DIG).getAmplifier()) * 1 : hasEffect(MobEffectList.SLOWER_DIG) ? 6 + (1 + getEffect(MobEffectList.SLOWER_DIG).getAmplifier()) * 2 : 6;
    }

    protected void c_()
    {
        int i = o();
        if(s)
        {
            t++;
            if(t >= i)
            {
                t = 0;
                s = false;
            }
        } else
        {
            t = 0;
        }
        am = (float)t / (float)i;
    }

    public void s()
    {
        if(n > 0)
            n--;
        if(world.difficulty == 0 && health < 20 && (ticksLived % 20) * 12 == 0)
            c(1, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.REGEN);
        inventory.h();
        q = r;
        super.s();
        aj = O;
        ak = P;
        if(isSprinting())
        {
            aj = (float)((double)aj + (double)O * 0.29999999999999999D);
            ak = (float)((double)ak + (double)P * 0.29999999999999999D);
        }
        float f = MathHelper.a(motX * motX + motZ * motZ);
        float f1 = (float)TrigMath.atan(-motY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
            f = 0.1F;
        if(!onGround || health <= 0)
            f = 0.0F;
        if(onGround || health <= 0)
            f1 = 0.0F;
        r += (f - r) * 0.4F;
        av += (f1 - av) * 0.8F;
        if(health > 0)
        {
            List list = world.b(this, boundingBox.b(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    Entity entity = (Entity)list.get(i);
                    if(!entity.dead)
                        j(entity);
                }

            }
        }
    }

    private void j(Entity entity)
    {
        entity.a_(this);
    }

    public void die(DamageSource damagesource)
    {
        super.die(damagesource);
        b(0.2F, 0.2F);
        setPosition(locX, locY, locZ);
        motY = 0.10000000149011612D;
        if(name.equals("Notch"))
            a(new net.minecraft.server.ItemStack(Item.APPLE, 1), true);
        inventory.j();
        if(damagesource != null)
        {
            motX = -MathHelper.cos(((ar + yaw) * 3.141593F) / 180F) * 0.1F;
            motZ = -MathHelper.sin(((ar + yaw) * 3.141593F) / 180F) * 0.1F;
        } else
        {
            motX = motZ = 0.0D;
        }
        height = 0.1F;
        a(StatisticList.y, 1);
    }

    public void b(Entity entity, int i)
    {
        p += i;
        if(entity instanceof EntityHuman)
            a(StatisticList.A, 1);
        else
            a(StatisticList.z, 1);
    }

    public void J()
    {
        a(inventory.splitStack(inventory.itemInHandIndex, 1), false);
    }

    public void b(net.minecraft.server.ItemStack itemstack)
    {
        a(itemstack, false);
    }

    public void a(net.minecraft.server.ItemStack itemstack, boolean flag)
    {
        if(itemstack != null)
        {
            EntityItem entityitem = new EntityItem(world, locX, (locY - 0.30000001192092896D) + (double)t(), locZ, itemstack);
            entityitem.pickupDelay = 40;
            float f = 0.1F;
            if(flag)
            {
                float f1 = random.nextFloat() * 0.5F;
                float f2 = random.nextFloat() * 3.141593F * 2.0F;
                entityitem.motX = -MathHelper.sin(f2) * f1;
                entityitem.motZ = MathHelper.cos(f2) * f1;
                entityitem.motY = 0.20000000298023224D;
            } else
            {
                f = 0.3F;
                entityitem.motX = -MathHelper.sin((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F) * f;
                entityitem.motZ = MathHelper.cos((yaw / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F) * f;
                entityitem.motY = -MathHelper.sin((pitch / 180F) * 3.141593F) * f + 0.1F;
                f = 0.02F;
                float f1 = random.nextFloat() * 3.141593F * 2.0F;
                f *= random.nextFloat();
                entityitem.motX += Math.cos(f1) * (double)f;
                entityitem.motY += (random.nextFloat() - random.nextFloat()) * 0.1F;
                entityitem.motZ += Math.sin(f1) * (double)f;
            }
            Player player = (Player)getBukkitEntity();
            CraftItem drop = new CraftItem(world.getServer(), entityitem);
            PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
            {
                player.getInventory().addItem(new ItemStack[] {
                    drop.getItemStack()
                });
                return;
            }
            a(entityitem);
            a(StatisticList.v, 1);
        }
    }

    protected void a(EntityItem entityitem)
    {
        world.addEntity(entityitem);
    }

    public float a(Block block)
    {
        float f = inventory.a(block);
        if(a(Material.WATER))
            f /= 5F;
        if(!onGround)
            f /= 5F;
        if(hasEffect(MobEffectList.FASTER_DIG))
            f *= 1.0F + (float)(getEffect(MobEffectList.FASTER_DIG).getAmplifier() + 1) * 0.2F;
        if(hasEffect(MobEffectList.SLOWER_DIG))
            f *= 1.0F - (float)(getEffect(MobEffectList.SLOWER_DIG).getAmplifier() + 1) * 0.2F;
        return f;
    }

    public boolean b(Block block)
    {
        return inventory.b(block);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.l("Inventory");
        inventory.b(nbttaglist);
        dimension = nbttagcompound.e("Dimension");
        sleeping = nbttagcompound.m("Sleeping");
        sleepTicks = nbttagcompound.d("SleepTimer");
        exp = nbttagcompound.e("Xp");
        expLevel = nbttagcompound.e("XpLevel");
        expTotal = nbttagcompound.e("XpTotal");
        if(sleeping)
        {
            E = new ChunkCoordinates(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ));
            a(true, true, false);
        }
        spawnWorld = nbttagcompound.getString("SpawnWorld");
        if("".equals(spawnWorld))
            spawnWorld = ((World)world.getServer().getWorlds().get(0)).getName();
        if(nbttagcompound.hasKey("SpawnX") && nbttagcompound.hasKey("SpawnY") && nbttagcompound.hasKey("SpawnZ"))
            b = new ChunkCoordinates(nbttagcompound.e("SpawnX"), nbttagcompound.e("SpawnY"), nbttagcompound.e("SpawnZ"));
        foodData.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.a("Inventory", inventory.a(new NBTTagList()));
        nbttagcompound.a("Dimension", dimension);
        nbttagcompound.a("Sleeping", sleeping);
        nbttagcompound.a("SleepTimer", (short)sleepTicks);
        nbttagcompound.a("Xp", exp);
        nbttagcompound.a("XpLevel", expLevel);
        nbttagcompound.a("XpTotal", expTotal);
        if(b != null)
        {
            nbttagcompound.a("SpawnX", b.x);
            nbttagcompound.a("SpawnY", b.y);
            nbttagcompound.a("SpawnZ", b.z);
            nbttagcompound.setString("SpawnWorld", spawnWorld);
        }
        foodData.b(nbttagcompound);
    }

    public void a(IInventory iinventory1)
    {
    }

    public void b(int l, int i1, int j1)
    {
    }

    public void receive(Entity entity1, int k)
    {
    }

    public float t()
    {
        return 0.12F;
    }

    protected void m_()
    {
        height = 1.62F;
    }

    public boolean damageEntity(DamageSource damagesource, int i)
    {
        if(abilities.isInvulnerable && !damagesource.ignoresInvulnerability())
            return false;
        aO = 0;
        if(health <= 0)
            return false;
        if(isSleeping() && !world.isStatic)
            a(true, true, false);
        Entity entity = damagesource.getEntity();
        if((entity instanceof EntityMonster) || (entity instanceof EntityArrow))
        {
            if(world.difficulty == 0)
                i = 0;
            if(world.difficulty == 1)
                i = i / 3 + 1;
            if(world.difficulty == 3)
                i = (i * 3) / 2;
        }
        if(i == 0)
            return false;
        Entity entity1 = entity;
        if((entity instanceof EntityArrow) && ((EntityArrow)entity).shooter != null)
            entity1 = ((EntityArrow)entity).shooter;
        if(entity1 instanceof EntityLiving)
        {
            if(!(entity.getBukkitEntity() instanceof Projectile))
            {
                org.bukkit.entity.Entity damager = entity1.getBukkitEntity();
                org.bukkit.entity.Entity damagee = getBukkitEntity();
                EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK, i);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled() || event.getDamage() == 0)
                    return false;
                i = event.getDamage();
            }
            a((EntityLiving)entity1, false);
        }
        a(StatisticList.x, i);
        return super.damageEntity(damagesource, i);
    }

    protected boolean n_()
    {
        return false;
    }

    protected void a(EntityLiving entityliving, boolean flag)
    {
        if(!(entityliving instanceof EntityCreeper) && !(entityliving instanceof EntityGhast))
        {
            if(entityliving instanceof EntityWolf)
            {
                EntityWolf entitywolf = (EntityWolf)entityliving;
                if(entitywolf.isTamed() && name.equals(entitywolf.getOwnerName()))
                    return;
            }
            if(!(entityliving instanceof EntityHuman) || n_())
            {
                List list = world.a(net/minecraft/server/EntityWolf, AxisAlignedBB.b(locX, locY, locZ, locX + 1.0D, locY + 1.0D, locZ + 1.0D).b(16D, 4D, 16D));
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Entity entity = (Entity)iterator.next();
                    EntityWolf entitywolf1 = (EntityWolf)entity;
                    if(entitywolf1.isTamed() && entitywolf1.C() == null && name.equals(entitywolf1.getOwnerName()) && (!flag || !entitywolf1.isSitting()))
                    {
                        org.bukkit.entity.Entity bukkitTarget = entity != null ? entityliving.getBukkitEntity() : null;
                        EntityTargetEvent event;
                        if(flag)
                            event = new EntityTargetEvent(entitywolf1.getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET);
                        else
                            event = new EntityTargetEvent(entitywolf1.getBukkitEntity(), bukkitTarget, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER);
                        world.getServer().getPluginManager().callEvent(event);
                        if(!event.isCancelled())
                        {
                            entitywolf1.setSitting(false);
                            entitywolf1.setTarget(entityliving);
                        }
                    }
                } while(true);
            }
        }
    }

    protected void b(DamageSource damagesource, int i)
    {
        if(!damagesource.ignoresArmor() && G())
            i = 1 + i >> 1;
        if(!damagesource.ignoresArmor())
        {
            int j = 25 - inventory.i();
            int k = i * j + f;
            inventory.d(i);
            i = k / 25;
            f = k % 25;
        }
        b(damagesource.c());
        super.b(damagesource, i);
    }

    public void a(TileEntityFurnace tileentityfurnace1)
    {
    }

    public void a(TileEntityDispenser tileentitydispenser1)
    {
    }

    public void a(TileEntitySign tileentitysign1)
    {
    }

    public void c(Entity entity)
    {
        if(!entity.b(this))
        {
            net.minecraft.server.ItemStack itemstack = K();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.a((EntityLiving)entity);
                if(itemstack.count == 0)
                {
                    itemstack.a(this);
                    L();
                }
            }
        }
    }

    public net.minecraft.server.ItemStack K()
    {
        return inventory.getItemInHand();
    }

    public void L()
    {
        inventory.setItem(inventory.itemInHandIndex, (net.minecraft.server.ItemStack)null);
    }

    public double M()
    {
        return (double)(height - 0.5F);
    }

    public void v()
    {
        if(!s || t >= o() / 2 || t < 0)
        {
            t = -1;
            s = true;
        }
    }

    public void d(Entity entity)
    {
        int i = inventory.a(entity);
        if(i > 0)
        {
            boolean flag = motY < 0.0D && !onGround && !p() && !ao();
            if(flag)
                i = (i * 3) / 2 + 1;
            if((entity instanceof EntityLiving) && !(entity instanceof EntityHuman))
            {
                org.bukkit.entity.Entity damager = getBukkitEntity();
                org.bukkit.entity.Entity damagee = entity != null ? entity.getBukkitEntity() : null;
                EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK, i);
                world.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled() || event.getDamage() == 0)
                    return;
                i = event.getDamage();
            }
            boolean flag1 = entity.damageEntity(DamageSource.playerAttack(this), i);
            if(!flag1)
                return;
            if(flag1)
            {
                if(isSprinting())
                {
                    entity.b(-MathHelper.sin((yaw * 3.141593F) / 180F) * 1.0F, 0.10000000000000001D, MathHelper.cos((yaw * 3.141593F) / 180F) * 1.0F);
                    motX *= 0.59999999999999998D;
                    motZ *= 0.59999999999999998D;
                    setSprinting(false);
                }
                if(flag)
                    e(entity);
            }
            net.minecraft.server.ItemStack itemstack = K();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.a((EntityLiving)entity, this);
                if(itemstack.count == 0)
                {
                    itemstack.a(this);
                    L();
                }
            }
            if(entity instanceof EntityLiving)
            {
                if(entity.ac())
                    a((EntityLiving)entity, true);
                a(StatisticList.w, i);
            }
            b(0.3F);
        }
    }

    public void e(Entity entity1)
    {
    }

    public void a(net.minecraft.server.ItemStack itemstack1)
    {
    }

    public void die()
    {
        super.die();
        defaultContainer.a(this);
        if(activeContainer != null)
            activeContainer.a(this);
    }

    public boolean O()
    {
        return !sleeping && super.O();
    }

    public EnumBedError a(int i, int j, int k)
    {
        if(!world.isStatic)
        {
            if(isSleeping() || !ac())
                return EnumBedError.OTHER_PROBLEM;
            if(world.worldProvider.c)
                return EnumBedError.NOT_POSSIBLE_HERE;
            if(world.d())
                return EnumBedError.NOT_POSSIBLE_NOW;
            if(Math.abs(locX - (double)i) > 3D || Math.abs(locY - (double)j) > 2D || Math.abs(locZ - (double)k) > 3D)
                return EnumBedError.TOO_FAR_AWAY;
        }
        if(getBukkitEntity() instanceof Player)
        {
            Player player = (Player)getBukkitEntity();
            org.bukkit.block.Block bed = world.getWorld().getBlockAt(i, j, k);
            PlayerBedEnterEvent event = new PlayerBedEnterEvent(player, bed);
            world.getServer().getPluginManager().callEvent(event);
            if(event.isCancelled())
                return EnumBedError.OTHER_PROBLEM;
        }
        b(0.2F, 0.2F);
        height = 0.2F;
        if(world.isLoaded(i, j, k))
        {
            int l = world.getData(i, j, k);
            int i1 = BlockBed.c(l);
            float f = 0.5F;
            float f1 = 0.5F;
            switch(i1)
            {
            case 0: // '\0'
                f1 = 0.9F;
                break;

            case 1: // '\001'
                f = 0.1F;
                break;

            case 2: // '\002'
                f1 = 0.1F;
                break;

            case 3: // '\003'
                f = 0.9F;
                break;
            }
            b(i1);
            setPosition((float)i + f, (float)j + 0.9375F, (float)k + f1);
        } else
        {
            setPosition((float)i + 0.5F, (float)j + 0.9375F, (float)k + 0.5F);
        }
        sleeping = true;
        sleepTicks = 0;
        E = new ChunkCoordinates(i, j, k);
        motX = motZ = motY = 0.0D;
        if(!world.isStatic)
            world.everyoneSleeping();
        return EnumBedError.OK;
    }

    private void b(int i)
    {
        F = 0.0F;
        G = 0.0F;
        switch(i)
        {
        case 0: // '\0'
            G = -1.8F;
            break;

        case 1: // '\001'
            F = 1.8F;
            break;

        case 2: // '\002'
            G = 1.8F;
            break;

        case 3: // '\003'
            F = -1.8F;
            break;
        }
    }

    public void a(boolean flag, boolean flag1, boolean flag2)
    {
        if(fauxSleeping && !sleeping)
            return;
        b(0.6F, 1.8F);
        m_();
        ChunkCoordinates chunkcoordinates = E;
        ChunkCoordinates chunkcoordinates1 = E;
        if(chunkcoordinates != null && world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) == Block.BED.id)
        {
            BlockBed.a(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, false);
            chunkcoordinates1 = BlockBed.f(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
            if(chunkcoordinates1 == null)
                chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.x, chunkcoordinates.y + 1, chunkcoordinates.z);
            setPosition((float)chunkcoordinates1.x + 0.5F, (float)chunkcoordinates1.y + height + 0.1F, (float)chunkcoordinates1.z + 0.5F);
        }
        sleeping = false;
        if(!world.isStatic && flag1)
            world.everyoneSleeping();
        if(getBukkitEntity() instanceof Player)
        {
            Player player = (Player)getBukkitEntity();
            org.bukkit.block.Block bed;
            if(chunkcoordinates != null)
                bed = world.getWorld().getBlockAt(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
            else
                bed = world.getWorld().getBlockAt(player.getLocation());
            PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed);
            world.getServer().getPluginManager().callEvent(event);
        }
        if(flag)
            sleepTicks = 0;
        else
            sleepTicks = 100;
        if(flag2)
            a(E);
    }

    private boolean w()
    {
        return world.getTypeId(E.x, E.y, E.z) == Block.BED.id;
    }

    public static ChunkCoordinates getBed(net.minecraft.server.World world, ChunkCoordinates chunkcoordinates)
    {
        IChunkProvider ichunkprovider = world.n();
        ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z - 3 >> 4);
        ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z - 3 >> 4);
        ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z + 3 >> 4);
        ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z + 3 >> 4);
        if(world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) != Block.BED.id)
        {
            return null;
        } else
        {
            ChunkCoordinates chunkcoordinates1 = BlockBed.f(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
            return chunkcoordinates1;
        }
    }

    public boolean isSleeping()
    {
        return sleeping;
    }

    public boolean isDeeplySleeping()
    {
        return sleeping && sleepTicks >= 100;
    }

    public void a(String s1)
    {
    }

    public ChunkCoordinates getBed()
    {
        return b;
    }

    public void a(ChunkCoordinates chunkcoordinates)
    {
        if(chunkcoordinates != null)
        {
            b = new ChunkCoordinates(chunkcoordinates);
            spawnWorld = world.worldData.name;
        } else
        {
            b = null;
        }
    }

    public void a(Statistic statistic)
    {
        a(statistic, 1);
    }

    public void a(Statistic statistic1, int k)
    {
    }

    protected void S()
    {
        super.S();
        a(StatisticList.u, 1);
        if(isSprinting())
            b(0.8F);
        else
            b(0.2F);
    }

    public void a(float f, float f1)
    {
        double d0 = locX;
        double d1 = locY;
        double d2 = locZ;
        if(abilities.isFlying)
        {
            double d3 = motY;
            float f2 = ak;
            ak = 0.05F;
            super.a(f, f1);
            motY = d3 * 0.59999999999999998D;
            ak = f2;
        } else
        {
            super.a(f, f1);
        }
        a(locX - d0, locY - d1, locZ - d2);
    }

    public void a(double d0, double d1, double d2)
    {
        if(vehicle == null)
            if(a(Material.WATER))
            {
                int i = Math.round(MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100F);
                if(i > 0)
                {
                    a(StatisticList.q, i);
                    b(0.015F * (float)i * 0.01F);
                }
            } else
            if(ao())
            {
                int i = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100F);
                if(i > 0)
                {
                    a(StatisticList.m, i);
                    b(0.015F * (float)i * 0.01F);
                }
            } else
            if(p())
            {
                if(d1 > 0.0D)
                    a(StatisticList.o, (int)Math.round(d1 * 100D));
            } else
            if(onGround)
            {
                int i = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100F);
                if(i > 0)
                {
                    a(StatisticList.l, i);
                    if(isSprinting())
                        b(0.09999999F * (float)i * 0.01F);
                    else
                        b(0.01F * (float)i * 0.01F);
                }
            } else
            {
                int i = Math.round(MathHelper.a(d0 * d0 + d2 * d2) * 100F);
                if(i > 25)
                    a(StatisticList.p, i);
            }
    }

    private void h(double d0, double d1, double d2)
    {
        if(vehicle != null)
        {
            int i = Math.round(MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100F);
            if(i > 0)
                if(vehicle instanceof EntityMinecart)
                {
                    a(StatisticList.r, i);
                    if(c == null)
                        c = new ChunkCoordinates(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ));
                    else
                    if(c.a(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ)) >= 1000D)
                        a(AchievementList.q, 1);
                } else
                if(vehicle instanceof EntityBoat)
                    a(StatisticList.s, i);
                else
                if(vehicle instanceof EntityPig)
                    a(StatisticList.t, i);
        }
    }

    protected void a(float f)
    {
        if(!abilities.canFly)
        {
            if(f >= 2.0F)
                a(StatisticList.n, (int)Math.round((double)f * 100D));
            super.a(f);
        }
    }

    public void a(EntityLiving entityliving)
    {
        if(entityliving instanceof EntityMonster)
            a(((Statistic) (AchievementList.s)));
    }

    public void T()
    {
        if(H > 0)
            H = 10;
        else
            I = true;
    }

    public void d(int i)
    {
        exp += i;
        expTotal += i;
        for(; exp >= U(); y())
            exp -= U();

    }

    public int U()
    {
        return (expLevel + 1) * 10;
    }

    private void y()
    {
        expLevel++;
    }

    public void b(float f)
    {
        if(!abilities.isInvulnerable && !world.isStatic)
            foodData.a(f);
    }

    public FoodMetaData getFoodData()
    {
        return foodData;
    }

    public boolean c(boolean flag)
    {
        return (flag || foodData.b()) && !abilities.isInvulnerable;
    }

    public boolean W()
    {
        return health > 0 && health < 20;
    }

    public void a(net.minecraft.server.ItemStack itemstack, int i)
    {
        if(itemstack != d)
        {
            d = itemstack;
            e = i;
            if(!world.isStatic)
                h(true);
        }
    }

    public boolean c(int i, int j, int k)
    {
        return true;
    }

    protected int a(EntityHuman entityhuman)
    {
        return expTotal >> 1;
    }

    protected boolean X()
    {
        return true;
    }

    public String Y()
    {
        return name;
    }

    public InventoryPlayer inventory;
    public Container defaultContainer;
    public Container activeContainer;
    protected FoodMetaData foodData;
    protected int n;
    public byte o;
    public int p;
    public float q;
    public float r;
    public boolean s;
    public int t;
    public String name;
    public int dimension;
    public int w;
    public double x;
    public double y;
    public double z;
    public double A;
    public double B;
    public double C;
    public boolean sleeping;
    public boolean fauxSleeping;
    public String spawnWorld;
    public int sleepTicks;
    public ChunkCoordinates E;
    public float F;
    public float G;
    private ChunkCoordinates b;
    private ChunkCoordinates c;
    public int H;
    protected boolean I;
    public float J;
    public PlayerAbilities abilities;
    public int exp;
    public int expLevel;
    public int expTotal;
    private net.minecraft.server.ItemStack d;
    private int e;
    protected float O;
    protected float P;
    private int f;
    public EntityFishingHook hookedFish;
}
