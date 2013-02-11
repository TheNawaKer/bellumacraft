package mod.legendaire45.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.legendaire45.mod_retrogame;
import net.minecraft.item.EnumArmorMaterial; 
import net.minecraft.item.ItemArmor; 
import net.minecraft.item.ItemStack; 
import net.minecraftforge.common.IArmorTextureProvider; 

public class ArmorBase extends ItemArmor implements IArmorTextureProvider 
{ 
    public ArmorBase(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    public String getArmorTextureFile(ItemStack par1Armor)
    { 
        if(par1Armor.itemID == mod_retrogame.ArmorE1.itemID || par1Armor.itemID == mod_retrogame.ArmorE2.itemID || par1Armor.itemID == mod_retrogame.ArmorE4.itemID) 
        { 
            return "/mod/armor/emerald_1.png"; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorE3.itemID) 
        { 
            return "/mod/armor/emerald_2.png"; 
        }
        
        if(par1Armor.itemID == mod_retrogame.ArmorS1.itemID || par1Armor.itemID == mod_retrogame.ArmorS2.itemID || par1Armor.itemID == mod_retrogame.ArmorS4.itemID) 
        { 
            return "/mod/armor/saphir_1.png"; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorS3.itemID) 
        { 
            return "/mod/armor/saphir_2.png"; 
        }
        
        if(par1Armor.itemID == mod_retrogame.ArmorR1.itemID || par1Armor.itemID == mod_retrogame.ArmorR2.itemID || par1Armor.itemID == mod_retrogame.ArmorR4.itemID) 
        { 
            return "/mod/armor/rubis_1.png"; 
        } 
        if(par1Armor.itemID == mod_retrogame.ArmorR3.itemID) 
        { 
            return "/mod/armor/rubis_2.png"; 
        }
        if(par1Armor.itemID == mod_retrogame.lunette1.itemID) 
        { 
            return "/mod/armor/lunette.png"; 
        } 
        if(par1Armor.itemID == mod_retrogame.lunette2.itemID) 
        { 
            return "/mod/armor/lunetteb.png"; 
        } 
        if(par1Armor.itemID == mod_retrogame.lunette3.itemID) 
        { 
            return "/mod/armor/lunettec.png"; 
        } 
        
        return "/mod/item.png"; 
    }
    
    public String getTextureFile()
    {
    return "/mod/item.png"; 

    }  
}