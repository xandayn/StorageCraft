package xan.storagecraft.client.interfaces.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntitySC;

public class ContainerSC extends Container{

	private TileEntitySC chest;
	private InventoryPlayer invPlayer;
	
	public ContainerSC(InventoryPlayer invPlayer, TileEntitySC chest){
		this.chest = chest;
		this.invPlayer = invPlayer;
		//chest.openChest();
		if(chest instanceof TileEntityIronChest){
			for (int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 198));
			}
			
			for (int y = 0; y < 3; y++){
				for (int x = 0; x < 9; x++){
					addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 140 + y * 18));
				}
			}
			for (int y = 0; y < 6; y++){
				for(int x = 0; x < 9; x++){
					addSlotToContainer(new Slot(chest, x + (y * 9), 8 + 18 * x, 18 + y * 18));
				}
			}
		}
		else if(chest instanceof TileEntityGoldChest){
			for (int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer, x, 35 + 18 * x, 198));
			}
			
			for (int y = 0; y < 3; y++){
				for (int x = 0; x < 9; x++){
					addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 35 + 18 * x, 140 + y * 18));
				}
			}
			for (int y = 0; y < 6; y++){
				for(int x = 0; x < 12; x++){
					addSlotToContainer(new Slot(chest, x + (y * 12), 8 + 18 * x, 18 + y * 18));
				}
			}
		}
		else if(chest instanceof TileEntityDiamondChest){
			for (int y = 0; y < 9; y++){
				for(int x = 0; x < 12; x++){
					addSlotToContainer(new Slot(chest, x + (y * 12), 8 + 18 * x, 18 + y * 18));
				}
			}
		}
		chest.openChest();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return chest.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}
	
	public IInventory getInv(){
		return (IInventory)chest;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		chest.closeChest();
	}
	
}
