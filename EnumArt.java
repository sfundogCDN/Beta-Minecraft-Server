// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


public final class EnumArt extends Enum
{

    public static EnumArt[] values()
    {
        return (EnumArt[])F.clone();
    }

    public static EnumArt valueOf(String s)
    {
        return (EnumArt)Enum.valueOf(net/minecraft/server/EnumArt, s);
    }

    private EnumArt(String s, int i, String s1, int j, int k, int l, int i1)
    {
        super(s, i);
        A = s1;
        B = j;
        C = k;
        D = l;
        E = i1;
    }

    public static final EnumArt KEBAB;
    public static final EnumArt AZTEC;
    public static final EnumArt ALBAN;
    public static final EnumArt AZTEC2;
    public static final EnumArt BOMB;
    public static final EnumArt PLANT;
    public static final EnumArt WASTELAND;
    public static final EnumArt POOL;
    public static final EnumArt COURBET;
    public static final EnumArt SEA;
    public static final EnumArt SUNSET;
    public static final EnumArt CREEBET;
    public static final EnumArt WANDERER;
    public static final EnumArt GRAHAM;
    public static final EnumArt MATCH;
    public static final EnumArt BUST;
    public static final EnumArt STAGE;
    public static final EnumArt VOID;
    public static final EnumArt SKULL_AND_ROSES;
    public static final EnumArt FIGHTERS;
    public static final EnumArt POINTER;
    public static final EnumArt PIGSCENE;
    public static final EnumArt BURNINGSKULL;
    public static final EnumArt SKELETON;
    public static final EnumArt DONKEYKONG;
    public static final int z = "SkullAndRoses".length();
    public final String A;
    public final int B;
    public final int C;
    public final int D;
    public final int E;
    private static final EnumArt F[];

    static 
    {
        KEBAB = new EnumArt("Kebab", 0, "Kebab", 16, 16, 0, 0);
        AZTEC = new EnumArt("Aztec", 1, "Aztec", 16, 16, 16, 0);
        ALBAN = new EnumArt("Alban", 2, "Alban", 16, 16, 32, 0);
        AZTEC2 = new EnumArt("Aztec2", 3, "Aztec2", 16, 16, 48, 0);
        BOMB = new EnumArt("Bomb", 4, "Bomb", 16, 16, 64, 0);
        PLANT = new EnumArt("Plant", 5, "Plant", 16, 16, 80, 0);
        WASTELAND = new EnumArt("Wasteland", 6, "Wasteland", 16, 16, 96, 0);
        POOL = new EnumArt("Pool", 7, "Pool", 32, 16, 0, 32);
        COURBET = new EnumArt("Courbet", 8, "Courbet", 32, 16, 32, 32);
        SEA = new EnumArt("Sea", 9, "Sea", 32, 16, 64, 32);
        SUNSET = new EnumArt("Sunset", 10, "Sunset", 32, 16, 96, 32);
        CREEBET = new EnumArt("Creebet", 11, "Creebet", 32, 16, 128, 32);
        WANDERER = new EnumArt("Wanderer", 12, "Wanderer", 16, 32, 0, 64);
        GRAHAM = new EnumArt("Graham", 13, "Graham", 16, 32, 16, 64);
        MATCH = new EnumArt("Match", 14, "Match", 32, 32, 0, 128);
        BUST = new EnumArt("Bust", 15, "Bust", 32, 32, 32, 128);
        STAGE = new EnumArt("Stage", 16, "Stage", 32, 32, 64, 128);
        VOID = new EnumArt("Void", 17, "Void", 32, 32, 96, 128);
        SKULL_AND_ROSES = new EnumArt("SkullAndRoses", 18, "SkullAndRoses", 32, 32, 128, 128);
        FIGHTERS = new EnumArt("Fighters", 19, "Fighters", 64, 32, 0, 96);
        POINTER = new EnumArt("Pointer", 20, "Pointer", 64, 64, 0, 192);
        PIGSCENE = new EnumArt("Pigscene", 21, "Pigscene", 64, 64, 64, 192);
        BURNINGSKULL = new EnumArt("BurningSkull", 22, "BurningSkull", 64, 64, 128, 192);
        SKELETON = new EnumArt("Skeleton", 23, "Skeleton", 64, 48, 192, 64);
        DONKEYKONG = new EnumArt("DonkeyKong", 24, "DonkeyKong", 64, 48, 192, 112);
        F = (new EnumArt[] {
            KEBAB, AZTEC, ALBAN, AZTEC2, BOMB, PLANT, WASTELAND, POOL, COURBET, SEA, 
            SUNSET, CREEBET, WANDERER, GRAHAM, MATCH, BUST, STAGE, VOID, SKULL_AND_ROSES, FIGHTERS, 
            POINTER, PIGSCENE, BURNINGSKULL, SKELETON, DONKEYKONG
        });
    }
}
