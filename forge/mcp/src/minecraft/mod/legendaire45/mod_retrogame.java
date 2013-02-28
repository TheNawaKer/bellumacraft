package mod.legendaire45;

import mod.legendaire45.blocks.BlockBeer;
import mod.legendaire45.blocks.BlockCropBeer;
import mod.legendaire45.blocks.BlockMoule;
import mod.legendaire45.blocks.BlockRuby;
import mod.legendaire45.blocks.BlockSaphir;
import mod.legendaire45.blocks.BlockSofa;
import mod.legendaire45.blocks.BlockStairLog;
import mod.legendaire45.blocks.BlockTrampoline;
import mod.legendaire45.client.ClientPacketHandler;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.entity.EntityMagicArrow;
import mod.legendaire45.entity.EntityTeleportArrow;
import mod.legendaire45.entity.mobs.EntityCheval;
import mod.legendaire45.entity.player.EntityPlayerSword;
import mod.legendaire45.event.BoneMealEvent;
import mod.legendaire45.gui.GuiHandler;
import mod.legendaire45.items.ArmorBase;
import mod.legendaire45.items.ItemCup;
import mod.legendaire45.items.ItemDisc;
import mod.legendaire45.items.ItemDrink;
import mod.legendaire45.items.ItemToolEpeeMod;
import mod.legendaire45.items.ItemToolHacheMod;
import mod.legendaire45.items.ItemToolPelleMod;
import mod.legendaire45.items.ItemToolPiocheMod;
import mod.legendaire45.items.MagicBow;
import mod.legendaire45.items.TeleportBow;
import mod.legendaire45.render.player.RenderPlayerSword;
import mod.legendaire45.server.ServerPacketHandler;
import mod.legendaire45.tile.TileEntityBeer;
import mod.legendaire45.tile.TileEntityTrampoline;
import mod.legendaire45.tools.NewSound;
import mod.legendaire45.world.WorldGenOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.PlayerAPI;
import net.minecraft.src.RenderPlayerAPI;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "mod_retrogame", name = "mod retrogame", version = "1.4.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"mod_retrogame","sword" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"mod_retrogame","sword" }, packetHandler = ServerPacketHandler.class))

public class mod_retrogame 
{	
	    @Instance
		public static mod_retrogame instance  = new mod_retrogame();
		private GuiHandler guiHandler = new GuiHandler();
		@SidedProxy(clientSide="mod.legendaire45.client.ClientProxy", serverSide="mod.legendaire45.common.CommonProxy", bukkitSide="mod.legendaire45.common.CommonProxy") //Vous remarquerez que Server et Bukkit partagent la même classe.
		public static CommonProxy proxy;
		private static String textureBlock = CommonProxy.textureBlock; //Ici une façon d'appeler une texture par exemple afin de plus bas pouvoir écrire "textureblock"
		private static String textureItem = CommonProxy.textureItem;
		
		static int IDoutil = 400;
		static int IDblock = 170;
		
		/*
		 * Material Outils
		 */
		static EnumToolMaterial emerald= EnumHelper.addToolMaterial("EMERALD", 2, 1800, 8.5F, 4, 22);
		static EnumToolMaterial saphir= EnumHelper.addToolMaterial("SAPHIR", 2, 1800, 9F, 3, 22);
		static EnumToolMaterial ruby= EnumHelper.addToolMaterial("RUBY", 2, 1800, 10F, 2, 22);		
		
		/*
		 * Material Armures + lunettes
		 */
		public static EnumArmorMaterial emeraldarmor = EnumHelper.addArmorMaterial("EMERALD", 33, new int[] {5, 10, 8, 5}, 25);
		public static EnumArmorMaterial saphirarmor = EnumHelper.addArmorMaterial("SAPHIR", 33, new int[] {4, 9, 7, 4}, 25);
		public static EnumArmorMaterial rubyarmor = EnumHelper.addArmorMaterial("RUBY", 33, new int[] {4, 9, 7, 4}, 25);
		public static EnumArmorMaterial lunette = EnumHelper.addArmorMaterial("PLASTIC", 5, new int[] {1, 2, 3, 4}, 9);
		
		/*
		 * Block
		 */
		public static final Block beer = (new BlockBeer(IDblock+1, 37, Material.grass)).setTextureFile(textureItem).setBlockName("Distributeur2").setCreativeTab(CreativeTabs.tabBlock);
		public static final Block blockTrampoline = new BlockTrampoline(IDblock+2, 1, Material.grass).setBlockName("Bloc de Slime").setTextureFile(textureBlock).setHardness(.5F).setStepSound(Block.soundSnowFootstep).setCreativeTab(CreativeTabs.tabBlock);
		public static final Block cropBeer = (new BlockCropBeer(IDblock+3)).setTextureFile(textureBlock).setBlockName("cropBeer").setStepSound(Block.soundGrassFootstep);
		public static final Block stair = (new BlockStairLog(IDblock+4, net.minecraft.block.Block.wood, 10)).setTextureFile(textureBlock).setBlockName("Escalier en buche").setCreativeTab(CreativeTabs.tabBlock);
		
		public static final Block rubyOre = (new BlockRuby(IDblock+6, 14, Material.rock)).setTextureFile(textureBlock).setStepSound(Block.soundStoneFootstep).setBlockName("ruby").setCreativeTab(CreativeTabs.tabBlock);
		public static final Block saphirOre = (new BlockSaphir(IDblock+7, 15, Material.rock)).setTextureFile(textureBlock).setStepSound(Block.soundStoneFootstep).setBlockName("saphir").setCreativeTab(CreativeTabs.tabBlock);
		
		public static final Block sofa = (new BlockSofa(IDblock+5)).setStepSound(Block.soundWoodFootstep).setBlockName("sofa").setCreativeTab(CreativeTabs.tabDecorations);
		
		public static final Block moule = (new BlockMoule(IDblock+8, 4, Material.wood)).setStepSound(Block.soundWoodFootstep).setBlockName("moule").setCreativeTab(CreativeTabs.tabDecorations);
		/*
		 * Item
		 */
		public static final Item sofas = (new ItemReed(IDoutil+41,sofa)).setTextureFile(textureItem).setIconIndex(40).setItemName("Beer").setCreativeTab(CreativeTabs.tabBlock);
	    
		public static final Item Cup = (new ItemCup(IDoutil+31)).setTextureFile(textureItem).setIconIndex(0).setItemName("Chope Vide").setCreativeTab(CreativeTabs.tabBlock);
		public static final Item CupGlass = (new Item(IDoutil+44)).setTextureFile(textureItem).setIconCoord(15, 2).setItemName("Chope de Tireuse").setCreativeTab(CreativeTabs.tabBlock);
	    public static final Item BucketBeer = (new ItemCup(IDoutil+32)).setTextureFile(textureItem).setIconIndex(2).setItemName("seau de biere").setCreativeTab(CreativeTabs.tabBlock);
	    public static final Item CupBeer = (new ItemDrink(IDoutil+33, 10, 0.0F, false)).setAlwaysEdible().setTextureFile(textureItem).setIconIndex(1).setItemName("Chope Pleine").setCreativeTab(CreativeTabs.tabBlock);
	  
	    public static Item seedBeer = (new ItemSeeds(IDoutil+34, cropBeer.blockID, Block.tilledField.blockID)).setTextureFile(textureItem).setIconIndex(39).setItemName("seedBeer").setCreativeTab(CreativeTabs.tabBlock);
	    public static Item wheatBeer = (new Item(IDoutil+35)).setTextureFile(textureItem).setIconIndex(40).setItemName("Beer").setCreativeTab(CreativeTabs.tabBlock);
	    
	    public static Item rubyGem = (new Item(IDoutil+42)).setTextureFile(textureItem).setIconCoord(13, 2).setItemName("ruby").setCreativeTab(CreativeTabs.tabMaterials);
	    public static Item saphirGem = (new Item(IDoutil+43)).setTextureFile(textureItem).setIconCoord(14, 2).setItemName("saphir").setCreativeTab(CreativeTabs.tabMaterials);
	    
	    /*
	     * Outils
	     */
		public static final Item pelleToolE= (new ItemToolPelleMod(IDoutil+5, emerald )).setTextureFile(textureItem).setItemName("tool_pelle_e").setIconIndex(3);
		public static final Item piocheToolE= (new ItemToolPiocheMod(IDoutil+6, emerald )).setTextureFile(textureItem).setItemName("tool_pioche_e").setIconIndex(4);
		public static final Item hacheToolE= (new ItemToolHacheMod(IDoutil+7, emerald )).setTextureFile(textureItem).setItemName("tool_hache_e").setIconIndex(5);
		public static final Item epeeToolE= (new ItemToolEpeeMod(IDoutil+8, emerald )).setTextureFile(textureItem).setItemName("tool_epee_e").setIconIndex(6);
		public static final Item pelleToolS= (new ItemToolPelleMod(IDoutil+1, saphir )).setTextureFile(textureItem).setItemName("tool_pelle_s").setIconIndex(7);
		public static final Item piocheToolS= (new ItemToolPiocheMod(IDoutil+9, saphir )).setTextureFile(textureItem).setItemName("tool_pioche_s").setIconIndex(8);
		public static final Item hacheToolS= (new ItemToolHacheMod(IDoutil+10, saphir )).setTextureFile(textureItem).setItemName("tool_hache_s").setIconIndex(9);
		public static final Item epeeToolS= (new ItemToolEpeeMod(IDoutil+11, saphir )).setTextureFile(textureItem).setItemName("tool_epee_s").setIconIndex(10);
		public static final Item pelleToolR= (new ItemToolPelleMod(IDoutil+12, ruby )).setTextureFile(textureItem).setItemName("tool_pelle_r").setIconIndex(11);
		public static final Item piocheToolR= (new ItemToolPiocheMod(IDoutil+13, ruby )).setTextureFile(textureItem).setItemName("tool_pioche_r").setIconIndex(12);
		public static final Item hacheToolR= (new ItemToolHacheMod(IDoutil+14, ruby )).setTextureFile(textureItem).setItemName("tool_hache_r").setIconIndex(13);
		public static final Item epeeToolR= (new ItemToolEpeeMod(IDoutil+15, ruby )).setTextureFile(textureItem).setItemName("tool_epee_r").setIconIndex(14);

		/*
		 * Armures + lunettes
		 */
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

		public static final Item lunette1 = new ArmorBase(IDoutil+28, lunette, 0,0).setTextureFile(textureItem).setIconIndex(35).setItemName("lunette");
		public static final Item lunette2 = new ArmorBase(IDoutil+29, lunette, 0,0).setTextureFile(textureItem).setIconIndex(36).setItemName("lunette2");
		public static final Item lunette3 = new ArmorBase(IDoutil+30, lunette, 0,0).setTextureFile(textureItem).setIconIndex(38).setItemName("lunette3");
		
		/*
		 * Arc + fleche
		 */
	    public static final Item firebow = (new MagicBow(IDoutil+36)).setIconCoord(10, 2).setTextureFile(textureItem).setItemName("firebow");
	    public static final Item teleportbow = (new TeleportBow(IDoutil+38)).setIconCoord(12, 2).setTextureFile(textureItem).setItemName("teleportbow");
	    public static final Item firearrow = (new Item(IDoutil+37)).setIconCoord(10, 3).setTextureFile(textureItem).setItemName("firearrow").setCreativeTab(CreativeTabs.tabCombat);
	    public static final Item teleportarrow = (new Item(IDoutil+39)).setIconCoord(12, 3).setTextureFile(textureItem).setItemName("teleportarrow").setCreativeTab(CreativeTabs.tabCombat);
	 
	    public static final Item bowfix = (new Item(IDoutil+45)).setIconCoord(5, 1).setItemName("arc render");
	    
	    /*
	     * Disc
	     */
	    public static Item disc1 = (new ItemDisc(2012, "mia")).setIconCoord(0, 15).setItemName("mia");
	    public static Item disc2 = (new ItemDisc(2013, "skrillex")).setIconCoord(0, 15).setItemName("skrillex");
	    
		@PreInit
		public void initConfig(FMLPreInitializationEvent event)
		{
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if(side == side.CLIENT)
			{
				PlayerAPI.register("mod_retrogame_sword", EntityPlayerSword.class);
		    	RenderPlayerAPI.register("mod_retrogame_sword", RenderPlayerSword.class);
				MinecraftForge.EVENT_BUS.register(new NewSound());
				MinecraftForge.EVENT_BUS.register(new SoundEvent());			
			}
			MinecraftForge.addGrassSeed(new ItemStack(seedBeer), 10);
			MinecraftForge.EVENT_BUS.register(new BoneMealEvent());
			MinecraftForge.setToolClass(this.piocheToolE, "pickaxe", 2);
			MinecraftForge.setToolClass(this.pelleToolE, "shovel", 2);
			MinecraftForge.setToolClass(this.hacheToolE, "axe", 2);
			MinecraftForge.setToolClass(this.piocheToolS, "pickaxe", 2);
			MinecraftForge.setToolClass(this.pelleToolS, "shovel", 2);
			MinecraftForge.setToolClass(this.hacheToolS, "axe", 2);
			MinecraftForge.setToolClass(this.piocheToolR, "pickaxe", 2);
			MinecraftForge.setToolClass(this.pelleToolR, "shovel", 2);
			MinecraftForge.setToolClass(this.hacheToolR, "axe", 2);
		}
		
		@Init
		public void load(FMLInitializationEvent event)
		{	
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if(side == side.SERVER)
			{
				ModLoader.registerTileEntity(TileEntityBeer.class, "beer");
				ModLoader.registerTileEntity(TileEntityTrampoline.class, "trampoline");
			}
			ModLoader.registerEntityID(EntityCheval.class, "Cheval", ModLoader.getUniqueEntityId());   // Donne une ID au mob			 
		    ModLoader.addSpawn(EntityCheval.class, 10, 4, 6,EnumCreatureType.creature);
		    proxy.registerRenderThings(); //Et oui, il faut bien dire de charger les proxy :)
			EntityRegistry.registerModEntity(EntityMagicArrow.class, "firearrow", 1, this, 250, 5, false);
			EntityRegistry.registerModEntity(EntityTeleportArrow.class, "teleportarrow", 2, this, 250, 5, false);
			NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
			
			register();
			name();
	    }
		
		@PostInit
		private void craft(FMLPostInitializationEvent event)
		{
			GameRegistry.addRecipe(new ItemStack(blockTrampoline), new Object[]	{"XXX", "XXX", "XXX", 'X', Item.slimeBall});
			
			GameRegistry.addRecipe(new ItemStack(pelleToolR), new Object[]	{" 0 ", " X ", " X ", 'X', Item.stick, '0', rubyGem});
			GameRegistry.addRecipe(new ItemStack(piocheToolR), new Object[]	{"000", " X ", " X ", 'X', Item.stick, '0', rubyGem});
			GameRegistry.addRecipe(new ItemStack(hacheToolR), new Object[]	{"000", "0X0", " X ", 'X', Item.stick, '0', rubyGem});
			GameRegistry.addRecipe(new ItemStack(epeeToolR), new Object[]	{" 0 ", " 0 ", " X ",  'X', Item.stick, '0', rubyGem});
			
			GameRegistry.addRecipe(new ItemStack(pelleToolS), new Object[]	{" 0 ", " X ", " X ", 'X', Item.stick, '0', saphirGem});
			GameRegistry.addRecipe(new ItemStack(piocheToolS), new Object[]	{"000", " X ", " X ", 'X', Item.stick, '0', saphirGem});
			GameRegistry.addRecipe(new ItemStack(hacheToolS), new Object[]	{"000", "0X0", " X ", 'X', Item.stick, '0', saphirGem});
			GameRegistry.addRecipe(new ItemStack(epeeToolS), new Object[]	{" 0 ", " 0 ", " X ",  'X', Item.stick, '0', saphirGem});
		
			GameRegistry.addRecipe(new ItemStack(pelleToolE), new Object[]	{" 0 ", " X ", " X ", 'X', Item.stick, '0', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(piocheToolE), new Object[]	{"000", " X ", " X ", 'X', Item.stick, '0', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(hacheToolE), new Object[]	{"000", "0X0", " X ", 'X', Item.stick, '0', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(epeeToolE), new Object[]	{" 0 ", " 0 ", " X ",  'X', Item.stick, '0', Item.emerald});
			
			GameRegistry.addRecipe(new ItemStack(ArmorE1), new Object[]	{"XXX", "X X", 'X', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(ArmorE2), new Object[]	{"X X", "XXX", "XXX", 'X', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(ArmorE3), new Object[]	{"XXX", "X X", "X X", 'X', Item.emerald});
			GameRegistry.addRecipe(new ItemStack(ArmorE4), new Object[]	{"X X", "X X", 'X', Item.emerald});
			
			GameRegistry.addRecipe(new ItemStack(ArmorS1), new Object[]	{"XXX", "X X", 'X', saphirGem});
			GameRegistry.addRecipe(new ItemStack(ArmorS2), new Object[]	{"X X", "XXX", "XXX", 'X', saphirGem});
			GameRegistry.addRecipe(new ItemStack(ArmorS3), new Object[]	{"XXX", "X X", "X X", 'X', saphirGem});
			GameRegistry.addRecipe(new ItemStack(ArmorS4), new Object[]	{"X X", "X X", 'X', saphirGem});

			GameRegistry.addRecipe(new ItemStack(ArmorR1), new Object[]	{"XXX", "X X", 'X', rubyGem});
			GameRegistry.addRecipe(new ItemStack(ArmorR2), new Object[]	{"X X", "XXX", "XXX", 'X', rubyGem});
			GameRegistry.addRecipe(new ItemStack(ArmorR3), new Object[]	{"XXX", "X X", "X X", 'X', rubyGem});
			GameRegistry.addRecipe(new ItemStack(ArmorR4), new Object[]	{"X X", "X X", 'X', rubyGem});
			
			GameRegistry.addRecipe(new ItemStack(lunette2), new Object[] {"GWG", "W W", 'W', new ItemStack(Block.cloth, 1, 0), 'G', Block.glass});
			GameRegistry.addRecipe(new ItemStack(lunette1), new Object[] {"GWG", "W W", 'W', new ItemStack(Block.cloth, 1, 15), 'G', Block.glass});
			GameRegistry.addRecipe(new ItemStack(lunette3), new Object[] {"GWG", "W W", 'W', new ItemStack(Block.cloth, 1, 2), 'G', Block.glass});
			
			GameRegistry.addShapelessRecipe(new ItemStack(lunette1), new Object[] {lunette2, new ItemStack(Item.dyePowder, 1, 0)});
			GameRegistry.addShapelessRecipe(new ItemStack(lunette3), new Object[] {lunette2, new ItemStack(Item.dyePowder, 1, 13)});
			
			GameRegistry.addRecipe(new ItemStack(Cup), new Object[]	{"   ", "X X", " X ",  'X', Block.planks});
			GameRegistry.addRecipe(new ItemStack(CupGlass, 3), new Object[] {"X X", "X X", " X ",  'X', Block.glass});
			
			GameRegistry.addRecipe(new ItemStack(beer), new Object[] {"VVV", "VPV", "OOO",  'O', Block.obsidian, 'V', Block.glass, 'P', CupGlass});
			
			GameRegistry.addRecipe(new ItemStack(BucketBeer), new Object[] {"H", "B", "S",  'H', wheatBeer, 'B', new ItemStack(Item.dyePowder, 1, 15), 'S', Item.bucketWater});
		}   		
		
		private void register() {
			GameRegistry.registerBlock(beer);
			GameRegistry.registerBlock(blockTrampoline);
			GameRegistry.registerBlock(cropBeer);
			GameRegistry.registerBlock(stair);	
			GameRegistry.registerBlock(sofa);	
			GameRegistry.registerBlock(moule);
			GameRegistry.registerBlock(rubyOre);
			GameRegistry.registerBlock(saphirOre);
			GameRegistry.registerWorldGenerator(new WorldGenOre());
		}
		
	    private void name() {
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
			LanguageRegistry.addName(lunette1, "Lunettes Noir");
			LanguageRegistry.addName(lunette2, "Lunettes Blanche");
			LanguageRegistry.addName(lunette3, "Lunettes Violette");

			LanguageRegistry.addName(beer, "Tireuse de Bière");
			LanguageRegistry.addName(blockTrampoline, "Bloc de Slime");
			LanguageRegistry.addName(cropBeer, "Plante de Houblon");
			LanguageRegistry.addName(stair, "Escalier en Buche");
			
			
			LanguageRegistry.addName(Cup, "Chope");
			LanguageRegistry.addName(CupGlass, "Chope de Tireuse");
			LanguageRegistry.addName(BucketBeer, "Seau de Houblon");
			LanguageRegistry.addName(CupBeer, "Chope Pleine");
			LanguageRegistry.addName(seedBeer, "Graine de Houblon");
			LanguageRegistry.addName(wheatBeer, "Houblon");
			
			LanguageRegistry.addName(firearrow, "Flèche Eclairante");
			LanguageRegistry.addName(firebow, "Arc Eclairant");
			LanguageRegistry.addName(teleportarrow, "Ender Flèche");
			LanguageRegistry.addName(teleportbow, "Ender Arc");
			LanguageRegistry.addName(rubyOre, "Minerai de Ruby");
			LanguageRegistry.addName(saphirOre, "Minerai de Saphir");
			LanguageRegistry.addName(rubyGem, "Ruby");
			LanguageRegistry.addName(saphirGem, "Saphir");
			
			LanguageRegistry.addName(disc1, "New Disc");
			LanguageRegistry.addName(disc2, "New Disc 2");
			
			LanguageRegistry.addName(moule, "Moule a Fromage");
			
		}
	    




}
