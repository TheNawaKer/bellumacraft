package mod.legendaire45.client;

import mod.legendaire45.mod_retrogame;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.entity.EntityMagicArrow;
import mod.legendaire45.entity.EntityTeleportArrow;
import mod.legendaire45.entity.mobs.EntityCheval;
import mod.legendaire45.entity.model.ModelCheval;
import mod.legendaire45.render.ItemRendererCheese;
import mod.legendaire45.render.RenderBeer;
import mod.legendaire45.render.RenderCheese;
import mod.legendaire45.render.RenderMagicArrow;
import mod.legendaire45.render.RenderMoule;
import mod.legendaire45.render.RenderTeleportArrow;
import mod.legendaire45.render.TileEntitySofaRenderer;
import mod.legendaire45.render.TileEntityTrampolineRenderer;
import mod.legendaire45.render.entity.RenderCheval;
import mod.legendaire45.tile.TileEntityBeer;
import mod.legendaire45.tile.TileEntityCheese;
import mod.legendaire45.tile.TileEntityMoule;
import mod.legendaire45.tile.TileEntitySofa;
import mod.legendaire45.tile.TileEntityTrampoline;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
    	MinecraftForgeClient.preloadTexture(this.firearrowtexture);
    	MinecraftForgeClient.preloadTexture(this.teleportarrowtexture);
    	MinecraftForgeClient.preloadTexture(this.cheval);
    	MinecraftForgeClient.preloadTexture(this.moule1);
    	MinecraftForgeClient.preloadTexture(this.moule2);
    	MinecraftForgeClient.preloadTexture(this.moule3);
    	MinecraftForgeClient.preloadTexture(this.moule4);
    	MinecraftForgeClient.preloadTexture(this.cheese);
    	
    	
		TileEntitySofaRenderer renderSofa = new TileEntitySofaRenderer();
		ModLoader.registerTileEntity(TileEntitySofa.class, "sofa", renderSofa);//ajout
		
		RenderBeer renderBeer = new RenderBeer();
		ModLoader.registerTileEntity(TileEntityBeer.class, "beer", renderBeer);//ajout
		
		TileEntityTrampolineRenderer trampolineRender = new TileEntityTrampolineRenderer();
		ModLoader.registerTileEntity(TileEntityTrampoline.class, "TileEntitytrampoline", trampolineRender);	

		RenderMoule renderMoule = new RenderMoule();
		ModLoader.registerTileEntity(TileEntityMoule.class, "CheeseMoule", renderMoule);	
		
		RenderCheese renderCheese = new RenderCheese();
		ModLoader.registerTileEntity(TileEntityCheese.class, "Cheese", renderCheese);
		
		ModLoader.registerEntityID(EntityMagicArrow.class, "firearrow", ModLoader.getUniqueEntityId());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicArrow.class, new RenderMagicArrow());
		ModLoader.registerEntityID(EntityTeleportArrow.class, "teleportarrow", ModLoader.getUniqueEntityId());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportArrow.class, new RenderTeleportArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityCheval.class, new RenderCheval(new ModelCheval(), new ModelCheval() , 0.7F)); 
		KeyBindingRegistry.registerKeyBinding(new KeyHandlerBow());
		
		MinecraftForgeClient.registerItemRenderer(mod_retrogame.cheese.blockID, (IItemRenderer)new ItemRendererCheese());
		
		 
		
    	
    }
}