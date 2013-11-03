package xan.storagecraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import xan.storagecraft.client.interfaces.gui.GuiHandler;
import xan.storagecraft.config.ConfigHandler;
import xan.storagecraft.event.SCEventHandler;
import xan.storagecraft.lib.Content;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.network.PacketHandler;
import xan.storagecraft.proxy.CommonProxy;
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
@NetworkMod(channels = {Reference.CHANNEL}, clientSideRequired=true, serverSideRequired=false, packetHandler = PacketHandler.class)
public class StorageCraft {


	
	@Instance(Reference.MOD_ID)
	public static StorageCraft instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_SIDE, serverSide=Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new SCEventHandler());
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		proxy.initRenderers();
		proxy.registerTileEntities();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){	
		Content.registerBlocks();
		
		GameRegistry.addRecipe(new ItemStack(Content.chestBlock, 1, 0), "XXX", "X X", "XXX", 'X', Item.ingotIron);
		GameRegistry.addRecipe(new ItemStack(Content.chestBlock, 1, 1), "XXX", "X X", "XXX", 'X', Item.ingotGold);
		GameRegistry.addRecipe(new ItemStack(Content.chestBlock, 1, 2), "XXX", "X X", "XXX", 'X', Item.diamond);
		
		LanguageRegistry.addName(new ItemStack(Content.chestBlock, 1, 0), "Iron Chest");
		LanguageRegistry.addName(new ItemStack(Content.chestBlock, 1, 1), "Gold Chest");
		LanguageRegistry.addName(new ItemStack(Content.chestBlock, 1, 2), "Diamond Chest");
		LanguageRegistry.addName(new ItemStack(Content.chestBlock, 1, 3), "Quartz Chest");
		
		new GuiHandler();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
}
