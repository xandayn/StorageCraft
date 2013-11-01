package xan.storagecraft.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SCChestItemBlock extends ItemBlock {

	public SCChestItemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		
		String name = "";
		switch(itemstack.getItemDamage()){
		case 0:
			name = "iron";
			break;
		case 1:
			name = "quartz";
			break;
		case 2:
			name = "gold";
			break;
		case 3:
			name = "diamond";
			break;
		default:
			name = "error";
		}
		
		return getUnlocalizedName()+"."+name;
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
}
