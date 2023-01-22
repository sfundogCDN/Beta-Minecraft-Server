// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Material, MaterialMapColor

public class MaterialLiquid extends Material
{

    public MaterialLiquid(MaterialMapColor materialmapcolor)
    {
        super(materialmapcolor);
        h();
        m();
    }

    public boolean isLiquid()
    {
        return true;
    }

    public boolean isSolid()
    {
        return false;
    }

    public boolean isBuildable()
    {
        return false;
    }
}
