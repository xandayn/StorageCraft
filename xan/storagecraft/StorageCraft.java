package xan.storagecraft;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import xan.storagecraft.block.BlockChestMulti;
import xan.storagecraft.block.SCChestItemBlock;
import xan.storagecraft.client.interfaces.gui.GuiHandler;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.proxy.CommonProxy;
import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntityQuartzChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class StorageCraft {

	public static Block chestBlock;
	
	@Instance(Reference.MOD_ID)
	public static StorageCraft instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_SIDE, serverSide=Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){	
		proxy.initRenderers();
		
		chestBlock = new BlockChestMulti(500).setUnlocalizedName("test");
		
		GameRegistry.registerBlock(chestBlock, SCChestItemBlock.class, Reference.MOD_ID+chestBlock.getUnlocalizedName().substring(5));
		
		GameRegistry.registerTileEntity(TileEntitySC.class, "TESC");
		GameRegistry.registerTileEntity(TileEntityIronChest.class, "TEIC");
		GameRegistry.registerTileEntity(TileEntityGoldChest.class, "TEGC");
		GameRegistry.registerTileEntity(TileEntityDiamondChest.class, "TEDC");
		GameRegistry.registerTileEntity(TileEntityQuartzChest.class, "TEQC");

		
		LanguageRegistry.addName(new ItemStack(chestBlock, 1, 0), "Iron Chest");
		LanguageRegistry.addName(new ItemStack(chestBlock, 1, 1), "Gold Chest");
		LanguageRegistry.addName(new ItemStack(chestBlock, 1, 2), "Diamond Chest");
		LanguageRegistry.addName(new ItemStack(chestBlock, 1, 3), "Quartz Chest");
		
		new GuiHandler();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
}
