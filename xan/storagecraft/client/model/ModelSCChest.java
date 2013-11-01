package xan.storagecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSCChest extends ModelBase
{
  //fields
    public ModelRenderer ChestBase;
    public ModelRenderer ChestLid;
    public ModelRenderer ChestLock;
  
  public ModelSCChest()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      ChestBase = new ModelRenderer(this, 0, 19);
      ChestBase.addBox(-7F, 0F, -7F, 14, 10, 14);
      ChestBase.setRotationPoint(0F, 14F, 0F);
      ChestBase.setTextureSize(64, 64);
      ChestBase.mirror = true;
      setRotation(ChestBase, 0F, 0F, 0F);
      
      ChestLid = new ModelRenderer(this, 0, 0);
      ChestLid.addBox(-7F, -4F, -14F, 14, 5, 14);
      ChestLid.setRotationPoint(0F, 14F, 7F);
      ChestLid.setTextureSize(64, 64);
      ChestLid.mirror = true;
      setRotation(ChestLid, 0F, 0F, 0F);
      
      ChestLock = new ModelRenderer(this, 0, 0);
      ChestLock.addBox(-1F, -1F, -15F, 2, 4, 1);
      ChestLock.setRotationPoint(0F, 14F, 7F);
      ChestLock.setTextureSize(64, 64);
      ChestLock.mirror = true;
      setRotation(ChestLock, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    ChestBase.render(f5);
    ChestLid.render(f5);
    ChestLock.render(f5);
  }
  
  public void renderAll(){
	 ChestBase.render(0.0625f);
	 ChestLid.render(0.0625f);
	 ChestLock.render(0.0625f);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
