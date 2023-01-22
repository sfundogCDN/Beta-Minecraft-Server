// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, World, Block, BlockJukeBox, 
//            ItemStack, EntityHuman

public class ItemRecord extends Item
{

    protected ItemRecord(int i, String s)
    {
        super(i);
        a = s;
        maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
    {
        if(world.getTypeId(i, j, k) == Block.JUKEBOX.id && world.getData(i, j, k) == 0)
        {
            if(world.isStatic)
            {
                return true;
            } else
            {
                ((BlockJukeBox)Block.JUKEBOX).f(world, i, j, k, id);
                world.a(null, 1005, i, j, k, id);
                itemstack.count--;
                return true;
            }
        } else
        {
            return false;
        }
    }

    public final String a;
}
