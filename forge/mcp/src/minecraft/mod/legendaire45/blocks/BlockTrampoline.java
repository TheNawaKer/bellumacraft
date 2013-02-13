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
    
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6)
    {		
    	if( tentity == null ) 
    	 {			
    	tentity = entity;			
    	tblock = entity.fallDistance;	
    	System.out.println("tblock OFU:"); 
    	System.out.println(tblock); 
    	} 
    	else world.scheduleBlockUpdate(x, y, z, blockID, 1);
    	entity.fallDistance = 0;	
    }
    
    public void updateTick(World world, int x, int y, int z, Random random)
    {		
    	if( tentity != null )
    	{
        System.out.println("Entity(avant null):"); 
        System.out.println(tentity);
        System.out.println("server tblock:"); 
        System.out.println(tblock);
    	float step = 0;			
    	if( tentity.isSneaking() ) 
    	 step = tblock/6;			
    	if( step > 1 ) 
    	 step = tblock/10;			
    	if( step > 3 ) 
    	 step = tblock/15;			
    	if( step > 6 ) 
    	 step = tblock/20;			
    	 tentity.motionY = 1 + step;
    	 System.out.println("motiony:"); 
    	 System.out.println(tentity.motionY);
         System.out.println("Entity(y):"); 
         System.out.println(tentity);
    	 tentity = null;		
    	 }
    	System.out.println("Entity:"); 
    	System.out.println(tentity);
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