package mod.legendaire45.client;

import mod.legendaire45.common.CommonProxy;
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
    	
    }
}