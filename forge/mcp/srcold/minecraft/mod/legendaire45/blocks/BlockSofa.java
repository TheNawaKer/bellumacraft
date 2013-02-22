package mod.legendaire45.blocks;

import java.util.Random;

import mod.legendaire45.tile.TileEntitySofa;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockSofa extends BlockContainer
{
    
    public BlockSofa (int par1)
    {
        super(par1, Material.wood);
        this.blockIndexInTexture = 4;
    }
    
    
    
    public int idDropped(int var1, Random var2)
    {
        return this.blockID;
    }
    
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    public int getMobilityFlag()
    {
        return 0;
    }
    

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 51; 
    }
    
    /**
     * Turns the adjacent Sofas to a double Sofa.
     */
    public void unifyAdjacentSofas(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            boolean var9 = true;
            int var10;
            int var11;
            boolean var12;
            byte var13;
            int var14;

            if (var5 != this.blockID && var6 != this.blockID)
            {
                if (var7 != this.blockID && var8 != this.blockID)
                {
                    var13 = 3;

                    if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
                    {
                        var13 = 3;
                    }

                    if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
                    {
                        var13 = 2;
                    }

                    if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
                    {
                        var13 = 5;
                    }

                    if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
                    {
                        var13 = 4;
                    }
                }
                else
                {
                    var10 = par1World.getBlockId(var7 == this.blockID ? par2 - 1 : par2 + 1, par3, par4 - 1);
                    var11 = par1World.getBlockId(var7 == this.blockID ? par2 - 1 : par2 + 1, par3, par4 + 1);
                    var13 = 3;
                    var12 = true;

                    if (var7 == this.blockID)
                    {
                        var14 = par1World.getBlockMetadata(par2 - 1, par3, par4);
                    }
                    else
                    {
                        var14 = par1World.getBlockMetadata(par2 + 1, par3, par4);
                    }

                    if (var14 == 2)
                    {
                        var13 = 2;
                    }

                    if ((Block.opaqueCubeLookup[var5] || Block.opaqueCubeLookup[var10]) && !Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var11])
                    {
                        var13 = 3;
                    }

                    if ((Block.opaqueCubeLookup[var6] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var10])
                    {
                        var13 = 2;
                    }
                }
            }
            else
            {
                var10 = par1World.getBlockId(par2 - 1, par3, var5 == this.blockID ? par4 - 1 : par4 + 1);
                var11 = par1World.getBlockId(par2 + 1, par3, var5 == this.blockID ? par4 - 1 : par4 + 1);
                var13 = 5;
                var12 = true;

                if (var5 == this.blockID)
                {
                    var14 = par1World.getBlockMetadata(par2, par3, par4 - 1);
                }
                else
                {
                    var14 = par1World.getBlockMetadata(par2, par3, par4 + 1);
                }

                if (var14 == 4)
                {
                    var13 = 4;
                }

                if ((Block.opaqueCubeLookup[var7] || Block.opaqueCubeLookup[var10]) && !Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var11])
                {
                    var13 = 5;
                }

                if ((Block.opaqueCubeLookup[var8] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var10])
                {
                    var13 = 4;
                }
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var13);
        }
    }
    
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = par1World.getBlockId(par2, par3, par4 - 1);
        int var7 = par1World.getBlockId(par2, par3, par4 + 1);
        int var8 = par1World.getBlockId(par2 - 1, par3, par4);
        int var9 = par1World.getBlockId(par2 + 1, par3, par4);
        byte var10 = 0;
        int var11 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var11 == 0)
        {
            var10 = 2;
        }

        if (var11 == 1)
        {
            var10 = 5;
        }

        if (var11 == 2)
        {
            var10 = 3;
        }

        if (var11 == 3)
        {
            var10 = 4;
        }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var10);
        
        // vu qu'un block complexe � souvent un sens, on va en profiter pour lui attribuer des metaData � la pose qui definiront son orientation.
    }


    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntitySofa();
    }
}