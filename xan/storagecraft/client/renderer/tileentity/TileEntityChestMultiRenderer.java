package xan.storagecraft.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import xan.storagecraft.client.model.ModelSCChest;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.tileentity.TileEntitySC;

public class TileEntityChestMultiRenderer extends TileEntitySpecialRenderer{

	private ModelSCChest chestModel = new ModelSCChest();
	private static final ResourceLocation IRON_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/IronChest.png");
    private static final ResourceLocation GOLD_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/GoldChest.png");
    private static final ResourceLocation QUARTZ_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/QuartzChest.png");
    private static final ResourceLocation DIAMOND_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/DiamondChest.png");
	
	public void renderTileEntitySCAt(TileEntitySC te, double d, double d1, double d2, float f){
		
            ModelSCChest modelchest;

            modelchest = this.chestModel;
            int meta = te.getBlockMetadata();
            if (meta % 4 == 0)
            {
                this.bindTexture(IRON_TEXTURE);
            }
            else if (meta % 4 == 1)
            {
            	this.bindTexture(GOLD_TEXTURE);
            }
            else if (meta % 4 == 2)
            {
            	this.bindTexture(DIAMOND_TEXTURE);
            }
            else if (meta % 4 == 3)
            {
            	this.bindTexture(QUARTZ_TEXTURE);
            }
            float rotation = 0;
            
            switch(meta >> 2){
            case 1:
            	rotation = 90;
            	break;
            case 2:
            	rotation = 180;
            	break;
            case 3:
            	rotation = -90;
            	break;
            }
            
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)d, (float)d1 + 1.0F, (float)d2 + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            
            GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0F, -1F, 0F);
            float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * f;
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.ChestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.ChestLock.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		if(tileentity instanceof TileEntitySC){
			renderTileEntitySCAt((TileEntitySC)tileentity, d0, d1, d2, f);
		}
	}

}
