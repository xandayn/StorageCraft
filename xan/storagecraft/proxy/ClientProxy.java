package xan.storagecraft.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import xan.storagecraft.client.renderer.item.ItemChestMultiRenderer;
import xan.storagecraft.client.renderer.tileentity.RenderLiquidTank;
import xan.storagecraft.client.renderer.tileentity.TileEntityChestMultiRenderer;
import xan.storagecraft.lib.BlockIDs;
import xan.storagecraft.lib.RenderIDs;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySC.class, new TileEntityChestMultiRenderer());
		MinecraftForgeClient.registerItemRenderer(BlockIDs.CHEST_MULTI_ID, new ItemChestMultiRenderer());
		RenderIDs.CHEST_ID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderLiquidTank tankRenderer = new RenderLiquidTank();
		RenderIDs.TANK_ID = tankRenderer.getRenderId();
		RenderingRegistry.registerBlockHandler(tankRenderer);
	}
}
