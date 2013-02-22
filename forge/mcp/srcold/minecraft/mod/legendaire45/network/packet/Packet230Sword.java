package mod.legendaire45.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class Packet230Sword extends Packet
{
		// L'id du joueur envoyant le packet
	public int playerId;
	
	public Packet230Sword(){}
	
	public Packet230Sword(EntityPlayer player)
	{
		this.playerId = player.entityId;
	}
	

	public void readPacketData(DataInputStream var1) throws IOException
	{
			//On récupère l'ID du joueur depuis le DIS
		this.playerId = var1.readInt();
	}


	public void writePacketData(DataOutputStream var1) throws IOException
	{
			//On écrit l'ID du joueur dans le DOS
		var1.writeInt(playerId);
	}


	public void processPacket(NetHandler var1)
	{
		var1.handleSword(this); // Fonction pour traiter le packet
	}


	public int getPacketSize()
	{
		return 4; //Taille du packet
	}
}