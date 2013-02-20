package mod.legendaire45.gui;

import mod.legendaire45.recipe.BeerRecipe;
import mod.legendaire45.tile.TileEntityBeer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerBeer extends Container
{
    private TileEntityBeer Beer;
    private World worldObj;
    private int coolTime = 0;
    private int BeereTime = 0;
    private int itemBeereTime = 0;
    private int level = 0;

	public ContainerBeer(TileEntityBeer tileentitybeer, InventoryPlayer player_inventory, World world)

	{

        this.worldObj = world;
        this.Beer = tileentitybeer;
        this.addSlotToContainer(new Slot(tileentitybeer, 0, 37, 8));
        this.addSlotToContainer(new Slot(tileentitybeer, 1, 37, 62));
        this.addSlotToContainer(new SlotBeer(player_inventory.player, tileentitybeer, 2, 109, 35));
        int var3;
        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(player_inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(player_inventory, var3, 8 + var3 * 18, 142));
        }
    }


    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.coolTime != this.Beer.BeerCoolTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.Beer.BeerCoolTime);
            }

            if (this.BeereTime != this.Beer.LevelBeer)
            {
                var2.sendProgressBarUpdate(this, 1, this.Beer.LevelBeer);
            }

            if (this.itemBeereTime != this.Beer.currentItemCoolTime)
            {
                var2.sendProgressBarUpdate(this, 2, this.Beer.currentItemCoolTime);
            }
        }

        this.coolTime = this.Beer.BeerCoolTime;
        this.BeereTime = this.Beer.LevelBeer;
        this.itemBeereTime = this.Beer.currentItemCoolTime;
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.Beer.BeerCoolTime = var2;
        }

        if (var1 == 1)
        {
            this.Beer.LevelBeer = var2;
        }

        if (var1 == 2)
        {
            this.Beer.currentItemCoolTime = var2;
        }
    }
    
    /*
    public ItemStack slotClick(int var1, int var2, boolean var3, EntityPlayer var4)
    {
        return super.slotClick(var1, var2, var3, var4);
    }*/

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.Beer.isUseableByPlayer(var1);
    }

    
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
    {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack())
        {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 == 2)
            {
                if (!this.mergeItemStack(var4, 3, 39, true))
                {
                    return null;
                }

                var3.onSlotChange(var4, var2);
            }
            else if (par1 != 1 && par1 != 0)
            {
                if (BeerRecipe.solidifying().getSolidifyingResult(var4) != null)
                {
                    if (!this.mergeItemStack(var4, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityBeer.isItemSolid(var4))
                {
                    if (!this.mergeItemStack(var4, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par1 >= 3 && par1 < 30)
                {
                    if (!this.mergeItemStack(var4, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var4, 3, 39, false))
            {
                return null;
            }

            if (var4.stackSize == 0)
            {
                var3.putStack((ItemStack)null);
            }
            else
            {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize)
            {
                return null;
            }

            var3.onPickupFromSlot(par1EntityPlayer, var4);
        }
        System.out.println("slot var2");
        System.out.println(var2);
        System.out.println("slot var3");
        System.out.println(var3);
        return var2;
    }

    public void onCraftGuiClosed(EntityPlayer var1)
    {
        InventoryPlayer var2 = var1.inventory;

        if (var2.getItemStack() != null)
        {
            var1.dropPlayerItem(var2.getItemStack());
            var2.setItemStack((ItemStack)null);
        }
    }
}
