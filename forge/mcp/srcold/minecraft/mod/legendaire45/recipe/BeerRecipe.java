package mod.legendaire45.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mod.legendaire45.mod_retrogame;
import net.minecraft.item.ItemStack;

public class BeerRecipe
{
    private static final BeerRecipe solidifyingBase = new BeerRecipe();
    private Map solidifyingList = new HashMap();
    private Map metasolidifyingList = new HashMap();

    public static final BeerRecipe solidifying()
    {
        return solidifyingBase;
    }

    private BeerRecipe()
    {
        this.addSolidifying(mod_retrogame.Cup.itemID, new ItemStack(mod_retrogame.CupBeer));
    }

    public void addSolidifying(int var1, ItemStack var2)
    {
        this.solidifyingList.put(Integer.valueOf(var1), var2);
    }
    
    @Deprecated
    public ItemStack getSolidifyingResult(int var1)
    {
        return (ItemStack)this.solidifyingList.get(Integer.valueOf(var1));
    }
    
    public Map getSolidifyingList()
    {
        return this.solidifyingList;
    }
    
    public void addSolidifying(int itemID, int metadata, ItemStack itemstack)
    {
        metasolidifyingList.put(Arrays.asList(itemID, metadata), itemstack);
    }
    
    public ItemStack getSolidifyingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metasolidifyingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)solidifyingList.get(Integer.valueOf(item.itemID));
    }


}
