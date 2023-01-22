// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityItem.java

package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.PluginManager;

// Referenced classes of package net.minecraft.server:
//            Entity, NBTTagCompound, ItemStack, MathHelper, 
//            World, Material, AxisAlignedBB, Block, 
//            DamageSource, EntityHuman, InventoryPlayer, AchievementList, 
//            Item, StatisticCollector

public class EntityItem extends Entity
{

    public EntityItem(World world, double d0, double d1, double d2, 
            ItemStack itemstack)
    {
        super(world);
        b = 0;
        f = 5;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        lastTick = (int)(System.currentTimeMillis() / 50L);
        b(0.25F, 0.25F);
        height = width / 2.0F;
        setPosition(d0, d1, d2);
        itemStack = itemstack;
        if(itemStack.count <= -1)
            itemStack.count = 1;
        yaw = (float)(Math.random() * 360D);
        motX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
        motY = 0.20000000298023224D;
        motZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
    }

    protected boolean e_()
    {
        return false;
    }

    public EntityItem(World world)
    {
        super(world);
        b = 0;
        f = 5;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        lastTick = (int)(System.currentTimeMillis() / 50L);
        b(0.25F, 0.25F);
        height = width / 2.0F;
    }

    protected void b()
    {
    }

    public void s_()
    {
        super.s_();
        int currentTick = (int)(System.currentTimeMillis() / 50L);
        pickupDelay -= currentTick - lastTick;
        lastTick = currentTick;
        lastX = locX;
        lastY = locY;
        lastZ = locZ;
        motY -= 0.039999999105930328D;
        if(world.getMaterial(MathHelper.floor(locX), MathHelper.floor(locY), MathHelper.floor(locZ)) == Material.LAVA)
        {
            motY = 0.20000000298023224D;
            motX = (random.nextFloat() - random.nextFloat()) * 0.2F;
            motZ = (random.nextFloat() - random.nextFloat()) * 0.2F;
            world.makeSound(this, "random.fizz", 0.4F, 2.0F + random.nextFloat() * 0.4F);
        }
        g(locX, (boundingBox.b + boundingBox.e) / 2D, locZ);
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
            motY *= -0.5D;
        e++;
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
        f -= i;
        if(f <= 0)
            die();
        return false;
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.a("Health", (byte)f);
        nbttagcompound.a("Age", (short)b);
        nbttagcompound.a("Item", itemStack.b(new NBTTagCompound()));
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        f = nbttagcompound.d("Health") & 0xff;
        b = nbttagcompound.d("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.k("Item");
        itemStack = ItemStack.a(nbttagcompound1);
        if(itemStack == null)
            die();
    }

    public void a_(EntityHuman entityhuman)
    {
        if(!world.isStatic)
        {
            int i = itemStack.count;
            int canHold = entityhuman.inventory.canHold(itemStack);
            int remaining = itemStack.count - canHold;
            if(pickupDelay <= 0 && canHold > 0)
            {
                itemStack.count = canHold;
                PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
                world.getServer().getPluginManager().callEvent(event);
                itemStack.count = canHold + remaining;
                if(event.isCancelled())
                    return;
                pickupDelay = 0;
            }
            if(pickupDelay == 0 && entityhuman.inventory.pickup(itemStack))
            {
                if(itemStack.id == Block.LOG.id)
                    entityhuman.a(AchievementList.g);
                if(itemStack.id == Item.LEATHER.id)
                    entityhuman.a(AchievementList.t);
                world.makeSound(this, "random.pop", 0.2F, ((random.nextFloat() - random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.receive(this, i);
                if(itemStack.count <= 0)
                    die();
            }
        }
    }

    public String Y()
    {
        return StatisticCollector.a((new StringBuilder()).append("item.").append(itemStack.k()).toString());
    }

    public ItemStack itemStack;
    private int e;
    public int b;
    public int pickupDelay;
    private int f;
    public float d;
    private int lastTick;
}
