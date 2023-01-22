// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            IMonster, Material, EntityAnimal, EntityWaterAnimal

public final class EnumCreatureType extends Enum
{

    public static EnumCreatureType[] values()
    {
        return (EnumCreatureType[])h.clone();
    }

    public static EnumCreatureType valueOf(String s)
    {
        return (EnumCreatureType)Enum.valueOf(net/minecraft/server/EnumCreatureType, s);
    }

    private EnumCreatureType(String s, int i, Class class1, int j, Material material, boolean flag)
    {
        super(s, i);
        d = class1;
        e = j;
        f = material;
        g = flag;
    }

    public Class a()
    {
        return d;
    }

    public int b()
    {
        return e;
    }

    public Material c()
    {
        return f;
    }

    public boolean d()
    {
        return g;
    }

    public static final EnumCreatureType MONSTER;
    public static final EnumCreatureType CREATURE;
    public static final EnumCreatureType WATER_CREATURE;
    private final Class d;
    private final int e;
    private final Material f;
    private final boolean g;
    private static final EnumCreatureType h[];

    static 
    {
        MONSTER = new EnumCreatureType("monster", 0, net/minecraft/server/IMonster, 70, Material.AIR, false);
        CREATURE = new EnumCreatureType("creature", 1, net/minecraft/server/EntityAnimal, 15, Material.AIR, true);
        WATER_CREATURE = new EnumCreatureType("waterCreature", 2, net/minecraft/server/EntityWaterAnimal, 5, Material.WATER, true);
        h = (new EnumCreatureType[] {
            MONSTER, CREATURE, WATER_CREATURE
        });
    }
}
