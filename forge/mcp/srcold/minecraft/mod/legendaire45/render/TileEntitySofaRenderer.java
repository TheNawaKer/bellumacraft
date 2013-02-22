package mod.legendaire45.render;
 
import mod.legendaire45.blocks.BlockSofa;
import mod.legendaire45.model.ModelCanape;
import mod.legendaire45.tile.TileEntitySofa;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
 
public class TileEntitySofaRenderer extends TileEntitySpecialRenderer
{
    private ModelCanape tileModel = new ModelCanape(); //on innitialise un instance de notre Model pour pouvoir l'appliquer
 
    private boolean field_92061_d;
 
    public TileEntitySofaRenderer(){}// on créer un constructeur (vide car on n'as pas besoin de variables particulières)
 
    /**
     * Renders the TileEntity for the Sofa at a position.
     */
    public void renderTileEntitySofaAt(TileEntitySofa par1TileEntitySofa, double par2, double par4, double par6, float par8)
    {
        int var9;

        if (!par1TileEntitySofa.func_70309_m())
        {
            var9 = 0;
        }
        else
        {
            Block var10 = par1TileEntitySofa.getBlockType();
            var9 = par1TileEntitySofa.getBlockMetadata();

            if (var10 != null && var9 == 0)
            {
                ((BlockSofa)var10).unifyAdjacentSofas(par1TileEntitySofa.getWorldObj(), par1TileEntitySofa.xCoord, par1TileEntitySofa.yCoord, par1TileEntitySofa.zCoord);
                var9 = par1TileEntitySofa.getBlockMetadata();
            }

            par1TileEntitySofa.checkForAdjacentSofas();
        }

        	ModelCanape var14 = this.tileModel;
        	int var15;

            this.bindTextureByName("/item/largeSofa.png");
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short var11 = 0;

            if (var9 == 5)
            {
                var11 = 180;
            }

            if (var9 == 4)
            {
                var11 = 0;
            }

            if (var9 == 2)
            {
                var11 = 90;
            }

            if (var9 == 3)
            {
                var11 = -90;
            }
            GL11.glRotatef((float)var11, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0F, -1F, 0F);
            if (par1TileEntitySofa.adjacentSofaXPos == null && par1TileEntitySofa.adjacentSofaZPosition == null)
            {
            	var14.renderAll();
            }
            else if (par1TileEntitySofa.adjacentSofaXPos != null && par1TileEntitySofa.adjacentSofaZPosition == null)
            {	
            	var14.renderAllAdj2();
            }
            else if (par1TileEntitySofa.adjacentSofaXPos == null && par1TileEntitySofa.adjacentSofaZPosition != null)
            {	
            	var14.renderAllAdj1();
            }
            else
            {
            	var14.renderAllAdjAll();
            }
            
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
 
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntitySofaAt((TileEntitySofa)par1TileEntity, par2, par4, par6, par8);
    }
}