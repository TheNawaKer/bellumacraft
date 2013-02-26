package mod.legendaire45.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


public class ClientPacketHandler implements IPacketHandler

{
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player)
	{
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		
	    if (payload.channel.equals("sword"))
	    {
	        handleSword(payload,player);
	    }

	}
	
    private void handleSword(Packet250CustomPayload packet, Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int select;
        ItemStack zero;
        ItemStack un;
        int playerId;
        
        try {
                select = inputStream.readInt();
                zero = Packet.readItemStack(inputStream);
                un = Packet.readItemStack(inputStream);
                playerId = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        EntityPlayer me = (EntityPlayer)player;
        if (me.entityId != playerId) // On verifie que ce n'est pas nous
    	{
        
	        EntityOtherPlayerMP other = (EntityOtherPlayerMP)(NetClientHandler.worldClient.getEntityByID(playerId)); // On recupere l entity du joueur
	    	if (other != null) //Si on a quelque chose, on met les infos a jour
	    	{
	    		other.select = select;
	    		other.tool = zero;
	    		other.tool2 = un;
	    	}
    	}
        else
        {
    		me.select = select;
    		me.tool = zero;
    		me.tool2 = un;
        }
    	
    }
}