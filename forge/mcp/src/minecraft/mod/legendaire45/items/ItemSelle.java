package mod.legendaire45.items;

import mod.legendaire45.entity.mobs.EntityCheval;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;

public class ItemSelle extends ItemSaddle
{

	public ItemSelle(int par1)
	{
		super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
    /**
     * dye sheep, place saddles, etc ...
     */
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
    {
        if (par2EntityLiving instanceof EntityPig)
        {
            EntityPig var3 = (EntityPig)par2EntityLiving;

            if (!var3.getSaddled() && !var3.isChild())
            {
                var3.setSaddled(true);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else if (par2EntityLiving instanceof EntityCheval)
        {
        	EntityCheval var3 = (EntityCheval)par2EntityLiving;

            if (!var3.getSaddled() && !var3.isChild())
            {
                var3.setSaddled(true);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

}
