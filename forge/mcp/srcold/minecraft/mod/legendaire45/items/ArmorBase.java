package mod.legendaire45.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.legendaire45.mod_retrogame;
import mod.legendaire45.common.CommonProxy;
import net.minecraft.item.EnumArmorMaterial; 
import net.minecraft.item.ItemArmor; 
import net.minecraft.item.ItemStack; 
import net.minecraftforge.common.IArmorTextureProvider; 

public class ArmorBase extends ItemArmor implements IArmorTextureProvider 
{ 
	private static String textureEmerald = CommonProxy.textureEmerald;
	private static String textureEmerald2 = CommonProxy.textureEmerald2;
	private static String textureSaphir = CommonProxy.textureSaphir;
	private static String textureSaphir2 = CommonProxy.textureSaphir2;
	private static String textureRuby = CommonProxy.textureRuby;
	private static String textureRuby2 = CommonProxy.textureRuby2;
	private static String lunettea = CommonProxy.lunettea;
	private static String lunetteb = CommonProxy.lunetteb;
	private static String lunettec = CommonProxy.lunettec;
	private static String textureItem = CommonProxy.textureItem;

    public ArmorBase(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    public String getArmorTextureFile(ItemStack par1Armor)
    { 
        if(par1Armor.itemID == mod_retrogame.ArmorE1.itemID || par1Armor.itemID == mod_retrogame.ArmorE2.itemID || par1Armor.itemID == mod_retrogame.ArmorE4.itemID) 
        { 
            return textureEmerald; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorE3.itemID) 
        { 
            return textureEmerald2; 
        }
        
        if(par1Armor.itemID == mod_retrogame.ArmorS1.itemID || par1Armor.itemID == mod_retrogame.ArmorS2.itemID || par1Armor.itemID == mod_retrogame.ArmorS4.itemID) 
        { 
            return textureSaphir; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorS3.itemID) 
        { 
            return textureSaphir2; 
        }
        
        if(par1Armor.itemID == mod_retrogame.ArmorR1.itemID || par1Armor.itemID == mod_retrogame.ArmorR2.itemID || par1Armor.itemID == mod_retrogame.ArmorR4.itemID) 
        { 
            return textureRuby; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorR3.itemID) 
        { 
            return textureRuby2; 
        }
        if(par1Armor.itemID == mod_retrogame.lunette1.itemID) 
        { 
            return lunettea; 
        } 
        if(par1Armor.itemID == mod_retrogame.lunette2.itemID) 
        { 
            return lunetteb; 
        } 
        if(par1Armor.itemID == mod_retrogame.lunette3.itemID) 
        { 
            return lunettec; 
        } 
        
        return textureItem; 
    }
    
    public String getTextureFile()
    {
    return textureItem; 

    }  
}