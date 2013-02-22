package mod.legendaire45.tile;

import java.util.Iterator;
import java.util.List;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;



public class TileEntitySofa extends TileEntity
{
    /** Determines if the check for adjacent Sofas has taken place. */
    public boolean adjacentSofaChecked = false;

    /** Contains the Sofa tile located adjacent to this one (if any) */
    public TileEntitySofa adjacentSofaZNeg;

    /** Contains the Sofa tile located adjacent to this one (if any) */
    public TileEntitySofa adjacentSofaXPos;

    /** Contains the Sofa tile located adjacent to this one (if any) */
    public TileEntitySofa adjacentSofaXNeg;

    /** Contains the Sofa tile located adjacent to this one (if any) */
    public TileEntitySofa adjacentSofaZPosition;
    
    
    
    private void func_90009_a(TileEntitySofa par1TileEntitySofa, int par2)
    {
        if (par1TileEntitySofa.isInvalid())
        {
            this.adjacentSofaChecked = false;
        }
        else if (this.adjacentSofaChecked)
        {
            switch (par2)
            {
                case 0:
                    if (this.adjacentSofaZPosition != par1TileEntitySofa)
                    {
                        this.adjacentSofaChecked = false;
                    }

                    break;
                case 1:
                    if (this.adjacentSofaXNeg != par1TileEntitySofa)
                    {
                        this.adjacentSofaChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentSofaZNeg != par1TileEntitySofa)
                    {
                        this.adjacentSofaChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentSofaXPos != par1TileEntitySofa)
                    {
                        this.adjacentSofaChecked = false;
                    }
            }
        }
    }
    
    /**
     * Performs the check for adjacent Sofas to determine if this Sofa is double or not.
     */
    public void checkForAdjacentSofas()
    {
        if (!this.adjacentSofaChecked)
        {
            this.adjacentSofaChecked = true;
            this.adjacentSofaZNeg = null;
            this.adjacentSofaXPos = null;
            this.adjacentSofaXNeg = null;
            this.adjacentSofaZPosition = null;

            if (this.worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord) == mod_retrogame.sofa.blockID)
            {
                this.adjacentSofaXNeg = (TileEntitySofa)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord) == mod_retrogame.sofa.blockID)
            {
                this.adjacentSofaXPos = (TileEntitySofa)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1) == mod_retrogame.sofa.blockID)
            {
                this.adjacentSofaZNeg = (TileEntitySofa)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1) == mod_retrogame.sofa.blockID)
            {
                this.adjacentSofaZPosition = (TileEntitySofa)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentSofaZNeg != null)
            {
                this.adjacentSofaZNeg.func_90009_a(this, 0);
            }

            if (this.adjacentSofaZPosition != null)
            {
                this.adjacentSofaZPosition.func_90009_a(this, 2);
            }

            if (this.adjacentSofaXPos != null)
            {
                this.adjacentSofaXPos.func_90009_a(this, 1);
            }

            if (this.adjacentSofaXNeg != null)
            {
                this.adjacentSofaXNeg.func_90009_a(this, 3);
            }
        }
    }
    
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        this.checkForAdjacentSofas();
    }

    /**
     * Reads a tile entity from NBT.
     */
	@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
    }

    /**
     * Writes a tile entity to NBT.
     */
	@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
    }

    public void openSofa(){}

    public void closeSofa(){}
	
}