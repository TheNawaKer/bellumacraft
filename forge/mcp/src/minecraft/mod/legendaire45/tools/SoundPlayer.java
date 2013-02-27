package mod.legendaire45.tools;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SoundPlayer 
{
	
	  public static void playCustomSound(Entity entity, String customSound, World worldObj)
	  {
	    worldObj.playSoundAtEntity(entity, customSound, 1.0F, 1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F);
	  }

	  public static void playCustomSound(Entity entity, String customSound, World worldObj, float volume)
	  {
	    worldObj.playSoundAtEntity(entity, customSound, volume, 1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F);
	  }

}
