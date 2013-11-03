package xan.storagecraft.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityQuartzChest extends TileEntitySC {

	public int selectedTab = 0;
	
	public TileEntityQuartzChest(){
		items = new ItemStack[540];
	}
	
	@Override
	public String getInvName() {
		return "InventoryQuartzChest";
	}
	
	public void recieveTabPacketEvent(int tab){
		selectedTab = tab;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setInteger("Tab", selectedTab);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		selectedTab = compound.getInteger("Tab");
	}
	
}
