package net.minecraft.enchantment;

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class EnchantmentDurability extends Enchantment {

   protected EnchantmentDurability(int p_i3707_1_, int p_i3707_2_) {
      super(p_i3707_1_, p_i3707_2_, EnumEnchantmentType.digger);
      this.func_77322_b("durability");
   }

   public int func_77321_a(int p_77321_1_) {
      return 5 + (p_77321_1_ - 1) * 8;
   }

   public int func_77317_b(int p_77317_1_) {
      return super.func_77321_a(p_77317_1_) + 50;
   }

   public int func_77325_b() {
      return 3;
   }

   public boolean func_92037_a(ItemStack p_92037_1_) {
      return p_92037_1_.func_77984_f()?true:super.func_92037_a(p_92037_1_);
   }

   public static boolean func_92045_a(ItemStack p_92045_0_, int p_92045_1_, Random p_92045_2_) {
      return p_92045_0_.func_77973_b() instanceof ItemArmor && p_92045_2_.nextFloat() < 0.6F?false:p_92045_2_.nextInt(p_92045_1_ + 1) > 0;
   }
}
