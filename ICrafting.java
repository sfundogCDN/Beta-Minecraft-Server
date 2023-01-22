// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.util.List;

// Referenced classes of package net.minecraft.server:
//            Container, ItemStack

public interface ICrafting
{

    public abstract void a(Container container, List list);

    public abstract void a(Container container, int i, ItemStack itemstack);

    public abstract void a(Container container, int i, int j);
}
