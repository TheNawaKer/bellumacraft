package mod.legendaire45.render;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.model.ModelMoule;
import mod.legendaire45.tile.TileEntityMoule;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;


public class RenderMoule extends TileEntitySpecialRenderer {  
		
		private ModelMoule model = new ModelMoule();
		
		private static String moule1 = CommonProxy.moule1;
		private static String moule2 = CommonProxy.moule2;
		private static String moule3 = CommonProxy.moule3;
		private static String moule4 = CommonProxy.moule4;
	    
		public void renderTileEntityTileAt(TileEntity tileEntity, double x, double y, double z, float par8) 
		{
			Block block = tileEntity.getBlockType();
			int metadata = tileEntity.getBlockMetadata();  
		    GL11.glPushMatrix();
		    mod_retrogame.moule.setBlockBounds(0.0F, 0F, 0.0F, 1F, 0.70F, 1F);
		    GL11.glTranslatef((float) x + 0.5F, (float) y - 2.5F, (float) z +0.5F );
		    switch(metadata)
		    {
			    case 0:
			    	 bindTextureByName(moule1);
			    	 break;
			    case 1:
			    	 bindTextureByName(moule2);
			    	 break;
			    case 2:
			    	 bindTextureByName(moule3);
			    	 break;
			    case 3:
			    	 bindTextureByName(moule4);
			    	 break;
		    }
		   
		    GL11.glPushMatrix();
		    model.renderAll();
		    GL11.glPopMatrix();
		    GL11.glPopMatrix(); 
		}
		
	    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	    {
	        this.renderTileEntityTileAt((TileEntityMoule)par1TileEntity, par2, par4, par6, par8);
	    }
}