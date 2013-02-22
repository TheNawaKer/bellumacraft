package mod.legendaire45.render.item;

import mod.legendaire45.tile.TileEntitySofa;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 
public class ItemSofaRenderer
{
    /** The static instance of TileItemRenderHelper. */
    public static ItemSofaRenderer instance = new ItemSofaRenderer(); //on creer une instance de cette class
 
    /** Instance of Tile's Tile Entity. */
    private TileEntitySofa theTile = new TileEntitySofa();// on recupere une instance de TileEntityTile
 
    /**
     * Renders a tile at 0,0,0 - used for item rendering
     */
    public void renderTile(Block par1Block, int par2, float par3)
    {
    	TileEntityRenderer.instance.renderTileEntityAt(this.theTile, 0.0D, 0.0D, 0.0D, 0.0F); //on applique le rendu du TileEntityTile a l'instance de l item.
    }
}