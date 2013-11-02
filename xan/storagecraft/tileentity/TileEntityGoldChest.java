package xan.storagecraft.tileentity;

import net.minecraft.item.ItemStack;

public class TileEntityGoldChest extends TileEntitySC {

	public TileEntityGoldChest(){
		items = new ItemStack[72];
	}
	
	@Override
	public String getInvName() {
		return "InventoryGoldChest";
	}
	
}
