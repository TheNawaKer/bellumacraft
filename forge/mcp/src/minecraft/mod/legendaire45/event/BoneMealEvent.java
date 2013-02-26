package mod.legendaire45.event;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.blocks.BlockCropBeer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BoneMealEvent
{
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.ID == mod_retrogame.beer.blockID)
			{
				((BlockCropBeer) mod_retrogame.beer).fertilize(event.world, event.X, event.Y, event.Z);
			}		 
		}
	}

}
