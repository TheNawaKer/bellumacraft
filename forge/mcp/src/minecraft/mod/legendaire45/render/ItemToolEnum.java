package mod.legendaire45.render;

import mod.legendaire45.mod_retrogame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemToolEnum {
	
	public static boolean isTool(ItemStack par1)
	{
		if(mod_retrogame.epeeToolE.itemID == par1.itemID)
			return true;
		if(mod_retrogame.epeeToolS.itemID == par1.itemID)
			return true;	
		if(mod_retrogame.epeeToolR.itemID == par1.itemID)
			return true;	
		if(Item.swordDiamond.itemID == par1.itemID)
			return true;
		if(Item.swordSteel.itemID == par1.itemID)
			return true;	
		if(Item.swordStone.itemID == par1.itemID)
			return true;	
		if(Item.swordGold.itemID == par1.itemID)
			return true;
		if(Item.swordWood.itemID == par1.itemID)
			return true;
		if(Item.bow.itemID == par1.itemID)
			return true;
		if(mod_retrogame.teleportbow.itemID == par1.itemID)
			return true;
		if(mod_retrogame.firebow.itemID == par1.itemID)
			return true;
		return false;
	}

}
