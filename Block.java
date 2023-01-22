// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Block.java

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            ItemStack, EntityItem, MovingObjectPosition, StepSound, 
//            StepSoundStone, StepSoundSand, BlockStone, BlockGrass, 
//            BlockDirt, BlockSapling, BlockFlowing, BlockStationary, 
//            BlockSand, BlockGravel, BlockOre, BlockLog, 
//            BlockLeaves, BlockSponge, BlockGlass, BlockDispenser, 
//            BlockSandStone, BlockNote, BlockBed, BlockMinecartTrack, 
//            BlockMinecartDetector, BlockPiston, BlockWeb, BlockLongGrass, 
//            BlockDeadBush, BlockPistonExtension, BlockCloth, BlockPistonMoving, 
//            BlockFlower, BlockMushroom, BlockOreBlock, BlockStep, 
//            BlockTNT, BlockBookshelf, BlockObsidian, BlockTorch, 
//            BlockFire, BlockMobSpawner, BlockStairs, BlockChest, 
//            BlockRedstoneWire, BlockWorkbench, BlockCrops, BlockSoil, 
//            BlockFurnace, BlockSign, TileEntitySign, BlockDoor, 
//            BlockLadder, BlockLever, BlockPressurePlate, BlockRedstoneOre, 
//            BlockRedstoneTorch, BlockButton, BlockSnow, BlockIce, 
//            BlockSnowBlock, BlockCactus, BlockClay, BlockReed, 
//            BlockJukeBox, BlockFence, BlockPumpkin, BlockBloodStone, 
//            BlockSlowSand, BlockLightStone, BlockPortal, BlockCake, 
//            BlockDiode, BlockLockedChest, BlockTrapdoor, BlockMonsterEggs, 
//            BlockSmoothBrick, BlockHugeMushroom, BlockThin, BlockMelon, 
//            BlockStem, BlockVine, BlockFenceGate, ItemCloth, 
//            ItemLog, ItemStep, ItemSapling, ItemLeaves, 
//            ItemColoredBlock, ItemPiston, ItemMobSpawner, ItemBlock, 
//            Material, IBlockAccess, AxisAlignedBB, EntityHuman, 
//            World, Vec3D, StatisticList, StatisticCollector, 
//            EnumMobType, Item, Entity, EntityLiving

public class Block
{

    protected Block(int i, Material material)
    {
        bD = true;
        bE = true;
        stepSound = d;
        bM = 1.0F;
        frictionFactor = 0.6F;
        if(byId[i] != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(i).append(" is already occupied by ").append(byId[i]).append(" when adding ").append(this).toString());
        } else
        {
            this.material = material;
            byId[i] = this;
            id = i;
            a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            o[i] = a();
            q[i] = a() ? 255 : 0;
            r[i] = !material.blocksLight();
            isTileEntity[i] = false;
            return;
        }
    }

    protected Block g()
    {
        t[id] = true;
        return this;
    }

    protected void h()
    {
    }

    protected Block(int i, int j, Material material)
    {
        this(i, material);
        textureId = j;
    }

    protected Block a(StepSound stepsound)
    {
        stepSound = stepsound;
        return this;
    }

    protected Block f(int i)
    {
        q[id] = i;
        return this;
    }

    protected Block a(float f)
    {
        s[id] = (int)(15F * f);
        return this;
    }

    protected Block b(float f)
    {
        durability = f * 3F;
        return this;
    }

    public boolean b()
    {
        return true;
    }

    protected Block c(float f)
    {
        strength = f;
        if(durability < f * 5F)
            durability = f * 5F;
        return this;
    }

    protected Block i()
    {
        c(-1F);
        return this;
    }

    public float j()
    {
        return strength;
    }

    protected Block a(boolean flag)
    {
        n[id] = flag;
        return this;
    }

    public void a(float f, float f1, float f2, float f3, float f4, float f5)
    {
        minX = f;
        minY = f1;
        minZ = f2;
        maxX = f3;
        maxY = f4;
        maxZ = f5;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return iblockaccess.getMaterial(i, j, k).isBuildable();
    }

    public int a(int i, int j)
    {
        return a(i);
    }

    public int a(int i)
    {
        return textureId;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        AxisAlignedBB axisalignedbb1 = e(world, i, j, k);
        if(axisalignedbb1 != null && axisalignedbb.a(axisalignedbb1))
            arraylist.add(axisalignedbb1);
    }

    public AxisAlignedBB e(World world, int i, int j, int k)
    {
        return AxisAlignedBB.b((double)i + minX, (double)j + minY, (double)k + minZ, (double)i + maxX, (double)j + maxY, (double)k + maxZ);
    }

    public boolean a()
    {
        return true;
    }

    public boolean a(int i, boolean flag)
    {
        return q_();
    }

    public boolean q_()
    {
        return true;
    }

    public void a(World world1, int i1, int j1, int k1, Random random1)
    {
    }

    public void postBreak(World world1, int i1, int j1, int k1, int l1)
    {
    }

    public void doPhysics(World world1, int i1, int j1, int k1, int l1)
    {
    }

    public int c()
    {
        return 10;
    }

    public void a(World world1, int i1, int j1, int k1)
    {
    }

    public void remove(World world1, int i1, int j1, int k1)
    {
    }

    public int a(Random random)
    {
        return 1;
    }

    public int a(int i, Random random)
    {
        return id;
    }

    public float getDamage(EntityHuman entityhuman)
    {
        return strength >= 0.0F ? entityhuman.b(this) ? entityhuman.a(this) / strength / 30F : 1.0F / strength / 100F : 0.0F;
    }

    public final void g(World world, int i, int j, int k, int l)
    {
        dropNaturally(world, i, j, k, l, 1.0F);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f)
    {
        if(!world.isStatic)
        {
            int i1 = a(world.random);
            for(int j1 = 0; j1 < i1; j1++)
            {
                if(world.random.nextFloat() >= f)
                    continue;
                int k1 = a(l, world.random);
                if(k1 > 0)
                    a(world, i, j, k, new ItemStack(k1, 1, a_(l)));
            }

        }
    }

    protected void a(World world, int i, int j, int k, ItemStack itemstack)
    {
        if(!world.isStatic)
        {
            float f = 0.7F;
            double d0 = (double)(world.random.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(world.random.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(world.random.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(world, (double)i + d0, (double)j + d1, (double)k + d2, itemstack);
            entityitem.pickupDelay = 10;
            world.addEntity(entityitem);
        }
    }

    protected int a_(int i)
    {
        return 0;
    }

    public float a(Entity entity)
    {
        return durability / 5F;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        a(((IBlockAccess) (world)), i, j, k);
        vec3d = vec3d.add(-i, -j, -k);
        vec3d1 = vec3d1.add(-i, -j, -k);
        Vec3D vec3d2 = vec3d.a(vec3d1, minX);
        Vec3D vec3d3 = vec3d.a(vec3d1, maxX);
        Vec3D vec3d4 = vec3d.b(vec3d1, minY);
        Vec3D vec3d5 = vec3d.b(vec3d1, maxY);
        Vec3D vec3d6 = vec3d.c(vec3d1, minZ);
        Vec3D vec3d7 = vec3d.c(vec3d1, maxZ);
        if(!a(vec3d2))
            vec3d2 = null;
        if(!a(vec3d3))
            vec3d3 = null;
        if(!b(vec3d4))
            vec3d4 = null;
        if(!b(vec3d5))
            vec3d5 = null;
        if(!c(vec3d6))
            vec3d6 = null;
        if(!c(vec3d7))
            vec3d7 = null;
        Vec3D vec3d8 = null;
        if(vec3d2 != null && (vec3d8 == null || vec3d.b(vec3d2) < vec3d.b(vec3d8)))
            vec3d8 = vec3d2;
        if(vec3d3 != null && (vec3d8 == null || vec3d.b(vec3d3) < vec3d.b(vec3d8)))
            vec3d8 = vec3d3;
        if(vec3d4 != null && (vec3d8 == null || vec3d.b(vec3d4) < vec3d.b(vec3d8)))
            vec3d8 = vec3d4;
        if(vec3d5 != null && (vec3d8 == null || vec3d.b(vec3d5) < vec3d.b(vec3d8)))
            vec3d8 = vec3d5;
        if(vec3d6 != null && (vec3d8 == null || vec3d.b(vec3d6) < vec3d.b(vec3d8)))
            vec3d8 = vec3d6;
        if(vec3d7 != null && (vec3d8 == null || vec3d.b(vec3d7) < vec3d.b(vec3d8)))
            vec3d8 = vec3d7;
        if(vec3d8 == null)
            return null;
        byte b0 = -1;
        if(vec3d8 == vec3d2)
            b0 = 4;
        if(vec3d8 == vec3d3)
            b0 = 5;
        if(vec3d8 == vec3d4)
            b0 = 0;
        if(vec3d8 == vec3d5)
            b0 = 1;
        if(vec3d8 == vec3d6)
            b0 = 2;
        if(vec3d8 == vec3d7)
            b0 = 3;
        return new MovingObjectPosition(i, j, k, b0, vec3d8.add(i, j, k));
    }

    private boolean a(Vec3D vec3d)
    {
        return vec3d != null ? vec3d.b >= minY && vec3d.b <= maxY && vec3d.c >= minZ && vec3d.c <= maxZ : false;
    }

    private boolean b(Vec3D vec3d)
    {
        return vec3d != null ? vec3d.a >= minX && vec3d.a <= maxX && vec3d.c >= minZ && vec3d.c <= maxZ : false;
    }

    private boolean c(Vec3D vec3d)
    {
        return vec3d != null ? vec3d.a >= minX && vec3d.a <= maxX && vec3d.b >= minY && vec3d.b <= maxY : false;
    }

    public void a_(World world1, int i1, int j1, int k1)
    {
    }

    public boolean canPlace(World world, int i, int j, int k, int l)
    {
        return canPlace(world, i, j, k);
    }

    public boolean canPlace(World world, int i, int j, int k)
    {
        int l = world.getTypeId(i, j, k);
        return l == 0 || byId[l].material.isReplacable();
    }

    public boolean interact(World world, int i, int j, int i1, EntityHuman entityhuman1)
    {
        return false;
    }

    public void b(World world1, int i1, int j1, int k1, Entity entity1)
    {
    }

    public void postPlace(World world1, int i1, int j1, int k1, int l1)
    {
    }

    public void b(World world1, int i1, int j1, int k1, EntityHuman entityhuman1)
    {
    }

    public void a(World world1, int i1, int j1, int k1, Entity entity1, Vec3D vec3d1)
    {
    }

    public void a(IBlockAccess iblockaccess1, int i1, int j1, int k1)
    {
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int i1, int j1)
    {
        return false;
    }

    public boolean isPowerSource()
    {
        return false;
    }

    public void a(World world1, int i1, int j1, int k1, Entity entity1)
    {
    }

    public boolean d(World world, int i, int j, int i1, int j1)
    {
        return false;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
    {
        entityhuman.a(StatisticList.C[id], 1);
        entityhuman.b(0.025F);
        g(world, i, j, k, l);
    }

    public boolean f(World world, int i, int j, int i1)
    {
        return true;
    }

    public void postPlace(World world1, int i1, int j1, int k1, EntityLiving entityliving1)
    {
    }

    public Block a(String s)
    {
        name = (new StringBuilder()).append("tile.").append(s).toString();
        return this;
    }

    public String k()
    {
        return StatisticCollector.a((new StringBuilder()).append(l()).append(".name").toString());
    }

    public String l()
    {
        return name;
    }

    public void a(World world1, int j1, int k1, int l1, int i2, int j2)
    {
    }

    public boolean m()
    {
        return bE;
    }

    protected Block n()
    {
        bE = false;
        return this;
    }

    public int e()
    {
        return material.l();
    }

    public static final StepSound d;
    public static final StepSound e;
    public static final StepSound f;
    public static final StepSound g;
    public static final StepSound h;
    public static final StepSound i;
    public static final StepSound j;
    public static final StepSound k;
    public static final StepSound l;
    public static final Block byId[];
    public static final boolean n[] = new boolean[256];
    public static final boolean o[] = new boolean[256];
    public static final boolean isTileEntity[] = new boolean[256];
    public static final int q[] = new int[256];
    public static final boolean r[];
    public static final int s[] = new int[256];
    public static final boolean t[] = new boolean[256];
    public static final Block STONE;
    public static final BlockGrass GRASS;
    public static final Block DIRT;
    public static final Block COBBLESTONE;
    public static final Block WOOD;
    public static final Block SAPLING;
    public static final Block BEDROCK;
    public static final Block WATER;
    public static final Block STATIONARY_WATER;
    public static final Block LAVA;
    public static final Block STATIONARY_LAVA;
    public static final Block SAND;
    public static final Block GRAVEL;
    public static final Block GOLD_ORE;
    public static final Block IRON_ORE;
    public static final Block COAL_ORE;
    public static final Block LOG;
    public static final BlockLeaves LEAVES;
    public static final Block SPONGE;
    public static final Block GLASS;
    public static final Block LAPIS_ORE;
    public static final Block LAPIS_BLOCK;
    public static final Block DISPENSER;
    public static final Block SANDSTONE;
    public static final Block NOTE_BLOCK = (new BlockNote(25)).c(0.8F).a("musicBlock").g();
    public static final Block BED = (new BlockBed(26)).c(0.2F).a("bed").n().g();
    public static final Block GOLDEN_RAIL;
    public static final Block DETECTOR_RAIL;
    public static final Block PISTON_STICKY;
    public static final Block WEB = (new BlockWeb(30, 11)).f(1).c(4F).a("web");
    public static final BlockLongGrass LONG_GRASS;
    public static final BlockDeadBush DEAD_BUSH;
    public static final Block PISTON;
    public static final BlockPistonExtension PISTON_EXTENSION = (BlockPistonExtension)(new BlockPistonExtension(34, 107)).g();
    public static final Block WOOL;
    public static final BlockPistonMoving PISTON_MOVING = new BlockPistonMoving(36);
    public static final BlockFlower YELLOW_FLOWER;
    public static final BlockFlower RED_ROSE;
    public static final BlockFlower BROWN_MUSHROOM;
    public static final BlockFlower RED_MUSHROOM;
    public static final Block GOLD_BLOCK;
    public static final Block IRON_BLOCK;
    public static final Block DOUBLE_STEP;
    public static final Block STEP;
    public static final Block BRICK;
    public static final Block TNT;
    public static final Block BOOKSHELF;
    public static final Block MOSSY_COBBLESTONE;
    public static final Block OBSIDIAN;
    public static final Block TORCH;
    public static final BlockFire FIRE;
    public static final Block MOB_SPAWNER;
    public static final Block WOOD_STAIRS;
    public static final Block CHEST;
    public static final Block REDSTONE_WIRE;
    public static final Block DIAMOND_ORE;
    public static final Block DIAMOND_BLOCK;
    public static final Block WORKBENCH;
    public static final Block CROPS;
    public static final Block SOIL;
    public static final Block FURNACE;
    public static final Block BURNING_FURNACE;
    public static final Block SIGN_POST;
    public static final Block WOODEN_DOOR;
    public static final Block LADDER;
    public static final Block RAILS;
    public static final Block COBBLESTONE_STAIRS;
    public static final Block WALL_SIGN;
    public static final Block LEVER;
    public static final Block STONE_PLATE;
    public static final Block IRON_DOOR_BLOCK;
    public static final Block WOOD_PLATE;
    public static final Block REDSTONE_ORE;
    public static final Block GLOWING_REDSTONE_ORE;
    public static final Block REDSTONE_TORCH_OFF;
    public static final Block REDSTONE_TORCH_ON;
    public static final Block STONE_BUTTON;
    public static final Block SNOW;
    public static final Block ICE;
    public static final Block SNOW_BLOCK;
    public static final Block CACTUS;
    public static final Block CLAY;
    public static final Block SUGAR_CANE_BLOCK;
    public static final Block JUKEBOX;
    public static final Block FENCE;
    public static final Block PUMPKIN;
    public static final Block NETHERRACK;
    public static final Block SOUL_SAND;
    public static final Block GLOWSTONE;
    public static final BlockPortal PORTAL;
    public static final Block JACK_O_LANTERN;
    public static final Block CAKE_BLOCK;
    public static final Block DIODE_OFF;
    public static final Block DIODE_ON;
    public static final Block LOCKED_CHEST;
    public static final Block TRAP_DOOR;
    public static final Block MONSTER_EGGS = (new BlockMonsterEggs(97)).c(0.75F);
    public static final Block SMOOTH_BRICK;
    public static final Block BIG_MUSHROOM_1;
    public static final Block BIG_MUSHROOM_2;
    public static final Block IRON_FENCE;
    public static final Block THIN_GLASS;
    public static final Block MELON;
    public static final Block PUMPKIN_STEM;
    public static final Block MELON_STEM;
    public static final Block VINE;
    public static final Block FENCE_GATE;
    public static final Block BRICK_STAIRS;
    public static final Block STONE_STAIRS;
    public int textureId;
    public final int id;
    protected float strength;
    protected float durability;
    protected boolean bD;
    protected boolean bE;
    public double minX;
    public double minY;
    public double minZ;
    public double maxX;
    public double maxY;
    public double maxZ;
    public StepSound stepSound;
    public float bM;
    public final Material material;
    public float frictionFactor;
    private String name;

    static 
    {
        d = new StepSound("stone", 1.0F, 1.0F);
        e = new StepSound("wood", 1.0F, 1.0F);
        f = new StepSound("gravel", 1.0F, 1.0F);
        g = new StepSound("grass", 1.0F, 1.0F);
        h = new StepSound("stone", 1.0F, 1.0F);
        i = new StepSound("stone", 1.0F, 1.5F);
        j = new StepSoundStone("stone", 1.0F, 1.0F);
        k = new StepSound("cloth", 1.0F, 1.0F);
        l = new StepSoundSand("sand", 1.0F, 1.0F);
        byId = new Block[256];
        r = new boolean[256];
        STONE = (new BlockStone(1, 1)).c(1.5F).b(10F).a(h).a("stone");
        GRASS = (BlockGrass)(new BlockGrass(2)).c(0.6F).a(g).a("grass");
        DIRT = (new BlockDirt(3, 2)).c(0.5F).a(f).a("dirt");
        COBBLESTONE = (new Block(4, 16, Material.STONE)).c(2.0F).b(10F).a(h).a("stonebrick");
        WOOD = (new Block(5, 4, Material.WOOD)).c(2.0F).b(5F).a(e).a("wood").g();
        SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(g).a("sapling").g();
        BEDROCK = (new Block(7, 17, Material.STONE)).i().b(6000000F).a(h).a("bedrock").n();
        WATER = (new BlockFlowing(8, Material.WATER)).c(100F).f(3).a("water").n().g();
        STATIONARY_WATER = (new BlockStationary(9, Material.WATER)).c(100F).f(3).a("water").n().g();
        LAVA = (new BlockFlowing(10, Material.LAVA)).c(0.0F).a(1.0F).f(255).a("lava").n().g();
        STATIONARY_LAVA = (new BlockStationary(11, Material.LAVA)).c(100F).a(1.0F).f(255).a("lava").n().g();
        SAND = (new BlockSand(12, 18)).c(0.5F).a(l).a("sand");
        GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
        GOLD_ORE = (new BlockOre(14, 32)).c(3F).b(5F).a(h).a("oreGold");
        IRON_ORE = (new BlockOre(15, 33)).c(3F).b(5F).a(h).a("oreIron");
        COAL_ORE = (new BlockOre(16, 34)).c(3F).b(5F).a(h).a("oreCoal");
        LOG = (new BlockLog(17)).c(2.0F).a(e).a("log").g();
        LEAVES = (BlockLeaves)(new BlockLeaves(18, 52)).c(0.2F).f(1).a(g).a("leaves").g();
        SPONGE = (new BlockSponge(19)).c(0.6F).a(g).a("sponge");
        GLASS = (new BlockGlass(20, 49, Material.SHATTERABLE, false)).c(0.3F).a(j).a("glass");
        LAPIS_ORE = (new BlockOre(21, 160)).c(3F).b(5F).a(h).a("oreLapis");
        LAPIS_BLOCK = (new Block(22, 144, Material.STONE)).c(3F).b(5F).a(h).a("blockLapis");
        DISPENSER = (new BlockDispenser(23)).c(3.5F).a(h).a("dispenser").g();
        SANDSTONE = (new BlockSandStone(24)).a(h).c(0.8F).a("sandStone");
        GOLDEN_RAIL = (new BlockMinecartTrack(27, 179, true)).c(0.7F).a(i).a("goldenRail").g();
        DETECTOR_RAIL = (new BlockMinecartDetector(28, 195)).c(0.7F).a(i).a("detectorRail").g();
        PISTON_STICKY = (new BlockPiston(29, 106, true)).a("pistonStickyBase").g();
        LONG_GRASS = (BlockLongGrass)(new BlockLongGrass(31, 39)).c(0.0F).a(g).a("tallgrass");
        DEAD_BUSH = (BlockDeadBush)(new BlockDeadBush(32, 55)).c(0.0F).a(g).a("deadbush");
        PISTON = (new BlockPiston(33, 107, false)).a("pistonBase").g();
        WOOL = (new BlockCloth()).c(0.8F).a(k).a("cloth").g();
        YELLOW_FLOWER = (BlockFlower)(new BlockFlower(37, 13)).c(0.0F).a(g).a("flower");
        RED_ROSE = (BlockFlower)(new BlockFlower(38, 12)).c(0.0F).a(g).a("rose");
        BROWN_MUSHROOM = (BlockFlower)(new BlockMushroom(39, 29)).c(0.0F).a(g).a(0.125F).a("mushroom");
        RED_MUSHROOM = (BlockFlower)(new BlockMushroom(40, 28)).c(0.0F).a(g).a("mushroom");
        GOLD_BLOCK = (new BlockOreBlock(41, 23)).c(3F).b(10F).a(i).a("blockGold");
        IRON_BLOCK = (new BlockOreBlock(42, 22)).c(5F).b(10F).a(i).a("blockIron");
        DOUBLE_STEP = (new BlockStep(43, true)).c(2.0F).b(10F).a(h).a("stoneSlab");
        STEP = (new BlockStep(44, false)).c(2.0F).b(10F).a(h).a("stoneSlab");
        BRICK = (new Block(45, 7, Material.STONE)).c(2.0F).b(10F).a(h).a("brick");
        TNT = (new BlockTNT(46, 8)).c(0.0F).a(g).a("tnt");
        BOOKSHELF = (new BlockBookshelf(47, 35)).c(1.5F).a(e).a("bookshelf");
        MOSSY_COBBLESTONE = (new Block(48, 36, Material.STONE)).c(2.0F).b(10F).a(h).a("stoneMoss");
        OBSIDIAN = (new BlockObsidian(49, 37)).c(10F).b(2000F).a(h).a("obsidian");
        TORCH = (new BlockTorch(50, 80)).c(0.0F).a(0.9375F).a(e).a("torch").g();
        FIRE = (BlockFire)(new BlockFire(51, 31)).c(0.0F).a(1.0F).a(e).a("fire").n();
        MOB_SPAWNER = (new BlockMobSpawner(52, 65)).c(5F).a(i).a("mobSpawner").n();
        WOOD_STAIRS = (new BlockStairs(53, WOOD)).a("stairsWood").g();
        CHEST = (new BlockChest(54)).c(2.5F).a(e).a("chest").g();
        REDSTONE_WIRE = (new BlockRedstoneWire(55, 164)).c(0.0F).a(d).a("redstoneDust").n().g();
        DIAMOND_ORE = (new BlockOre(56, 50)).c(3F).b(5F).a(h).a("oreDiamond");
        DIAMOND_BLOCK = (new BlockOreBlock(57, 24)).c(5F).b(10F).a(i).a("blockDiamond");
        WORKBENCH = (new BlockWorkbench(58)).c(2.5F).a(e).a("workbench");
        CROPS = (new BlockCrops(59, 88)).c(0.0F).a(g).a("crops").n().g();
        SOIL = (new BlockSoil(60)).c(0.6F).a(f).a("farmland").g();
        FURNACE = (new BlockFurnace(61, false)).c(3.5F).a(h).a("furnace").g();
        BURNING_FURNACE = (new BlockFurnace(62, true)).c(3.5F).a(h).a(0.875F).a("furnace").g();
        SIGN_POST = (new BlockSign(63, net/minecraft/server/TileEntitySign, true)).c(1.0F).a(e).a("sign").n().g();
        WOODEN_DOOR = (new BlockDoor(64, Material.WOOD)).c(3F).a(e).a("doorWood").n().g();
        LADDER = (new BlockLadder(65, 83)).c(0.4F).a(e).a("ladder").g();
        RAILS = (new BlockMinecartTrack(66, 128, false)).c(0.7F).a(i).a("rail").g();
        COBBLESTONE_STAIRS = (new BlockStairs(67, COBBLESTONE)).a("stairsStone").g();
        WALL_SIGN = (new BlockSign(68, net/minecraft/server/TileEntitySign, false)).c(1.0F).a(e).a("sign").n().g();
        LEVER = (new BlockLever(69, 96)).c(0.5F).a(e).a("lever").g();
        STONE_PLATE = (new BlockPressurePlate(70, STONE.textureId, EnumMobType.MOBS, Material.STONE)).c(0.5F).a(h).a("pressurePlate").g();
        IRON_DOOR_BLOCK = (new BlockDoor(71, Material.ORE)).c(5F).a(i).a("doorIron").n().g();
        WOOD_PLATE = (new BlockPressurePlate(72, WOOD.textureId, EnumMobType.EVERYTHING, Material.WOOD)).c(0.5F).a(e).a("pressurePlate").g();
        REDSTONE_ORE = (new BlockRedstoneOre(73, 51, false)).c(3F).b(5F).a(h).a("oreRedstone").g();
        GLOWING_REDSTONE_ORE = (new BlockRedstoneOre(74, 51, true)).a(0.625F).c(3F).b(5F).a(h).a("oreRedstone").g();
        REDSTONE_TORCH_OFF = (new BlockRedstoneTorch(75, 115, false)).c(0.0F).a(e).a("notGate").g();
        REDSTONE_TORCH_ON = (new BlockRedstoneTorch(76, 99, true)).c(0.0F).a(0.5F).a(e).a("notGate").g();
        STONE_BUTTON = (new BlockButton(77, STONE.textureId)).c(0.5F).a(h).a("button").g();
        SNOW = (new BlockSnow(78, 66)).c(0.1F).a(k).a("snow");
        ICE = (new BlockIce(79, 67)).c(0.5F).f(3).a(j).a("ice");
        SNOW_BLOCK = (new BlockSnowBlock(80, 66)).c(0.2F).a(k).a("snow");
        CACTUS = (new BlockCactus(81, 70)).c(0.4F).a(k).a("cactus");
        CLAY = (new BlockClay(82, 72)).c(0.6F).a(f).a("clay");
        SUGAR_CANE_BLOCK = (new BlockReed(83, 73)).c(0.0F).a(g).a("reeds").n();
        JUKEBOX = (new BlockJukeBox(84, 74)).c(2.0F).b(10F).a(h).a("jukebox").g();
        FENCE = (new BlockFence(85, 4)).c(2.0F).b(5F).a(e).a("fence");
        PUMPKIN = (new BlockPumpkin(86, 102, false)).c(1.0F).a(e).a("pumpkin").g();
        NETHERRACK = (new BlockBloodStone(87, 103)).c(0.4F).a(h).a("hellrock");
        SOUL_SAND = (new BlockSlowSand(88, 104)).c(0.5F).a(l).a("hellsand");
        GLOWSTONE = (new BlockLightStone(89, 105, Material.STONE)).c(0.3F).a(j).a(1.0F).a("lightgem");
        PORTAL = (BlockPortal)(new BlockPortal(90, 14)).c(-1F).a(j).a(0.75F).a("portal");
        JACK_O_LANTERN = (new BlockPumpkin(91, 102, true)).c(1.0F).a(e).a(1.0F).a("litpumpkin").g();
        CAKE_BLOCK = (new BlockCake(92, 121)).c(0.5F).a(k).a("cake").n().g();
        DIODE_OFF = (new BlockDiode(93, false)).c(0.0F).a(e).a("diode").n().g();
        DIODE_ON = (new BlockDiode(94, true)).c(0.0F).a(0.625F).a(e).a("diode").n().g();
        LOCKED_CHEST = (new BlockLockedChest(95)).c(0.0F).a(1.0F).a(e).a("lockedchest").a(true).g();
        TRAP_DOOR = (new BlockTrapdoor(96, Material.WOOD)).c(3F).a(e).a("trapdoor").n().g();
        SMOOTH_BRICK = (new BlockSmoothBrick(98)).c(1.5F).b(10F).a(h).a("stonebricksmooth");
        BIG_MUSHROOM_1 = (new BlockHugeMushroom(99, Material.WOOD, 142, 0)).c(0.2F).a(e).a("mushroom").g();
        BIG_MUSHROOM_2 = (new BlockHugeMushroom(100, Material.WOOD, 142, 1)).c(0.2F).a(e).a("mushroom").g();
        IRON_FENCE = (new BlockThin(101, 85, 85, Material.ORE)).c(5F).b(10F).a(i).a("fenceIron");
        THIN_GLASS = (new BlockThin(102, 49, 148, Material.SHATTERABLE)).c(0.3F).a(j).a("thinGlass");
        MELON = (new BlockMelon(103)).c(1.0F).a(e).a("melon");
        PUMPKIN_STEM = (new BlockStem(104, PUMPKIN)).c(0.0F).a(e).a("pumpkinStem").g();
        MELON_STEM = (new BlockStem(105, MELON)).c(0.0F).a(e).a("pumpkinStem").g();
        VINE = (new BlockVine(106)).c(0.2F).a(g).a("vine").g();
        FENCE_GATE = (new BlockFenceGate(107, 4)).c(2.0F).b(5F).a(e).a("fenceGate").g();
        BRICK_STAIRS = (new BlockStairs(108, BRICK)).a("stairsBrick").g();
        STONE_STAIRS = (new BlockStairs(109, SMOOTH_BRICK)).a("stairsStoneBrickSmooth").g();
        Item.byId[WOOL.id] = (new ItemCloth(WOOL.id - 256)).a("cloth");
        Item.byId[LOG.id] = (new ItemLog(LOG.id - 256, LOG)).a("log");
        Item.byId[SMOOTH_BRICK.id] = (new ItemLog(SMOOTH_BRICK.id - 256, SMOOTH_BRICK)).a("stonebricksmooth");
        Item.byId[STEP.id] = (new ItemStep(STEP.id - 256)).a("stoneSlab");
        Item.byId[SAPLING.id] = (new ItemSapling(SAPLING.id - 256)).a("sapling");
        Item.byId[LEAVES.id] = (new ItemLeaves(LEAVES.id - 256)).a("leaves");
        Item.byId[VINE.id] = new ItemColoredBlock(VINE.id - 256, false);
        Item.byId[LONG_GRASS.id] = new ItemColoredBlock(LONG_GRASS.id - 256, true);
        Item.byId[PISTON.id] = new ItemPiston(PISTON.id - 256);
        Item.byId[PISTON_STICKY.id] = new ItemPiston(PISTON_STICKY.id - 256);
        Item.byId[BIG_MUSHROOM_1.id] = new ItemLog(BIG_MUSHROOM_1.id - 256, BIG_MUSHROOM_1);
        Item.byId[BIG_MUSHROOM_2.id] = new ItemLog(BIG_MUSHROOM_2.id - 256, BIG_MUSHROOM_2);
        Item.byId[MOB_SPAWNER.id] = new ItemMobSpawner(MOB_SPAWNER.id - 256);
        for(int i = 0; i < 256; i++)
            if(byId[i] != null && Item.byId[i] == null)
            {
                Item.byId[i] = new ItemBlock(i - 256);
                byId[i].h();
            }

        r[0] = true;
        StatisticList.b();
    }
}
