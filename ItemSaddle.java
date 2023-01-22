// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Item, EntityPig, ItemStack, EntityLiving

public class ItemSaddle extends Item
{

    public ItemSaddle(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving)
    {
        if(entityliving instanceof EntityPig)
        {
            EntityPig entitypig = (EntityPig)entityliving;
            if(!entitypig.hasSaddle())
            {
                entitypig.setSaddle(true);
                itemstack.count--;
            }
        }
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        a(itemstack, entityliving);
        return true;
    }
}
