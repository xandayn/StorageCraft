package xan.storagecraft.tileentity;

import net.minecraft.item.ItemStack;

public class TileEntityDiamondChest extends TileEntitySC {

	public TileEntityDiamondChest(){
		items = new ItemStack[108];
	}
	
	@Override
	public String getInvName() {
		return "InventoryDiamondChest";
	}
	
}
