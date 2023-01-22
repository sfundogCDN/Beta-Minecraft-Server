// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            IProgressUpdate

public interface Convertable
{

    public abstract boolean isConvertable(String s);

    public abstract boolean convert(String s, IProgressUpdate iprogressupdate);
}
