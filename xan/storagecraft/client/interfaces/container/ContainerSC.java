package xan.storagecraft.client.interfaces.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xan.storagecraft.StorageCraft;
import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntityQuartzChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSC extends Container{

	private TileEntitySC chest;
	private InventoryPlayer invPlayer;
	private int invRows;
	private int invColumns;
	
	public ContainerSC(InventoryPlayer invPlayer, TileEntitySC chest){
		this.chest = chest;
		this.invPlayer = invPlayer;
		if(chest instanceof TileEntityIronChest){
			invRows = 9;
			invColumns = 6;
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
			invRows = 12;
			invColumns = 6;
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
			invRows = 12;
			invColumns = 9;
			for (int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer, x, 45 + 18 * x, 232));
			}
			
			for (int y = 0; y < 3; y++){
				for (int x = 0; x < 9; x++){
					addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 45 + 18 * x, 174 + y * 18));
				}
			}
			for (int y = 0; y < 9; y++){
				for(int x = 0; x < 12; x++){
					addSlotToContainer(new Slot(chest, x + (y * 12),  17 + 18 * x, 10 + y * 18));
				}
			}
		}else if(chest instanceof TileEntityQuartzChest){
			invRows = 12;
			invColumns = 9;
			for (int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer, x, 45 + 18 * x, 232));
			}
			
			for (int y = 0; y < 3; y++){
				for (int x = 0; x < 9; x++){
					addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 45 + 18 * x, 174 + y * 18));
				}
			}
			for (int y = 0; y < 9; y++){
				for(int x = 0; x < 12; x++){
					addSlotToContainer(new Slot(chest, (x + (y * 12)) + (108 * (((TileEntityQuartzChest)chest).selectedTab)),  17 + 18 * x, 10 + y * 18));
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
        Slot slot = getSlot(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            ItemStack newItemStack = itemStack.copy();

            if (par2 >= 36) {
                if (!mergeItemStack(itemStack, 0, 36, false))
                    return null;
            }
            else if (!this.mergeItemStack(itemStack, 36, 36 + chest.getSizeInventory(), false)){
                return null;
            }
            
            if(itemStack.stackSize == 0){
            	slot.putStack(null);
            }else {
            	slot.onSlotChanged();
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemStack);
            return newItemStack;
        }
        
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

	public TileEntitySC getTileEntity() {
		return chest;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);
		if(chest instanceof TileEntityQuartzChest){
			player.sendProgressBarUpdate(this, 0, ((TileEntityQuartzChest)chest).selectedTab);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if(chest instanceof TileEntityQuartzChest){
			((TileEntityQuartzChest)chest).selectedTab = data;
			//FMLNetworkHandler.openGui(this.invPlayer.player, StorageCraft.instance, 3, chest.worldObj, chest.xCoord, chest.yCoord, chest.zCoord);
		}
	}
	
	private int oldInt = 6;
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if(chest instanceof TileEntityQuartzChest){
			for(Object player : crafters){
				if(((TileEntityQuartzChest)chest).selectedTab != oldInt){
					((ICrafting)player).sendProgressBarUpdate(this, 0, ((TileEntityQuartzChest)chest).selectedTab);
				}
			}
			oldInt = ((TileEntityQuartzChest)chest).selectedTab;
		}
	}
	
}
