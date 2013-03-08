package mod.legendaire45.render;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.model.ModelCheese;
import mod.legendaire45.tile.TileEntityCheese;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;


public class RenderCheese extends TileEntitySpecialRenderer {  
		
		private ModelCheese model = new ModelCheese();
		
		private RenderBlocks blockRenderer = new RenderBlocks();
		
		private static String cheese = CommonProxy.cheese;
		
		private Minecraft mc = FMLClientHandler.instance().getClient();
	    
		public void renderTileEntityTileAt(TileEntity tileEntity, double x, double y, double z, float par8) 
		{
			Block block = tileEntity.getBlockType();
			int metadata = tileEntity.getBlockMetadata();  
			System.out.println(metadata);
		    GL11.glPushMatrix();
		    mod_retrogame.cheese.setBlockBounds(0.0F, 0F, 0.0F, 1F, 0.60F, 1F);
		    GL11.glTranslatef((float) x+0.5F, (float) y-1.1F, (float) z+0.5F );
		    bindTextureByName(cheese);
		    GL11.glPushMatrix();
		    model.renderPart(metadata);		    
		    GL11.glPopMatrix();
		    GL11.glPopMatrix(); 
		}
		
	    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	    {
	        this.renderTileEntityTileAt((TileEntityCheese)par1TileEntity, par2, par4, par6, par8);
	    }
}