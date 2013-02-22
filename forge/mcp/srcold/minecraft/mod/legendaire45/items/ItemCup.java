package mod.legendaire45.items;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCup extends Item 
{
    public ItemCup(int var1)
    {
        super(var1);
        this.maxStackSize = 64;
    }

    public void addCreativeItems(ArrayList var1)
    {
        var1.add(new ItemStack(this));
    }
}
