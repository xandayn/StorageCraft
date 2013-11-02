package xan.storagecraft.proxy;

import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntityQuartzChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void initRenderers(){
		
	}
	
	public void registerTileEntities(){
		
		GameRegistry.registerTileEntity(TileEntitySC.class, "TESC");
		GameRegistry.registerTileEntity(TileEntityIronChest.class, "TEIC");
		GameRegistry.registerTileEntity(TileEntityGoldChest.class, "TEGC");
		GameRegistry.registerTileEntity(TileEntityDiamondChest.class, "TEDC");
		GameRegistry.registerTileEntity(TileEntityQuartzChest.class, "TEQC");
	}
	
}
