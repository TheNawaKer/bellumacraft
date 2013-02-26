package mod.legendaire45.entity.mobs;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;



public class EntityCheval extends EntityMob
{
    public EntityCheval(World world)
    {
        super(world);
        texture = "/mod/Raptor.png";
        moveSpeed = 1.0F;                   // réglez ca vitesse ici
        tasks.addTask(0, new EntityAISwimming(this));    
        tasks.addTask(1, new EntityAIWander(this, moveSpeed));
        tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(6, new EntityAIWander(this, 0.16F));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }



    public int getMaxHealth()
    {
        return 30;                       // ici sa vie
    }

    public boolean interact(EntityPlayer entityplayer)     // cette fonction sert a mettre une interaction avec le joueur, on pourra le monter :) en mettant sur true la fonction private boolean suivante.
    {
        if (!super.interact(entityplayer))
        {
            if (getSaddled() && !worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer))
            {
                entityplayer.mountEntity(this);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }



    private boolean getSaddled() 
    {
		return true;
	}
    
    protected boolean isAIEnabled()  
    {
    	return true;
    }



	public float getBlockPathWeight(int i, int j, int k)
    {
        return worldObj.getLightBrightness(i, j, k) - 0.5F;
    }

}

