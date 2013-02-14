package mod.legendaire45.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.entity.EntityMagicArrow;
import mod.legendaire45.render.RenderBeer;
import mod.legendaire45.render.RenderMagicArrow;
import mod.legendaire45.render.TileEntityTrampolineRenderer;
import mod.legendaire45.tile.TileEntityBeer;
import mod.legendaire45.tile.TileEntityTrampoline;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy 
{
    @Override
    public void registerRenderThings()
    {
        //C'est ici que l'on va enregistrer tout ce qui concerne UNIQUEMENT le Client comme les RenderBlocks,... et dans notre exemple, l'enregistrement d'un fichier de texture.
    	MinecraftForgeClient.preloadTexture(this.textureBlock);
    	MinecraftForgeClient.preloadTexture(this.textureItem);
    	MinecraftForgeClient.preloadTexture(this.textureEmerald);
    	MinecraftForgeClient.preloadTexture(this.textureEmerald2);
    	MinecraftForgeClient.preloadTexture(this.textureSaphir);
    	MinecraftForgeClient.preloadTexture(this.textureSaphir2);
    	MinecraftForgeClient.preloadTexture(this.textureRuby);
    	MinecraftForgeClient.preloadTexture(this.textureRuby2);
    	MinecraftForgeClient.preloadTexture(this.trampoline);
    	MinecraftForgeClient.preloadTexture(this.lunettea);
    	MinecraftForgeClient.preloadTexture(this.lunetteb);
    	MinecraftForgeClient.preloadTexture(this.lunettec);
    	MinecraftForgeClient.preloadTexture(this.guibeer);
    	MinecraftForgeClient.preloadTexture(this.distributor);
		RenderBeer renderBeer = new RenderBeer();
		ModLoader.registerTileEntity(TileEntityBeer.class, "tile", renderBeer);//ajout
		TileEntityTrampolineRenderer trampolineRender = new TileEntityTrampolineRenderer();
		ModLoader.registerTileEntity(TileEntityTrampoline.class, "TileEntitytrampoline", trampolineRender);	
		ModLoader.registerEntityID(EntityMagicArrow.class, "firearrow", ModLoader.getUniqueEntityId());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicArrow.class, new RenderMagicArrow());
		 
		
    	
    }
}