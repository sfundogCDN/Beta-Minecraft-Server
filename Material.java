// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            MaterialTransparent, MaterialMapColor, MaterialLiquid, MaterialLogic, 
//            MaterialPortal, MaterialWeb

public class Material
{

    public Material(MaterialMapColor materialmapcolor)
    {
        H = true;
        D = materialmapcolor;
    }

    public boolean isLiquid()
    {
        return false;
    }

    public boolean isBuildable()
    {
        return true;
    }

    public boolean blocksLight()
    {
        return true;
    }

    public boolean isSolid()
    {
        return true;
    }

    private Material o()
    {
        G = true;
        return this;
    }

    protected Material e()
    {
        H = false;
        return this;
    }

    protected Material f()
    {
        canBurn = true;
        return this;
    }

    public boolean isBurnable()
    {
        return canBurn;
    }

    public Material h()
    {
        F = true;
        return this;
    }

    public boolean isReplacable()
    {
        return F;
    }

    public boolean j()
    {
        if(G)
            return false;
        else
            return isSolid();
    }

    public boolean k()
    {
        return H;
    }

    public int l()
    {
        return I;
    }

    protected Material m()
    {
        I = 1;
        return this;
    }

    protected Material n()
    {
        I = 2;
        return this;
    }

    public static final Material AIR;
    public static final Material GRASS;
    public static final Material EARTH;
    public static final Material WOOD;
    public static final Material STONE;
    public static final Material ORE;
    public static final Material WATER;
    public static final Material LAVA;
    public static final Material LEAVES;
    public static final Material PLANT;
    public static final Material REPLACEABLE_PLANT;
    public static final Material SPONGE;
    public static final Material CLOTH;
    public static final Material FIRE;
    public static final Material SAND;
    public static final Material ORIENTABLE;
    public static final Material SHATTERABLE;
    public static final Material TNT;
    public static final Material CORAL;
    public static final Material ICE;
    public static final Material SNOW_LAYER;
    public static final Material SNOW_BLOCK;
    public static final Material CACTUS;
    public static final Material CLAY;
    public static final Material PUMPKIN;
    public static final Material PORTAL;
    public static final Material CAKE;
    public static final Material WEB;
    public static final Material PISTON;
    private boolean canBurn;
    private boolean F;
    private boolean G;
    public final MaterialMapColor D;
    private boolean H;
    private int I;

    static 
    {
        AIR = new MaterialTransparent(MaterialMapColor.b);
        GRASS = new Material(MaterialMapColor.c);
        EARTH = new Material(MaterialMapColor.l);
        WOOD = (new Material(MaterialMapColor.o)).f();
        STONE = (new Material(MaterialMapColor.m)).e();
        ORE = (new Material(MaterialMapColor.h)).e();
        WATER = (new MaterialLiquid(MaterialMapColor.n)).m();
        LAVA = (new MaterialLiquid(MaterialMapColor.f)).m();
        LEAVES = (new Material(MaterialMapColor.i)).f().o().m();
        PLANT = (new MaterialLogic(MaterialMapColor.i)).m();
        REPLACEABLE_PLANT = (new MaterialLogic(MaterialMapColor.i)).f().m().h();
        SPONGE = new Material(MaterialMapColor.e);
        CLOTH = (new Material(MaterialMapColor.e)).f();
        FIRE = (new MaterialTransparent(MaterialMapColor.b)).m();
        SAND = new Material(MaterialMapColor.d);
        ORIENTABLE = (new MaterialLogic(MaterialMapColor.b)).m();
        SHATTERABLE = (new Material(MaterialMapColor.b)).o();
        TNT = (new Material(MaterialMapColor.f)).f().o();
        CORAL = (new Material(MaterialMapColor.i)).m();
        ICE = (new Material(MaterialMapColor.g)).o();
        SNOW_LAYER = (new MaterialLogic(MaterialMapColor.j)).h().o().e().m();
        SNOW_BLOCK = (new Material(MaterialMapColor.j)).e();
        CACTUS = (new Material(MaterialMapColor.i)).o().m();
        CLAY = new Material(MaterialMapColor.k);
        PUMPKIN = (new Material(MaterialMapColor.i)).m();
        PORTAL = (new MaterialPortal(MaterialMapColor.b)).n();
        CAKE = (new Material(MaterialMapColor.b)).m();
        WEB = (new MaterialWeb(MaterialMapColor.e)).e().m();
        PISTON = (new Material(MaterialMapColor.m)).n();
    }
}
