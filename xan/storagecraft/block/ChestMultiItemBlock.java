package xan.storagecraft.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ChestMultiItemBlock extends ItemBlock {

	public ChestMultiItemBlock(int par1) {
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
			name = "gold";
			break;
		case 2:
			name = "diamond";
			break;
		case 3:
			name = "quartz";
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
