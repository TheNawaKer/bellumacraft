package mod.legendaire45.items;

import mod.legendaire45.mod_retrogame;
import net.minecraft.item.ItemBlock;

public class ItemMoule extends ItemBlock
{
    public ItemMoule(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public int getIconFromDamage(int i)
    {
        return mod_retrogame.moule.getBlockTextureFromSideAndMetadata(2, i);
    }

    public int getMetadata(int i)
    {
        return i;
    }
}
