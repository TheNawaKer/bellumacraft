package mod.legendaire45;

import mod.legendaire45.blocks.BlockCarottes;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.items.ItemToolEpeeMod;
import mod.legendaire45.items.ItemToolHacheMod;
import mod.legendaire45.items.ItemToolPelleMod;
import mod.legendaire45.items.ItemToolPiocheMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod; 
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "mod_iut", name = "mod iut", version = "1.4.0")

@NetworkMod(clientSideRequired = false, serverSideRequired = true)

public class mod_iut
{	
	    @Instance("Mod")
		public static mod_iut modInstance;
		@SidedProxy(clientSide="mod.legendaire45.client.ClientProxy", serverSide="mod.legendaire45.common.CommonProxy", bukkitSide="mod.legendaire45.common.CommonProxy") //Vous remarquerez que Server et Bukkit partagent la même classe.
		public static CommonProxy proxy;
		private static String textureBlock = CommonProxy.textureBlock; //Ici une façon d'appeler une texture par exemple afin de plus bas pouvoir écrire "textureblock"
		private static String textureItem = CommonProxy.textureItem;
		
		@PreInit
		public void initConfig(FMLPreInitializationEvent event)
		{
			MinecraftForge.setToolClass(this.piocheToolE, "pickaxe", 2);
			MinecraftForge.setToolClass(this.pelleToolE, "shovel", 2);
			MinecraftForge.setToolClass(this.hacheToolE, "axe", 2);
		}
		
		@Init
		public void load(FMLInitializationEvent event)
		{	
			proxy.registerRenderThings(); //Et oui, il faut bien dire de charger les proxy :)
			/**Enregistre le bloc**/

			GameRegistry.registerBlock(carottes);


			/** D�fini le nom IN-GAME des items/blocs**/

			LanguageRegistry.addName(pelleToolE, "Pelle en Emeraude");
			LanguageRegistry.addName(piocheToolE, "Pioche en Emeraude");
			LanguageRegistry.addName(hacheToolE, "Hache en Emeraude");
			LanguageRegistry.addName(epeeToolE, "Epée en Emeraude");
			LanguageRegistry.addName(pelleToolS, "Pelle en Saphir");
			LanguageRegistry.addName(piocheToolS, "Pioche en Saphir");
			LanguageRegistry.addName(hacheToolS, "Hache en Saphir");
			LanguageRegistry.addName(epeeToolS, "Epée en Saphir");
			LanguageRegistry.addName(pelleToolR, "Pelle en Ruby");
			LanguageRegistry.addName(piocheToolR, "Pioche en Ruby");
			LanguageRegistry.addName(hacheToolR, "Hache en Ruby");
			LanguageRegistry.addName(epeeToolR, "Epée en Ruby");
			LanguageRegistry.addName(carottes, "Bloc Carottes");
	    }	

		static EnumToolMaterial emerald= net.minecraftforge.common.EnumHelper.addToolMaterial("EMERALD", 2, 500, 7F, 3, 9);
		static EnumToolMaterial saphir= net.minecraftforge.common.EnumHelper.addToolMaterial("SAPHIR", 2, 500, 7F, 3, 9);
		static EnumToolMaterial ruby= net.minecraftforge.common.EnumHelper.addToolMaterial("RUBY", 2, 500, 7F, 3, 9);		
		public static final Block carottes = (new BlockCarottes(170, 0, Material.ground)).setTextureFile(textureBlock).setBlockName("Carottes Block").setCreativeTab(CreativeTabs.tabBlock);
		public static final Item pelleToolE= (new ItemToolPelleMod(400, emerald )).setTextureFile(textureItem).setItemName("tool_pelle_e").setIconIndex(3);
		public static final Item piocheToolE= (new ItemToolPiocheMod(401, emerald )).setTextureFile(textureItem).setItemName("tool_pioche_e").setIconIndex(4);
		public static final Item hacheToolE= (new ItemToolHacheMod(402, emerald )).setTextureFile(textureItem).setItemName("tool_hache_e").setIconIndex(5);
		public static final Item epeeToolE= (new ItemToolEpeeMod(403, emerald )).setTextureFile(textureItem).setItemName("tool_epee_e").setIconIndex(6);
		public static final Item pelleToolS= (new ItemToolPelleMod(404, saphir )).setTextureFile(textureItem).setItemName("tool_pelle_s").setIconIndex(7);
		public static final Item piocheToolS= (new ItemToolPiocheMod(405, saphir )).setTextureFile(textureItem).setItemName("tool_pioche_s").setIconIndex(8);
		public static final Item hacheToolS= (new ItemToolHacheMod(406, saphir )).setTextureFile(textureItem).setItemName("tool_hache_s").setIconIndex(9);
		public static final Item epeeToolS= (new ItemToolEpeeMod(407, saphir )).setTextureFile(textureItem).setItemName("tool_epee_s").setIconIndex(10);
		public static final Item pelleToolR= (new ItemToolPelleMod(408, ruby )).setTextureFile(textureItem).setItemName("tool_pelle_r").setIconIndex(11);
		public static final Item piocheToolR= (new ItemToolPiocheMod(409, ruby )).setTextureFile(textureItem).setItemName("tool_pioche_r").setIconIndex(12);
		public static final Item hacheToolR= (new ItemToolHacheMod(410, ruby )).setTextureFile(textureItem).setItemName("tool_hache_r").setIconIndex(13);
		public static final Item epeeToolR= (new ItemToolEpeeMod(411, ruby )).setTextureFile(textureItem).setItemName("tool_epee_r").setIconIndex(14);
}
