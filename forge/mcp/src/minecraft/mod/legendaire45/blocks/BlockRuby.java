package mod.legendaire45.blocks;

import java.util.Random;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;


public class BlockRuby extends Block 
{
    public BlockRuby(int var1, int var2, Material var3)
    {
        super(var1, var2, var3);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return mod_retrogame.rubyGem.itemID;
    }
    
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
    	this.dropXpOnBlockBreak(world, x, y, z, 25);
    }
}
