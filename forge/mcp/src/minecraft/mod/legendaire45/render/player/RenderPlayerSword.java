package mod.legendaire45.render.player;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.render.ItemToolEnum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.RenderPlayerAPI;
import net.minecraft.src.RenderPlayerBase;

import org.lwjgl.opengl.GL11;

public class RenderPlayerSword extends RenderPlayerBase
{			
	public RenderPlayerSword(RenderPlayerAPI renderPlayerAPI)
	{
		super(renderPlayerAPI);
	}
	
	public final void beforeRenderSpecialItemInHand(EntityPlayer par1EntityPlayer, float var2)
    {
		ItemStack zero = par1EntityPlayer.getDataWatcher().getWatchableObjectItemStack(26);
        if(zero != null)
        {
	        if(par1EntityPlayer.getDataWatcher().getWatchableObjectInt(25) != 0)
	        {
		        if(ItemToolEnum.isWeapon(zero))
		        {
			        float var40 = 0.625F;
			        GL11.glPushMatrix();
			        GL11.glTranslatef(0.1875F, 0.0F, 0.1875F);
			        GL11.glScalef(var40, -var40, var40);
	                if (par1EntityPlayer.isSneaking())
	                {
	                	GL11.glRotatef(-208.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X	
	                }else
	                {
	                	GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X
	                }
			        GL11.glRotatef(310.0F, 0.0F, 1.0F, 0.0F);//Axe Y
			        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);//Axe Z
			        if(zero.itemID==Item.bow.itemID)
			        {
			        	RenderManager.instance.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, new ItemStack(mod_retrogame.bowfix, 1), 0);
			        }
			        else
			        {
			        	RenderManager.instance.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, zero, 0);
			        }
			        GL11.glPopMatrix();
		        }
	        }
        }
        ItemStack un = par1EntityPlayer.getDataWatcher().getWatchableObjectItemStack(27);
        if(un != null)
        {
	        if(par1EntityPlayer.getDataWatcher().getWatchableObjectInt(25) != 1)
	        {
		        if(ItemToolEnum.isWeapon(un))
		        {
			        float var40 = 0.625F;
			        GL11.glPushMatrix();
		        	GL11.glTranslatef(-0.1875F, 0.0F, 0.2750F);
		        	GL11.glScalef(-var40, -var40, -var40);	
	                if (par1EntityPlayer.isSneaking())
	                {
	                	GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X	
	                }else
	                {
	                	GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X
	                }
			        GL11.glRotatef(310.0F, 0.0F, 1.0F, 0.0F);//Axe Y
			        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);//Axe Z
			        if(un.itemID==Item.bow.itemID)
			        {
			        	RenderManager.instance.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, new ItemStack(mod_retrogame.bowfix, 1), 0);
			        }
			        else
			        {
			        	RenderManager.instance.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, un, 0);
			        }
			        GL11.glPopMatrix();
		        }
	        }
        }
    }

	
}
