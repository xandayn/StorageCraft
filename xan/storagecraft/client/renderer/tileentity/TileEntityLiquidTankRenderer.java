package xan.storagecraft.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import xan.storagecraft.client.model.ModelLiquidTank;
import xan.storagecraft.tileentity.TileEntityLiquidStorageTank;

public class TileEntityLiquidTankRenderer extends TileEntitySpecialRenderer{

	private ModelLiquidTank modelTank = new ModelLiquidTank();
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		if(tileentity instanceof TileEntityLiquidStorageTank){
			renderTankAt((TileEntityLiquidStorageTank)tileentity, d0, d1, d2, f);
		}
	}
	
	public void renderTankAt(TileEntityLiquidStorageTank tank, double x, double y, double z, float partialTickTime){
		
	}

}
