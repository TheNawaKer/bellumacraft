package mod.legendaire45.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
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
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;


public class ClientPacketHandler implements IPacketHandler
{
	
	private Minecraft mc = FMLClientHandler.instance().getClient();
	
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player)
	{
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		
	    if (payload.channel.equals("sword"))
	    {
	        handleSword(payload,player);
	    }
	    if (payload.channel.equals("field"))
	    {
	        handleField(payload,player);
	    }
	    if (payload.channel.equals("field"))
	    {
	        handleFieldInit(payload,player);
	    }
	    if (payload.channel.equals("other"))
	    {
	        handleFieldOther(payload,player);
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
        
	        EntityOtherPlayerMP other = (EntityOtherPlayerMP)(this.mc.theWorld.getEntityByID(playerId)); // On recupere l entity du joueur
	    	if (other != null) //Si on a quelque chose, on met les infos a jour
	    	{
	    		other.getDataWatcher().updateObject(25, select);
	    		if(zero!=null)
	    		other.getDataWatcher().updateObject(26, zero);
	    		else
	    		other.getDataWatcher().updateObject(26, new ItemStack(0,0,0));
	    		if(un!=null)
	    		other.getDataWatcher().updateObject(27, un);
	    		else
	    		other.getDataWatcher().updateObject(27, new ItemStack(0,0,0));
	    	}
    	}
        else
        {
        	me.getDataWatcher().updateObject(25, select);
    		if(zero!=null)
    		me.getDataWatcher().updateObject(26, zero);
    		else
    		me.getDataWatcher().updateObject(26, new ItemStack(0,0,0));
    		if(un!=null)
    		me.getDataWatcher().updateObject(27, un);
    		else
    		me.getDataWatcher().updateObject(27, new ItemStack(0,0,0));
        }
    	
    }
    
    private void handleField(Packet250CustomPayload packet, Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int playerId;
        
        try {
                playerId = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
    	EntityPlayer me = (EntityPlayer)player;
        if (me.entityId != playerId) // On verifie que ce n'est pas nous
    	{
        
	        EntityOtherPlayerMP other = (EntityOtherPlayerMP)(this.mc.theWorld.getEntityByID(playerId)); // On recupere l entity du joueur
	    	if (other != null) //Si on a quelque chose, on met les infos a jour
	    	{
	    		other.getDataWatcher().addObject(25, new Integer(0));
	    		other.getDataWatcher().addObjectByDataType(26, 5);
	    		other.getDataWatcher().addObjectByDataType(27, 5);
	    	}
    	}
        else
        {
        	me.getDataWatcher().addObject(25, new Integer(0));
        	me.getDataWatcher().addObjectByDataType(26, 5);
        	me.getDataWatcher().addObjectByDataType(27, 5);
        }
    }
    
    private void handleFieldInit(Packet250CustomPayload packet,Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int playerId;
        
        try {
                playerId = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        System.out.println(playerId);
        if (this.mc.thePlayer.entityId != playerId) // On verifie que ce n'est pas nous
    	{
        	int myID = this.mc.thePlayer.entityId;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(16);
            DataOutputStream outputStream = new DataOutputStream(baos);
            try {
                    outputStream.writeInt(myID);
                    outputStream.writeInt(playerId);
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
            Packet250CustomPayload other = new Packet250CustomPayload();
            other.channel = "other";
            other.data = baos.toByteArray();
            other.length = baos.size();
            this.mc.thePlayer.sendQueue.addToSendQueue(other);
            
    	}
    }
    
    private void handleFieldOther(Packet250CustomPayload packet, Player player)
    {
    	System.out.println("adding player ...");
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int myID;
        int playerId;
        
        try {
                myID = inputStream.readInt();
                playerId = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        if(this.mc.thePlayer.entityId==playerId)
        {
	        EntityOtherPlayerMP other = (EntityOtherPlayerMP)(this.mc.theWorld.getEntityByID(myID)); // On recupere l entity du joueur
	    	if (other != null) //Si on a quelque chose, on met les infos a jour
	    	{
	    		other.getDataWatcher().addObject(25, new Integer(0));
	    		other.getDataWatcher().addObjectByDataType(26, 5);
	    		other.getDataWatcher().addObjectByDataType(27, 5);
	    	}
        }
    }
}