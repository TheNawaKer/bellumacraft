package mod.legendaire45.blocks;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mod.legendaire45.tile.TileEntityBeer;
import mod.legendaire45.tile.TileEntityTrampoline;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class BlockTrampoline extends BlockContainer
{
    
    public BlockTrampoline (int par1, int par2, Material material)
    {
        super(par1, par2, material);
        slipperiness = 1.05F;
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
    
    private Entity tentity;	
    private float tblock;
    private boolean verif;
    
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6)
    {		
    	if( tentity == null ) 
    	 {			
    	tentity = entity;			
    	tblock = entity.fallDistance;	
    	} 
    	else world.scheduleBlockUpdate(x, y, z, blockID, 1);
    	entity.fallDistance = 0;	
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float var5 = 0.125F;
        return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - var5), (double)(par4 + 1));
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	
    	if(tentity!=null) 
    	{						
    	float step = 0;			
    	if( par5Entity.isSneaking() ) 
    	 step = tblock/6;			
    	if( step > 1 ) 
    	 step = tblock/10;			
    	if( step > 3 ) 
    	 step = tblock/15;			
    	if( step > 6 ) 
    	 step = tblock/20;			
    	par5Entity.motionY = 0.5F + step;
    	tentity=null;
    	}
    }
    
    public int getRenderBlockPass() 
    { 
   return 1; 
    }


    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityTrampoline();
    }
}