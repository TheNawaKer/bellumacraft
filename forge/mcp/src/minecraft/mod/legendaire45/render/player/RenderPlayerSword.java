package mod.legendaire45.render.player;

import mod.legendaire45.network.player.PlayerInfoSender;
import mod.legendaire45.render.ItemToolEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.RenderPlayerAPI;
import net.minecraft.src.RenderPlayerBase;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderPlayerSword extends RenderPlayerBase
{	
	public RenderPlayerSword(RenderPlayerAPI renderPlayerAPI)
	{
		super(renderPlayerAPI);
	}
	
	public final void beforeRenderSpecialItemInHand(EntityPlayer par1EntityPlayer, float var2)
    {
        if(par1EntityPlayer.tool != null)
        {
	        if(par1EntityPlayer.select != 0)
	        {
		        ItemStack var41 = par1EntityPlayer.tool;
		        if(ItemToolEnum.isTool(var41))
		        {
			        float var40 = 0.625F;
			        GL11.glPushMatrix();
			        GL11.glTranslatef(0.1875F, 0.0F, 0.1875F);
			        GL11.glScalef(var40, -var40, var40);
	                if (par1EntityPlayer.isSneaking())
	                {
		            GL11.glRotatef(-220.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X	
	                }else
	                {
	                GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X
	                }
			        GL11.glRotatef(310.0F, 0.0F, 1.0F, 0.0F);//Axe Y
			        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);//Axe Z
			        this.renderPlayer.renderManager.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, var41, 0);
			        GL11.glPopMatrix();
		        }
	        }
        }
    }
}
