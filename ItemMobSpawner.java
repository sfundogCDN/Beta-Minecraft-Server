// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemMobSpawner.java

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            ItemLog, TileEntityMobSpawner, Block, World, 
//            ItemStack, EntityHuman

public class ItemMobSpawner extends ItemLog
{

    public ItemMobSpawner(int id)
    {
        super(id, Block.MOB_SPAWNER);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int x, int y, int z, int face)
    {
        if(!super.a(itemstack, entityhuman, world, x, y, z, face))
            return false;
        if(face == 0)
            y--;
        else
        if(face == 1)
            y++;
        else
        if(face == 2)
            z--;
        else
        if(face == 3)
            z++;
        else
        if(face == 4)
            x--;
        else
        if(face == 5)
            x++;
        TileEntity entity = world.getTileEntity(x, y, z);
        if(entity instanceof TileEntityMobSpawner)
        {
            ((TileEntityMobSpawner)entity).setId(itemstack.getData());
            return true;
        } else
        {
            return false;
        }
    }
}
