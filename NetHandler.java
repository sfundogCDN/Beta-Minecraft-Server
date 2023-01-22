// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

package net.minecraft.server;


// Referenced classes of package net.minecraft.server:
//            Packet51MapChunk, Packet, Packet255KickDisconnect, Packet1Login, 
//            Packet10Flying, Packet52MultiBlockChange, Packet14BlockDig, Packet53BlockChange, 
//            Packet50PreChunk, Packet20NamedEntitySpawn, Packet30Entity, Packet34EntityTeleport, 
//            Packet15Place, Packet16BlockItemSwitch, Packet29DestroyEntity, Packet21PickupSpawn, 
//            Packet22Collect, Packet3Chat, Packet23VehicleSpawn, Packet18ArmAnimation, 
//            Packet19EntityAction, Packet2Handshake, Packet24MobSpawn, Packet4UpdateTime, 
//            Packet6SpawnPosition, Packet28EntityVelocity, Packet40EntityMetadata, Packet39AttachEntity, 
//            Packet7UseEntity, Packet38EntityStatus, Packet8UpdateHealth, Packet9Respawn, 
//            Packet60Explosion, Packet100OpenWindow, Packet101CloseWindow, Packet102WindowClick, 
//            Packet103SetSlot, Packet104WindowItems, Packet130UpdateSign, Packet105CraftProgressBar, 
//            Packet5EntityEquipment, Packet106Transaction, Packet25EntityPainting, Packet54PlayNoteBlock, 
//            Packet200Statistic, Packet17, Packet27, Packet70Bed, 
//            Packet71Weather, Packet131, Packet61, Packet254GetInfo, 
//            Packet41MobEffect, Packet42RemoveMobEffect, Packet201PlayerInfo, Packet0KeepAlive, 
//            Packet43SetExperience, Packet107SetCreativeSlot, Packet26AddExpOrb

public abstract class NetHandler
{

    public NetHandler()
    {
    }

    public abstract boolean c();

    public void a(Packet51MapChunk packet51mapchunk)
    {
    }

    public void a(Packet packet)
    {
    }

    public void a(String s, Object aobj[])
    {
    }

    public void a(Packet255KickDisconnect packet255kickdisconnect)
    {
        a(((Packet) (packet255kickdisconnect)));
    }

    public void a(Packet1Login packet1login)
    {
        a(((Packet) (packet1login)));
    }

    public void a(Packet10Flying packet10flying)
    {
        a(((Packet) (packet10flying)));
    }

    public void a(Packet52MultiBlockChange packet52multiblockchange)
    {
        a(((Packet) (packet52multiblockchange)));
    }

    public void a(Packet14BlockDig packet14blockdig)
    {
        a(((Packet) (packet14blockdig)));
    }

    public void a(Packet53BlockChange packet53blockchange)
    {
        a(((Packet) (packet53blockchange)));
    }

    public void a(Packet50PreChunk packet50prechunk)
    {
        a(((Packet) (packet50prechunk)));
    }

    public void a(Packet20NamedEntitySpawn packet20namedentityspawn)
    {
        a(((Packet) (packet20namedentityspawn)));
    }

    public void a(Packet30Entity packet30entity)
    {
        a(((Packet) (packet30entity)));
    }

    public void a(Packet34EntityTeleport packet34entityteleport)
    {
        a(((Packet) (packet34entityteleport)));
    }

    public void a(Packet15Place packet15place)
    {
        a(((Packet) (packet15place)));
    }

    public void a(Packet16BlockItemSwitch packet16blockitemswitch)
    {
        a(((Packet) (packet16blockitemswitch)));
    }

    public void a(Packet29DestroyEntity packet29destroyentity)
    {
        a(((Packet) (packet29destroyentity)));
    }

    public void a(Packet21PickupSpawn packet21pickupspawn)
    {
        a(((Packet) (packet21pickupspawn)));
    }

    public void a(Packet22Collect packet22collect)
    {
        a(((Packet) (packet22collect)));
    }

    public void a(Packet3Chat packet3chat)
    {
        a(((Packet) (packet3chat)));
    }

    public void a(Packet23VehicleSpawn packet23vehiclespawn)
    {
        a(((Packet) (packet23vehiclespawn)));
    }

    public void a(Packet18ArmAnimation packet18armanimation)
    {
        a(((Packet) (packet18armanimation)));
    }

    public void a(Packet19EntityAction packet19entityaction)
    {
        a(((Packet) (packet19entityaction)));
    }

    public void a(Packet2Handshake packet2handshake)
    {
        a(((Packet) (packet2handshake)));
    }

    public void a(Packet24MobSpawn packet24mobspawn)
    {
        a(((Packet) (packet24mobspawn)));
    }

    public void a(Packet4UpdateTime packet4updatetime)
    {
        a(((Packet) (packet4updatetime)));
    }

    public void a(Packet6SpawnPosition packet6spawnposition)
    {
        a(((Packet) (packet6spawnposition)));
    }

    public void a(Packet28EntityVelocity packet28entityvelocity)
    {
        a(((Packet) (packet28entityvelocity)));
    }

    public void a(Packet40EntityMetadata packet40entitymetadata)
    {
        a(((Packet) (packet40entitymetadata)));
    }

    public void a(Packet39AttachEntity packet39attachentity)
    {
        a(((Packet) (packet39attachentity)));
    }

    public void a(Packet7UseEntity packet7useentity)
    {
        a(((Packet) (packet7useentity)));
    }

    public void a(Packet38EntityStatus packet38entitystatus)
    {
        a(((Packet) (packet38entitystatus)));
    }

    public void a(Packet8UpdateHealth packet8updatehealth)
    {
        a(((Packet) (packet8updatehealth)));
    }

    public void a(Packet9Respawn packet9respawn)
    {
        a(((Packet) (packet9respawn)));
    }

    public void a(Packet60Explosion packet60explosion)
    {
        a(((Packet) (packet60explosion)));
    }

    public void a(Packet100OpenWindow packet100openwindow)
    {
        a(((Packet) (packet100openwindow)));
    }

    public void a(Packet101CloseWindow packet101closewindow)
    {
        a(((Packet) (packet101closewindow)));
    }

    public void a(Packet102WindowClick packet102windowclick)
    {
        a(((Packet) (packet102windowclick)));
    }

    public void a(Packet103SetSlot packet103setslot)
    {
        a(((Packet) (packet103setslot)));
    }

    public void a(Packet104WindowItems packet104windowitems)
    {
        a(((Packet) (packet104windowitems)));
    }

    public void a(Packet130UpdateSign packet130updatesign)
    {
        a(((Packet) (packet130updatesign)));
    }

    public void a(Packet105CraftProgressBar packet105craftprogressbar)
    {
        a(((Packet) (packet105craftprogressbar)));
    }

    public void a(Packet5EntityEquipment packet5entityequipment)
    {
        a(((Packet) (packet5entityequipment)));
    }

    public void a(Packet106Transaction packet106transaction)
    {
        a(((Packet) (packet106transaction)));
    }

    public void a(Packet25EntityPainting packet25entitypainting)
    {
        a(((Packet) (packet25entitypainting)));
    }

    public void a(Packet54PlayNoteBlock packet54playnoteblock)
    {
        a(((Packet) (packet54playnoteblock)));
    }

    public void a(Packet200Statistic packet200statistic)
    {
        a(((Packet) (packet200statistic)));
    }

    public void a(Packet17 packet17)
    {
        a(((Packet) (packet17)));
    }

    public void a(Packet27 packet27)
    {
        a(((Packet) (packet27)));
    }

    public void a(Packet70Bed packet70bed)
    {
        a(((Packet) (packet70bed)));
    }

    public void a(Packet71Weather packet71weather)
    {
        a(((Packet) (packet71weather)));
    }

    public void a(Packet131 packet131)
    {
        a(((Packet) (packet131)));
    }

    public void a(Packet61 packet61)
    {
        a(((Packet) (packet61)));
    }

    public void a(Packet254GetInfo packet254getinfo)
    {
        a(((Packet) (packet254getinfo)));
    }

    public void a(Packet41MobEffect packet41mobeffect)
    {
        a(((Packet) (packet41mobeffect)));
    }

    public void a(Packet42RemoveMobEffect packet42removemobeffect)
    {
        a(((Packet) (packet42removemobeffect)));
    }

    public void a(Packet201PlayerInfo packet201playerinfo)
    {
        a(((Packet) (packet201playerinfo)));
    }

    public void a(Packet0KeepAlive packet0keepalive)
    {
        a(((Packet) (packet0keepalive)));
    }

    public void a(Packet43SetExperience packet43setexperience)
    {
        a(((Packet) (packet43setexperience)));
    }

    public void a(Packet107SetCreativeSlot packet107setcreativeslot)
    {
        a(((Packet) (packet107setcreativeslot)));
    }

    public void a(Packet26AddExpOrb packet26addexporb)
    {
        a(((Packet) (packet26addexporb)));
    }
}
