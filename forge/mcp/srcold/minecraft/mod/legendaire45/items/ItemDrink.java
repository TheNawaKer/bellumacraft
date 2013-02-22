package mod.legendaire45.items;

import java.util.ArrayList;
import java.util.Random;

import mod.legendaire45.mod_retrogame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDrink extends ItemFood 
{
    private int burp;
    private int burppow;
    private int confu;
    private int power;
    private float potionEffectProbability;

    public ItemDrink(int var1, int var2, float var3, boolean var4)
    {
        super(var1, var2, var4);
        this.maxStackSize = 64;
        this.burp = 0;
        this.confu = 0;
        this.power = 0;
        Random var5 = new Random();
        int var6 = var5.nextInt(4);
        int var7 = var5.nextInt(2);
    }

    public ItemDrink(int var1, int var2, boolean var3)
    {
        this(var1, var2, 0.6F, var3);
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.drink;
    }

    public ItemStack onFoodEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        super.onFoodEaten(var1, var2, var3);

        if (this.burp == this.confu + 4)
        {
            var3.addPotionEffect(new PotionEffect(Potion.confusion.id, 1200, 0));
            this.confu = 0;
            this.burp = 0;
        }

        if (this.burppow == this.power + 2)
        {
            var3.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 0));
            this.power = 0;
            this.burppow = 0;
        }

        ++this.burp;
        ++this.burppow;
        var3.inventory.addItemStackToInventory(new ItemStack(mod_retrogame.Cup));
        return var1;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 32;
    }

    public void addCreativeItems(ArrayList var1)
    {
        var1.add(new ItemStack(this));
    }
}
