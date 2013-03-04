package mod.legendaire45.items;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpawnEgg extends Item {

	private int id;
	public SpawnEgg(int par1,int par2) {
        super(par1);
        //this.setHasSubtypes(true);
        System.out.println(par2);
        this.id=par2;
        this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        return "Oeuf de Cheval";
    }
    
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            int var11 = par3World.getBlockId(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double var12 = 0.0D;

            if (par7 == 1 && Block.blocksList[var11] != null && Block.blocksList[var11].getRenderType() == 11)
            {
                var12 = 0.5D;
            }

            if (spawnCreature(par3World, this.id, (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D) != null && !par2EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }

            return true;
        }
    }
    
    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
    {
    	Entity var8 = EntityList.createEntityByID(par1, par0World);
        if (var8 != null && var8 instanceof EntityLiving)
        {
            EntityLiving var10 = (EntityLiving)var8;
            var8.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
            var10.rotationYawHead = var10.rotationYaw;
            var10.renderYawOffset = var10.rotationYaw;
            var10.initCreature();
            par0World.spawnEntityInWorld(var8);
            var10.playLivingSound();
        }
        return var8;
    }

}
