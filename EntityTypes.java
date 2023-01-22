// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityTypes.java

package net.minecraft.server;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.server:
//            World, Entity, EntityItem, EntityExperienceOrb, 
//            EntityArrow, EntitySnowball, EntityPainting, EntityLiving, 
//            EntityMonster, EntityCreeper, EntitySkeleton, EntitySpider, 
//            EntityGiantZombie, EntityZombie, EntitySlime, EntityGhast, 
//            EntityPigZombie, EntityEnderman, EntityCaveSpider, EntitySilverfish, 
//            EntityPig, EntitySheep, EntityCow, EntityChicken, 
//            EntitySquid, EntityWolf, EntityTNTPrimed, EntityFallingSand, 
//            EntityMinecart, EntityBoat, NBTTagCompound

public class EntityTypes
{

    public EntityTypes()
    {
    }

    private static void a(Class oclass, String s, int i)
    {
        a.put(s, oclass);
        b.put(oclass, s);
        c.put(Integer.valueOf(i), oclass);
        d.put(oclass, Integer.valueOf(i));
    }

    public static Entity a(String s, World world)
    {
        Entity entity = null;
        try
        {
            Class oclass = (Class)a.get(s);
            if(oclass != null)
                entity = (Entity)oclass.getConstructor(new Class[] {
                    net/minecraft/server/World
                }).newInstance(new Object[] {
                    world
                });
        }
        catch(Exception exception) { }
        return entity;
    }

    public static Entity a(NBTTagCompound nbttagcompound, World world)
    {
        Entity entity = null;
        try
        {
            Class oclass = (Class)a.get(nbttagcompound.getString("id"));
            if(oclass != null)
                entity = (Entity)oclass.getConstructor(new Class[] {
                    net/minecraft/server/World
                }).newInstance(new Object[] {
                    world
                });
        }
        catch(Exception exception) { }
        if(entity != null)
            entity.e(nbttagcompound);
        else
            System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(nbttagcompound.getString("id")).toString());
        return entity;
    }

    public static int a(Entity entity)
    {
        return ((Integer)d.get(entity.getClass())).intValue();
    }

    public static String b(Entity entity)
    {
        return (String)b.get(entity.getClass());
    }

    public static Class getClassFromId(int id)
    {
        return (Class)c.get(Integer.valueOf(id));
    }

    public static int getIdFromClass(Class oClass)
    {
        return ((Integer)d.get(oClass)).intValue();
    }

    public static Class getClassFromName(String name)
    {
        return (Class)a.get(name);
    }

    public static String getNameFromClass(Class oClass)
    {
        return (String)b.get(oClass);
    }

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();

    static 
    {
        a(net/minecraft/server/EntityItem, "Item", 1);
        a(net/minecraft/server/EntityExperienceOrb, "XPOrb", 2);
        a(net/minecraft/server/EntityArrow, "Arrow", 10);
        a(net/minecraft/server/EntitySnowball, "Snowball", 11);
        a(net/minecraft/server/EntityPainting, "Painting", 9);
        a(net/minecraft/server/EntityLiving, "Mob", 48);
        a(net/minecraft/server/EntityMonster, "Monster", 49);
        a(net/minecraft/server/EntityCreeper, "Creeper", 50);
        a(net/minecraft/server/EntitySkeleton, "Skeleton", 51);
        a(net/minecraft/server/EntitySpider, "Spider", 52);
        a(net/minecraft/server/EntityGiantZombie, "Giant", 53);
        a(net/minecraft/server/EntityZombie, "Zombie", 54);
        a(net/minecraft/server/EntitySlime, "Slime", 55);
        a(net/minecraft/server/EntityGhast, "Ghast", 56);
        a(net/minecraft/server/EntityPigZombie, "PigZombie", 57);
        a(net/minecraft/server/EntityEnderman, "Enderman", 58);
        a(net/minecraft/server/EntityCaveSpider, "CaveSpider", 59);
        a(net/minecraft/server/EntitySilverfish, "Silverfish", 60);
        a(net/minecraft/server/EntityPig, "Pig", 90);
        a(net/minecraft/server/EntitySheep, "Sheep", 91);
        a(net/minecraft/server/EntityCow, "Cow", 92);
        a(net/minecraft/server/EntityChicken, "Chicken", 93);
        a(net/minecraft/server/EntitySquid, "Squid", 94);
        a(net/minecraft/server/EntityWolf, "Wolf", 95);
        a(net/minecraft/server/EntityTNTPrimed, "PrimedTnt", 20);
        a(net/minecraft/server/EntityFallingSand, "FallingSand", 21);
        a(net/minecraft/server/EntityMinecart, "Minecart", 40);
        a(net/minecraft/server/EntityBoat, "Boat", 41);
    }
}
