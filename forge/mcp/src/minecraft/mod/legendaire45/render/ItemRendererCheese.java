package mod.legendaire45.render;

import org.lwjgl.opengl.GL11;

import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.model.ModelCheese;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererCheese implements IItemRenderer {

	protected ModelCheese model;
	private static String cheese = CommonProxy.cheese;

	public ItemRendererCheese()
	{
		model = new ModelCheese();
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
				ForgeHooksClient.bindTexture(cheese,0);
				GL11.glRotatef(-45F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(70F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-25F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef((float) 0.6F, (float) -1.2F, (float) 0F );
				
				model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				ForgeHooksClient.unbindTexture();
				GL11.glPopMatrix();
			}
			default:
				break;
		}

	}

}