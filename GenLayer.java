// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            LayerIsland, GenLayerZoomFuzzy, GenLayerIsland, GenLayerZoom, 
//            GenLayerRiverInit, GenLayerRiver, GenLayerSmooth, GenLayerBiome, 
//            GenLayerTemperature, GenLayerDownfall, GenLayerSmoothZoom, GenLayerTemperatureMix, 
//            GenLayerDownfallMix, GenLayerRiverMix, GenLayerZoomVoronoi

public abstract class GenLayer
{

    public static GenLayer[] a(long l)
    {
        Object obj = new LayerIsland(1L);
        obj = new GenLayerZoomFuzzy(2000L, ((GenLayer) (obj)));
        obj = new GenLayerIsland(1L, ((GenLayer) (obj)));
        obj = new GenLayerZoom(2001L, ((GenLayer) (obj)));
        obj = new GenLayerIsland(2L, ((GenLayer) (obj)));
        obj = new GenLayerZoom(2002L, ((GenLayer) (obj)));
        obj = new GenLayerIsland(3L, ((GenLayer) (obj)));
        obj = new GenLayerZoom(2003L, ((GenLayer) (obj)));
        obj = new GenLayerIsland(3L, ((GenLayer) (obj)));
        obj = new GenLayerZoom(2004L, ((GenLayer) (obj)));
        obj = new GenLayerIsland(3L, ((GenLayer) (obj)));
        byte byte0 = 4;
        Object obj1 = obj;
        obj1 = GenLayerZoom.a(1000L, ((GenLayer) (obj1)), 0);
        obj1 = new GenLayerRiverInit(100L, ((GenLayer) (obj1)));
        obj1 = GenLayerZoom.a(1000L, ((GenLayer) (obj1)), byte0 + 2);
        obj1 = new GenLayerRiver(1L, ((GenLayer) (obj1)));
        obj1 = new GenLayerSmooth(1000L, ((GenLayer) (obj1)));
        Object obj2 = obj;
        obj2 = GenLayerZoom.a(1000L, ((GenLayer) (obj2)), 0);
        obj2 = new GenLayerBiome(200L, ((GenLayer) (obj2)));
        obj2 = GenLayerZoom.a(1000L, ((GenLayer) (obj2)), 2);
        Object obj3 = new GenLayerTemperature(((GenLayer) (obj2)));
        Object obj4 = new GenLayerDownfall(((GenLayer) (obj2)));
        for(int i = 0; i < byte0; i++)
        {
            obj2 = new GenLayerZoom(1000 + i, ((GenLayer) (obj2)));
            if(i == 0)
                obj2 = new GenLayerIsland(3L, ((GenLayer) (obj2)));
            obj3 = new GenLayerSmoothZoom(1000 + i, ((GenLayer) (obj3)));
            obj3 = new GenLayerTemperatureMix(((GenLayer) (obj3)), ((GenLayer) (obj2)), i);
            obj4 = new GenLayerSmoothZoom(1000 + i, ((GenLayer) (obj4)));
            obj4 = new GenLayerDownfallMix(((GenLayer) (obj4)), ((GenLayer) (obj2)), i);
        }

        obj2 = new GenLayerSmooth(1000L, ((GenLayer) (obj2)));
        obj2 = new GenLayerRiverMix(100L, ((GenLayer) (obj2)), ((GenLayer) (obj1)));
        obj3 = GenLayerSmoothZoom.a(1000L, ((GenLayer) (obj3)), 2);
        obj4 = GenLayerSmoothZoom.a(1000L, ((GenLayer) (obj4)), 2);
        GenLayerZoomVoronoi genlayerzoomvoronoi = new GenLayerZoomVoronoi(10L, ((GenLayer) (obj2)));
        ((GenLayer) (obj2)).b(l);
        ((GenLayer) (obj3)).b(l);
        ((GenLayer) (obj4)).b(l);
        genlayerzoomvoronoi.b(l);
        return (new GenLayer[] {
            obj2, genlayerzoomvoronoi, obj3, obj4
        });
    }

    public GenLayer(long l)
    {
        d = l;
        d *= d * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        d += l;
        d *= d * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        d += l;
        d *= d * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        d += l;
    }

    public void b(long l)
    {
        b = l;
        if(a != null)
            a.b(l);
        b *= b * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        b += d;
        b *= b * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        b += d;
        b *= b * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        b += d;
    }

    public void a(long l, long l1)
    {
        c = b;
        c *= c * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        c += l;
        c *= c * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        c += l1;
        c *= c * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        c += l;
        c *= c * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        c += l1;
    }

    protected int a(int i)
    {
        int j = (int)((c >> 24) % (long)i);
        if(j < 0)
            j += i;
        c *= c * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        c += b;
        return j;
    }

    public abstract int[] a(int i, int j, int k, int l);

    private long b;
    protected GenLayer a;
    private long c;
    private long d;
}
