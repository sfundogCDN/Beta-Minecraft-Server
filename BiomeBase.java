// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            Block, BlockGrass, WorldGenTrees, WorldGenBigTree, 
//            WorldGenForest, WorldGenSwampTree, BiomeMeta, EntitySheep, 
//            EntityPig, EntityChicken, EntityCow, EntitySpider, 
//            EntityZombie, EntitySkeleton, EntityCreeper, EntitySlime, 
//            EntityEnderman, EntitySquid, BiomeDecorator, EnumCreatureType, 
//            BiomeOcean, BiomePlains, BiomeDesert, BiomeBigHills, 
//            BiomeForest, BiomeTaiga, BiomeSwamp, BiomeRiver, 
//            BiomeHell, BiomeSky, WorldGenerator, World

public abstract class BiomeBase
{

    protected BiomeBase(int i)
    {
        n = (byte)Block.GRASS.id;
        o = (byte)Block.DIRT.id;
        p = 0x4ee031;
        q = 0.1F;
        r = 0.3F;
        s = 0.5F;
        t = 0.5F;
        v = new ArrayList();
        w = new ArrayList();
        x = new ArrayList();
        E = true;
        z = new WorldGenTrees();
        A = new WorldGenBigTree();
        B = new WorldGenForest();
        C = new WorldGenSwampTree();
        y = i;
        a[i] = this;
        u = a();
        w.add(new BiomeMeta(net/minecraft/server/EntitySheep, 12, 4, 4));
        w.add(new BiomeMeta(net/minecraft/server/EntityPig, 10, 4, 4));
        w.add(new BiomeMeta(net/minecraft/server/EntityChicken, 10, 4, 4));
        w.add(new BiomeMeta(net/minecraft/server/EntityCow, 8, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntitySpider, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntityZombie, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntitySkeleton, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntityCreeper, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntitySlime, 10, 4, 4));
        v.add(new BiomeMeta(net/minecraft/server/EntityEnderman, 2, 4, 4));
        x.add(new BiomeMeta(net/minecraft/server/EntitySquid, 10, 4, 4));
    }

    protected BiomeDecorator a()
    {
        return new BiomeDecorator(this);
    }

    private BiomeBase a(float f1, float f2)
    {
        s = f1;
        t = f2;
        return this;
    }

    private BiomeBase b(float f1, float f2)
    {
        q = f1;
        r = f2;
        return this;
    }

    private BiomeBase g()
    {
        E = false;
        return this;
    }

    public WorldGenerator a(Random random)
    {
        if(random.nextInt(10) == 0)
            return A;
        else
            return z;
    }

    protected BiomeBase a(String s1)
    {
        l = s1;
        return this;
    }

    protected BiomeBase a(int i)
    {
        p = i;
        return this;
    }

    protected BiomeBase b(int i)
    {
        m = i;
        return this;
    }

    public List a(EnumCreatureType enumcreaturetype)
    {
        if(enumcreaturetype == EnumCreatureType.MONSTER)
            return v;
        if(enumcreaturetype == EnumCreatureType.CREATURE)
            return w;
        if(enumcreaturetype == EnumCreatureType.WATER_CREATURE)
            return x;
        else
            return null;
    }

    public boolean b()
    {
        return D;
    }

    public boolean c()
    {
        if(D)
            return false;
        else
            return E;
    }

    public float d()
    {
        return 0.1F;
    }

    public final int e()
    {
        return (int)(t * 65536F);
    }

    public final int f()
    {
        return (int)(s * 65536F);
    }

    public void a(World world, Random random, int i, int j)
    {
        u.a(world, random, i, j);
    }

    public static final BiomeBase a[] = new BiomeBase[256];
    public static final BiomeBase OCEAN = (new BiomeOcean(0)).b(112).a("Ocean").b(-1F, 0.5F);
    public static final BiomeBase PLAINS = (new BiomePlains(1)).b(0x8db360).a("Plains").a(0.8F, 0.4F);
    public static final BiomeBase DESERT = (new BiomeDesert(2)).b(0xfa9418).a("Desert").g().a(2.0F, 0.0F).b(0.1F, 0.2F);
    public static final BiomeBase EXTREME_HILLS = (new BiomeBigHills(3)).b(0x606060).a("Extreme Hills").b(0.2F, 1.8F).a(0.2F, 0.3F);
    public static final BiomeBase FOREST = (new BiomeForest(4)).b(0x56621).a("Forest").a(0x4eba31).a(0.7F, 0.8F);
    public static final BiomeBase TAIGA = (new BiomeTaiga(5)).b(0xb6659).a("Taiga").a(0x4eba31).a(0.3F, 0.8F).b(0.1F, 0.4F);
    public static final BiomeBase SWAMPLAND = (new BiomeSwamp(6)).b(0x7f9b2).a("Swampland").a(0x8baf48).b(-0.2F, 0.1F).a(0.8F, 0.9F);
    public static final BiomeBase RIVER = (new BiomeRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
    public static final BiomeBase HELL = (new BiomeHell(8)).b(0xff0000).a("Hell").g();
    public static final BiomeBase SKY = (new BiomeSky(9)).b(0x8080ff).a("Sky").g();
    public String l;
    public int m;
    public byte n;
    public byte o;
    public int p;
    public float q;
    public float r;
    public float s;
    public float t;
    public BiomeDecorator u;
    protected List v;
    protected List w;
    protected List x;
    private boolean D;
    private boolean E;
    public final int y;
    protected WorldGenTrees z;
    protected WorldGenBigTree A;
    protected WorldGenForest B;
    protected WorldGenSwampTree C;

}
