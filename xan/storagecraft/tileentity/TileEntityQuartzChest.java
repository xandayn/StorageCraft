package xan.storagecraft.tileentity;

import net.minecraft.item.ItemStack;

public class TileEntityQuartzChest extends TileEntitySC {

	public TileEntityQuartzChest(){
		items = new ItemStack[54];
	}
	
	@Override
	public String getInvName() {
		return "InventoryQuartzChest";
	}
	
}
