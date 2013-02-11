package mod.legendaire45;

import mod.legendaire45.blocks.BlockBeer;
import mod.legendaire45.blocks.BlockCarottes;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.items.ArmorBase;
import mod.legendaire45.items.ItemToolEpeeMod;
import mod.legendaire45.items.ItemToolHacheMod;
import mod.legendaire45.items.ItemToolPelleMod;
import mod.legendaire45.items.ItemToolPiocheMod;
import mod.legendaire45.render.RenderBeer;
import mod.legendaire45.tile.TileEntityBeer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.EnumHelper;
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
			GameRegistry.registerBlock(beer);
			RenderBeer renderBeer = new RenderBeer();
			ModLoader.registerTileEntity(TileEntityBeer.class, "tile", renderBeer);//ajout
			


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
			
			LanguageRegistry.addName(ArmorE1, "Casque en Emeraude");
			LanguageRegistry.addName(ArmorE2, "Torse en Emeraude");
			LanguageRegistry.addName(ArmorE3, "Jambiere en Emeraude");
			LanguageRegistry.addName(ArmorE4, "Bottes en Emeraude");
			LanguageRegistry.addName(ArmorS1, "Casque en Saphir");
			LanguageRegistry.addName(ArmorS2, "Torse en Saphir");
			LanguageRegistry.addName(ArmorS3, "Jambiere en Saphir");
			LanguageRegistry.addName(ArmorS4, "Bottes en Saphir");
			LanguageRegistry.addName(ArmorR1, "Casque en Ruby");
			LanguageRegistry.addName(ArmorR2, "Torse en Ruby");
			LanguageRegistry.addName(ArmorR3, "Jambiere en Ruby");
			LanguageRegistry.addName(ArmorR4, "Bottes en Ruby");
			
			LanguageRegistry.addName(carottes, "Bloc Carottes");
			LanguageRegistry.addName(beer, "Distributeur");
			LanguageRegistry.addName(BeerItem, "Distributeur");
	    }	

		static EnumToolMaterial emerald= EnumHelper.addToolMaterial("EMERALD", 2, 500, 7F, 3, 9);
		static EnumToolMaterial saphir= EnumHelper.addToolMaterial("SAPHIR", 2, 500, 7F, 3, 9);
		static EnumToolMaterial ruby= EnumHelper.addToolMaterial("RUBY", 2, 500, 7F, 3, 9);		
		
		public static EnumArmorMaterial emeraldarmor = EnumHelper.addArmorMaterial("EMERALD", 29, new int[] {1, 2, 3, 4}, 9);
		public static EnumArmorMaterial saphirarmor = EnumHelper.addArmorMaterial("SAPHIR", 29, new int[] {1, 2, 3, 4}, 9);
		public static EnumArmorMaterial rubyarmor = EnumHelper.addArmorMaterial("RUBY", 29, new int[] {1, 2, 3, 4}, 9);
		public static EnumArmorMaterial lunette = EnumHelper.addArmorMaterial("PLASTIC", 29, new int[] {1, 2, 3, 4}, 9);
		
		public static final Block carottes= (new BlockCarottes(170, 0, Material.ground)).setTextureFile(textureBlock).setBlockName("Carottes Block").setCreativeTab(CreativeTabs.tabBlock);
		public static final Block beer= (new BlockBeer(171, 37, Material.wood)).setTextureFile(textureItem).setBlockName("Distributeur2").setCreativeTab(CreativeTabs.tabBlock);
		
		public static final Item BeerItem = (new ItemReed(399, beer)).setTextureFile(textureItem).setIconIndex(37).setItemName("Distributeur").setCreativeTab(CreativeTabs.tabBlock);
		
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

		public static final Item ArmorE1 = new ArmorBase(412, emeraldarmor, 0,0).setTextureFile(textureItem).setIconIndex(19).setItemName("armor_head_e");
		public static final Item ArmorE2 = new ArmorBase(413, emeraldarmor, 1,1).setTextureFile(textureItem).setIconIndex(20).setItemName("armor_plate_e");
		public static final Item ArmorE3= new ArmorBase(414, emeraldarmor, 2,2).setTextureFile(textureItem).setIconIndex(21).setItemName("armor_legs_e");
		public static final Item ArmorE4= new ArmorBase(415, emeraldarmor, 3,3).setTextureFile(textureItem).setIconIndex(22).setItemName("armor_foot_e");
		public static final Item ArmorS1 = new ArmorBase(416, saphirarmor, 0,0).setTextureFile(textureItem).setIconIndex(23).setItemName("armor_head_s");
		public static final Item ArmorS2 = new ArmorBase(417, saphirarmor, 1,1).setTextureFile(textureItem).setIconIndex(24).setItemName("armor_plate_s");
		public static final Item ArmorS3= new ArmorBase(418, saphirarmor, 2,2).setTextureFile(textureItem).setIconIndex(25).setItemName("armor_legs_s");
		public static final Item ArmorS4= new ArmorBase(419, saphirarmor, 3,3).setTextureFile(textureItem).setIconIndex(26).setItemName("armor_foot_s");		
		public static final Item ArmorR1 = new ArmorBase(420, rubyarmor, 0,0).setTextureFile(textureItem).setIconIndex(27).setItemName("armor_head_r");
		public static final Item ArmorR2 = new ArmorBase(421, rubyarmor, 1,1).setTextureFile(textureItem).setIconIndex(28).setItemName("armor_plate_r");
		public static final Item ArmorR3= new ArmorBase(422, rubyarmor, 2,2).setTextureFile(textureItem).setIconIndex(29).setItemName("armor_legs_r");
		public static final Item ArmorR4= new ArmorBase(423, rubyarmor, 3,3).setTextureFile(textureItem).setIconIndex(30).setItemName("armor_foot_r");

		public static final Item lunette1 = new ArmorBase(424, lunette, 0,0).setTextureFile(textureItem).setIconIndex(35).setItemName("armor_head_e");
		public static final Item lunette2 = new ArmorBase(425, lunette, 0,0).setTextureFile(textureItem).setIconIndex(36).setItemName("armor_head_e");
		public static final Item lunette3 = new ArmorBase(426, lunette, 0,0).setTextureFile(textureItem).setIconIndex(36).setItemName("armor_head_e");
}
