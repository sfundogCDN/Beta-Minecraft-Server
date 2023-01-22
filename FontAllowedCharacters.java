// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FontAllowedCharacters
{

    public FontAllowedCharacters()
    {
    }

    private static String a()
    {
        String s = "";
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(net/minecraft/server/FontAllowedCharacters.getResourceAsStream("/font.txt"), "UTF-8"));
            String s1 = "";
            do
            {
                String s2;
                if((s2 = bufferedreader.readLine()) == null)
                    break;
                if(!s2.startsWith("#"))
                    s = (new StringBuilder()).append(s).append(s2).toString();
            } while(true);
            bufferedreader.close();
        }
        catch(Exception exception) { }
        return s;
    }

    public static final String allowedCharacters = a();
    public static final char b[] = {
        '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', 
        '<', '>', '|', '"', ':'
    };

}
