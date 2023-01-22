// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.PrintStream;
import java.util.Random;

// Referenced classes of package net.minecraft.server:
//            StatisticCollector, EnumAnimation, ItemSpade, EnumToolMaterial, 
//            ItemPickaxe, ItemAxe, ItemFlintAndSteel, ItemFood, 
//            ItemBow, ItemCoal, ItemSword, ItemSoup, 
//            ItemHoe, ItemSeeds, Block, ItemArmor, 
//            ItemPainting, MobEffectList, ItemSign, ItemDoor, 
//            Material, ItemBucket, ItemMinecart, ItemSaddle, 
//            ItemRedstone, ItemSnowball, ItemBoat, ItemReed, 
//            ItemEgg, ItemFishingRod, ItemDye, ItemBed, 
//            ItemWorldMap, ItemShears, ItemRecord, StatisticList, 
//            ItemStack, EntityHuman, World, EntityLiving, 
//            Entity

public class Item
{

    protected Item(int k)
    {
        maxStackSize = 64;
        durability = 0;
        br = false;
        bs = false;
        craftingResult = null;
        id = 256 + k;
        if(byId[256 + k] != null)
            System.out.println((new StringBuilder()).append("CONFLICT @ ").append(k).toString());
        byId[256 + k] = this;
    }

    public Item b(int k)
    {
        textureId = k;
        return this;
    }

    public Item c(int k)
    {
        maxStackSize = k;
        return this;
    }

    public Item a(int k, int l)
    {
        textureId = k + l * 16;
        return this;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int k, int l, int i1, int j1)
    {
        return false;
    }

    public float a(ItemStack itemstack, Block block)
    {
        return 1.0F;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        return itemstack;
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
        return itemstack;
    }

    public int getMaxStackSize()
    {
        return maxStackSize;
    }

    public int filterData(int k)
    {
        return 0;
    }

    public boolean d()
    {
        return bs;
    }

    protected Item a(boolean flag)
    {
        bs = flag;
        return this;
    }

    public int getMaxDurability()
    {
        return durability;
    }

    protected Item d(int k)
    {
        durability = k;
        return this;
    }

    public boolean f()
    {
        return durability > 0 && !bs;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        return false;
    }

    public boolean a(ItemStack itemstack, int k, int l, int i1, int j1, EntityLiving entityliving)
    {
        return false;
    }

    public int a(Entity entity)
    {
        return 1;
    }

    public boolean a(Block block)
    {
        return false;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving)
    {
    }

    public Item g()
    {
        br = true;
        return this;
    }

    public Item a(String s)
    {
        name = (new StringBuilder()).append("item.").append(s).toString();
        return this;
    }

    public String b()
    {
        return name;
    }

    public String a(ItemStack itemstack)
    {
        return name;
    }

    public Item a(Item item)
    {
        if(maxStackSize > 1)
        {
            throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
        } else
        {
            craftingResult = item;
            return this;
        }
    }

    public Item h()
    {
        return craftingResult;
    }

    public boolean i()
    {
        return craftingResult != null;
    }

    public String j()
    {
        return StatisticCollector.a((new StringBuilder()).append(b()).append(".name").toString());
    }

    public void a(ItemStack itemstack, World world, Entity entity, int k, boolean flag)
    {
    }

    public void d(ItemStack itemstack, World world, EntityHuman entityhuman)
    {
    }

    public boolean i_()
    {
        return false;
    }

    public EnumAnimation b(ItemStack itemstack)
    {
        return EnumAnimation.a;
    }

    public int c(ItemStack itemstack)
    {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int k)
    {
    }

    protected static Random b = new Random();
    public static Item byId[] = new Item[32000];
    public static Item IRON_SPADE;
    public static Item IRON_PICKAXE;
    public static Item IRON_AXE;
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).a(5, 0).a("flintAndSteel");
    public static Item APPLE = (new ItemFood(4, 4, 0.3F, false)).a(10, 0).a("apple");
    public static Item BOW = (new ItemBow(5)).a(5, 1).a("bow");
    public static Item ARROW = (new Item(6)).a(5, 2).a("arrow");
    public static Item COAL = (new ItemCoal(7)).a(7, 0).a("coal");
    public static Item DIAMOND = (new Item(8)).a(7, 3).a("emerald");
    public static Item IRON_INGOT = (new Item(9)).a(7, 1).a("ingotIron");
    public static Item GOLD_INGOT = (new Item(10)).a(7, 2).a("ingotGold");
    public static Item IRON_SWORD;
    public static Item WOOD_SWORD;
    public static Item WOOD_SPADE;
    public static Item WOOD_PICKAXE;
    public static Item WOOD_AXE;
    public static Item STONE_SWORD;
    public static Item STONE_SPADE;
    public static Item STONE_PICKAXE;
    public static Item STONE_AXE;
    public static Item DIAMOND_SWORD;
    public static Item DIAMOND_SPADE;
    public static Item DIAMOND_PICKAXE;
    public static Item DIAMOND_AXE;
    public static Item STICK = (new Item(24)).a(5, 3).g().a("stick");
    public static Item BOWL = (new Item(25)).a(7, 4).a("bowl");
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 8)).a(8, 4).a("mushroomStew");
    public static Item GOLD_SWORD;
    public static Item GOLD_SPADE;
    public static Item GOLD_PICKAXE;
    public static Item GOLD_AXE;
    public static Item STRING = (new Item(31)).a(8, 0).a("string");
    public static Item FEATHER = (new Item(32)).a(8, 1).a("feather");
    public static Item SULPHUR = (new Item(33)).a(8, 2).a("sulphur");
    public static Item WOOD_HOE;
    public static Item STONE_HOE;
    public static Item IRON_HOE;
    public static Item DIAMOND_HOE;
    public static Item GOLD_HOE;
    public static Item SEEDS;
    public static Item WHEAT = (new Item(40)).a(9, 1).a("wheat");
    public static Item BREAD = (new ItemFood(41, 5, 0.6F, false)).a(9, 2).a("bread");
    public static Item LEATHER_HELMET = (new ItemArmor(42, 0, 0, 0)).a(0, 0).a("helmetCloth");
    public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, 0, 0, 1)).a(0, 1).a("chestplateCloth");
    public static Item LEATHER_LEGGINGS = (new ItemArmor(44, 0, 0, 2)).a(0, 2).a("leggingsCloth");
    public static Item LEATHER_BOOTS = (new ItemArmor(45, 0, 0, 3)).a(0, 3).a("bootsCloth");
    public static Item CHAINMAIL_HELMET = (new ItemArmor(46, 1, 1, 0)).a(1, 0).a("helmetChain");
    public static Item CHAINMAIL_CHESTPLATE = (new ItemArmor(47, 1, 1, 1)).a(1, 1).a("chestplateChain");
    public static Item CHAINMAIL_LEGGINGS = (new ItemArmor(48, 1, 1, 2)).a(1, 2).a("leggingsChain");
    public static Item CHAINMAIL_BOOTS = (new ItemArmor(49, 1, 1, 3)).a(1, 3).a("bootsChain");
    public static Item IRON_HELMET = (new ItemArmor(50, 2, 2, 0)).a(2, 0).a("helmetIron");
    public static Item IRON_CHESTPLATE = (new ItemArmor(51, 2, 2, 1)).a(2, 1).a("chestplateIron");
    public static Item IRON_LEGGINGS = (new ItemArmor(52, 2, 2, 2)).a(2, 2).a("leggingsIron");
    public static Item IRON_BOOTS = (new ItemArmor(53, 2, 2, 3)).a(2, 3).a("bootsIron");
    public static Item DIAMOND_HELMET = (new ItemArmor(54, 3, 3, 0)).a(3, 0).a("helmetDiamond");
    public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, 3, 3, 1)).a(3, 1).a("chestplateDiamond");
    public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, 3, 3, 2)).a(3, 2).a("leggingsDiamond");
    public static Item DIAMOND_BOOTS = (new ItemArmor(57, 3, 3, 3)).a(3, 3).a("bootsDiamond");
    public static Item GOLD_HELMET = (new ItemArmor(58, 1, 4, 0)).a(4, 0).a("helmetGold");
    public static Item GOLD_CHESTPLATE = (new ItemArmor(59, 1, 4, 1)).a(4, 1).a("chestplateGold");
    public static Item GOLD_LEGGINGS = (new ItemArmor(60, 1, 4, 2)).a(4, 2).a("leggingsGold");
    public static Item GOLD_BOOTS = (new ItemArmor(61, 1, 4, 3)).a(4, 3).a("bootsGold");
    public static Item FLINT = (new Item(62)).a(6, 0).a("flint");
    public static Item PORK = (new ItemFood(63, 3, 0.3F, true)).a(7, 5).a("porkchopRaw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8, 0.8F, true)).a(8, 5).a("porkchopCooked");
    public static Item PAINTING = (new ItemPainting(65)).a(10, 1).a("painting");
    public static Item GOLDEN_APPLE;
    public static Item SIGN = (new ItemSign(67)).a(10, 2).a("sign");
    public static Item WOOD_DOOR;
    public static Item BUCKET;
    public static Item WATER_BUCKET;
    public static Item LAVA_BUCKET;
    public static Item MINECART = (new ItemMinecart(72, 0)).a(7, 8).a("minecart");
    public static Item SADDLE = (new ItemSaddle(73)).a(8, 6).a("saddle");
    public static Item IRON_DOOR;
    public static Item REDSTONE = (new ItemRedstone(75)).a(8, 3).a("redstone");
    public static Item SNOW_BALL = (new ItemSnowball(76)).a(14, 0).a("snowball");
    public static Item BOAT = (new ItemBoat(77)).a(8, 8).a("boat");
    public static Item LEATHER = (new Item(78)).a(7, 6).a("leather");
    public static Item MILK_BUCKET;
    public static Item CLAY_BRICK = (new Item(80)).a(6, 1).a("brick");
    public static Item CLAY_BALL = (new Item(81)).a(9, 3).a("clay");
    public static Item SUGAR_CANE;
    public static Item PAPER = (new Item(83)).a(10, 3).a("paper");
    public static Item BOOK = (new Item(84)).a(11, 3).a("book");
    public static Item SLIME_BALL = (new Item(85)).a(14, 1).a("slimeball");
    public static Item STORAGE_MINECART = (new ItemMinecart(86, 1)).a(7, 9).a("minecartChest");
    public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).a(7, 10).a("minecartFurnace");
    public static Item EGG = (new ItemEgg(88)).a(12, 0).a("egg");
    public static Item COMPASS = (new Item(89)).a(6, 3).a("compass");
    public static Item FISHING_ROD = (new ItemFishingRod(90)).a(5, 4).a("fishingRod");
    public static Item WATCH = (new Item(91)).a(6, 4).a("clock");
    public static Item GLOWSTONE_DUST = (new Item(92)).a(9, 4).a("yellowDust");
    public static Item RAW_FISH = (new ItemFood(93, 2, 0.3F, false)).a(9, 5).a("fishRaw");
    public static Item COOKED_FISH = (new ItemFood(94, 5, 0.6F, false)).a(10, 5).a("fishCooked");
    public static Item INK_SACK = (new ItemDye(95)).a(14, 4).a("dyePowder");
    public static Item BONE = (new Item(96)).a(12, 1).a("bone").g();
    public static Item SUGAR = (new Item(97)).a(13, 0).a("sugar").g();
    public static Item CAKE;
    public static Item BED = (new ItemBed(99)).c(1).a(13, 2).a("bed");
    public static Item DIODE;
    public static Item COOKIE = (new ItemFood(101, 1, 0.1F, false)).a(12, 5).a("cookie");
    public static ItemWorldMap MAP = (ItemWorldMap)(new ItemWorldMap(102)).a(12, 3).a("map");
    public static ItemShears SHEARS = (ItemShears)(new ItemShears(103)).a(13, 5).a("shears");
    public static Item MELON = (new ItemFood(104, 2, 0.3F, false)).a(13, 6).a("melon");
    public static Item PUMPKIN_SEEDS;
    public static Item MELON_SEEDS;
    public static Item RAW_BEEF = (new ItemFood(107, 3, 0.3F, true)).a(9, 6).a("beefRaw");
    public static Item COOKED_BEEF = (new ItemFood(108, 8, 0.8F, true)).a(10, 6).a("beefCooked");
    public static Item RAW_CHICKEN;
    public static Item COOKED_CHICKEN = (new ItemFood(110, 6, 0.6F, true)).a(10, 7).a("chickenCooked");
    public static Item ROTTEN_FLESH;
    public static Item ENDER_PEARL = (new Item(112)).a(11, 6).a("enderPearl");
    public static Item GOLD_RECORD = (new ItemRecord(2000, "13")).a(0, 15).a("record");
    public static Item GREEN_RECORD = (new ItemRecord(2001, "cat")).a(1, 15).a("record");
    public final int id;
    protected int maxStackSize;
    private int durability;
    protected int textureId;
    protected boolean br;
    protected boolean bs;
    private Item craftingResult;
    private String name;

    static 
    {
        IRON_SPADE = (new ItemSpade(0, EnumToolMaterial.IRON)).a(2, 5).a("shovelIron");
        IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).a(2, 6).a("pickaxeIron");
        IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).a(2, 7).a("hatchetIron");
        IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).a(2, 4).a("swordIron");
        WOOD_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).a(0, 4).a("swordWood");
        WOOD_SPADE = (new ItemSpade(13, EnumToolMaterial.WOOD)).a(0, 5).a("shovelWood");
        WOOD_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).a(0, 6).a("pickaxeWood");
        WOOD_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).a(0, 7).a("hatchetWood");
        STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).a(1, 4).a("swordStone");
        STONE_SPADE = (new ItemSpade(17, EnumToolMaterial.STONE)).a(1, 5).a("shovelStone");
        STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).a(1, 6).a("pickaxeStone");
        STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).a(1, 7).a("hatchetStone");
        DIAMOND_SWORD = (new ItemSword(20, EnumToolMaterial.DIAMOND)).a(3, 4).a("swordDiamond");
        DIAMOND_SPADE = (new ItemSpade(21, EnumToolMaterial.DIAMOND)).a(3, 5).a("shovelDiamond");
        DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.DIAMOND)).a(3, 6).a("pickaxeDiamond");
        DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.DIAMOND)).a(3, 7).a("hatchetDiamond");
        GOLD_SWORD = (new ItemSword(27, EnumToolMaterial.GOLD)).a(4, 4).a("swordGold");
        GOLD_SPADE = (new ItemSpade(28, EnumToolMaterial.GOLD)).a(4, 5).a("shovelGold");
        GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).a(4, 6).a("pickaxeGold");
        GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).a(4, 7).a("hatchetGold");
        WOOD_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).a(0, 8).a("hoeWood");
        STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).a(1, 8).a("hoeStone");
        IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).a(2, 8).a("hoeIron");
        DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.DIAMOND)).a(3, 8).a("hoeDiamond");
        GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).a(4, 8).a("hoeGold");
        SEEDS = (new ItemSeeds(39, Block.CROPS.id)).a(9, 0).a("seeds");
        GOLDEN_APPLE = (new ItemFood(66, 10, 1.2F, false)).n().a(MobEffectList.REGENERATION.id, 30, 0, 1.0F).a(11, 0).a("appleGold");
        WOOD_DOOR = (new ItemDoor(68, Material.WOOD)).a(11, 2).a("doorWood");
        BUCKET = (new ItemBucket(69, 0)).a(10, 4).a("bucket");
        WATER_BUCKET = (new ItemBucket(70, Block.WATER.id)).a(11, 4).a("bucketWater").a(BUCKET);
        LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.id)).a(12, 4).a("bucketLava").a(BUCKET);
        IRON_DOOR = (new ItemDoor(74, Material.ORE)).a(12, 2).a("doorIron");
        MILK_BUCKET = (new ItemBucket(79, -1)).a(13, 4).a("milk").a(BUCKET);
        SUGAR_CANE = (new ItemReed(82, Block.SUGAR_CANE_BLOCK)).a(11, 1).a("reeds");
        CAKE = (new ItemReed(98, Block.CAKE_BLOCK)).c(1).a(13, 1).a("cake");
        DIODE = (new ItemReed(100, Block.DIODE_OFF)).a(6, 5).a("diode");
        PUMPKIN_SEEDS = (new ItemSeeds(105, Block.PUMPKIN_STEM.id)).a(13, 3).a("seeds_pumpkin");
        MELON_SEEDS = (new ItemSeeds(106, Block.MELON_STEM.id)).a(14, 3).a("seeds_melon");
        RAW_CHICKEN = (new ItemFood(109, 2, 0.3F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).a(9, 7).a("chickenRaw");
        ROTTEN_FLESH = (new ItemFood(111, 4, 0.1F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).a(11, 5).a("rottenFlesh");
        StatisticList.c();
    }
}
