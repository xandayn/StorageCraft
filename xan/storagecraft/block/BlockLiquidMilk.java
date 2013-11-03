package xan.storagecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import xan.storagecraft.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidMilk extends BlockFluidFinite{

	@SideOnly(Side.CLIENT)
	public Icon stillIcon;
	public Icon flowIcon;
	
	public BlockLiquidMilk(int id, Fluid fluid) {
		super(id, fluid, Material.water);
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		stillIcon = par1IconRegister.registerIcon(Reference.MOD_ID+":milk_still");
		flowIcon = par1IconRegister.registerIcon(Reference.MOD_ID+":milk_flow");
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		if(par1 == 0 || par1 == 1)
			return stillIcon;
		
		return flowIcon;
	}
}
