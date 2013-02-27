package mod.legendaire45.network.player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class PlayerInfoSender 
{
	public static void sendInfo(EntityPlayer par1EntityPlayer)
	{
        int select = par1EntityPlayer.inventory.currentItem;
        ItemStack zero = par1EntityPlayer.inventory.mainInventory[0];
        ItemStack un = par1EntityPlayer.inventory.mainInventory[1];
        int playerId = par1EntityPlayer.entityId;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
                outputStream.writeInt(select);
                Packet.writeItemStack(zero, outputStream);
                Packet.writeItemStack(un, outputStream);
                outputStream.writeInt(playerId);
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "sword";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
                // We are on the server side.
                EntityPlayerMP player = (EntityPlayerMP) par1EntityPlayer;
                
        } else if (side == Side.CLIENT) {
                // We are on the client side.
        	System.out.println("envoi ...");
                EntityClientPlayerMP player = (EntityClientPlayerMP) par1EntityPlayer;
                player.sendQueue.addToSendQueue(packet);
        } else {
                // We are on the Bukkit server.
        }
	}
}
