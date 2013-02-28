package mod.legendaire45.items;

import java.util.List;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDisc extends ItemRecord
{
	public final String recordName;
	public ItemDisc(int par1, String par2Str)
	{
		super(par1, par2Str);
		this.recordName = par2Str;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0)
		{
			if (par3World.isRemote)
			{
				return true;
			}
			else
			{
				((BlockJukeBox)Block.jukebox).insertRecord(par3World, par4, par5, par6, par1ItemStack);
				par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, this.itemID);
				--par1ItemStack.stackSize;
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (this.recordName == "mia")
		{
			par3List.add("M.I.A - " + "Paper Planes");
		}
		else if (this.recordName == "skrillex")
		{
			par3List.add("Skrillex" + " - " + this.recordName);
		}
	}
	
    /**
     * Return the title for this record.
     */
    public String getRecordTitle()
    {
		if (this.recordName == "skrillex")
		{
			return "Skrillex - " + "Skrillex";
		}
		else
		{
			return "M.I.A - " + "Paper Planes";
		}
    }
}
