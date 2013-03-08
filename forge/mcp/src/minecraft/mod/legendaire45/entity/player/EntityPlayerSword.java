package mod.legendaire45.entity.player;

import cpw.mods.fml.client.FMLClientHandler;
import mod.legendaire45.network.player.PlayerInfoSender;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.src.PlayerAPI;
import net.minecraft.src.PlayerBase;

public class EntityPlayerSword extends PlayerBase
{
	
	private Minecraft mc = FMLClientHandler.instance().getClient();
	
    public EntityPlayerSword(PlayerAPI playerapi)
    {
            super(playerapi);
    }
    
    public void afterOnUpdate()
    {
		 boolean var = false;
		 if(this.mc.thePlayer.inventory.currentItem != this.mc.thePlayer.getDataWatcher().getWatchableObjectInt(25))
		 {
			 var=true;
		 }
		 if(this.mc.thePlayer.inventory.mainInventory[0] != null && var!=true)
		 {
			 if(this.mc.thePlayer.getDataWatcher().getWatchableObjectItemStack(26) == null)
			 {
				 var=true;
			 }
			 else if(this.mc.thePlayer.inventory.mainInventory[0].itemID != this.mc.thePlayer.getDataWatcher().getWatchableObjectItemStack(26).itemID)
			 {
				 var=true;
			 }
		 }
		 if(this.mc.thePlayer.inventory.mainInventory[1] != null && var!=true)
		 {
			 if(this.mc.thePlayer.getDataWatcher().getWatchableObjectItemStack(27) == null)
			 {
				 var=true;
			 }
			 else if(this.mc.thePlayer.inventory.mainInventory[1].itemID != this.mc.thePlayer.getDataWatcher().getWatchableObjectItemStack(27).itemID)
			 {
				 var=true;
			 }
		 }
		 
		 if(var)
		 {
			 PlayerInfoSender.sendInfo(this.mc.thePlayer); 
		 }
    }
    
    public void afterCloseScreen()
    {
    	 PlayerInfoSender.sendInfo(this.mc.thePlayer); 
    } 
}
