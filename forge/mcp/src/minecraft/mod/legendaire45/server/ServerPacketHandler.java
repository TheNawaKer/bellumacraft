package mod.legendaire45.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;


public class ServerPacketHandler implements IPacketHandler

{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player)
	{
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		EntityPlayer sender = (EntityPlayer) player;	
	    if (payload.channel.equals("sword"))
	    {
	    	handleAll(payload,player);
	    }
	    if (payload.channel.equals("other"))
	    {
	    	handleAll(payload,player);
	    }
	}
	
    private void handleAll(Packet250CustomPayload packet, Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        PacketDispatcher.sendPacketToAllPlayers(packet);
    }
    
    


}