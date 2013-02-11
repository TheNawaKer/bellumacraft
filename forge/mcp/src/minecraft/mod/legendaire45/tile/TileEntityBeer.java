package mod.legendaire45.tile;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.recipe.BeerRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityBeer extends TileEntity implements IInventory, ISidedInventory
{

    public TileEntityBeer()
    {
        this.BeerItemStacks = new ItemStack[3];
        LevelBeer = 0;
        LevelBeerdose = 0;
        currentItemCoolTime = 0;
        BeerCoolTime = 0;
        level = 10; //nombre de dose, de verre, de mug!!
        vitesse = 105; //nombre de dose, de verre, de mug!!
    }

    public int getSizeInventory()
    {
        return this.BeerItemStacks.length;
    }

    public ItemStack getStackInSlot(int var1)
    {
        return this.BeerItemStacks[var1];
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(this.BeerItemStacks[i] != null)
        {
            if(this.BeerItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = this.BeerItemStacks[i];
                this.BeerItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.BeerItemStacks[i].splitStack(j);
            if(this.BeerItemStacks[i].stackSize == 0)
            {
                this.BeerItemStacks[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.BeerItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "Beer";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        this.BeerItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("SlotBeer");
            if(byte0 >= 0 && byte0 < this.BeerItemStacks.length)
            {
                this.BeerItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        LevelBeer = nbttagcompound.getShort("BeereTime");
        BeerCoolTime = nbttagcompound.getShort("CoolTime");
        currentItemCoolTime = getItemBeereTime(this.BeerItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BeereTime", (short)LevelBeer);
        nbttagcompound.setShort("CoolTime", (short)BeerCoolTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.BeerItemStacks.length; i++)
        {
            if(this.BeerItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("SlotBeer", (byte)i);
                this.BeerItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCoolProgressScaled(int i)
    {
        return (BeerCoolTime * i) / 200;
    }

    public int getBeereTimeRemainingScaled(int i)
    {
        if(currentItemCoolTime == 0)
        {
            currentItemCoolTime = 200;
        }
        return LevelBeer;
    }

	public int Getlevel()
    {
    	return level;
    }

    public boolean isBeering()
    {
        return LevelBeer > 0;
    }

    public void updateEntity()
    {
        boolean flag = LevelBeer > 0;
        boolean flag1 = false;
        
        if (!this.worldObj.isRemote)
        {
            if(LevelBeer == 0 && canSolidify())
            {
                currentItemCoolTime = LevelBeer = getItemBeereTime(this.BeerItemStacks[1]);
                if(LevelBeer > 0)
                {
                    flag1 = true;
                    if(this.BeerItemStacks[1] != null)
                    {
                        if(this.BeerItemStacks[1].getItem().hasContainerItem())
                        {
                        	this.BeerItemStacks[1] = new ItemStack(this.BeerItemStacks[1].getItem().getContainerItem());
                        } else
                        {
                            this.BeerItemStacks[1].stackSize--;
                        }
                        if(this.BeerItemStacks[1].stackSize == 0)
                        {
                        	this.BeerItemStacks[1] = null;
                        }
                    }
                }
            }
        
            if(isBeering() && canSolidify())
            {
                BeerCoolTime++;
                if(BeerCoolTime == vitesse)
                {
                    BeerCoolTime = 0;
                    solidifyItem();
                    if(LevelBeer > 0)
                    {
                        LevelBeer--;
                    }
                    flag1 = true;
                }
            } else
            {
                BeerCoolTime = 0;
            }
        if(flag != (LevelBeer > 0))
        {
            flag1 = true;
        }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
        
    }

    private boolean canSolidify()
    {
        if(this.BeerItemStacks[0] == null)
        {
            return false;
        }
        ItemStack itemstack = BeerRecipe.solidifying().getSolidifyingResult(this.BeerItemStacks[0]);
        if(itemstack == null)
        {
            return false;
        }
        if(this.BeerItemStacks[2] == null)
        {
            return true;
        }
        if(!this.BeerItemStacks[2].isItemEqual(itemstack))
        {
            return false;
        }
        if(this.BeerItemStacks[2].stackSize < getInventoryStackLimit() && this.BeerItemStacks[2].stackSize < this.BeerItemStacks[2].getMaxStackSize())
        {
            return true;
        }
        return this.BeerItemStacks[2].stackSize < itemstack.getMaxStackSize();
    }

    public void solidifyItem()
    {
        if(!canSolidify())
        {
            return;
        }
        ItemStack itemstack = BeerRecipe.solidifying().getSolidifyingResult(this.BeerItemStacks[0]);
        if(this.BeerItemStacks[2] == null)
        {
            this.BeerItemStacks[2] = itemstack.copy();
        } else
        if(this.BeerItemStacks[2].itemID == itemstack.itemID)
        {
            this.BeerItemStacks[2].stackSize += itemstack.stackSize;
        }
        if(this.BeerItemStacks[0].getItem().hasContainerItem())
        {
            this.BeerItemStacks[0] = new ItemStack(this.BeerItemStacks[0].getItem().getContainerItem());
        } else
        {
            this.BeerItemStacks[0].stackSize--;
        }
        if(this.BeerItemStacks[0].stackSize <= 0)
        {
            this.BeerItemStacks[0] = null;
        }
    }

    private static int getItemBeereTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().itemID;
        if (i == mod_retrogame.BucketBeer.itemID)
        {
                return level;
        }
        else
        {
                return 0;
        }
    }
    
    public static boolean isItemSolid(ItemStack par0ItemStack)
    {
        return getItemBeereTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}
    
    
   public ItemStack getStackInSlotOnClosing(int var1)
   {
       if (this.BeerItemStacks[var1] != null)
       {
           ItemStack var2 = this.BeerItemStacks[var1];
           this.BeerItemStacks[var1] = null;
           return var2;
       }
       else
       {
           return null;
       }
   }

    private ItemStack BeerItemStacks[];
    public int LevelBeer;
    public int LevelBeerdose;
    public int currentItemCoolTime;
    public int BeerCoolTime;
    public static int level;
    public int vitesse;
    
	@Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN) return 1;
        if (side == ForgeDirection.UP) return 0; 
        return 2;
    }

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
        return 1;
	}
}