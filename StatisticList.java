// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.*;

// Referenced classes of package net.minecraft.server:
//            Block, CraftingManager, CraftingRecipe, ItemStack, 
//            FurnaceRecipes, Statistic, Item, StatisticCollector, 
//            CraftingStatistic, BlockFlower, BlockGrass, CounterStatistic, 
//            AchievementList

public class StatisticList
{

    public StatisticList()
    {
    }

    public static void a()
    {
    }

    public static void b()
    {
        E = a(E, "stat.useItem", 0x1020000, 0, Block.byId.length);
        F = b(F, "stat.breakItem", 0x1030000, 0, Block.byId.length);
        G = true;
        d();
    }

    public static void c()
    {
        E = a(E, "stat.useItem", 0x1020000, Block.byId.length, 32000);
        F = b(F, "stat.breakItem", 0x1030000, Block.byId.length, 32000);
        H = true;
        d();
    }

    public static void d()
    {
        if(!G || !H)
            return;
        HashSet hashset = new HashSet();
        CraftingRecipe craftingrecipe;
        for(Iterator iterator = CraftingManager.getInstance().b().iterator(); iterator.hasNext(); hashset.add(Integer.valueOf(craftingrecipe.b().id)))
            craftingrecipe = (CraftingRecipe)iterator.next();

        ItemStack itemstack;
        for(Iterator iterator1 = FurnaceRecipes.getInstance().b().values().iterator(); iterator1.hasNext(); hashset.add(Integer.valueOf(itemstack.id)))
            itemstack = (ItemStack)iterator1.next();

        D = new Statistic[32000];
        Iterator iterator2 = hashset.iterator();
        do
        {
            if(!iterator2.hasNext())
                break;
            Integer integer = (Integer)iterator2.next();
            if(Item.byId[integer.intValue()] != null)
            {
                String s1 = StatisticCollector.a("stat.craftItem", new Object[] {
                    Item.byId[integer.intValue()].j()
                });
                D[integer.intValue()] = (new CraftingStatistic(0x1010000 + integer.intValue(), s1, integer.intValue())).d();
            }
        } while(true);
        a(D);
    }

    private static Statistic[] a(String s1, int i1)
    {
        Statistic astatistic[] = new Statistic[256];
        for(int j1 = 0; j1 < 256; j1++)
            if(Block.byId[j1] != null && Block.byId[j1].m())
            {
                String s2 = StatisticCollector.a(s1, new Object[] {
                    Block.byId[j1].k()
                });
                astatistic[j1] = (new CraftingStatistic(i1 + j1, s2, j1)).d();
                e.add((CraftingStatistic)astatistic[j1]);
            }

        a(astatistic);
        return astatistic;
    }

    private static Statistic[] a(Statistic astatistic[], String s1, int i1, int j1, int k1)
    {
        if(astatistic == null)
            astatistic = new Statistic[32000];
        for(int l1 = j1; l1 < k1; l1++)
        {
            if(Item.byId[l1] == null)
                continue;
            String s2 = StatisticCollector.a(s1, new Object[] {
                Item.byId[l1].j()
            });
            astatistic[l1] = (new CraftingStatistic(i1 + l1, s2, l1)).d();
            if(l1 >= Block.byId.length)
                d.add((CraftingStatistic)astatistic[l1]);
        }

        a(astatistic);
        return astatistic;
    }

    private static Statistic[] b(Statistic astatistic[], String s1, int i1, int j1, int k1)
    {
        if(astatistic == null)
            astatistic = new Statistic[32000];
        for(int l1 = j1; l1 < k1; l1++)
            if(Item.byId[l1] != null && Item.byId[l1].f())
            {
                String s2 = StatisticCollector.a(s1, new Object[] {
                    Item.byId[l1].j()
                });
                astatistic[l1] = (new CraftingStatistic(i1 + l1, s2, l1)).d();
            }

        a(astatistic);
        return astatistic;
    }

    private static void a(Statistic astatistic[])
    {
        a(astatistic, Block.STATIONARY_WATER.id, Block.WATER.id);
        a(astatistic, Block.STATIONARY_LAVA.id, Block.STATIONARY_LAVA.id);
        a(astatistic, Block.JACK_O_LANTERN.id, Block.PUMPKIN.id);
        a(astatistic, Block.BURNING_FURNACE.id, Block.FURNACE.id);
        a(astatistic, Block.GLOWING_REDSTONE_ORE.id, Block.REDSTONE_ORE.id);
        a(astatistic, Block.DIODE_ON.id, Block.DIODE_OFF.id);
        a(astatistic, Block.REDSTONE_TORCH_ON.id, Block.REDSTONE_TORCH_OFF.id);
        a(astatistic, Block.RED_MUSHROOM.id, Block.BROWN_MUSHROOM.id);
        a(astatistic, Block.DOUBLE_STEP.id, Block.STEP.id);
        a(astatistic, Block.GRASS.id, Block.DIRT.id);
        a(astatistic, Block.SOIL.id, Block.DIRT.id);
    }

    private static void a(Statistic astatistic[], int i1, int j1)
    {
        if(astatistic[i1] != null && astatistic[j1] == null)
        {
            astatistic[j1] = astatistic[i1];
            return;
        } else
        {
            b.remove(astatistic[i1]);
            e.remove(astatistic[i1]);
            c.remove(astatistic[i1]);
            astatistic[i1] = astatistic[j1];
            return;
        }
    }

    protected static Map a = new HashMap();
    public static List b = new ArrayList();
    public static List c = new ArrayList();
    public static List d = new ArrayList();
    public static List e = new ArrayList();
    public static Statistic f = (new CounterStatistic(1000, StatisticCollector.a("stat.startGame"))).e().d();
    public static Statistic g = (new CounterStatistic(1001, StatisticCollector.a("stat.createWorld"))).e().d();
    public static Statistic h = (new CounterStatistic(1002, StatisticCollector.a("stat.loadWorld"))).e().d();
    public static Statistic i = (new CounterStatistic(1003, StatisticCollector.a("stat.joinMultiplayer"))).e().d();
    public static Statistic j = (new CounterStatistic(1004, StatisticCollector.a("stat.leaveGame"))).e().d();
    public static Statistic k;
    public static Statistic l;
    public static Statistic m;
    public static Statistic n;
    public static Statistic o;
    public static Statistic p;
    public static Statistic q;
    public static Statistic r;
    public static Statistic s;
    public static Statistic t;
    public static Statistic u = (new CounterStatistic(2010, StatisticCollector.a("stat.jump"))).e().d();
    public static Statistic v = (new CounterStatistic(2011, StatisticCollector.a("stat.drop"))).e().d();
    public static Statistic w = (new CounterStatistic(2020, StatisticCollector.a("stat.damageDealt"))).d();
    public static Statistic x = (new CounterStatistic(2021, StatisticCollector.a("stat.damageTaken"))).d();
    public static Statistic y = (new CounterStatistic(2022, StatisticCollector.a("stat.deaths"))).d();
    public static Statistic z = (new CounterStatistic(2023, StatisticCollector.a("stat.mobKills"))).d();
    public static Statistic A = (new CounterStatistic(2024, StatisticCollector.a("stat.playerKills"))).d();
    public static Statistic B = (new CounterStatistic(2025, StatisticCollector.a("stat.fishCaught"))).d();
    public static Statistic C[] = a("stat.mineBlock", 0x1000000);
    public static Statistic D[];
    public static Statistic E[];
    public static Statistic F[];
    private static boolean G = false;
    private static boolean H = false;

    static 
    {
        k = (new CounterStatistic(1100, StatisticCollector.a("stat.playOneMinute"), Statistic.j)).e().d();
        l = (new CounterStatistic(2000, StatisticCollector.a("stat.walkOneCm"), Statistic.k)).e().d();
        m = (new CounterStatistic(2001, StatisticCollector.a("stat.swimOneCm"), Statistic.k)).e().d();
        n = (new CounterStatistic(2002, StatisticCollector.a("stat.fallOneCm"), Statistic.k)).e().d();
        o = (new CounterStatistic(2003, StatisticCollector.a("stat.climbOneCm"), Statistic.k)).e().d();
        p = (new CounterStatistic(2004, StatisticCollector.a("stat.flyOneCm"), Statistic.k)).e().d();
        q = (new CounterStatistic(2005, StatisticCollector.a("stat.diveOneCm"), Statistic.k)).e().d();
        r = (new CounterStatistic(2006, StatisticCollector.a("stat.minecartOneCm"), Statistic.k)).e().d();
        s = (new CounterStatistic(2007, StatisticCollector.a("stat.boatOneCm"), Statistic.k)).e().d();
        t = (new CounterStatistic(2008, StatisticCollector.a("stat.pigOneCm"), Statistic.k)).e().d();
        AchievementList.a();
    }
}
