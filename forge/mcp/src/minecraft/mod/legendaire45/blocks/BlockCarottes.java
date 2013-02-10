package mod.legendaire45.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockCarottes extends Block
{

    public BlockCarottes (int par1, int par2, Material material)
    {
        super(par1, par2, material);
    }

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
   
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
}