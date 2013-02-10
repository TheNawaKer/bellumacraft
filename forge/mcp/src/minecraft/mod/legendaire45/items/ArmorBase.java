package mod.legendaire45.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.legendaire45.mod_iut;
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

    @SideOnly(Side.CLIENT) //Pr�cise que le serveur n'a pas � g�rer �a 
    public String getArmorTextureFile(ItemStack par1Armor)
    { 
        if(par1Armor.itemID == mod_iut.ArmorE1.itemID || par1Armor.itemID == mod_iut.ArmorE2.itemID || par1Armor.itemID == mod_iut.ArmorE4.itemID) 
        { 
            return "/mod/armor/emerald_1.png"; 
        } 
        if(par1Armor.itemID == mod_iut.ArmorE3.itemID) 
        { 
            return "/mod/armor/emerald_2.png"; 
        }
        
        if(par1Armor.itemID == mod_iut.ArmorS1.itemID || par1Armor.itemID == mod_iut.ArmorS2.itemID || par1Armor.itemID == mod_iut.ArmorS4.itemID) 
        { 
            return "/mod/armor/saphir_1.png"; 
        } 
        if(par1Armor.itemID == mod_iut.ArmorS3.itemID) 
        { 
            return "/mod/armor/saphir_2.png"; 
        }
        
        if(par1Armor.itemID == mod_iut.ArmorR1.itemID || par1Armor.itemID == mod_iut.ArmorR2.itemID || par1Armor.itemID == mod_iut.ArmorR4.itemID) 
        { 
            return "/mod/armor/rubis_1.png"; 
        } 
        if(par1Armor.itemID == mod_iut.ArmorR3.itemID) 
        { 
            return "/mod/armor/rubis_2.png"; 
        }
        
        return "/mod/item.png"; 
    }
    
    @SideOnly(Side.CLIENT) //Pr�cise que le serveur n'a pas � g�rer �a 
    public String getTextureFile()
    {
    //Rajouter les autres armures de la m�me fa�on (exemple le copper) 
    return "/mod/item.png"; 

    }  
}