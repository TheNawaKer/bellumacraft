package mod.legendaire45.render;

import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.model.ModelTrampoline;
import mod.legendaire45.tile.TileEntityTrampoline;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;


public class TileEntityTrampolineRenderer extends TileEntitySpecialRenderer {  
		
		private ModelTrampoline model = new ModelTrampoline();
		
		private static String trampoline = CommonProxy.trampoline;

		public void renderTileEntityTileAt(TileEntity tileEntity, double x, double y, double z, float par8) 
		{        
			GL11.glPushMatrix();        
			GL11.glTranslatef((float)x+.5F, (float)y+.5F, (float)z+.5F);        
			this.bindTextureByName(trampoline); 
			this.model.render();
			GL11.glPopMatrix();    
		}
		
	    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	    {
	        this.renderTileEntityTileAt((TileEntityTrampoline)par1TileEntity, par2, par4, par6, par8);
	    }
}