package mod.legendaire45.tile;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileItemRenderHelper
{
    /** The static instance of TileItemRenderHelper. */
    public static TileItemRenderHelper instance = new TileItemRenderHelper(); //on créer une instance de cette class

    /** Instance of Tile's Tile Entity. */
    private TileEntity theTile = new TileEntity();// on récupère une instance de TileEntityTile

    /**
     * Renders a tile at 0,0,0 - used for item rendering
     */
    public void renderTile(Block par1Block, int par2, float par3)
    {
    	TileEntityRenderer.instance.renderTileEntityAt(this.theTile, 0.0D, 0.0D, 0.0D, 0.0F); //on applique le rendu du TileEntityTile à l'instance de l'item.
    }
}