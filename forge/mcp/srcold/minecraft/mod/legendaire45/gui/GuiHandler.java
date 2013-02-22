package mod.legendaire45.gui;



import cpw.mods.fml.common.network.IGuiHandler;

import mod.legendaire45.tile.TileEntityBeer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import mod.legendaire45.gui.ContainerBeer;



public class GuiHandler implements IGuiHandler

{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)

	{

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if(tile_entity instanceof TileEntityBeer)

		{

			return new ContainerBeer((TileEntityBeer) tile_entity, player.inventory, world);

		}

		

		return null;

	}

	

	@Override

	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)

	{

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		

		if(tile_entity instanceof TileEntityBeer)

		{

			return new GuiBeer(player.inventory, (TileEntityBeer) tile_entity, world);

		}

	

		return null;

	}

}