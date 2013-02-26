package mod.legendaire45.render;

import mod.legendaire45.mod_retrogame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemToolEnum {
	
	public static boolean isWeapon(ItemStack par1)
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
		return isTool(par1);
	}
	
	public static boolean isTool(ItemStack par1)
	{
		if(mod_retrogame.hacheToolE.itemID == par1.itemID)
			return true;
		if(mod_retrogame.hacheToolS.itemID == par1.itemID)
			return true;	
		if(mod_retrogame.hacheToolR.itemID == par1.itemID)
			return true;
		if(mod_retrogame.piocheToolE.itemID == par1.itemID)
			return true;
		if(mod_retrogame.piocheToolS.itemID == par1.itemID)
			return true;	
		if(mod_retrogame.piocheToolR.itemID == par1.itemID)
			return true;
		if(mod_retrogame.pelleToolE.itemID == par1.itemID)
			return true;
		if(mod_retrogame.pelleToolS.itemID == par1.itemID)
			return true;	
		if(mod_retrogame.pelleToolR.itemID == par1.itemID)
			return true;
		if(Item.pickaxeDiamond.itemID == par1.itemID)
			return true;
		if(Item.pickaxeGold.itemID == par1.itemID)
			return true;
		if(Item.pickaxeSteel.itemID == par1.itemID)
			return true;
		if(Item.pickaxeWood.itemID == par1.itemID)
			return true;
		if(Item.pickaxeStone.itemID == par1.itemID)
			return true;
		if(Item.axeDiamond.itemID == par1.itemID)
			return true;
		if(Item.axeGold.itemID == par1.itemID)
			return true;
		if(Item.axeSteel.itemID == par1.itemID)
			return true;
		if(Item.axeWood.itemID == par1.itemID)
			return true;
		if(Item.axeStone.itemID == par1.itemID)
			return true;
		if(Item.shovelDiamond.itemID == par1.itemID)
			return true;
		if(Item.shovelGold.itemID == par1.itemID)
			return true;
		if(Item.shovelSteel.itemID == par1.itemID)
			return true;
		if(Item.shovelWood.itemID == par1.itemID)
			return true;
		if(Item.shovelStone.itemID == par1.itemID)
			return true;
		return false;
	}

}
