package xan.storagecraft.client.renderer.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import xan.storagecraft.client.model.ModelSCChest;
import xan.storagecraft.lib.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemChestMultiRenderer implements IItemRenderer{

	private ModelSCChest chestModel = new ModelSCChest();
    private final String[] textLoc = {"textures/models/IronChest.png", "textures/models/GoldChest.png", "textures/models/DiamondChest.png", "textures/models/QuartzChest.png"};
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		ResourceLocation textToUse = new ResourceLocation(Reference.MOD_ID, textLoc[item.getItemDamageForDisplay()]);
		
		switch(type){
		case ENTITY:
			renderChest(0.0F, 1.0F, 0.0F, 1.0F, textToUse);
			break;
		case EQUIPPED:
			renderChest(0.5F, 1.5F, 0.5F, 1.0F, textToUse);
			break;
		case EQUIPPED_FIRST_PERSON:
			renderChest(0.5F, 1.5F, 0.5F, 1.0F, textToUse);
			break;
		case INVENTORY:
			renderChest(0.0F, 1.0F, 0.0F, 1.0F, textToUse);
			break;
		default: return;
		}
		
	}

	
	private void renderChest(float x, float y, float z, float scale, ResourceLocation texture){
		
		GL11.glPushMatrix();
			GL11.glScalef(scale, scale, scale);
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(-180, 1F, 0F, 0F);
			GL11.glRotatef(-90, 0.0F, 1F, 0.0F);
			
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
			
			chestModel.renderAll();
		GL11.glPopMatrix();
		
	}
}
