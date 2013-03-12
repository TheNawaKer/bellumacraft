package mod.legendaire45.render.item;

import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.model.ModelCheese;
import mod.legendaire45.model.ModelMoule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRendererCheese implements IItemRenderer {

	protected ModelCheese model;
	protected ModelMoule model2;
	private static String cheese = CommonProxy.cheese;
	private static String moule = CommonProxy.moule1;
	private int var;

	public ItemRendererCheese(int render)
	{
		switch(render)
		{
			case 0:
				model = new ModelCheese();
				break;
			case 1:
				model2 = new ModelMoule();
				break;
		}
		this.var = render;
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch(type)
		{
			case EQUIPPED: return true;
			default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		switch(type)
		{
			case EQUIPPED:
			{
				GL11.glPushMatrix();
				switch(var)
				{
				case 0:
					ForgeHooksClient.bindTexture(cheese,0);
					break;
				case 1:
					ForgeHooksClient.bindTexture(moule,0);
					break;
				}


				
				boolean isFirstPerson = false;
				if(data[1] != null && data[1] instanceof EntityPlayer)
				{
					if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
					{
						GL11.glRotatef(-40, 1.0F, 0.0F, 0.0F);
						GL11.glRotatef(-40, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(25, 0.0F, 0.0F, 1.0F);		
						if(var==0)
						GL11.glTranslatef((float) 0.5F, (float) -1.6F, (float) 0F );
						else
						GL11.glTranslatef((float) 0.5F, (float) -3.5F, (float) 0F );
					}
					else
					{
						GL11.glRotatef(55, 0.0F, 0.0F, 1.0F);
						GL11.glRotatef(60, 0.0F, 1.0F, 0.0F);
						GL11.glTranslatef(0,-2,1);
					}
				}
				switch(var)
				{
					case 0:
						model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
						break;
					case 1:
						model2.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
						break;
				}
				ForgeHooksClient.unbindTexture();
				GL11.glPopMatrix();
			}
			default:
				break;
		}

	}

}