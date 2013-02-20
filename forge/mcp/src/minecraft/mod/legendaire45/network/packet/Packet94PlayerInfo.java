package mod.legendaire45.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet20NamedEntitySpawn;

public class Packet94PlayerInfo extends Packet20NamedEntitySpawn
{
    
    public int itemSelect;
    public ItemStack item;
    
    
    public Packet94PlayerInfo() {}

    public Packet94PlayerInfo(EntityPlayer par1EntityPlayer)
    {
    	super(par1EntityPlayer);
        this.item = par1EntityPlayer.tool; //item a render
        this.itemSelect = par1EntityPlayer.select;//item selectionner
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
    	System.out.println("lecture ...");
    	this.itemSelect = par1DataInputStream.readInt();
    	this.item = readItemStack(par1DataInputStream);
        super.readPacketData(par1DataInputStream);
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
    	System.out.println("envoi ...");
    	par1DataOutputStream.writeInt(this.itemSelect);
    	writeItemStack(this.item, par1DataOutputStream);
        super.writePacketData(par1DataOutputStream);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler par1NetHandler)
    {
        par1NetHandler.handleNamedEntitySpawn(this); //La fonction qui sert à traiter le packet
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 4; // La taille du packet
    }
}