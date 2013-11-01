package xan.storagecraft.proxy;

import xan.storagecraft.client.renderer.tileentity.TileEntityChestMultiRenderer;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySC.class, new TileEntityChestMultiRenderer());
	}
}
