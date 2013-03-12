package mod.legendaire45.blocks;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Direction;

public class BlockBedColor extends BlockBed
{

    public BlockBedColor(int par1, int par2)
    {
        super(par1);
        this.blockIndexInTexture=par2;
        this.setRequiresSelfNotify();
    }

	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par1 == 0)
        {
            return Block.planks.blockIndexInTexture;
        }
        else
        {
            int var3 = getDirection(par2);
            int var4 = Direction.bedDirection[var3][par1];
            return isBlockHeadOfBed(par2) ? (var4 == 2 ? this.blockIndexInTexture + 2 + 16 : (var4 != 5 && var4 != 4 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture + 1 + 16)) : (var4 == 3 ? this.blockIndexInTexture - 1 + 16 : (var4 != 5 && var4 != 4 ? this.blockIndexInTexture : this.blockIndexInTexture + 16));
        }
    }

}
