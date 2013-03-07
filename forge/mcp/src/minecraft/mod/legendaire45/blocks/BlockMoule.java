package mod.legendaire45.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mod.legendaire45.tile.TileEntityMoule;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMoule extends BlockContainer
{
	final int timer = 1200; 
	private int time = timer;
	
    public BlockMoule (int par1, int par2, Material material)
    {
        super(par1, par2, material);
        this.setRequiresSelfNotify();
        this.setTickRandomly(true);
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    } 
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t)
	{
    	int meta = world.getBlockMetadata(x, y, z);
    	ItemStack item = player.inventory.getCurrentItem();
    	if(item == null && meta!=3)
    	{
    		return true;
    	}
    	
    	if(meta==3)
    	{
    		world.setBlockMetadataWithNotify(x, y, z, 0);
    	}
    	else if(meta==0 && item.itemID== Item.bucketMilk.itemID)
    	{
    		world.setBlockMetadataWithNotify(x, y, z, 1);
    		world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate());
    	}
		return true;
	}
    
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        	int var7 = world.getBlockMetadata(i, j, k);
            super.updateTick(world, i, j, k, random);
            if (var7==1)
            {            	
                if(this.time == 0)
                {
                    world.setBlockMetadataWithNotify(i, j, k, var7+1);   
                    this.time = timer;
                }
                this.time--;
                world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate());
            }
            else if (var7==2 && world.getBlockLightValue(i,j+1,k) <= 9)
            {            	
                if(this.time == 0)
                {
                    world.setBlockMetadataWithNotify(i, j, k, var7+1);   
                    this.time = timer;
                }
                this.time--;
                world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate());
            }
            
    }
   
    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 20;
    }
    
	@Override
	public int damageDropped (int metadata)
	{
		return metadata;
	}
    
    public int idDropped(int var1, Random var2)
    {
        return this.blockID;
    }
    
    public int quantityDropped(Random par1Random)
    {
        return 1;
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
        return 52; 
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
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityMoule();
    }
}