package mod.legendaire45;

import mod.legendaire45.blocks.BlockBeer;
import mod.legendaire45.blocks.BlockCarottes;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.gui.GuiHandler;
import mod.legendaire45.items.ArmorBase;
import mod.legendaire45.items.ItemCup;
import mod.legendaire45.items.ItemDrink;
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
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mod.legendaire45.client.ClientPacketHandler;
import mod.legendaire45.server.ServerPacketHandler;

@Mod(modid = "mod_retrogame", name = "mod retrogame", version = "1.4.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"mod_retrogame" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"mod_retrogame" }, packetHandler = ServerPacketHandler.class))

public class mod_retrogame
{	
	    @Instance
		public static mod_retrogame instance  = new mod_retrogame();
		private GuiHandler guiHandler = new GuiHandler();
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
			NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
			/**Enregistre le bloc**/

			GameRegistry.registerBlock(carottes);
			GameRegistry.registerBlock(beer);
			


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
			
			LanguageRegistry.addName(Cup, "Chope");
			LanguageRegistry.addName(BucketBeer, "Seau de Houblon");
			LanguageRegistry.addName(CupBeer, "Chope Pleine");
	    }
		static int IDoutil = 400;

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
	    public static final Item Cup = (new ItemCup(IDoutil+2)).setTextureFile(textureItem).setIconIndex(0).setItemName("Chope Vide").setCreativeTab(CreativeTabs.tabBlock);
	    public static final Item BucketBeer = (new ItemCup(IDoutil+3)).setTextureFile(textureItem).setIconIndex(2).setItemName("seau de biere").setCreativeTab(CreativeTabs.tabBlock);
	    public static final Item CupBeer = (new ItemDrink(IDoutil+4, 10, 0.0F, false)).setAlwaysEdible().setTextureFile(textureItem).setIconIndex(1).setItemName("Chope Pleine").setCreativeTab(CreativeTabs.tabBlock);
	    
		public static final Item pelleToolE= (new ItemToolPelleMod(IDoutil+5, emerald )).setTextureFile(textureItem).setItemName("tool_pelle_e").setIconIndex(3);
		public static final Item piocheToolE= (new ItemToolPiocheMod(IDoutil+6, emerald )).setTextureFile(textureItem).setItemName("tool_pioche_e").setIconIndex(4);
		public static final Item hacheToolE= (new ItemToolHacheMod(IDoutil+7, emerald )).setTextureFile(textureItem).setItemName("tool_hache_e").setIconIndex(5);
		public static final Item epeeToolE= (new ItemToolEpeeMod(IDoutil+8, emerald )).setTextureFile(textureItem).setItemName("tool_epee_e").setIconIndex(6);
		public static final Item pelleToolS= (new ItemToolPelleMod(IDoutil+9, saphir )).setTextureFile(textureItem).setItemName("tool_pelle_s").setIconIndex(7);
		public static final Item piocheToolS= (new ItemToolPiocheMod(IDoutil+9, saphir )).setTextureFile(textureItem).setItemName("tool_pioche_s").setIconIndex(8);
		public static final Item hacheToolS= (new ItemToolHacheMod(IDoutil+10, saphir )).setTextureFile(textureItem).setItemName("tool_hache_s").setIconIndex(9);
		public static final Item epeeToolS= (new ItemToolEpeeMod(IDoutil+11, saphir )).setTextureFile(textureItem).setItemName("tool_epee_s").setIconIndex(10);
		public static final Item pelleToolR= (new ItemToolPelleMod(IDoutil+12, ruby )).setTextureFile(textureItem).setItemName("tool_pelle_r").setIconIndex(11);
		public static final Item piocheToolR= (new ItemToolPiocheMod(IDoutil+13, ruby )).setTextureFile(textureItem).setItemName("tool_pioche_r").setIconIndex(12);
		public static final Item hacheToolR= (new ItemToolHacheMod(IDoutil+14, ruby )).setTextureFile(textureItem).setItemName("tool_hache_r").setIconIndex(13);
		public static final Item epeeToolR= (new ItemToolEpeeMod(IDoutil+15, ruby )).setTextureFile(textureItem).setItemName("tool_epee_r").setIconIndex(14);

		public static final Item ArmorE1 = new ArmorBase(IDoutil+16, emeraldarmor, 0,0).setTextureFile(textureItem).setIconIndex(19).setItemName("armor_head_e");
		public static final Item ArmorE2 = new ArmorBase(IDoutil+17, emeraldarmor, 1,1).setTextureFile(textureItem).setIconIndex(20).setItemName("armor_plate_e");
		public static final Item ArmorE3= new ArmorBase(IDoutil+18, emeraldarmor, 2,2).setTextureFile(textureItem).setIconIndex(21).setItemName("armor_legs_e");
		public static final Item ArmorE4= new ArmorBase(IDoutil+19, emeraldarmor, 3,3).setTextureFile(textureItem).setIconIndex(22).setItemName("armor_foot_e");
		public static final Item ArmorS1 = new ArmorBase(IDoutil+20, saphirarmor, 0,0).setTextureFile(textureItem).setIconIndex(23).setItemName("armor_head_s");
		public static final Item ArmorS2 = new ArmorBase(IDoutil+21, saphirarmor, 1,1).setTextureFile(textureItem).setIconIndex(24).setItemName("armor_plate_s");
		public static final Item ArmorS3= new ArmorBase(IDoutil+22, saphirarmor, 2,2).setTextureFile(textureItem).setIconIndex(25).setItemName("armor_legs_s");
		public static final Item ArmorS4= new ArmorBase(IDoutil+23, saphirarmor, 3,3).setTextureFile(textureItem).setIconIndex(26).setItemName("armor_foot_s");		
		public static final Item ArmorR1 = new ArmorBase(IDoutil+24, rubyarmor, 0,0).setTextureFile(textureItem).setIconIndex(27).setItemName("armor_head_r");
		public static final Item ArmorR2 = new ArmorBase(IDoutil+25, rubyarmor, 1,1).setTextureFile(textureItem).setIconIndex(28).setItemName("armor_plate_r");
		public static final Item ArmorR3= new ArmorBase(IDoutil+26, rubyarmor, 2,2).setTextureFile(textureItem).setIconIndex(29).setItemName("armor_legs_r");
		public static final Item ArmorR4= new ArmorBase(IDoutil+27, rubyarmor, 3,3).setTextureFile(textureItem).setIconIndex(30).setItemName("armor_foot_r");

		public static final Item lunette1 = new ArmorBase(IDoutil+28, lunette, 0,0).setTextureFile(textureItem).setIconIndex(35).setItemName("armor_head_e");
		public static final Item lunette2 = new ArmorBase(IDoutil+29, lunette, 0,0).setTextureFile(textureItem).setIconIndex(36).setItemName("armor_head_e");
		public static final Item lunette3 = new ArmorBase(IDoutil+30, lunette, 0,0).setTextureFile(textureItem).setIconIndex(36).setItemName("armor_head_e");
}
