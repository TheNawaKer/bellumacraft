package mod.legendaire45.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCheval extends ModelBase
{
  //fields
    ModelRenderer pied1;
    ModelRenderer pied2;
    ModelRenderer pied4;
    ModelRenderer pied3;
    ModelRenderer corps;
    ModelRenderer queue;
    ModelRenderer cou;
    ModelRenderer tete;
    ModelRenderer oreille1;
    ModelRenderer oreille2;
  
  public ModelCheval()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      pied1 = new ModelRenderer(this, 0, -2);
      pied1.addBox(0F, 0F, 0F, 3, 14, 3);
      pied1.setRotationPoint(2F, 10F, -7F);
      pied1.setTextureSize(128, 32);
      pied1.mirror = true;
      setRotation(pied1, 0F, 1.570796F, 0F);
      pied2 = new ModelRenderer(this, 0, 0);
      pied2.addBox(0F, 0F, 0F, 3, 14, 3);
      pied2.setRotationPoint(-5F, 10F, -7F);
      pied2.setTextureSize(128, 32);
      pied2.mirror = true;
      setRotation(pied2, 0F, 1.570796F, 0F);
      pied4 = new ModelRenderer(this, 0, 0);
      pied4.addBox(0F, 0F, 0F, 3, 14, 3);
      pied4.setRotationPoint(-5F, 10F, 9F);
      pied4.setTextureSize(128, 32);
      pied4.mirror = true;
      setRotation(pied4, 0F, 1.570796F, 0F);
      pied3 = new ModelRenderer(this, 0, 0);
      pied3.addBox(0F, 0F, 0F, 3, 14, 3);
      pied3.setRotationPoint(2F, 10F, 10F);
      pied3.setTextureSize(128, 32);
      pied3.mirror = true;
      setRotation(pied3, 0F, 1.570796F, 0F);
      corps = new ModelRenderer(this, 4, 14);
      corps.addBox(0F, 0F, 0F, 20, 8, 10);
      corps.setRotationPoint(-5F, 4F, 10F);
      corps.setTextureSize(128, 32);
      corps.mirror = true;
      setRotation(corps, 0F, 1.570796F, 0F);
      queue = new ModelRenderer(this, 55, 0);
      queue.addBox(0F, -1F, 0F, 2, 14, 2);
      queue.setRotationPoint(-1F, 4F, 10F);
      queue.setTextureSize(128, 32);
      queue.mirror = true;
      setRotation(queue, 0F, 1.570796F, 0.418879F);
      cou = new ModelRenderer(this, 35, 0);
      cou.addBox(0F, 0F, 0F, 5, 10, 4);
      cou.setRotationPoint(-2F, -4F, -9F);
      cou.setTextureSize(128, 32);
      cou.mirror = true;
      setRotation(cou, 0F, 1.570796F, 0.4014257F);
      tete = new ModelRenderer(this, 69, 0);
      tete.addBox(0F, 0F, 0F, 9, 4, 6);
      tete.setRotationPoint(-3F, -5F, -9F);
      tete.setTextureSize(128, 32);
      tete.mirror = true;
      setRotation(tete, 0F, 1.570796F, 0.2617994F);
      oreille1 = new ModelRenderer(this, 16, 0);
      oreille1.addBox(0F, 0F, 0F, 2, 2, 1);
      oreille1.setRotationPoint(1.5F, -6.5F, -10F);
      oreille1.setTextureSize(128, 32);
      oreille1.mirror = true;
      setRotation(oreille1, 0F, 1.570796F, 0.2617994F);
      oreille2 = new ModelRenderer(this, 16, 0);
      oreille2.addBox(0F, 0F, 0F, 2, 2, 1);
      oreille2.setRotationPoint(-2.5F, -6.5F, -10F);
      oreille2.setTextureSize(128, 32);
      oreille2.mirror = true;
      setRotation(oreille2, 0F, 1.570796F, 0.2617994F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    pied1.render(f5);
    pied2.render(f5);
    pied4.render(f5);
    pied3.render(f5);
    corps.render(f5);
    queue.render(f5);
    cou.render(f5);
    tete.render(f5);
    oreille1.render(f5);
    oreille2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
