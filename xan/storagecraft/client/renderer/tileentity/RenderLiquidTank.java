package xan.storagecraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import xan.storagecraft.lib.BlockIDs;
import xan.storagecraft.tileentity.TileEntityLiquidStorageTank;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderLiquidTank implements ISimpleBlockRenderingHandler{

	int id;
	
	public RenderLiquidTank(){
		id = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        tessellator.draw();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.setColorOpaque_F(1.0f, 1.0f, 1.0f);

		
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityLiquidStorageTank){
			TileEntityLiquidStorageTank te = (TileEntityLiquidStorageTank)world.getBlockTileEntity(x, y, z);
			FluidStack flu = te.tank.getFluid();
			Icon icon = block.getIcon(0, 1);
			
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			block.setBlockBounds(0.125f, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
			renderer.setRenderBoundsFromBlock(block);
			Tessellator.instance.setColorOpaque_F(1.0f, 1.0f, 1.0f);
			renderer.renderFaceYPos(block, x, y, z, block.getIcon(0, 0));
			renderer.renderFaceZPos(block, x, y, z, icon);
			renderer.renderFaceXPos(block, x, y, z, icon);
			renderer.renderFaceYNeg(block, x, y, z, block.getIcon(0, 0));
			renderer.renderFaceZNeg(block, x, y, z, icon);
			renderer.renderFaceXNeg(block, x, y, z, icon);
			block.setBlockBounds(0, 0, 0, 1, 1, 1);
			

			if(flu != null){
				ItemStack stack = new ItemStack(flu.getFluid().getBlockID(), 1, 0);
				if(stack.getIconIndex() != null)
					icon = stack.getIconIndex();
				int amount = (int)(te.tank.getFluidAmount() / 1000);
				
				switch(amount){
				case 0:
					block.setBlockBounds(0, 0, 0, 0, 0, 0);
					break;
				case 1:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.21875f, 0.874f);
					break;
				case 2:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.3125f, 0.874f);
					break;
				case 3:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.40625f, 0.874f);
					break;
				case 4:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.5f, 0.874f);
					break;
				case 5:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.59375f, 0.874f);
					break;
				case 6:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.6875f, 0.874f);
					break;
				case 7: 
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.78125f, 0.874f);
					break;
				case 8:
					block.setBlockBounds(0.126f, 0.126f, 0.126f, 0.874f, 0.874f, 0.874f);
					break;
				}
				
				renderer.setRenderBoundsFromBlock(block);
				Tessellator.instance.setColorOpaque_F(1.0f, 1.0f, 1.0f);
				renderer.renderFaceYPos(block, x, y, z, icon);
				renderer.renderFaceZPos(block, x, y, z, icon);
				renderer.renderFaceXPos(block, x, y, z, icon);
				renderer.renderFaceYNeg(block, x, y, z, icon);
				renderer.renderFaceZNeg(block, x, y, z, icon);
				renderer.renderFaceXNeg(block, x, y, z, icon);
				block.setBlockBounds(0, 0, 0, 1, 1, 1);
				
			}
			
		}
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return id;
	}

}
